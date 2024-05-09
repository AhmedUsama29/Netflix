package com.mycompany.netflix;

import java.util.Scanner;
import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Netflix {
    
    static Scanner s;
    public static Database database;
    static Admin admin;
    
    public static void main(String[] args) throws IOException {
          
        StartRun();    
                draweq();
                System.out.println("Welcome to Netflix!");      // Login Screen
                draweq();
                System.out.println("1)Login\n2)New User\n3)Exit");
        s = new Scanner(System.in);
        s.useDelimiter("\\n"); //to fix scan problem FULL NAME etc.....
        int x = s.nextInt();
        
        switch(x){
            case 1:  
  /* i put those 2 lines here , beacuse  if i put them at first line of login() and you come from newuser() to login()
  they override the new user's data with the old one (when we open the program) */
        String UsersFile = ToFile(database.usersS);
        database.users = UsersInArr(UsersFile);
        draw();
        login();break;
            case 2:newuser();break;
            case 3:System.exit(0);
            default :System.out.print("Error!, please try again");
        }
    }
    
    protected static void login() throws IOException{
        
        System.out.println("Enter your email: ");
        String email = s.next();
        draw();
        System.out.println("Enter your password: ");
        String password = s.next();
        draw();
        // explained in database class
        int n = database.login(email, password) ;
        if (n!= -1){
        NormalUser user = database.getuser(n);
        UserLogIned(user,user.show);
   
        String MoviesFile = ToFile(database.movieslistS);
        database.movieslist = MoviesInArr(MoviesFile);
        
        String WatchHistory = ToFile(user.watchlist.movieshistoryS);
        user.watchlist.movieshistory = WatchInArr(user.getEmail(),WatchHistory);
            
        String WatchLater = ToFile(user.watchlater.watchlaterS);
        user.watchlater.watchlater = WatchInArr(user.getEmail(),WatchLater);
            
        String RatingsFile = ToFile(user.show.RatingS);
        RatingsInArr(database.movieslist,RatingsFile);           
        
           
        user.menu();
        MainMenu(user,WatchHistory,WatchLater,RatingsFile);
        
        
        }else if(email.equalsIgnoreCase(admin.getEmail()) && password.equals(admin.getPassword())){
            
            
            String MoviesFile = ToFile(database.movieslistS);
            database.movieslist = MoviesInArr(MoviesFile);
            
        admin.menu();
        int v = s.nextInt();
        switch (v){
            case 1: //1)Add Movie
                admin.AddMovie(admin,MoviesFile); break;
            case 2://2)Statistics
                admin.Statistics(admin,MoviesFile); break;
            case 3://3)Sign Out
                draweq();
                System.out.println("Welcome to Netflix!");
                draweq();
                System.out.println("1)Login\n2)New User\n3)Exit");
                      s = new Scanner(System.in);
                      s.useDelimiter("\\n"); 
                      int o = s.nextInt();
                      switch(o){
                          case 1:login(); break ;
                          case 2:newuser(); break ;
                          case 3:System.exit(0);
                          default : System.out.println("Wrong Input"); System.exit(0);
                      }
            default : System.out.println("Wrong Input"); System.exit(0);
                
        }
        
        }else{ // Wrong Email or Password in Login Screen
        System.out.println("Wrong email or password");
        System.out.println("1)Try Again\n2)Exit\n3)Forget Password");
        int x3 = s.nextInt();
        switch(x3){
            case 1: login(); break;
            case 2: System.exit(0);
            case 3: ForgetPassword();
            default : System.out.println("Wrong Input"); System.exit(0);
        }
        }
}
    
    protected static void newuser() throws IOException{
        
        String UsersFile = ToFile(database.usersS);
        database.users = UsersInArr(UsersFile);

        draw();
        System.out.println("Enter your Full name: ");
        String name = s.next();
        draw();
        System.out.println("Enter your email: ");
        String email = s.next();
        
        while(!CheckEmailTrue(email) || IsEmailInList(email, database.users)){

               if(!CheckEmailTrue(email)){
            System.out.println("Please re-enter a valid email: ");
            email = s.next();
                }else{
                System.out.println("This email is already existed");
                System.out.println("1)Type a different email\n2)Exit");
                int x5 = s.nextInt();
                switch(x5){
                    case 1: System.out.println("Enter the email: ");
                        email = s.next(); break;
                    case 2:System.exit(0);
                    default : System.out.println("Wrong Input"); System.exit(0);
                           }
                        }
        }

        draw();
        System.out.println("Enter your password: ");
        String password = s.next();
        
        while(!CheckPasswordTrue(password)){
            draweq();
            System.out.println("The Password must contain the following:\n");
            System.out.println("""
                               -Numbers
                               -Uppercase letters
                               -Lowercase letters
                               -Special characters
                               -Its length must be at least 8 characters""");
            draweq();
            password = s.next();
        }
        draw();
        System.out.println("Enter your phone number: ");
        String phonenumber = s.next();
        
            while(IsPhoneNumInList(phonenumber,database.users) || !CheckPhoneNumTrue(phonenumber)){
                if(!CheckPhoneNumTrue(phonenumber)){
            System.out.println("Please enter a valid phone number (01xxxxxxxxx): ");
            phonenumber = s.next();
                }else{
                System.out.println("This phone number is already linked to an account");
                System.out.println("1)Type a different phone number\n2)Exit");
                int x4 = s.nextInt();
                switch(x4){
                    case 1: System.out.println("Enter the phonenumber: ");
                        phonenumber = s.next(); break;
                    case 2:System.exit(0);
                    default : System.out.println("Wrong Input"); System.exit(0);
                           }
                        }
                    }            
            // can't put it all in 1 func because i'll need the data for the object :
        NormalUser user = new NormalUser(name,email,password,phonenumber);
        FileWriter f = new FileWriter("users.txt");
                PlanChoose(user,UsersFile,f);
}
    
    
    
private static String ToFile(ArrayList<String> S){
    // returns the users data from the String array as one String
    String s = "";
    for(int i =0;i < S.size();i++){
    s = (s + S.get(i) + "\n");
    }

return s;
}

protected static boolean IsEmailInList(String email, ArrayList<NormalUser> U) {
    //checks if the email address is already a user
    for(NormalUser temp : U){
        if(email.equalsIgnoreCase(temp.getEmail()) || email.equalsIgnoreCase("admin@admin.com")){
        return true;
            }
        }
        return false;
     }

protected static boolean IsPhoneNumInList(String phonenumber,ArrayList<NormalUser> U){
    //checks if the phonenumber is already a user
    for(NormalUser temp : U){
    if(phonenumber.equals(temp.getPhonenumber())){
    return true;
        }
    }
    return false;
}

private static ArrayList<NormalUser> UsersInArr(String UsersFile){
    //puts the String users data into an array of (user class) with their data modefied
String Users[] = UsersFile.split(",|\n");
ArrayList<NormalUser> U = new ArrayList<NormalUser>();

           for (int i = 0 ; i <= (Users.length-5) ; i+=5) {
            NormalUser temp = new NormalUser();
            temp.setName(Users[i]);
            temp.setEmail(Users[i+1]);
            temp.setPassword(Users[i+2]);
            temp.setPhonenumber(Users[i+3]);
            temp.setUserPlanName(Users[i+4]);
            U.add(temp);
        }

return U;
}

private static ArrayList<Movies> MoviesInArr(String MoviesFile){
    //puts the String movies data into an array of (Movies class) with their data modefied
String Movies[] = MoviesFile.split(",|\n");
ArrayList<Movies> M = new ArrayList<Movies>();

           for (int i = 0 ; i <= (Movies.length-11) ; i+=11) {
            Movies temp = new Movies();
            temp.setTitle(Movies[i]);
            temp.setYear(Movies[i+1]);
            temp.setMonth(Movies[i+2]);
            temp.setDuration(Movies[i+3]);
            temp.setLanguages(Movies[i+4]);
            temp.setImbd_score(Movies[i+5]);
            temp.setCountry(Movies[i+6]);
            temp.setDirector_name(Movies[i+7]);
            temp.setGenreID(Integer.parseInt(Movies[i+8])); //Integer.parseInt(digitString) ---> converts the String digi to an int
            // Movies[i+9] == MovieID , So we are going to skip it
            temp.setActors(Movies[i+10]);
            M.add(temp);
        }

return M;
}



private static boolean CheckEmailTrue(String email){
    // checks if the email entered has correct shape
    if(email.contains("@") && email.contains(".")){
    return true; // Email is correct
    }else
        return false;

}
protected static boolean CheckPasswordTrue(String password){
    //checks the rules of a password
    boolean HasLowerCase = false;
    boolean HasUpperCase = false;
    boolean HasDigit = false;
    boolean HasSpecialChar = false;
    boolean HasRightLength = false;
    
    for(char c : password.toCharArray()){
    if(Character.isLowerCase(c)){
    HasLowerCase = true;
    }
    if(Character.isUpperCase(c)){
    HasUpperCase = true;
    }
    if(Character.isDigit(c)){
    HasDigit = true;
    }
    if(!Character.isLetterOrDigit(c) && c != ' '){
        HasSpecialChar = true;
    }
    }
        if(password.toCharArray().length >= 8){
        HasRightLength = true;
        }
        return HasLowerCase && HasUpperCase && HasDigit && HasSpecialChar && HasRightLength;
}

protected static boolean CheckPhoneNumTrue(String phonenumber){
//checks if the phonenumber doesn't have letters and it has correct length (01xxxxxxxxx) "11"
for(char c : phonenumber.toCharArray()){
    if(!Character.isDigit(c) || !phonenumber.startsWith("01") || phonenumber.toCharArray().length != 11){
        return false;
        }
    }
        return true;

}

private static String EditInList(String WatchHistory, Movies m,String email){

    String cut[] = WatchHistory.split(",|\n"); //email[0],Shawshank-Interstaller [1] 
    String Final = "";
    for(int i = 0; i < cut.length;i++){ 
    if(email.equalsIgnoreCase(cut[i])){
     cut[i+1] += "-" + m.getTitle(); // [1]
    }
    if(CheckEmailTrue(cut[i])){
    Final += cut[i] + ",";}else{
    Final += cut[i] + "\n";
    }
    }
    return Final;
}

private static String EditInRate(String RatingsFile, Movies m){
    
    String cut[] = RatingsFile.split(",|\n"); //MovieTitle[0], 3.2-5.4 [1] 
    String Final = "";
    for(int i = 0; i < cut.length;i++){ 
    if(m.getTitle().equalsIgnoreCase(cut[i])){
     cut[i+1] += "-" + m.Rating.getLast(); // [1]
    }
    if(!cut[i].contains("0123456789,")){
    Final += cut[i] + ",";}else{
    Final += cut[i] + "\n";
    }
    }
    return Final;
}

private static ArrayList<Movies> WatchInArr(String email,String WatchHistory){
    
    String History[] = WatchHistory.split(",|\n"); //email[0]  creed-stargirl-inter [1]
    ArrayList<Movies> WH = new ArrayList<Movies>();
    for(int i = 0; i < History.length;i++){
        if(History[i].equalsIgnoreCase(email)){
            WH = database.GetMovieFromList(History[i+1]);
            //History[i+1] == movies title , put it in the arraylist (the movies)
        }
    }

    return WH;
}


private static void RatingsInArr(ArrayList<Movies> Movie,String Moviesdata){
    // movie.Ratings = return
    String Ratings[] = Moviesdata.split(",|\n"); //Movie name[0]  9.8-3.5-2.5 [1]
    ArrayList<Double> R = new ArrayList<Double>();
    for(int j = 0; j < Movie.size() ; j++){
    for(int i = 0; i < Ratings.length;i++){
        if(Ratings[i].equalsIgnoreCase(Movie.get(j).getTitle())){
            R = database.GetRatingFromList(Ratings[i+1]);
            Movie.get(j).setRating(R);
            //History[i+1] == movies title , put it in the arraylist (the movies)
        }
    }
}

}

private static void PrintActorsGood(String actorsbad){

String Cutter[] = actorsbad.split("-"); //Actor(15/male)[0]
int i = 1;
for(String s:Cutter){
    System.out.println("- " + s);
    if(i == Cutter.length)break;
    i++;
}
}

private static String GenerateVerificationCode(int length, boolean digitsOnly) {
   
    SecureRandom random = new SecureRandom();

    // Character set for verification code
    String characters;
    if (digitsOnly) {
      characters = "0123456789";
    } else {
      characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    }

    StringBuilder code = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(characters.length());
      code.append(characters.charAt(index));
    }

    return code.toString();
  }

