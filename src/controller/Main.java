package controller;

import java.awt.EventQueue;
import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import view.CartesianPanel;
import view.MainWindow;

public class Main {

	
	
	
	
	public static void main(String[] args) {
		
		CartesianPanel cartesianPanel = new CartesianPanel();
		DataParser dataParser = new DataParser(cartesianPanel);
		dataParser.readFromTxt("..//tmp_points//outputFile0.txt");
		ArrayList<ArrayList<Double[]>> clusters = dataParser.getClusters();
		int i=0;
		
		for(ArrayList<Double[]> points : clusters){
			XYSeries serie = new XYSeries("serie_"+i);
			i++;
			for(Double[] point : points){
				
				serie.add(point[0], point[1]);
				
			}
			cartesianPanel.addXYSeries(serie);
			
		}
		XYSeriesCollection tmp = cartesianPanel.getSeries();
		System.out.println("punti acquisiti");
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(cartesianPanel);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
