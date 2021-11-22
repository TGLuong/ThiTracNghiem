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
@XmlRootElement(name="sinhvien")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    private String id_user;
    private String name_user;
    private String birthday;
    private String id_class;
    private String password;
    private String id_department;
    
    
    public User(){}
    public User(String id_user,String password)
    {
        this.id_user=id_user;
        this.password=password;
    }
    public User(String id_user, String name_user, String birthday, String id_class,String id_department) {
        this.id_user = id_user;
        this.name_user = name_user;
        this.birthday = birthday;
        this.id_class = id_class;
        this.id_department=id_department;
    }
    public User(String id_user, String name_user, String birthday, String id_class,String id_department,String password) {
        this.id_user = id_user;
        this.name_user = name_user;
        this.birthday = birthday;
        this.id_class = id_class;
        this.password=password;
        this.id_department=id_department;
        
    }
    public boolean login()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loginQuery());
            if(resultSet.next())
            {
                return true;
            }
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public ArrayList<User> getListUser()
    {
         ArrayList<User> listUser = new ArrayList<User>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getListUserQuery());
            while(resultSet.next())
            {
                User user = new User(resultSet.getString("id_user"),resultSet.getString("name_user"),
                        resultSet.getString("birthday"),resultSet.getString("id_class"),resultSet.getString("id_department"));
                listUser.add(user);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUser;
    }
    
    public User getUser(String id_user)
    {
        User user = new User();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getUserQuery(id_user));
            if(resultSet.next())
            {
                user = new User(resultSet.getString("id_user"),resultSet.getString("name_user"),
                        resultSet.getString("birthday"),resultSet.getString("id_class"),resultSet.getString("id_department"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    public boolean addUser(User user)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute(addUserQuery(user));
            return true;
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    public boolean updateUser(User user)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateUserQuery(user));
            return true;
            
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    public boolean resetPassword(String id_user,String password)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(resertPasswordQuery(id_user,password));
            return true;
            
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    public boolean deleteUser(String iduser)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute(deleteUserQuery(iduser));
            return true;
            
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    // -----------------------   QUERY    -------------------------
    
    
    
    private String loginQuery()
    {
       return "select id_user from users where id_user = '"+id_user+"' and password_user = '"+password+"'";
    }
    private String getListUserQuery()
    {
       return "select top 100 id_user,name_user,birthday,id_class,id_department from users ";
    }
    private String getUserQuery(String id_User)
    {
       return "select id_user,name_user,birthday,id_class,id_department from users "
               + "where id_user='"+id_User+"'";
    }
    private String addUserQuery(User user)
    {
        return "insert into users(id_user,name_user,birthday,id_class,password_user,id_department) "
                + "values ('"+user.id_user+"',N'"+user.name_user+"','"+user.birthday+"','"+user.getId_class()+"','"+user.password+"','"+user.getId_department()+"')";
    }
    private String updateUserQuery(User user)
    {
        return "update users set name_user = N'"+user.name_user+"' ,birthday = '"+user.birthday+"'"
                + ",id_class ='"+user.getId_class()+"',id_department='"+user.getId_department()+"' where id_user = '"+user.id_user+"'";
    }
    private String resertPasswordQuery(String id_user,String password)
    {
        return "update users set password_user = '"+password+"' where id_user = '"+id_user+"'";
    }
    private String deleteUserQuery(String iduser)
    {
        return "delete from users where id_user='"+iduser+"'";
    }
    
    // ------------------------   END QUERY    -----------------------------
    
    
    

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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


}