protected static void Update(String email, String newPassword, String newPhoneNumber) throws IOException {

    File usersFile = new File("users.txt");
    Scanner scanner = new Scanner(usersFile);
    ArrayList<String> updatedLines = new ArrayList<>();

    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.contains(email)) {
            
            String[] parts = line.split(",");
            parts[2] = newPassword; 
            parts[3] = newPhoneNumber; 
            StringBuilder updatedLine = new StringBuilder();
            for (String part : parts) {
                updatedLine.append(part).append(",");
            }
            updatedLine.deleteCharAt(updatedLine.length() - 1);
            updatedLines.add(updatedLine.toString());
        } else {
            updatedLines.add(line);
        }
    }
    scanner.close();
    
    FileWriter writer = new FileWriter(usersFile);
    for (String line : updatedLines) {
        writer.write(line + "\n");
    }
    writer.close();
} 
protected static String maskEmail(String email) {
  int atIndex = email.indexOf('@');
  
  StringBuilder maskedEmail = new StringBuilder();
  maskedEmail.append(email.substring(0, 2));
  for (int i = 2; i < atIndex; i++) {
    maskedEmail.append('*');
  }
  maskedEmail.append(email.substring(atIndex));
  return maskedEmail.toString();
}

protected static String maskPassword(String password) {
  
  StringBuilder maskedPassword = new StringBuilder();
  maskedPassword.append(password.charAt(0)); // Append first character
  for (int i = 1; i < password.length() - 1; i++) {
    maskedPassword.append('*');
  }
  maskedPassword.append(password.charAt(password.length() - 1)); // Append last character
  return maskedPassword.toString();
}

