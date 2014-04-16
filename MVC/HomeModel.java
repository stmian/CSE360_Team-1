
import java.io.*;
import java.lang.Object;


public class HomeModel {
    //=================== Private properties/methods ===================//
    /*TODO: Enter private variables here e.e.: private String name; */



    //=================== Public properties/methods ====================//
    public HomeModel() {

    }

    public void printData(){
        String name, height, weight, birthdate, bmi, bp, hr, bs, avgSleep, avgWorkout, avgWork, avgCalories, totCal, totSleep, totWO, totWork = "";

        //Get data from database
        name = "Zach Josephson";
        height = "6ft 2in";
        weight = "200lbs";
        birthdate = "02/28/1994";
        bmi = "23.7";
        bp = "120/80";
        hr = "57";
        bs = "20";
        avgSleep = "8hrs";
        avgWorkout = "1hr";
        avgWork = "7hr";
        avgCalories = "1900";
        totCal = "170,000";
        totSleep = "136hr";
        totWO = "36hr";
        totWork = "127 hr";


        try {
            //HTML template from file
            File htmlTemplateFile = new File("template.html");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(htmlTemplateFile));
            String htmlString = "";
            String line = "";

            while((line = bufferedReader.readLine())!= null){
                htmlString = htmlString + line + "\n";
                System.out.println(line);
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

    /*TODO: Enter public getters/setters here e.g.:

     public String getName() {
     return name;
     }
     */
}
