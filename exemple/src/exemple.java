import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class exemple{
	
    public static void main(String args[]) {
    	
    	
      
        JFrame frame = new JFrame("Frame Meau");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridBagLayout());
        
        JMenuBar ob = new JMenuBar();
        JMenu ob1 = new JMenu("FILE");
        JMenu ob2 = new JMenu("Help");
        ob.add(ob1);
        ob.add(ob2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m12 = new JMenuItem("Save as");
        JMenuItem m13 = new JMenuItem("Close");
        ob1.add(m11);
        ob1.add(m12);
        ob1.add(m13);
        JMenuItem m21 = new JMenuItem("About");
        JMenuItem m22 = new JMenuItem("Help Contents");
        ob2.add(m21);
        ob2.add(m22);
        
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts up to 10 characters
        
        JTextArea ta = new JTextArea(10,10);
       // ta.append(strA);
        
        JButton revers = new JButton("Reverse");
        
        revers.addActionListener(new ActionListener(){
        	   public void actionPerformed(ActionEvent ae){
        	      String strC = tf.getText();
        	      tf.setText(null);
        	      ta.append(strC);
        	      StringBuilder strB = new StringBuilder();
        	    	strB.append(strC+"-");
        	    	strB = strB.reverse();
        	    	String strA= strB.toString();
        	    	ta.append(strA);
        	    	ta.append("\n");
        	    	        	      // .... do some operation on value ...
        	   }
        	});
        
        JButton reset  = new JButton("Reset form");
        reset.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				reset.setBackground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				reset.setBackground(null);
			}
        	
        });
        reset.addActionListener(new ActionListener(){
     	   public void actionPerformed(ActionEvent ae){
     	      ta.setText(null);
     	      tf.setText(null);    	    	        	      // .... do some operation on value ...
     	   }
     	});
        
        
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(revers);
        panel.add(reset);
        
       
        frame.add(ob, new GridBagConstraints(0,0,1,1,1,1,
        		GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
        		new Insets(0,0,2,0),0,0));
        frame.add(label, new GridBagConstraints(GridBagConstraints.RELATIVE,1,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(tf, new GridBagConstraints(GridBagConstraints.RELATIVE,1,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(revers, new GridBagConstraints(GridBagConstraints.RELATIVE,2,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(reset, new GridBagConstraints(GridBagConstraints.RELATIVE,2,2,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(ta, new GridBagConstraints(GridBagConstraints.RELATIVE,3,2,2,2,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        //frame.getContentPane().add(BorderLayout.SOUTH, panel);
        //frame.getContentPane().add(BorderLayout.WEST, tf);
        //frame.getContentPane().add(BorderLayout.NORTH, ob);
        //frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
        frame.pack();
    
        
    }
    
    public class SendButtonClick implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
    		//String strC = tf.getText();
    	}
    	   
    }  
    

}
