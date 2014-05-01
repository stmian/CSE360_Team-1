import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;



public class HealthController {

    //=================== Private properties/methods ===================//
    private HealthModel model;
    private HealthView view;

    //=================== Public properties/methods ====================//
    public HealthController(HealthModel model) {
        this.model = model;
        this.view = new HealthView(this);
        this.view.createView();
    }

    public ArrayList<HealthMetric> gethealthMetrics()
    {
        return model.gethealthMetrics();
    }


    public boolean addHealthMetric(int typeId,double metric, Date date)
    {
        boolean success=model.addHealthMetric(typeId, metric, date);
        return success;
    }
    public boolean removeHealthMetric(int id)
    {
        boolean success=model.removeHealthMetric(id);
        return success;
    }

    public JPanel getPanel() {
        return view.getPanel();
    } //getPanel

    public void fetchHealthMetrics() {
        model.fetchHealthMetrics();
    } //fetchHealthMetrics
}
