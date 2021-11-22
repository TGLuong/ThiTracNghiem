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
@Path("/user")
public class UserController {
    Question question;
    User user;
    Subject subject;
    Score score;
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_XML)
    public User login(@QueryParam("id_user") String id_user,@QueryParam("password") String passWord)
    {
        user = new User(id_user,passWord);
        if(user.login())
        {
            return user.getUser(id_user);
        }
        else
        return null;
    }
    @GET
    @Path("/subject")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Subject> getListSubject()
    {
        subject = new Subject();
        return subject.getListSubject();
    }
    @PUT
    @Path("/password")
    @Produces(MediaType.TEXT_PLAIN)
    public String resetPassword(@QueryParam("id_user")String iduser,@QueryParam("password")String password)
    {
        user =new User();
        user.resetPassword(iduser, password);
        return "reset passwrod susseccful";
    }
    @GET
    @Path("/score")
    @Produces(MediaType.APPLICATION_XML)
    public Score getScore(@QueryParam("id_user")String id_user,@QueryParam("id_subject")String id_subject)
    {
        score = new Score();
        return score.getScore(id_user,id_subject);
        
    }
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Question> test(@QueryParam("id_subject")String id_subject,@QueryParam("id_department")String id_department)
    {
        question = new Question();
        return question.getTest(id_subject,id_department);
    }
    @POST
    @Path("/done")
    @Produces(MediaType.TEXT_PLAIN)
    public int mark(String a)
    {
        return 20;
    }
}
