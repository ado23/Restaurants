package services;

import models.helpers.AdministratorStatistics;
import models.tables.Reservation;
import models.tables.Restaurant;
import models.tables.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The type Administrator service.
 */
@Singleton
public class AdministratorService extends BaseService {

	@Inject
	private AdministratorService() { }

	public AdministratorStatistics getStatistics() {

		AdministratorStatistics adminStats = new AdministratorStatistics() ;

		adminStats.setNumberOfUsers(getSession().createCriteria(User.class).list().size());
		adminStats.setNumberOfRestaurants(getSession().createCriteria(Restaurant.class).list().size());
		adminStats.setNumberOfReservations(getSession().createCriteria(Reservation.class).list().size());

		return adminStats;
	}

}
