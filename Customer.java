import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public final class Customer implements Serializable{
	
	private int Funds=0;
	private final String Name;
	private Database data;
	private ArrayList<Things> Cart=new ArrayList<Things>();
	private ArrayList<Integer> CartQ=new ArrayList<Integer>();
	protected int pointer=0; 
	public Customer(String Name,Database data) {
		// TODO Auto-generated constructor stub
		this.data=data;
		this.Name=Name;
	}
	
	public final ArrayList<Things> GetCart() {
		return Cart;
	}
	public  final void AddFunds(int number) {
		Funds+=number;
	}
	
	public  final int GetFunds() {
		return Funds;
	}
	
	public  final String getName() {
		return Name;
	}
	
	public  final void setDatabase(Database data) {
		this.data=data;
	}
	
	protected  final void AddProduct(String product,int Quantity) throws ProductNotFound, ProductOutOfStock
	{
		Things obj=data.Search(product,0);
		if(obj==null) {
			throw new ProductNotFound("Product Not Found");
		}
		if(Quantity>obj.getQuantity()) {
			throw new ProductOutOfStock("Product Out of Stock");
		}
		
		if(Cart.size()==0) {
			Cart.add(pointer, obj);
			CartQ.add(pointer, Quantity);
			pointer++;
		}
		else {
		for(int u=0;u<Cart.size();u++) {
			
			if(Cart.get(u).getName().compareTo(product)==0) {
				CartQ.set(u, CartQ.get(u)+Quantity);
				break;
			}
			else if(u==Cart.size()-1) {
				Cart.add(pointer, obj);
				CartQ.add(pointer, Quantity);
				pointer++;
				break;
			}
		}
		}
		
		
	}
	
	protected  final void CheckOut() throws InsufficientFunds, ProductOutOfStock, ProductNotFound {
		int amount=0;
		int funds=Funds;
		for(int u=0;u<pointer;u++) {
			int a=data.Sale(Cart.get(u), CartQ.get(u), funds);
			amount+=a;
			funds-=a;
		}
		System.out.println();
		System.out.println("Total Amount: " + amount);
		Funds-=amount;
		ClearCart();
		
	}
	
	public Database getData() {
		return data;
	}
	
	public void ClearCart() {
		int u=Cart.size()-1;
		while(Cart.size()!=0) {
			Cart.remove(u);
			CartQ.remove(u);
			u--;
		}
		pointer=0;
		
	}
	
	
	
	
}
