package controllers;

import play.db.jpa.Transactional;
import play.mvc.Result;
import services.AdministratorService;

import javax.inject.Inject;

/**
 * The type Administrator controller.
 */
public class AdministratorController extends BaseController {

	private AdministratorService service;

	/**
	 * Sets service.
	 *
	 * @param service the service
	 */
	@Inject
	public void setService(final AdministratorService service) {
		this.service = service;
	}

	/**
	 * Gets all users.
	 *
	 * @return the all users
	 */
	@Transactional(readOnly = true)
	public Result getStatistics() {

		return wrapForAdmin(() -> this.service.getStatistics());
	}

}
