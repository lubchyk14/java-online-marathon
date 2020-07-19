import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class MyUtils {
    private Connection connection;
    private Statement statement;
    private String schemaName ;
    private static  final String JDBC = "jdbc:mysql://127.0.0.1:3306";
    private static final String username= "root";
    private static  final String password = "16021974Olga";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static MyUtils obj = new MyUtils() ;

    public Connection createConnection() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        connection = DriverManager.getConnection("jdbc:h2:mem:test", "", "");
//        try {
//            Class.forName(driver);
//            connection =  DriverManager.getConnection(JDBC,username,password);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
    public Statement createStatement() throws SQLException {
        statement = connection.createStatement();
        return statement;
    }
    public void closeStatement() throws SQLException {
        statement.close();
    }
    public void createSchema(String schemaName) throws SQLException {
        this.schemaName=schemaName;
        statement.execute("CREATE SCHEMA "+schemaName);
    }
    public void dropSchema() throws SQLException {
        statement.execute("DROP SCHEMA "+schemaName);
    }
    public void useSchema() throws SQLException {
        statement.execute("USE "+schemaName);
    }

//
//    public static void main(String[] args) {
//
//        try {
//            obj.createConnection();
//            obj.createStatement();
//            obj.createSchema("test18");
//            obj.createTableRoles();
//            obj.createTableDirections();
//            obj.createTableProjects();
//            obj.createTableEmployee();
//            obj.insertTableRoles("Developer");
//            obj.insertTableDirections("Java");
//            obj.insertTableProjects("Project1","Java");
//            obj.insertTableEmployee("Liubomyr","Developer","Project1");
//
//
////            obj.dropSchema();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();}
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }
    public void createTableRoles() throws SQLException {
        statement.execute("CREATE TABLE "+schemaName +" .roles (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  roleName VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (id));");
    }
    public void createTableDirections() throws SQLException {
        statement.execute("CREATE TABLE "+schemaName+" .directions (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  directionName VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (id));");
    }
    public void createTableProjects() throws SQLException {
        statement.execute("CREATE TABLE "+schemaName+" .projects (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  projectName VARCHAR(45) NULL,\n" +
                "  directionId INT NULL,\n" +
                "  PRIMARY KEY (id)," +
                "  FOREIGN KEY (directionId) REFERENCES "+schemaName +" .directions(id));");
    }
    public void createTableEmployee() throws SQLException {
        statement.execute("CREATE TABLE "+schemaName+" .employee (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  firstName VARCHAR(45) NULL,\n" +
                "  roleId INT NULL,\n" +
                "  projectId INT NULL,\n" +
                "  PRIMARY KEY (id)," +
                "  FOREIGN  KEY (roleId) REFERENCES "+schemaName +" .roles(id)," +
                "  FOREIGN KEY (projectId) REFERENCES "+schemaName+" .projects(id));");
    }
    public void dropTable(String tableName) throws SQLException {
        statement.execute("DROP TABLE "+schemaName+" ."+tableName+";");
    }
    public void insertTableRoles(String roleName) throws SQLException {
        
        statement.executeUpdate("INSERT INTO "+schemaName+".roles VALUES (DEFAULT ,\'"+roleName+"\');");
//        statement.executeUpdate("INSERT INTO "+schemaName+".roles VALUES (DEFAULT ,'"+roleName.toString()+"');");
    }
    public void insertTableDirections(String directionName) throws SQLException {
        statement.execute("INSERT INTO "+schemaName+".directions VALUES (DEFAULT ,\'"+directionName+"\');");
    }
    
    public void insertTableProjects(String projectName, String directionName) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT id FROM "+schemaName+
                ".directions WHERE directionName= \'"+directionName+"\'");
        int direction = 10;
        if(rs.next()){
            direction = rs.getInt(1);
        }

        statement.execute("INSERT INTO "+schemaName+
                ".projects VALUES (DEFAULT,\'"+projectName+"\',\'"+direction+"\');");
    }
    public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException {
        ResultSet rsRoleId = statement.executeQuery("SELECT id FROM "+schemaName+".roles " +
                " WHERE  roleName= \'"+roleName+"\'");
        rsRoleId.next();
        int roleId = rsRoleId.getInt(1);
        ResultSet rsProjectId = statement.executeQuery("SELECT  id FROM "+schemaName+".projects " +
                " WHERE projectName= \'"+projectName+"\'");
        rsProjectId.next();
        int projectId = rsProjectId.getInt(1);

        statement.execute("INSERT INTO "+schemaName+" .employee VALUES (DEFAULT,\'"+
                firstName+"\',\'"+roleId+"\',\' "+projectId+"\');");

    }
    public int getRoleId(String roleName) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT id  FROM "+schemaName+" .roles WHERE roleName = \'"+roleName+"\';");
        rs.next();
        return rs.getInt(1);
    }
    public int getDirectionId(String directionName) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT id  FROM "+schemaName+
                " .directions WHERE directionName = \'"+directionName+"\';");
        rs.next();
        return rs.getInt(1);
    }
    public int getProjectId(String projectName) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT id  FROM "+schemaName
                +" .projects WHERE projectName = \'"+projectName+"\';");
        rs.next();
        return rs.getInt(1);
    }
    public int getEmployeeId(String firstName) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT id  FROM "+schemaName
                +" .employee WHERE firstName = \'"+firstName+"\';");
        rs.next();
        return rs.getInt(1);
    }
    public List<String> getAllRoles() throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT roleName FROM "+schemaName+
                " .roles ");
        while (rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
    public List<String> getAllDirestion() throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT  directionName FROM "+schemaName+
                " .directions");
        while (rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
    public List<String> getAllProjects() throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT  projectName FROM "+schemaName+
                " .projects");
        while (rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
    public List<String> getAllEmployee() throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT  firstName FROM "+schemaName+
                " .employee");
        while (rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
    public List<String> getAllDevelopers() throws SQLException {
        int roleId = getRoleId("Developer");
        List<String> list = new ArrayList<>();
//        ResultSet rs = statement.executeQuery("SELECT id FROM "+schemaName+
//                " .roles WHERE rollName= \'Developer\'");
//        rs.next();
//        int value = rs.getInt(1);
        ResultSet rs1 = statement.executeQuery("SELECT  firstName FROM "+schemaName+
                " .employee WHERE roleId= \'"+roleId+"\'");
        while (rs1.next()){
            list.add(rs1.getString(1));
        }
        return list;

    }
    public List<String> getAllJavaProjects() throws SQLException {
        List<String > list = new ArrayList<>();
        int directionId = getDirectionId("Java");
        ResultSet rs =statement.executeQuery("SELECT projectName FROM "+schemaName+
                " .projects WHERE directionId= \'"+directionId+"\'");
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
    public List<String> getAllJavaDevelopers() throws SQLException {
        int role = getRoleId("Developer");
        int directionId = getDirectionId("Java");
        List<String> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT employee.firstName \n" +
                "\tFROM "+schemaName+" .employee \n" +
                "    JOIN "+schemaName+".roles\n" +
                "    ON employee.roleId=roles.id\n" +
                "    JOIN "+schemaName+".projects\n" +
                "    ON employee.projectId=projects.id\n" +
                "    WHERE directionId=\'"+directionId+"\' AND roleName = \'Developer\';");
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }

}



