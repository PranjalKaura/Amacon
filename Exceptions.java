

public	class	Exceptions	extends	Exception	{	
	public	Exceptions(String	message)	{	
					super(message);	
	}	
}

class ProductExists extends Exception{
	public ProductExists(String message) {
		super(message);
	}
}

class ProductNotFound extends Exception{
	public ProductNotFound(String message) {
		super(message);
	}
}

class ProductOutOfStock extends Exception{
	public ProductOutOfStock(String message) {
		super(message);
	}
}

class InsufficientFunds extends Exception{
	public InsufficientFunds(String message) {
		super(message);
	}
}

class InvalidPath extends Exception{
	public InvalidPath(String message) {
		super(message);
	}
}

