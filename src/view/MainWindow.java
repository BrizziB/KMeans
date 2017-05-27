package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.jfree.data.xy.XYSeries;

public class MainWindow {

	public JFrame frame;

	/**
	 * Create the application.
	 */
	CartesianPanel cartesianPanel;
	
	private JButton btnRunSerialKmeans;
	private JButton btnLoadDataset;
	private JCheckBox chckbxPlotPoints;
	private JButton btnRunHadoopKmeans;
	private JButton btnRunCudaKmeans;
	private JButton btnIterate;
	
	public MainWindow(CartesianPanel cartesianPanel) {
		this.cartesianPanel = cartesianPanel;
		initialize();
		
	}

	public JButton getBtnIterate(){
		return btnIterate;
	}
	public JButton getBtnSerial(){
		return btnRunSerialKmeans;
	}
	public JButton getBtnHadoop(){
		return btnRunHadoopKmeans;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//UIManager.put("swing.boldMetal", Boolean.FALSE);
		frame = new JFrame();
		frame.setTitle("K-Means");
		frame.getContentPane().setBackground(new Color(51, 153, 204));
		frame.setBounds(100, 100, 1400, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		chckbxPlotPoints = new JCheckBox("Plot Points");
		springLayout.putConstraint(SpringLayout.EAST, chckbxPlotPoints, -67, SpringLayout.EAST, frame.getContentPane());
		chckbxPlotPoints.setBackground(new Color(0, 153, 204));
		frame.getContentPane().add(chckbxPlotPoints);
		
		btnLoadDataset = new JButton("Load Dataset...");
		springLayout.putConstraint(SpringLayout.WEST, chckbxPlotPoints, 0, SpringLayout.WEST, btnLoadDataset);
		springLayout.putConstraint(SpringLayout.SOUTH, chckbxPlotPoints, -6, SpringLayout.NORTH, btnLoadDataset);
		springLayout.putConstraint(SpringLayout.EAST, btnLoadDataset, -31, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnLoadDataset, 49, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(btnLoadDataset);
		
		btnRunSerialKmeans = new JButton("Run Serial K-Means");
		springLayout.putConstraint(SpringLayout.NORTH, btnRunSerialKmeans, 15, SpringLayout.SOUTH, btnLoadDataset);
		springLayout.putConstraint(SpringLayout.WEST, btnLoadDataset, 0, SpringLayout.WEST, btnRunSerialKmeans);
		springLayout.putConstraint(SpringLayout.EAST, btnRunSerialKmeans, -31, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnRunSerialKmeans);
		
		btnRunHadoopKmeans = new JButton("Run Hadoop K-means");
		springLayout.putConstraint(SpringLayout.NORTH, btnRunHadoopKmeans, 16, SpringLayout.SOUTH, btnRunSerialKmeans);
		springLayout.putConstraint(SpringLayout.WEST, btnRunSerialKmeans, 0, SpringLayout.WEST, btnRunHadoopKmeans);
		springLayout.putConstraint(SpringLayout.WEST, btnRunHadoopKmeans, 1374, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnRunHadoopKmeans, -31, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnRunHadoopKmeans);
		
		btnRunCudaKmeans = new JButton("Run CUDA K-means");
		springLayout.putConstraint(SpringLayout.NORTH, btnRunCudaKmeans, 16, SpringLayout.SOUTH, btnRunHadoopKmeans);
		springLayout.putConstraint(SpringLayout.WEST, btnRunCudaKmeans, 0, SpringLayout.WEST, btnLoadDataset);
		springLayout.putConstraint(SpringLayout.EAST, btnRunCudaKmeans, -31, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnRunCudaKmeans);
		
		btnIterate = new JButton("Iterate");
		springLayout.putConstraint(SpringLayout.NORTH, btnIterate, 64, SpringLayout.SOUTH, btnRunCudaKmeans);
		springLayout.putConstraint(SpringLayout.WEST, btnIterate, -149, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnIterate, -78, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnIterate);
		
		cartesianPanel.plotChart();
		frame.getContentPane().add(cartesianPanel);
		
	}
	
	public void plotGraph(){
		cartesianPanel.plotChart();	
		frame.getContentPane().add(cartesianPanel);
	}
	
}
