import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//test comment. woooaaaaahhhhhh
public class Activities extends JPanel{

	//Activities variables
	String[] activities = {"Sleep", "Eating", "Work", "Workout"};
	String[] units = {"hrs", "Cal", "hrs", "hrs"};

	//Components
	JLabel titleL, iconL, enterL, logL, unitsL;
	JComboBox activityCB;
	JTextField valueTF, dateTF;
	JTextArea logTA;
	JButton addB, removeB, importB;
	JScrollPane scroll;

	public Activities(){
		this.setLayout(new GridBagLayout());

		//Initialize standard variables
		ButtonListener bl = new ButtonListener();
		Font font_title = new Font("Calibri",Font.PLAIN,30);

		//Initialize components
		titleL = new JLabel("Keep track of daily activities");
		titleL.setFont(font_title);
		iconL = new JLabel(new ImageIcon("logo1.png"));
		enterL = new JLabel("Enter new activity");
		logL = new JLabel("Log");
		unitsL = new JLabel(units[0]);
		activityCB = new JComboBox(activities);
		activityCB.setPreferredSize(new Dimension(110, 25));
		valueTF = new JTextField();
		valueTF.setPreferredSize(new Dimension(90,25));
		dateTF = new JTextField("2/8/14");
		dateTF.setPreferredSize(new Dimension(80,25));
		logTA = new JTextArea();
		scroll = new JScrollPane(logTA);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(300,230));
		addB = new JButton("Add");
		addB.setPreferredSize(new Dimension(80, 25));
		addB.addActionListener(bl);
		removeB = new JButton("Remove");
		removeB.setPreferredSize(new Dimension(80, 25));
		removeB.addActionListener(bl);
		importB = new JButton("Import");
		importB.addActionListener(bl);

		//Used for padding around components
		int[] inset = {5,5,5,5};
		int[] inset2 = {108,5,5,5}; //Remove button
		int[] inset3 = {0,5,28,5}; //Title/icon 
		

		//Add components
		addItem(this,iconL,0,0,1,1,inset3,GridBagConstraints.WEST);
		addItem(this,titleL,1,0,4,1,inset3,GridBagConstraints.WEST);
		addItem(this,enterL,0,1,4,1,inset,GridBagConstraints.WEST);
		addItem(this,activityCB,0,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(this,valueTF,1,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(this,unitsL,2,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(this,dateTF,3,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(this,addB,4,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(this,logL,0,3,4,1,inset,GridBagConstraints.WEST);
		addItem(this,scroll,0,4,4,2,inset,GridBagConstraints.WEST);
		addItem(this,removeB,4,4,1,1,inset2,GridBagConstraints.CENTER);
		addItem(this,importB,4,5,1,1,inset,GridBagConstraints.CENTER);
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
