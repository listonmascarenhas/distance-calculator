package main.java;

public class UserObject {
	private double latitude, longitude;
	private long userId;
	private String name;

	public UserObject(double latitude, double longitude, long userId, String name) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.userId = userId;
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitute() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
