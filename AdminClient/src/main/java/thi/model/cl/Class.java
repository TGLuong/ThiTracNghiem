/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.cl;
import jakarta.xml.bind.annotation.*;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="class")
@XmlAccessorType(XmlAccessType.FIELD)
public class Class {
    private String id_class;
    private String id_department;
    private String classname;
    public Class(){}

    public Class(String id_class, String id_department, String classname) {
        this.id_class = id_class;
        this.id_department = id_department;
        this.classname = classname;
    }
    
    

    public String getId_class() {
        return id_class;
    }

    public void setId_class(String id_class) {
        this.id_class = id_class;
    }

    public String getId_department() {
        return id_department;
    }

    public void setId_department(String id_department) {
        this.id_department = id_department;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
    
}
