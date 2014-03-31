
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ActivitiesModel.Activity;

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
    	healthMetrics = new ArrayList();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String[] temp;
        Statement st;
			try {
				st = (BeHealthy.conn).createStatement();
				ResultSet res = st.executeQuery("SELECT * FROM  activities");
				temp = res.toString().split(" ");
	            while (res.next()) {
	                // TODO: Change this to add Activities to the collection without the add method. This is only used to add new records.
	                addHealthMetric(Integer.parseInt(temp[0]), Double.parseDouble(temp[1]), dateFormatter.parse(temp[2]));
	            	}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            
            
            
   
    } //__constructor

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
        HealthMetric d = new HealthMetric(1,typeId,name,metric, date);
        int i = -1;

        try {
            Statement st = (BeHealthy.conn).createStatement();

            i = st.executeUpdate("");//TODO ADD APPROPRIATE SQL QUERY TO ADD ACTIVITY

        } catch (SQLException e) {

            e.printStackTrace();
        }

        if (i == 1) {
            healthMetrics.add(d);
            return true;
        } else {
            return false;
        }

    } //addHealthMetric

    public boolean updateHealthMetric(int id, int typeId, double metric, Date date) {
        // TODO: add database call
    } //updateHealthMetric

    public boolean removeHealthMetric(int id) {
    	int i = -1;

        try {
            Statement st = (BeHealthy.conn).createStatement();

            i = st.executeUpdate("");//ADD APPROPRIATE SQL QUERY TO REMOVE ACTIVITY
        } catch (SQLException e) {

            e.printStackTrace();
        }

        if (i == 1) {
            healthMetrics.remove(i);
            return true;
        } else {
            return false;
        }

    } //updateHealthMetric
} //HealthModel
