/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.xml.bind.annotation.*;

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
