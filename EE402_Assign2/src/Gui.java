import javax.swing.*;

import java.awt.event.*;

import javax.swing.border.*;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;

public class Gui extends JFrame {        
        JLabel l1 = new JLabel("This is a Test Swing Application");
        JButton b1, b2;
        JPasswordField pwd;
        private JTextField tfInterval;
        final int[] x = new int[] {0, 40, 80, 120, 160, 200, 240, 280, 320, 360};
        final int[] y = new int[] {100, 200, 100, 50, 200, 0, 300, 200, 100, 300};
		 
	public Gui() {
		setUpPanel();
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 720);
		setUpPanel();
	}

	private void setUpPanel(){
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JButton btnRead = new JButton("Read");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnRead.setBounds(15, 533, 137, 115);
		getContentPane().add(btnRead);
		
		tfInterval = new JTextField();
		tfInterval.setHorizontalAlignment(SwingConstants.CENTER);
		tfInterval.setText("Enter Time Interval");
		tfInterval.setBounds(635, 533, 238, 115);
		getContentPane().add(tfInterval);
		tfInterval.setColumns(10);
		
		JPanel graphPanel = new JPanel();
		graphPanel.setBackground(Color.BLACK);
		graphPanel.setBounds(94, 46, 663, 424);
		graphPanel.add(new JPanelImpl(x, y));
		
		getContentPane().add(graphPanel);
	}
	
	private static final class JPanelImpl extends JPanel {
        private final int[] x;
        private final int[] y;

        public JPanelImpl(final int[] x, final int[] y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void paintComponent(final Graphics graphics) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, getWidth(), getHeight());

            for(int i = 0; i < this.x.length; i++) {
                if(i + 1 < this.x.length) {
                    final int x0 = this.x[i];
                    final int y0 = this.y[i];
                    final int x1 = this.x[i + 1];
                    final int y1 = this.y[i + 1];

                    graphics.setColor(Color.BLACK);
                    graphics.drawLine(x0, y0, x1, y1);
                    graphics.fillOval(x1 - 3, y1 - 3, 6, 6);
                }
            }
        }
    }
}
