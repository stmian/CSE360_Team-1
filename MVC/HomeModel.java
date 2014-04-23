
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HomeModel {
    //=================== Private properties/methods ===================//
    /*TODO: Enter private variables here e.e.: private String name; */



    //=================== Public properties/methods ====================//
    public HomeModel() {

    }


    /*TODO: Enter public getters/setters here e.g.:

     public String getName() {
     return name;
     }
     */

    public void printData(String name, String birthdate, double height, double[] activityData,
                          double BMI, double HR, double weight, String BP){
        String currentDate = "";
        //String name = name;
        String heightString = "";
        String weightString = "";
        String bmi = "";
        String bp = "";
        String hr = "";
        String avgSleep = "";
        String avgWorkout = "";
        String avgWork = "";
        String avgCalories = "";
        String totCal = "";
        String totSleep = "";
        String totWO = "";
        String totWork = "";

        DecimalFormat df = new DecimalFormat("#.##");

        //Get current date
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();

        //User height in feet and inches
        double heightSTD = height * 0.393701;
        int heightFT = (int) heightSTD / 12;
        int heightINCH = (int) heightSTD % 12;



        //Get data from database
        currentDate = String.valueOf(dateFormat.format(date));
        heightString = String.valueOf(heightFT) + " ft. " + String.valueOf(heightINCH) + " in.";
        weightString = String.valueOf(weight) + " lbs";
        birthdate = birthdate;
        bmi = String.valueOf(df.format(BMI));
        bp = BP;
        hr = String.valueOf(HR) + " BMP";
        avgSleep = String.valueOf(df.format(activityData[0]/activityData[1])) + " hr";
        avgWorkout = String.valueOf(df.format(activityData[4]/activityData[5])) + " hr";
        avgWork = String.valueOf(activityData[2]/activityData[3]) + " hr";
        avgCalories = String.valueOf(df.format(activityData[6]/activityData[7])) + " Cal";
        totCal = String.valueOf(activityData[6]) + " Cal";
        totSleep = String.valueOf(activityData[0]) + " hr";
        totWO = String.valueOf(activityData[4]) + " hr";
        totWork = String.valueOf(activityData[2]) + " hr";

        try {
            //HTML template from file at URL
            URL template = new URL("https://dl.dropboxusercontent.com/u/9366248/template.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(template.openStream()));
            String htmlString = "";
            String line;

            while((line = bufferedReader.readLine())!= null){
                htmlString = htmlString + line + "\n";
            }

            //Replaces the appropriate tag in the template with the correct data
            htmlString = htmlString.replace("$reportDate", currentDate);
            htmlString = htmlString.replace("$name", name);
            htmlString = htmlString.replace("$height", heightString);
            htmlString = htmlString.replace("$weight", weightString);
            htmlString = htmlString.replace("$birthdate", birthdate);
            htmlString = htmlString.replace("$bmi", bmi);
            htmlString = htmlString.replace("$avgBP", bp);
            htmlString = htmlString.replace("$avgHR", hr);
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
}
