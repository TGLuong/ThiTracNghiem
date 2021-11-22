/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.admin;


 
import jakarta.xml.bind.annotation.*;


 
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAdmin {
    private String user_name;
    private String cookie;
    //Constructor
    public UserAdmin()
    {
          
    }

    public UserAdmin(String user_name, String cookie) {
        this.user_name = user_name;
        this.cookie = cookie;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
    
}    
