package controller;

import java.awt.EventQueue;

import view.CartesianPanel;

public class Main {

	
	
	public static void main(String[] args) {
		CartesianPanel cartesianPanel = new CartesianPanel();
		Controller controller = new Controller(cartesianPanel);
		controller.buildWindow();
		
		//questo lo uso per aspettare che la mainWindow sia costruita
		//pazzesco che funzioni davvero con così poco!
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				controller.startListening();
			}
		});
		
		

	}
	
	

}
