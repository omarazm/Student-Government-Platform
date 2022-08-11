//Omar Abouelazm
//Period 3 
/*This class allows you to access the database and insert, 
update, delete, and view data from it*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JavaSqlAccess
{
    String dbName;
    Connection dbConn;
    ArrayList <ArrayList<String>> data;
    
    public JavaSqlAccess()
    {
        dbName = "";
        dbConn = null;
        data = null;
    }
    public JavaSqlAccess(String dbName)
    {
        setDbName(dbName);
        this.setDbConn();
        data = null;
    }
    
    //Sets database name
    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }
    
    /*
    Create connection URL
    Create Class.forName
    Connect to driver
    */
    
    //Sets database connection
    public void setDbConn()
    {
        String connectionURL = "jdbc:mysql://localhost:3306/" + this.dbName;
        this.dbConn = null;
        
        //find the driver and make connection
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           this.dbConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
        }
        catch (ClassNotFoundException cnfe)
        {
           System.out.println("Class for name not found!");
           cnfe.printStackTrace(System.err);
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection Error!");
            se.printStackTrace(System.err);
        }
    }
    //Sets data
    public void setData(ArrayList<ArrayList<String>> data)
    {
        this.data = data;
    }
    //gets database name
    public String getDbName()
    {
        return this.dbName;
    }
    //gets database connection
    public Connection getDbConn()
    {
        return this.dbConn;
    }
    
    /*
    Obtain tableName
    Obtain tableHeaders
    
    Create query of SELECT
    
    try
        executingQuery
        
        while (there are rows of data)
            Obtain data from row
    catch
        Error
    
    return data
    */
    
    //gets data from database
    public ArrayList<ArrayList<String>> getData(String tableName, String[] tableHeaders)
    {
        int columnCount = tableHeaders.length;
        Statement s = null;
        ResultSet rs = null;
        String dbQuery = "SELECT * FROM " + tableName;
        this.data = new ArrayList<>();
        
        //Reads data
        try
        {
            //Send the query and recieve data
            s = this.dbConn.createStatement();
            rs = s.executeQuery(dbQuery);
            
            //Read the data using rs and store in ArrayList Data
            while (rs.next()) 
            {
                ArrayList<String> row = new ArrayList<>();
                for (int i=0; i<columnCount; i++)
                {
                    row.add(rs.getString(tableHeaders[i]));
                }
                this.data.add(row);
            }
        }
        catch (SQLException se)
        {
              System.out.println("SQL Error: Not able to get data");
              se.printStackTrace(System.err);
        }
        return this.data;
    }
    
    //gets data from database for a specific house
    public ArrayList<ArrayList<String>> getHouseData(String tableName, String[] tableHeaders, String houseType)
    {
        int columnCount = tableHeaders.length;
        Statement s = null;
        ResultSet rs = null;
        String dbQuery = "SELECT * FROM " + tableName + " WHERE HouseType = ?";
        this.data = new ArrayList<>();
        
        //Reads data
        try
        {
            PreparedStatement ps = dbConn.prepareStatement(dbQuery);
            
            ps.setString(1, houseType);
            
            //Send the query and recieve data
            rs = ps.executeQuery();
            
            //Read the data using rs and store in ArrayList Data
            while (rs.next()) 
            {
                ArrayList<String> row = new ArrayList<>();
                for (int i=0; i<columnCount; i++)
                {
                    row.add(rs.getString(tableHeaders[i]));
                }
                this.data.add(row);
            }
        }
        catch (SQLException se)
        {
              System.out.println("SQL Error: Not able to get data");
              se.printStackTrace(System.err);
        }
        return this.data;
    }
    
    /*
    Obtain databaseName
    
    Set databaseName
    Create connection URL w createDatabase = trueß
    
    try
        Connecting to driver
    catch 
        Error
    */
    
    //Creates database
    public void createDb(String newDbName)
    {
        setDbName(newDbName);
        String connectionURL = "jdbc:mysql://localhost:3306/";
        this.dbConn = null;
        
        //find the driver and make connection
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           this.dbConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
           Statement s = dbConn.createStatement();
           int result = s.executeUpdate("Create DATABASE " + newDbName);
           
           System.out.println("New Database " + this.dbName + " created!");
           this.dbConn.close();
        }
        catch (ClassNotFoundException cnfe)
        {
           System.out.println("Class for name not found!");
           cnfe.printStackTrace(System.err);
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection Error!");
            se.printStackTrace(System.err);
        }
    }
    @Override
    public String toString()
    {
        return "JavaDatabase{" + "dbName=" + dbName + ", dbConn=" + 
                dbConn + ", data=" + data + '}';
    }
        
    /*
    Obtain tableName and databaseName
    
    Set databaseName
    Set databaseConnection
    
    try
        Create new Table
    catch 
        Error
    */
    
    //Creates table in database
    public void createTable(String newTable, String dName)
    {
        System.out.println(newTable);
        setDbName(dbName);
        setDbConn();
        Statement s;
        
        try
        {
            s = this.dbConn.createStatement();
            s.execute(newTable);
            System.out.println("New Table Created!");
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection Error!");
            se.printStackTrace(System.err);
        }
    }
    //Closes database connection
    public void closeDbConn()
    {
        try
        {
            this.dbConn.close();
        }
        catch (Exception err)
        {
            System.out.println("DB closing error.");
            System.exit(0);
        }
    }
    
    /*
    Obtain data
    
    Obtain columnCount from data
    create dataList with dataSize and columnCount
    
    for(dataSize is less than maxDataSize)
        set row = data
        
        for(columnCount is less than maxColumnCount)
        set dataList = row
    
    return dataList
    */
    
    //Converts data to 2d array
    public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
    {
        int columnCount = data.get(0).size();
        Object[][] dataList = new Object[data.size()][columnCount];
        
        for (int i=0; i<data.size(); i++)
        {
            ArrayList<String> row = data.get(i);
            
            for (int j=0; j<columnCount; j++)
            {
                dataList[i][j] = row.get(j);
            }
        }  
        
        return dataList;
    }
    
    //Declares main method of database access
    public static void main(String[] args)
    {
        String dbName = "VillageHousesDb";

        //Creates object of database access
        String[] columnName = {"HouseType", "AnnouncementId", "AnnouncementTitle", "Announcement"};
        JavaSqlAccess objDb2 = new JavaSqlAccess(dbName);
        
        //gets data
        ArrayList<ArrayList<String>> myData = objDb2.getHouseData("Announcements", columnName, "Dragon");
        
        //converts data to 2d array
        Object[][] testData = objDb2.to2dArray(myData);
             
        //displays data
        for (int i=0; i<testData.length; i++)
        {
            for (int j=0; j<testData[i].length; j++)
            {
                System.out.println(testData[i][j]);
            }
        }
    }
}