private static void Support(NormalUser user,String WatchHistory,String WatchLater,String RatingsFile) throws IOException{
    draweq();
    System.out.println("How can we help you ?");
    draweq();
    System.out.println("1)The app is experiencing slow browsing \n"  
            + "2)I can't access my account after updating the app\n"  
            + "3)The subtitles are not working correctly\n" 
            + "4)other\n5)Back to Main Menu"); 
    draw();
    int e = s.nextInt(); 
    switch(e){
        case 1: System.out.println("This may be due to cached mamory.\n"
                + "Try clearing it or using a stronger internet connection.");break;
        case 2: System.out.println("This issue can be resolved by clearing the app data or logging out logging back in");break;
        case 3: System.out.println("You can try reloading the episode or checking the subtitle settings in the app");break;
        case 4: System.out.println("Send your problem on our email:\nadmin@admin.com");break;
        case 5: user.menu();
                MainMenu(user,WatchHistory,WatchLater,RatingsFile);break ;
        default : System.out.println("Wrong Input"); System.exit(0);
    }
    draw();
    System.out.println("1)Back to Support Menu\n2)Go to Main Menu\n3)Exit"); int l = s.nextInt();
                    switch(l){
                        case 1:
                            Support(user,WatchHistory,WatchLater,RatingsFile); break;
                        case 2:
                user.menu();
                MainMenu(user,WatchHistory,WatchLater,RatingsFile);break ;
                        case 3: System.exit(0);
                        default : System.out.println("Wrong Input"); System.exit(0);
                    }
    
    
}

