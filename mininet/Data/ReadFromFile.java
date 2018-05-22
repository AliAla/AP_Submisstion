package Data;

/**
 * @author Ali Alahmari and Md Shakil Khan
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
Class to read text files and store information into arraylists
*/
public class ReadFromFile {
	// create a database instance to enter records into it
	public static Database db = null;

	public void setUpDatabase() {
		db = new Database();
		db.create();
		db.createTables();
	}

	// method to read profiles text file
	public void readProfiles() throws IOException {
		// open the text file in read mode
		BufferedReader br = new BufferedReader(new FileReader("people.txt"));
		String line = "";
		// read the file line by line
		while ((line = br.readLine()) != null) {
			// split each line to get values
			String[] recs = line.split(",");
			// inserting records into database
			db.insertProfile(recs[0], recs[1], recs[2], recs[3].charAt(0), Integer.parseInt(recs[4]), recs[5]);
		}
	}

	// method to read relations text file
	public void readRelations() throws IOException {
		// open the text file in read mode
		BufferedReader br = new BufferedReader(new FileReader("relations.txt"));
		String line = "";
		// read the file line by line
		while ((line = br.readLine()) != null) {
			// split each line to get values
			String[] recs = line.split(",");
			// insert relation record into database
			db.insertRelation(recs[0], recs[1], recs[2]);
		}
	}

}
