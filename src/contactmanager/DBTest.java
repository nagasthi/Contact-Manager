package contactmanager;

import java.sql.*;
import java.util.ArrayList;  
import java.text.ParseException;
import java.text.SimpleDateFormat;

class DBTest{  
     Connection con = null;
     Statement stmt = null;
     ResultSet rs = null;
     ArrayList<String> FirstName = new ArrayList<String>();


	private void closeConnection(Connection con)
    {
    try
      {
      if (con != null)
        {
        con.close();
        }
      con = null;
      }
    catch (Exception e)
      {
      e.printStackTrace();
      }
    }
	
	public ArrayList<BusinessObject> search(BusinessObject bo) throws ClassNotFoundException, SQLException
	{
	
		String userName = "root";
		String password = "meliferocks2";

		String url = "jdbc:mysql://localhost:3306/contact_manager";

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, userName, password);
		
		String searchquery="select * from contacts where sex=? ";
		int count=0;
		
		if(bo.contact_id!=null)
		{
			searchquery+="and contact_id = ? ";
			count+=1;
		}
		
			
		if((bo.fname !=null) && !bo.fname.isEmpty())
		{
			searchquery+="and fname=? ";
			count+=1;
		}
		
		
		if((bo.mi !=null) && !bo.mi.isEmpty())
		{
			searchquery+="and mname=? ";
			count+=1;

		}
		if((bo.lname !=null) && !bo.lname.isEmpty())
		{
			searchquery+="and lname=? ";
			count+=1;

		}
		if((bo.bday !=null) && !bo.bday.isEmpty())
		{
			searchquery+="and bday=? ";
			count+=1;

		}
		
		System.out.println(searchquery);
		System.out.println("count"+count);
		
		PreparedStatement pstmt1= conn.prepareStatement(searchquery);

		
		pstmt1.setString(1,bo.sex);			
		count+=1;
		if((bo.bday !=null) && (!bo.bday.isEmpty()) &&  count>0)
		{
		pstmt1.setString(count, bo.bday);	
		count-=1;
		}
		
		if((bo.lname !=null)&& (!bo.lname.isEmpty()) &&  count>0)
		{
		pstmt1.setString(count, bo.lname);	
		count-=1;
		}
		
		if((bo.mi !=null) && (!bo.mi.isEmpty()) && count>0)
		{
		pstmt1.setString(count, bo.mi);	
		count-=1;
		}

		if((bo.fname !=null) && (!bo.fname.isEmpty()) &&  count>0)
		{
		pstmt1.setString(count,bo.fname);	
		count-=1;
		}
		
		if((bo.contact_id!=null) && (!bo.contact_id.isEmpty()) && count>0)
		{
		pstmt1.setString(count,bo.contact_id);	
		count-=1;
		}
		
		ResultSet rs = pstmt1.executeQuery();
		ArrayList<BusinessObject> BoList = new ArrayList<BusinessObject>();
		while(rs.next())
		{
			BusinessObject bo_out = new BusinessObject();
			bo_out.contact_id = rs.getString(1);
			bo_out.fname = rs.getString(2);
			bo_out.mi = rs.getString(3);
			bo_out.lname = rs.getString(4);
			bo_out.sex=rs.getString(7);
			bo_out.bday=rs.getString(11);
			bo_out.address_id = rs.getString("address_id");
			bo_out.phn_num_id = rs.getString("phnno_id");
			bo_out.email_id_no = rs.getString("email_id_no");
			BoList.add(bo_out);
			
		}
		for(int i= 0; i< BoList.size();i++)
		{
			BusinessObject bo_inp = BoList.get(i);
			BusinessObject bo_gen = new BusinessObject();
			ArrayList<BusinessObject> BoAdrList = SearchAddress(bo_inp);
			System.out.println(bo_inp.contact_id + " cont");
			for(int j=0;j<BoAdrList.size();j++)
			{
				bo_gen = BoAdrList.get(j);
				if(bo_inp.contact_id.equals(bo_gen.contact_id))
				{
					bo_inp.address_id = bo_gen.address_id;
					bo_inp.street = bo_gen.street;
					bo_inp.city = bo_gen.city;
					bo_inp.state = bo_gen.state;
					bo_inp.zipcode = bo_gen.zipcode;
					bo_inp.country = bo_gen.country;
				}
			}
			ArrayList<BusinessObject> BophnList = SearchPhn(bo_inp);
			for(int j=0;j<BophnList.size();j++)
			{
				bo_gen = BophnList.get(j);
				if(bo_inp.contact_id.equals(bo_gen.contact_id))
				{
					bo_inp.phn_num_id = bo_gen.phn_num_id;
					bo_inp.phnno = bo_gen.phnno;	
				}
			}
			ArrayList<BusinessObject> BoEmailList = SearchEmail(bo_inp);
			for(int j=0;j<BoEmailList.size();j++)
			{
				bo_gen = BoEmailList.get(j);
				if(bo_inp.contact_id.equals(bo_gen.contact_id))
				{
					bo_inp.email = bo_gen.email;
				}
			}
			System.out.println("input phn " + bo_inp.phnno);
			BoList.set(i, bo_inp);
			
		}
		
		

