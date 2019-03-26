
import org.junit.Before;
import org.junit.Test;

//import org.junit.Test;

public class MyTestVersion1 {
	Database D;
	@Before
	public void CreateDatabase() {
		D=new Database();
	}
	
	@Test(expected=ProductExists.class)
	public void SamePathTest() throws ProductExists {
	//	Database D=new Database();
		D.Insert("Electronics>Phone>Iphone", "Iphone4", 13000, 3);
		D.Insert("Electronics>Phone", "Iphone4", 13000, 3);
	}
	
	@Test(expected=InvalidPath.class)
	public void DeleteProductTest() throws InvalidPath, ProductExists {
//		Database D=new Database();
		//D.Insert("Electronics>Phone2>Iphone", "Iphone4", 13000, 3);
		D.Delete("Electronics>Phone>Iphone");
	}
	
	@Test(expected=ProductNotFound.class)
	public void SearchProductTest() throws ProductNotFound {
		//Database D=new Database();
		D.Search("Iphone4", 0);
	}
	
	
	@Test(expected=ProductOutOfStock.class)
	public void ProductOutofStockTest() throws ProductNotFound, ProductExists, ProductOutOfStock {
		//Database D=new Database();
		D.Insert("Electronics>Phone>Iphone", "Iphone4", 13000, 3);
		Customer C=new Customer("Pranjal", D);
		C.AddProduct("Iphone4", 4);
	}
	
	@Test(expected=InsufficientFunds.class)
	public void OutofFunds() throws ProductNotFound, ProductExists, ProductOutOfStock, InsufficientFunds {
		//Database D=new Database();
		D.Insert("Electronics>Phone>Iphone", "Iphone4", 13000, 3);
		Customer C=new Customer("Pranjal", D);
		C.AddProduct("Iphone4", 3);
		C.AddFunds(10000);
		C.CheckOut();
	}
	
	
	
	
	
	

}
