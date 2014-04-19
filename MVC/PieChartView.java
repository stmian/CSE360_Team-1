import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class PieChartView extends JPanel{
	JFreeChart chart;	
	private ActivitiesModel model;
	
	public PieChartView(){
		model = new ActivitiesModel();
		PieDataset dataset = createDataset();

		chart = createChart(dataset, "Average Daily Breakdown");
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(HomeView.CHART_DIMS[0],HomeView.CHART_DIMS[1]));
		this.add(chartPanel);
	}

	private  PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		ArrayList<Activity> activities=model.getActivities();
		
		double sleep = 0;
		double work = 0;
		double workout = 0;
		int sleepCount=0;
		int workCount=0;
		int wOCount=0;
		double other=24;
	
		
		for(int i=0;i<activities.size();i++)
		{
			if(activities.get(i).typeName.equals("Sleep"))
			{
				sleepCount++;
				sleep+=activities.get(i).duration;
			}
			if(activities.get(i).typeName.equals("Work"))
			{
				workCount++;
				work+=activities.get(i).duration;
			}
			if(activities.get(i).typeName.equals("Workout"))
			{
				wOCount++;
				workout+=activities.get(i).duration;
			}
		}
		
		sleep = sleep/sleepCount;
		work = work/workCount;
		workout = workout/wOCount;
		other = 24 - sleep - work - workout;

		result.setValue("Sleep", sleep);
		result.setValue("Work", work);
		result.setValue("Workout", workout);
		result.setValue("Other",other);
		return result;
	}

	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart(title,          // chart title
				dataset,                // data
				false,                   // include legend
				true,
				false);

		chart.setBackgroundPaint(null);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setBackgroundPaint(null);
		return chart;

	}
	
	public JFreeChart getChart(){
		return chart;
	}

}
