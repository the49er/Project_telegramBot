package ua.goit.telegrambot.settings;

import java.util.List;

public class UserService {
    private static volatile UserService instance;
    private StorageOfUsers userStorage;

    public UserService() {
        userStorage = StorageOfUsers.getInstance();
    }

    public static UserService getInstance() { //«блокировка с двойной проверкой» (Double-Checked Locking)
        UserService result = instance;
        if (result != null) {
            return result;
        }
        synchronized (StorageOfUsers.class) {
            if (instance == null) {
                instance = new UserService();
            }
            return instance;
        }
    }

    public void createUser(int userId){
        userStorage.add(new User(userId));
    }

    public void setBank(int userId, String bank) {
        userStorage.get(userId).setBank(bank);
    }

    public String getBank(int userId){
        return  userStorage.get(userId).getBank();
    }

    public void setRounding(int userId, int rounding){
        userStorage.get(userId).setRounding(rounding);
    }

    public void setUsd(int userId, boolean usd){
        userStorage.get(userId).setUsd(usd);
    }

    public void setEur(int userId, boolean eur){
        userStorage.get(userId).setEur(eur);
    }

    public void setGbp(int userId, boolean gbp){
        userStorage.get(userId).setGbp(gbp);
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

    public boolean getGbp(int userId) {
        return userStorage.get(userId).isGbp();
    }

    public boolean getScheduler(int userId) { return userStorage.get(userId).isScheduler(); }

    public int getSchedulerTime(int userId) { return userStorage.get(userId).getSchedulerTime(); }

    public void setScheduler(int userId, boolean scheduler) {
        userStorage.get(userId).setScheduler(scheduler);
    }

    public void setSchedulerTime(int userId, int time) {
        userStorage.get(userId).setSchedulerTime(time);
    }

    public String getCurrency(int userId){
        if (getUsd(userId)){
            return "usd";
        }else if (getEur(userId)){
            return "eur";
        }else {
            return "gbp";
        }
    }

    public List<Integer> getUsersWithNotficationOnCurrentHour(int time) {
        return userStorage.getUsersWithNotficationOnCurrentHour(time);
    }

//    public boolean getIsEnglish(int userId){
//        return userStorage.get(userId).getIsEnglish();
//    }
//
//    public void setIsEnglish(int userId, boolean english){
//        userStorage.get(userId).setIsEnglish(english);
//    }


    public String getInfo(int userId){
        String bank = getBank(userId);
        boolean usd = getUsd(userId);
        boolean eur = getEur(userId);
        boolean gbp = getGbp(userId);
        int rounding = getRounding(userId);
        //получение курса валют
        //HashMap<String, Currency> currenciesData = data.getCurrenciesByBank(bank);
        return "test";//красивое фориматирование всех данных
    }

}
