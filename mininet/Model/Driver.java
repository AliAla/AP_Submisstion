package Model;

/**
 * @author Ali Alahmari and Md Shakil Khan
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Data.Database;
import Data.ReadFromFile;
import View.GUI;

/*
class to hold implementation of all the methods
*/
public class Driver {

	// databse instance
	Database db = new Database();

	// array lists to store text file information
	public static int selected = -1;
	public static ArrayList<Profile> profiles = new ArrayList<Profile>();
	public static ArrayList<Relation> relations = new ArrayList<Relation>();

	// method to start the application
	public void start() throws IOException {
		// create read instacne to read data from text files
		ReadFromFile data = new ReadFromFile();
		// setup the database
		data.setUpDatabase();
		// call method to read and load profiles to databse
		data.readProfiles();
		// call method to read and load relations to database
		data.readRelations();
		// get profiles from database
		profiles = db.getProfiles();
		// get relations from database
		relations = db.getRelations();

		// create GUI instance
		GUI gui = new GUI();
		// call method to setup and display the GUI
		gui.setUp();
	}

	// method to add a new person to the network
	public void addNew() throws Exceptions {
		// ask for necessary details
		String name = JOptionPane.showInputDialog("Name: ");
		String pic = JOptionPane.showInputDialog("Picture: ");
		String Status = JOptionPane.showInputDialog("Status: ");
		char gender = JOptionPane.showInputDialog("Gender: ").charAt(0);
		int age = Integer.parseInt(JOptionPane.showInputDialog("age: "));
		String state = JOptionPane.showInputDialog("State: ");
		if (age <= 16) {// if the person's age is less than 6, ask to add his parents
			// ask for the parents' names
			String parent1 = JOptionPane.showInputDialog("Enter Your First Parent's Name: ");
			String parent2 = JOptionPane.showInputDialog("Enter Your Second Parent's Name: ");
			// search them and add to his profile
			for (int a = 0; a < relations.size(); a++) {
				if (parent1.equalsIgnoreCase(relations.get(a).getName1())
						&& parent2.equalsIgnoreCase(relations.get(a).getName2())
						&& relations.get(a).getRelation().equals("couple")
						|| parent1.equalsIgnoreCase(relations.get(a).getName2())
								&& parent2.equalsIgnoreCase(relations.get(a).getName1())
								&& relations.get(a).getRelation().equals("couple")) {
					// create a new profile
					Profile p = new Profile(name, pic, Status, gender, age, state);

					// and add it to list of profiles
					profiles.add(p);
					db.insertProfile(name, pic, Status, gender, age, state);
					// display success message to user
					JOptionPane.showMessageDialog(null, "Person Added To Network!");
					// add a new relation aas well
					Relation r = new Relation(name, parent1, "parent");
					relations.add(r);
					r = new Relation(name, parent2, "parent");
					relations.add(r);
				} else
					throw new Exceptions("NoParentException");
			}
		} else {// if age is not under 16, add them to network
				// create a new profile
			Profile p = new Profile(name, pic, Status, gender, age, state);
			db.insertProfile(name, pic, Status, gender, age, state);
			// and add it to list of profiles
			profiles.add(p);
			// display success message to user
			JOptionPane.showMessageDialog(null, "Person Added To Network!");
		}
	}

	// Lists Everyone in the Network
	public void listEveryone() {
		for (int i = 1; i < profiles.size(); i++) {

			JOptionPane.showMessageDialog(null, profiles.get(i).getName());
		}
	}

	// method to select a user profile
	public void selectPerson() {
		boolean found = false;
		// ask for person's name
		String name = JOptionPane.showInputDialog("Name: ");
		// iterate the list and search for that person
		for (int a = 0; a < profiles.size(); a++) {
			if (name.equalsIgnoreCase(profiles.get(a).getName())) {
				selected = a;
				found = true;
			}
		}
		if (!found) // if not found, display the error message
			JOptionPane.showMessageDialog(null, "Person Not Found!");
		else // if found, display success message with selected person's name
			JOptionPane.showMessageDialog(null, profiles.get(selected).getName() + " selected.");
	}

	// method to display information for a selected person
	public void displayProfile() {
		// display the selected person's details
		JOptionPane.showMessageDialog(null, profiles.get(selected));
	}

	// method to updae a user profile on network
	public void updateProfile() {
		// displa a menu to user for updation
		String menu = "What do you want to update?\n" + "1. Picture\n" + "2. Status\n" + "3. Gender\n" + "4. Age\n"
				+ "5. State\n" + "Select an option: ";
		// ask for user's choice
		int choice = Integer.parseInt(JOptionPane.showInputDialog(menu));
		switch (choice) {
		case 1:
			// update the picture if user wants to update image name
			String name = JOptionPane.showInputDialog("New Picture: ");
			profiles.get(selected).setImage(name);
			break;
		case 2:
			// update the Status if user wants to update his Status
			String Status = JOptionPane.showInputDialog("new Status: ");
			profiles.get(selected).setStatus(Status);
			break;
		case 3:
			// update the genderif user wants to update his/her gender
			char gender = JOptionPane.showInputDialog("Gender: ").charAt(0);
			profiles.get(selected).setGender(gender);
			break;
		case 4:
			// update the age if user wants to update his/her age
			int age = Integer.parseInt(JOptionPane.showInputDialog("Age: "));
			profiles.get(selected).setAge(age);
			break;
		case 5:
			// update the state if user wants to update state
			String state = JOptionPane.showInputDialog("State: ");
			profiles.get(selected).setState(state);
			break;
		default:
			// display error message on unexpected inputs
			JOptionPane.showMessageDialog(null, "Invalid Input!");
			break;
		}
	}

