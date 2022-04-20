package ua.goit.telegrambot.settings;

import lombok.extern.slf4j.Slf4j;
import ua.goit.telegrambot.api.dto.Currency;
import ua.goit.telegrambot.api.service.MonoCurrencyService;
import ua.goit.telegrambot.api.service.NBUCurrencyService;
import ua.goit.telegrambot.api.service.PrivateBankCurrencyService;

import java.math.BigDecimal;
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
        String currencyPairEur = "UAH/USD";
        String currencyPairGbp = "UAH/USD";
        if (bank.equals("nbu")) {

            if (getUsd(userId)) {
                BigDecimal purchaseRate = nbuCurrencyService.getRate(Currency.USD).get("rateUSD");
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "NBU", currencyPairUsd, purchaseRate);

            }
            if (getEur(userId)) {
                BigDecimal purchaseRate = nbuCurrencyService.getRate(Currency.EUR).get("rateEur");
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "NBU", currencyPairEur, purchaseRate);

            }
            if (getGbp(userId)) {
                BigDecimal purchaseRate = nbuCurrencyService.getRate(Currency.GBP).get("rateGBP");
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "NBU", currencyPairGbp, purchaseRate);

            }
        }

        if (bank.equals("monobank")) {
            if (getUsd(userId)) {
                BigDecimal purchaseRate = monoCurrencyService.getRate(Currency.USD).get("buyUSD");
                BigDecimal saleRate = monoCurrencyService.getRate(Currency.USD).get("sellUSD");
                if (saleRate == null) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Monobank", currencyPairUsd, purchaseRate);
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Monobank", currencyPairUsd, purchaseRate, saleRate);
                }
            }
            if (getEur(userId)) {
                BigDecimal purchaseRate = monoCurrencyService.getRate(Currency.USD).get("buyEUR");
                BigDecimal saleRate = monoCurrencyService.getRate(Currency.USD).get("sellEUR");
                if (saleRate == null) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Monobank", currencyPairEur, purchaseRate);
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Monobank", currencyPairEur, purchaseRate, saleRate);
                }
            }
            if (getGbp(userId)) {
                BigDecimal purchaseRate = monoCurrencyService.getRate(Currency.GBP).get("rateGBP");
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: ⏳\n Sale: ⏳ ", "NBU", currencyPairGbp, purchaseRate);

            }
        }

        if (bank.equals("private")) {
            if (getUsd(userId)) {
                BigDecimal purchaseRate = privateBankCurrencyService.getRate(Currency.USD).get("buyUSD");
                BigDecimal saleRate = privateBankCurrencyService.getRate(Currency.USD).get("SellUSD");
                if (saleRate == null) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Private", currencyPairUsd, purchaseRate);
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Private", currencyPairUsd, purchaseRate, saleRate);
                }
            }
            if (getEur(userId)) {
                BigDecimal purchaseRate = privateBankCurrencyService.getRate(Currency.USD).get("buyEUR");
                BigDecimal saleRate = privateBankCurrencyService.getRate(Currency.USD).get("SellEUR");
                if (saleRate == null) {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: ⏳ ", "Private", currencyPairEur, purchaseRate);
                } else {
                    result = MessageFormat
                            .format("{0} exchange rate: {1}\n Purchase: {2}\n Sale: {3}", "Private", currencyPairEur, purchaseRate, saleRate);
                }
            }
            if (getGbp(userId)) {
                BigDecimal purchaseRate = privateBankCurrencyService.getRate(Currency.GBP).get("crossGBP");
                result = MessageFormat
                        .format("{0} exchange rate: {1}\n Purchase: ⏳\n Sale: ⏳ ", "Private", currencyPairGbp, purchaseRate);

            }
        }
        log.info(result);
        return result;
    }
}
