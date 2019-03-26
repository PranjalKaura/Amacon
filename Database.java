import java.awt.Robot;
import java.io.Serializable;

public class Database implements Serializable{
	private  SubCategory Amacon=new SubCategory("Root");
	private  int totalRev=0;
	
	public boolean equals(Object d) {
		Database d1=(Database)d;
		if(Amacon.equals(d1.getRoot())) {
			return true;
		}
		else {
			return false;
		}
	}
//	
	public SubCategory getRoot() {
		return Amacon;
	}
	protected  final void Insert(String path,String product,int Price, int Quantity) throws ProductExists {
		String[] arrPath=path.split(">");
		Things obj=Amacon;
		obj.setParent(null);
		for(int u=0;u<obj.getproductsUnder().size();u++) {
			if(obj.getproductsUnder().get(u).getName().compareTo(product)==0) {
				throw new ProductExists("ProductExists!");
			}
			
		}
		for(int h=0;h<arrPath.length;h++) {
			int flag=0;
			
			for(int u=0;u<obj.objects.size();u++) {
				if(obj.getObjects().get(u).getName().compareTo(arrPath[h])==0) {
					obj= obj.objects.get(u);
					flag=1;
					break;
				}
			}
			if(flag==0) {
			SubCategory objinsert=new SubCategory(arrPath[h]);
			//System.out.println(objinsert.Name);
			obj.getObjects().add(objinsert);
			objinsert.setParent(obj);
			obj=objinsert;
			
			}
			
		}
		for(int u=0;u<obj.getObjects().size();u++) {
			if(obj.getObjects().get(u).getName().compareTo((product))==0) {
				System.out.println("Sorry, Product Already Exists");
				return;
			}
		}
		Product prod=new Product(product);
		prod.ChangePrice(Price);
		prod.AddQuantity(Quantity);
		
			obj.getObjects().add(prod);
			prod.setParent(obj);
		Things transverse=prod;
		while(transverse!=null) {
			transverse.getproductsUnder().add(prod);
			transverse=transverse.parent;
		}
		
	}
	
	
	protected  final void Delete(String path) throws InvalidPath {
		String[] arrPath=path.split(">");
		Things obj=Amacon;
		Things obj3=Amacon;
		for(int u=0;u<arrPath.length;u++) {
			if(u==0 && obj.getObjects().size()==0) {
				throw new InvalidPath("InvalidPath");
			}
			for(int h=0;h<obj.getObjects().size();h++) {
				if(obj.getObjects().get(h).getName().compareTo(arrPath[u])==0) {
					if(u==arrPath.length-1) {
						
						Things obj2=obj.getObjects().get(h);
						for(int h1=0;h1<obj.getObjects().get(h).getproductsUnder().size();h1++) {
							obj3.getproductsUnder().remove(obj.getObjects().get(h).getproductsUnder().get(h1));
						}
					obj.getObjects().remove(h);
					if(obj2.getClass()==Product.class) {
						
						obj3.productsUnder.remove(obj2);
					}
					break;
					}
					obj=obj.getObjects().get(h);
					break;
				}
				else if(h==obj.getObjects().size()-1) {
					throw new InvalidPath("InvalidPath");
				}
			}
		}
			
}
		
	
	
	
	protected final Things Search(String product,int Flag) throws ProductNotFound {
		Things tranverse=Amacon;
		String[] arrP=new String[100];
		int counter=0;
		int flag2=0;
		for(int u=0;u<tranverse.productsUnder.size();u++) {
			if(tranverse.getproductsUnder().get(u).getName().compareTo(product)==0) {
				flag2=1;
				break;
			}
			
		}
		if(flag2==0) {
			throw new ProductNotFound("Product Not Found");
		}
		
		while(tranverse.getName().compareTo(product)!=0) {
			for(int u=0;u<tranverse.getObjects().size();u++) {
				int flag=0;
				Things tranverse2=tranverse.objects.get(u);
				if(tranverse2.getName().compareTo(product)==0) {
					tranverse=tranverse2;
				}
				for(int g=0;g<tranverse2.productsUnder.size();g++) {
					if(tranverse2.getproductsUnder().get(g).getName().compareTo(product)==0) {
						tranverse=tranverse2;
						arrP[counter]=tranverse.getName();
						counter++;
						flag=1;
						break;
					}
				}
				if(flag==1) {
					break;
				}
			}
		}
		if(Flag==1) {
		System.out.print("PATH:  ");
		for(int h=0;h<counter;h++) {
			System.out.print(arrP[h] + " ");
		}
		System.out.println(); 
		System.out.println("Name: " + tranverse.getName());
		System.out.println("Price: " + tranverse.getPrice());
		System.out.println("Quantity: " + tranverse.getQuantity());
		}
		return tranverse;
	}
	
	protected  final void Modify(int price, int number,String product) throws ProductNotFound {
		Things obj=Search(product,0);
			if(obj!=null) {
			obj.ChangePrice(price);
			obj.AddQuantity(number);
			}
		
	}
	public final  int getRevenue() {
		return totalRev;
	}
	
	
	protected  int Sale(Things product, int Quantity, int Customerfunds) throws InsufficientFunds,ProductOutOfStock, ProductNotFound {

		
		int AmountDue=Quantity*product.getPrice();
		System.out.println("Product: " + product.getName());
		System.out.println("Quantity: " + Quantity);
		System.out.println("Price: "+ product.getPrice());
		System.out.println("Amount Due: " + AmountDue);
		if(AmountDue>Customerfunds)  {
			throw new InsufficientFunds("Sorry, you have Insufficient Funds");
			
		}
		else if(Quantity>product.getQuantity()) {
			throw new ProductOutOfStock(product.getName() + " is out of stock");
		}
		else {
			product.AddQuantity2(-Quantity);
			totalRev+=AmountDue;
			return AmountDue;
		}
	}
	

	
}
