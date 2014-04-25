
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Brenden
 */
public class ActivitiesModel {
    //=================== Private properties/methods ===================//
    private ArrayList<Activity> activities;
    
    //=================== Public properties/methods ====================//
    public ActivitiesModel() {
        activities = new ArrayList<Activity>();

        this.fetchActivities();
    } //__constructor
    
    public ArrayList<Activity> getActivities() {
        return activities;
    } //getActivities
    
    public boolean addActivity(int typeId, String typeName, Date date, double duration) {
        PreparedStatement query = null;
        ResultSet resultSet = null;
        java.sql.Date date_sql = new java.sql.Date(date.getTime());
        
        try {
            query = BeHealthy.conn.prepareStatement("INSERT INTO activities (userId, typeId, date, value) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            query.setInt(1, BeHealthy.getCurrentUserId());
            query.setInt(2, typeId);
            query.setDate(3, date_sql);
            query.setDouble(4, duration);
            
            // Insert into table and returns new ID
            query.executeUpdate();
            resultSet = query.getGeneratedKeys();
            resultSet.next();
            int newId = resultSet.getInt(1);
            
            activities.add(new Activity(
                                        newId,
                                        typeId,
                                        typeName,
                                        date,
                                        duration
                                        )); //activities
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } //try-catch
    } //addActivity
    
    public boolean removeActivity(int id) {
        PreparedStatement query = null;
        ResultSet resultSet = null;
        
        try {
            query = BeHealthy.conn.prepareStatement("DELETE FROM activities WHERE id = ?");
            query.setInt(1, id);
            query.executeUpdate();
            
            // TODO: Remove activity from array list
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } //try-catch
    }
    
    public boolean importActivity(File file) {
        try {
            Scanner scanner = new Scanner(file);
           // ArrayList<String> time = new ArrayList<String>();
            //ArrayList<String> active = new ArrayList<String>();
            int count = 0;
            //int falseCount = 0;
            
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                line = line.trim();
                String[] inputLine = line.split(",");
/*
                if(lineParts[1].equals("-1")){
                	falseCount += 1;
                }
                else {
                	falseCount = 0;
                }
                if(falseCount > 1){
                	time.remove(time.size() - 1);
                	active.remove(active.size() - 1);
                	System.out.println("Inactive");
                	break;
                }
                //time.add(lineParts[0]);
                //active.add(lineParts[1]);
                System.out.println("Time: " + time.get(count) + " Active: " + active.get(count));
                //count = count + 1;
         */
                String type = inputLine[0];
                int inputID = 0;
                int time = Integer.parseInt(inputLine[1]);
                Date inputDate;


                //Check to see what type the activity is
                if(type.equals("Sleep"))
                {
                    inputID = 1;
                }
                if(type.equals("Eating"))
                {
                    inputID = 2;
                }
                if(type.equals("Work"))
                {
                    inputID = 3;
                }
                if(type.equals("Workout"))
                {
                    inputID = 4;
                }


                if(inputLine.length < 3) {

                    Calendar c = Calendar.getInstance();
                    inputDate = c.getTime();
                    //System.out.println(differenceHours);
                    System.out.println(inputDate);
                }
                else {

                    //inputDate = inputLine[2];
                    Calendar d = Calendar.getInstance();
                    inputDate = d.getTime();
                    //System.out.println("ELSE" + inputDate);
                }


                //TODO use strings to add activity to database
               addActivity(inputID, type, inputDate, time);
               //addActivity(BeHealthy.getCurrentUserId(), type, inputDate, time);
            }
            
           // if(time.size() == 0){
           // 	return false;
           // }
            /*
            String startTimeString = time.get(0);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            Date startDate = null;
            try {
                startDate = sdf.parse(startTimeString);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            String stopTimeString = time.get(time.size() - 1);
            Date stopDate = null;
            try {
                stopDate = sdf.parse(stopTimeString);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            if (stopDate.getTime() < startDate.getTime()) {
                Calendar c = Calendar.getInstance();
                c.setTime(stopDate);
                c.add(Calendar.DATE, 1);
                stopDate = c.getTime();
            }
            */
           // long difference = stopDate.getTime() - startDate.getTime();
           // double conversionFactor = 1000 * 60 * 60;
           // double differenceHours = (double) (difference / conversionFactor);
/*
            String type = input[0];
            int time = (int) input[1];

          if(input.length() < 3) {

              Calendar c = Calendar.getInstance();
              Date inputDate = c.getTime();
              //System.out.println(differenceHours);
          }
          else {

              String inputDate = input[2];
          }
             // addActivity(type, time, inputDate);		//TODO Uncomment once addActivity functionality is complete
*/
            return true;

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return false;
        }
    }
    
    public void updateActivity(int id, int typeId, double duration, Date date) {
        // TODO: Add database call
    } //updateActivity

    public void fetchActivities() {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = BeHealthy.conn.createStatement();
            resultSet = statement.executeQuery("SELECT a.*, at.name AS typeName FROM activities a, activity_types at WHERE a.typeId = at.id");

            activities.clear();

            while (resultSet.next()) {
                activities.add(new Activity(
                        resultSet.getInt("id"),
                        resultSet.getInt("typeId"),
                        resultSet.getString("typeName"),
                        resultSet.getDate("date"),
                        resultSet.getDouble("value")
                ));
            } //while
        } catch (Exception e) {
            e.printStackTrace();
        } //try-catch
    } //fetchActivities
}
