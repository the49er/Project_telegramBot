package ua.goit.telegrambot.api.dto.threads;

import ua.goit.telegrambot.api.dto.BankApi;
import ua.goit.telegrambot.api.dto.MonoBankApi;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


public class MonoBankApiRate extends Thread implements Rates {
    private static final String API_LINK = "https://api.monobank.ua/bank/currency";
    private String jsonFilePath = "src/monoBank_rates.json";

    @Override
    public void getRate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_LINK))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = BankApi.CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (FileWriter output = new FileWriter(jsonFilePath)) {
            output.write(response.body());
        } catch (IOException e) {
            e.getStackTrace();
        }
        System.out.println("File has been created " + dateFormat.format(date));
        MonoBankApi monoBankApi = new MonoBankApi();
        System.out.println(monoBankApi.getCurrencyBuyRatesByUserRequest(840));
        System.out.println(monoBankApi.getCurrencySellRatesByUserRequest(840));
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000l);
                getRate();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
