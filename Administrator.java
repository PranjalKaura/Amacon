import java.io.Serializable;

public final class Administrator implements Serializable{	
	private final String Name;
	private final Database data;
	public Administrator(String name,Database data ) {
		// TODO Auto-generated constructor stub
		Name=name;
		this.data=data;
	}
	
	public String getName() {
		return Name;
	}
	
	public Database getDatabase() {
		return data;
	}

}
