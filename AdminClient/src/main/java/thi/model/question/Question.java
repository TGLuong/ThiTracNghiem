/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.question;

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
@XmlRootElement(name="question")
@XmlAccessorType(XmlAccessType.FIELD)
public class Question {
    private String id_subject;
    private String id_question;
    private String question;
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private String answer;
 
    public Question(){}
    public Question(String id_subject, String id_question, String question, String answer_a, String answer_b, String answer_c, String answer_d,String answer) {
        this.id_subject = id_subject;
        this.id_question = id_question;
        this.question = question;
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
        this.answer =answer;
    }
    public Question(String id_subject, String id_question, String question, String answer_a, String answer_b, String answer_c, String answer_d) {
        this.id_subject = id_subject;
        this.id_question = id_question;
        this.question = question;
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
    }
 
    /**
     * @return the id_subject
     */
    public String getId_subject() {
        return id_subject;
    }

    /**
     * @param id_subject the id_subject to set
     */
    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    /**
     * @return the id_question
     */
    public String getId_question() {
        return id_question;
    }

    /**
     * @param id_question the id_question to set
     */
    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the answer_a
     */
    public String getAnswer_a() {
        return answer_a;
    }

    /**
     * @param answer_a the answer_a to set
     */
    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    /**
     * @return the answer_b
     */
    public String getAnswer_b() {
        return answer_b;
    }

    /**
     * @param answer_b the answer_b to set
     */
    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    /**
     * @return the answer_c
     */
    public String getAnswer_c() {
        return answer_c;
    }

    /**
     * @param answer_c the answer_c to set
     */
    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    /**
     * @return the answer_d
     */
    public String getAnswer_d() {
        return answer_d;
    }

    /**
     * @param answer_d the answer_d to set
     */
    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
