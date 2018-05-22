package Model;

/**
 * @author Ali Alahmari and Md Shakil Khan
 *
 */

/*
 * Calss to display information for a Profile
 */
public class Profile {
	// profile class attributes
	private String name, image, status, state;
	private char gender;
	private int age;

	// profile class constructor
	public Profile(String name, String image, String status, char gender, int age, String state) {
		this.name = name;
		this.image = image;
		this.status = status;
		this.state = state;
		this.gender = gender;
		this.age = age;
	}

	/*
	 * setter and getter methods for all the attributes
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// method to return whole information in a string form
	@Override
	public String toString() {
		return "Name: " + name + "\nImage: " + image + "\nstatus: " + status + "\nGender: " + gender + "\nAge: " + age
				+ "\nState: " + state;
	}

}
