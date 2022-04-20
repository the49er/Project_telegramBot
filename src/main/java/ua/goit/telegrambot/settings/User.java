package ua.goit.telegrambot.settings;

import lombok.Data;

//TODO telegram bot API has own User class -> please rename this entity to BotUser or smth
@Data
public class User {
    private final int id;
    private String bank; //TODO we have to make it ENUM or CONSTANT
    private boolean usd;
    private boolean eur;
    private boolean gbp;
    private int rounding;
    private boolean scheduler;
    private int schedulerTime;
    private boolean isEnglish;
    private boolean isUkrainian;
    //TODO currency field is missing, need to be ENUM or CONSTANT

    public int getSchedulerTime() {
        return schedulerTime;
    }

    public void setSchedulerTime(int schedulerTime) {
        this.schedulerTime = schedulerTime;
    }

    public User(int id) {
        this.id = id;
        bank = "nbu";
        usd = true;
        eur = false;
        gbp = false;
        rounding = 2;
        scheduler = true;
        schedulerTime = 13;
        isEnglish = true;
        isUkrainian = false;
    }

//    public int getId() {
//        return id;
//    }
//
//    public String getBank() {
//        return bank;
//    }
//
//    public void setBank(String bank) {
//        this.bank = bank;
//    }
//
//    public boolean isUsd() {
//        return usd;
//    }
//
//    public void setUsd(boolean usd) {
//        this.usd = usd;
//    }
//
//    public boolean isEur() {
//        return eur;
//    }
//
//    public void setEur(boolean eur) {
//        this.eur = eur;
//    }
//
//    public boolean isGbp() {
//        return gbp;
//    }
//
//    public void setGbp(boolean rub) {
//        this.gbp = gbp;
//    }
//
//    public int getRounding() {
//        return rounding;
//    }
//
//    public void setRounding(int rounding) {
//        this.rounding = rounding;
//    }
//
//    public boolean isScheduler() {
//        return scheduler;
//    }
//
//    public void setScheduler(boolean scheduler) {
//        this.scheduler = scheduler;
//    }
//
//    public boolean getIsEnglish(){ return english; }
//
//    public void setIsEnglish(boolean english){ this.english = english; }

}