private static void MoviesList(NormalUser user,String WatchHistory,String WatchLater,String RatingsFile,int x) throws IOException{
    Shows show = new Shows();
    if(x != 1){
    System.out.println("Movies Genres: ");
                show.ContentGenres(); 
                 int m = s.nextInt(); //Scans Genre ID
//prints only movies in the genre choosen
    for(Movies temp : database.movieslist ){
    if(m == temp.getGenreID()){
  temp.PrintMovieMenu();
}else if(m == 10){
  user.menu();
  MainMenu(user,WatchHistory,WatchLater,RatingsFile); // Call MainMenu
}
        }
}else{
    x--;
    }
            
            int m2 = s.nextInt();
                for(Movies movie : database.movieslist){
                if(m2 == movie.MovieID){ // choosing the movie
                    movie.PrintMovieInfo();
                    int c = s.nextInt();
                    switch(c){
                        case 1 : //Play
                            user.watchlist.AddMovie(movie);
                            FileWriter W = new FileWriter("watchhistory.txt");
                            if(!WatchHistory.contains(user.getEmail())){
                            W.write(WatchHistory + user.getEmail() + "," + user.watchlist.ToString());
                            W.close();
                            }else{
                            W.write(EditInList(WatchHistory,movie,user.getEmail()));
                            W.close();
                            }
                            
                            System.out.println("You have watched the movie");
                            System.out.println("1)Go to Genres\n2)Go to Main Menu");
                                int j0 = s.nextInt();
                                switch(j0){
                                    case 1:MoviesList(user,WatchHistory,WatchLater,RatingsFile,x); break;
                                    case 2:user.menu(); MainMenu(user,WatchHistory,WatchLater,RatingsFile);break;
                                    default : System.out.println("Wrong Input"); System.exit(0);
                                }
                        case 2: //Watch Later
                            if(CheckMovieInList(movie,user.watchlater.watchlater)){
                                System.out.println("This movie is already added in your list");
                                System.out.println("1)Go to Genres\n2)Go to Main Menu");
                                int g = s.nextInt();
                                switch(g){
                                    case 1:MoviesList(user,WatchHistory,WatchLater,RatingsFile,x); break;
                                    case 2:user.menu(); MainMenu(user,WatchHistory,WatchLater,RatingsFile);break;
                                    default : System.out.println("Wrong Input"); System.exit(0);
                                }
                                
                            }else{
                           user.watchlater.AddMovie(movie);
                            FileWriter W2 = new FileWriter("watchlater.txt");
                            if(!WatchLater.contains(user.getEmail())){
                            W2.write(WatchLater + user.getEmail() + "," + user.watchlater.ToString());
                            W2.close();
                            }else{
                            W2.write(EditInList(WatchLater,movie,user.getEmail()));
                            W2.close();
                            }
                            System.out.println( movie.getTitle() + " has been added to your watch later list");
                            System.out.println("1)Go to Genres\n2)Go to Main Menu");
                                int j = s.nextInt();
                                switch(j){
                                    case 1:MoviesList(user,WatchHistory,WatchLater,RatingsFile,x); break;
                                    case 2:user.menu(); MainMenu(user,WatchHistory,WatchLater,RatingsFile);break;
                                    default : System.out.println("Wrong Input"); System.exit(0);
                                }
}
                        case 3: //Cast
                            PrintActorsGood(movie.getActors());
                            System.out.println("1)Go to Genres\n2)Go to Main Menu");
                                int j1 = s.nextInt();
                                switch(j1){
                                    case 1:MoviesList(user,WatchHistory,WatchLater,RatingsFile,x); break;
                                    case 2:user.menu(); MainMenu(user,WatchHistory,WatchLater,RatingsFile);break;
                                    default : System.out.println("Wrong Input"); System.exit(0);
                                }
                            
                            break;
                        case 4:  //Rate
                                FileWriter RA = new FileWriter("Ratings.txt");
                                System.out.println(" Rate " + movie.getTitle() + " ( 1 - 10 stars ) : ");
                                double R = s.nextDouble();
                                movie.addRating(R);
                                
                                if(!RatingsFile.contains(movie.getTitle())){
                            RA.write(RatingsFile + movie.getTitle() + "," + R);
                            RA.close();
                            }else{
                            RA.write(EditInRate(RatingsFile,movie));
                            RA.close();
                            }
                                
                                System.out.println("You rated " + movie.getTitle() + " " + R + " stars.");
                                System.out.println("The total rate for (" + movie.getTitle() + ") is: " + movie.getAverageRatinggood());
                                System.out.println("1)Go to Genres\n2)Go to Main Menu");
                                int j2 = s.nextInt();
                                switch(j2){
                                    case 1:MoviesList(user,WatchHistory,WatchLater,RatingsFile,x); break;
                                    case 2:user.menu(); MainMenu(user,WatchHistory,WatchLater,RatingsFile);break;
                                    default : System.out.println("Wrong Input"); System.exit(0);
                                }
                                   
                        case 5: MoviesList(user,WatchHistory,WatchLater,RatingsFile,x); // back to main menu
                    }
                }
                }
                if(m2 == database.movieslist.getLast().getMovieID() + 1){
                            user.menu();MainMenu(user,WatchHistory,WatchLater,RatingsFile);
                            }else
                               System.out.println("Wrong Input"); System.exit(0);
}

