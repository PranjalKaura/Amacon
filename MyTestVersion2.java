import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class MyTestVersion2 {
	
	public static void Serialize(Database c2 ) throws IOException  {
		ObjectOutputStream out	=	null;
		
		try {
			out=new	ObjectOutputStream	(new FileOutputStream("OutDatabaseCheck.txt"));
			out.writeObject(c2);
			
		}
		finally {
			out.close();
		}
		
		
	}
	
	public static void Serialize(Customer c2 ) throws IOException  {
		ObjectOutputStream out	=	null;
		
		try {
			out=new	ObjectOutputStream	(new FileOutputStream("OutDatabaseCheck.txt"));
			out.writeObject(c2);
			
		}
		finally {
			out.close();
		}
		
		
	}
	
	public static Database DeserializeD() throws IOException, ClassNotFoundException {
		ObjectInputStream	in	=	null;	
		Database c3=null;
		try {
			in=new ObjectInputStream(new FileInputStream("OutDatabaseCheck.txt"));
			
			 c3=(Database)in.readObject();	
		}
		finally{
			in.close();
		}
		return c3;
		
	}
	
	public static Customer DeserializeC() throws IOException, ClassNotFoundException {
		ObjectInputStream	in	=	null;	
		Customer c3=null;
		try {
			in=new ObjectInputStream(new FileInputStream("OutDatabaseCheck.txt"));
			
			 c3=(Customer)in.readObject();	
		}
		finally{
			in.close();
		}
		return c3;
		
	}
	
	@Test
	public void DatabaseSerTest() throws ProductExists, IOException, ClassNotFoundException, ProductNotFound {
		Database D=new Database();
		D.Insert("Electronics>Phone>Iphone", "Iphone4", 13000, 3);
		Serialize(D);
		assertEquals(D, DeserializeD());
	}
	
	@Test
	public void DatabaseCartTest() throws ProductExists, IOException, ProductNotFound, ProductOutOfStock, ClassNotFoundException {
		Database D=new Database();
		D.Insert("Electronics>Phone>Iphone", "Iphone4", 13000, 3);
		Customer c1=new Customer("Pranjal", D);
		c1.AddProduct("Iphone4", 1);
		Serialize(c1);
		Customer c2=DeserializeC();
		assertEquals(c1.GetCart(), c2.GetCart());
		
	}
	
	

}
