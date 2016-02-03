/**
 *Speedrun Database
* Author: Kyler Nelson
* Class: TCSS 445
* Date: 2015-03-13
 */

package query;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Queries are executed from a given connection provided by the
 * Database for a variety of sample queries.
 * @author Kyler
 *
 */
public class Query
{
    private static Connection conn;
    
    /**
     * The query requires a connection and the executes statements.
     * @param theConnection the database connection
     * @throws SQLException
     */
    public Query(Connection theConnection) throws SQLException
    {
        conn = theConnection;
    }
    
    /**
     * Queries whether nelson_kyler_db exists in the given server
     * @return
     * @throws SQLException
     */
    public boolean databaseExists() throws SQLException
    {
        ResultSet rs = conn.createStatement().executeQuery("SELECT SCHEMA_NAME "
                + " FROM INFORMATION_SCHEMA.SCHEMATA "
                + " WHERE SCHEMA_NAME = '"+ Database.getName() + "'");
        return rs.next();
    }
    
    /**
     * Creates a database with the SQL script packaged in scripts.
     */
    public void createDatabase()
    {
        File sqlCreateDatabase = new File("scripts/nelson_kyler_backend.sql");
        
        try
        {
            Database.importSQL(conn, new FileInputStream(sqlCreateDatabase));
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Tells the server to use our database
     * @throws SQLException
     */
    public void useDatabase() throws SQLException
    {
        conn.createStatement().execute("use " + Database.getName());
    }
    
    /**
     * Drops our database.
     * @throws SQLException
     */
    public void dropDatabase() throws SQLException
    {
        conn.createStatement().execute("drop database " + Database.getName());
    }
    
    /**
     * Generates a list of tables in the current database.
     * @throws SQLException
     */
    public void listDatabaseTables() throws SQLException
    {
        ResultSet rs = conn.createStatement().executeQuery(
                "SELECT table_name"
                + " FROM information_schema.tables"
                + " WHERE table_schema = '" + Database.getName() + "'"
                + " ORDER BY table_name;");
        
        printResultSet(rs);
    }
    
    public void listNintendoReleases() throws SQLException
    {
        ResultSet rs = conn.createStatement().executeQuery(
                "select games.title, games.developer"
                + " from games"
                + " where games.developer = 'Nintendo EAD';");
        
        printResultSet(rs);
    }
    
    /**
     * Lists all the runs in the runs table.
     * @param theGame the game to match
     * @param limit the rows to limit
     * @throws SQLException
     */
    public void listRuns(String theGame, int limit) throws SQLException
    {
        System.out.println("Querying for list of runs(limit "+limit+")...");
        String limitResults = (limit>0)? " LIMIT "+limit+";":";";
        String query = "select players.name, games.title, releases.platform, releases.region, category, runDuration"
                + " from runs natural join players"
                + "           natural join releases"
                + "           natural join games"
                + " where games.title = '"+theGame+"'"
                + " order by runDuration desc"
                + limitResults;
        ResultSet rs = conn.createStatement().executeQuery(query);
        
        printResultSet(rs);
    }
    
    /**
     * Lists the average run duration for all games for a given category.
     * Games without the caategory are excluded. 
     * @param theCategory
     * @throws SQLException
     */
    public void listAverageRunDurationForCategory(String theCategory) throws SQLException
    {
        String query = "select games.title, runs.category, ROUND(AVG(runDuration), 2) as 'averageTime (s)'"
                + " FROM runs natural join releases"
                + "           natural join games"
                + " WHERE runs.category = '"+theCategory+"'"
                + " group by games.title, runs.category;";
        ResultSet rs = conn.createStatement().executeQuery(query);
        
        printResultSet(rs);
    }
    
    /**
     * List the best time for a given game. Categories and regions are ignored.
     * @param theGame
     * @throws SQLException
     */
    public void listWorldRecord(String theGame) throws SQLException
    {
        listRuns(theGame, 1);
    }
    

    public void listAverageRunDurationAllGames() throws SQLException
    {
        System.out.println("Querying for average run duration of all releases...");
        ResultSet rs = conn.createStatement().executeQuery(
                "select games.title, releases.platform, releases.region, ROUND(AVG(runDuration), 2) as 'averageTime (s)'"
                + " from runs natural join releases"
                + "           natural join games"
                + " group by runs.releaseId;");
        
        printResultSet(rs);
    }
    
    /**
     * Lists every category a player has a run in and the best run time
     * the player has in the category, called personal bests.
     * @param thePlayer
     * @throws SQLException
     */
    public void listPersonalBests(String thePlayer) throws SQLException
    {
        System.out.println("Querying for personal bests of "+thePlayer+"...");
        ResultSet rs = conn.createStatement().executeQuery(
                "select players.name, games.title, releases.platform, releases.region, category,"
                + "     MIN(runDuration) as personalBest, completionDate"
                + " from runs natural join players"
                + "           natural join releases"
                + "           natural join games"
                + " WHERE players.name = '"+thePlayer+"'"
                + " group by playerId, releaseId, category;");
        
        printResultSet(rs);
    }
    
    /**
     * Prints the results of the table in a primitive JOPtionPane because the
     * programmer is lazy.
     * @param rs the result set to print
     * @throws SQLException
     */
    public void printResultSet(ResultSet rs) throws SQLException
    {
        System.out.println(">Printing results of the query...");
        ResultSetMetaData md = rs.getMetaData();
        int i = 1;
        int count = md.getColumnCount();
        String result = "/# ";
        
        while( i <= count )
        {
            result += md.getColumnLabel(i) + " | ";
            i++;
        }
        
        while( rs.next() )
        {
            i = 1;
            
            result += "\n# ";
            while( i <= count )
            {
                result += rs.getObject(i) + " | ";
                i++;
            }
        }
        result += "\n#/";
        
        JOptionPane.showMessageDialog(null, result, "Query results",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
