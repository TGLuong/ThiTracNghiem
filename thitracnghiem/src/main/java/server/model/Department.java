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
@XmlRootElement(name="department")
@XmlAccessorType(XmlAccessType.FIELD)
public class Department {
    private String id_department;
    private String department_name;
    @XmlTransient
    private Connection connection;
    @XmlTransient
    private Statement statement;
    public Department()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Department> getListDepartment()
    {
         ArrayList<Department> listDepartment = new ArrayList<Department>();
        try {
            ResultSet resultSet = statement.executeQuery(getListDepartmentQuery());
            while(resultSet.next())
            {
                Department cn = new Department(resultSet.getString("id_department"),resultSet.getString("department_name"));
                listDepartment.add(cn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDepartment;
    }
    public Department getDepartment(String id_department)
    {
        Department department = new Department();
        try {
            ResultSet resultSet = statement.executeQuery(getDepartmentQuery(id_department));
            if(resultSet.next())
            {
                department =new Department(resultSet.getString("id_department"),resultSet.getString("department_name"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        }
        return department;
    }
    public boolean addDepartment(Department department)
    {
        try {
            statement.execute(addDepartmentQuery(department));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }          
    }   public boolean updateDepartment(Department department)
    {
        try {
            statement.executeUpdate(updateDepartmentQuery(department));
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    public boolean deleteDepartment(String id_department)
    {
        try {
            statement.execute(deleteDepartmentQuery(id_department));
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    
    
    //--------------------- QUERY -------------------------------
    private String getListDepartmentQuery()
    {
        return "select top 20 * from department";
    }
    private String getDepartmentQuery(String id_department)
    {
        return "select * from department where id_department = '"+id_department+"'";
    }
    private String addDepartmentQuery(Department department)
    {
        return "insert into department values('"+department.getId_department()+"',N'"+department.getDepartment_name()+"')";
    }
    private String updateDepartmentQuery(Department department)
    {
        return "update department set department_name = N'"+department.getDepartment_name()+"' where id_department = '"+department.getId_department()+"'";
    }
    private String deleteDepartmentQuery(String id_department)
    {
        return "delete from department where id_department = '"+id_department+"'";
    }
    
    
    
    
    
    
    public Department(String id_department, String department_name) {
        this();
        this.id_department = id_department;
        this.department_name = department_name;
    }

    public String getId_department() {
        return id_department;
    }

    public void setId_department(String id_department) {
        this.id_department = id_department;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
    
}
