/**
 *Speedrun Database
* Author: Kyler Nelson
* Class: TCSS 445
* Date: 2015-03-13
 */

package test;

import java.sql.SQLException;

import query.Database;
import query.Query;

/**
 * Test class for the queries.
 * @author Kyler
 *
 */
public class QueryTest
{
    public static void main(String[] args) throws SQLException
    {
        Query q = new Query(Database.getConnection());
        if( q.databaseExists() )
        {
            q.dropDatabase();
        }
        q.createDatabase();
        q.useDatabase();
        
        //q.listDatabaseTables();
        q.listNintendoReleases();
    }
}
