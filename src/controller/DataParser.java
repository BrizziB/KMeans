package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import view.CartesianPanel;

public class DataParser {
	ArrayList<ArrayList<Double[]>> clusters ;
	CartesianPanel panel;
	
	public DataParser(CartesianPanel panel){
		clusters = new ArrayList();
		this.panel = panel;
	}
	
	

	public ArrayList getClusters() {
		return clusters;
	}


	public void readFromTxt(String filePath){
		BufferedReader reader = null;
	
	    try {
	        // use buffered reader to read line by line
	        reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
	
	        Double[] val = new Double[2] ;
	        String line = null;
	        String[] numbers = null;
	        int i=0;
	        int clusterNum=0;
	        int totPts=0;
	        boolean isCentroid = false;
	        //decodifica veramente illeggibile ma l'importante è che funzioni
	        //bisogna cambiare la logica qua ! sarà: centroide e punti del centroide, centroide e punti del centroide
	        ArrayList<Double[]> tmpList = new ArrayList<Double[]>();
	        while ((line = reader.readLine()) != null) {
		    
	        	if(i==0){
	        		totPts = Integer.valueOf(line);
	        		System.out.println("numero di punti: " + totPts);	
	        	}        	
	        	else if(line.isEmpty()){
	        		clusterNum++;
	        		clusters.add((ArrayList<Double[]>) tmpList.clone());
	        		tmpList.clear();
	        		
	        	}
	        	else{
	        		numbers = line.split("\\d\\s+");
		            val[0] = Double.valueOf(numbers[0].trim());
		            val[1] = Double.valueOf(numbers[1].trim());	        		
		            tmpList.add(val.clone());
		            isCentroid = false;
	        	}
	        	i++;
	        }
	    } catch (IOException e) {
	        System.err.println("Exception:" + e.toString());
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e) {
	                System.err.println("Exception:" + e.toString());
	            }
	        }
	    }
		
	}
}	