private static void StartRun() throws IOException{
admin = new Admin("Admin","admin@admin.com","128800Aa?","01010679791");
        database = new Database();
        
        // scans the users files and puts it in database string array
            File F = new File("users.txt");
            Scanner sf = new Scanner(F);
            while(sf.hasNextLine()){
                database.usersS.add(sf.nextLine());
            }
        // scans the genre file and puts it in shows string array
            File F3 = new File("movies.txt");
            Scanner sf3 = new Scanner(F3);
            while(sf3.hasNextLine()){
                database.movieslistS.add(sf3.nextLine());
            }
}

private static void UserLogIned(NormalUser user,Shows show) throws IOException{
        // scans the watchlist file and puts it in watchfiles string array
            File F3 = new File("watchhistory.txt");
            Scanner sf3 = new Scanner(F3);
            while(sf3.hasNextLine()){
                user.watchlist.movieshistoryS.add(sf3.nextLine());
            }
                    // scans the watchlater file and puts it in watchfiles string array
            File F5 = new File("watchlater.txt");
            Scanner sf5 = new Scanner(F5);
            while(sf5.hasNextLine()){
                user.watchlater.watchlaterS.add(sf5.nextLine());
            }
            
                        File F4 = new File("Ratings.txt");
            Scanner sf4 = new Scanner(F4);
            while(sf4.hasNextLine()){
                show.RatingS.add(sf4.nextLine());
                
            }
            
}


