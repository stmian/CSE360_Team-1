/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package behealthy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Brenden
 */
public class BeHealthy {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		JFrame mainFrame = new JFrame();

		UserModel userModel = new UserModel("", 0, "", false);
		UserController userController = new UserController(userModel);
		JPanel userPanel = userController.getPanel();

		HealthModel healthModel = new HealthModel();
		HealthController healthController = new HealthController(healthModel);
		JPanel healthPanel = healthController.getPanel();

		ActivitiesModel activitiesModel = new ActivitiesModel();
		ActivitiesController activitiesController = new ActivitiesController(activitiesModel);
		JPanel activitiesPanel = activitiesController.getPanel();

		HomeModel homeModel = new HomeModel();
		HomeController homeController = new HomeController(homeModel);
		JPanel homePanel = homeController.getPanel();

		//Create tabbed pane
		JTabbedPane mPane = new JTabbedPane();
		mPane.addTab("Home", homePanel);
		mPane.addTab("Activities", activitiesPanel);
		mPane.addTab("Health", healthPanel);
		mPane.addTab("Profile", userPanel);
		
		mainFrame = new JFrame();
		mainFrame.setSize(500, 500);
		mainFrame.setTitle("BeHealthy");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.add(mPane);
	} //main

} //BeHealthy