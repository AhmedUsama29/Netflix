package com.mycompany.netflix;

import java.io.IOException;
import java.util.Scanner;


public class Settings {
    
static Scanner s = new Scanner(System.in);

public void PrintUserDetails(NormalUser user){
                Netflix.draweq();
                System.out.println("Your User Name is: " + user.getName());
                System.out.println("Your email is: " + Netflix.maskEmail(user.getEmail()));
                System.out.println("Your Password is: " + Netflix.maskPassword(user.getPassword()));
                Netflix.draweq();
                System.out.println("1)Change Password\n2)Change Phonenumber\n3)Back to Main Menu");
}
    
    public void ChangePassword(NormalUser user) throws IOException{
    System.out.println("Enter your old password: " );
                    String oldpass = s.next();
                    String NewPassword;
                    System.out.println("Enter your new password:" );
                    NewPassword =s.next();
                    while(!Netflix.CheckPasswordTrue(NewPassword)){
            System.out.println("The Password must contain the following:\n");
            System.out.println("-Numbers\n-Uppercase letters\n-Lowercase letters\n-Special characters"
                    + "\n-Its length must be at least 8 characters");
            NewPassword = s.next();
        }
                    System.out.println("Re-Enter your new password:");
                    String NewPassword2 =s.next();
                        
                    if(oldpass.equals(user.getPassword()) && NewPassword.equals(NewPassword2)){
                    user.setPassword(NewPassword);
                    Netflix.Update(user.getEmail(), NewPassword, user.getPhonenumber());
                    Netflix.draw();
                    System.out.println("Your password has been changed successfully");
                    Netflix.draw();
                    }
                    
                    else{
                        System.out.println("The old password is wrong");
                        System.out.println("1)Try again\n2)Exit");
                        int k = s.nextInt();
                        switch(k){
                            case 1 : ChangePassword(user);
                            case 2 : System.exit(0);
                        }
                        
                    }
    
    }
    public void ChangePhoneNumber(NormalUser user) throws IOException{
    System.out.println("Enter your old phone number: ");
                    String oldnumber = s.next();
                    System.out.println("Enter your new phone number:");
                    String newnumber = s.next() ;
                    
                while(Netflix.IsPhoneNumInList(newnumber,Netflix.database.users) || !Netflix.CheckPhoneNumTrue(newnumber)){
                if(!Netflix.CheckPhoneNumTrue(newnumber)){
            System.out.println("Please enter a valid phone number (01xxxxxxxxx): ");
            newnumber = s.next();
                }else{
                System.out.println("This phone number is already linked to an account,Enter a different one");
                newnumber = s.next();
                        }
                    }            
                                
                    System.out.println("Re-Enter your new phone number:");
                    String newnumber2 = s.next() ;
                    if(oldnumber.equals(user.getPhonenumber()) && newnumber.equals(newnumber2)){
                        user.setPhonenumber(newnumber);
                     Netflix.Update(user.getEmail(),user.getPassword(), newnumber);
                     Netflix.draw();
                    System.out.println("Your phone number has been changed successfully");
                    Netflix.draw();
                     
                    }
                    else{
                        System.out.println("The old phone number is wrong");
                        System.out.println("1)Try again\n2)Exit");
                        int p = s.nextInt() ;
                        switch(p){
                            case 1 : ChangePhoneNumber(user);
                            case 2 :  System.exit(0);
                        }
                       
                    }
    }
    
    }

