import java.sql.*;
import java.sql.Date;
import java.io.*;
import java.util.*;

//import com.jcraft.jsch.*;

public class Project{
	
	private static Connection con;

	public static void main(String[] args)throws ClassNotFoundException,  SQLException, InstantiationException, IllegalAccessException  {
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	con = DriverManager.getConnection("jdbc:mysql://localhost:5940", "msandbox", "databasepassword");
	System.out.println(con.toString());
	if(args.length==0){
	System.err.println ("Use java Project /? to get usage info");
	System.exit(0);
	}
	if (args[0].equals("/?") ){
		System.out.println ("Usage : java Project CreateItem <itemCode> <itemDescription> <price>");
		System.out.println ("Usage : java Project CreatePurchase <itemCode> <PurchaseQuantity>");
		System.out.println ("Usage : java Project CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>");
		System.out.println ("Usage : java Project GetItems <itemCode>");
		System.out.println ("Usage : java Project GetShipments <itemCode>");
		System.out.println ("Usage : java Project GetPurchases <itemCode>");
		System.out.println ("Usage : java Project ItemsAvailable <itemCode>");
		System.out.println ("Usage : java Project UpdateItem <itemCode> <price>");
		System.out.println ("Usage : java Project DeleteItem <itemCode>");
		System.out.println ("Usage : java Project DeleteShipment <itemCode>");
		System.out.println ("Usage : java Project DeletePurchase <itemCode>");
		}else {
			if(args[0].equals("CreateItem")){
				System.out.println(ProjectMethods.createItem(args[1],args[2],Float.parseFloat(args[3])));
			}else if(args[0].equals("CreatePurchase")){
				System.out.println(ProjectMethods.createPurchase(args[1],Integer.parseInt(args[2])));
			}else if(args[0].equals("CreateShipment")){
				System.out.println(ProjectMethods.createShipment(args[1],Integer.parseInt(args[2]),Date.valueOf(args[3])));
			}else if(args[0].equals("GetItems")){
				System.out.println(ProjectMethods.getItems(args[1]));
			}else if(args[0].equals("GetShipments")){
				System.out.println(ProjectMethods.getShipments(args[1]));
			}else if(args[0].equals("GetPurchases")){
				System.out.println(ProjectMethods.getPurchases(args[1]));
			}else if(args[0].equals("ItemsAvailable")){
				System.out.println(ProjectMethods.itemsAvailable(args[1]));
			}else if(args[0].equals("UpdateItem")){
				System.out.println(ProjectMethods.updateItem(args[1],Float.parseFloat(args[2])));
			}else if(args[0].equals("DeleteItem")){
				System.out.println(ProjectMethods.deleteItem(args[1]));
			}else if(args[0].equals("DeleteShipment")){
				System.out.println(ProjectMethods.deleteShipment(args[1]));
			}else if(args[0].equals("DeletePurchase")){
				System.out.println(ProjectMethods.deletePurchase(args[1]));
			}else {
				System.out.println ("Usage : java Project CreateItem <itemCode> <itemDescription> <price>");
				System.out.println ("Usage : java Project CreatePurchase <itemCode> <PurchaseQuantity>");
				System.out.println ("Usage : java Project CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>");
				System.out.println ("Usage : java Project GetItems <itemCode>");
				System.out.println ("Usage : java Project GetShipments <itemCode>");
				System.out.println ("Usage : java Project GetPurchases <itemCode>");
				System.out.println ("Usage : java Project ItemsAvailable <itemCode>");
				System.out.println ("Usage : java Project UpdateItem <itemCode> <price>");
				System.out.println ("Usage : java Project DeleteItem <itemCode>");
				System.out.println ("Usage : java Project DeleteShipment <itemCode>");
				System.out.println ("Usage : java Project DeletePurchase <itemCode>");
			}
		}

	con.close();
	}
	
	/**
	 * Return the connection to the database.
	 * @return
	 */
	public static Connection getConnection() {
		return con;
	}
}
