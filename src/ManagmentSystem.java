

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ManagmentSystem{
private static Connection conn;
private static ManagmentSystem instance;
private FileInputStream fin;
private Properties ht;
private static String URL="";
private static String USERNAME="";
private static  String PASSWORD="";
private static String DB_NAME="";
private static String DRIVER="";

private ManagmentSystem(String pathToConfig){
ht=new Properties();
try{
fin=new FileInputStream(pathToConfig);
if(fin!=null){
ht.load(fin);
fin.close();
}

}catch(FileNotFoundException e){
System.out.println("Ne mogu naiti conf fail ["+e+"]");
}
catch(IOException e){
System.out.println("Oshibka chteniya conf faila ["+e+"]");
}
URL=(String)ht.get("url");
DB_NAME=(String)ht.get("db_name");
USERNAME=(String)ht.get("username");
PASSWORD="";
DRIVER=(String)ht.get("driver");


try{
Class.forName (DRIVER).newInstance ();

}catch(Exception ex){
System.out.println("Proizoshla oshibka pri sozdanii draivera");
System.out.println(ex);
return;
}
}
public static synchronized ManagmentSystem getInstance(String pathToConfig) throws Exception{
        if(instance == null) {
            instance = new ManagmentSystem(pathToConfig);
        }
        return instance;
    }
public static Connection getConn(){

 try{conn=DriverManager.getConnection(URL+DB_NAME,USERNAME,PASSWORD);

}catch(SQLException ex){
    ex.printStackTrace();
}
return conn;
}
}
