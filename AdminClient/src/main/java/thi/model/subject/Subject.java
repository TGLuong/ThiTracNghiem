/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.subject;
import jakarta.xml.bind.annotation.*;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {
    private String id_subject;
    private String name_subject;
    public Subject(){}
    public Subject(String id_subject)
    {
        this();
        this.id_subject=id_subject;
    }
    public Subject(String id_subject,String name_subject)
    {
        this(id_subject);
        this.name_subject=name_subject;
    }
    
    
    
    
    
    public String getId_subject() {
        return id_subject;
    }

    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }
    
    
    
    
}
