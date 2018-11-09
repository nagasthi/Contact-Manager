package contactmanager;

// Haan Mo Johng
// 11.11.2016
// 


import javax.swing.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ContactManager extends JFrame{
    
	
	 JPanel MainPanel ;
	 JPanel SecondMainPanel ;
	 JPanel ThirdMainPanel;
	 JPanel FourthMainPanel;
	 JPanel FifthMainPanel;
	 JPanel SixthMainPanel;
	
	 
	JLabel FirstName;
	JTextField FirstNameText;
	JLabel Contact_id;
	JTextField Contact_id_text;
	
	JLabel addresslabelinfo;
    JLabel MiddleInitial;
    JTextField MiddleInitialText;
	JLabel LastName;
	JTextField LastNameText;
	JLabel Address;
	JTextField AddressText ;
	JLabel Sex;
	JTextField SexText;
	JComboBox MorF;
	JLabel EmailId;
	JTextField EmailIdText;
	JLabel PhoneNo;
	JTextField PhoneNoText;
	JLabel Bday;
	JTextField BdayText;
	JLabel FirstNameList;
	JLabel MiddleNameList;
	JLabel LastNameList;
	JLabel SSNList;
	JLabel SexList;
	JLabel BdayList;
	JButton Search;
	JButton Save;
	JButton NewEmployee;
	JButton Delete;
	JButton Update;
	
	JTable table;
	
	
	
	String[] mORf = {"M", "F"};	
	
	ContactManager()
	{
		super();
		this.setTitle("Contact Manager");
		Box box = Box.createVerticalBox(); 
		
		
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setVgap(4);
        
        Container contain = this.getContentPane();
        contain.setLayout(flowLayout);
        
		MainPanel = new JPanel();
		MainPanel.setLayout(flowLayout);
		box.add(MainPanel);
		
		SecondMainPanel = new JPanel();
		SecondMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		box.add(SecondMainPanel);
		

  
		ThirdMainPanel = new JPanel();
		ThirdMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		box.add(ThirdMainPanel);
		
		box.add(Box.createVerticalStrut(10));
		    
		FifthMainPanel = new JPanel();
		FifthMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		addresslabelinfo = new JLabel("Add street,city,state,zipcode,country in same order seperated by commas");
		FifthMainPanel.add(addresslabelinfo);
		addresslabelinfo.setVisible(false);
		addresslabelinfo.setForeground(Color.red);
		box.add(FifthMainPanel);
		
		
		FirstName = new JLabel("First Name");
	    FirstName.setFont(new Font("Monosapace",13, 13));
		FirstNameText = new JTextField(8);
		
		Contact_id = new JLabel("Contact_id");
		Contact_id_text = new JTextField(8);
		
		MiddleInitial = new JLabel("M.I.");
		MiddleInitial.setFont(new Font("Monosapace",13, 13));
		MiddleInitialText = new JTextField(2);
		
		LastName = new JLabel("Last Name");
		LastName.setFont(new Font("Monosapace",13, 13));
		LastNameText = new JTextField(8);
		
		Address = new JLabel("Address");
		Address.setFont(new Font("Monosapace",13, 13));
		AddressText= new JTextField(15);
		
		Sex = new JLabel("Sex");
		Sex.setFont(new Font("Monosapace",13, 13));
		MorF = new JComboBox(mORf);
		
		EmailId = new JLabel("Email Id");
		EmailId.setFont(new Font("Monosapace",13, 13));
		EmailIdText= new JTextField(10);
		
		PhoneNo = new JLabel("Phone Number");
		PhoneNo.setFont(new Font("Monosapace",13, 13));
		PhoneNoText= new JTextField(10);
		
		Bday = new JLabel("Date of Birth");
		Bday.setFont(new Font("Monosapace",13, 13));
		BdayText= new JTextField(10);
				
		Search = new JButton("Search");
        Save = new JButton("Save");
        Update = new JButton("Update");
        NewEmployee = new JButton("New Employee");
        Delete = new JButton("Delete");
    	
		MainPanel.add(FirstName);
		MainPanel.add(FirstNameText);
		
		MainPanel.add(Box.createHorizontalStrut(10));
		
		MainPanel.add(MiddleInitial);
		MainPanel.add(MiddleInitialText);
		
		MainPanel.add(Box.createHorizontalStrut(10));
		
		MainPanel.add(LastName);
		MainPanel.add(LastNameText);
		
		MainPanel.add(Box.createHorizontalStrut(10));
		
	    SecondMainPanel.add(Address);
	    SecondMainPanel.add(AddressText);
	    
	    SecondMainPanel.add(Box.createHorizontalStrut(10));
	    
	    SecondMainPanel.add(Sex);
	    SecondMainPanel.add(MorF);
	    
	    SecondMainPanel.add(Box.createHorizontalStrut(10));
	    
	    SecondMainPanel.add(EmailId);
	    SecondMainPanel.add(EmailIdText);
		
	    ThirdMainPanel.add(PhoneNo);
		ThirdMainPanel.add(PhoneNoText);
		ThirdMainPanel.add(Search);
		
		ThirdMainPanel.add(Box.createHorizontalStrut(10));
		
		ThirdMainPanel.add(Bday);
		ThirdMainPanel.add(BdayText);
		
		FifthMainPanel.add(Save);
		FifthMainPanel.add(NewEmployee);
		FifthMainPanel.add(Delete);
		FifthMainPanel.add(Update);
		
		this.add(box, BorderLayout.CENTER);
		this.setSize(750, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void allsearch()
	{
		
		BusinessObject bo=new BusinessObject();
		 bo.fname = FirstNameText.getText();
	     // System.out.println(bo.fname);
	      bo.mi= MiddleInitialText.getText();
	      bo.lname= LastNameText.getText();
	      bo.address= AddressText.getText();
	      bo.sex=(String) (MorF.getSelectedItem());
	      bo.email= EmailIdText.getText();
	      bo.phnno= PhoneNoText.getText();
	      bo.bday= BdayText.getText();
	      
		NewEmployee.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				FirstNameText.setText("");
				MiddleInitialText.setText("");
				LastNameText.setText("");
				AddressText.setText("");
				MorF.setSelectedIndex(0);
				EmailIdText.setText("");
				PhoneNoText.setText("");
				BdayText.setText("");
				
			}
		});
		
		Save.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				BusinessObject bo= new BusinessObject();
				
				bo.fname = FirstNameText.getText();
				bo.mi= MiddleInitialText.getText();
				bo.lname= LastNameText.getText();
				bo.address= AddressText.getText();
				bo.sex=(String) (MorF.getSelectedItem());
				bo.email= EmailIdText.getText();
				bo.phnno= PhoneNoText.getText();
				bo.bday= BdayText.getText();
				
				String address = bo.address;
				String parts[] = address.split(",");
				bo.street = parts[0];
				bo.city = parts[1];
				bo.state = parts[2];
				bo.zipcode = parts[3];
				bo.country = parts[4];
				
				Boolean flag = ValidateInputs(bo);
			
				DBTest dbtest=new DBTest();
				try {
					if(flag)
					{
						dbtest.save(bo);
						JOptionPane.showMessageDialog(null, "contact added successfully");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		AddressText.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		    	  System.out.println("in focus");
		        addresslabelinfo.setVisible(true);	   
		        getContentPane().validate();
		      }

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				addresslabelinfo.setVisible(false);
				getContentPane().validate();
			}
		});
		
		Search.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
			      
				   BusinessObject bo= new BusinessObject();
					
					bo.fname = FirstNameText.getText();
					bo.mi= MiddleInitialText.getText();
					bo.lname= LastNameText.getText();
					bo.address= AddressText.getText();
					bo.sex=(String) (MorF.getSelectedItem());
					bo.email= EmailIdText.getText();
					bo.phnno= PhoneNoText.getText();
					bo.bday= BdayText.getText();
				   
					System.out.println("sex"+bo.sex);
					DBTest dbtest = new DBTest();
					
					try {
						ArrayList<BusinessObject> bolist = dbtest.search(bo);
						Object rowdata[][] = new Object[bolist.size()][6];
						Object columnNames[] = {"First Name","Middle Name","Last Name","Sex","Birth Date","Contact Id"};
						int j = 0;
						for(int i=0;i<bolist.size();i++)
						{
							
							BusinessObject bo_in = bolist.get(i);
							rowdata[j][0] = bo_in.fname;
							rowdata[j][1] = bo_in.mi;
							rowdata[j][2] = bo_in.lname;
							rowdata[j][3] = bo_in.sex;
							rowdata[j][4] = bo_in.bday;
							rowdata[j][5] = bo_in.contact_id;
							j= j+1;
						}	
						final JTable jtbl = new JTable(rowdata, columnNames);
						jtbl.getColumnModel().getColumn(5).setMinWidth(0);
						jtbl.getColumnModel().getColumn(5).setMaxWidth(0);
						//System.out.println(jtbl.getValueAt(0, 5));
						JScrollPane jsrlPane = new JScrollPane(jtbl);
						
						//getContentPane().remove(FourthMainPanel);
						getContentPane().add(jsrlPane,BorderLayout.CENTER);
						getContentPane().setVisible(true);
						
						jtbl.addMouseListener(new MouseAdapter() {
							  public void mouseClicked(MouseEvent e) {
								  if(e.getClickCount() == 1)
								  {
									  DBTest dbtest = new DBTest();
									  JTable target = (JTable)e.getSource();
								      int row = target.getSelectedRow();
								      int column = target.getSelectedColumn();
								      BusinessObject bo_inp = new BusinessObject();
								      bo_inp.contact_id = jtbl.getValueAt(row, 5).toString();
								      bo_inp.sex = jtbl.getValueAt(row,3).toString();
								      try {
										ArrayList<BusinessObject> bout = dbtest.search(bo_inp);
										System.out.println("bout size" + bout.size());
										System.out.println("b value "+ bout.get(0).contact_id);
										for(int i=0;i<bout.size();i++)
										{
											BusinessObject bo_print = bout.get(i);
											String Address =bo_print.street;
											Address+=",";
											Address+=bo_print.city;
											Address+=",";
											Address+=bo_print.state;
											Address+=",";
											Address+=bo_print.zipcode;
											Address+=",";
											Address+=bo_print.country;
					
											System.out.println(Address);
											
											if(bo_inp.contact_id.equals(bo_print.contact_id))
											{
												System.out.println("in click");
												System.out.println(bo_print.phnno);
												FirstNameText.setText(bo_print.fname);
												MiddleInitialText.setText(bo_print.mi);
												LastNameText.setText(bo_print.lname);
												AddressText.setText(Address);
												MorF.setSelectedItem(bo_print.sex);
												EmailIdText.setText(bo_print.email);
												PhoneNoText.setText(bo_print.phnno);
												Contact_id_text.setText(bo_print.contact_id);
												Contact_id_text.setVisible(false);
												BdayText.setText(bo_print.bday);
												break;
											}
											
										}
									} catch (ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								  }
							    if (e.getClickCount() == 2) {
							      JTable target = (JTable)e.getSource();
							      int row = target.getSelectedRow();
							      int column = target.getSelectedColumn();
							    }
							  }
							});
						
					    getContentPane().validate();
					    getContentPane().repaint();
					
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}   
				   
			   }
			});	
	
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BusinessObject bo= new BusinessObject();
				
				bo.contact_id = Contact_id_text.getText();
				
				DBTest dbtest = new DBTest();
				
				try {
					dbtest.DeleteRecords(bo);			
					JOptionPane.showMessageDialog(null,"Contact deleted succesfully");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	Update.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			BusinessObject bo= new BusinessObject();
			
			bo.fname = FirstNameText.getText();
			bo.mi= MiddleInitialText.getText();
			bo.lname= LastNameText.getText();
			bo.address= AddressText.getText();
			bo.sex=(String) (MorF.getSelectedItem());
			bo.email= EmailIdText.getText();
			bo.phnno= PhoneNoText.getText();
			bo.bday= BdayText.getText();
			
			bo.contact_id = Contact_id_text.getText();
			Boolean flag = ValidateInputs(bo);
			
			DBTest dbtest = new DBTest();
			
			try {
				if(flag)
				{
				dbtest.UpdateRecords(bo);
				
				JOptionPane.showMessageDialog(null,"Contact Updated succesfully");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	}
	
	public Boolean ValidateInputs(BusinessObject bo)
	{
		Boolean flag = true;
		if(bo.phnno.length() != 10)
		{
			JOptionPane.showMessageDialog(null,"Phone number should be of length 10");
			flag = false;
		}
	    
		
		try{
		    int yourNumber = Integer.parseInt(bo.phnno);
		}catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null,"Phone number should be Integers only");
			flag = false;
		}
		
		
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		Date bday;
		try {
			bday = sdf.parse(bo.bday);
			if(bday.compareTo(today) > 0)
			{
				JOptionPane.showMessageDialog(null,"Birth Date cannot be a future date");
				flag = false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] address = bo.address.split(",");
		if(address[3].length() != 5)
		{
			JOptionPane.showMessageDialog(null,"Zipcode should be of length 5");
			flag = false;
		}
		return flag;
	}
	
	public static void main(String args[])
	{
		ContactManager contactMgr = new ContactManager();
		
		contactMgr.allsearch();
	}
}

