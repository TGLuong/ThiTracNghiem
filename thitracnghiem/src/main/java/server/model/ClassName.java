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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.*;

/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="class")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassName {
    private String id_class;
    private String id_department;
    private String classname;
    @XmlTransient
    private Connection connection;
    @XmlTransient
    private Statement statement;
    
    public ClassName()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassName.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClassName.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ClassName(String id_class, String id_department, String classname) {
        this();
        this.id_class = id_class;
        this.id_department = id_department;
        this.classname = classname;
    }
    public ArrayList<ClassName> getListClass()
    {
         ArrayList<ClassName> listClass = new ArrayList<ClassName>();
        try {
            ResultSet resultSet = statement.executeQuery(getListClassQuery());
            while(resultSet.next())
            {
                ClassName cn = new ClassName(resultSet.getString("id_class"),resultSet.getString("id_department"),resultSet.getString("classname"));
                listClass.add(cn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassName.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listClass;
    }
    public ClassName getClass(String id_class,String id_department)
    {
        ClassName className = new ClassName();
        try {
            ResultSet resultSet = statement.executeQuery(getClassQuery(id_class,id_department));
            if(resultSet.next())
            {
                className =new ClassName(resultSet.getString("id_class"),resultSet.getString("id_department"),resultSet.getString("classname"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClassName.class.getName()).log(Level.SEVERE, null, ex);
        }
        return className;
    }
    public boolean addClass(ClassName className)
    {
        try {
            statement.execute(addClassQuery(className));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClassName.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }          
    }
    public boolean updateClass(ClassName cn)
    {
        try {
            statement.executeUpdate(updateClassQuery(cn));
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClassName.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    public boolean deleteClass(String id_class,String id_department)
    {
        try {
            statement.execute(deleteClassQuery(id_class, id_department));
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    
    
    //========================= QUERY ===========================
    
    private String getListClassQuery()
    {
        return "select top 20 * from class";
    }
    private String getClassQuery(String id_class,String id_department)
    {
        return "select * from class where id_class='"+id_class+"' and id_department='"+id_department+"'";
    }
    private String addClassQuery(ClassName className)
    {
        return "insert into class values('"+className.getId_class()+"','"+className.getId_department()+"',N'"+className.getClassname()+"')";
    }
    private String updateClassQuery(ClassName cn)
    {
        return "update class set classname = N'"+cn.getClassname()+"' where id_class='"+cn.getId_class()+"' and id_department='"+cn.getId_department()+"'";
    }
    private String deleteClassQuery(String id_class,String id_department)
    {
        return "delete from class where id_class='"+id_class+"' and id_department='"+id_department+"'";
    }
    
    
    

    public String getId_class() {
        return id_class;
    }

    public void setId_class(String id_class) {
        this.id_class = id_class;
    }

    public String getId_department() {
        return id_department;
    }

    public void setId_department(String id_department) {
        this.id_department = id_department;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
    
    
}
