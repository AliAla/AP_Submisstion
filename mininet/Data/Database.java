package Data;

/**
 * @author Ali Alahmari and Md Shakil Khan
 *
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Model.Profile;
import Model.Relation;

/*
A class to handle the database
*/
public class Database {
	// method to connect to database
	public Connection connect() {
		// database path/url
		String url = "jdbc:sqlite:mininet.db";
		Connection conn = null;
		// connect to database
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return conn;
	}

	// method to create the db if it does not exist
	public void create() {
		// db path
		String url = "jdbc:sqlite:mininet.db";
		// connect to the database
		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {// if connection succeeded, prin the success message after creating the database
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	// metho to create tables in the database
	public void createTables() {
		// database path
		String url = "jdbc:sqlite:mininet.db";
		// query to create the table for proiles
		String sql = "CREATE TABLE IF NOT EXISTS profile (\n" + "	name text PRIMARY KEY,\n" + "	image text,\n"
				+ "	role text,\n" + "	gender text,\n" + "	age integer,\n" + "	state text\n" + ");";
		// execute the query for profiles table
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("table created");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		// query to create the table for relations
		sql = "CREATE TABLE IF NOT EXISTS relations (\n" + "	name1 text,\n" + "	name2 text,\n" + "	relation text\n"
				+ ");";
		// execute the query for relations table
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("table created");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	// method to get datta and insert into database
	public void insertProfile(String name, String image, String role, char gender, int age, String state) {
		// query to insert data into db
		String sql = "INSERT INTO profile(name,image,role,gender,age,state) VALUES(?,?,?,?,?,?)";
		// execute teh query and insert data into table profile
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, image);
			pstmt.setString(3, role);
			pstmt.setString(4, gender + "");
			pstmt.setInt(5, age);
			pstmt.setString(6, state);
			pstmt.executeUpdate();
			System.out.println("row is created");
			conn.commit();
		} catch (SQLException e) {
		}
	}

	// method to get datta and insert into relations table
	public void insertRelation(String name1, String name2, String relation) {
		// query to insert data into db
		String sql = "INSERT INTO relations(name1,name2,relation) VALUES(?,?,?)";
		// execute teh query and insert data into table relations
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name1);
			pstmt.setString(2, name2);
			pstmt.setString(3, relation);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	// method to read profiles from databse
	public ArrayList<Profile> getProfiles() {
		// create list to store records fetched from databse
		ArrayList<Profile> list = new ArrayList<Profile>();
		// query to get records from db
		String sql = "SELECT DISTINCT name, image, role, gender, age, state FROM profile";
		// get all records using loop
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				// create profile objects
				Profile p = new Profile(rs.getString("name"), rs.getString("image"), rs.getString("role"),
						rs.getString("gender").charAt(0), rs.getInt("age"), rs.getString("state"));
				// add them to list
				list.add(p);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return list;
	}

	public ArrayList<Relation> getRelations() {
		// create list to store records fetched from databse
		ArrayList<Relation> list = new ArrayList<Relation>();
		// query to get records from db
		String sql = "SELECT DISTINCT name1, name2, relation FROM relations";
		// get all records using loop
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				// create relation objects
				Relation r = new Relation(rs.getString("name1"), rs.getString("name2"), rs.getString("relation"));
				// add them to list
				list.add(r);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return list;
	}
}