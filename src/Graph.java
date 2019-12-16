import java.awt.BasicStroke;
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

@SuppressWarnings("serial")
public class Graph extends JPanel {
	private List<Float> temperatures;
	private List<Float> avgTemperatures = new ArrayList<>();
	 private int width = 800;
	    private int heigth = 400;
	    private int padding = 25;
	    private int labelPadding = 25;
	    private Color lineColor = new Color(44, 102, 230, 180);
	    private Color pointColor = new Color(100, 100, 100, 180);
	    private Color gridColor = new Color(200, 200, 200, 200);
	    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	    private static final Stroke GRAPH_STROKE2 = new BasicStroke(2f);
	    private int pointWidth = 4;
	    private int numberYDivisions = 10;
	 
	public Graph(List<Float> temperatures) {
	        this.temperatures=temperatures;
	        setVisible(true);
	    }
	
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (temperatures.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / ((getMaxTemperature()) - (getMinTemperature()));

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < temperatures.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxTemperature() - temperatures.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }
        List<Point> graphPoints2 = new ArrayList<>();
        for (int i = 0; i < avgTemperatures.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxTemperature() - avgTemperatures.get(i)) * yScale + padding);
            graphPoints2.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (temperatures.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinTemperature() + (getMaxTemperature() - getMinTemperature()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for marks and gridlines creating x axis
        for (int i = 0; i < temperatures.size(); i++) {
            if (temperatures.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (temperatures.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((temperatures.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
        
        //drawline for tempertaures
        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
            
        }
        
      //drawline for average tempertaures
        /*Stroke oldStroke2 = g2.getStroke();
        g2.setStroke(GRAPH_STROKE2);
        g2.setColor(Color.GREEN);
        for (int i = 0; i < graphPoints2.size() - 1; i++) {
            int x3 = graphPoints2.get(i).x;
            int y3 = graphPoints2.get(i).y;
            int x4 = graphPoints2.get(i + 1).x;
            int y4 = graphPoints2.get(i + 1).y;
            g2.drawLine(x3, y3, x4, y4);
        }
        g2.setStroke(oldStroke2);
        g2.setStroke(oldStroke2);
        g2.setColor(Color.orange);
        for (int i = 0; i < graphPoints2.size(); i++) {
            int x = graphPoints2.get(i).x - pointWidth ;
            int y = graphPoints2.get(i).y - pointWidth ;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }*/
        
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }
	private float getMinTemperature() {
        float minTemperature = Float.MAX_VALUE;
        for (Float temperature : temperatures) {
            minTemperature = Math.min(minTemperature, temperature);
        }
        return minTemperature;
    }

    private float getMaxTemperature() {
        float maxTemperature = Float.MIN_VALUE;
        for (Float temperature : temperatures) {
            maxTemperature = Math.max(maxTemperature, temperature);
        }
        return maxTemperature;
    }

    public void setAvgTemperatures(List<Float> temperatures) {
        this.avgTemperatures = temperatures;
        invalidate();
        this.repaint();
        
    }
    
    public void setTemperatures(List<Float> temperatures) {
        this.temperatures = temperatures;
        invalidate();      
    }

    public List<Float> gettemperatures() {
        return temperatures;
    }
	
}
