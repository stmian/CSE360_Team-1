
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFileChooser;

import de.wannawork.jcalendar.JCalendarComboBox;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivitiesView implements ActionListener {
    
    //Activities variables
    String[] activities = {"Sleep", "Eating", "Work", "Workout"};
    String[] units = {"hrs", "Cal", "hrs", "hrs"};
    
    
    //Components
    JLabel titleL, iconL, enterL, logL, unitsL;
    JComboBox activityCB;
    JTextField valueTF;
    JTable logTA;
    JButton addB, removeB, importB;
    JScrollPane scroll;
    JPanel activitiesPanel;
    JCalendarComboBox date;
    
    
    ActivitiesController controller;
    
    
    public ActivitiesView(ActivitiesController controller) {
        this.controller = controller;
        
    }
    
    public void createView() {
        activitiesPanel = new JPanel();
        activitiesPanel.setLayout(new GridBagLayout());
        
        //Initialize standard variables
        Font font_title = new Font("Calibri", Font.PLAIN, 30);
        
        //Initialize components
        titleL = new JLabel("Keep track of daily activities");
        titleL.setFont(font_title);
        iconL = new JLabel(new ImageIcon("logo1.png"));
        enterL = new JLabel("Enter new activity");
        logL = new JLabel("Log");
        unitsL = new JLabel(units[0]);
        activityCB = new JComboBox(activities);
        activityCB.setPreferredSize(new Dimension(110, 25));
        activityCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 unitsL.setText(units[activityCB.getSelectedIndex()]);
            } //actionPerformed
        });
        
        valueTF = new JTextField();
        valueTF.setPreferredSize(new Dimension(90, 25));
        date = new JCalendarComboBox();
        date.setDateFormat(new SimpleDateFormat("MM/dd/yyy"));
        
        // TODO: Change this to a table and populate with ArrayList from controller.getActivities
        String[] columnNames = {"Type",
            "Duration",
            "Date"};
        
        final String[][] data= new String[50][3];
        for(int i=0;i<controller.getActivities().size();i++)
        {
        	
            data[i][0]=controller.getActivities().get(i).typeName;
            data[i][1]=String.valueOf(controller.getActivities().get(i).duration);
            data[i][2]=controller.getActivities().get(i).date.toString();
            
        }
        logTA = new JTable(data,columnNames);
        
        scroll = new JScrollPane(logTA);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(300, 230));
        
        addB = new JButton("Add");
        addB.setPreferredSize(new Dimension(80, 25));
        addB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	if(controller.addActivity(
                			 				  activityCB.getSelectedIndex()+1,
                                              activityCB.getSelectedItem().toString(),
                                              new java.sql.Date(date.getDate().getTime()),
                                              Double.parseDouble(valueTF.getText()))
                       ); //addActivity
                    {
                        data[controller.getActivities().size()-1][0]=activityCB.getSelectedItem().toString();
                        data[controller.getActivities().size()-1][1]=valueTF.getText();
                        data[controller.getActivities().size()-1][2]=BeHealthy.dateFormatter.format(date.getDate());
                    }
                    logTA.updateUI();
                    
                    valueTF.setText("");
            		
                } catch (Exception ex) {
                    ex.printStackTrace();
                } //try-catch
            } //actionPerformed
        });
        
        // TODO: Change method calls to point to controller instead of model
        removeB = new JButton("Remove");
        removeB.setPreferredSize(new Dimension(80, 25));
        removeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(logTA.getSelectedRow()<controller.getActivities().size())
        		{
                	
                    controller.removeActivity(controller.getActivities().get(logTA.getSelectedRow()).id);
                    for(int i=logTA.getSelectedRow();i<controller.getActivities().size();i++)
                    {
                        data[i][0]=data[i+1][0];
                        data[i][1]=data[i+1][1];
                        data[i][2]=data[i+1][2];
                    }
                    logTA.updateUI();
                    controller.getActivities().remove(logTA.getSelectedRow());
                }
            }
        });
        
        importB = new JButton("Import");
        importB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = null;
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = fc.getSelectedFile();
                    controller.importActivity(file);
                } else {
                    return;
                }
                
            }
        });
        
        //Used for padding around components
        int[] inset = {5, 5, 5, 5};
        int[] inset2 = {108, 5, 5, 5}; //Remove button
        int[] inset3 = {0, 5, 28, 5}; //Title/icon
        
        //Add components
        addItem(activitiesPanel, iconL, 0, 0, 1, 1, inset3, GridBagConstraints.WEST);
        addItem(activitiesPanel, titleL, 1, 0, 4, 1, inset3, GridBagConstraints.WEST);
        addItem(activitiesPanel, enterL, 0, 1, 4, 1, inset, GridBagConstraints.WEST);
        addItem(activitiesPanel, activityCB, 0, 2, 1, 1, inset, GridBagConstraints.CENTER);
        addItem(activitiesPanel, valueTF, 1, 2, 1, 1, inset, GridBagConstraints.CENTER);
        addItem(activitiesPanel, unitsL, 2, 2, 1, 1, inset, GridBagConstraints.CENTER);
        addItem(activitiesPanel, date, 3, 2, 1, 1, inset, GridBagConstraints.CENTER);
        addItem(activitiesPanel, addB, 4, 2, 1, 1, inset, GridBagConstraints.CENTER);
        addItem(activitiesPanel, logL, 0, 3, 4, 1, inset, GridBagConstraints.WEST);
        addItem(activitiesPanel, scroll, 0, 4, 4, 2, inset, GridBagConstraints.WEST);
        addItem(activitiesPanel, removeB, 4, 4, 1, 1, inset2, GridBagConstraints.CENTER);
        addItem(activitiesPanel, importB, 4, 5, 1, 1, inset, GridBagConstraints.CENTER);
    }
    
    public JPanel getPanel() {
        return this.activitiesPanel;
    }
    
    public void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int[] inset, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.insets = new Insets(inset[0], inset[1], inset[2], inset[3]);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
