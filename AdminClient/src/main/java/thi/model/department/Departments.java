/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.department;
import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class Departments {
    @XmlElement(name="department")
    private ArrayList<Department> list = new ArrayList<Department>();
    public ArrayList<Department> getList()
    {
        return list;
    }
}
