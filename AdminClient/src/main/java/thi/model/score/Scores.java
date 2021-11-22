/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.score;
import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class Scores {
    @XmlElement(name="diem")
    private ArrayList<Score> list = new ArrayList<Score>();
    public ArrayList<Score> getList()
    {
        return list;
    }
    
}
