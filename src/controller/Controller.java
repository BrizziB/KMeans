package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	private String lastCalledSolver = "none";
	
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
				
				if(lastCalledSolver == "serial_solver"){
					completeFilePath = filePath+"//outputFile"+Integer.toString(clickCounter)+".txt";
				}
				else if(lastCalledSolver =="hadoop_solver"){
					completeFilePath = filePath+"//iteraz_"+Integer.toString(clickCounter)+"//part-r-00000";
				}
				acquireDataset(completeFilePath);
				window.plotGraph();
				clickCounter++;
			}
		});
		
		JButton serialCaller = window.getBtnSerial();
		serialCaller.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				lastCalledSolver = "serial_solver";
				try {
					ProcessBuilder process = new ProcessBuilder("cmd", "/c", "SerialKMeans.exe.lnk");
					Process p = process.start();
					p.waitFor();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton hadoopCaller = window.getBtnHadoop();
		
		hadoopCaller.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				lastCalledSolver = "hadoop_solver";
				
				ProcessBuilder process = new ProcessBuilder("java", "-jar", "../hadoop-kmeans.jar");
				try {
					process.start();
					
					process.redirectOutput();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
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
