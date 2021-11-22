/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.test;
import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tests {
    @XmlElement(name="test")
    private ArrayList<Test> list = new ArrayList<Test>();
    public ArrayList<Test> getList()
    {
        return list;
    }
}
