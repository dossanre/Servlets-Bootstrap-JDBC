package testpack;

import java.sql.*;
import java.util.ArrayList;


public class DB_Access {
    private String url = "jdbc:mysql://localhost:3306/test";
	private String driver = "com.mysql.jdbc.Driver";
	private String uname = "root";
	private String upass = "";

	private Connection c;
	private Statement st;
	private PreparedStatement pst;

	public DB_Access() {
		try {
			Class.forName(driver).newInstance();
			c = DriverManager.getConnection(url, uname, upass);
			st = c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int validateLogin(String un, String up) {
		int uid = -1; // lets agree that -1 is for invalid user login

		String sql = "select uid from tuser02 where loginname = ? and loginpass = ?";
		try {
			pst = c.prepareStatement(sql);
			pst.setString(1, un);
			pst.setString(2, up);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uid = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return uid;
	}

	public String getUserName(int uid) {
		String sql = "select name from tuser02 where uid = " + uid;
		String uname = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next())
				uname = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uname;
	}
	
	public String getUserPassword(int uid) {
		String sql = "select LoginPass from tuser02 where uid = " + uid;
		String upass = "";
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next())
				upass = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return upass;
	}

	public ArrayList<Item> getAllUserItems(int uid) {
		ArrayList<Item> all = new ArrayList<Item>();

		String sql = "select iid, itemname, qty from titems02 where uid = " + uid+" Order by itemname";

		try {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Item i = new Item(rs.getInt(1), rs.getString(2), rs.getInt(3));
				all.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return all;
	}

	public int createUserAccount(User u) {
		
		// 0 means everything is OK, user is created
		// 1 means values are too long
		// 2 means unique constraint on the login name has been violated
		// 3 means that an empty form field was submitted
		// 4 means that the passwords are not the same
		int status = 0;
		try {
		if(u.getLoginName().trim().equals("") || 
				u.getName().trim().equals("") || 
				u.getLoginPass1().trim().equals("") ||
				u.getLoginPass2().trim().equals("")) return 3;
		if(u.getLoginName().trim().length() > 20 || 
				u.getName().trim().length() > 20 || 
				u.getLoginPass1().trim().length()> 20 ||
				u.getLoginPass2().trim().length()>20) return 1;
		
		if(!u.getLoginPass1().trim().equals(u.getLoginPass2().trim())) return 4;
		
		String sql = "insert into tuser02 (LoginName, Name, LoginPass) values (?, ?, ?)";
		
		
			pst = c.prepareStatement(sql);
			pst.setString(1, u.getLoginName());
			pst.setString(2, u.getName());
			pst.setString(3, u.getLoginPass1());
			pst.executeUpdate();
		} catch (SQLException e) {
			status=2;
		//	e.printStackTrace();
		}

		return status;
	}
	
	public int updateItem(Item i) { 
		
		int res = 0;

		String sql;
		sql = "update titems02 set itemname=?, qty=? where iid=?";
														
		try {
			// 0 - OK - item was inserted
			// 1 - item name was not given
			// 2 - item qty was either not given or not a valid int

			pst = c.prepareStatement(sql);
			pst.setString(1, i.getName());
			pst.setInt(2, i.getQty());// yes
			pst.setInt(3, i.getId()); // 3,iid
			pst.executeUpdate();
			res = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public int deleteItem(int iid) { // logged in user can only log in and iid
									// passed as a parameter
		int res = 0;
		String sql = "delete from titems02 where iid=?";
		// int qty = 0;

		try {
			pst = c.prepareStatement(sql);
			pst.setInt(1, iid);
			pst.executeUpdate();// because we need to execute the query string
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return res;
	}

	public int addItem(String iname, int qty, Integer uid) {
		int res = 0;
		// 0 - OK - item was inserted
		// 1 - item name was not given
		// 2 - item qty was either not given or not a valid int
		if (iname == null || iname.trim().equals(""))
			return 1;

		String sql = "insert into titems02 (ItemName, Qty, uid) values (?, ?, ?)";
		try {
			pst = c.prepareStatement(sql);
			pst.setString(1, iname);
			pst.setInt(2, qty);
			pst.setInt(3, uid);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int deleteAccount(User u) {
		int res = 0;
		String sql = "delete from tuser02 where uid=?";

		try {
			pst = c.prepareStatement(sql);
			pst.setInt(1, u.getUid());
			// pst.setInt(2, uid);
			pst.executeUpdate();// because we need to execute the query string
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return res;
	}
	
	public Item getNameQty(Item it) {

		String sql = "select iid, itemname, qty from titems02 where iid = " + it.getId();

		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				it.setId(rs.getInt(1));
				it.setName(rs.getString(2));
				it.setQty(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return it;
	}
	
	public int updateUserInFullName( String fName, int uid ) {
		
		String sql = "update tuser02 set name=? where uid=?";
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, fName);
			pst.setInt(2, uid);
			int res = pst.executeUpdate();

			if (res==0) {
				return 0;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
		
		return 1;
		
	}	
	
	public int updateUserPassword( String nPass, int uid ) {
		
		String sql = "update tuser02 set loginpass=? where uid=?";
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, nPass);
			pst.setInt(2, uid);
			int res = pst.executeUpdate();

			if (res==0) {
				return 2;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
		return 0;
		
	}
	

}
