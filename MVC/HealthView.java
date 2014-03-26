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

public class HealthView implements ActionListener{

	//Activities variables
	String[] activities = {"Weight", "Blood Pressure", "Blood Sugar", "Heart Rate"};
	String[] units = {"lbs", "", "mg/dL", "bpm"};

	//Components
	JLabel titleL, iconL, enterL, logL, unitsL;
	JComboBox metricCB;
	JTextField valueTF, dateTF;
	JTextArea logTA;
	JButton addB, removeB;
	JScrollPane scroll;
	JPanel healthPanel;
	
	HealthModel model;
	HealthController controller;

	public HealthView(HealthController controller, HealthModel model){
			this.controller = controller;
			this.model = model;
	}
	
	public void createView(){
		healthPanel = new JPanel();
		healthPanel.setLayout(new GridBagLayout());

		//Initialize standard variables
		Font font_title = new Font("Calibri",Font.PLAIN,30);

		//Initialize components
		titleL = new JLabel("Keep track of your health     ");
		titleL.setFont(font_title);
		iconL = new JLabel(new ImageIcon("logo1.png"));
		enterL = new JLabel("Enter new metric");
		logL = new JLabel("Log");
		unitsL = new JLabel(units[0]);
		metricCB = new JComboBox(activities);
		metricCB.setPreferredSize(new Dimension(110, 25));
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
		addB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}			
		});
		
		removeB = new JButton("Remove");
		removeB.setPreferredSize(new Dimension(80, 25));
		removeB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}			
		});

		//Used for padding around components
		int[] inset = {5,5,5,5};
		int[] inset3 = {0,5,28,5}; //Title/icon 

		//Add components
		addItem(healthPanel,iconL,0,0,1,1,inset3,GridBagConstraints.WEST);
		addItem(healthPanel,titleL,1,0,4,1,inset3,GridBagConstraints.WEST);
		addItem(healthPanel,enterL,0,1,4,1,inset,GridBagConstraints.WEST);
		addItem(healthPanel,metricCB,0,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(healthPanel,valueTF,1,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(healthPanel,unitsL,2,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(healthPanel,dateTF,3,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(healthPanel,addB,4,2,1,1,inset,GridBagConstraints.CENTER);
		addItem(healthPanel,logL,0,3,4,1,inset,GridBagConstraints.WEST);
		addItem(healthPanel,scroll,0,4,4,1,inset,GridBagConstraints.WEST);
		addItem(healthPanel,removeB,4,4,1,1,inset,GridBagConstraints.CENTER);
	}

	public JPanel getPanel(){
		return this.healthPanel;		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}