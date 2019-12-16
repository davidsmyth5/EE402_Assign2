

import java.awt.BasicStroke;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class Gui extends JFrame implements Runnable {
	private JTextField tfIteration;        
	private List<Float> temperatures;
	private int iteration;
	private int time;
	temperature myTemperature = new temperature();
	Graph graphPanel;
	
	
	public void setTime(int time){
		this.time=time;
	}
	public void setiteration(int iteration){
		this.iteration=iteration;
	}
	
	
	public Gui() {
		initComponents();
		
		 //List<Double> scores = new ArrayList<>();
		 
	       /*/ Graph mainPanel = new Graph(temperatures);
	        mainPanel.setPreferredSize(new Dimension(800, 600));
	        JFrame frame = new JFrame("DrawGraph");
	        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(mainPanel);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);/*/
	}
	public void showGraph(){
		        
	}
	private void createEvents(){
		
		
		
	}
	
	/*public void setPrefSize(){
		
		if (i==1){
		graphPanel.setPreferredSize(new Dimension(401, 401));
		i--;
		}
		else{
		graphPanel.setPreferredSize(new Dimension(400, 400));
		
		}
	}*/
	
	public void setTextPane(String text){
		txtpnSetAvgTemp.setText(text);
	}
	
	
	private void initComponents(){
		JFrame frame = new JFrame("Temperature App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*JPanel graphpanel = new JPanel();
        graphpanel.setBackground(Color.white);
        graphpanel.setPreferredSize(new Dimension(420,420));
        DrawGraph drawgraph = new DrawGraph();
        graphpanel.add(drawgraph);*/
        
        JPanel graphPanel = new JPanel();
        graphPanel.setPreferredSize(new Dimension(400, 400));
        graphPanel.setBackground(Color.white);
        graphPanel.setLayout(new BorderLayout(0, 0));
        Graph graph = new Graph(myTemperature.getTemperatures());
        graphPanel.add(graph);
        
      
        
        JTextPane tpShowLastTemp = new JTextPane();
        tpShowLastTemp.setText("LastTemp");
        
        
        frame.getContentPane().add(graphPanel);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        
        JButton btngetTemp = new JButton("Read Temperature");
        btngetTemp.setForeground(new Color(0, 0, 0));
        
        
        btngetTemp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		int i=iteration;
        		while (i >=0){
        			        		
        		String lastTemp =String.valueOf(myTemperature.getTemp());
        		i--;
        		try {
        			int seconds = time*10;
					Thread.sleep(seconds);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		}   
        		setTextPane("WORK");
        		
        	}
        });
        
        final JSlider sliderIteration = new JSlider();
        sliderIteration.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent arg0) {
        		setiteration(sliderIteration.getValue());
        	}
        });
        
        	// if JSlider value is changed 
            
        sliderIteration.setSnapToTicks(true);
        sliderIteration.setFont(new Font("Tahoma", Font.PLAIN, 10));
        sliderIteration.setPaintLabels(true);
        sliderIteration.setMinimum(1);
        sliderIteration.setValue(10);
        sliderIteration.setMaximum(20);
        sliderIteration.setMajorTickSpacing(1);
        sliderIteration.setPaintTicks(true);
        sliderIteration.setToolTipText("Number of iterations set to =" + sliderIteration.getValue());
        
        final JSlider sliderTime = new JSlider();
        sliderTime.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent arg0) {
        		setTime( sliderTime.getValue());
        	}
        });
        
        
        sliderTime.setSnapToTicks(true);
        sliderTime.setValue(150);
        sliderTime.setPaintTicks(true);
        sliderTime.setPaintLabels(true);
        sliderTime.setMajorTickSpacing(30);
        sliderTime.setMaximum(300);
        sliderTime.setFont(new Font("Tahoma", Font.PLAIN, 10));
        sliderTime.setToolTipText("Iteration period set to =" + sliderIteration.getValue());
        
        JTextPane textPaneIteration = new JTextPane();
        textPaneIteration.setText("Number of Iterations");
        textPaneIteration.setEditable(false);
        
        JTextPane textPanePeriod = new JTextPane();
        textPanePeriod.setEditable(false);
        textPanePeriod.setText("Iteration Period (s)");
        
        JTextPane txtpnAverageTemp = new JTextPane();
        txtpnAverageTemp.setEditable(false);
        txtpnAverageTemp.setText("Average Temp");
        
        JTextPane txtpnSetAvgTemp = new JTextPane();
        txtpnSetAvgTemp.setForeground(Color.RED);
        txtpnSetAvgTemp.setFont(new Font("Tahoma", Font.BOLD, 28));
        
        
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(btngetTemp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(sliderIteration, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(sliderTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(textPanePeriod, 0, 0, Short.MAX_VALUE)
        				.addComponent(textPaneIteration, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
        			.addGap(133)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(txtpnSetAvgTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtpnAverageTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(89, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(btngetTemp, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(sliderIteration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        						.addComponent(textPaneIteration, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtpnAverageTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        				.addComponent(sliderTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(txtpnSetAvgTemp, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        					.addGap(14))
        				.addComponent(textPanePeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(19))
        );
        panel.setLayout(gl_panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       
        
        
	}
	@Override
	public void run() {
		
	}
}


