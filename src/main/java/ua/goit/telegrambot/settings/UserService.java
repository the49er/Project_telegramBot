package ua.goit.telegrambot.settings;

import java.util.List;

public class UserService {
    private static volatile UserService user;
    private StorageOfUsers userStorage;

    public UserService() {
        userStorage = new StorageOfUsers();
    }

    public void createUser(int userId){
        userStorage.add(new User(userId));
    }

    public void setBank(int userId, String bank) {
        userStorage.get(userId).setBank(bank);
    }

    public void setRounding(int userId, int rounding){
        userStorage.get(userId).setRounding(rounding);
    }

    public void setUsd(int userId, boolean usd){
        userStorage.get(userId).setUsd(usd);
    }

    public void setEur(int userId, boolean usd){
        userStorage.get(userId).setUsd(usd);
    }

    public void setRub(int userId, boolean usd){
        userStorage.get(userId).setUsd(usd);
    }

    public int getRounding(int userId){
        return userStorage.get(userId).getRounding();
    }

    public boolean getUsd(int userId) {
        return userStorage.get(userId).isUsd();
    }

    public boolean getEur(int userId) {
        return userStorage.get(userId).isEur();
    }

    public boolean getRub(int userId) {
        return userStorage.get(userId).isRub();
    }

    public boolean getScheduler(int userId) { return userStorage.get(userId).isScheduler(); }

    public int getSchedulerTime(int userId) { return userStorage.get(userId).getSchedulerTime(); }

    public void setScheduler(int userId, boolean scheduler) {
        userStorage.get(userId).setScheduler(scheduler);
    }

    public void setSchedulerTime(int userId, int time) {
        userStorage.get(userId).setSchedulerTime(time);
    }

    public List<Integer> getUsersWithNotficationOnCurrentHour(int time) {
        return userStorage.getUsersWithNotficationOnCurrentHour(time);
    }

    public boolean getIsEnglish(int userId){
        return userStorage.get(userId).getIsEnglish();
    }

    public void setIsEnglish(int userId, boolean english){
        userStorage.get(userId).setIsEnglish(english);
    }


    public String getInfo(int userId){
        String bank = userStorage.get(userId).getBank();
        boolean usd = getUsd(userId);
        boolean eur = getEur(userId);
        boolean rub = getRub(userId);
        int rounding = getRounding(userId);
        //получение курса валют
        //HashMap<String, Currency> currenciesData = data.getCurrenciesByBank(bank);
        return "";//красивое фориматирование всех данных
    }

}
