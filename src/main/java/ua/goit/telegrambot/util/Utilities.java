package ua.goit.telegrambot.util;

import org.jsoup.Jsoup;
import ua.goit.telegrambot.api.currency.FileUpdate;

import java.io.IOException;

public final class Utilities {

    //Get request from API
    public static String getAPIRequest(String url){
        String json;
        try {
            json = Jsoup
                    .connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't connect to API");
        }
        return json;
    }

    //check for error from API
    public static boolean checkCurrency(){
        return FileUpdate.isCheckErr();
    }

}
