import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
 
public class InvokeCursorSP {
 
    public static void main(String[] args) {
        String jdbcClassName="com.ibm.db2.jcc.DB2Driver";
        String url="jdbc:db2://192.168.1.161:50000/SAMPLE";
        String user="db2inst1";
        String password="db2inst1-pwd";
        
        Connection connection = null;
        try {
            //Load class into memory
            Class.forName(jdbcClassName);
            //Establish connection
            connection = DriverManager.getConnection(url, user, password);
            CallableStatement call=null;
			   call = connection.prepareCall("{call PRC_CUR_SAMPLE4(1002,?,?)}");
			   call.registerOutParameter(1, Types.CHAR);
			   call.registerOutParameter(2, Types.CHAR);
			   call.execute();
			   
			   String FName = call.getString(1);
			   System.out.println(FName);
			   
			   String LName = call.getString(2);
			   System.out.println(LName);
			   
			   
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(connection!=null){
                System.out.println("Connected successfully.");
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 
    }
 
}