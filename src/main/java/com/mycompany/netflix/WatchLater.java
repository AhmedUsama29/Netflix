package com.mycompany.netflix;

import java.util.ArrayList;


public class WatchLater {

     ArrayList<Movies> watchlater = new ArrayList<Movies>();
     ArrayList<String> watchlaterS = new ArrayList<String>();

     public void AddListMovies(ArrayList<Movies> MM){
         watchlater.addAll(MM);
     }
      public void AddMovie(Movies M){
          watchlater.add(M);
      }
      
      public String ToString(){
          String s = "";
          for(Movies temp:watchlater){
              if(temp.equals(watchlater.getLast())){
                  s+= temp.getTitle();
              }else{
          s += temp.getTitle()+"-";}
      }
          return s;
      }
      
    public void PrintList(){
System.out.println((Netflix.database.movieslist.getLast().getMovieID() + 1) + ")Back to Main Menu");
        for(Movies temp:watchlater){
            System.out.println(temp.getMovieID()+")"+temp.getTitle());

        }
    }
}
