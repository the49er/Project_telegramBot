package ua.goit.telegrambot.util;

import org.jsoup.Jsoup;
import ua.goit.telegrambot.api.currency.CurrencyUpdate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public final class Utilities {

    //Get request from API
    public static String getAPIRequest(String url) {
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
            throw new IllegalStateException("getAPIRequest method error, " +
                    "\nCan't connect to API");
        }
        return json;
    }

    //check for API error
    public static boolean checkNbuCurrencyError() {
        return CurrencyUpdate.isNbuCheckErr();
    }

    public static boolean checkPrivatCurrencyError() {
        return CurrencyUpdate.isPrivatCheckErr();
    }

    public static boolean checkMonoCurrencyError() {
        return CurrencyUpdate.isMonoCheckErr();
    }

    //wait
    public static void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
