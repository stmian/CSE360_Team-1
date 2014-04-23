import javax.swing.JPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import java.awt.Dimension;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.net.*;


public class HomeController {

    //=================== Private properties/methods ===================//
    private HomeModel model;
    private HomeView view;
    private ActivitiesController activity;
    private UserController user;
    private HealthController health;
    final private int IMAGE_OFFSET = 100;

    //=================== Public properties/methods ====================//
    public HomeController(HomeModel model, ActivitiesController activitiesController, UserController userController, HealthController healthController) {
        this.model = new HomeModel();
        this.view = new HomeView(this, this.model);
        this.view.createView();
        activity = activitiesController;
        user = userController;
        health = healthController;
    }

    public JPanel getPanel() {
        return view.getPanel();
    } //getPanel

    public String getName(){
        String name;
        name = user.getName();
        return name;
    }

    public double getHeight(){
        double height;
        height = user.getHeight();
        return height;


    }

    public double getWeight(){
        String metricName;
        double metricValue;
        Date metricDate;
        double weight = 0;
        Date weightDate = new Date(0);

        final ArrayList<HealthMetric> healthArray=health.gethealthMetrics();
        for(int i=0;i<healthArray.size();i++)
        {
            metricName = healthArray.get(i).typeName;
            metricValue = healthArray.get(i).metric ;
            metricDate = healthArray.get(i).date;
            if(metricName.equals("Weight") && metricDate.after(weightDate)){
                weight = metricValue;
                weightDate = metricDate;
            }
        }

        return weight;
    }

    public String getBirthdate(){
        String birthdate;
        birthdate = String.valueOf(user.getBirthdate());
        return birthdate;
    }

    public double getBMI(){
        double weight = getWeight();
        double heightCM = getHeight();
        double heightIN = heightCM * 0.393701;
        double BMI = (weight / (heightIN * heightIN) * 703);
        return BMI;
    }

    public String bp(){
        String metricName;
        String metricValue;
        Date metricDate;
        String BP = "";
        Date BPDate = new Date(0);

        final ArrayList<HealthMetric> healthArray=health.gethealthMetrics();
        for(int i=0;i<healthArray.size();i++)
        {
            metricName = healthArray.get(i).typeName;
            metricValue = String.valueOf(healthArray.get(i).metric) ;
            metricDate = healthArray.get(i).date;
            if(metricName.equals("Blood Pressure") && metricDate.after(BPDate)){
                BP = metricValue;
                BPDate = metricDate;
            }
        }

        return BP;
    }

    public double getHR(){
        String metricName;
        double metricValue;
        Date metricDate;
        double HR = 0;
        Date HRDate = new Date(0);

        final ArrayList<HealthMetric> healthArray=health.gethealthMetrics();
        for(int i=0;i<healthArray.size();i++)
        {
            metricName = healthArray.get(i).typeName;
            metricValue = healthArray.get(i).metric ;
            metricDate = healthArray.get(i).date;
            if(metricName.equals("Heart Rate") && metricDate.after(HRDate)){
                HR = metricValue;
                HRDate = metricDate;
            }
        }

        return HR;
    }

    public double[] getActivityData(){
        String activityName;
        double duration = 0;
        double sleepDuration = 0;
        double sleepCount = 0;
        double workDuration = 0;
        double workCount = 0;
        double WODuration = 0;
        double WOCount = 0;
        double totCals = 0;
        double calDays = 0;
        double[] activityData = new double[8];

        final ArrayList<Activity> array=activity.getActivities();
        for(int i=0;i<array.size();i++)
        {

            activityName = array.get(i).typeName;
            duration=array.get(i).duration;
            if(activityName.equals("Sleep")){
                sleepDuration += duration;
                sleepCount += 1;
            }
            else if(activityName.equals("Work")){
                workDuration += duration;
                workCount += 1;
            }
            else if(activityName.equals("Workout")){
                WODuration += duration;
                WOCount += 1;
            }
            else if(activityName.equals("Eating")){
                totCals += duration;
                calDays += 1;
            }
        }

        activityData[0] = sleepDuration;
        activityData[1] = sleepCount;
        activityData[2] = workDuration;
        activityData[3] = workCount;
        activityData[4] = WODuration;
        activityData[5] = WOCount;
        activityData[6] = totCals;
        activityData[7] = calDays;

        return activityData;
    }


    public void printData(){
       model.printData(getName(), getBirthdate(), getHeight(), getActivityData(), getBMI(),
             getHR(), getWeight());
    }
    
    public void chartsToImages(JFreeChart pie, JFreeChart weight, JFreeChart calories){
    	File pieFile = new File("piechart.png");
    	File weightFile = new File("linechartweight.png");
    	File calFile = new File("linechartcalories.png");
    	
    	try {
    		FileOutputStream fout = new FileOutputStream(pieFile);
			ChartUtilities.writeChartAsPNG(fout, pie, HomeView.CHART_DIMS[0] + IMAGE_OFFSET,HomeView.CHART_DIMS[1] + IMAGE_OFFSET);
			
			fout = new FileOutputStream(weightFile);
			ChartUtilities.writeChartAsPNG(fout, weight, HomeView.CHART_DIMS[0] + IMAGE_OFFSET,HomeView.CHART_DIMS[1] + IMAGE_OFFSET);
			
			fout = new FileOutputStream(calFile);
			ChartUtilities.writeChartAsPNG(fout, calories, HomeView.CHART_DIMS[0] + IMAGE_OFFSET ,HomeView.CHART_DIMS[1] + IMAGE_OFFSET);
			
			fout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }


}
