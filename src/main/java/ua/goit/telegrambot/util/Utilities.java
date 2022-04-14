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

    //check get currency from api
    public static boolean checkCurrency(){
        boolean err = FileUpdate.isCheckErr();
        return err;
    }

}
