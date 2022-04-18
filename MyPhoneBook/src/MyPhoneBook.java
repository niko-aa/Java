import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyPhoneBook {
    
	static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB = "phonebookdb";
    static final String USER = "root";
    static final String PASSWORD = "Alunish15";
     
 public static void main(String[] args) {
	 
	 	Connection conn = dbConection();
	    Statement stmt = dbStatement(conn);
	    dbCreate(stmt);
	 
	 JFrame frameApp = new JFrame("MyPhoneBook");
	 	
	 frameApp.setLocationRelativeTo(null);
	 frameApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frameApp.setLayout(new GridBagLayout());
	 
	 JPanel panelApp = new JPanel();
	 panelApp.setFocusCycleRoot(true);
	 panelApp.grabFocus();
	 panelApp.setBorder(BorderFactory.createTitledBorder(
	         BorderFactory.createEtchedBorder(), "Application panel", TitledBorder.LEFT, TitledBorder.TOP));
	 
	 JPanel panelView = new JPanel(); 
	 panelView.setFocusCycleRoot(true);
	 panelView.grabFocus();
	
	 panelView.setBorder(BorderFactory.createTitledBorder(
	         BorderFactory.createEtchedBorder(), "View Content Table", TitledBorder.LEFT, TitledBorder.TOP));
	    
	    final JPopupMenu popupMenu = new JPopupMenu();
	    JTable table = tabelView(stmt);
	    table.setComponentPopupMenu(popupMenu);
	    
	    popupMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                        if (rowAtPoint > -1) {
                            table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub
            }
        });
     
     	JScrollPane scrollPane = new JScrollPane(table);
     	table.setFillsViewportHeight(true);
    	panelView.add(scrollPane);
    	
    	JMenuItem deleteItem = new JMenuItem("Delete");
	    popupMenu.add(deleteItem);
	    
	    deleteItem.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent ae) {
				
				int rowIndex = table.getSelectedRow();
				System.out.println("Delete in table pos # -> " + rowIndex);
				String strFirstname = table.getValueAt (rowIndex, 0).toString();
				String strLastname = table.getValueAt (rowIndex, 1).toString();
				String strDatabirthday = table.getValueAt (rowIndex, 2).toString();
				String strPhonenumber = table.getValueAt (rowIndex, 3).toString();
				
				JFrame frameDeleteOk = new JFrame("Delete contact information for " + strFirstname + " " + strLastname);
		   	    frameDeleteOk.setLocationRelativeTo(null);
		   	    frameDeleteOk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   	    frameDeleteOk.setSize(400, 600);
		   	    frameDeleteOk.setLayout(new GridBagLayout());
		   	    
		   	    JPanel panelDeleteOk = new JPanel();
		   	    panelDeleteOk.setFocusCycleRoot(true);
		   	    panelDeleteOk.grabFocus();
		   	            
		   	    JLabel DeleteOkLabel = new JLabel();
		   	    DeleteOkLabel.setText("Are you sure delete contact " + strFirstname + " " + strLastname + "?");
		   	    DeleteOkLabel.setPreferredSize(new Dimension(450, 50));
		   	    DeleteOkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		   	   
		   	    JButton buttonDeleteOk = new JButton("Delete");
		   	    buttonDeleteOk.setPreferredSize(new Dimension(50, 20));
		   	    buttonDeleteOk.addActionListener(new ActionListener(){
		   			public void actionPerformed(ActionEvent ae){
		   				Connection conn = dbConection();
		   			    Statement stmt = dbStatement(conn);
		   			    	
		   				String sqlu = "USE " + DB;
			   			String sqlOkDelete = "DELETE FROM phonebook WHERE firstname = '" + strFirstname + "' " 
					    		+ "and  lastname = '" + strLastname + "' " 
					    		+ "and  databirthday = '" + strDatabirthday + "' "
					    		+ "and  phonenumber = '" + strPhonenumber + "'; ";
			   			System.out.println(sqlOkDelete);
			   			try {
							stmt.executeUpdate(sqlu);
							stmt.execute(sqlOkDelete);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   			tabelRefresh(popupMenu, panelView, frameApp);
			   			
			   			tabelView(stmt).setComponentPopupMenu(popupMenu);
		   				frameDeleteOk.dispose();
		   			}
		   			});
		   		
		   	JButton buttonCloseDelete = new JButton("Cancel");
		   	buttonCloseDelete.setPreferredSize(new Dimension(50, 20));
		   	buttonCloseDelete.addActionListener(new ActionListener(){
		   		public void actionPerformed(ActionEvent ae){
		   				frameDeleteOk.dispose();	
		   		}});
		   		
		   	frameDeleteOk.add(DeleteOkLabel, new GridBagConstraints(0,0,1,1,0,1,
		   	    		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
		   	    		new Insets(2,2,2,2),0,0));
	   	    frameDeleteOk.add(buttonDeleteOk, new GridBagConstraints(0,1,1,1,0,1,
		   	    		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
		   	    		new Insets(2,2,2,2),0,0));
		   	frameDeleteOk.add(buttonCloseDelete, new GridBagConstraints(0,2,1,1,0,1,
				   	    GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				   	    new Insets(2,2,2,2),0,0));
		   	    		
		   	frameDeleteOk.setVisible(true);
		   	frameDeleteOk.pack();
			}
		});
	    
	    JMenuItem editItem = new JMenuItem("Edit");
	    popupMenu.add(editItem);
	    editItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				int rowIndex = table.getSelectedRow();
				System.out.println("Edit in table pos # -> " + rowIndex);
				String strFirstname = table.getValueAt (rowIndex, 0).toString();
				String strLastname = table.getValueAt (rowIndex, 1).toString();
				String strDatabirthday = table.getValueAt (rowIndex, 2).toString();
				String strPhonenumber = table.getValueAt (rowIndex, 3).toString();
				String strNote = table.getValueAt (rowIndex, 4).toString();
				
				JFrame frameEdit = new JFrame("Edit record");
				frameEdit.setLocationRelativeTo(null);
				frameEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frameEdit.setSize(400, 600);
				frameEdit.setLayout(new GridBagLayout());
				
				JPanel panelEdit = new JPanel(); 
			 	panelEdit.setFocusCycleRoot(true);
			 	panelEdit.grabFocus();
			 	panelEdit.setBorder(BorderFactory.createTitledBorder(
				         BorderFactory.createEtchedBorder(), "Add New Contact", TitledBorder.LEFT, TitledBorder.TOP));
			           
			    JLabel firstnameLabel = new JLabel();
			    firstnameLabel.setText("*First Name:");
			    JFormattedTextField firstnameField = new JFormattedTextField();
			    firstnameField.setPreferredSize(new Dimension(200, 20));
			    firstnameField.setText(strFirstname);
			    
			    JLabel lastnameLabel = new JLabel();
			    lastnameLabel.setText("*Last Name:");
			    JFormattedTextField lastnameField = new JFormattedTextField();
			    lastnameField.setPreferredSize(new Dimension(200, 20));
			    lastnameField.setText(strLastname);
			    
			    JLabel databirthdayLabel = new JLabel();
			    databirthdayLabel.setText("*Birthday Date:");
			    JFormattedTextField databirthdayField = new JFormattedTextField(createFormatter("####-##-##"));
			    databirthdayField.setPreferredSize(new Dimension(200, 20));
			    databirthdayField.setText(strDatabirthday);
			    
			    JLabel phonenumberLabel = new JLabel();
			    phonenumberLabel.setText("*Phone Number:");
			    JFormattedTextField phonenumberField = new JFormattedTextField(createFormatter("(+###) ###-###-##"));
			    phonenumberField.setPreferredSize(new Dimension(200, 20));
			    phonenumberField.setText(strPhonenumber);
			    
			    JLabel noteLabel = new JLabel();
			    noteLabel.setText("Note:");
			    JTextArea noteArea = new JTextArea();
			    noteArea.setPreferredSize(new Dimension(200, 200));
			    noteArea.setText(strNote);
			    
			    JButton buttonApply = new JButton("Apply");
			    buttonApply.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent ae){
			    		
			    		String strFirstnameUp = firstnameField.getText();
						String strLastnameUp = lastnameField.getText();
						String strDatabirthdayUp = databirthdayField.getText();
						String strPhonenumberUp = phonenumberField.getText();
						String strNoteUp = noteArea.getText();
			    		String updateRow ="UPDATE phonebook "
			    				+ "SET firstname = '"+ strFirstnameUp + "', "
			    				+ "lastname = '"+ strLastnameUp + "', "
			    				+ "databirthday = '"+ strDatabirthdayUp + "', "
			    				+ "phonenumber = '"+ strPhonenumberUp + "', "
			    				+ "note = '"+ strNoteUp + "' "
			    				+ "where firstname = '"+ strFirstname + "' "
			    				+ "and lastname = '"+ strLastname + "' "
			    				+ "and databirthday = '"+ strDatabirthday + "' "
			    				+ "and phonenumber = '"+ strPhonenumber + "' "
			    				+ "and note = '"+ strNote + "' ";
			    		
				    	System.out.println(updateRow);
				    	String sqlu = "USE " + DB;
				    	Connection conn = dbConection();
		   			    Statement stmt = dbStatement(conn);
			    	    try {
							stmt.executeUpdate(sqlu);
							stmt.executeUpdate(updateRow);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	    
			    	    JFrame frameOk = new JFrame("MyPhoneBook");
		    	    	frameOk.setLocationRelativeTo(null);
		    	    	frameOk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	    	frameOk.setSize(400, 600);
		    	    	frameOk.setLayout(new GridBagLayout());
		    	    	JPanel panelOk = new JPanel(); 
				   	    panelOk.setFocusCycleRoot(true);
				   	    panelOk.grabFocus();
				   	            
				   	    JLabel OkLabel = new JLabel();
				   	    OkLabel.setText("Successfully update record for >>" + strFirstnameUp + " " + strLastnameUp + "!");
				   	    OkLabel.setPreferredSize(new Dimension(300, 20));
				   	    
				   	    	JButton buttonOk = new JButton("Ok");
				   	    	buttonOk.setPreferredSize(new Dimension(50, 20));
				   	    	buttonOk.addActionListener(new ActionListener(){
				   	    		public void actionPerformed(ActionEvent ae){
				   	    			tabelRefresh(popupMenu, panelView, frameApp);
				   	    			table.setComponentPopupMenu(popupMenu);
				   	    			frameOk.dispose();
				   	    		}
				   	    	});
				   	    	
				   	    panelOk.add(OkLabel);
					   	panelOk.add(buttonOk);
					   	
					   	frameOk.add(OkLabel, new GridBagConstraints(0,0,1,1,0,1,
				   	    		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				   	    		new Insets(2,2,2,2),0,0));
				   	    frameOk.add(buttonOk, new GridBagConstraints(0,1,1,1,0,1,
				   	    		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				   	    		new Insets(2,2,2,2),0,0));
				   	    frameOk.setVisible(true);
				   	    frameOk.pack();
				   	    
				frameEdit.dispose();
			    	}
			    });
			    
			    JButton buttonClearFields = new JButton("Clear Fields");
				buttonClearFields.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						firstnameField.setText(null);
						lastnameField.setText(null);
						databirthdayField.setText(null);
						phonenumberField.setText(null);
						noteArea.setText(null);
					}
				});
				
				panelEdit.add(firstnameLabel);
				panelEdit.add(lastnameLabel);
				panelEdit.add(databirthdayLabel);
				panelEdit.add(phonenumberLabel);
				panelEdit.add(noteLabel);
				panelEdit.add(firstnameField);
				panelEdit.add(lastnameField);
				panelEdit.add(databirthdayField);
				panelEdit.add(phonenumberField);
				panelEdit.add(noteArea);
				panelEdit.add(buttonApply);
				panelEdit.add(buttonClearFields);

				frameEdit.add(firstnameLabel, new GridBagConstraints(0,0,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameEdit.add(firstnameField, new GridBagConstraints(1,0,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameEdit.add(lastnameLabel, new GridBagConstraints(0,1,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameEdit.add(lastnameField, new GridBagConstraints(1,1,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameEdit.add(databirthdayLabel, new GridBagConstraints(0,2,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameEdit.add(databirthdayField, new GridBagConstraints(1,2,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameEdit.add(phonenumberLabel, new GridBagConstraints(0,3,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameEdit.add(phonenumberField, new GridBagConstraints(1,3,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameEdit.add(noteLabel, new GridBagConstraints(0,4,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameEdit.add(noteArea, new GridBagConstraints(1,4,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameEdit.add(buttonApply, new GridBagConstraints(0,5,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameEdit.add(buttonClearFields, new GridBagConstraints(1,5,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameEdit.setVisible(true);
				frameEdit.pack();
	    	}
	    });
    	
    	JMenuItem refreshItem = new JMenuItem("Refresh");
	    popupMenu.add(refreshItem);
	    refreshItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tabelRefresh(popupMenu, panelView, frameApp);
			}
		});
	    
	 JLabel labelView = new JLabel();
	 labelView.setText("List of Contacts:");
	    
	 JButton buttonAddNewContact = new JButton("New Contact");
	 buttonAddNewContact.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent ae){
			 JFrame frameAdd = new JFrame("Add new record");
			 frameAdd.setLocationRelativeTo(null);
			 frameAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 frameAdd.setSize(400, 600);
			 frameAdd.setLayout(new GridBagLayout());
			 
			 	JPanel panelAdd = new JPanel(); 
			 	panelAdd.setFocusCycleRoot(true);
			 	panelAdd.grabFocus();
			 	panelAdd.setBorder(BorderFactory.createTitledBorder(
				         BorderFactory.createEtchedBorder(), "Add New Contact", TitledBorder.LEFT, TitledBorder.TOP));
			           
			    JLabel firstnameLabel = new JLabel();
			    firstnameLabel.setText("*First Name:");
			    JFormattedTextField firstnameField = new JFormattedTextField();
			    firstnameField.setPreferredSize(new Dimension(200, 20));
			    
			    JLabel lastnameLabel = new JLabel();
			    lastnameLabel.setText("*Last Name:");
			    JFormattedTextField lastnameField = new JFormattedTextField();
			    lastnameField.setPreferredSize(new Dimension(200, 20));
			    
			    JLabel databirthdayLabel = new JLabel();
			    databirthdayLabel.setText("*Birthday Date:");
			    JFormattedTextField databirthdayField = new JFormattedTextField(createFormatter("####-##-##"));
			    databirthdayField.setPreferredSize(new Dimension(200, 20));
			    
			    JLabel phonenumberLabel = new JLabel();
			    phonenumberLabel.setText("*Phone Number:");
			    JFormattedTextField phonenumberField = new JFormattedTextField(createFormatter("(+###) ###-###-##"));
			    phonenumberField.setPreferredSize(new Dimension(200, 20));
			    
			    JLabel noteLabel = new JLabel();
			    noteLabel.setText("Note:");
			    JTextArea noteArea = new JTextArea();
			    noteArea.setPreferredSize(new Dimension(200, 200));
			    
			    JButton buttonAdd = new JButton("Add");
			    buttonAdd.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent ae){
			    		String insertRow ="INSERT phonebook(firstname, lastname, databirthday, phonenumber, note) VALUES (" + "'" + firstnameField.getText() + "', " 
				    			+ "'" + lastnameField.getText() + "', "
				    			+ "'" + databirthdayField.getText() + "', " +  "'" + phonenumberField.getText() +  "', " + "'" + noteArea.getText() +  "')";
				    	System.out.println(insertRow);
				    	firstnameField.setText(null);
				    	lastnameField.setText(null);
				    	databirthdayField.setText(null);
				    	phonenumberField.setText(null);
				    	noteArea.setText(null);
				    	String sqlu = "USE " + DB;
				    	Connection conn = dbConection();
		   			    Statement stmt = dbStatement(conn);
			    	    try {
							stmt.executeUpdate(sqlu);
							stmt.executeUpdate(insertRow);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	    	JFrame frameOk = new JFrame("MyPhoneBook");
			    	    	frameOk.setLocationRelativeTo(null);
			    	    	frameOk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    	    	frameOk.setSize(400, 600);
			    	    	frameOk.setLayout(new GridBagLayout());
			    	    	JPanel panelOk = new JPanel(); 
					   	    panelOk.setFocusCycleRoot(true);
					   	    panelOk.grabFocus();
					   	            
					   	    JLabel OkLabel = new JLabel();
					   	    OkLabel.setText("Successfully add record!");
					   	    OkLabel.setPreferredSize(new Dimension(200, 20));
					   	    
					   	    	JButton buttonOk = new JButton("Ok");
					   	    	buttonOk.setPreferredSize(new Dimension(50, 20));
					   	    	buttonOk.addActionListener(new ActionListener(){
					   	    		public void actionPerformed(ActionEvent ae){
					   	    			tabelRefresh(popupMenu, panelView, frameApp);
					   	    			frameOk.dispose();
					   	    		}
					   	    	});
					   	    panelOk.add(OkLabel);
						   	panelOk.add(buttonOk);
						   	
						   	frameOk.add(OkLabel, new GridBagConstraints(0,0,1,1,0,1,
					   	    		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
					   	    		new Insets(2,2,2,2),0,0));
					   	    frameOk.add(buttonOk, new GridBagConstraints(0,1,1,1,0,1,
					   	    		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
					   	    		new Insets(2,2,2,2),0,0));
					   	    frameOk.setVisible(true);
					   	    frameOk.pack();
					   	    
				frameAdd.dispose();
			    }
			    });
			    
			    JButton buttonClearFields = new JButton("Clear Fields");
				buttonClearFields.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						firstnameField.setText(null);
						lastnameField.setText(null);
						databirthdayField.setText(null);
						phonenumberField.setText(null);
						noteArea.setText(null);
					}
				});
				
				panelAdd.add(firstnameLabel);
				panelAdd.add(lastnameLabel);
				panelAdd.add(databirthdayLabel);
				panelAdd.add(phonenumberLabel);
				panelAdd.add(noteLabel);
				panelAdd.add(firstnameField);
				panelAdd.add(lastnameField);
				panelAdd.add(databirthdayField);
				panelAdd.add(phonenumberField);
				panelAdd.add(noteArea);
				panelAdd.add(buttonAdd);
				panelAdd.add(buttonClearFields);

				frameAdd.add(firstnameLabel, new GridBagConstraints(0,0,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameAdd.add(firstnameField, new GridBagConstraints(1,0,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameAdd.add(lastnameLabel, new GridBagConstraints(0,1,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameAdd.add(lastnameField, new GridBagConstraints(1,1,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameAdd.add(databirthdayLabel, new GridBagConstraints(0,2,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameAdd.add(databirthdayField, new GridBagConstraints(1,2,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameAdd.add(phonenumberLabel, new GridBagConstraints(0,3,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameAdd.add(phonenumberField, new GridBagConstraints(1,3,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameAdd.add(noteLabel, new GridBagConstraints(0,4,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameAdd.add(noteArea, new GridBagConstraints(1,4,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameAdd.add(buttonAdd, new GridBagConstraints(0,5,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
				frameAdd.add(buttonClearFields, new GridBagConstraints(1,5,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));

				frameAdd.setVisible(true);
				frameAdd.pack();
		 }
		 });
	 
	 JButton buttonSearch = new JButton("Search");
	 buttonSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				JFrame frameFind = new JFrame("Find contact");
			 	frameFind.setLocationRelativeTo(null);
			 	frameFind.setLayout(new GridBagLayout());
			 	frameFind.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 	
			    JPanel panelFind = new JPanel();
			    panelFind.setFocusCycleRoot(true);
			    panelFind.grabFocus();
			            
			    JLabel labelFind = new JLabel();
			    labelFind.setText("Find contact");
			    JLabel labelFindRez = new JLabel();
			    labelFindRez.setText("Search Results");
			    
			    JLabel firstnameLabelFind = new JLabel();
			    firstnameLabelFind.setText("First Name:");
			    JFormattedTextField firstnameFieldFind = new JFormattedTextField();
			    firstnameFieldFind.setPreferredSize(new Dimension(200, 20));
			    
			    JLabel lastnameLabelFind = new JLabel();
			    lastnameLabelFind.setText("Last Name:");
			    JFormattedTextField lastnameFieldFind = new JFormattedTextField();
			    lastnameFieldFind.setPreferredSize(new Dimension(200, 20));
			    
			    JLabel databirthdayLabelFind = new JLabel();
			    databirthdayLabelFind.setText("Birthday Date:");
			    JFormattedTextField databirthdayFieldFind = new JFormattedTextField(createFormatter("####-##-##"));
			    databirthdayFieldFind.setPreferredSize(new Dimension(200, 20));
			    
			    JLabel phonenumberLabelFind = new JLabel();
			    phonenumberLabelFind.setText("Phone Number:");
			    JFormattedTextField phonenumberFieldFind = new JFormattedTextField(createFormatter("(+###) ###-###-##"));
			    phonenumberFieldFind.setPreferredSize(new Dimension(200, 20));
			    
			    JButton buttonClearFields = new JButton("Clear Fields");
				buttonClearFields.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						firstnameFieldFind.setText(null);
						lastnameFieldFind.setText(null);
						databirthdayFieldFind.setText(null);
						phonenumberFieldFind.setText(null);
					}
				});     
	    	    
	    	    JButton buttonFind = new JButton("Find");
	    	    buttonFind.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						
						JFrame frameFindRez = new JFrame("Search results");
					 	frameFindRez.setLocationRelativeTo(null);
					 	frameFindRez.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					 	
					    JPanel panelView = new JPanel();
					    panelView.setFocusCycleRoot(true);
					    panelView.grabFocus();
						
						String[] columnNames = new String[] { "First Name", "Last Name", "Birthday Data", "Phone Number", "Note" };
		                
		                DefaultTableModel tableModel = new DefaultTableModel();
		                JTable table = new JTable(tableModel);
		                table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
						
						Connection connFind = null;
			    	 	Statement stmtFind = null;
			    	 	System.out.println("Connecting database...");
			    	 	try {
							connFind = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	    System.out.println("Connected database successfully...");
			    	    
			    	    try {
							stmtFind = connFind.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
			    	    String sqlu = "USE " + DB;
			    	    String sqlfind = "SELECT firstname, lastname, databirthday, phonenumber, note FROM phonebook "
			    	    		+ "WHERE (firstname LIKE " + "'" + firstnameFieldFind.getText() + "'" + ") " + 
			    	    			"OR  (lastname LIKE " + "'" + lastnameFieldFind.getText() + "'" + ") " + 
			    	    				" OR  (databirthday LIKE " + "'" + databirthdayFieldFind.getText() + "'" + ") " + 
			    	    					" OR  (phonenumber LIKE " + "'" + phonenumberFieldFind.getText() + "'" + ")";
			    	    System.out.println(sqlfind);
			    	    try {
			    	    	stmtFind.executeUpdate(sqlu);
			    	    	stmtFind.execute(sqlfind);
			    	    	
							final ResultSet rec = stmtFind.getResultSet();
							
							ResultSetMetaData metaData = rec.getMetaData();
							if (rec.next()) {
								rec.previous();
								int ColumnSize = metaData.getColumnCount();
								System.out.println("Numarul de cimpuri in tabela phonebook = " + ColumnSize);
								ArrayList<String[]> rows = new ArrayList<>(); 
								
								while(rec.next()){
									String[] currentRow = new String[ColumnSize];
									for(int i = 0; i < ColumnSize; i++){
										currentRow[i] = rec.getString(i+1);
									}
									rows.add(currentRow);
								}
								
								String[][] rowsArray = new String[rows.size()][ColumnSize];
								rowsArray = rows.toArray(rowsArray);
								table = new JTable(rowsArray, columnNames);
								frameFindRez.getContentPane().add(new JScrollPane(table), "Center"); 
							 	frameFindRez.setSize(600, 400); 
							 	frameFindRez.setVisible(true);
							} else 
							{
								JFrame frameOk = new JFrame("Search results");
						   	    frameOk.setLocationRelativeTo(null);
						   	    frameOk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						   	    frameOk.setSize(400, 600);
						   	    frameOk.setLayout(new GridBagLayout());
						   	    
						   	    JPanel panelOk = new JPanel(); 
						   	    panelOk.setFocusCycleRoot(true);
						   	    panelOk.grabFocus();
						   	            
						   	    JLabel OkLabel = new JLabel();
						   	    OkLabel.setText("No items match your search!");
						   	    OkLabel.setPreferredSize(new Dimension(250, 50));
						   	    OkLabel.setHorizontalAlignment(SwingConstants.CENTER);
						   	    JButton buttonClose = new JButton("Close");
						   	    buttonClose.setPreferredSize(new Dimension(50, 20));
						   	    buttonClose.addActionListener(new ActionListener(){
						   			public void actionPerformed(ActionEvent ae){
						   				frameOk.dispose();
						   	}
						   	});   
						   	    panelOk.add(OkLabel);
						   	    panelOk.add(buttonClose);
						   	    frameFindRez.dispose();
						   	    
						   	    		frameOk.add(OkLabel, new GridBagConstraints(0,0,1,1,0,1,
						   	    		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
						   	    		new Insets(2,2,2,2),0,0));
						   	    		frameOk.add(buttonClose, new GridBagConstraints(0,1,1,1,0,1,
						   	    		GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
						   	    		new Insets(2,2,2,2),0,0));
						   	    
						   	    frameOk.setVisible(true);
						   	    frameOk.pack();
				    	    	}
						    try{
					    		if(stmtFind!=null)
					    			connFind.close();
					    	}catch(SQLException se){
					    	}// do nothing
					    	try{
					    		if(connFind!=null)
					    			connFind.close();
					    	}catch(SQLException se){
					    		se.printStackTrace();
					    	}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});  
	    	    
	    	    JButton buttonClose = new JButton("Close");
	    	    buttonClose.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						frameFind.dispose();
					}
				}); 
	    	    
	    	    panelFind.add(labelFind);
	    	    panelFind.add(buttonClearFields);
	    	    panelFind.add(firstnameLabelFind);
	    	    panelFind.add(firstnameFieldFind);
	    	    panelFind.add(lastnameLabelFind);
	    	    panelFind.add(lastnameFieldFind);
	    	    panelFind.add(databirthdayLabelFind);
	    	    panelFind.add(databirthdayFieldFind);
	    	    panelFind.add(phonenumberLabelFind);
	    	    panelFind.add(phonenumberFieldFind);
	    	    panelFind.add(buttonFind);
	    	    panelFind.add(labelFindRez);
	    	    
	    	    panelFind.add(buttonClose);
	    	    
	    	    frameFind.add(labelFind, new GridBagConstraints(1,0,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    	    frameFind.add(buttonClearFields, new GridBagConstraints(0,5,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    	    
	    	    frameFind.add(firstnameLabelFind, new GridBagConstraints(0,1,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    		frameFind.add(firstnameFieldFind, new GridBagConstraints(1,1,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    		
	    		frameFind.add(lastnameLabelFind, new GridBagConstraints(0,2,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    		frameFind.add(lastnameFieldFind, new GridBagConstraints(1,2,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    		
	    		frameFind.add(databirthdayLabelFind, new GridBagConstraints(0,3,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    		frameFind.add(databirthdayFieldFind, new GridBagConstraints(1,3,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    		
	    		frameFind.add(phonenumberLabelFind, new GridBagConstraints(0,4,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    		frameFind.add(phonenumberFieldFind, new GridBagConstraints(1,4,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    		
	    		frameFind.add(buttonFind, new GridBagConstraints(1,5,1,1,0,1,
	    				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
	    				new Insets(2,2,2,2),0,0));
	    		
	    		frameFind.setVisible(true);
				frameFind.pack();
			}
		});
	 
	 
	 JButton buttonRefresh = new JButton("Refresh");
	 
	 buttonRefresh.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae){
    			tabelRefresh(popupMenu, panelView, frameApp);
    		}
    	});
	 
	 panelApp.add(buttonAddNewContact);
	 panelApp.add(buttonSearch);
	 panelApp.add(buttonRefresh);
	 
	 frameApp.add(panelApp, new GridBagConstraints(0,0,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
	 frameApp.add(panelView, new GridBagConstraints(0,1,1,1,0,1,
				GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
				new Insets(2,2,2,2),0,0));
	 
	 dbClose(stmt,conn);
	 frameApp.setVisible(true);
	 frameApp.pack();
 }
 
 	private static Connection dbConection() {
		Connection connView = null;
	 	System.out.println("Connecting database...");
	 	try {
			connView = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
			System.out.println("Connected database successfully...");
			return connView;
	 		} 
	 	catch (SQLException e) {
	 			e.printStackTrace();
	 			System.out.println("Connection failed...");
	 			return null;
	 			}
	}
	
	private static Connection dbClose(Statement stmtView, Connection connView) {
	
	try{
		if(stmtView!=null)
			connView.close();
	}catch(SQLException se){
	}
	try{
		if(connView!=null)
			connView.close();
	}catch(SQLException se){
		se.printStackTrace();
	}
	return null;
	}
	
	private static Statement dbStatement(Connection connView) {
	 	Statement stmtView = null;
	    try {
			stmtView = connView.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println("Statement create...");
			return stmtView;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Created Statement failed...");
			return null;
		}
	}
	
	private static void dbCreate(Statement stmt) {
		try {
	    	
	    	String sql = "CREATE DATABASE IF NOT EXISTS " + DB;
	    	stmt.executeUpdate(sql);
	    	
	    	String sqlu = "USE " + DB;
	    	stmt.executeUpdate(sqlu);
	    	
	    	String sqlt = "CREATE TABLE IF NOT EXISTS phonebook(" +
 				"id BIGINT AUTO_INCREMENT," +
 				"firstname VARCHAR(255) NOT NULL," +
 				"lastname VARCHAR(255) NOT NULL," +
 				"databirthday DATE NOT NULL," +
 				"phonenumber VARCHAR(255) NOT NULL,"+
 				"note VARCHAR(255)," +
 				"PRIMARY KEY ( id ))";
	    	
			stmt.executeUpdate(sqlt);
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	private static void tabelRefresh(JPopupMenu popupMenu, JPanel panelView, JFrame frameApp) {
		
	Connection conn = dbConection();
	Statement stmt = dbStatement(conn);
	
	JTable table = tabelView(stmt);
	table.setComponentPopupMenu(popupMenu);
	panelView.removeAll();
	JScrollPane scrollPane = new JScrollPane(table);
	panelView.add(scrollPane);
	
	frameApp.remove(panelView);
	frameApp.add(panelView, new GridBagConstraints(0,1,1,1,0,1,
			GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,
			new Insets(2,2,2,2),0,0));
	frameApp.revalidate();
	frameApp.setSize(700, 500);
	frameApp.pack();
	}
	
	private static JTable tabelView(Statement stmt) {
		
		String[] columnNames = new String[] { "First Name", "Last Name", "Birthday Data", "Phone Number", "Note" };
	     
	    DefaultTableModel tableModel = new DefaultTableModel();
	    JTable table = new JTable(tableModel);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
	    
	    String sqlu = "USE " + DB;
		String sqlfind = "SELECT firstname, lastname, databirthday, phonenumber, note FROM phonebook ";
		    
	    try {
	    		stmt.executeUpdate(sqlu);
				stmt.execute(sqlfind);
		} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
	     	
	    try {
			final ResultSet rec = stmt.getResultSet();
			ResultSetMetaData metaData = rec.getMetaData();
	        int ColumnSize = metaData.getColumnCount();
	        
				ArrayList<String[]> rows = new ArrayList<>(); 
			   
				while(rec.next()){
			        String[] currentRow = new String[ColumnSize];
			        for(int i = 0; i < ColumnSize; i++){
			            currentRow[i] = rec.getString(i+1);
			        }
			        rows.add(currentRow);
			    }
			    
			    String[][] rowsArray = new String[rows.size()][ColumnSize];
			    rowsArray = rows.toArray(rowsArray);
			    table = new JTable(rowsArray, columnNames);
			    return table;   
			    
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
			
	}
	
	protected static MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
}


