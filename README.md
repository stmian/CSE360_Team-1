# BeHealthy
## ASU - CSE 360 - Spring 2014 - Group 1
### Version 1.0 - 05/02/2014
_____


- A simple application to track a variety of health metrics for a single user
- The application can track activiety levels including: time spent sleeping, time spent working out, time spent working, and calories eaten on a daily basis.
- It also has the ability to track several commonly measure health metrics such as: Blood Pressure, Blood Sugar, Heart Rate, and Weight.
- When a user inputs a new data item, the program will update a summary page with average statistics as well as a series of graphs that show trends in various metrics.
- The use is also able to export the data summary and graphs to a HTML document which can then be printed or shared.

## Requirements
- This application was implemented using the Java programming language and it is therefore necessary that you have the Java SDK installed on your computer.
- The application has been tested to run on a Windows environment, however as java is platform independent, it should be able to run on any operating system that is can run the Java SDK
- The project requires the use of the following non-standard Java libraries: `mysql-connector`, `jcommon`, `jfreechart`, and `jcalendar`
- An internet connection is required to use this application as it relies on a hosted mySQL database.

## Running the Application
### From a Jar File
1. Make sure you are connected to the internet
2. Run the application

### From the code
1. Open a Java IDE
2. Create a new project
3. Link all files
4. Add external libraries
5. Run program

## Architecture
- This program was developed using an incremental development plan in GUI was developed first followed by the various program functions
- The program also used the MVC system architecture to organize all of the required system components.
	- As such, each section of the application has it's own `view.java`, `model.java`, and `controller.java` files, in addition to the main application controller which is found in `BeHealthy.java`
- The program uses a mySQL databse hosted on a private server to maintain all of the user's data. When a user inputs new data, the data is immediatly displayed locally and is also transmitted to the server so that it will be accessable the next time that the application is opened.

