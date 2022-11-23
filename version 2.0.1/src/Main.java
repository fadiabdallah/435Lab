
import java.sql.*;
/**
 *
 * @author Mohammad Ayoub & Sahar Tabatabai & Fadi Abdallah
 * @version 2.2
 */
public class Main {


	static String url = "jdbc:sqlite:Game3/db/" + "test.db";
	static String url1 = "jdbc:sqlite:Game3/db/" + "test1.db";
	@SuppressWarnings("null")
	public static void updateScore(String name, int score) {
		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn == null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
			   Statement stmt =conn.createStatement();
	    		stmt.execute("update  Scores set Score="+score+" where name='"+name+"'");
	    		System.out.println("updated succesduly");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:Game3/db/" + fileName;

        try (Connection conn = DriverManager.getConnection(url);) {
            if (conn == null) {
                @SuppressWarnings("null")
				DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
            Statement stmt =conn.createStatement();
    		ResultSet rs;
    		
    	  stmt.execute("CREATE TABLE Users (   PersonID int,userName varchar(255) Primary Key, password varchar(255),hightScore int  )");
    
    		System.out.println("hello");
    	
    		rs= stmt.executeQuery("select * from Users");
    		while(rs.next())
			{
				int id=rs.getInt("PersonID");
				String name=rs.getString("userNAME");
				String pass=rs.getString("password");
				

				System.out.print(id+" ");
				System.out.print(name+" ");
				System.out.print(pass+" ");

			}
    	
    		

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try (Connection conn = DriverManager.getConnection(url1);) {
            if (conn == null) {
                @SuppressWarnings("null")
				DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
            Statement stmt =conn.createStatement();
    		ResultSet rs;
    		
    	  stmt.execute("CREATE TABLE Users (   PersonID int,userName varchar(255) , password varchar(255),hightScore int  )");
    		System.out.println("hello");
    	
    		
    		rs= stmt.executeQuery("select * from Users");
    		while(rs.next())
			{
				int id=rs.getInt("PersonID");
				String name=rs.getString("userNAME");
				String pass=rs.getString("password");
				

				System.out.print(id+" ");
				System.out.print(name+" ");
				System.out.print(pass+" ");

			}
    	
    		

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * @param args command line arguments
     */
    public static void main(String[] args) {
    	/**
    	 * Connect to a sample database
    	 *
    	 * @param fileName the database file name
    	 */
        createNewDatabase("test.db");
    }
}