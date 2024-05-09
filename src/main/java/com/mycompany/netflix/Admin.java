package com.mycompany.netflix;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Admin extends user {
    static Scanner s = new Scanner(System.in);
    Shows show = new Shows();

    public Admin(String name, String email, String password, String phonenumber) {
        super(name, email, password, phonenumber);
    }
    
    @Override
void menu(){
        Netflix.draweq();
        System.out.println("Hello " + getName() + ", What do you need to do ?");
        Netflix.draweq();
        System.out.println("1)Add Movie\n2)Statistics\n3)Sign Out");
        
}
   
    public void AddMovie(Admin admin,String MoviesFile) throws IOException{
    System.out.println("Enter The movie name:");
                String Mname = s.next();
                System.out.println("Enter The release month:");
                String month = s.next();
                System.out.println("Enter The release year:");
                String year = s.next();
                System.out.println("Enter The duration of the movie:");
                String duration = s.next();
                System.out.println("Enter The movie's imbd rating:");
                String imbd_score = s.next();
                System.out.println("Enter The movie language:");
                String language = s.next();
                System.out.println("Enter The country made the movie:");
                String country = s.next();
                System.out.println("Enter The movie's Director:");
                String director = s.next();
                // we did not makea class for it because we have no time and it will take a lot of time/space
                System.out.println("Enter The Actors list: Actor1(age/gender)-Actor2(age/gender)-......");
                String Actors = s.next();
                Movies movie = new Movies(Mname,month,year,duration,language,imbd_score,country,director,Actors);
                System.out.println("Which genre is the movie?");
                    show.ContentGenres();
                int v2 = s.nextInt();
                movie.setGenreID(v2);
                Netflix.database.addmovie(movie);
                FileWriter M = new FileWriter("movies.txt");
                M.write(MoviesFile + movie.ToString());
                M.close();
                System.out.println("Movie has been added, Thank you :)");
                System.out.println("Movie ID is: " + movie.getMovieID());
                Netflix.admin.menu();
                int e = s.nextInt();
                switch (e){
                    case 1: //1)Add Movie
                        Netflix.admin.AddMovie(admin,MoviesFile); break;
                    case 2://2)Statistics
                        admin.Statistics(admin,MoviesFile); break;}
    }
    
    public static void Statistics(Admin admin,String MoviesFile) throws IOException{
    
        System.out.println("1)Number of users\n2)Number of Movies\n3)Get any users data\n4)back");
        int t = s.nextInt();
        switch(t){
            case 1:System.out.println("Number of users on the system : " + Netflix.database.users.size() + "\n0)back");break;
            case 2:System.out.println("Number of movies on the system : " + Netflix.database.movieslist.size() + "\n0)back");break;
            case 3:System.out.println("Enter The user's email or phonenumber: ");
                String emailorphone = s.next();
                if(Netflix.IsEmailInList(emailorphone,Netflix.database.users) || Netflix.IsPhoneNumInList(emailorphone,Netflix.database.users)){
                int y = Netflix.database.forgetpassword(emailorphone);
                NormalUser user = Netflix.database.getuser(y);
                user.PrintData(); System.out.println(" \n0)back");break;}else{
                    System.out.println("There is no email or phonenumber like that");
                }
            case 4: Netflix.admin.menu();
        int v = s.nextInt();
        switch (v){
            case 1: //1)Add Movie
                Netflix.admin.AddMovie(admin,MoviesFile); break;
            case 2://2)Statistics
                admin.Statistics(admin,MoviesFile); break;
            case 3://3)Sign Out
                Netflix.draweq();
                System.out.println("Welcome to Netflix!");
                Netflix.draweq();
                System.out.println("1)Login\n2)New User\n3)Exit");
                      s = new Scanner(System.in);
                      s.useDelimiter("\\n"); 
                      int o = s.nextInt();
                      switch(o){
                          case 1:Netflix.login(); break ;
                          case 2:Netflix.newuser(); break ;
                          case 3:System.exit(0);
                          default : System.out.println("Wrong Input"); System.exit(0);
                      }
            default : System.out.println("Wrong Input"); System.exit(0);
                
                
        }break;
        default : System.out.println("Wrong Input"); System.exit(0);
        }
        int u = s.nextInt();
            switch(u){
                case 0: Statistics(admin,MoviesFile);
                default : System.out.println("Wrong Input"); System.exit(0);
            }
    
    }
    
    

}
