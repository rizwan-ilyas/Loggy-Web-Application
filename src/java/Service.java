
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Service {
    String db,username,password;
    Connection myConnection;

    public Service(String db, String username, String password) throws ClassNotFoundException {
        this.db = db;
        this.username = username;
        this.password = password;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.myConnection = DriverManager.getConnection(db,username,password);
            //this.myConnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tasks", "root","");
            System.out.println("Connection established");
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    public ArrayList<Log> getLogs(){
        try {
            ArrayList<Log> logArray=new ArrayList<Log>();
            Statement stat;
            ResultSet res;
            stat=myConnection.createStatement();
            res=stat.executeQuery("select id,title,content,date_format(timestamp, '%d/%m/%y %h:%m:%s') from logs order by timestamp");
            ResultSetMetaData data=res.getMetaData();
            while (res.next()) {
                Log l=new Log(Integer.parseInt(res.getString(1)) ,res.getString(2), res.getString(3),res.getString(4));
                logArray.add(l);
            }
            
            return logArray;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void addLog(Log l){
        try {
            
            String query="insert into logs (title,content,timestamp) values (?,?,now());";
            //Statement stat=myConnection.createStatement();
            PreparedStatement prepStat=myConnection.prepareStatement(query);
            prepStat.setString(1, l.title);
            prepStat.setString(2, l.logContent);
            prepStat.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex); 
            
        }
    }
    
    public Log getLogById(int id){
           ArrayList<Log> l=this.getLogs();
           for (Log a:l){
               if(a.getId()==id){
                   return a;
               }
           }return null;

    }
    
    
    public boolean deleteLog(String id){
        try {
            String query="DELETE from logs where id=?";
            //Statement stat=myConnection.createStatement();
            PreparedStatement prepStat=myConnection.prepareStatement(query);
            prepStat.setString(1,id);
            prepStat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);      
        }
        return false;
    }
    
    boolean editLog(Log l){
        try {
            String query="UPDATE logs set title=?, content=? where id=?";
            PreparedStatement prepStat=myConnection.prepareStatement(query);
            prepStat.setString(1,l.getTitle());
            prepStat.setString(2,l.getLogContent());
            prepStat.setString(3,String.valueOf(l.getId()) );
            prepStat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);      
        }
        return false;
    }
    
    
    /*
    public static void main(String[] args) throws ClassNotFoundException {
        Service s=new Service("jdbc:mysql://localhost:3306/tasks", "root", "");
        s.addLog(new Log("shdf", "dshjk"));
        
    }*/

    private String toString(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
