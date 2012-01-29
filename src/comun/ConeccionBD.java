package comun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConeccionBD {
	static Connection con;
	static String database,username, password;	
	
	
	public ConeccionBD() {
		// TODO Auto-generated constructor stub
		database = "oldfundalara";
		username = "postgres";
		password = "123456";
		System.out.println("Checking if Driver is registered with DriverManager.");
		try {
		    Class.forName("org.postgresql.Driver");
		  } catch (ClassNotFoundException cnfe) {
		    System.out.println("Couldn't find the driver!");
		    System.exit(1);
		  }
		  
		  con = null;
		  
		  try {
		    // The second and third arguments are the username and password,
		    // respectively. They should be whatever is necessary to connect
		    // to the database.
		    con = DriverManager.getConnection("jdbc:postgresql://localhost/"+database,
		                                    username,password );
		  } catch (SQLException se) {
		   
		    se.printStackTrace();
		    System.exit(1);
		  }
	}



	public static Connection getCon() throws SQLException {		
		database = "oldfundalara";
		username = "postgres";
		password = "123456";
		try {
		    Class.forName("org.postgresql.Driver");
		  } catch (ClassNotFoundException cnfe) {
		    System.out.println("Couldn't find the driver!");
		    System.exit(1);
		  }
		con = DriverManager.getConnection("jdbc:postgresql://localhost/"+database,
		                                    username,password );
		return con;
	}
	
	public static Connection getCon(String db,String user,String pass) throws SQLException {		
		
		try {
		    Class.forName("org.postgresql.Driver");
		  } catch (ClassNotFoundException cnfe) {
		    System.out.println("Couldn't find the driver!");
		    System.exit(1);
		  }
		con = DriverManager.getConnection("jdbc:postgresql://localhost/"+db.toString(),
		                                    user.toString(),pass.toString() );
		return con;
	}


	public static void setCon(Connection con) {
		ConeccionBD.con = con;
	}
	



	public String getDatabase() {
		return database;
	}



	public void setDatabase(String database) {
		this.database = database;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	  	
	  

}
