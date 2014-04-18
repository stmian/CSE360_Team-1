import java.awt.Color;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;

public class LineChartCaloriesView  extends JPanel{
	
	JFreeChart chart;
	
	public LineChartCaloriesView(){
		XYDataset dataset = createDataset();

		chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(HomeView.CHART_DIMS[0],HomeView.CHART_DIMS[1]));
		this.add(chartPanel);
	}

	private XYDataset createDataset(){
		final int MAX = 90; //maximum number of entries to read in
		int entries = 20; //number of entries in DB
		int start = 0; //starting location to read in to arrays
		int[] date;
		double[] calories;

		//TODO: Get number of entries from DB

		if(entries > MAX){
			start = entries - MAX;
			entries = MAX;
		}

		date = new int[entries];
		calories = new double[entries];

		for(int i=start; i<entries; i++){
			date[i] = i+1; //TODO: Read in last 90 entries
			calories[i] = 2500 - (i * (int)(Math.random() * 50)); //TODO: Read in last 90 entries
		}

		XYSeries series1 = new XYSeries("Calories");
		for(int i=0; i<entries; i++){
			series1.add(date[i], calories[i]);
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
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		return chart;

	}

	public JFreeChart getChart(){
		return chart;
	}
}