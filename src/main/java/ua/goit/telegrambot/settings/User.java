package ua.goit.telegrambot.settings;

//TODO telegram bot API has own User class -> please rename this entity to BotUser or smth
public class User {
    private final int id;
    private String bank; //TODO we have to make it ENUM or CONSTANT
    private boolean usd;
    private boolean eur;
    private boolean rub;
    private int rounding;
    private boolean scheduler;
    private int schedulerTime;
    private boolean english; // if it false, then language is ukrainian
    //TODO currency field is missing, need to be ENUM or CONSTANT

    public int getSchedulerTime() {
        return schedulerTime;
    }

    public void setSchedulerTime(int schedulerTime) {
        this.schedulerTime = schedulerTime;
    }

    public User(int id) {
        this.id = id;
        bank = "NBU";
        usd = true;
        eur = true;
        rub = true;
        rounding = 2;
        scheduler = true;
        schedulerTime = 9;
        english = true;
    }

    public int getId() {
        return id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public boolean isUsd() {
        return usd;
    }

    public void setUsd(boolean usd) {
        this.usd = usd;
    }

    public boolean isEur() {
        return eur;
    }

    public void setEur(boolean eur) {
        this.eur = eur;
    }

    public boolean isRub() {
        return rub;
    }

    public void setRub(boolean rub) {
        this.rub = rub;
    }

    public int getRounding() {
        return rounding;
    }

    public void setRounding(int rounding) {
        this.rounding = rounding;
    }

    public boolean isScheduler() {
        return scheduler;
    }

    public void setScheduler(boolean scheduler) {
        this.scheduler = scheduler;
    }

    public boolean getIsEnglish(){ return english; }

    public void setIsEnglish(boolean english){ this.english = english; }

}
