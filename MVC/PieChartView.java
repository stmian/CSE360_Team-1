import java.awt.Dimension;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class PieChartView extends JPanel{
	
	public PieChartView(){
		System.out.println("Loading chart...");
		
		PieDataset dataset = createDataset();
		
		JFreeChart chart = createChart(dataset, "Average Daily Breakdown");
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(178,178));
		this.add(chartPanel);
		
		System.out.println("Chart loaded.");
	}
	
	private  PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        
        double sleep = 8.3;
        double work = 8.1;
        double workout = .89;
        double other = 24-sleep-work-workout;
        
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

}
