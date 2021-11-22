/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;
import Tool.DBConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.*;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {
    private String id_subject;
    private String name_subject;
    public Subject(){}
    public Subject(String id_subject)
    {
        this();
        this.id_subject=id_subject;
    }
    public Subject(String id_subject,String name_subject)
    {
        this(id_subject);
        this.name_subject=name_subject;
    }
    
    public ArrayList<Subject> getListSubject()
    {
         ArrayList<Subject> listSubject = new ArrayList<Subject>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getListSubjectQuery());
            while(resultSet.next())
            {
                Subject subject = new Subject(resultSet.getString("id_subject"),resultSet.getString("name_subject"));
                listSubject.add(subject);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSubject;
    }
    
    public Subject getSubject(String id_subject)
    {
        Subject subject = new Subject();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getSubjectQuery(id_subject));
            if(resultSet.next())
            {
                subject = new Subject(resultSet.getString("id_subject"),resultSet.getString("name_subject"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subject;
    }
    public boolean addSubject(Subject subject)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute(addSubjectQuery(subject));
            return true;
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    public boolean updateSubject(Subject subject)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateSubjectQuery(subject));
            return true;
            
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    public boolean deleteSubject(String id_subject)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute(deleteSubjectQuery(id_subject));
            SQLWarning sw = statement.getWarnings();
            return true;
            
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    
    
    //------------------ QUERY  -----------------
    private String getListSubjectQuery()
    {
       return "select id_subject,name_subject from subjects ";
    }
    private String getSubjectQuery(String id_subject)
    {
       return "select id_subject,name_subject from subjects "
               + "where id_subject='"+id_subject+"'";
    }
    private String addSubjectQuery(Subject subject)
    {
        return "insert into subjects(id_subject,name_subject) "
                + "values ('"+subject.id_subject+"',N'"+subject.name_subject+"')";
    }
    private String updateSubjectQuery(Subject subject)
    {
        return "update subjects set name_subject = N'"+subject.name_subject+
                "' where id_subject = '"+subject.id_subject+"'";
    }
    private String deleteSubjectQuery(String id_subject)
    {
        return "delete from subjects where id_subject='"+id_subject+"'";
    }
    
    
    
    
    
    
    
    
    
    
    // ----------------  END QUERY   --------------------
    
    
    
    
    public String getId_subject() {
        return id_subject;
    }

    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }
    
    
    
    
}
