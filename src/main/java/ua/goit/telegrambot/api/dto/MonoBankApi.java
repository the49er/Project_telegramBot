package ua.goit.telegrambot.api.dto;

import lombok.Data;
import org.decimal4j.util.DoubleRounder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;


public class MonoBankApi extends BankApi {
    private static final String API_LINK = "https://api.monobank.ua/bank/currency";
    private String jsonFilePath = "src/monoBank_rates.json";

    @Override
    public void getRatesAndSaveToJsonFile() {
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
    }

    @Override
    public double getCurrencyBuyRatesByUserRequest(int currencyA) {
        double result =  Arrays.stream(getRatesList())
                .filter(it -> it.getCurrencyCodeA() == currencyA)
                .filter(it -> it.getCurrencyCodeB() == Currency.UAH.getCurrency())
                .map(MonoBankJsonResponse::getRateBuy)
                .findFirst()
                .orElseThrow();

        double buy = DoubleRounder.round(result, 2);

        return buy;
    }

    @Override
    public double getCurrencySellRatesByUserRequest(int currencyA) {
        double result =  Arrays.stream(getRatesList())
                .filter(it -> it.getCurrencyCodeA() == currencyA)
                .filter(it -> it.getCurrencyCodeB() == Currency.UAH.getCurrency())
                .map(MonoBankJsonResponse::getRateSell)
                .findFirst()
                .orElseThrow();

        double sell = DoubleRounder.round(result, 4);

        return sell;
    }
    public MonoBankJsonResponse[] getRatesList(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src/monoBank_rates.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuffer stringBuffer = new StringBuffer();
        try {
            int i;
            while ((i = fileReader.read()) != -1) {
                stringBuffer.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
          String resultSb = stringBuffer.toString();

        MonoBankJsonResponse[] currenciesRates = BankApi.GSON.fromJson(resultSb, MonoBankJsonResponse[].class);
        return currenciesRates;
    }


    @Data
    public class MonoBankJsonResponse {
        int currencyCodeA;
        int currencyCodeB;
        long date;
        float rateSell;
        float rateBuy;
        float rateCross;
    }
}

