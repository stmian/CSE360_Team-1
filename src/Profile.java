import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Profile extends JPanel{
	
	//Averages
	String[] averages = {"Blood Pressure", "Heart Rate", "Blood Sugar", "Sleep", "Workout", "Work", "Calories"};
	
	//Components
	JLabel titleL, iconL, nameL, birthdateL, heightL, resetL;
	JTextField nameTF, birthdateTF, heightTF;
	JTextArea messageTA;
	JComboBox averagesCB;
	JButton resetB, resetActB, importB;
	
	public Profile(){
		this.setLayout(new GridBagLayout());

		//Initialize standard variables
		ButtonListener bl = new ButtonListener();
		Font font_title = new Font("Calibri",Font.PLAIN,30);
		
		//Initialize components
		titleL = new JLabel("Manage your profile");
		titleL.setFont(font_title);
		iconL = new JLabel(new ImageIcon("logo1.png"));
		nameL = new JLabel("Name:");
		birthdateL = new JLabel("Birthdate:");
		heightL = new JLabel("Height:");
		resetL = new JLabel("Reset Averages");
		nameTF = new JTextField();
		nameTF.setPreferredSize(new Dimension(90,25));
		birthdateTF = new JTextField();
		birthdateTF.setPreferredSize(new Dimension(90,25));
		heightTF = new JTextField();
		heightTF.setPreferredSize(new Dimension(90,25));
		messageTA = new JTextArea("                 Start by entering a little bit of information about yourself." +
				"\n    Track your daily activities in the “Activities” tab. Track you health day " +
				"\nto day in the “Health” tab.  View and print a summary in the “Home” tab.");
		messageTA.setBackground(null);
		messageTA.setEditable(false);
		averagesCB = new JComboBox(averages);
		resetB = new JButton("Reset Avg");
		resetB.addActionListener(bl);
		resetActB = new JButton("Reset Account");
		resetActB.addActionListener(bl);
		importB = new JButton("Import");
		importB.addActionListener(bl);
		

		//Used for padding around components
		int[] inset = {5,5,5,5};
		int[] inset2 = {30,5,5,5}; //Import button
		int[] inset3 = {0,5,20,40}; //icon 
		int[] inset4 = {5,5,30,5}; //Height
		int[] inset5 = {5,5,20,5}; //message TA
		int[] inset6 = {0,0,20,100}; //Title

		//Add components
		addItem(this,iconL,0,0,1,1,inset3,GridBagConstraints.EAST);
		addItem(this,titleL,1,0,3,1,inset6,GridBagConstraints.WEST);
		addItem(this,messageTA,0,1,4,1,inset5,GridBagConstraints.CENTER);
		addItem(this,nameL,0,2,2,1,inset,GridBagConstraints.CENTER);
		addItem(this,nameTF,2,2,2,1,inset,GridBagConstraints.WEST);
		addItem(this,birthdateL,0,3,2,1,inset,GridBagConstraints.CENTER);
		addItem(this,birthdateTF,2,3,2,1,inset,GridBagConstraints.WEST);
		addItem(this,heightL,0,4,2,1,inset4,GridBagConstraints.CENTER);
		addItem(this,heightTF,2,4,2,1,inset4,GridBagConstraints.WEST);
		addItem(this,resetL,0,5,4,1,inset,GridBagConstraints.WEST);
		addItem(this,averagesCB,0,6,1,1,inset,GridBagConstraints.CENTER);
		addItem(this,resetB,1,6,1,1,inset,GridBagConstraints.CENTER);
		addItem(this,resetActB,3,6,1,1,inset,GridBagConstraints.CENTER);
		addItem(this,importB,0,7,4,1,inset2,GridBagConstraints.SOUTH);	
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}

	public void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int[] inset, int align)
	{
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.insets = new Insets(inset[0],inset[1],inset[2],inset[3]);
		gc.anchor = align;
		gc.fill = GridBagConstraints.NONE;
		p.add(c, gc);
	}
}
