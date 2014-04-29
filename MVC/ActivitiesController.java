
import javax.swing.JPanel;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

public class ActivitiesController {

    //=================== Private properties/methods ===================//
    private ActivitiesModel model;
    private ActivitiesView view;

    //=================== Public properties/methods ====================//
    public ActivitiesController(ActivitiesModel model) {
        this.model = model;
        this.view = new ActivitiesView(this);
        this.view.createView();
    }

    public ArrayList<Activity> getActivities() {
        return model.getActivities();
    } //getActivities

    public boolean addActivity(int typeId, String typeName, Date date, double duration) {
        // TODO: Add success checking if necessary; else remove boolean return on Model
        boolean success = model.addActivity(typeId, typeName, date, duration);
        return success;
    } //addActivity

    public boolean removeActivity(int id)
    {
        boolean success= model.removeActivity(id);
        return success;
    }

    public boolean importActivity(File file)
    {
        boolean success= model.importActivity(file);
        return success;
    }
    public JPanel getPanel() {
        return view.getPanel();
    } //getPanel

    public void fetchActivities() {
        model.fetchActivities();
    } //fetchActivities
}
