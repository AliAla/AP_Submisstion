package Model;

/**
 * @author Ali Alahmari and Md Shakil Khan
 *
 */

/*
 * Relation class to hold relation information
 */
public class Relation {

	// class attributes
	private String name1;
	private String name2;
	private String relation;

	// class constructor to set values
	public Relation(String name1, String name2, String relation) {
		this.name1 = name1;
		this.name2 = name2;
		this.relation = relation;
	}

	/*
	 * setter and getter methods for all attributes
	 */
	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	// method to return whole information in a string form
	@Override
	public String toString() {
		return "Relation{" + "name1=" + name1 + ", name2=" + name2 + ", relation=" + relation + '}';
	}

}
