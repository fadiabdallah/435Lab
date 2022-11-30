
import java.sql.*;
/**
 *
 * @author Mohammad Ayoub & Sahar Tabatabaie & Fadi Abdallah
 */
public class Main {


	static String url = "jdbc:sqlite:./db/" + "test.db";
	static String url1 = "jdbc:sqlite:./db/" + "test1.db";
	@SuppressWarnings("null")
	public static void updateScore(String name, int score) {
		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn == null) {
                DatabaseMetaData meta = conn.getMetaData();
      
            }
			   Statement stmt =conn.createStatement();
	    		stmt.execute("update  Scores set Score="+score+" where name='"+name+"'");
	    		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:./db/" + fileName;

        try (Connection conn = DriverManager.getConnection(url);) {
            if (conn == null) {
                @SuppressWarnings("null")
				DatabaseMetaData meta = conn.getMetaData();
               
            }
            Statement stmt =conn.createStatement();
    		ResultSet rs;
    	
    	  stmt.execute("CREATE TABLE Users (   PersonID int,userName varchar(255) Primary Key, password varchar(255),hightScore int  )");
    	
    		
    		rs= stmt.executeQuery("select * from Users");
    		while(rs.next())
			{
				int id=rs.getInt("PersonID");
				String name=rs.getString("userNAME");
				String pass=rs.getString("password");
				


			}
    	
    		

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try (Connection conn = DriverManager.getConnection(url1);) {
            if (conn == null) {
                @SuppressWarnings("null")
				DatabaseMetaData meta = conn.getMetaData();
         
            }
            Statement stmt =conn.createStatement();
    		ResultSet rs;
    		
    	  stmt.execute("CREATE TABLE Users (   PersonID int,userName varchar(255) , password varchar(255),hightScore int  )");
    		
    	
    	  
    	  
    		
    		rs= stmt.executeQuery("select * from Users");
    		while(rs.next())
			{
				int id=rs.getInt("PersonID");
				String name=rs.getString("userNAME");
				String pass=rs.getString("password");
				


			}
    	
    		

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * @param args the command line arguments
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