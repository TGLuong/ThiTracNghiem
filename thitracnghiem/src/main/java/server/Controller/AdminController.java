/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.Controller;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import server.model.*;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
/**
 *
 * @author GiaLuong
 */
@Path("/admin")
public class AdminController {
    private UserAdmin admin;
    private Question cauHoi;
    private User user;
    private Subject subject;
    private Score score;
    private Test test;
    private ClassName className;
    private Department department;
    
    
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_XML)
    public UserAdmin login(@QueryParam("username") String userName,@QueryParam("password") String passWord)
    {
        admin = new UserAdmin(userName, passWord);
        if(admin.login())return admin;
            else return null;
    }
    
    
    //-----------------------   questions    -----------------------------
    
    
    @GET
    @Path("/question")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Question> getListQuestion()
    {
        cauHoi= new Question();
        return cauHoi.getListQuestions();
        
    }
    @GET
    @Path("/question/info")
    @Produces(MediaType.APPLICATION_XML)
    
    public Question getQuestion(@QueryParam("id_subject")String id_subject,@QueryParam("id_question")String id_question,@QueryParam("cookie") String cookie)
    {
        System.out.println("hahas");
        Question question = new Question();
        return question.getQuestions(id_subject, id_question);
        
    }
    @POST
    @Path("/question")
    @Produces(MediaType.APPLICATION_XML)
    public Question addQuestion(Question ch)
    {
        ch.addQuestion(ch);
        return ch;
    }
    @PUT
    @Path("/question")
    @Produces(MediaType.APPLICATION_XML)
    public Question updateQuestion(Question ch)
    {
        ch.updateQuestion(ch);
        return ch;
    }
    @DELETE
    @Path("/question")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteQuestion(@QueryParam("id_subject") String id_subject,@QueryParam("id_question") String id_question)
    {
        cauHoi = new Question();
        cauHoi.deleteQuestion(id_subject, id_question);
        return "true";
    }
    
    
    //---------------------- user -----------------------------
    
    
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<User> getListUser()
    {
        user = new User();
        return user.getListUser() ;
    }
    
    @GET
    @Path("/user/info")
    @Produces(MediaType.APPLICATION_XML)
    public User getUser(@QueryParam("id_user")String id_user)
    {
        user = new User();
        return user.getUser(id_user);
    }
    
    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_XML)
    public User addUser(User u)
    {
        user = u;
        user.addUser(u);
        return u;
    }
    
    @PUT
    @Path("/user")
    @Produces(MediaType.APPLICATION_XML)
    public User updateUser(User u)
    {
        user = u;
        user.updateUser(u);
        return u;
    }
    @PUT
    @Path("/user/reset_password")
    @Produces(MediaType.TEXT_PLAIN)
    public String resetPassword(@QueryParam("id_user")String iduser,@QueryParam("password")String password)
    {
        user =new User();
        user.resetPassword(iduser, password);
        return "reset passwrod susseccful";
    }
    
    @DELETE
    @Path("/user")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteUser(@QueryParam("id_user")String iduser)
    {
        user = new User();
        user.deleteUser(iduser);
        return "ok";
    }
    
    //-------------------------- end user ----------------------
    
    
    
    // -----------------------  subject --------------------
    @GET
    @Path("/subject")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Subject> getListSubject()
    {
        subject = new Subject();
        return subject.getListSubject();
    }
    
    @GET
    @Path("/subject/info")
    @Produces(MediaType.APPLICATION_XML)
    public Subject getSubject(@QueryParam("id_subject")String id_subject)
    {
        subject = new Subject();
        return subject.getSubject(id_subject);
    }
    
    @POST
    @Path("/subject")
    @Produces(MediaType.APPLICATION_XML)
    public Subject addSubject(Subject subject)
    {
        subject.addSubject(subject);
        return subject;
    }
    
    @PUT
    @Path("/subject")
    @Produces(MediaType.APPLICATION_XML)
    public Subject updateSubject(Subject subject)
    {
        subject.updateSubject(subject);
        return subject;
    }
    @DELETE
    @Path("/subject")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteSubject(@QueryParam("id_subject")String id_subject)
    {
        subject = new Subject();
        subject.deleteSubject(id_subject);
        return "ok";
    }
    
    
    
    // -----------------------   end subject -----------------
    
    // ----------------------- score -------------------
    @GET
    @Path("/score")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Score> getListScore()
    {
        score = new Score();
        return score.getListScore();
    }
    
    @GET
    @Path("/score/info")
    @Produces(MediaType.APPLICATION_XML)
    public Score getScore(@QueryParam("id_user")String id_user,@QueryParam("id_subject")String id_subject)
    {
        score = new Score();
        return score.getScore(id_user,id_subject);
        
    }
    
    // ----------------------- TEST -------------------
    
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Test> getListConfigTest()
    {
        test=new Test();
        return test.getListConfigTest();
    }
    
    @GET
    @Path("/test/info")
    @Produces(MediaType.APPLICATION_XML)
    public Test getConfigTest(@QueryParam("id_subject")String id_subject,@QueryParam("id_department")String id_deartment)
    {
        test = new Test();
        return test.getConfigTest(id_subject, id_deartment);
    }
    @POST
    @Path("/test/do")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean setDoTest(boolean do_,@QueryParam("id_subject")String id_subject,@QueryParam("id_department")String id_department)
    {
        test = new Test();
        if(test.setDoTest(do_, id_subject, id_department))return true;
            else return false;
    }
    @POST
    @Path("/test")
    public String addConfigTest(Test test)
    {
        test.addConfigTest(test);
        return "true";
    }
    @PUT
    @Path("/test")
    @Produces(MediaType.APPLICATION_XML)
    public String updateConfigTest(Test test)
    {
        test.updateConfigTest(test);
        return "true";
    }
    @DELETE
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteConfigTest(@QueryParam("id_subject")String id_subject,@QueryParam("id_department")String id_department)
    {
        test = new Test();
        test.deleteConfigTest(id_subject, id_department);
        return "true";
    }
    
    // ----------------------- CLASS -------------------
    @GET
    @Path("/class")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<ClassName> getListClass()
    {
        className = new ClassName();
        return className.getListClass();
    }
    @GET
    @Path("/class/info")
    @Produces(MediaType.APPLICATION_XML)
    public ClassName getClass(@QueryParam("id_class")String id_class,@QueryParam("id_department")String id_department)
    {
        className = new ClassName();
        return className.getClass(id_class, id_department);
    }
    @POST
    @Path("/class")
    @Produces(MediaType.APPLICATION_XML)
    public String addClass(ClassName className)
    {
        className.addClass(className);
        return "true";
    }
    @PUT
    @Path("/class")
    @Produces(MediaType.APPLICATION_XML)
    public String updateClass(ClassName className)
    {
        className.updateClass(className);
        return "true";
    }
    @DELETE
    @Path("/class")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteClass(@QueryParam("id_class")String id_class,@QueryParam("id_department") String id_department)
    {
        className = new ClassName();
        className.deleteClass(id_class, id_department);
        return "true";
    }
    // ----------------------- Department -------------------
    @GET
    @Path("/department")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Department> getListdepartment()
    {
        department = new Department();
        return department.getListDepartment();
    }
    @GET
    @Path("/department/info")
    @Produces(MediaType.APPLICATION_XML)
    public Department getdepartment(@QueryParam("id_department")String id_department)
    {
        department = new Department();
        return department.getDepartment(id_department);
    }
    @POST
    @Path("/department")
    @Produces(MediaType.APPLICATION_XML)
    public String adddepartment(Department department)
    {
        department.addDepartment(department);
        return "true";
    }
    @PUT
    @Path("/department")
    @Produces(MediaType.APPLICATION_XML)
    public String updatedepartment(Department  department)
    {
        department.updateDepartment(department);
        return "true";
    }
    @DELETE
    @Path("/department")
    @Produces(MediaType.APPLICATION_XML)
    public String deletdepartment(@QueryParam("id_department") String id_department)
    {
        department = new Department();
        department.deleteDepartment(id_department);
        return "true";
    }
    
    
    
    
    // ----------------------- end score -------------------
}
