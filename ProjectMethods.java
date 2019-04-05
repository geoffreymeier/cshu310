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
	
	public static String getItems(String itemCode) {
		
		Connection con = getConnection();
		
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Item where "+itemCode+" = ItemCode or "+itemCode+"= '%'");
			
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	private static Connection getConnection() {
		 return Project.getConnection(); //get the connection to the database
	}

}
