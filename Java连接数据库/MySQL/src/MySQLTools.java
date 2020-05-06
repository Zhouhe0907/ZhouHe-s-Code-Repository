import java.sql.*;
import java.util.Scanner;

public class MySQLTools
{
    public static Scanner scanner = new Scanner(System.in);
    public static Connection connectWithDataBase = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";//load JDBC drive
    static final String DATABASENAME = "Hero"; //Data-base's name
    static final String USERNAME = "root";//user's name
    static final String PASSWARD = "hao1018113";//user's password
    static String tableName = "英雄信息";//table's name in date-base

    static final String DB_URL = "jdbc:mysql://localhost:3306/"+ DATABASENAME +"?useUnicode=true&characterEncoding=UTF-8";//table's url
  

    /**
     * This method is to get connected with data bank
     */
    public static void getConnection()
    {

        try {
            //JDBC
            Class.forName(JDBC_DRIVER);
            //open interlinkage
            System.out.println("Linking the Date-base named " + DATABASENAME + "...");
            connectWithDataBase = DriverManager.getConnection(DB_URL,USERNAME,PASSWARD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to close the connection between data bank and Java
     */
    public static void closeConnection()
    {
        try {
            connectWithDataBase.close();
            connectWithDataBase = null;
            System.out.println("The Date-base has been closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to query data from data bank and then output data queried on the console
     */
    public static void referFromDataBank()
    {
        getConnection();//get connected with date-base
        Statement statement = null;//create the statement of searching

        try {
            statement = connectWithDataBase.createStatement();
            String sql; //sql查询语法
            sql = "SELECT ID, 英雄名, 职业 ,熟练度 FROM " + tableName ;//从table中查询数据
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next())
            {
                System.out.println("Searching...\n" +
                        "+---------------------------------------------------------+");
            }

            // 展开结果集数据库
            while(resultSet.next())
            {
                // 通过字段检索
                int id  = resultSet.getInt("ID");
                String name = resultSet.getString("英雄名");
                String occupation = resultSet.getString("职业");
                String proficiency = resultSet.getString("熟练度");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print("   英雄名: " + name);
                System.out.print("   职业: " + occupation);
                System.out.print("   熟练度：" + proficiency);
                System.out.print("\n");
            }

            // 完成后关闭
            resultSet.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try{
                if(statement!=null) statement.close();
            }catch(SQLException ignored){ }

            if(connectWithDataBase !=null) closeConnection();
        }
    }

    /**
     * This method is to create a new table if it not exsisted
     */
    public static void createTable()
    {
        getConnection();
        String  exsistOrnot = null;
        Statement statement = null;
        try {
            DatabaseMetaData dbmd=connectWithDataBase.getMetaData();
            statement = connectWithDataBase.createStatement();
            ResultSet exsisted = dbmd.getTables(null, null, tableName, null);

            if (exsisted.next())
            { System.out.println(" the table is exsited"); }
            else
            {
                String sql = null;
                sql = "    CREATE TABLE `" + tableName + "` (\n" +
                    "          `ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "          `英雄名` char(5) NOT NULL DEFAULT '' COMMENT '英雄名字',\n" +
                    "          `职业` char(2) NOT NULL DEFAULT '',\n" +
                    "          `熟练度` int(4) NOT NULL,\n" +
                    "          PRIMARY KEY (`ID`)\n" +
                    "        ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
                statement.executeLargeUpdate(sql);
                System.out.println("Create table sucessfully!！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is to insert new information into the Data-base
     */
    public static void insertIntoDataBank()
    {
        getConnection();

        Statement statement = null;

        try {
            statement = connectWithDataBase.createStatement();
            String sql = null;

            System.out.print("英雄名：");
            String name = scanner.nextLine();

            System.out.print("熟练度：");
            int proficiency = scanner.nextInt();
            scanner.nextLine();

            System.out.print("职业：");
            String occupation = scanner.nextLine();
            sql= "INSERT INTO 英雄信息 VALUES (null,\"" + name + "\",\"" + occupation + "\"," + proficiency + ");";

            statement.executeLargeUpdate(sql);
            System.out.println("Insert sucessfully！");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is to delete the specific information in Data-base
     */
    public static void deleteFromDataBank()
    {
        getConnection();

        Statement statement = null;

        try {
            statement = connectWithDataBase.createStatement();
            String sql = null;

            System.out.print("您想删除的英雄名：");
            String name = scanner.nextLine();

            sql= "DELETE FROM 英雄信息 WHERE 英雄名 = \"" + name + "\";";

            statement.executeLargeUpdate(sql);
            System.out.println("Delete sucessfully！");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is to update the specific information in Data-base
     */
    public static void updateInDataBank()
    {
        getConnection();
        Statement statement = null;

        try {
            statement = connectWithDataBase.createStatement();
            String sql = null;

            System.out.print("您想修改信息的英雄名：");
            String name = scanner.nextLine();
            System.out.print("修改后的英雄名：");
            String update = scanner.nextLine();

            sql= "UPDATE 英雄信息 SET 英雄名 = \"" + update + "\" WHERE 英雄名 = \"" + name + "\";";

            statement.executeLargeUpdate(sql);
            System.out.println("Update sucessfully！");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
