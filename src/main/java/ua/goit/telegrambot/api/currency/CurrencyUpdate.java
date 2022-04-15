package ua.goit.telegrambot.api.currency;

import lombok.Getter;
import ua.goit.telegrambot.api.currency.dto.Currency;
import ua.goit.telegrambot.util.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CurrencyUpdate implements Runnable {
    private static final String ABSOLUTE_PATH_NBU = "src\\main\\resources\\Currency_NBU_rates.json";
    private static final String ABSOLUTE_PATH_PRIVAT = "src\\main\\resources\\Currency_Privat_rates.json";
    private static final String ABSOLUTE_PATH_MONO = "src\\main\\resources\\Currency_Mono_rates.json";

    @Getter
    private static boolean nbuCheckErr = true;
    @Getter
    private static boolean privatCheckErr = true;
    @Getter
    private static boolean monoCheckErr = true;

    @Override
    public void run() {

        //NBU currency update
        final Runnable nbu = () ->
        {

            while (true) {

                try {
                    CurrencyService currencyServiceNBU = new NBUCurrencyService();
                    List<Double> nbuRateUSD = currencyServiceNBU.getRate(Currency.USD);
                    List<Double> nbuRateEUR = currencyServiceNBU.getRate(Currency.EUR);
                    List<Double> nbuRateGBP = currencyServiceNBU.getRate(Currency.GBP);

                    File file = new File(ABSOLUTE_PATH_NBU);

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
                                "}\n" +
                                "]");
                        nbuCheckErr = false;
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }

                } catch (IllegalStateException e) {
                    System.err.println(e.getMessage());
                    System.err.println("Can't connect to NBU API");
                    nbuCheckErr = true;
                } finally {
                    Utilities.wait(360);
                }
            }
        };

        //Privat currency update
        final Runnable privat = () ->
        {
            while (true) {

                try {
                    CurrencyService currencyServicePrivat = new PrivateBankCurrencyService();
                    List<Double> privateRateUSD = currencyServicePrivat.getRate(Currency.USD);
                    List<Double> privateRateEUR = currencyServicePrivat.getRate(Currency.EUR);

                    File file = new File(ABSOLUTE_PATH_PRIVAT);

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
                                "  \"bank\": \"Privat\",\n" +
                                "  \"buyUSD\": \"" + privateRateUSD.get(0) + "\",\n" +
                                "  \"saleUSD\": \"" + privateRateUSD.get(1) + "\",\n" +
                                "  \"buyEUR\": \"" + privateRateEUR.get(0) + "\",\n" +
                                "  \"saleEUR\": \"" + privateRateEUR.get(1) + "\"\n" +
                                "}\n" +
                                "]");
                        privatCheckErr = false;
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }

                } catch (IllegalStateException e) {
                    System.err.println(e.getMessage());
                    System.err.println("Can't connect to Privat API");
                    privatCheckErr = true;
                } finally {
                    Utilities.wait(360);
                }
            }
        };

        //Mono currency update
        final Runnable mono = () ->
        {
            while (true) {

                try {
                    CurrencyService currencyServiceMono = new MonoCurrencyService();
                    List<Double> monoRateUSD = currencyServiceMono.getRate(Currency.USD);

                    File file = new File(ABSOLUTE_PATH_MONO);

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
                                "  \"bank\": \"Mono\",\n" +
                                "  \"buyUSD\": \"" + monoRateUSD.get(0) + "\",\n" +
                                "  \"saleUSD\": \"" + monoRateUSD.get(1) + "\",\n" +
                                "  \"buyEUR\": \"" + monoRateUSD.get(2) + "\",\n" +
                                "  \"saleEUR\": \"" + monoRateUSD.get(3) + "\",\n" +
                                "  \"CrossCurseGBP\": \"" + monoRateUSD.get(4) + "\"\n" +
                                "}\n" +
                                "]");
                        monoCheckErr = false;
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }

                } catch (IllegalStateException e) {
                    System.err.println(e.getMessage());
                    System.err.println("Can't connect to Mono API");
                    monoCheckErr = true;
                } finally {
                    Utilities.wait(360);
                }
            }
        };
        new Thread(nbu).start();
        new Thread(privat).start();
        new Thread(mono).start();
    }

}
