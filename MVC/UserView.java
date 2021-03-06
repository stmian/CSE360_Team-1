
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author Brenden
 */
public class UserView implements ActionListener {

    //=================== Private properties/methods ===================//
    String[] averages = {"Blood Pressure", "Heart Rate", "Blood Sugar", "Sleep", "Workout", "Work", "Calories"};

    //Components
    JLabel titleL, iconL, nameL, birthdateL, heightL;
    JTextField nameTF, birthdateTF, heightTF;
    JTextArea messageTA;
    JButton resetActB, saveB;
    JPanel userPanel;

    UserModel model;
    UserController controller;

    /**
     * Transforms centimeters to English standard format
     *
     * @return String formatted with feet"inches'
     */
    private String displayHeight() {
        double height_cm = controller.getHeight();
        int feet = (int) (height_cm * 0.0328084f);
        int inches = (int) (height_cm * 0.393701f - (feet * 12));

        return String.format("%d\"%d\'", feet, inches);
    } //displayHeight


    //=================== Public properties/methods ====================//
    public UserView(UserController controller, UserModel model) {
        this.controller = controller;
        this.model = model;
    } //__constructor

    public void createView() {
        userPanel = new JPanel();
        userPanel.setLayout(new GridBagLayout());

        //Initialize standard variables
        Font font_title = new Font("Calibri", Font.PLAIN, 30);

        //Initialize components
        titleL = new JLabel("Manage your profile");
        titleL.setFont(font_title);
        iconL = new JLabel(new ImageIcon("logo1.png"));

        nameL = new JLabel("Name:");
        nameTF = new JTextField(controller.getName());
        nameTF.setPreferredSize(new Dimension(130, 25));

        birthdateL = new JLabel("Birthdate:");
        birthdateTF = new JTextField(BeHealthy.dateFormatter.format(controller.getBirthdate()));
        birthdateTF.setPreferredSize(new Dimension(130, 25));

        heightL = new JLabel("Height:");
        heightTF = new JFormattedTextField(displayHeight());
        heightTF.setPreferredSize(new Dimension(130, 25));

        messageTA = new JTextArea("                 Start by entering a little bit of information about yourself."
                + "\n    Track your daily activities in the Activities tab. Track you health day "
                + "\nto day in the Health tab.  View and print a summary in the Home tab.");
        messageTA.setBackground(null);
        messageTA.setEditable(false);

        resetActB = new JButton("Reset Account");
        resetActB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.resetUserProfile();
            } //actionPerformed
        });

        saveB = new JButton("Save");
        saveB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // TODO: Add action code
            } //actionPerformed
        });

        //Used for padding around components
        int[] inset = {5, 5, 5, 5};
        int[] inset2 = {30, 5, 5, 5}; //Import button
        int[] inset3 = {0, 5, 20, 40}; //icon
        int[] inset4 = {5, 5, 30, 5}; //Height
        int[] inset5 = {5, 5, 20, 5}; //message TA
        int[] inset6 = {0, 0, 20, 100}; //Title

        //Add components
        addItem(userPanel, iconL, 0, 0, 1, 1, inset3, GridBagConstraints.EAST);
        addItem(userPanel, titleL, 1, 0, 3, 1, inset6, GridBagConstraints.WEST);
        addItem(userPanel, messageTA, 0, 1, 4, 1, inset5, GridBagConstraints.CENTER);
        addItem(userPanel, nameL, 0, 2, 2, 1, inset, GridBagConstraints.CENTER);
        addItem(userPanel, nameTF, 2, 2, 2, 1, inset, GridBagConstraints.WEST);
        addItem(userPanel, birthdateL, 0, 3, 2, 1, inset, GridBagConstraints.CENTER);
        addItem(userPanel, birthdateTF, 2, 3, 2, 1, inset, GridBagConstraints.WEST);
        addItem(userPanel, heightL, 0, 4, 2, 1, inset4, GridBagConstraints.CENTER);
        addItem(userPanel, heightTF, 2, 4, 2, 1, inset4, GridBagConstraints.WEST);
        addItem(userPanel, resetActB, 0, 6, 4, 1, inset, GridBagConstraints.CENTER);
        addItem(userPanel, saveB, 0, 7, 4, 1, inset2, GridBagConstraints.SOUTH);
    } //createView

    public JPanel getPanel() {
        return this.userPanel;
    } //getPanel

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
    } //addItem

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} //UserView
