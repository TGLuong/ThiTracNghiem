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
import java.sql.Statement;

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
    @XmlTransient
    private Connection connection;
    @XmlTransient
    private Statement statement;
    public Question()
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
    public Question(String id_subject, String id_question, String question, String answer_a, String answer_b, String answer_c, String answer_d,String answer) {
        this();
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
        this();
        this.id_subject = id_subject;
        this.id_question = id_question;
        this.question = question;
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
    }
    public ArrayList<Question> getListQuestions()
    {
         ArrayList<Question> listQuestion = new ArrayList<Question>();
        try {
            ResultSet resultSet = statement.executeQuery(getListQuestionQuery());
            while(resultSet.next())
            {
                Question ch = new Question(resultSet.getString("id_subject"),resultSet.getString("id_question"),resultSet.getString("question"),resultSet.getString("answer_a"),resultSet.getString("answer_b"),resultSet.getString("answer_c"),resultSet.getString("answer_d"),resultSet.getString("answer"));
                listQuestion.add(ch);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listQuestion;
    }
    public Question getQuestions(String id_subject,String id_question)
    {
        Question question = new Question();
        try {
            ResultSet resultSet = statement.executeQuery(getQuestionQuery(id_subject,id_question));
            if(resultSet.next())
            {
                question = new Question(resultSet.getString("id_subject"),resultSet.getString("id_question"),resultSet.getString("question"),resultSet.getString("answer_a"),resultSet.getString("answer_b"),resultSet.getString("answer_c"),resultSet.getString("answer_d"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }
    public boolean addQuestion(Question ch)
    {
        try {
            statement.execute(addQuestionQuery(ch));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }          
    }
    public boolean deleteQuestion(String id_subject,String id_question)
    {
        try {
            statement.execute(deleteQuestionQuery(id_subject, id_question));
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    public boolean updateQuestion(Question ch)
    {
        try {
            statement.executeUpdate(updateQuestionQuery(ch));
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserAdmin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        
    }
    public ArrayList<Question> getTest (String id_subject,String id_department)
    {
        ArrayList<Question> list = new ArrayList<Question>();
        int easy_question=0;
        int normal_question=0;
        int hard_question=0;
        try
        {
            Statement stm = connection.createStatement();
            ResultSet res =stm.executeQuery("select easy_question,normal_question,hard_question from test where id_subject='"+id_subject+"' and id_department='"+id_department+"'");
            if(res.next())
            {
                easy_question=res.getInt("easy_question");
                normal_question=res.getInt("normal_question");
                hard_question=res.getInt("hard_question");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        String sql1 = "SELECT TOP "+easy_question+"*FROM questions where  id_subject='" + id_subject + "'and id_question like 'A%' ORDER BY NEWID()";
        try {
            
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Question ch1 = new Question();
                ch1.setId_subject(rs1.getString("id_subject"));
                ch1.setId_question(rs1.getString("id_question"));
                ch1.setQuestion(rs1.getString("question"));
                ch1.setAnswer_a(rs1.getString("answer_a"));
                ch1.setAnswer_b(rs1.getString("answer_b"));
                ch1.setAnswer_c(rs1.getString("answer_c"));
                ch1.setAnswer_d(rs1.getString("answer_d"));
                System.out.println(ch1.getAnswer());
                list.add(ch1);
            }
        } catch (Exception e) {
        }
       
        String sql2 = "SELECT TOP "+normal_question+"*FROM questions where  id_subject='" + id_subject + "'and id_question like 'B%' ORDER BY NEWID()";
        try {
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                Question ch2 = new Question();
                ch2.setId_subject(rs2.getString("id_subject"));
                ch2.setId_question(rs2.getString("id_question"));
                ch2.setQuestion(rs2.getString("question"));
                ch2.setAnswer_a(rs2.getString("answer_a"));
                ch2.setAnswer_b(rs2.getString("answer_b"));
                ch2.setAnswer_c(rs2.getString("answer_c"));
                ch2.setAnswer_d(rs2.getString("answer_d"));
                list.add(ch2);
            }
        } catch (Exception e) {
        }
       
        String sql3 = "SELECT TOP "+hard_question+"*FROM questions where  id_subject='" + id_subject + "'and id_question like 'C%' ORDER BY NEWID()";
        try {
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                Question ch3 = new Question();
                ch3.setId_subject(rs3.getString("id_subject"));
                ch3.setId_question(rs3.getString("id_question"));
                ch3.setQuestion(rs3.getString("question"));
                ch3.setAnswer_a(rs3.getString("answer_a"));
                ch3.setAnswer_b(rs3.getString("answer_b"));
                ch3.setAnswer_c(rs3.getString("answer_c"));
                ch3.setAnswer_d(rs3.getString("answer_d"));
                list.add(ch3);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    

    //----------------------- query --------------------------
    
    
    
    private String addQuestionQuery(Question ch)
    {
        return "insert into questions(id_subject,id_question,question,answer_a,answer_b,answer_c,answer_d,answer) "
                + "values ('"+ch.id_subject+"','"+ch.id_question+"',N'"+ch.question+"',N'"+ch.answer_a+"',N'"+ch.answer_b+"',N'"+ch.answer_c+"',N'"+ch.answer_d+"','"+ch.answer+"')";
    }
    private String updateQuestionQuery(Question ch)
    {
        return "update questions set question = N'"+ch.question+"' ,answer_a = N'"+ch.answer_a+"',answer_b =N'"+ch.answer_b+"' "
                + ",answer_c =N'"+ch.answer_c+"',answer_d = N'"+ch.answer_d+"',answer = '"+ch.answer+"' where id_subject='"+ch.id_subject+"' and id_question='"+ch.id_question+"'";
    }
    private String getListQuestionQuery()
    {
       return "select top 100 * from questions ";
    }
    private String getQuestionQuery(String id_subject,String id_question)
    {
       return "select id_subject,id_question,question,answer_a,answer_b,answer_c,answer_d from questions "
               + "where id_subject='"+id_subject+"' and id_question='"+id_question+"'";
    }
    private String deleteQuestionQuery(String id_subject,String id_question)
    {
        return "delete from questions where id_subject='"+id_subject+"' and id_question = '"+id_question+"'";
    }
     
    //----------------------- end ---------------------------
    
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
