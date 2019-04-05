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
	
	/**
	 * Get Items
	 * @param itemCode
	 * @return
	 */
	public static String getItems(String itemCode) {
		
		Connection con = getConnection();
		
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Item where "+itemCode+" = ItemCode or "+itemCode+"= '%'");
			
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
			ResultSet rs = stmt.executeQuery("select s.* from Shipment s " + 
											 "		left join Item i on i.ID = s.ItemID" + 
											 "	where "+itemCode+" = ItemCode or "+itemCode+" = '%';");
			
			String table = getTable(rs);
			return table;
			
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
