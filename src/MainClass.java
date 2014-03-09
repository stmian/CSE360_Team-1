import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class MainClass extends JFrame{

	//Constants
	private int WIDTH = 500, HEIGHT = 500;

	//Components
	private JTabbedPane mPane;

	//Set up classes
	private Home mHome;
	private Activities mActivities;
	private Health mHealth;
	private Profile mProfile;
	
	public static void main(String[] args) {
		new MainClass();
	}

	public MainClass(){
		
		//Initialize Classes
		mHome = new Home();
		mActivities = new Activities();
		mHealth = new Health();
		mProfile = new Profile();

		//Create tabbed pane
		mPane = new JTabbedPane();
		mPane.addTab("Home", mHome);
		mPane.addTab("Activities", mActivities);
		mPane.addTab("Health", mHealth);
		mPane.addTab("Profile", mProfile);

		//Set up frame
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("BeHealthy");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(mPane);
		this.setVisible(true);
	}
}
