package View;
/**
 * 
 */

/**
 * @author Ali Alahmari and Md Shakil Khan
 *
 */
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Model.Driver;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

/*
This class is to implement a graphical user interface for the user
*/
public class GUI {
	private JFrame frame;
	private JPanel MenuPanel;
	private JPanel WelComePanel;

	// methos to set up the GUI
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setUp() {
		// create a frame and set its components
		JFrame frame = new JFrame("MiniNet");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/Resources/network_add.png")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(439, 228);
		frame.setLocationRelativeTo(null);

		// Process class instance to access its methods
		Driver p = new Driver();

		// =======create a panel for frame===========
		final JPanel MenuPanel = new JPanel();
		MenuPanel.setLayout(null);
		MenuPanel.setVisible(false);

		JButton ShowEveryone = new JButton("Show Everyone");
		ShowEveryone.setFont(new Font("Tahoma", Font.PLAIN, 10));
		ShowEveryone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p.listEveryone();
			}
		});
		ShowEveryone.setBounds(15, 126, 138, 25);
		MenuPanel.add(ShowEveryone);

		// button to select a person from network
		JButton select = new JButton("Select Person");
		select.setBounds(163, 18, 120, 25);
		select.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MenuPanel.add(select);
		// action listener for the select person button
		select.addActionListener((ActionEvent e) -> {
			try {
				p.selectPerson();// call other class method to select a person from the network
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		});
		// button to display profile of selected person
		JButton display = new JButton("Disply Profile");
		display.setBounds(163, 54, 120, 25);
		display.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MenuPanel.add(display);
		// action listener for display button
		display.addActionListener((ActionEvent e) -> {
			try {
				p.displayProfile();// call other class method to display selected person's profile
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		});
		// button to update profile of a selected person
		JButton update = new JButton("Update Profile");
		update.setBounds(163, 90, 120, 25);
		update.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MenuPanel.add(update);
		// action listener for the update button
		update.addActionListener((ActionEvent e) -> {
			try {
				p.updateProfile();// call other class method to update profile of a person
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		});
		// button to delete a person from network
		JButton delete = new JButton("Delete Profile");
		delete.setBounds(163, 126, 120, 25);
		delete.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MenuPanel.add(delete);
		// action listener for delete button
		delete.addActionListener((ActionEvent e) -> {
			try {
				p.deleteProfile();// call other class method to delete a person from network
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		});
		// button to connect two persons on the network
		JButton connect = new JButton("Connect Two");
		connect.setBounds(293, 54, 120, 25);
		connect.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MenuPanel.add(connect);
		// action listener for connect button
		connect.addActionListener((ActionEvent e) -> {
			try {
				p.connectPersons();// call other class method to connect two persons
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		});
		// button to check if 2 persons are friends together or not
		JButton direct = new JButton("Check Relation");
		direct.setBounds(293, 18, 120, 25);
		direct.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MenuPanel.add(direct);
		// action listener for direct friends button
		direct.addActionListener((ActionEvent e) -> {
			try {
				p.directFriends();// call other class method to check whether 2 people are friends or not
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		});
		// button to find parent's children and children's parent
		JButton findPeople = new JButton("Find People");
		findPeople.setBounds(293, 90, 120, 25);
		findPeople.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MenuPanel.add(findPeople);
		// action listener for find people button
		findPeople.addActionListener((ActionEvent e) -> {
			try {
				p.findPeople();// call other class method to find parent's children and children's parent
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		});
		// button to exit the program

		JButton exit = new JButton("Exit");
		exit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		exit.setBounds(293, 128, 120, 23);
		MenuPanel.add(exit);
		// action listener for exit button
		exit.addActionListener((ActionEvent e) -> {
			// frame = new JFrame("Exit");
			if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "MiniNet",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
				System.exit(0);// terminate application
			}
		});
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(MenuPanel, "name_63341761375916");// add panel to frame

		// button to add a new person to the social network
		JButton createAc = new JButton("Create Account");
		createAc.setBounds(15, 90, 138, 25);
		createAc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		MenuPanel.add(createAc);

		JLabel lblWorldOf = new JLabel("World of ");
		lblWorldOf.setToolTipText("");
		lblWorldOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorldOf.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblWorldOf.setBounds(15, 7, 120, 29);
		MenuPanel.add(lblWorldOf);

		JLabel lblMiniNet = new JLabel("MiniNet");
		lblMiniNet.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiniNet.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblMiniNet.setBounds(35, 39, 83, 14);
		MenuPanel.add(lblMiniNet);

		// BackgroundImage
		JLabel BackgroundImage = new JLabel("");
		BackgroundImage.setBounds(0, 0, 423, 181);
		MenuPanel.add(BackgroundImage);
		BackgroundImage.setIcon(new ImageIcon(GUI.class.getResource("/Resources/image.jpg")));

		final JPanel WelComePanel = new JPanel();
		frame.getContentPane().add(WelComePanel, "name_97294047102665");
		WelComePanel.setLayout(null);
		WelComePanel.setVisible(true);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPanel.setVisible(true);
				WelComePanel.setVisible(false);
			}
		});
		btnStart.setBounds(170, 108, 89, 23);
		WelComePanel.add(btnStart);

		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "MiniNet",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);// terminate application
				}
			}
		});
		button.setBounds(159, 166, 120, 23);
		WelComePanel.add(button);

		JLabel Welcome = new JLabel("Welcome to");
		Welcome.setBounds(10, 30, 403, 17);
		Welcome.setToolTipText("");
		Welcome.setHorizontalAlignment(SwingConstants.CENTER);
		Welcome.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 22));
		WelComePanel.add(Welcome);

		JLabel MiniNet = new JLabel("MiniNet");
		MiniNet.setBounds(10, 62, 403, 17);
		MiniNet.setHorizontalAlignment(SwingConstants.CENTER);
		MiniNet.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		WelComePanel.add(MiniNet);

		JLabel Bckground = new JLabel("");
		Bckground.setIcon(new ImageIcon(GUI.class.getResource("/Resources/image.jpg")));
		Bckground.setBounds(0, 0, 423, 189);
		WelComePanel.add(Bckground);

		// action listener for the add buttons
		createAc.addActionListener((ActionEvent e) -> {
			try {
				p.addNew();// call other class method to add a new person to network
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		});
		frame.setVisible(true);// set frame to visible for user

	}
}
