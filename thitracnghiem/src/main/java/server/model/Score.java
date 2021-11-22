/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;
import Tool.DBConfig;
import javax.xml.bind.annotation.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="diem")
@XmlAccessorType(XmlAccessType.FIELD)
public class Score {
    @XmlElement(name="id_user")
    private String id_user;
    @XmlElement(name="id_subject")
    private String id_subject;
    @XmlElement(name="score")
    private float score;
    
    
    public Score(){}

    public Score(String id_user, String id_subject, float score) {
        this.id_user = id_user;
        this.id_subject = id_subject;
        this.score = score;
    }
    
    public ArrayList<Score> getListScore()
    {
         ArrayList<Score> listScore = new ArrayList<Score>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getListScoreQuery());
            while(resultSet.next())
            {
                Score score = new Score(resultSet.getString("id_user"),resultSet.getString("id_subject"),resultSet.getFloat("score"));
                listScore.add(score);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listScore;
    }
    
    public Score getScore(String id_user,String id_subject)
    {
        Score score = new Score();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getScoreQuery(id_user,id_subject));
            if(resultSet.next())
            {
                score = new Score(resultSet.getString("id_user"),resultSet.getString("id_subject"),resultSet.getFloat("score"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return score;
    }
    
    
    
    //------------------ QUERY  -----------------
    private String getListScoreQuery()
    {
       return "select top 10 id_user,id_subject,score from scores ";
    }
    private String getScoreQuery(String id_user,String id_subject)
    {
       return "select id_user,id_subject,score from scores "
               + "where id_user='"+id_user+"' and id_subject = '"+id_subject+"'";
    }
    
    
    
    
    
    
    
    
    
    
    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_subject() {
        return id_subject;
    }

    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    
}
