
import javax.swing.JPanel;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import java.awt.Dimension;
import java.io.*;
import java.util.ArrayList;
//import java.sql.Date;
import java.util.Date;


public class HomeController {

    //=================== Private properties/methods ===================//
    private HomeModel model;
    private HomeView view;
    private ActivitiesController activity;
    private UserController user;
    private HealthController health;
    final private int IMAGE_OFFSET = 30;

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

    public void printData(){
        String name, height, weight, birthdate, bmi, bp, hr, bs, avgSleep, avgWorkout, avgWork, avgCalories, totCal, totSleep, totWO, totWork;
        double sleepDuration = 0;
        double sleepCount = 0;
        double workDuration = 0;
        double workCount = 0;
        double WODuration = 0;
        double WOCount = 0;
        String activityName;
        double duration;


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

        }

        //User height in feet and inches
        double heightCM = user.getHeight();
        double heightSTD = heightCM * 0.393701;
        int heightFT = (int) heightSTD / 12;
        int heightINCH = (int) heightSTD % 12;

        //Get health metrics
        final ArrayList<HealthMetric> healthArray=health.gethealthMetrics();
        String metricName = "";
        double metricValue;
        double rawWeight = 0;
        Date weightDate = new Date(1);
        Date metricDate = new Date(2);

        for(int i=0;i<healthArray.size();i++)
        {
            metricName = healthArray.get(i).typeName;
            metricValue = healthArray.get(i).metric ;
            metricDate = healthArray.get(i).date;
            if(metricName.equals("Weight") && metricDate.after(weightDate)){
                rawWeight = metricValue;
                weightDate = metricDate;
            }

        }





        //Get data from database
        name = user.getName();
        height = String.valueOf(heightFT) + " ft. " + String.valueOf(heightINCH) + " in.";
        weight = String.valueOf(rawWeight) + " lbs";
        birthdate = String.valueOf(user.getBirthdate());
        bmi = "23.7";
        bp = "120/80";
        hr = "57";
        bs = "20";
        avgSleep = String.valueOf(sleepDuration/sleepCount) + " hr";
        avgWorkout = String.valueOf(WODuration/WOCount) + " hr";
        avgWork = String.valueOf(workDuration/workCount) + " hr";
        avgCalories = "1900";
        totCal = "170,000";
        totSleep = String.valueOf(sleepDuration) + " hr";
        totWO = String.valueOf(WODuration) + " hr";
        totWork = String.valueOf(workDuration) + " hr";

        try {
            //HTML template from file
            File htmlTemplateFile = new File("template.html");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(htmlTemplateFile));
            String htmlString = "";
            String line;

            while((line = bufferedReader.readLine())!= null){
                htmlString = htmlString + line + "\n";
            }

            //Replaces the appropriate tag in the template with the correct data
            htmlString = htmlString.replace("$name", name);
            htmlString = htmlString.replace("$height", height);
            htmlString = htmlString.replace("$weight", weight);
            htmlString = htmlString.replace("$birthdate", birthdate);
            htmlString = htmlString.replace("$bmi", bmi);
            htmlString = htmlString.replace("$avgBP", bp);
            htmlString = htmlString.replace("$avgHR", hr);
            htmlString = htmlString.replace("$avgBS", bs);
            htmlString = htmlString.replace("$avgSleep", avgSleep);
            htmlString = htmlString.replace("$avgWO", avgWorkout);
            htmlString = htmlString.replace("$avgWork", avgWork);
            htmlString = htmlString.replace("$avgCal", avgCalories);
            htmlString = htmlString.replace("$totCal", totCal);
            htmlString = htmlString.replace("$totSleep", totSleep);
            htmlString = htmlString.replace("$totWO", totWO);
            htmlString = htmlString.replace("$totWork", totWork);

            PrintWriter writer = new PrintWriter("BeHealthy.html", "UTF-8");
            writer.print(htmlString);
            writer.close();
        }
        catch(FileNotFoundException e){}
        catch(UnsupportedEncodingException e) {}
        catch (IOException e) {}
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
			ChartUtilities.writeChartAsPNG(fout, calories, HomeView.CHART_DIMS[0],HomeView.CHART_DIMS[1] + IMAGE_OFFSET);
			
			fout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }


}
