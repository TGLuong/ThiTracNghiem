/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;


 
import javax.xml.bind.annotation.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;
import Tool.DBConfig;

 
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAdmin {
    @XmlElement(name="user_name")
    private String userName;
    @XmlTransient
    private String passWord;
    private String cookie;
    @XmlTransient
    private Statement statement;
    @XmlTransient
    private Connection connection;
    //Constructor
    public UserAdmin()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public UserAdmin(String cookie)
    {
        this();
        setCookie(cookie);
    }
    public UserAdmin(String userName, String passWord) {
        this();
        this.userName = userName;
        this.passWord = passWord;
    }
    
    
    
    
    public boolean login()
    {
        try {
            ResultSet resultSet = statement.executeQuery(getUserQuery(getUserName(),getPassWord()));
            if(resultSet.next())
            {
                cookie = UUID.randomUUID().toString();
                statement.executeUpdate(updateCookieQuery(cookie, getUserName()));
                return true ;
            }
            else
                return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean isLogin()
    {
        try {
            ResultSet resultSet = statement.executeQuery(checkLoginQuery(getCookie()));
            if(resultSet.next())
            {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean isLogin(String cookie)
    {
        try {
            ResultSet resultSet = statement.executeQuery(checkLoginQuery(cookie));
            if(resultSet.next())
            {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    private String checkLoginQuery(String cookie)
    {
        return "select cookie from admin_account where cookie = '"+cookie+"'";
    }
    private String updateCookieQuery(String cookie,String username)
    {
        return "update admin_account set cookie='"+cookie+"' where admin_user_name='"+username+"'";
    }
    
    private String getUserQuery(String username,String password)
    {
       return "select admin_user_name,admin_name from admin_account where admin_user_name = '"+username+"' and admin_password = '"+password+"'";
    }
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
    
}
