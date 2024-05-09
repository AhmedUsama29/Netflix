package com.mycompany.netflix;


public class NormalUser extends user {
    
Subscription basic = new Subscription("1","Basic",70,"Good","Good","720p (HD)");
Subscription standard = new Subscription("2","Standard",120,"Great","Great","1080p (FHD)");
Subscription premium = new Subscription("3","Premium",165,"Best","Best","4K (Ultra HD + HDR)");

Payment payment = new Payment();

WatchList watchlist = new WatchList();
WatchLater watchlater = new WatchLater();
Shows show = new Shows();
Settings settings = new Settings();

    public NormalUser(String name, String email, String password, String phonenumber) {
        super(name, email, password, phonenumber);
    }

    public NormalUser() {
    }

    public String getUserPlanName() {
        return UserPlanName;
    }

    public void setUserPlanName(String UserPlanName) {
        this.UserPlanName = UserPlanName;
    }

void PrintData(){

    System.out.println("Name: " + getName() +"\nEmail: " + getEmail()
    + "\nPassword: " + getPassword()+ "\nPhoneNumber: " + getPhonenumber()
    + "\nSubscribtion: "+ getUserPlanName());
}
    
void menu(){
        Netflix.draweq();
        System.out.println("Welcome Back, " + getName() );
        System.out.println("You are on [" + getUserPlanName() + "] plan");
        Netflix.draweq();
        System.out.println("1)Movies\n2)Settings\n3)My List\n4)Support\n5)Sign Out");
        Netflix.draw();

}
}
