import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * This class contains the methods that will be used in the Final Project for CS-HU310
 * @author Geoffrey Meier
 * @author Ryan Cox
 *
 */
public class ProjectMethods {
	public static String createItem(String code, String description, float price){
	PreparedStatement stmt = null;
	Connection con = getConnection();
	try {
		stmt = con.prepareStatement("use db2; Insert into Item (ItemCode, ItemDescription, Price) values(?,?,?);");
		stmt.setString(1, ""+code); // input parameter			
		stmt.setString(2, ""+description);
		stmt.setString(3, ""+price);
		int testing = stmt.executeUpdate();
      		System.out.println(("Item "+code+" created succesfully"));
	} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release resources in a finally{} block
			// in reverse-order of their creation if they are no-longer needed
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}

		return("");
	}


public static String createPurchase(String code, int quantity){
	PreparedStatement stmt = null;
	Connection con = getConnection();
	try {
		stmt = con.prepareStatement("use db2; Insert into Purchase (ItemID, Quantity) \n values(?,?);");
		stmt.setString(1, "(select ID from Item where code like \""+code+"\")"); // input parameter			
		stmt.setString(2, ""+quantity);
		stmt.execute();
      		System.out.println(("Purchase created succesfully"));
	} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release resources in a finally{} block
			// in reverse-order of their creation if they are no-longer needed
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}

		return("");
	}

	public static String createShipment(String code, int quantity, Date day){
		PreparedStatement stmt = null;
		Connection con = getConnection();
		try {
			stmt = con.prepareStatement("use db2; Insert into Shipment (ItemID, Quantity,ShipmentDate) \n values(?,?,?);");
			stmt.setString(1, "(select ID from Item where code like \""+code+"\")"); // input parameter			
			stmt.setString(2, ""+quantity);
			stmt.setString(3,"\'"+day.toString()+"\'");
			stmt.execute();
			System.out.println(("Shipment created succesfully"));
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release resources in a finally{} block
			// in reverse-order of their creation if they are no-longer needed
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}

		return("");
	}

	public static String getItems(String itemCode) {

		Connection con = getConnection();

		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("use db2; select * from Item where \""+itemCode+"\" = ItemCode or \""+itemCode+"\" = '%'");

			String table = getTable(rs);
			return table;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}


	public static String getShipments(String itemCode) {

		Connection con = getConnection();

		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("use db2; select s.* from Shipment s " + 
					"		left join Item i on i.ID = s.ItemID" + 
					"	where \""+itemCode+"\" = ItemCode or \""+itemCode+"\" = '%';");

			String table = getTable(rs);
			return table;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}

	public static String getPurchases(String itemCode) {

		Connection con = getConnection();

		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("use db2; select s.* from Purchase p " + 
					"		left join Item i on i.ID = p.ItemID" + 
					"	where \""+itemCode+"\" = ItemCode or \""+itemCode+"\" = '%';");

			String table = getTable(rs);
			return table;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}

	public static String itemsAvailable(String itemCode) {

		Connection con = getConnection();

		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("use db2; select  ItemCode, \n" + 
					"		ItemDescription, \n" + 
					"		IFNULL(sum(s.Quantity),0) - IFNULL(sum(p.Quantity),0) \n" + 
					"			as NumAvailable\n" + 
					"	from Item i\n" + 
					"		left join Shipment s on i.ID = s.ID\n" + 
					"		left join Purchase p on i.ID = p.ID\n" + 
					"	where \""+itemCode+"\" = ItemCode or \""+itemCode+"\" = '%'" +
					"	group by ItemCode, ItemDescription;");

			String table = getTable(rs);
			return table;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}


	public static String updateItem(String itemCode,double price) {

		Connection con = getConnection();

		try {
			java.sql.Statement stmt = con.createStatement();
			int result = stmt.executeUpdate("use db2; update Item set Price = "+price+" where ItemCode = \""+itemCode+"\";");

			return result+" records affected.";

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}


	public static String deleteItem(String itemCode) {

		Connection con = getConnection();

		try {
			java.sql.Statement stmt = con.createStatement();
			int result = stmt.executeUpdate("use db2; delete from Item where ItemCode = \""+itemCode+"\";");

			return result+" records affected.";

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}


	public static String deleteShipment(String itemCode) {

		Connection con = getConnection();

		try {
			java.sql.Statement stmt = con.createStatement();
			int result = stmt.executeUpdate("use db2; delete s from Shipment\n" + 
					"		left join Item i on i.ID = s.ItemID\n" + 
					"	where \""+itemCode+"\" = ItemCode\n" + 
					"	having ShipmentDate = max(ShipmentDate);");

			return result+" records affected.";

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}



	/**
	 * Return the connection from the Project
	 * @return
	 */
	private static Connection getConnection() {
		return Project.getConnection(); //get the connection to the database
	}

	/**
	 * Given a result set, return the table of values
	 * @param rs The result set
	 * @return The table of values
	 * @throws SQLException 
	 */
	private static String getTable(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		String table = "";

		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) table += (",  ");
				String columnValue = rs.getString(i);
				table += (columnValue + " " + rsmd.getColumnName(i));
			}
			table += "\n";
		}
		return table;
	}
}
