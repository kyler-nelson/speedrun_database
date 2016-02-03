/**
 *Speedrun Database
* Author: Kyler Nelson
* Class: TCSS 445
* Date: 2015-03-13
 */

package query;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * The database handles connecting to a MySql server and importing
 * SQL scripts, notably the one for creating the database.
 * @author Kyler
 *
 */
public class Database
{
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    private static final String DATABASE = "nelson_kyler_db";
    private static Database INSTANCE = new Database();
	
	private Database()
	{
	    try
        {
            Class.forName(MYSQL_DRIVER);
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	/**
	 * Parses and executes the SQL script in a series of statements
	 * @param conn the connection to the database
	 * @param in the inputStream (file to read from)
	 * @throws SQLException
	 */
	public static void importSQL(Connection conn, InputStream in) throws SQLException
	{
        Scanner s = null;
        Statement st = null;
        
	    try
	    {
	        s = new Scanner(in);
	        s.useDelimiter("(;(\r)?\n)|(--\n)");
	        
	        st = conn.createStatement();
	        while (s.hasNext())
	        {
	            String line = s.next();
	            if (line.startsWith("/*!") && line.endsWith("*/"))
	            {
	                int i = line.indexOf(' ');
	                line = line.substring(i + 1, line.length() - " */".length());
	            }

	            if (line.trim().length() > 0)
	            {
	                st.execute(line);
	            }
	        }
	    }
	    finally
	    {
	        s.close();
	    }
	}
	
	/**
	 * @return get a connection with default parameters
	 */
    public static Connection getConnection()
    {
        return getConnection(DB_URL, USER, PASSWORD);
        
    }
    
    /**
     * Attempts to connect to the database with the given information.
     * @param theUrl The server url to connect to
     * @param theUsername the username
     * @param thePassword the password
     * @return
     */
    public Connection createConnection(String theUrl, String theUsername, String thePassword)
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(theUrl, theUsername, thePassword);
        } catch (SQLException e) {
            System.out.println("Unable to connect to database: url=" + theUrl 
                                                          + ", user=" + theUsername
                                                          + ", pass=" + thePassword);
        }
        return conn;
    }
    
    /**
     * Factory method of creating a connection with an instance of Database.
     * This connection will be used in the Query class.
     * @param theUrl The server url to connect to
     * @param theUsername the username
     * @param thePassword the password
     * @return the conneciton
     */
    public static Connection getConnection(String theUrl, String theUsername, String thePassword)
    {
        return INSTANCE.createConnection(theUrl, theUsername, thePassword);
    }
    
    /**
     * The name of the database created.
     * @return the namess
     */
    public static String getName()
    {
        return DATABASE;
    }
}
