/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi.model.test;

/**
 *
 * @author GiaLuong
 */
public class Test {
    private String id_subject;
    private String id_department;
    private String subeject_name;
    private String department_name;
    private int number_question;
    private int easy_question;
    private int normal_question;
    private int hard_question;
    private int time_test;
    private boolean do_test;

    
    public Test(){}

    public Test(String id_subject, String id_department,String subject_name,String department_name, int number_question, int easy_question, int normal_question, int hard_question,int time_test,boolean do_test) {
        this.id_subject = id_subject;
        this.id_department = id_department;
        this.subeject_name=subject_name;
        this.department_name=department_name;
        this.number_question = number_question;
        this.easy_question = easy_question;
        this.normal_question = normal_question;
        this.hard_question = hard_question;
        this.time_test=time_test;
        this.do_test=do_test;
    }

    public String getId_subject() {
        return id_subject;
    }

    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    public String getId_department() {
        return id_department;
    }

    public void setId_department(String id_department) {
        this.id_department = id_department;
    }

    public int getNumber_question() {
        return number_question;
    }

    public void setNumber_question(int number_question) {
        this.number_question = number_question;
    }

    public int getEasy_question() {
        return easy_question;
    }

    public void setEasy_question(int easy_question) {
        this.easy_question = easy_question;
    }

    public int getNormal_question() {
        return normal_question;
    }

    public void setNormal_question(int normal_question) {
        this.normal_question = normal_question;
    }

    public int getHard_question() {
        return hard_question;
    }

    public void setHard_question(int hard_question) {
        this.hard_question = hard_question;
    }

    public String getSubeject_name() {
        return subeject_name;
    }

    public void setSubeject_name(String subeject_name) {
        this.subeject_name = subeject_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public int getTime_test() {
        return time_test;
    }

    public void setTime_test(int time_test) {
        this.time_test = time_test;
    }
    public boolean getDo_test() {
        return do_test;
    }

    public void setDo_test(boolean do_test) {
        this.do_test = do_test;
    }
}
