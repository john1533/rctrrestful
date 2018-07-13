package me.fourtween.ms.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class ConnectionManager
{

    private static com.jolbox.bonecp.BoneCP boneCPConnectionPool = null;

    public static com.jolbox.bonecp.BoneCP getConnectionPool()
    {
        return boneCPConnectionPool;
    }

    public static void setConnectionPool(com.jolbox.bonecp.BoneCP connectionPool)
    {
        ConnectionManager.boneCPConnectionPool = connectionPool;
    }

    public static void configureBoneCPConnectionPool()
    {
        try
        {
            /*
             * load the database driver (make sure this is in your classpath!)
             */
            Class.forName("com.mysql.jdbc.Driver");

            /*
             * setup the connection pool
             */

            BoneCPConfig boneCPConfig = new BoneCPConfig();
            boneCPConfig.setJdbcUrl("jdbc:mysql://localhost:3306/marksix?useUnicode=true&characterEncoding=UTF-8");

            boneCPConfig.setUsername("root");
            boneCPConfig.setPassword("123456");
            boneCPConfig.setPartitionCount(3);
            boneCPConfig.setMinConnectionsPerPartition(5);
            boneCPConfig.setMaxConnectionsPerPartition(10);
            boneCPConfig.setPartitionCount(1);
            boneCPConfig.setDisableConnectionTracking(true);
            boneCPConnectionPool = new com.jolbox.bonecp.BoneCP(boneCPConfig);

            setConnectionPool(boneCPConnectionPool);

            System.out.println("contextInitialized.....Connection Pooling is configured");

        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }

    }

    public static void shutdownBoneCPConnectionPool()
    {

        try
        {
            com.jolbox.bonecp.BoneCP connectionPool = ConnectionManager
                    .getConnectionPool();
            if (connectionPool != null)
            {
                /*
                 * This method must be called only once when the application
                 * stops. you don't need to call it every time when you get a
                 * connection from the Connection Pool
                 */

                connectionPool.shutdown();
                System.out.println("contextDestroyed.....Connection Pooling shut downed!");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public static Connection getConnection() {

    	Connection conn = null;
    	try {
    	 conn = getConnectionPool().getConnection();
    	//will get a thread-safe connection from the BoneCP connection pool.
    	//synchronization of the method will be done inside BoneCP source

    	} catch (Exception e) {
    	e.printStackTrace();
    	}
    	return conn;

    	}

    	public static void closeStatement(Statement stmt) {
	    	try {
	    	if (stmt != null)
	    	stmt.close();
	    	} catch (Exception e) {
	    	e.printStackTrace();
	    	}

    	}

    	public static void closeResultSet(ResultSet rSet) {
    	try {
    	if (rSet != null)
    	rSet.close();
    	} catch (Exception e) {
    	e.printStackTrace();
    	}

    	}

    	public static void closeConnection(Connection conn) {
    	try {
    	if (conn != null)
    	conn.close(); //release the connection - the name is tricky but connection is not closed it is released
    	   //and it will stay in pool
    	} catch (SQLException e) {
    	e.printStackTrace();
    	}

    	}

    	

}
