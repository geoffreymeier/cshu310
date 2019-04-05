



import java.io.*;
import java.sql.*;
import java.util.*;

//import com.jcraft.jsch.*;

public class Project{
	
	public final static Connection CON;

	public static void main(String[] args)throws ClassNotFoundException,  SQLException  {
	if(args.length==0){
	System.err.println ("Use java Project /? to get usage info");
	System.exit(0);
	}
	Class.forName("com.mysql.jdbc.Driver");
	CON = DriverManager.getConnection("jdbc:mysql://localhost:5940/db2?verifyServerCertificate=false&useSSL=true", "msandbox", "databasepassword");
	System.out.println(CON.toString());

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
				System.out.println(CreateItem(args[1],args[2],Float.parseFloat(args[3])));
			}else if(args[0]=="CreatePurchase"){
				System.out.println(CreatePurchase(args[1],Integer.parseInt(args[2])));
			}else if(args[0]=="CreateShipment"){
				System.out.println(CreateShipment(args[1],Integer.parseInt(args[2]),Date.valueOf(args[3])));
			}else if(args[0]=="GetItems"){
				System.out.println(GetItems(args[1]));
			}else if(args[0]=="GetShipments"){
				System.out.println(GetShipments(args[1]));
			}else if(args[0]=="GetPurchases"){
				System.out.println(GetPurchses(args[1]));
			}else if(args[0]=="ItemsAvailable"){
				System.out.println(ItemsAvailable(args[1]));
			}else if(args[0]=="UpdateItem"){
				System.out.println(UpdateItem(args[1],Float.parseFloat(args[2])));
			}else if(args[0]=="DeleteItem"){
				System.out.println(DeleteItem(args[1]));
			}else if(args[0]=="DeleteShipment"){
				System.out.println(DeleteShipment(args[1]));
			}else if(args[0]=="DeletePurchase"){
				System.out.println(DeletePurchase(args[1]));
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
}
