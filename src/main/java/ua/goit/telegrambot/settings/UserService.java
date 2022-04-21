package ua.goit.telegrambot.settings;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.api.service.NBUCurrencyService;
import ua.goit.telegrambot.api.service.PrivateBankCurrencyService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
public class UserService {
    private static volatile UserService instance;
    private StorageOfUsers userStorage;

    NBUCurrencyService nbuCurrencyService = new NBUCurrencyService();
    PrivateBankCurrencyService privateBankCurrencyService = new PrivateBankCurrencyService();
    MonoCurrencyService monoCurrencyService = new MonoCurrencyService();

    public UserService() {
        userStorage = StorageOfUsers.getInstance();
    }

    public static UserService getInstance() { //«блокировка с двойной проверкой» (Double-Checked Locking)
        UserService result = instance;
        if (result != null) {
            return result;
        }
        synchronized (StorageOfUsers.class) {
            if (instance == null) {
                instance = new UserService();
            }
            return instance;
        }
    }

    public void createUser(int userId) {
        userStorage.add(new User(userId));
    }

    public void setBank(int userId, String bank) {
        userStorage.get(userId).setBank(bank);
    }

    public String getBank(int userId) {
        return userStorage.get(userId).getBank();
    }

    public void setRounding(int userId, int rounding) {
        userStorage.get(userId).setRounding(rounding);
    }

    public void setUsd(int userId, boolean usd) {
        userStorage.get(userId).setUsd(usd);
    }

    public void setEur(int userId, boolean eur) {
        userStorage.get(userId).setEur(eur);
    }

    public void setGbp(int userId, boolean gbp) {
        userStorage.get(userId).setGbp(gbp);
    }

    public int getRounding(int userId) {
        return userStorage.get(userId).getRounding();
    }

    public boolean getUsd(int userId) {
        return userStorage.get(userId).isUsd();
    }

    public boolean getEur(int userId) {
        return userStorage.get(userId).isEur();
    }

    public boolean getGbp(int userId) {
        return userStorage.get(userId).isGbp();
    }

    public boolean getScheduler(int userId) {
        return userStorage.get(userId).isScheduler();
    }

    public int getSchedulerTime(int userId) {
        return userStorage.get(userId).getSchedulerTime();
    }

    public void setScheduler(int userId, boolean scheduler) {
        userStorage.get(userId).setScheduler(scheduler);
    }

    public void setSchedulerTime(int userId, int time) {
        userStorage.get(userId).setSchedulerTime(time);
    }

    public boolean getEnglish(int userId) {
        return userStorage.get(userId).isEnglish();
    }

    public boolean getUkrainian(int userId) {
        return userStorage.get(userId).isUkrainian();
    }

    public String getCurrency(int userId) {
        if (getUsd(userId)) {
            return "usd";
        } else if (getEur(userId)) {
            return "eur";
        } else {
            return "gbp";
        }
    }

    public List<Integer> getUsersWithNotficationOnCurrentHour(int time) {
        return userStorage.getUsersWithNotficationOnCurrentHour(time);
    }

//    public boolean getIsEnglish(int userId){
//        return userStorage.get(userId).getIsEnglish();
//    }
//
//    public void setIsEnglish(int userId, boolean english){
//        userStorage.get(userId).setIsEnglish(english);
//    }


    public String getInfo(int userId) {
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
                double purchaseRate = Precision.round(purchaseRate1,rounding);
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "NBU", currencyPairUsd, String.format("%."+rounding+"f",purchaseRate));

            }
            if (getEur(userId)) {
                double purchaseRate1 = nbuCurrencyService.getRate(Currency.EUR).get("rateEUR");
                double purchaseRate = Precision.round(purchaseRate1,rounding);
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "NBU", currencyPairEur, String.format("%."+rounding+"f",purchaseRate));

            }
            if (getGbp(userId)) {
                double purchaseRate1 = nbuCurrencyService.getRate(Currency.GBP).get("rateGBP");
                double purchaseRate = Precision.round(purchaseRate1,rounding);
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "NBU", currencyPairGbp, String.format("%."+rounding+"f",purchaseRate));

            }
        }

        if (bank.equals("monobank")) {
            if (getUsd(userId)) {
                double purchaseRate = monoCurrencyService.getRate(Currency.USD).get("buyUSD");
                double saleRate = monoCurrencyService.getRate(Currency.USD).get("SellUSD");
                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Monobank", currencyPairUsd, String.format("%."+rounding+"f",purchaseRate));
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Monobank", currencyPairUsd, String.format("%."+rounding+"f",purchaseRate), String.format("%."+rounding+"f",saleRate));
                }
            }
            if (getEur(userId)) {
                double purchaseRate = monoCurrencyService.getRate(Currency.EUR).get("buyEUR");
                double saleRate = monoCurrencyService.getRate(Currency.EUR).get("SellEUR");

                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Monobank", currencyPairEur, String.format("%."+rounding+"f",purchaseRate));
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Monobank", currencyPairEur, String.format("%."+rounding+"f",purchaseRate), String.format("%."+rounding+"f",saleRate));
                }
            }
            if (getGbp(userId)) {
                double purchaseRate1 = monoCurrencyService.getRate(Currency.GBP).get("crossGBP");
                double purchaseRate = Precision.round(purchaseRate1,rounding);
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Monobank",currencyPairGbp, String.format("%."+rounding+"f",purchaseRate));

            }
        }

        if (bank.equals("privat")) {
            if (getUsd(userId)) {
                double purchaseRate = privateBankCurrencyService.getRate(Currency.USD).get("buyUSD");
                double saleRate = privateBankCurrencyService.getRate(Currency.USD).get("sellUSD");
                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Private", currencyPairUsd, String.format("%."+rounding+"f",purchaseRate));
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Private", currencyPairUsd, String.format("%."+rounding+"f",purchaseRate), String.format("%."+rounding+"f",saleRate));
                }
            }
            if (getEur(userId)) {
                double purchaseRate = privateBankCurrencyService.getRate(Currency.EUR).get("sellEUR");
                double saleRate = privateBankCurrencyService.getRate(Currency.EUR).get("buyEUR");
                if (saleRate == 0) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Privat", currencyPairEur, String.format("%."+rounding+"f",saleRate));
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Privat", currencyPairEur, String.format("%."+rounding+"f",saleRate), String.format("%."+rounding+"f",purchaseRate));
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
}