		closeConnection(conn);
		 return BoList;
		
		
	}
	
		public ArrayList<BusinessObject> SearchAddress(BusinessObject bo) throws SQLException, ClassNotFoundException
		{
			String userName = "root";
			String password = "meliferocks2";

			String url = "jdbc:mysql://localhost:3306/contact_manager";

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String AddressQuery = "select * from address where contact_id = ? and address_id= ?";
			PreparedStatement pstmt = conn.prepareStatement(AddressQuery);
			
			pstmt.setString(1,bo.contact_id);
			pstmt.setString(2,bo.address_id);
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<BusinessObject> bolist = new ArrayList<BusinessObject>();
			while(rs.next())
			{
				BusinessObject bo_in = new BusinessObject();
				bo_in.contact_id = rs.getString("contact_id");
				bo_in.address_id = rs.getString("address_id");
				bo_in.street = rs.getString("street");
				bo_in.city = rs.getString("city");
				bo_in.state = rs.getString("state");
				bo_in.zipcode = rs.getString("zipcode");
				bo_in.country = rs.getString("country");
				
				bolist.add(bo_in);
			}
			
			return bolist;
			
		}
		
		public ArrayList<BusinessObject> SearchPhn(BusinessObject bo) throws SQLException, ClassNotFoundException
		{
			String userName = "root";
			String password = "meliferocks2";
	
			String url = "jdbc:mysql://localhost:3306/contact_manager";
	
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String AddressQuery = "select * from phonenumbers where contact_id = ? and phnno_id= ?";
			PreparedStatement pstmt = conn.prepareStatement(AddressQuery);
			
			pstmt.setString(1,bo.contact_id);
			pstmt.setString(2,bo.phn_num_id);
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<BusinessObject> bolist = new ArrayList<BusinessObject>();
			while(rs.next())
			{
				BusinessObject bo_in = new BusinessObject();
				System.out.println(rs.getString("phone_number") + " phn num " + rs.getString("contact_id")+ " contact id");
				bo_in.contact_id = rs.getString("contact_id");
				bo_in.phn_num_id = rs.getString("phnno_id");
				bo_in.phnno = rs.getString("phone_number");
				
				bolist.add(bo_in);
			}
		
		return bolist;
}

		public ArrayList<BusinessObject> SearchEmail(BusinessObject bo) throws SQLException, ClassNotFoundException
		{
			String userName = "root";
			String password = "meliferocks2";
	
			String url = "jdbc:mysql://localhost:3306/contact_manager";
	
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String AddressQuery = "select * from email where contact_id = ? and email_id_no= ?";
			PreparedStatement pstmt = conn.prepareStatement(AddressQuery);
			
			pstmt.setString(1,bo.contact_id);
			pstmt.setString(2,bo.email_id_no);
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<BusinessObject> bolist = new ArrayList<BusinessObject>();
			while(rs.next())
			{
				BusinessObject bo_in = new BusinessObject();
				bo_in.contact_id = rs.getString("contact_id");
				bo_in.email_id_no = rs.getString("email_id_no");
				bo_in.email = rs.getString("email_id");
				
				bolist.add(bo_in);
			}
		
		return bolist;
}
		
		public void DeleteRecords(BusinessObject bo) throws ClassNotFoundException, SQLException
		{
			PreparedStatement pstmt = null;
			
			String userName = "root";
			String password = "meliferocks2";
	
			String url = "jdbc:mysql://localhost:3306/contact_manager";
	
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			
			String GetIdsQuery = "select email_id_no,phnno_id,address_Id from Contacts where contact_id = ?";
			pstmt = conn.prepareStatement(GetIdsQuery);
			pstmt.setString(1,bo.contact_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				bo.email_id_no = rs.getString("email_id_no");
				bo.phn_num_id = rs.getString("phnno_id");
				bo.address_id = rs.getString("address_id");
				
			}
			
			String updateIdsQuery = "update Contacts set email_id_no = ?, phnno_id = ?,address_Id = ? where contact_id = ?";
			pstmt = conn.prepareStatement(updateIdsQuery);
			pstmt.setString(1,null);
			pstmt.setString(2,null);
			pstmt.setString(3,null);
			pstmt.setString(4,bo.contact_id);
			pstmt.executeUpdate();
			
			System.out.println("contact id " + bo.contact_id);
			
			String deleteemail = "delete from email where contact_id = ? ";
			pstmt = conn.prepareStatement(deleteemail);
			pstmt.setString(1,bo.contact_id);
			//pstmt.setString(2,bo.email_id_no);
			pstmt.executeUpdate();
			
			String deletePhnnumber = "delete from phonenumbers where contact_id = ? ";
			pstmt = conn.prepareStatement(deletePhnnumber);
			pstmt.setString(1,bo.contact_id);
			//pstmt.setString(2,bo.phn_num_id);
			pstmt.executeUpdate();
			
			String deleteAddress = "delete from Address where contact_id = ? ";
			pstmt = conn.prepareStatement(deleteAddress);
			pstmt.setString(1,bo.contact_id);
			//pstmt.setString(2,bo.address_id);
			pstmt.executeUpdate();
			
			String deleteContacts = "delete from Contacts where contact_id =?";
			pstmt = conn.prepareStatement(deleteContacts);
			pstmt.setString(1,bo.contact_id);
			pstmt.executeUpdate();
			closeConnection(conn);
			
			
		}
		
		public void UpdateRecords(BusinessObject bo) throws ClassNotFoundException, SQLException
		{

			PreparedStatement pstmt = null;
			
			String userName = "root";
			String password = "meliferocks2";
	
			String url = "jdbc:mysql://localhost:3306/contact_manager";
	
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			
			String GetIdsQuery = "select email_id_no,phnno_id,address_Id from Contacts where contact_id = ?";
			pstmt = conn.prepareStatement(GetIdsQuery);
			pstmt.setString(1,bo.contact_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				bo.email_id_no = rs.getString("email_id_no");
				bo.phn_num_id = rs.getString("phnno_id");
				bo.address_id = rs.getString("address_id");
				
			}
			
			String updatePhnQuery = "update phonenumbers set phone_number=? where contact_id = ? and phnno_id = ?";
			pstmt = conn.prepareStatement(updatePhnQuery);
			pstmt.setString(1,bo.phnno);
			pstmt.setString(2,bo.contact_id);
			pstmt.setString(3,bo.phn_num_id);
			pstmt.executeUpdate();
			
			

			String updateEmailQuery = "update email set email_id=? where contact_id = ? and email_id_no = ?";
			pstmt = conn.prepareStatement(updateEmailQuery);
			pstmt.setString(1,bo.email);
			pstmt.setString(2,bo.contact_id);
			pstmt.setString(3,bo.email_id_no);
			pstmt.executeUpdate();
			
			String[] addr= bo.address.split(",");
			String updateAddrQuery = "update address set street=?,city=?,state=?,zipcode=?,country=? where contact_id = ? and address_id = ?";
			pstmt = conn.prepareStatement(updateAddrQuery);
			pstmt.setString(1,addr[0]);
			pstmt.setString(2,addr[1]);
			pstmt.setString(3,addr[2]);
			pstmt.setString(4,addr[3]);
			pstmt.setString(5,addr[4]);
			pstmt.setString(6,bo.contact_id);
			pstmt.setString(7,bo.address_id);
			pstmt.executeUpdate();
			
			String updateCntctQuery = "update contacts set fname=?,mname=?,lname=?,sex=? where contact_id = ? ";
			pstmt = conn.prepareStatement(updateCntctQuery);
			pstmt.setString(1,bo.fname);
			pstmt.setString(2,bo.mi);
			pstmt.setString(3,bo.lname);
			pstmt.setString(4,bo.sex);
			pstmt.setString(5,bo.contact_id);
			pstmt.executeUpdate();
			
			
			closeConnection(conn);
			
			
		
		}
		
			
	public void save(BusinessObject cm) throws SQLException, ParseException, ClassNotFoundException 
	{
		
		String userName = "root";
		String password = "meliferocks2";

		String url = "jdbc:mysql://localhost:3306/contact_manager";

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, userName, password);
		
		String maxcntctidQuery = "select max(cast(contact_ID as unsigned)) from contacts;";
		String insertContact="insert into contacts(contact_id,fname,mname,lname,sex,nickname,address_id,email_id_no,bday,phnno_id) values(?,?,?,?,?,?,?,?,?,?)";
		
		Statement stmt1 = conn.createStatement();
		stmt1.execute(maxcntctidQuery);
		ResultSet rs = stmt1.getResultSet();
		if(rs.next())
		{
			cm.contact_id = rs.getString(1);
		}
		int maxid = Integer.parseInt(cm.contact_id);
		maxid = maxid+1;
		System.out.println(maxid);
		PreparedStatement stmt = conn.prepareStatement(insertContact);
		stmt.setString(1,String.valueOf(maxid));
		stmt.setString(2,cm.fname);
		stmt.setString(3,cm.mi);
		stmt.setString(4,cm.lname);
		stmt.setString(5,cm.sex);
		stmt.setString(6,cm.nickname);
		stmt.setString(7,cm.address_id);
		stmt.setString(8,cm.email_id_no);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		Date bday = new java.sql.Date(sdf.parse(cm.bday).getTime());
		stmt.setDate(9,bday);
		stmt.setString(10,cm.phn_num_id);

		stmt.executeUpdate();
		closeConnection(conn);
		
		//To insert address record
		String maxaddridQuery = "select max(cast(address_id as unsigned)) from address";
		String insertAddress = "insert into address(contact_id,address_type,address_id,street,city,state,zipcode,country) values(?,?,?,?,?,?,?,?)";

		Connection conn1 = DriverManager.getConnection(url, userName, password);
		Statement stmt2 = conn1.createStatement();
		stmt2.execute(maxaddridQuery);
		ResultSet rs1 = stmt2.getResultSet();
		if(rs1.next())
		{
			cm.address_id = rs1.getString(1);
		}
		int maxaddid = Integer.parseInt(cm.address_id);
		maxaddid = maxaddid+1;
		System.out.println(maxaddid);
		
		PreparedStatement pstmt2 = conn1.prepareStatement(insertAddress);
		
		pstmt2.setString(1,String.valueOf(maxid));
		pstmt2.setString(2,"home");
		pstmt2.setString(3,String.valueOf(maxaddid));
		pstmt2.setString(4,cm.street);
		pstmt2.setString(5,cm.city);
		pstmt2.setString(6,cm.state);
		pstmt2.setString(7,cm.zipcode);
		pstmt2.setString(8,cm.country);
		pstmt2.executeUpdate();
		closeConnection(conn1);
		
		
		//To insert phone number record
		String maxphnnoidQuery = "select max(cast(phnno_id as unsigned)) from phonenumbers";
		String insertphnno = "insert into phonenumbers values(?,?,?,?)";
		
		Connection conn2 = DriverManager.getConnection(url, userName, password);
		Statement stmt3 = conn2.createStatement();
		stmt3.execute(maxphnnoidQuery);
		
		ResultSet rs2 = stmt3.getResultSet();
		if(rs2.next())
		{
			cm.phn_num_id=rs2.getString(1);
		}
		int maxphnnoid = Integer.parseInt(cm.phn_num_id);
		maxphnnoid+=1;
				
		PreparedStatement pstmt3 = conn2.prepareStatement(insertphnno);
		pstmt3.setString(1,String.valueOf(maxid));
		pstmt3.setString(2,"mobile");
		pstmt3.setString(3,cm.phnno);
		pstmt3.setString(4,String.valueOf(maxphnnoid));
		
		pstmt3.executeUpdate();
		closeConnection(conn2);
		//To insert email id
		String maxemailidnoQuery = "select max(cast(email_id_no as unsigned)) from email";
		String insertEmail = "insert into Email values(?,?,?)";
		
		Connection conn3 = DriverManager.getConnection(url, userName, password);
		Statement stmt4= conn3.createStatement();
		stmt4.execute(maxemailidnoQuery);
		
		ResultSet rs4= stmt4.getResultSet();
		if(rs4.next())
		{
			cm.email_id_no=rs4.getString(1);
		}
		int maxemailidno = Integer.parseInt(cm.email_id_no);
		maxemailidno+=1;
		
		PreparedStatement pstmt4 = conn3.prepareStatement(insertEmail);
		pstmt4.setString(1,String.valueOf(maxid));
		pstmt4.setString(2,cm.email);
		pstmt4.setString(3,String.valueOf(maxemailidno));
		
		pstmt4.executeUpdate();
		closeConnection(conn3);
		
		Connection conn4 = DriverManager.getConnection(url, userName, password);
		
		String updatecntcQuery ="update Contacts set address_id=?,phnno_id=?,email_id_no=? where contact_id=?";
		PreparedStatement stmt5 = conn4.prepareStatement(updatecntcQuery);
		stmt5.setString(1, String.valueOf(maxaddid));
		stmt5.setString(2, String.valueOf(maxphnnoid));
		stmt5.setString(3, String.valueOf(maxemailidno));
		stmt5.setString(4,String.valueOf(maxid));
		stmt5.executeUpdate();
		
		closeConnection(conn4);
		
		
	}
}  
 
