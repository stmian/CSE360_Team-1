
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HealthModel {

    //=================== Private properties/methods ===================//


    private ArrayList<HealthMetric> healthMetrics;

    //=================== Public properties/methods ====================//
    public HealthModel() {
        healthMetrics = new ArrayList<HealthMetric>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        this.fetchHealthMetrics();
    } //__constructor

    public ArrayList<HealthMetric> gethealthMetrics()
    {
        return healthMetrics;
    }

    public boolean addHealthMetric(int typeId,double metric, Date date) {
        String name="";
        if(typeId==1)
        {
            name="Weight";
        }
        if(typeId==2)
        {
            name="Blood Pressure";
        }
        if(typeId==3)
        {
            name="Blood Sugar";
        }
        if(typeId==4)
        {
            name="Heart Rate";
        }

        PreparedStatement query = null;
        ResultSet resultSet = null;
        java.sql.Date date_sql = new java.sql.Date(date.getTime());

        try {
            query = BeHealthy.conn.prepareStatement("INSERT INTO health_metrics (userId, typeId, date, value) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            query.setInt(1, BeHealthy.getCurrentUserId());
            query.setInt(2, typeId);
            query.setDate(3, date_sql);
            query.setDouble(4, metric);

            // Insert into table and returns new ID
            query.executeUpdate();
            resultSet = query.getGeneratedKeys();
            resultSet.next();
            int newId = resultSet.getInt(1);

            healthMetrics.add(new HealthMetric(newId,typeId,name,metric, date));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } //try-catch
    } //addHealthMetric

    public boolean updateHealthMetric(int id, int typeId, double metric, Date date) {
        // TODO: add database call
        return false;
    } //updateHealthMetric

    public boolean removeHealthMetric(int id) {
        PreparedStatement query = null;
        ResultSet resultSet = null;

        try {
            query = BeHealthy.conn.prepareStatement("DELETE FROM health_metrics WHERE id = ?");
            query.setInt(1, id);
            query.executeUpdate();

            // TODO: Remove activity from array list
            System.out.println("it deleted!");
            return true;
        } catch (SQLException e) {
            System.out.println("no it didnt");
            e.printStackTrace();
            return false;
        } //try-catch
    }//removeHealthMetric

    public void fetchHealthMetrics() {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = BeHealthy.conn.createStatement();
            resultSet = statement.executeQuery("SELECT hm.*, hmt.name AS typeName FROM health_metrics hm, health_metric_types hmt WHERE hm.typeId = hmt.id");

            healthMetrics.clear();

            while (resultSet.next()) {
                healthMetrics.add(new HealthMetric(
                        resultSet.getInt("id"),
                        resultSet.getInt("typeId"),
                        resultSet.getString("typeName"),
                        resultSet.getDouble("value"),
                        resultSet.getDate("date")
                ));
            } //while
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //try-catch
    } //fetchHealthMetrics
} //HealthModel