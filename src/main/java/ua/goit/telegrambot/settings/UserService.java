package ua.goit.telegrambot.settings;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.CurrencyService;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.api.service.NBUCurrencyService;
import ua.goit.telegrambot.api.service.PrivateBankCurrencyService;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
public class UserService {
    private static volatile UserService instance;
    private StorageOfUsers userStorage;//final

    NBUCurrencyService nbuCurrencyService = new NBUCurrencyService();//why classes do not have static reference methods
    PrivateBankCurrencyService privateBankCurrencyService = new PrivateBankCurrencyService();
    MonoCurrencyService monoCurrencyService = new MonoCurrencyService();

    private UserService() {
        userStorage = StorageOfUsers.getInstance();
    }

    public static UserService getInstance() { //«блокировка с двойной проверкой» (Double-Checked Locking)
        UserService result = instance;
        if (result != null) {
            return result;
        }
        synchronized (UserService.class) {
            if (instance == null) {
                instance = new UserService();
            }
            return instance;
        }
    }

    public void createUser(long userId) {
        userStorage.add(new User(userId));
    }

    public void setBank(long userId, String bank) {
        userStorage.get(userId).setBank(bank);
    }

    public String getBank(long userId) {
        return userStorage.get(userId).getBank();
    }

    public void setRounding(long userId, int rounding) {
        userStorage.get(userId).setRounding(rounding);
    }

    public void setUsd(long userId, boolean usd) {
        userStorage.get(userId).setUsd(usd);
    }

    public void setEur(long userId, boolean eur) {
        userStorage.get(userId).setEur(eur);
    }

    public void setGbp(long userId, boolean gbp) {
        userStorage.get(userId).setGbp(gbp);
    }

    public int getRounding(long userId) {
        return userStorage.get(userId).getRounding();
    }

    public boolean getUsd(long userId) {
        return userStorage.get(userId).isUsd();
    }

    public boolean getEur(long userId) {
        return userStorage.get(userId).isEur();
    }

    public boolean getGbp(long userId) {
        return userStorage.get(userId).isGbp();
    }

    public boolean getScheduler(long userId) {
        return userStorage.get(userId).isScheduler();
    }

    public int getSchedulerTime(long userId) {
        return userStorage.get(userId).getSchedulerTime();
    }

    public void setScheduler(long userId, boolean scheduler) {
        userStorage.get(userId).setScheduler(scheduler);
    }

    public void setSchedulerTime(long userId, int time) {
        userStorage.get(userId).setSchedulerTime(time);
    }

    public boolean getEnglish(long userId) {
        return userStorage.get(userId).isEnglish();
    }

    public boolean getUkrainian(long userId) {
        return userStorage.get(userId).isUkrainian();
    }

    public void setEnglish(long userId, boolean english) {
        userStorage.get(userId).setEnglish(english);
    }

    public void setUkrainian(long userId, boolean english) {
        userStorage.get(userId).setUkrainian(english);
    }


    public String getCurrency(long userId) {
        if (getUsd(userId)) {
            return "usd";
        } else if (getEur(userId)) {
            return "eur";
        } else {
            return "gbp";
        }
    }

    public List<Long> getUsersWithNotificationOnCurrentHour(int time) {
        return userStorage.getUsersWithNotficationOnCurrentHour(time);
    }


