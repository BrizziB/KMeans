package view;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;

import java.awt.Shape;

public class CartesianPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	ChartPanel panel;
	JFreeChart chart;
	XYSeriesCollection datasets;
	
	
	public CartesianPanel() {
		datasets = new XYSeriesCollection();
	}
	
	public XYSeriesCollection getSeries(){
		return datasets;
	}
	
	public void resetDataset(){
		datasets.removeAllSeries();
	}
	
	public void addXYSeries(XYSeries series){
		datasets.addSeries(series);
	}
	
	public void plotChart(){
		JFreeChart chart = ChartFactory.createScatterPlot("Points", "X coordinates", "Y coordinates", datasets);
		XYPlot xyPlot = (XYPlot) chart.getPlot();
		xyPlot.setDomainCrosshairVisible(true);
		xyPlot.setRenderer(new XYLineAndShapeRenderer(false, true){ //estendo lo shape renderer in modo da distinguere come segnare i punti (e distinguere i centroidi)
			//private static final long serialVersionUID = 1L;

			@Override//qui non riesco ad selezionare il primo elemento di ogni serie usanod row e col
			public Shape getItemShape(int row, int col){
				//if(col==0)
					//return ShapeUtilities.createDiagonalCross(5, 2);
				//else
					return super.getItemShape(row, col);
			}
			
		});
		panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(900, 600));
        this.add(panel);
	}

}


