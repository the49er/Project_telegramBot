package ua.goit.telegrambot.api.dto;


public enum Currency {
    USD(840),
    EUR(978),
    UAH(980);


    private int currency;

    Currency(int currency) {
        this.currency = currency;
    }

    public int getCurrency(){
        return currency;
    }
}
