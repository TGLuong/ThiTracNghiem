/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.cl;
import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class Classes {
    @XmlElement(name="class")
    private ArrayList<Class> list = new ArrayList<Class>();
    public ArrayList<Class> getList()
    {
        return list;
    }
}