private static void ForgetPassword() throws IOException{
System.out.println("Enter your email or phonenumber: ");
            String forgotten = s.next();
            if(IsEmailInList(forgotten,database.users) || IsPhoneNumInList(forgotten,database.users)){
                System.out.println("We'll send you a verification code on your email/phonenumber you provided");
                String Code = GenerateVerificationCode(8,false);
                //instead of sending it to user because we can't , we will pass the Code into a file called VerfCode.txt
                FileWriter c = new FileWriter("VerfCode.txt");
                c.write(Code); c.close();
                System.out.println("Please Enter the code you got: ");
                String UserCode = s.next();
                if(UserCode.equals(Code)){
                int k = database.forgetpassword(forgotten);
                NormalUser user = database.getuser(k);
                // edeloh el password el adeem or make him assign a new password w save it
                System.out.println("Welcome ! Reset your password: " );
                     String NewPassword7 =s.next();
                     while(!CheckPasswordTrue(NewPassword7)){
            System.out.println("The Password must contain the following:\n");
            System.out.println("-Numbers\n-Uppercase letters\n-Lowercase letters\n-Special characters"
                    + "\n-Its length must be at least 8 characters");
            NewPassword7 = s.next();
        }
                     System.out.println("Re-Enter your new password:");
                     String NewPassword8 =s.next();
                     if(NewPassword7.equals(NewPassword8)){
                     user.setPassword(NewPassword7);
                     Update(user.getEmail(), NewPassword7, user.getPhonenumber());
                     System.out.println("Your password has been reset successfully\nTry log in again: ");}else{
                         System.out.println("The Passwords don't match! ");
                         draw();
                     }

                login();
                }else{
                    System.out.println("Wrong Code! 1)Try Again\n2)Exit");
                    int f = s.nextInt();
                    switch(f){
                        case 1:ForgetPassword(); break;
                        case 2:System.exit(0);
                        default : System.out.println("Wrong Input!"); System.exit(0);
                    }
                    
                }
            }
}

