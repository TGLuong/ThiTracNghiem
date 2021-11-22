package server.application;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import server.Controller.AdminController;
import server.Controller.UserController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GiaLuong
 */
@ApplicationPath("/api")
public class serverApplication extends Application {
    public serverApplication()
    {
        new AdminController();
        new UserController();
    }
}
