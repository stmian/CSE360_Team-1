import javax.swing.JPanel;

public class ActivitiesController {
	//=================== Private properties/methods ===================//
	private ActivitiesModel model;
	private ActivitiesView view;
	
    //=================== Public properties/methods ====================//
	public ActivitiesController(ActivitiesModel model){
		this.model = model;
		this.view = new ActivitiesView(this, this.model);
		this.view.createView();
	}
	
	public JPanel getPanel() {
        return view.getPanel();
    } //getPanel
}