package com.yash.fromdatabase;

/**
 * This is the user POJO which will act as data traveler throw different layers
 * of the project.
 * 
 * @author shyam.patidar
 *
 */
public class User {
	/**
	 * Unique id of the user
	 */
	private int id;

	/**
	 * name of the user
	 */
	private String name;
	
	/**
	 * contact of the user
	 */
	private String contact;

	/**
	 * email of the user
	 */
	private String email;

	/**
	 * unique registrationId of the user 
	 */
	private String registrationId;
	/**
	 * password of the user
	 */
	private String password;
	/**
	 * enabled 1 when active user
	 * and 0 when blocked
	 */
	private int enabled;
	
	/**
	 * username is the unique loginname which is registrationId in our case
	 */
	private String username;

	/**
	 *   default constructor of user
	 */
	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
