package ua.goit.telegrambot.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class StorageOfUsers {
    private static volatile StorageOfUsers instance;
    private ConcurrentHashMap<Integer, User> userSettings;

    public StorageOfUsers(){
        userSettings = new ConcurrentHashMap<>();
    }

    public static StorageOfUsers getInstance() { //«блокировка с двойной проверкой» (Double-Checked Locking)
        StorageOfUsers result = instance;
        if (result != null) {
            return result;
        }
        synchronized(StorageOfUsers.class) {
            if (instance == null) {
                instance = new StorageOfUsers();
            }
            return instance;
        }
    }

    public void add(User user){
        userSettings.put(user.getId(), user);
    }

    public User get(int userId) { return userSettings.get(userId); }

    public List<Integer> getUsersWithNotficationOnCurrentHour(int time){
        List<Integer> userIds = new ArrayList<>();
        for (User user : userSettings.values()){
            if (user.isScheduler() && user.getSchedulerTime() == time){
                userIds.add(user.getId());
            }
        }
        return userIds;
    }
}
