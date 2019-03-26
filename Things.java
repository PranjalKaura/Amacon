import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class Things implements Serializable {
	private int Quantity;
	private int Price;
	private final String Name;
	protected Things parent;
	LinkedList<Things> objects=new LinkedList<Things>();
	LinkedList<Things> productsUnder=new LinkedList<Things>();
	public Things(String name) {
		// TODO Auto-generated constructor stub
		Name=name;
		parent=null;
	}
	public  final String getName() {
		return Name;
		
	}
	
	public boolean equals(Object d1) {
		Things d=(Things)d1;
		for(int u=0;u<objects.size();u++) {
			if(!objects.get(u).equals(d.objects.get(u))) {
				return false;
			}
		}

		
		if(Name.compareTo(d.getName())!=0) {
			return false;
		}
		if(Price!=getPrice()) {
			return false;
		}
		if(Quantity!=d.getQuantity()) {
			return false;
		}
		if(objects.size()!=d.objects.size() && productsUnder.size()!=d.productsUnder.size()) {
			return false;
		}
		return true;
		
	}
	
	public  final LinkedList<Things> getObjects() {
		return objects ;
		
	}
	
	public  final LinkedList<Things> getproductsUnder() {
		return productsUnder ;
		
	}
	
	public  final int getQuantity() {
		return Quantity;
		
	}
	
	public  final void AddQuantity2(int number) {
		Quantity+=number;
		
	}
	public  final void AddQuantity(int number) {
		Quantity+=number;
		
	}
	public  final int getPrice() {
		return Price;
		
	}
	
	public  final void ChangePrice(int number) {
		Price=number;
	}
	
	public void setParent(Things t1) {
		parent=t1;
	}
	public  final Things getParent() {
		return parent;
	}
}

final class Product extends Things{
	public  Product(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
}
final class SubCategory extends Things{
	
	public SubCategory(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		
	}
	public void setParent(Things t1) {
		parent=t1;
	}
	
//	public boolean equals(Object d1) {
//		SubCategory d=(SubCategory)d1;
//		if(objects.size()==d.objects.size() && productsUnder.size()==d.productsUnder.size() && getName().equalsIgnoreCase(d.getName())) {
//			return true;
//		}
//		else {
//			return false;
//		}
//		
//	}
	
}
