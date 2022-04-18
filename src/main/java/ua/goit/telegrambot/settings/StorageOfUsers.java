package ua.goit.telegrambot.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class StorageOfUsers {
    ConcurrentHashMap<Integer, User> userSettings;

    public StorageOfUsers(){
        userSettings = new ConcurrentHashMap<>();
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
