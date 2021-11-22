/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.subject;
import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="collection")

public class Subjects {
    @XmlElement(name="subject")
    private ArrayList<Subject> list = new ArrayList<Subject>();
    public ArrayList<Subject> getList()
    {
        return list;
    }
}
