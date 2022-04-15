package ua.goit.telegrambot.api;

import lombok.Getter;
import ua.goit.telegrambot.utils.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CurrencyJsonUpdate implements Runnable {
    @Getter
    private static final String ABSOLUTE_PATH_NBU = "src\\main\\resources\\Currency_NBU_rates.json";
    @Getter
    private static final String ABSOLUTE_PATH_PRIVAT = "src\\main\\resources\\Currency_Privat_rates.json";
    @Getter
    private static final String ABSOLUTE_PATH_MONO = "src\\main\\resources\\Currency_Mono_rates.json";
    public static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    public static final String PRIVAT_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    public static final String MONO_URL = "https://api.monobank.ua/bank/currency";


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
                        writer.write(Utilities.getAPIRequest(NBU_URL));
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
                        writer.write(Utilities.getAPIRequest(PRIVAT_URL));
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
                        writer.write(Utilities.getAPIRequest(MONO_URL));
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