	// method to delete the selected profile
	public void deleteProfile() {
		// display the success message
		JOptionPane.showMessageDialog(null, profiles.get(selected).getName() + " removed.");
		profiles.remove(selected);// delete the person selected

	}

	// method to connect two people on the network
	public void connectPersons() throws Exceptions {
		int age1 = 0, age2 = 0;
		// ask for both names and relation between them
		String name1 = JOptionPane.showInputDialog("First Person Name: ");
		String name2 = JOptionPane.showInputDialog("Second Person Name: ");
		String relation = JOptionPane.showInputDialog("Relationship: ");
		// search for first person and get its age
		for (int a = 0; a < profiles.size(); a++) {
			if (name1.equalsIgnoreCase(profiles.get(a).getName())) {
				age1 = profiles.get(a).getAge();
			}
		}
		// search for second person and get its age
		for (int a = 0; a < profiles.size(); a++) {
			if (name2.equalsIgnoreCase(profiles.get(a).getName())) {
				age2 = profiles.get(a).getAge();
			}
		}
		// check if their ages are not vaid
		if (age1 < 0 || age1 > 150 || age2 < 0 || age2 > 150) {
			// throw an exception
			throw new Exceptions("NoSuchAgeException");
		}
		// check if their age is less than 2 years
		else if (age1 <= 2 || age2 <= 2) {
			if (relation.equalsIgnoreCase("friend"))
				// throw exception that they cant have friends
				throw new Exceptions("TooYoungException");
			else if (relation.equalsIgnoreCase("sibling")) {// if they want to be siblings
				Relation r = new Relation(name1, name2, relation);// create a relation between them
				relations.add(r);
			}
		}
		// if they are young like 3-16 years old
		else if (age1 < 16 && age2 < 16) {
			// get their age difference
			int dif = age1 - age2;
			if (dif < 0) {
				dif = dif * (-1);
			}
			// if their age difference is greater than 3 year
			if (dif > 3) {
				// they cant be friends
				throw new Exceptions("NotToBeFriendsException");
			} else { // if difference is 3 or less than 3 years
						// create a relation between them
				Relation r = new Relation(name1, name2, relation);
				relations.add(r);
			}
		}
		// if one is adult and second is young
		else if ((age1 < 16 && age2 >= 16) || (age1 >= 16 && age2 < 16)) {
			// throw an exception
			throw new Exceptions("NotToBeFriendsException");
		} else {// create relations between them in all other cases
			Relation r = new Relation(name1, name2, relation);
			relations.add(r);
		}
	}

	// method to check if two people are friends or not
	public void directFriends() {
		// ask for both names
		String name1 = JOptionPane.showInputDialog("First Person Name: ");
		String name2 = JOptionPane.showInputDialog("Second Person Name: ");
		boolean friends = false;
		// iterate the relations list and check if they are in any relations
		for (int a = 0; a < relations.size(); a++) {
			if ((name1.equals(relations.get(a).getName1()) || name1.equals(relations.get(a).getName2()))
					&& (name2.equals(relations.get(a).getName2()) || name2.equals(relations.get(a).getName1()))) {
				// display sucess message
				JOptionPane.showMessageDialog(null, "Yes, They are friends!");
				// and set friends to true
				friends = true;
			}
		}
		if (!friends) {// if they happened to be not friends
			// display the error msg
			JOptionPane.showMessageDialog(null, "No, They are not friends!");
		}
	}

	// method to find parent's children or children's parents
	public void findPeople() {
		// ask for user's choice
		String menu = "What do you want to search?\n" + "1. Parent's Child(ren)\n" + "2. Child's Parents\n"
				+ "Select an option: ";
		// get the user input
		int choice = Integer.parseInt(JOptionPane.showInputDialog(menu));
		switch (choice) {
		case 1:// if user wants to searhc for parent's children
				// ask for parent's name
			String name = JOptionPane.showInputDialog("Parent's Name: ");
			String names = "";
			// find the children in list and siplay their names
			for (int a = 0; a < relations.size(); a++) {
				if (name.equalsIgnoreCase(relations.get(a).getName1())
						&& relations.get(a).getRelation().equals("parent")) {
					names += relations.get(a).getName2() + "\n";
				} else if (name.equalsIgnoreCase(relations.get(a).getName2())
						&& relations.get(a).getRelation().equals("parent")) {
					names += relations.get(a).getName1() + "\n";
				}
			}
			// display the children names
			JOptionPane.showMessageDialog(null, names);
			break;
		case 2:// if user wants to search for child's parents
				// ask for child's name
			name = JOptionPane.showInputDialog("Child's Name: ");
			// find the parents in list and display their names
			names = "";
			for (int a = 0; a < relations.size(); a++) {
				if (name.equalsIgnoreCase(relations.get(a).getName1())
						&& relations.get(a).getRelation().equals("parent")) {
					names += relations.get(a).getName2() + "\n";
				} else if (name.equalsIgnoreCase(relations.get(a).getName2())
						&& relations.get(a).getRelation().equals("parent")) {
					names += relations.get(a).getName1() + "\n";
				}
			}
			// display the names
			JOptionPane.showMessageDialog(null, names);
			break;
		}
	}
}