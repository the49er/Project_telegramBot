package ua.goit.telegrambot.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;

import java.io.IOException;

public abstract class ApiService {

    public Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String getJsoup(String URL) {
        String text = null;
        try {
            text = Jsoup
                    .connect(URL)
                    .timeout(5000)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }
}