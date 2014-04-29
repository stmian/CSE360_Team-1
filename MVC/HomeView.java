
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class HomeView implements ActionListener {

    //For Box see JFD p622
    //Components
    private JLabel titleL, iconL;
    private JButton printB, graph1B, graph2B, graph3B;
    private JTable statsT;
    private Object[][] statsObj;
    private JPanel homePanel;
    private JPanel graphP;

    //MVCs
    HomeModel model;
    HomeController controller;
    PieChartView pieChart;
    LineChartWeightView lineWeight;
    LineChartCaloriesView lineCalories;

    //Constants
    final public static int[] CHART_DIMS = {178, 178};

    //Stats variables
    String height, weight, heartrate, bloodsugar, sleep, workout,
            work, calories, totalCalories, totalSleep, totalWorkout, totalWork, BMI, bloodpressure;

    public HomeView(HomeController controller, HomeModel model) {
        this.controller = controller;
        this.model = model;
    }

    public void createView() {
        homePanel = new JPanel();
        homePanel.setLayout(new GridBagLayout());
        lineWeight = new LineChartWeightView();
        lineCalories = new LineChartCaloriesView();
        pieChart = new PieChartView();

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
        graphP = new JPanel();
        graphP.add(lineWeight);
        statsT = new JTable(statsObj, headers);
        statsT.setBackground(null);
        statsT.setShowGrid(false);
        mPanel = new JPanel(new BorderLayout());
        mPanel.add(statsT);
        mPanel.setBorder(border);

        printB = new JButton("Print");
        printB.setPreferredSize(new Dimension(65, 25));
        printB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.chartsToImages(pieChart.getChart(), lineWeight.getChart(), lineCalories.getChart());
                controller.printData();
            }
        });

        graph1B = new JButton("Weight");
        graph1B.setPreferredSize(new Dimension(90, 25));
        graph1B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineWeight = new LineChartWeightView();
                graphP.removeAll();
                graphP.add(lineWeight);
                graphP.repaint();
                graphP.revalidate(); //Do not remove this line. Comment it out if necessary. Will not work on Windows w/o
            }
        });

        graph2B = new JButton("Calories");
        graph2B.setPreferredSize(new Dimension(90, 25));
        graph2B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineCalories = new LineChartCaloriesView();
                graphP.removeAll();
                graphP.add(lineCalories);
                graphP.repaint();
                graphP.revalidate(); //Do not remove this line. Comment it out if necessary. Will not work on Windows w/o
            }
        });

        graph3B = new JButton("Daily");
        graph3B.setPreferredSize(new Dimension(90, 25));
        graph3B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pieChart = new PieChartView();
                graphP.removeAll();
                graphP.add(pieChart);
                graphP.repaint();
                graphP.revalidate(); //Do not remove this line. Comment it out if necessary. Will not work on Windows w/o
            }
        });

        //Used for padding around components
        int[] inset = {0, 5, 5, 5};
        int[] inset2 = {0, 110, 5, 5}; //print button
        int[] inset3 = {0, 5, 28, 0}; //Title/icon
        int[] inset4 = {0, 5, 5, 5}; //graph buttons

        //Add components
        addItem(homePanel, iconL, 0, 0, 1, 1, inset3, GridBagConstraints.WEST);
        addItem(homePanel, titleL, 1, 0, 3, 1, inset3, GridBagConstraints.WEST);
        addItem(homePanel, mPanel, 0, 1, 4, 1, inset, GridBagConstraints.CENTER);
        addItem(homePanel, graphP, 0, 2, 4, 1, inset, GridBagConstraints.CENTER);
        addItem(homePanel, graph1B, 0, 3, 1, 1, inset4, GridBagConstraints.SOUTH);
        addItem(homePanel, graph2B, 1, 3, 1, 1, inset4, GridBagConstraints.SOUTH);
        addItem(homePanel, graph3B, 2, 3, 1, 1, inset4, GridBagConstraints.SOUTH);
        addItem(homePanel, printB, 3, 3, 1, 1, inset2, GridBagConstraints.SOUTH);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        }
    }

    public void initializeStats() {
        System.out.println("Initializing Stats");
        double[] activityData = controller.getActivityData();
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormat df2 = new DecimalFormat("#");

        height = df2.format(controller.getHeight()*.393701);
        weight = Double.toString(controller.getWeight());
        bloodpressure = controller.getBP();
        bloodpressure = "120/80";
        heartrate = Double.toString(controller.getHR());
        sleep = df.format(activityData[0]/activityData[1]);
        workout = df.format(activityData[4]/activityData[5]);
        work = df.format(activityData[2]/activityData[3]);
        calories = df.format(activityData[6]/activityData[7]);
        totalCalories = Double.toString(activityData[6]);
        totalSleep = Double.toString(activityData[0]);
        totalWorkout = Double.toString(activityData[4]);
        totalWork = Double.toString(activityData[2]);
        BMI = df.format(controller.getWeight()/(controller.getHeight() * controller.getHeight())*703);

        Object[][] temp = {
                {"Height: ", height + " in", "Avg Cal: ", calories + " Cal", "Total Work:", totalWork + " hrs"},
                {"Weight: ", weight + " lbs", "Avg Sleep: ", sleep + " hrs", "Total Sleep: ", totalSleep + " hrs"},
                {"BMI: ", BMI, "Avg WO: ", workout + " hrs", "Total WO: ", totalWorkout + " hrs"},
                {"BP: ", bloodpressure, "Avg Work: ", work + " hrs", "Total Cal: ", totalCalories + " Cal"},
                {"HR: ", heartrate + " bpm", "","", "", ""}
        };

        statsObj = temp;
    }

    public JPanel getPanel() {
        return this.homePanel;
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
