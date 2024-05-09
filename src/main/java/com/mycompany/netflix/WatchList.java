
package com.mycompany.netflix;

import java.util.ArrayList;

public class WatchList {
    

    
     public static ArrayList<Movies> movieshistory = new ArrayList<Movies>();
     ArrayList<String> movieshistoryS = new ArrayList<String>();

     
      public void AddMovie(Movies M){
          movieshistory.add(M);
      }
      
      public String ToString(){
          String s = "";
          for(Movies temp:movieshistory){
              if(temp.equals(movieshistory.getLast())){
                  s+= temp.getTitle();
              }else{
          s += temp.getTitle()+"-";}
      }
          return s;
      }
      public void PrintList(){
System.out.println((Netflix.database.movieslist.getLast().getMovieID() + 1)+")Back to Main Menu");
        for(Movies temp:movieshistory){
            System.out.println(temp.getMovieID()+")"+temp.getTitle());
        }
    }

}
