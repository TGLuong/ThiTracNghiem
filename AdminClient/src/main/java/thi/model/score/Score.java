/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.score;
import jakarta.xml.bind.annotation.*;
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
