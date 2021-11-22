/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.*;
import Tool.DBConfig;
import java.sql.PreparedStatement;
import java.util.Collections;

/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="test")
@XmlAccessorType(XmlAccessType.FIELD)
public class Test {
    private String id_subject;
    private String id_department;
    private String subject_name;
    private String department_name;
    private int number_question;
    private int easy_question;
    private int normal_question;
    private int hard_question;
    private int time_test;
    private boolean do_test;
    @XmlTransient
    private Connection connection;
    @XmlTransient
    private Statement statement;
    
    public Test()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Test(String id_subject, String id_department,String subject_name,String department_name, int number_question, int easy_question, int normal_question, int hard_question,int time_test,boolean do_test) {
        this();
        this.id_subject = id_subject;
        this.id_department = id_department;
        this.subject_name=subject_name;
        this.department_name=department_name;
        this.number_question = number_question;
        this.easy_question = easy_question;
        this.normal_question = normal_question;
        this.hard_question = hard_question;
        this.time_test=time_test;
        this.do_test=do_test;
    }
    public ArrayList<Test> getListConfigTest()
    {
         ArrayList<Test> listTest = new ArrayList<Test>();
        try {
            ResultSet resultSet = statement.executeQuery(getListTestQuery());
            while(resultSet.next())
            {
                Test test =new Test(resultSet.getString("id_subject"),resultSet.getString("id_department"),resultSet.getString("name_subject"),resultSet.getString("department_name"),resultSet.getInt("number_question"),resultSet.getInt("easy_question"),resultSet.getInt("normal_question"),resultSet.getInt("hard_question"),resultSet.getInt("time_test"),resultSet.getBoolean("do"));
                listTest.add(test);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTest;
    }
    public Test getConfigTest(String  id_subject,String id_department)
    {
        Test test = new Test();
        try {
            ResultSet resultSet = statement.executeQuery(getConfigQuery(id_subject, id_department));
            if(resultSet.next())
            {
                test = new Test(resultSet.getString("id_subject"),resultSet.getString("id_department"),resultSet.getString("name_subject"),resultSet.getString("department_name"),resultSet.getInt("number_question"),resultSet.getInt("easy_question"),resultSet.getInt("normal_question"),resultSet.getInt("hard_question"),resultSet.getInt("time_test"),resultSet.getBoolean("do"));
            }
            return test;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return test;
        }
    }
    
    public boolean updateConfigTest(Test test)
    {
        try {
            statement.executeUpdate(updateTestQuery(test));
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    public boolean addConfigTest(Test test)
    {
        try {
            statement.execute(addTestQuery(test));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }          
    }
    public boolean deleteConfigTest(String id_subject,String id_department)
    {
        try {
            statement.execute(deleteTetQuery(id_subject, id_department));
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    public boolean setDoTest(boolean do_,String id_subjetc,String id_department)
    {
        int check;
        if(do_==true)
        {
            check=1;
        }
        else check=0;
        try
        {
            statement.executeUpdate("update test set do = "+check+"where id_subject = '"+id_subjetc+"' and id_department= '"+id_department+"'");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    
    
    
    
    //================== QUERY =======================
    String getConfigQuery(String id_subject,String id_department)
    {
        return "select * from test_full_info where id_subject = '"+id_subject+"' and id_department = '"+id_department+"'";
    }
    String getListTestQuery()
    {
        return "select top 20 * from test_full_info";
    }
    String updateTestQuery(Test test)
    {
        return "update test set number_question= "+test.getNumber_question()+", easy_question ="+test.getEasy_question()+", normal_question="+test.getNormal_question()+",hard_question="+test.getHard_question()+",time_test = "+test.getTime_test()+" where id_subject='"+test.getId_subject()+"' and id_department = '"+test.getId_department()+"'";
    }
    String addTestQuery(Test test)
    {
        return "insert into test(id_subject,id_department,number_question,easy_question,normal_question,hard_question,time_test) values('"+test.getId_subject()+"','"+test.getId_department()+"',"+test.getNumber_question()+",'"+test.getEasy_question()+"','"+test.getNormal_question()+"','"+test.getHard_question()+"','"+test.getTime_test()+"')";
    }
    String deleteTetQuery(String id_subject,String id_department)
    {
        return "delete from test where id_subject='"+id_subject+"' and id_department='"+id_department+"'";
    }
    
    
    
    
    public String getId_subject() {
        return id_subject;
    }

    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    public String getId_department() {
        return id_department;
    }

    public void setId_department(String id_department) {
        this.id_department = id_department;
    }

    public int getNumber_question() {
        return number_question;
    }

    public void setNumber_question(int number_question) {
        this.number_question = number_question;
    }

    public int getEasy_question() {
        return easy_question;
    }

    public void setEasy_question(int easy_question) {
        this.easy_question = easy_question;
    }

    public int getNormal_question() {
        return normal_question;
    }

    public void setNormal_question(int normal_question) {
        this.normal_question = normal_question;
    }

    public int getHard_question() {
        return hard_question;
    }

    public void setHard_question(int hard_question) {
        this.hard_question = hard_question;
    }
    public void setTime_test(int time_test)
    {
        this.time_test=time_test;
    }
    public int getTime_test()
    {
        return this.time_test;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public boolean getDo_test() {
        return do_test;
    }

    public void setDo_test(boolean do_test) {
        this.do_test = do_test;
    }
    
    
    
}