private static void MainMenu(NormalUser user,String WatchHistory,String WatchLater,String RatingsFile) throws IOException{
int v = s.nextInt();
        switch(v){
            case 1: // 1)Movies
             MoviesList(user,WatchHistory,WatchLater,RatingsFile,0);
              break;
              
            case 2: //2)settings 
                user.settings.PrintUserDetails(user);
                int f =s.nextInt();
                switch(f){
                    case 1: //1)Change Password
                        user.settings.ChangePassword(user);
                        user.menu();
                        MainMenu(user,WatchHistory,WatchLater,RatingsFile);
                        break;
                    case 2: //2)Change Phone Number
                        user.settings.ChangePhoneNumber(user);
                        user.menu();
                        MainMenu(user,WatchHistory,WatchLater,RatingsFile);
                        break;
                    case 3://3)Back to MainMenu
                        user.menu();
                        MainMenu(user,WatchHistory,WatchLater,RatingsFile);break ;
                    default :System.out.println("Wrong Input"); System.exit(0);
                }
                break ;
            case 3: //My Lists
                draw();
                System.out.println("1)My Watch History\n2)Watch Later List");
                draw();
                int w = s.nextInt();
                switch(w){
                    case 1: //1)My Watch History
                        if(!user.watchlist.movieshistory.isEmpty()){
                        user.watchlist.PrintList();// My Watch History
                   MoviesList(user,WatchHistory,WatchLater,RatingsFile,1);break;}else{
                            System.out.println("Your History is empty\n"+ (database.movieslist.getLast().getMovieID() + 1) +")Back to Main Menu");}
                        int a = s.nextInt();
                            if(a == database.movieslist.getLast().getMovieID() + 1){
                            user.menu();MainMenu(user,WatchHistory,WatchLater,RatingsFile); break;
                            }else
                                System.out.println("Wrong Input"); System.exit(0);
                    case 2: //2)Watch Later List
                        if(!user.watchlater.watchlater.isEmpty()){
                        user.watchlater.PrintList();//Watch Later      
                   MoviesList(user,WatchHistory,WatchLater,RatingsFile,1);break;}else{
                            System.out.println("Your Watch Later list is empty\n"+ (database.movieslist.getLast().getMovieID() + 1) +")Back to Main Menu");}
                        int d = s.nextInt();
                        if(d == database.movieslist.getLast().getMovieID() + 1){
                            user.menu();MainMenu(user,WatchHistory,WatchLater,RatingsFile); break;
                            }else
                                System.out.println("Wrong Input"); System.exit(0);
                }
                
                case 4 : //Support
                Support(user,WatchHistory,WatchLater,RatingsFile); 
                    break;
                
            case 5 : // log out
                draweq();
                System.out.println("Welcome to Netflix!");
                draweq();
                System.out.println("1)Login\n2)New User\n3)Exit");
                      s = new Scanner(System.in);
                      s.useDelimiter("\\n"); 
                      int o = s.nextInt();
                      switch(o){
                          case 1:login(); break ;
                          case 2:newuser(); break ;
                          case 3:System.exit(0);
                          default : System.out.println("Wrong Input"); System.exit(0);
                      }
        }
}

private static void PlanChoose(NormalUser user,String UsersFile,FileWriter f) throws IOException{
        draweq();
                System.out.println("Choose your plan:");
        draweq();        
                user.basic.displaySubscriptionDetails();
                user.standard.displaySubscriptionDetails();
                user.premium.displaySubscriptionDetails();
                int p1 = s.nextInt();
                switch(p1){
                    case 1:
                        user.setUserPlanName("Basic");
                        break;
                    case 2:
                        user.setUserPlanName("Standard");
                        break;
                    case 3:   
                        user.setUserPlanName("Premium");
                        break;
                    default : System.out.println("Wrong Input"); System.exit(0);
                }

                System.out.println("Choose Your payment method:\n1)Credit/Debit Card\n2)PayPal");
                int p = s.nextInt();
                switch(p){
                    case 1: user.payment.AddCard(); break;
                    case 2: user.payment.AddPayPal(); break;
                    default:
                        System.out.println("Wrong Input"); System.exit(0);
                }

                database.adduser(user);
                f.write(UsersFile + user.ToString());
                f.close();
                draw();
                System.out.println("User created successufully !");
                draw();
                System.out.println("Login to your account: ");
                login();
}

public static boolean CheckMovieInList(Movies movie,ArrayList<Movies> WatchLater){

        return WatchLater.contains(movie); //returns true if the movie is already in the watchlist

}
public static void draw(){
    System.out.println("-------------------------------------");
}
public static void draweq(){
    System.out.println("======================================");
}
}
