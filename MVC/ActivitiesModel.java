
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ActivitiesModel {

    //=================== Private properties/methods ===================//
    private class Activity {

        String name;
        double duration;
        int date;

        public Activity(String name, double duration, int date) {
            this.name = name;
            this.duration = duration;
            this.date = date;
        }
    }

    ArrayList<Activity> activities;

    //=================== Public properties/methods ====================//
    public ActivitiesModel() {
        activities = new ArrayList();

        String[] temp;
        try {
            Statement st = (BeHealthy.conn).createStatement();

            ResultSet res = st.executeQuery("SELECT * FROM  activities");
            temp = res.toString().split(" ");
            while (res.next()) {
                addActivity(temp[0], Double.parseDouble(temp[1]), Integer.parseInt(temp[2]));

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
    public boolean addActivity(String name, double duration, int date) {
        Activity d = new Activity(name, duration, date);
        int i = -1;

        try {
            Statement st = (BeHealthy.conn).createStatement();

            i = st.executeUpdate("");//TODO ADD APPROPRIATE SQL QUERY TO ADD ACTIVITY

        } catch (SQLException e) {

            e.printStackTrace();
        }

        if (i == 1) {
            activities.add(d);
            return true;
        } else {
            return false;
        }

    }

    public boolean removeActivity(int act) {

        int i = -1;

        try {
            Statement st = (BeHealthy.conn).createStatement();

            i = st.executeUpdate("");//ADD APPROPRIATE SQL QUERY TO REMOVE ACTIVITY
        } catch (SQLException e) {

            e.printStackTrace();
        }

        if (i == 1) {
            activities.remove(act);
            return true;
        } else {
            return false;
        }

    }

    public boolean importActivity(File file) {
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<String> time = new ArrayList<String>();
            ArrayList<String> active = new ArrayList<String>();
            int count = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.trim();
                String[] lineParts = line.split(",");
                time.add(lineParts[0]);
                active.add(lineParts[1]);
                System.out.println("Time: " + time.get(count) + " Active: " + active.get(count));
                count = count + 1;

                //TODO use strings to add activity to database
            }
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

            long difference = stopDate.getTime() - startDate.getTime();
            double conversionFactor = 1000 * 60 * 60;
            double differenceHours = (double) (difference / conversionFactor);

            Calendar c = Calendar.getInstance();
            Date current = c.getTime();
            System.out.println(current);
            //addActivity("Sleep", differenceHours, current);		//TODO Uncomment once addActivity functionality is complete
            return true;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return false;
        }
    }

}
