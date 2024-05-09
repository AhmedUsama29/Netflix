package com.mycompany.netflix;

import java.util.ArrayList;

public class Subscription {
    private String PlanID;
    private String PlanName;
    private int monthlyPrice;
    private String soundQuality;
    private String videoQuality;
    private String resolution;
    

    public Subscription() {
    }

    public Subscription(String PlanID,String PlanName,int monthlyPrice, String soundQuality, String videoQuality,
                        String resolution) {
        this.PlanID = PlanID;
        this.PlanName = PlanName;
        this.monthlyPrice = monthlyPrice;
        this.soundQuality = soundQuality;
        this.videoQuality = videoQuality;
        this.resolution = resolution;

    }

    public String getPlanID() {
        return PlanID;
    }

    public void setPlanID(String PlanID) {
        this.PlanID = PlanID;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String PlanName) {
        this.PlanName = PlanName;
    }

    
    public int getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(int monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getSoundQuality() {
        return soundQuality;
    }

    public void setSoundQuality(String soundQuality) {
        this.soundQuality = soundQuality;
    }

    public String getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(String videoQuality) {
        this.videoQuality = videoQuality;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    
    
    public void displaySubscriptionDetails() {
        System.out.println(PlanID+")"+PlanName + " :");
        Netflix.draw();
        System.out.println("-Monthly Price: " + monthlyPrice + "$");
        System.out.println("-Sound Quality: " + soundQuality);
        System.out.println("-Video Quality: " + videoQuality);
        System.out.println("-Resolution: " + resolution);
        Netflix.draw();
    }

}