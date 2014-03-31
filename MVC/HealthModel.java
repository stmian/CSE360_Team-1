
import java.util.ArrayList;
import java.util.Date;

public class HealthModel {

    //=================== Private properties/methods ===================//
    private class HealthMetric {

        int id;
        int typeId;
        String typeName;
        double metric;
        Date date;

        public HealthMetric(int id, int typeId, String typeName, double metric, Date date) {
            this.id = id;
            this.typeId = typeId;
            this.typeName = typeName;
            this.metric = metric;
            this.date = date;
        } //__constructor
    } //HealthMetric

    private ArrayList<HealthMetric> healthMetrics;

    //=================== Public properties/methods ====================//
    public HealthModel() {
        // TODO: add database call to read all health metric records
    } //__constructor

    public void addHealthMetric(int typeId, double metric, Date date) {
        // TODO: add database call
    } //addHealthMetric

    public void updateHealthMetric(int id, int typeId, double metric, Date date) {
        // TODO: add database call
    } //updateHealthMetric

    public void removeHealthMetric(int id) {
        // TODO: add database call
    } //updateHealthMetric
} //HealthModel
