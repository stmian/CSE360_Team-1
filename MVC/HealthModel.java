
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
    	healthMetrics = new ArrayList();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String[] temp;
        Statement st;
        try {
            st = (BeHealthy.conn).createStatement();
            ResultSet res = st.executeQuery("SELECT hm.*, hmt.id, hmt.name AS type_id FROM health_metrics hm, health_metric_types hmt WHERE hm.typeId = hmt.id");
            temp = res.toString().split(" ");
            while (res.next()) {
                // TODO: Change this to add Activities to the collection without the add method. This is only used to add new records.
                int typeId=res.getInt("typeid");
                String name="";
                if(typeId==0)
                {
                    name="Weight";
                }
                if(typeId==1)
                {
                    name="Blood Pressure";
                }
                if(typeId==2)
                {
                    name="Blood Sugar";
                }
                if(typeId==3)
                {
                    name="Heart Rate";
                }
                
                healthMetrics.add(new HealthMetric(res.getInt("id"), typeId, name, res.getDouble("value"), res.getDate("date")));
                
                //addHealthMetric(Integer.parseInt(temp[0]), Double.parseDouble(temp[1]), dateFormatter.parse(temp[2]));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        
        
    } //__constructor
    public ArrayList<HealthMetric> gethealthMetrics()
    {
    	return healthMetrics;
    }
    public boolean addHealthMetric(int typeId,double metric, Date date) {
    	String name="";
    	if(typeId==0)
    	{
    		name="Weight";
    	}
    	if(typeId==1)
    	{
    		name="Blood Pressure";
    	}
    	if(typeId==2)
    	{
    		name="Blood Sugar";
    	}
    	if(typeId==3)
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
    }//HealthModel
}