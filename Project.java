import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

//import com.jcraft.jsch.*;

public class Project{
	private static Connection con;

	public static void main(String[] args)throws ClassNotFoundException,  SQLException  {
	if(args.length==0){
	System.err.println ("Use java Project /? to get usage info");
	System.exit(0);
	}
	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:5940/db2?verifyServerCertificate=false&useSSL=true", "msandbox", "databasepassword");
	System.out.println(con.toString());

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
			if(args[0]=="CreateItem"){
				System.out.println(ProjectMethods.createItem(args[1],args[2],Float.parseFloat(args[3])));
			}else if(args[0]=="CreatePurchase"){
				System.out.println(ProjectMethods.createPurchase(args[1],Integer.parseInt(args[2])));
			}else if(args[0]=="CreateShipment"){
				System.out.println(ProjectMethods.createShipment(args[1],Integer.parseInt(args[2]),Date.valueOf(args[3])));
			}else if(args[0]=="GetItems"){
				System.out.println(ProjectMethods.getItems(args[1]));
			}else if(args[0]=="GetShipments"){
				System.out.println(ProjectMethods.getShipments(args[1]));
			}else if(args[0]=="GetPurchases"){
				System.out.println(ProjectMethods.getPurchses(args[1]));
			}else if(args[0]=="ItemsAvailable"){
				System.out.println(ProjectMethods.itemsAvailable(args[1]));
			}else if(args[0]=="UpdateItem"){
				System.out.println(ProjectMethods.updateItem(args[1],Float.parseFloat(args[2])));
			}else if(args[0]=="DeleteItem"){
				System.out.println(ProjectMethods.deleteItem(args[1]));
			}else if(args[0]=="DeleteShipment"){
				System.out.println(ProjectMethods.deleteShipment(args[1]));
			}else if(args[0]=="DeletePurchase"){
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
