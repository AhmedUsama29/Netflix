package com.mycompany.netflix;

import java.util.ArrayList;
import java.util.Scanner;

public class Shows {
    
    static Scanner s = new Scanner(System.in);
    
    protected String Title ;
    protected String Year ;
    protected String  Month;
    protected String Duration ;
    protected String Languages ;
    protected String imbd_score ;
    protected String Country ;
    protected String Director_name ;
    protected String Actors;
    protected double AverageRating;
    protected ArrayList<Double> Rating = new ArrayList<Double>();
    protected ArrayList<String> RatingS = new ArrayList<String>();

    public Shows() {
    }

    public Shows(String Title, String Year, String Month, String Duration, String Languages, String imbd_score, String Country, String Director_name,String Actors) {
        this.Title = Title;
        this.Year = Year;
        this.Month = Month;
        this.Duration = Duration;
        this.Languages = Languages;
        this.imbd_score = imbd_score;
        this.Country = Country;
        this.Director_name = Director_name;
        this.Actors = Actors;
    }

    public void setRating(ArrayList<Double> Rating) {
        this.Rating = Rating;
    }


       
       public String getAverageRatinggood(){
    String formattedString = String.format("%.2f", AverageRating);
    return formattedString;
}


 public void addRating(double Rate ) {
        if (Rate >= 1 && Rate <= 10){
        Rating.add(Rate);
        updateAverageRating();
        System.out.println("Your rating added successfully");
    }
        else  {
            System.out.println("Invalid rating. Please enter a value between 1 and 10.");
            double c = s.nextDouble();
            addRating(c);
        }
    }


    public void updateAverageRating() {
        double sum = 0;
         for ( int i = 0 ; i < Rating.size() ; i++){
            sum += Rating.get(i);
        }
        AverageRating = sum / Rating.size();
    }

    
    public void ContentGenres ()
    {
        System.out.println("0)Action" + "\n1)Drama" + "\n2)Horror" + "\n3)Fantasy" + "\n4)Science fiction" +
                "\n5)Historical" + "\n6)Animation" + "\n7)Comedy" + "\n8)Documentary" + "\n9)Romantic"+ "\n10)Back to Main Menu");
    }
    
    
    
}
