package ua.goit.telegrambot.api.currency;

import lombok.Getter;
import ua.goit.telegrambot.api.currency.dto.Currency;
import ua.goit.telegrambot.util.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUpdate extends Thread {
    private static final String ABSOLUTE_PATH = "src\\main\\resources\\Currency_rates.json";
    @Getter
    private static boolean checkErr;

    //update file with currency every 6 min
    @Override
    public void run() {

        while (true) {

            try {
                CurrencyService currencyServiceNBU = new NBUCurrencyService();
                List<Double> nbuRateUSD = currencyServiceNBU.getRate(Currency.USD);
                List<Double> nbuRateEUR = currencyServiceNBU.getRate(Currency.EUR);
                List<Double> nbuRateGBP = currencyServiceNBU.getRate(Currency.GBP);

                CurrencyService currencyServicePrivat = new PrivateBankCurrencyService();
                List<Double> privateRateUSD = currencyServicePrivat.getRate(Currency.USD);
                List<Double> privateRateEUR = currencyServicePrivat.getRate(Currency.EUR);

                CurrencyService currencyServiceMono = new MonoCurrencyService();
                List<Double> monoRateUSD = currencyServiceMono.getRate(Currency.USD);

                File file = new File(ABSOLUTE_PATH);

                if (!file.exists()) {
                    file.getParentFile().mkdirs();

                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[\n" + //Create a file manually to avoid boxing and unboxing
                            "{\n" +
                            "  \"bank\": \"NBU\",\n" +
                            "  \"rateUSD\": \"" + nbuRateUSD.get(0) + "\",\n" +
                            "  \"rateEUR\": \"" + nbuRateEUR.get(0) + "\",\n" +
                            "  \"rateGBP\": \"" + nbuRateGBP.get(0) + "\"\n" +
                            "},\n" +
                            "{\n" +
                            "  \"bank\": \"Privat\",\n" +
                            "  \"buyUSD\": \"" + privateRateUSD.get(0) + "\",\n" +
                            "  \"saleUSD\": \"" + privateRateUSD.get(1) + "\",\n" +
                            "  \"buyEUR\": \"" + privateRateEUR.get(0) + "\",\n" +
                            "  \"saleEUR\": \"" + privateRateEUR.get(1) + "\"\n" +
                            "},\n" +
                            "{\n" +
                            "  \"bank\": \"Mono\",\n" +
                            "  \"buyUSD\": \"" + monoRateUSD.get(0) + "\",\n" +
                            "  \"saleUSD\": \"" + monoRateUSD.get(1) + "\",\n" +
                            "  \"buyEUR\": \"" + monoRateUSD.get(2) + "\",\n" +
                            "  \"saleEUR\": \"" + monoRateUSD.get(3) + "\",\n" +
                            "  \"CrossCurseGBP\": \"" + monoRateUSD.get(4) + "\"\n" +
                            "}\n" +
                            "]");
                    checkErr = true;//
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }

            } catch (IllegalStateException e) {
                System.err.println(e.getMessage());
                checkErr = false;
            } finally {
                Utilities.wait(360);
            }
        }
    }
}