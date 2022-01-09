package d3.protonmanexe.controller;

import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private String email;
    private Integer phoneNo;

    public Contact () {
    }

    public Contact (String name, String email, Integer phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
    }

    // public String getId() {
    //     return id;
    // }

    // public void setId(String id) {
    //     this.id = id;
    // }

}
// README
// Note:
// you need setters to allow the html page to bind directly ot the variable names as 
// specified in the html page; without setters, Thymeleaf cannot link or associate the 
// text box linked to each variable in this class directly from the html page