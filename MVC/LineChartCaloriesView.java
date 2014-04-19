import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;

public class LineChartCaloriesView  extends JPanel{

	JFreeChart chart;
	private ActivitiesModel model;
	String[] date;

	public LineChartCaloriesView(){
		model = new ActivitiesModel();
		XYDataset dataset = createDataset();

		chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(HomeView.CHART_DIMS[0],HomeView.CHART_DIMS[1]));
		this.add(chartPanel);
	}

	private XYDataset createDataset(){
		int entries = 0; //number of entries in DB
		int[] d;
		double[] calories;
		ArrayList<Activity> activity=model.getActivities();
		Calendar cal = Calendar.getInstance();

		//TODO: Get number of entries from DB
		for(int i=0; i<activity.size();i++){
			if(activity.get(i).typeName.equals("Eating")){
				entries++;
			}
		}
		
		date = new String[entries];
		d = new int[entries];
		calories = new double[entries];

		int x=-1;
		for(int i=0; i<activity.size(); i++){
			if(activity.get(i).typeName.equals("Eating")){
				x++;
				cal.setTime(activity.get(i).date);
				d[x] = x+1;
				date[x] = (Integer.toString(cal.get(Calendar.MONTH))+Integer.toString(cal.get(Calendar.DAY_OF_MONTH))+Integer.toString(cal.get(Calendar.YEAR)));
				calories[x] = activity.get(i).duration;
			}
		}

		XYSeries series1 = new XYSeries("Calories");
		for(int i=0; i<entries; i++){
			series1.add(d[i], calories[i]);
		}

		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);

		return dataset;		
	}

	private JFreeChart createChart(final XYDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Daily Calories",      // chart title
				"Date",                      // x axis label
				"Calories (Cal)",                      // y axis label
				dataset,                  // data
				PlotOrientation.VERTICAL,
				false,                     // include legend
				true,                     // tooltips
				false                     // urls
				);

		chart.setBackgroundPaint(null);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(null);
		plot.setDomainGridlinePaint(Color.gray);
		plot.setRangeGridlinePaint(Color.gray);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(1, false);
		renderer.setSeriesShapesVisible(0, false);
		plot.setRenderer(renderer);

		// change the auto tick unit selection to integer units only...
		final ValueAxis domain = new SymbolAxis("Dates", date);
		domain.setVerticalTickLabels(true);
		plot.setDomainAxis(domain);

		return chart;

	}

	public JFreeChart getChart(){
		return chart;
	}
}