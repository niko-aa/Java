import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calc{
	static boolean goBack = false;
	static boolean finishEquals = false;
    /**
     * @param args
     */
    public static void main(String args[]) {
    	
    	
      
        JFrame frame = new JFrame("My Calc");
        frame.setLocationRelativeTo(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
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
        panel.setFocusCycleRoot(true);
        panel.grabFocus();
                
        JTextField CalcDisplay = new JTextField();
        CalcDisplay.setText("0");
        CalcDisplay.setFont(new Font("Areal", Font.BOLD, 25));
        CalcDisplay.setHorizontalAlignment(JTextField.RIGHT);
        CalcDisplay.setEditable(false);
        
       Toolkit.getDefaultToolkit().addAWTEventListener(
                new AWTEventListener()
                {
                    public void eventDispatched(AWTEvent event)
                    {
                        KeyEvent kevt = (KeyEvent) event;
                        if (kevt.getID() == KeyEvent.KEY_PRESSED) {
                        	System.out.println("Key Pressed! -> "+ Character.toString(kevt.getKeyChar()));
                        	
                        if (Character.toString(kevt.getKeyChar()).equals("1"))
                        {  
                            CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                        }else
                        	
                        if (Character.toString(kevt.getKeyChar()).equals("2"))
                        {
                            CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                        }else
                        	
                        if (Character.toString(kevt.getKeyChar()).equals("3"))
                        { 
                        	CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                        }else   
                        	
                        if (Character.toString(kevt.getKeyChar()).equals("4"))
                        {
                        	CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                        }else
                        	
                        if (Character.toString(kevt.getKeyChar()).equals("5"))
                        {
                        	CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                        }else
                            	
                        if (Character.toString(kevt.getKeyChar()).equals("6"))
                        {
                        	CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                    	}else
                    		
                    	if (Character.toString(kevt.getKeyChar()).equals("7"))
                    	{ 
                        	CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                    	}else
                        		
                        if (Character.toString(kevt.getKeyChar()).equals("8"))
                       	{
                         	CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                     	}else
                            		
                     	if (Character.toString(kevt.getKeyChar()).equals("9"))
                     	{
                     		CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                      	}else
                      		
                      	if (Character.toString(kevt.getKeyChar()).equals("0"))
                      	{
                      		CalcDisplay.setText(Digit(CalcDisplay.getText(), Character.toString(kevt.getKeyChar())));
                      	} else
                      		
                      	if (Character.toString(kevt.getKeyChar()).equals("."))
                      	{ 
                      		CalcDisplay.setText(Point(CalcDisplay.getText()));
                      	} else
                      		
                      	if (Character.toString(kevt.getKeyChar()).equals("+"))
                      	{  
                      		goBack = true;
                     		CalcDisplay.setText(Operator(CalcDisplay.getText(),"+"));
                      	} else
                      	
                      	if (Character.toString(kevt.getKeyChar()).equals("-"))
                      	{  
                      		goBack = true; 
                      		CalcDisplay.setText(Operator(CalcDisplay.getText(),"-"));
                      	} else
                      		
                      	if (Character.toString(kevt.getKeyChar()).equals("*"))
                      	{  
                      		goBack = true;
                      		CalcDisplay.setText(Operator(CalcDisplay.getText(),"*"));
                      	} else
                      		
                      	if (Character.toString(kevt.getKeyChar()).equals("/"))
                      	{  
                      		goBack = true;
                   		   	CalcDisplay.setText(Operator(CalcDisplay.getText(),"/"));
                      	} else
                      	
                      	if (Character.toString(kevt.getKeyChar()).equals("="))
                      	{  
                      		goBack = true;
                    		CalcDisplay.setText(Operator(CalcDisplay.getText(),""));
                      	} else
                      		
                      	if (((KeyEvent) event).getKeyCode() == KeyEvent.VK_ENTER)
                      	{  
                      		goBack = true;
                    		CalcDisplay.setText(Operator(CalcDisplay.getText(),""));
                      	} else
                      	
                      	if (((KeyEvent) event).getKeyCode() == KeyEvent.VK_BACK_SPACE)
                      	{  
                      		CalcDisplay.setText(Delete(CalcDisplay.getText()));
                      	} else
                      	if (((KeyEvent) event).getKeyCode() == KeyEvent.VK_ESCAPE)
                      	{  
                      		CalcDisplay.setText(C(CalcDisplay.getText()));
                      	} else
                      	if (((KeyEvent) event).getKeyCode() == KeyEvent.VK_DELETE)
                      	{  
                      		CalcDisplay.setText(CE(CalcDisplay.getText()));
                      	} else
                      	{}
                    }}
                },
                AWTEvent.KEY_EVENT_MASK );
              
       
        JButton button1 = new JButton("1");
        
        button1.addActionListener(new ActionListener(){
     	   public void actionPerformed(ActionEvent ae){
     		 CalcDisplay.setText(Digit(CalcDisplay.getText(), "1"));
     	   }     		 
     	});
       
        JButton button2 = new JButton("2");
        
        button2.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		CalcDisplay.setText(Digit(CalcDisplay.getText(), "2"));
       	   }
      	});
        
        JButton button3 = new JButton("3");
        
        button3.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		 CalcDisplay.setText(Digit(CalcDisplay.getText(), "3"));
       	   }
      	});
        
        JButton button4 = new JButton("4");
        
        button4.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		   CalcDisplay.setText(Digit(CalcDisplay.getText(), "4"));
       	   }
      	});
        
        JButton button5 = new JButton("5");
        
        button5.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		   CalcDisplay.setText(Digit(CalcDisplay.getText(), "5"));
       	   }
      	});
        
        JButton button6 = new JButton("6");
        
        button6.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		   CalcDisplay.setText(Digit(CalcDisplay.getText(), "6"));
       	   }
      	});
        
        JButton button7 = new JButton("7");
        
        button7.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		   CalcDisplay.setText(Digit(CalcDisplay.getText(), "7"));
       	   }
      	});
        
        JButton button8 = new JButton("8");
        
        button8.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		 CalcDisplay.setText(Digit(CalcDisplay.getText(), "8"));
       	   }
      	});
        
        JButton button9 = new JButton("9");
        
        button9.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		   CalcDisplay.setText(Digit(CalcDisplay.getText(), "9"));
       	   }
      	});
        
        JButton button0 = new JButton("0");
        
        button0.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		CalcDisplay.setText(Digit(CalcDisplay.getText(), "0"));
       	   }
      	});
        
        JButton buttonpunct = new JButton(".");
        
        buttonpunct.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		 CalcDisplay.setText(Point(CalcDisplay.getText()));
      	   }
      	 });
        
        JButton buttonplusminus = new JButton("+/-");
        
        buttonplusminus.addActionListener(new ActionListener(){
     	   public void actionPerformed(ActionEvent ae){
     		      String strLine = CalcDisplay.getText();
     		try {
     			if (strLine.equals("0") || strLine.equals("0.0") || strLine.equals("0.") || (Float.parseFloat(strLine) % 1 == 0) && 
     					((int)Float.parseFloat(strLine) == 0))  { 
           			CalcDisplay.setText("0");
           	     }
     			else
     	     		 if (strLine.equals("Operatie imposibila! Impartirea la zero")) { 
     	      			String strPresedZeroDevide = "0";
     	      			CalcDisplay.setText(strPresedZeroDevide);
     	      	     }
           		 else {
     			   float numLine = Float.parseFloat(strLine);
     			   numLine = numLine * -1;
     			  if (numLine % 1 == 0) {
   					CalcDisplay.setText(Integer.toString((int)numLine));
   					} 
   					else {
   						CalcDisplay.setText(String.valueOf(numLine));
   					}
     	        	   }
     		}
     		catch (NumberFormatException ex) {
   	         System.out.println("Operatie imposibila! Nu exista expresie pentru modificarea semnului");
       	  		} 
     		}
     	   });
        
                
        JButton buttonplus = new JButton("+");   //suma a doi termeni
        
        buttonplus.addActionListener(new ActionListener(){
     	   public void actionPerformed(ActionEvent ae){
     		   	goBack = true;
      		 	CalcDisplay.setText(Operator(CalcDisplay.getText(),"+"));
      	   }
     	}); 

        JButton buttonminus = new JButton("-");   //diferenta a doi termeni
                
        buttonminus.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
        		goBack = true; 
        		CalcDisplay.setText(Operator(CalcDisplay.getText(),"-"));
         	   }
        	});
        
        JButton buttoninmultire = new JButton("*");    //inmultirea a doi factori
        
        buttoninmultire.addActionListener(new ActionListener(){
     	   	public void actionPerformed(ActionEvent ae){
     		   goBack = true;
     		   CalcDisplay.setText(Operator(CalcDisplay.getText(),"*"));
      	   }
     	});
        
        JButton buttonimpartire = new JButton("/");    //impartirea a doi factori
        
        buttonimpartire.addActionListener(new ActionListener(){
      	   public void actionPerformed(ActionEvent ae){
      		   goBack = true;
      		   CalcDisplay.setText(Operator(CalcDisplay.getText(),"/"));
       	   }
      	});
        
        JButton buttonegal = new JButton("=");   //(+-*/) a doi termeni
        	
        buttonegal.addActionListener(new ActionListener(){
       	   public void actionPerformed(ActionEvent ae){
       		   goBack = true;
       		   CalcDisplay.setText(Operator(CalcDisplay.getText(),""));
        	   }
       	});
        
        JButton buttonCE = new JButton("CE");
        
        buttonCE.addActionListener(new ActionListener(){
        	   public void actionPerformed(ActionEvent ae){
        		   CalcDisplay.setText(CE(CalcDisplay.getText()));
         	   }
        	});
        
        JButton buttonC = new JButton("C");
        
        buttonC.addActionListener(new ActionListener(){
     	   public void actionPerformed(ActionEvent ae){
     		  CalcDisplay.setText(C(CalcDisplay.getText()));
      	   }
     	});
        
        JButton buttonBack = new JButton("<Back");
        
        buttonBack.addActionListener(new ActionListener(){
        	   public void actionPerformed(ActionEvent ae){
        	     CalcDisplay.setText(Delete(CalcDisplay.getText()));
        	}});
        
        panel.add(CalcDisplay); 
        panel.add(buttonCE);
        panel.add(buttonC);
        panel.add(buttonBack);
        panel.add(buttonimpartire);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(buttoninmultire);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(buttonminus);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(buttonplus);
        panel.add(buttonplusminus);
        panel.add(button0);
        panel.add(buttonpunct);
        panel.add(buttonegal);
       
        frame.add(ob, new GridBagConstraints(0,0,GridBagConstraints.REMAINDER,1,1,1,
        		GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
        		new Insets(0,0,2,0),0,0));
       
        
        frame.add(CalcDisplay, new GridBagConstraints(0,1,GridBagConstraints.REMAINDER,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(5,2,10,2),0,0));
        
        
        frame.add(buttonCE, new GridBagConstraints(0,2,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(buttonC, new GridBagConstraints(1,2,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(buttonBack, new GridBagConstraints(2,2,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(buttonimpartire, new GridBagConstraints(3,2,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        
        frame.add(button7, new GridBagConstraints(0,3,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(button8, new GridBagConstraints(1,3,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(button9, new GridBagConstraints(2,3,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(buttoninmultire, new GridBagConstraints(3,3,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        
        frame.add(button4, new GridBagConstraints(0,4,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(button5, new GridBagConstraints(1,4,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(button6, new GridBagConstraints(2,4,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(buttonminus, new GridBagConstraints(3,4,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        
        frame.add(button1, new GridBagConstraints(0,5,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(button2, new GridBagConstraints(1,5,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(button3, new GridBagConstraints(2,5,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(buttonplus, new GridBagConstraints(3,5,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        
        frame.add(buttonplusminus, new GridBagConstraints(0,6,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(button0, new GridBagConstraints(1,6,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(buttonpunct, new GridBagConstraints(2,6,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
        frame.add(buttonegal, new GridBagConstraints(3,6,1,1,0,1,
        		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
        		new Insets(2,2,2,2),0,0));
     
        frame.setVisible(true);
        frame.pack();
    }
    
    public class SendButtonClick implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
    	} 
    } 
    
    // metode implimentate
    
    private static String Digit(String strLine, String Cifra)  {     // add digit
    	if (strLine.equals("0")) {
 			goBack = false;
 			return Cifra;
 	     }
    	if (strLine.substring(strLine.length()-1,strLine.length()).equals("0") &&
    			(strLine.substring(strLine.length()-2,strLine.length()-1).equals("+") ||
    				strLine.substring(strLine.length()-2,strLine.length()-1).equals("-") ||
    					strLine.substring(strLine.length()-2,strLine.length()-1).equals("*") ||
							strLine.substring(strLine.length()-2,strLine.length()-1).equals("/"))) {
    		return strLine;	
    	}
 		 else
 			if (strLine.equals("Operatie imposibila! Impartirea la zero")) {
 	  			goBack = false;
 	  			return Cifra;
 	  	     }
 	 		 else
 	 			 if (finishEquals &&
 	 	    			(strLine.substring(strLine.length()-1,strLine.length()).equals("+") ||
 	 	      				strLine.substring(strLine.length()-1,strLine.length()).equals("-") ||
 	 	      					strLine.substring(strLine.length()-1,strLine.length()).equals("*") ||
 	 	  							strLine.substring(strLine.length()-1,strLine.length()).equals("/"))) {
 	 				 goBack = false;
 	 				 finishEquals =false;
 	 				 return strLine + Cifra;
 	 			 }
 	 			 else
 	 				if (finishEquals){
 	 					goBack = false;
 	 	 				 finishEquals =false;
 	 	 				 return Cifra;	
 	 				}
 	 				else
 	 			 	{
 	 				 goBack = false;
 	 				 return strLine + Cifra;
 	 			 	}
  	   }    
    
    private static String Point(String strLine) {		//add (.)
    	
  		try {
  		 if (strLine.equals("0"))  { 
  			return "0.";
  	     }
  		else
    		 if (strLine.equals("Operatie imposibila! Impartirea la zero")) { 
    			 return  "0.";
     	     }
  		 else 
  			if (strLine.substring(strLine.length()-1,strLine.length()).equals(".")) {
  				return strLine;
  			}
  			else
  				if (!(strLine.contains(".")) && 
  						!(strLine.contains("+") || strLine.contains("-") || strLine.contains("*") || strLine.contains("/"))) {
  					return strLine + ".";
  				} 
  				else 
  					if (strLine.contains(".") && 
      						!(((strLine.contains("+") || strLine.contains("-") || strLine.contains("*") || strLine.contains("/"))))) {
  						return strLine;
      				}  
  					else
  						if (strLine.substring(strLine.length()-1,strLine.length()).equals("+") ||
  							strLine.substring(strLine.length()-1,strLine.length()).equals("-") ||
  									strLine.substring(strLine.length()-1,strLine.length()).equals("*") ||
  											strLine.substring(strLine.length()-1,strLine.length()).equals("/")) {
  							return strLine + "0.";
  					}
  					else
  						if ((strLine.contains("+") && strLine.substring(strLine.indexOf("+")+1, strLine.length()).contains(".")) ||
  		      					(strLine.contains("-") && strLine.substring(strLine.indexOf("+")+1, strLine.length()).contains(".")) ||
  	      						(strLine.contains("*") && strLine.substring(strLine.indexOf("+")+1, strLine.length()).contains(".")) ||
  	      							(strLine.contains("/") && strLine.substring(strLine.indexOf("+")+1, strLine.length()).contains("."))) {
  							return strLine;
  						} else 
  							{
  							return strLine + ".";
  						} 
   	   }
  		
  		catch (StringIndexOutOfBoundsException ex) {
	         System.out.println("Operatie imposibila! Expresie inadmisibila pentru +");
    	  		}
  		return null;
    }
    
    private static String CE(String strLine) {       // CE
    	   
    	if (strLine.equals("0"))  { 
  			return "0";
  	     }
  		else
    		 if (strLine.equals("Operatie imposibila! Impartirea la zero")) { 
    			 return "0";
     	     }
  		 else 
  			if (!(strLine.contains("+") || strLine.contains("-") || strLine.contains("*") || strLine.contains("/"))) 
  				{
  				return "0";
  				} 
  				else {
  					
  					String operatie =null;
  					if (strLine.contains("+")) {
   						operatie = "+";
   					 }
   					 else
   						if (strLine.contains("-")) {
       						operatie = "-";
       					 }
       					 else
       						if (strLine.contains("*")) {
           						operatie = "*";
           					 }
           					 else
           						if (strLine.contains("/")) {
               						operatie = "/";
               					 }	
  					return strLine.substring(0,strLine.indexOf(operatie)) + operatie;	
  		} 
    }
        
    private static String C(String strLine) {			//C
    	goBack = false;
    	return "0";
    }
    
    private static String Delete(String strDelete) {		//Delete
	     if (!goBack) {
	     try {
	     if (strDelete.equals("0")) {
	    	 return "0";
	     }
	     else {
	    	 
	    	 if (strDelete.length()==1) {
	    		 return "0"; 
	    	 } else {
	    		 if (!(strDelete.contains("+") || strDelete.contains("-") || strDelete.contains("*") || strDelete.contains("/"))) {
	    			 return strDelete.substring(0, strDelete.length() - 1);
	    		 } else {
	    			String operatie =null;
 					if (strDelete.contains("+")) {
  						operatie = "+";
  					 }
  					 else
  						if (strDelete.contains("-")) {
      						operatie = "-";
      					 }
      					 else
      						if (strDelete.contains("*")) {
          						operatie = "*";
          					 }
          					 else
          						if (strDelete.contains("/")) {
              						operatie = "/";
              					 }	
 					if (strDelete.substring(strDelete.length() - 1,strDelete.length()).equals(operatie)){
 						return strDelete;
						} else {
							return strDelete.substring(0,strDelete.length() - 1);
						}
	    		 }
	    	 
	    	 }
	     }
	     }
	    catch (StringIndexOutOfBoundsException e) {
	         System.out.println("Operatie imposibila! Nu exista caractere de inlaturat");
	    } 
	      }
	      else {
	    	  return strDelete;
	      }
	     return null;
    }
    
    private static String Operator(String strLine,String Oper) {		// Operatie +; -; *; /; =; enter;
    	
    	if (strLine.equals("0"))  { 
 			return "0" + Oper;
 	     }
 		else
    		 if (strLine.equals("Operatie imposibila! Impartirea la zero")) {
    			 return "0";
     	     }
    		 else
    	    	 if (strLine.substring(0,1).equals("-") && 
    	    			 !(strLine.substring(1,strLine.length()).contains("+") || 
    	    					 strLine.substring(1,strLine.length()).contains("-") || 
    	    					 	strLine.substring(1,strLine.length()).contains("*") || 
    	    					 		strLine.substring(1,strLine.length()).contains("/"))) {
    	    		 return strLine + Oper;
			 }
 		 else
 			if (strLine.substring(strLine.length()-1,strLine.length()).equals(".") && 
	    			 !(strLine.substring(1,strLine.length()).contains("+") || 
	    					 strLine.substring(1,strLine.length()).contains("-") || 
	    					 	strLine.substring(1,strLine.length()).contains("*") || 
	    					 		strLine.substring(1,strLine.length()).contains("/"))) {
 				return strLine + "0" + Oper;
 			}
 			else		
 			 if (!(strLine.contains("+") || strLine.contains("-") || strLine.contains("*") || strLine.contains("/"))) {
 				return strLine + Oper;
 			 }
 			 else
 				 if (strLine.substring(strLine.length()-1,strLine.length()).equals("+") ||
						strLine.substring(strLine.length()-1,strLine.length()).equals("-") ||
								strLine.substring(strLine.length()-1,strLine.length()).equals("*") ||
										strLine.substring(strLine.length()-1,strLine.length()).equals("/")) 
 				 {
 					 if (strLine.substring(strLine.length()-1,strLine.length()).equals(Oper)) {
 						return strLine; 
 					 } else
 						return strLine.substring(0,strLine.length()-1) + Oper;
			 }
 				 else {
 					String operatie = null;
 					if (strLine.substring(0,1).equals("-")) 
 					
 					{
 					 if (strLine.substring(1,strLine.length()).contains("+")) {
 						operatie = "+";
 					 }
 					 else
 						if (strLine.substring(1,strLine.length()).contains("-")) {
     						operatie = "-";
     					 }
     					 else
     						if (strLine.substring(1,strLine.length()).contains("*")) {
         						operatie = "*";
         					 }
         					 else
         						if (strLine.substring(1,strLine.length()).contains("/")) {
             						operatie = "/";
             					 }
 					}
 					else
 					{
 						 if (strLine.contains("+")) {
      						operatie = "+";
      					 }
      					 else
      						if (strLine.contains("-")) {
          						operatie = "-";
          					 }
          					 else
          						if (strLine.contains("*")) {
              						operatie = "*";
              					 }
              					 else
              						if (strLine.contains("/")) {
                  						operatie = "/";
                  					 }	
 					}
 			System.out.print(operatie);	 
 			switch (operatie) {
 			case "+": 
 				int indexPlus = strLine.indexOf("+");
 				String strPlusNum1 = strLine.substring(0,indexPlus);
 				float plusNum1 = Float.parseFloat(strPlusNum1);
 				String strPlusNum2 = strLine.substring(indexPlus+1,strLine.length());
 				float plusNum2 = Float.parseFloat(strPlusNum2);
 				float rezultatPlus = plusNum1 + plusNum2;
 				finishEquals = true;
 				if (rezultatPlus % 1 == 0) {
 					int Intreg = (int)rezultatPlus;
 					String strRezultatPlus = Integer.toString(Intreg);
 					return strRezultatPlus + Oper;
 				} 
 				else {
 				String strRezultatPlus = String.valueOf(rezultatPlus);
 				return strRezultatPlus + Oper;
 				}
 				 			
 			case "-": 
 				int indexMinus = strLine.indexOf("-");
 				String strMinusNum1 = strLine.substring(0,indexMinus);
 				float minusNum1 = Float.parseFloat(strMinusNum1);
 				String strMinusNum2 = strLine.substring(indexMinus+1,strLine.length());
 				float minusNum2 = Float.parseFloat(strMinusNum2);
 				float rezultatMinus = minusNum1 - minusNum2;
 				finishEquals = true;
 				if (rezultatMinus % 1 == 0) {
 					int Intreg = (int)rezultatMinus;
 					String strRezultatMinus = Integer.toString(Intreg);
 					return strRezultatMinus + Oper;
 				} 
 				else {
 				String strRezultatMinus = String.valueOf(rezultatMinus);
 				return strRezultatMinus + Oper;
 				}
     		
 			case "*": 
 				int indexInmultire = strLine.indexOf("*");
 				String strInmultireNum1 = strLine.substring(0,indexInmultire);
 				float inmultireNum1 = Float.parseFloat(strInmultireNum1);
 				String strInmultireNum2 = strLine.substring(indexInmultire+1,strLine.length());
 				float inmultireNum2 = Float.parseFloat(strInmultireNum2);
 				float rezultatInmultire = inmultireNum1 * inmultireNum2;
 				finishEquals = true;
 				if (rezultatInmultire % 1 == 0) {
 					int Intreg = (int)rezultatInmultire;
 					String strRezultatInmultire = Integer.toString(Intreg);
 					return strRezultatInmultire + Oper;
 				} 
 				else {
 				String strRezultatInmultire = String.valueOf(rezultatInmultire);
 				return strRezultatInmultire + Oper;
 				}
     		
 			case "/": 
 				int indexImpartire = strLine.indexOf("/");
 				String strImpartireNum1 = strLine.substring(0,indexImpartire);
 				float impartireNum1 = Float.parseFloat(strImpartireNum1);
 				String strImpartireNum2 = strLine.substring(indexImpartire+1,strLine.length());
 				float impartireNum2 = Float.parseFloat(strImpartireNum2);
 				finishEquals = true;
 				if (impartireNum2 == 0) {
 					return "Operatie imposibila! Impartirea la zero";	
 				}
 				else {
 					float rezultatImpartire = impartireNum1 / impartireNum2;
 					if (rezultatImpartire % 1 == 0) {
 						int Intreg = (int)rezultatImpartire;
 						String strRezultatImpartire = Integer.toString(Intreg);
 						return strRezultatImpartire + Oper;
 					} 
 				else {
 					String strRezultatImpartire = String.valueOf(rezultatImpartire);
 					return strRezultatImpartire + Oper;
 				}
 				}
     		
 			}
 		 }
		return null;
    	
    }
    

}