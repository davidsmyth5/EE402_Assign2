

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

@SuppressWarnings("serial")
public class Gui extends JFrame {
	private JTextField tfIteration;        
	private List<Float> temperatures;
	temperature myTemperature = new temperature();
	Graph graphPanel;
	
	public Gui() {
		initComponents();
		createEvents();
		
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
	
	private void initComponents(){
		JFrame frame = new JFrame("Temperature App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*/JPanel graphpanel = new JPanel();
        graphpanel.setBackground(Color.white);
        graphpanel.setPreferredSize(new Dimension(420,420));
        DrawGraph drawgraph = new DrawGraph();
        graphpanel.add(drawgraph);
        */
        JPanel graphPanel = new JPanel();
        graphPanel.setPreferredSize(new Dimension(400, 400));
        graphPanel.setBackground(Color.white);
        graphPanel.setLayout(new BorderLayout(0, 0));
        Graph graph = new Graph(myTemperature.getTemperatures());
        graphPanel.add(graph);
        
      
        
        JTextPane tpShowLastTemp = new JTextPane();
        tpShowLastTemp.setText("LastTemp");
        
        JSlider sliderPeriod = new JSlider();
        sliderPeriod.setPaintLabels(true);
        sliderPeriod.setMinimum(1);
        sliderPeriod.setMaximum(20);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
     
        
        frame.getContentPane().add(graphPanel);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        
        JButton btngetTemp = new JButton("read temperature");
        btngetTemp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		String lastTemp =String.valueOf(myTemperature.getTemp());
        		
        	}
        });
        btngetTemp.setHorizontalAlignment(SwingConstants.LEFT);
        
        JSlider slider = new JSlider();
        slider.setValue(10);
        slider.setMaximum(20);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(slider, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
        				.addComponent(btngetTemp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap(285, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addComponent(btngetTemp)
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        panel.setLayout(gl_panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
         
	}
}

