package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import view.CartesianPanel;
import view.MainWindow;

public class Controller {

	private MainWindow window;
	private static CartesianPanel cartesianPanel;
	private String filePath = "..//tmp_points";
	
	public Controller(CartesianPanel cartesianPanel){
		Controller.cartesianPanel=cartesianPanel;
		
	}
	
	public void buildWindow(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainWindow(cartesianPanel);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void startListening(){
		
		JButton iterController = window.getBtnIterate();
		iterController.addActionListener(new ActionListener(){
		int clickCounter = 0;
		String completeFilePath = filePath;
			@Override
			public void actionPerformed(ActionEvent e){
				cartesianPanel.resetDataset();
				//inserisci da c++ il numero di file, così puoi ciclare
				completeFilePath = filePath+"//outputFile"+Integer.toString(clickCounter)+".txt";
				acquireDataset(completeFilePath);
				window.plotGraph();
				clickCounter++;
				
				
			}
			
		});
		
	}
	
	public void acquireDataset(String filePath){	
		DataParser dataParser = new DataParser(cartesianPanel);
		dataParser.readFromTxt(filePath);
		ArrayList<ArrayList<Double[]>> clusters = dataParser.getClusters();
		int i=0;
		for(ArrayList<Double[]> points : clusters){
			XYSeries serie = new XYSeries("serie_"+i);
			i++;
			for(Double[] point : points){
				
				serie.add(point[0], point[1]);
				
			}
			cartesianPanel.addXYSeries(serie); //aggiornare il dataset comporta il redraw automatico del grafico
			
		}
		System.out.println("punti acquisiti");
	}
}
