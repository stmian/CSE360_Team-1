
import javax.swing.JPanel;
import java.sql.Date;
import java.util.ArrayList;

public class ActivitiesController {

    //=================== Private properties/methods ===================//
    private ActivitiesModel model;
    private ActivitiesView view;

    //=================== Public properties/methods ====================//
    public ActivitiesController(ActivitiesModel model) {
        this.model = model;
        this.view = new ActivitiesView(this, this.model);
        this.view.createView();
    }

    public ArrayList<Activity> getActivities() {
        return model.getActivities();
    } //getActivities

    public void addActivity(int typeId, String typeName, Date date, double duration) {
        // TODO: Add success checking if necessary; else remove boolean return on Model
        boolean success = model.addActivity(typeId, typeName, date, duration);
    } //addActivity

    public JPanel getPanel() {
        return view.getPanel();
    } //getPanel
}
