/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.user;
import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
    @XmlElement(name="sinhvien")
    private ArrayList<User> list = new ArrayList<>();
    public ArrayList<User> getList()
    {
        return list;
    }
}
