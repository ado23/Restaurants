package models.helpers;

/**
 * The type Administrator statistics.
 */
public class AdministratorStatistics {

	private Integer numberOfUsers;
	private Integer numberOfRestaurants;
	private Integer numberOfReservations;

	public AdministratorStatistics() {}

	public Integer getNumberOfUsers() { return numberOfUsers; }

	public void setNumberOfUsers(Integer numberOfUsers) { this.numberOfUsers = numberOfUsers; }


	public Integer getNumberOfRestaurants() { return numberOfRestaurants; }

	public void setNumberOfRestaurants(Integer numberOfRestaurants) { this.numberOfRestaurants = numberOfRestaurants; }


	public Integer getNumberOfReservations() { return numberOfReservations; }

	public void setNumberOfReservations(Integer numberOfReservations) { this.numberOfReservations = numberOfReservations; }
}
