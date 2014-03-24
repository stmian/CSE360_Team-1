/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package behealthy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Brenden
 */
public class BeHealthy {
    
    static JFrame mainFrame = new JFrame();
    
    
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        createMainFrame();
        
        UserModel userModel = new UserModel("", 0, "", false);
        UserController userController = new UserController(userModel);
        JPanel userPanel = userController.getPanel();
        
        //Create tabbed pane
        JTabbedPane mPane = new JTabbedPane();
	mPane.addTab("Profile", userPanel);
	mainFrame.add(mPane);
    } //main
    
    
    public static void createMainFrame() {
        mainFrame = new JFrame();
        mainFrame.setSize(500, 500);
        mainFrame.setTitle("BeHealthy");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    } //createMainFrame
    
} //BeHealthy