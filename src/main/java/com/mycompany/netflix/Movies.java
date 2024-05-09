package com.mycompany.netflix;

public class Movies extends Shows
{

    int GenreID;
    public static int Moviecount = 1;
    int MovieID = Moviecount;
    
    public Movies() {
        Moviecount++;
    }

    public Movies(String Title, String Year, String Month, String Duration, String Languages, String imbd_score, String Country, String Director_name,String Actors) {
        super(Title, Year, Month, Duration, Languages, imbd_score, Country, Director_name,Actors);
        Moviecount++;
    }

    public void setActors(String Actors) {
        this.Actors = Actors;
    }

    public String getActors() {
        return Actors;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int MovieID) {
        this.MovieID = MovieID;
    }

    public int getMoviecount() {
        return Moviecount;
    }


    public int getGenreID() {
        return GenreID;
    }

    public void setGenreID(int GenreID) {
        this.GenreID = GenreID;
    }

    
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String Month) {
        this.Month = Month;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String Languages) {
        this.Languages = Languages;
    }

    public String getImbd_score() {
        return imbd_score;
    }

    public void setImbd_score(String imbd_score) {
        this.imbd_score = imbd_score;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getDirector_name() {
        return Director_name;
    }

    public void setDirector_name(String Director_name) {
        this.Director_name = Director_name;
    }


    public void PrintMovieMenu(){
        System.out.println(getMovieID()+")"+getTitle());
    }

        
        
    public void PrintMovieInfo()
    {
        updateAverageRating();
        Netflix.draweq();
        System.out.println(
                getTitle()+"-("+getMonth()+"/"+getYear()+")"+
                "\nDirector :"+getDirector_name()+
                "\nLanguages:"+getLanguages()+
                "\nCountry :"+getCountry()+
                "\nimbd score :"+getImbd_score()+
                "\nRating :"+getAverageRatinggood() +
                "\n-----------------------\n"+
                "1)Play\n"+
                "2)Add to \"watch later\"\n"+
                "3)Cast\n"+
                "4)Rate \n"+
                "5)Back to Genres\n"
);  
      Netflix.draweq();
    }
    
    

    
    public String ToString(){
        return Title + "," + Year + "," + Month + "," +Duration + "," + Languages + "," +imbd_score +","+Country+","+Director_name+","+GenreID+","+MovieID+","+Actors;
    }
}