    public String getInfo(long userId) {//lots of repetative code
        String bank = getBank(userId);
        int rounding = getRounding(userId);
        String result = "";
        String currencyPairUsd = "UAH/USD";
        String currencyPairEur = "UAH/EUR";
        String currencyPairGbp = "UAH/GBP";
        if (bank.equals("nbu")) {

            if (getUsd(userId)) result = checkForCurrencyAndPring(nbuCurrencyService, rounding, currencyPairUsd);
            if (getEur(userId)) result = checkForCurrencyAndPring(nbuCurrencyService, rounding, currencyPairEur);
            if (getGbp(userId)) result = checkForCurrencyAndPring(nbuCurrencyService, rounding, currencyPairGbp);

        }

        if (bank.equals("monobank")) {
            if (getUsd(userId)) {
                double purchaseRate = monoCurrencyService.getRate(Currency.USD).get("buyUSD");
                double saleRate = monoCurrencyService.getRate(Currency.USD).get("SellUSD");
                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Monobank", currencyPairUsd, String.format("%." + rounding + "f", purchaseRate));
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Monobank", currencyPairUsd, String.format("%." + rounding + "f", purchaseRate), String.format("%." + rounding + "f", saleRate));
                }
            }
            if (getEur(userId)) {
                double purchaseRate = monoCurrencyService.getRate(Currency.EUR).get("buyEUR");
                double saleRate = monoCurrencyService.getRate(Currency.EUR).get("SellEUR");

                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Monobank", currencyPairEur, String.format("%." + rounding + "f", purchaseRate));
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Monobank", currencyPairEur, String.format("%." + rounding + "f", purchaseRate), String.format("%." + rounding + "f", saleRate));
                }
            }
            if (getGbp(userId)) {
                double purchaseRate1 = monoCurrencyService.getRate(Currency.GBP).get("crossGBP");
                double purchaseRate = Precision.round(purchaseRate1, rounding);
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Monobank", currencyPairGbp, String.format("%." + rounding + "f", purchaseRate));

            }
        }

        if (bank.equals("privat")) {
            if (getUsd(userId)) {
                double purchaseRate = privateBankCurrencyService.getRate(Currency.USD).get("buyUSD");
                double saleRate = privateBankCurrencyService.getRate(Currency.USD).get("sellUSD");
                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Private", currencyPairUsd, String.format("%." + rounding + "f", purchaseRate));
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Private", currencyPairUsd, String.format("%." + rounding + "f", purchaseRate), String.format("%." + rounding + "f", saleRate));
                }
            }
            if (getEur(userId)) {
                double purchaseRate = privateBankCurrencyService.getRate(Currency.EUR).get("sellEUR");
                double saleRate = privateBankCurrencyService.getRate(Currency.EUR).get("buyEUR");
                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Privat", currencyPairEur, String.format("%." + rounding + "f", saleRate));
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Privat", currencyPairEur, String.format("%." + rounding + "f", saleRate), String.format("%." + rounding + "f", purchaseRate));
                }
            }
            if (getGbp(userId)) {
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: ⏳\n Sale: ⏳ ", "Privat", currencyPairGbp);

            }
        }
        log.info(result);
        return result;
    }

    private static String checkForCurrencyAndPring(CurrencyService currencyService, int rounding, String currencyPair) {
        double purchaseRate1 = currencyService.getRate(Currency.USD).get("rateUSD");
        double purchaseRate = Precision.round(purchaseRate1, rounding);
        return MessageFormat
                .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "NBU", currencyPair, String.format("%." + rounding + "f", purchaseRate));
    }

    public String getInfoUkr(long userId) {
        String bank = getBank(userId);
        boolean usd = getUsd(userId);
        boolean eur = getEur(userId);
        boolean gbp = getGbp(userId);
        int rounding = getRounding(userId);
        String result = "";
        String currencyPairUsd = "UAH/USD";
        String currencyPairEur = "UAH/EUR";
        String currencyPairGbp = "UAH/GBP";
        if (bank.equals("nbu")) {

            if (getUsd(userId)) {
                double purchaseRate1 = nbuCurrencyService.getRate(Currency.USD).get("rateUSD");
                double purchaseRate = Precision.round(purchaseRate1, rounding);
                result = MessageFormat
                        .format("{0} курс валют: {1}\n купівля: {2}\n продаж: ⏳ ", "НБУ", currencyPairUsd, String.format("%." + rounding + "f", purchaseRate));

            }
            if (getEur(userId)) {
                double purchaseRate1 = nbuCurrencyService.getRate(Currency.EUR).get("rateEUR");
                double purchaseRate = Precision.round(purchaseRate1, rounding);
                result = MessageFormat
                        .format("{0} курс валют: {1}\n купівля: {2}\n продаж: ⏳ ", "НБУ", currencyPairEur, String.format("%." + rounding + "f", purchaseRate));

            }
            if (getGbp(userId)) {
                double purchaseRate1 = nbuCurrencyService.getRate(Currency.GBP).get("rateGBP");
                double purchaseRate = Precision.round(purchaseRate1, rounding);
                result = MessageFormat
                        .format("{0} курс валют: {1}\n купівля: {2}\n продаж: ⏳ ", "НБУ", currencyPairGbp, String.format("%." + rounding + "f", purchaseRate));

            }
        }

        if (bank.equals("monobank")) {
            if (getUsd(userId)) {
                double purchaseRate = monoCurrencyService.getRate(Currency.USD).get("buyUSD");
                double saleRate = monoCurrencyService.getRate(Currency.USD).get("SellUSD");
                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} курс валют: {1}\n купівля: {2}\n продаж: ⏳ ", "МоноБанк", currencyPairUsd, String.format("%." + rounding + "f", purchaseRate));
                } else {
                    result = MessageFormat
                            .format("{0} курс валют: {1}\n купівля: {2}\n продаж: {3}", "МоноБанк", currencyPairUsd, String.format("%." + rounding + "f", purchaseRate), String.format("%." + rounding + "f", saleRate));
                }
            }
            if (getEur(userId)) {
                double purchaseRate = monoCurrencyService.getRate(Currency.EUR).get("buyEUR");
                double saleRate = monoCurrencyService.getRate(Currency.EUR).get("SellEUR");

                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} курс валют: {1}\n купівля: {2}\n продаж: ⏳ ", "МоноБанк", currencyPairEur, String.format("%." + rounding + "f", purchaseRate));
                } else {
                    result = MessageFormat
                            .format("{0} курс валют: {1}\n купівля: {2}\n продаж: {3}", "МоноБанк", currencyPairEur, String.format("%." + rounding + "f", purchaseRate), String.format("%." + rounding + "f", saleRate));
                }
            }
            if (getGbp(userId)) {
                double purchaseRate1 = monoCurrencyService.getRate(Currency.GBP).get("crossGBP");
                double purchaseRate = Precision.round(purchaseRate1, rounding);
                result = MessageFormat
                        .format("{0} курс валют: {1}\n купівля: {2}\n продаж: ⏳ ", "МоноБанк", currencyPairGbp, String.format("%." + rounding + "f", purchaseRate));

            }
        }

        if (bank.equals("privat")) {
            if (getUsd(userId)) {
                double purchaseRate = privateBankCurrencyService.getRate(Currency.USD).get("buyUSD");
                double saleRate = privateBankCurrencyService.getRate(Currency.USD).get("sellUSD");
                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} курс валют: {1}\n купівля: {2}\n продаж: ⏳ ", "ПриватБанк", currencyPairUsd, String.format("%." + rounding + "f", purchaseRate));
                } else {
                    result = MessageFormat
                            .format("{0} курс валют: {1}\n купівля: {2}\n продаж: {3}", "ПриватБанк", currencyPairUsd, String.format("%." + rounding + "f", purchaseRate), String.format("%." + rounding + "f", saleRate));
                }
            }
            if (getEur(userId)) {
                double purchaseRate = privateBankCurrencyService.getRate(Currency.EUR).get("sellEUR");
                double saleRate = privateBankCurrencyService.getRate(Currency.EUR).get("buyEUR");
                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} курс валют: {1}\n купівля: {2}\n продаж: ⏳ ", "ПриватБанк", currencyPairEur, String.format("%." + rounding + "f", saleRate));
                } else {
                    result = MessageFormat
                            .format("{0} курс валют: {1}\n купівля: {2}\n продаж: {3}", "ПриватБанк", currencyPairEur, String.format("%." + rounding + "f", saleRate), String.format("%." + rounding + "f", purchaseRate));
                }
            }
            if (getGbp(userId)) {
                double purchaseRate1 = nbuCurrencyService.getRate(Currency.GBP).get("rateGBP");
                double purchaseRate = Precision.round(purchaseRate1, rounding);
                result = MessageFormat
                        .format("{0} курс валют: {1}\n купівля: {2}\n продаж: ⏳ ", "ПриватБанк", currencyPairGbp, String.format("%." + rounding + "f", purchaseRate));

            }
        }
        log.info(result);
        return result;
    }
}
