package com.mycompany.netflix;

public abstract class user {
    
    protected String UserPlanName;
    private String name;
    private String email;
    private String password;
    private String phonenumber;

   
    //constructors

    public user(String name, String email, String password, String phonenumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
    }


    public user() {
    }
    
    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    //getters

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getPassword() {
        return password;
    }
    
        public String ToString(){
    return name + "," + email + "," + password + "," + phonenumber+","+UserPlanName;
    }

    abstract void menu();

}
