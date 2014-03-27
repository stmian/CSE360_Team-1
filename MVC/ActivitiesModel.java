import java.util.*;
import java.sql.*;


public class ActivitiesModel{
	//=================== Private properties/methods ===================//
	private class Activity {
		String name;
		double duration;
		int date;
        
		public Activity(String name, double duration, int date){
			this.name = name;
			this.duration = duration;
			this.date = date;
		}
	}
	
	ArrayList<Activity> activities;
	
	
	
    //=================== Public properties/methods ====================//
	public ActivitiesModel(){
		activities = new ArrayList();
		
		
		String[] temp;
		try {
			Statement st = (BeHealthy.conn).createStatement();
			
            ResultSet res = st.executeQuery("SELECT * FROM  event");
            temp=res.toString().split(" ");
            while (res.next()) {
                addActivity(temp[0],Double.parseDouble(temp[1]),Integer.parseInt(temp[2]));
                
            }
            
            
            
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/*TODO: Enter public getters/setters here e.g.:
	 
     public String getName() {
     return name;
     }
     */
    
	public boolean addActivity(String name, double duration, int date){
		Activity d= new Activity(name,duration,date);
		int i=-1;
		
        try {
			Statement st = (BeHealthy.conn).createStatement();
			
			i=st.executeUpdate("");//ADD APPROPRIATE SQL QUERY TO ADD ACTIVITY
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
		if(i==1)
		{
			activities.add(d);
			return true;
		}
		else
            return false;
		
	}
	public boolean removeActivity(int act){
		
		
		
        
		int i=-1;
		
        try {
			Statement st = (BeHealthy.conn).createStatement();
			
			i=st.executeUpdate("");//ADD APPROPRIATE SQL QUERY TO REMOVE ACTIVITY
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
		if(i==1)
		{
			activities.remove(act);
			return true;
		}
		else
            return false;
		
	}
	
}

