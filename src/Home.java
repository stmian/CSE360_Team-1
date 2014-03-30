
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
//import com.sun.org.glassfish.external.statistics.Stats;

public class Home extends JPanel {

    //For Box see JFD p622
    //Components
    private JLabel titleL, iconL, graphL;
    private JButton printB, graph1B, graph2B, graph3B;
    private JTable statsT;
    private Object[][] statsObj;

    //Stats variables
    double height, weight, heartrate, bloodsugar, sleep, workout,
            work, calories, totalCalories, totalSleep, totalWorkout, totalWork;
    String bloodpressure;

    public Home() {
        this.setLayout(new GridBagLayout());

        //Initialize standard variables
        ButtonListener bl = new ButtonListener();
        Font font_title = new Font("Comic Sans MS", Font.PLAIN, 42);
        Border border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Summary");
        JPanel mPanel; //Used for the border around the table

        //Headers for table
        String[] headers = {"", "", "", "", "", ""};

        //Initialize components
        initializeStats();
        titleL = new JLabel("BeHealthy");
        titleL.setFont(font_title);
        iconL = new JLabel(new ImageIcon("logo1.png"));
        graphL = new JLabel(new ImageIcon("ExGraph1.png"));
        statsT = new JTable(statsObj, headers);
        statsT.setBackground(null);
        statsT.setShowGrid(false);
        mPanel = new JPanel(new BorderLayout());
        mPanel.add(statsT);
        mPanel.setBorder(border);
        printB = new JButton("Print");
        printB.setPreferredSize(new Dimension(65, 25));
        graph1B = new JButton("Weight");
        graph1B.setPreferredSize(new Dimension(90, 25));
        graph2B = new JButton("Calories");
        graph2B.setPreferredSize(new Dimension(90, 25));
        graph3B = new JButton("Daily");
        graph3B.setPreferredSize(new Dimension(90, 25));

        //Used for padding around components
        int[] inset = {0, 5, 5, 5};
        int[] inset2 = {0, 110, 5, 5}; //print button
        int[] inset3 = {0, 5, 28, 0}; //Title/icon
        int[] inset4 = {0, 5, 5, 5}; //graph buttons

        //Add components
        addItem(this, iconL, 0, 0, 1, 1, inset3, GridBagConstraints.WEST);
        addItem(this, titleL, 1, 0, 3, 1, inset3, GridBagConstraints.WEST);
        addItem(this, mPanel, 0, 1, 4, 1, inset, GridBagConstraints.CENTER);
        addItem(this, graphL, 0, 2, 4, 1, inset, GridBagConstraints.CENTER);
        addItem(this, graph1B, 0, 3, 1, 1, inset4, GridBagConstraints.SOUTH);
        addItem(this, graph2B, 1, 3, 1, 1, inset4, GridBagConstraints.SOUTH);
        addItem(this, graph3B, 2, 3, 1, 1, inset4, GridBagConstraints.SOUTH);
        addItem(this, printB, 3, 3, 1, 1, inset2, GridBagConstraints.SOUTH);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        }
    }

    public void initializeStats() {
        height = 72;
        weight = 170;
        bloodpressure = "120/80";
        heartrate = 85;
        bloodsugar = 90;
        sleep = 8.2;
        workout = .75;
        work = 8.1;
        calories = 2100;
        totalCalories = 56000;
        totalSleep = 196;
        totalWorkout = 72;
        totalWork = 190;

        Object[][] temp = {
            {"Height: ", Double.toString(height) + " in", "Avg BS: ", Double.toString(bloodsugar) + " mg/dL", "Total Cal: ", Double.toString(totalCalories) + " Cal"},
            {"Weight: ", Double.toString(weight) + " lbs", "Avg Sleep: ", Double.toString(sleep) + " hrs", "Total Sleep: ", Double.toString(totalSleep) + " hrs"},
            {"BMI: ", Double.toString(weight / (height * height) * 703), "Avg WO: ", Double.toString(workout) + " hrs", "Total WO: ", Double.toString(totalWorkout) + " hrs"},
            {"Avg BP: ", bloodpressure, "Avg Work: ", Double.toString(work) + " hrs", "Total Work:" + " hrs", Double.toString(totalWork) + " hrs"},
            {"Avg HR: ", Double.toString(heartrate) + " bpm", "Avg Cal: ", Double.toString(calories) + " Cal", "", ""}
        };

        statsObj = temp;
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

}
