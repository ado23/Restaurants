package controllers;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.inject.Inject;
import models.helpers.forms.ImageUploadForm;
import play.Configuration;
import play.db.jpa.Transactional;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;

/**
 * The type Amazon controller.
 */
public class AmazonController extends BaseController {

	private static final String REGEX_PATTERN = "\\.";
	private static final String AWS_BUCKET_PATH = "AWSConfig.bucket";
	private static final String AWS_KEY_PATH = "AWSConfig.key";
	private static final String AWS_SECRET_PATH = "AWSConfig.secret";

	private static final String FROM_FILE_KEY = "file";

	private static final String MINUS = "-";
	private static final String DOT = ".";

	private static final String EMPTY_JSON = "{}";

	@Inject
	private static Configuration configuration;

	/**
	 * File upload result.
	 *
	 * @return the result
	 */
	@Transactional
	public Result fileUpload() {
		return wrapForAdmin(() -> {
			ImageUploadForm imageUploadForm = formFactory.form(ImageUploadForm.class).bindFromRequest().get();

			Http.MultipartFormData<File> body = request().body().asMultipartFormData();
			Http.MultipartFormData.FilePart<File> picture = body.getFile(FROM_FILE_KEY);

			String fileName = picture.getFilename();
			String contentType = picture.getContentType();
			File file = picture.getFile();

			String[] filenameArray = fileName.split(REGEX_PATTERN);
			String ext = filenameArray[filenameArray.length - 1];

			String bucketName = configuration.getString(AWS_BUCKET_PATH);
			String keyName = imageUploadForm.getRestaurantId() + MINUS + imageUploadForm.getImageType() + DOT + ext;

			BasicAWSCredentials awsCreds = new BasicAWSCredentials(configuration.getString(AWS_KEY_PATH), configuration.getString(AWS_SECRET_PATH));
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withRegion(Regions.DEFAULT_REGION)
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
					.build();

			AccessControlList acl = new AccessControlList();
			acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

			ObjectMetadata omd = new ObjectMetadata();
			omd.setContentType(contentType);

			s3Client.putObject(new PutObjectRequest(bucketName, keyName, file).withAccessControlList(acl).withMetadata(omd));

			return EMPTY_JSON;
		});
	}

}
