/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.department;
import jakarta.xml.bind.annotation.*;
/**
 *
 * @author GiaLuong
 */
@XmlRootElement(name="department")
@XmlAccessorType(XmlAccessType.FIELD)
public class Department {
    private String id_department;
    private String department_name;

    public Department() {
    }

    public String getId_department() {
        return id_department;
    }

    public void setId_department(String id_department) {
        this.id_department = id_department;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public Department(String id_department, String department_name) {
        this.id_department = id_department;
        this.department_name = department_name;
    }
    
}
