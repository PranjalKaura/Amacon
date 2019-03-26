
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;


/** Class for buffered reading int and double values */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
    	
        return Integer.parseInt( next() );
    }
    static float nextFloat() throws IOException{
    	return Float.parseFloat( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

public class MainWindow {
	
	public static ArrayList<Customer> Deserialize() throws IOException, ClassNotFoundException {
		ObjectInputStream	in	=	null;	
		ArrayList<Customer> c3=null;
		try {
			in=new ObjectInputStream(new FileInputStream("OutCustomer.txt"));
			 c3=(ArrayList<Customer>)in.readObject();	
		}
		finally{
			in.close();
		}
		return c3;
		
	}
	
	public static Database DeserializeD() throws IOException, ClassNotFoundException {
		ObjectInputStream	in	=	null;	
		Database c3=null;
		try {
			in=new ObjectInputStream(new FileInputStream("OutDatabase.txt"));
			
			 c3=(Database)in.readObject();	
		}
		finally{
			in.close();
		}
		return c3;
		
	}
	
	public static void Serialize(ArrayList<Customer> c2 ) throws IOException  {
		ObjectOutputStream out	=	null;
		try {
			out=new	ObjectOutputStream	(new FileOutputStream("OutCustomer.txt"));
			out.writeObject(c2);
			
		}
		finally {
			out.close();
		}
		
		
	}
	
	public static void Serialize(Database c2 ) throws IOException  {
		ObjectOutputStream out	=	null;
		
		try {
			out=new	ObjectOutputStream	(new FileOutputStream("OutDatabase.txt"));
			out.writeObject(c2);
			
		}
		finally {
			out.close();
		}
		
		
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, ProductNotFound {
		// TODO Auto-generated method stub
		Reader.init(System.in);
		
		ArrayList<Customer> CustomerList=new ArrayList<Customer>();
		
		int input1=5;
		boolean start=true;
		Database d=new Database();
		while(start) {
			if(input1==0) {
				break;
			}
			int flag=0;
			System.out.println("Press 1 For Administrative Command ");
			System.out.println("Press 2 For Customer Command ");
			System.out.println("Press 0 to Terminate ");
			try {
			input1=Reader.nextInt();
			}
			catch (Exception e) {
				System.out.println("Invalid Input!");
				continue;
			}
			
			while(flag==0) {
				if(input1==1) {
					try {
					d=DeserializeD();
					}
					catch(Exception e) {
						//System.out.println("Error");
					 
					}
					Administrator me=new Administrator("Mr. Administrator",d);
			System.out.println("Hello! " + me.getName());
			
			System.out.println("Please Choose one of the following: ");
			System.out.println("a. Insert product/category\r\n" + 
					"b. Delete product/category\r\n" + 
					"c. Search product\r\n" + 
					"d. Modify product\r\n" + 
					"e. Exit as Administrator");
			String input2=" ";
			try {
			 input2=Reader.next();
			input2=input2.toLowerCase();
			}
			catch (Exception e) {
				System.out.println("Invalid Input!");
			}
			if(input2.equals("a")) {
				try {
				System.out.print("Please Define Path: ");
				String path=Reader.next();
				System.out.print("Please Specify Product Name: ");
				String product=Reader.next();
				System.out.print("Please enter Product Price: ");
				int price=Reader.nextInt();
				System.out.print("Please Enter Prouct Quantity:");
				int Quantity=Reader.nextInt();
				me.getDatabase().Insert(path, product,price,Quantity);
				Serialize(d);
				}
				catch(ProductExists e) {
					System.out.println(e.getMessage()); 
					
				}
				catch (Exception e) {
					System.out.println("Invalid Input!");
				}
			}
			else if(input2.equals("b")) {
						System.out.print("Please Specify Path: ");
						String path=Reader.next();
						try {
						me.getDatabase().Delete(path);
						Serialize(d);
						}
						catch( InvalidPath e) {
							System.out.println(e.getMessage());
						}
					}
			else if(input2.equals("c")) {
				try {
				System.out.print("Please Enter product Name to search: ");
				String product=Reader.next();
				
				me.getDatabase().Search(product,1);
				}
				catch(ProductNotFound e) {
					System.out.println(e.getMessage());
				}
				catch(Exception e) {
					System.out.println("Invalid Input");
				}
			}
			else if(input2.equals("d")) {
				try {
				System.out.print("Please Specify Product Name: ");
				String product=Reader.next();
				System.out.print("Please enter Product Price: ");
				int price=Reader.nextInt();
				System.out.print("Please Enter Prouct Quantity:");
				int Quantity=Reader.nextInt();
				me.getDatabase().Modify(price, Quantity, product);
				Serialize(d);
				}
				catch(ProductNotFound e) {
					System.out.println(e.getMessage());
				}
				catch(Exception e) {
					System.out.println("Invalid Input");
				}
			}
			else if(input2.equals("e")) {
				Serialize(d);
				flag=1;
			}
		}
				
		else if(input1==2) {
			try {
				d=DeserializeD();
				}
				catch(Exception e) {
				}
			System.out.print("Please input your Name: ");
			String Name=Reader.next();
			Customer c1 = new Customer(Name,d);
			try {
			CustomerList=Deserialize();
			}
			catch(Exception e){}
			if(CustomerList.size()==0) {
				CustomerList.add(c1);
			}
			for(int u=0;u<CustomerList.size();u++) {
				if(CustomerList.get(u).getName().compareTo(Name)==0) {
					c1=CustomerList.get(u);
					c1.setDatabase(d);
				}
				else if(u==CustomerList.size()-1) {
					CustomerList.add(c1);
				}
			}
			System.out.println("Hello! " + Name);
			
			String input2=" ";
			while(input2.compareTo("d")!=0) {
				System.out.println("Please Choose one of the following: ");
				System.out.println("a. Add funds \r\n" + 
			"b. Add product to the cart\r\n" + 
			"c. Check-out cart\r\n" + 
			"d. Exit as Customer");
				try {
			 input2=Reader.next();
			input2=input2.toLowerCase();
				}
				catch (Exception e) {
					System.out.println("Invalid Input!");
				}
			if(input2.equals("a")) {
				try {
				System.out.print("Please input the Amount to be Added: ");
				int funds=Reader.nextInt();
				c1.AddFunds(funds);
				}
				catch (Exception e) {
					System.out.println("Invalid Input!");
				}
			}
			else if(input2.equals("b")) {
					try {
						System.out.print("Please Enter Product Name to buy: ");
						String product=Reader.next();
						System.out.print("Please input the Quanity: ");
						int Quantity=Reader.nextInt();
						
						c1.AddProduct(product, Quantity);
						}
					catch(ProductNotFound e) {
						System.out.println(e.getMessage());
					}
					catch(ProductOutOfStock e) {
						System.out.println(e.getMessage());
					}
						catch (Exception e) {
							System.out.println("Invalid Input!");
						}
						
					}
			else if(input2.equals("c")) {
				try {
						c1.CheckOut();
						Serialize(d);
				}
				catch(InsufficientFunds e) {
					System.out.println(e.getMessage());
				}
				catch(ProductOutOfStock e) {
					System.out.println(e.getMessage());
				}
		}
			else if(input2.equals("d")) {
				flag=1;
				System.out.println(c1.GetFunds());
				Serialize(CustomerList);
				Serialize(d);
		}
			}
			
			
		}
		else if(input1==0){
			Database D =new Database();
			try {
				D=DeserializeD();
			}
			catch(Exception e) {}
			System.out.println("Amacon Revenue: " + D.getRevenue());
			break;
		}
		else {
			System.out.println("Invalid Input!Try Again");
		}
			}
	}
		
	}

}
