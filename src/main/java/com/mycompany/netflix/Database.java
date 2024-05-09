package com.mycompany.netflix;

import java.util.ArrayList;

public class Database {
    
    public static ArrayList<Movies> movieslist = new ArrayList<Movies>();
    public static ArrayList<String> movieslistS = new ArrayList<String>();
                
    public static ArrayList<NormalUser> users = new ArrayList<NormalUser>();
     public static ArrayList<String> usersS = new ArrayList<String>();

    public Database(){
    }

   public void adduser(NormalUser s){
       users.add(s);
   }
    
   public void addmovie(Movies m){
   movieslist.add(m);
   }

   

   public int login(String email,String password){
   int z = -1;
   // why -1 ? A) because if account is not found,and z == 0 || any positive value , it will return the index of this user
   for(user temp : users){
   if (temp.getEmail().equalsIgnoreCase(email) && temp.getPassword().equals(password)){
   z = users.indexOf(temp);
   break;
   }
   }
       return z;
   }
   
      public int forgetpassword(String forgotten){
   int z = -1;
   // why -1 ? A) because if account is not found,and z == 0 || any positive value , it will return the index of this user
   for(user temp : users){
   if (temp.getEmail().equalsIgnoreCase(forgotten) || temp.getPhonenumber().equals(forgotten)){
   z = users.indexOf(temp);
   break;
   }
   }
       return z; // the index of the user in database
   }
      
   public NormalUser getuser(int n){
   return users.get(n);
   }


public static ArrayList<Movies> GetMovieFromList(String Movies){    
    
    String SingleMovies[] = Movies.split("-"); //Shawshank[0] Interstaller[1]
    ArrayList<Movies> m = new ArrayList<Movies>();
    for(String s: SingleMovies){
        for(Movies temp : movieslist){
    if(s.equalsIgnoreCase(temp.getTitle())){
        m.add(temp);
    }
    }
    }
    return m;
}

public static ArrayList<Double> GetRatingFromList(String Ratings){    
    String SingleRatings[] = Ratings.split("-"); //3.4 [0] , 7.8 [1]
    ArrayList<Double> m = new ArrayList<Double>();
    for(String s: SingleRatings){
        m.add(Double.parseDouble(s));
    }
    return m;
}

}
