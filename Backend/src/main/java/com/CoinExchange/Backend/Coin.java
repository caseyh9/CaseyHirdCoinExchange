package com.CoinExchange.Backend;

import java.util.HashMap;

public class Coin {

    private String ticker;
    private HashMap<String, Double> buy_prices;
    private HashMap<String, Double> sell_prices;
    private Double min_buy;
    private Double max_sell;
    private String buy_rec;
    private String sell_rec;

    public Coin() {}

    public Coin(String ticker) {
        this.ticker = ticker;
        buy_prices = new HashMap<>();
        sell_prices = new HashMap<>();
    }

    public void insertPrice(String type, String exchange, Double price) {
        if (type.equals("buy"))
            insertBuyPrice(exchange, price);
        else if (type.equals("sell"))
            insertSellPrice(exchange, price);
    }

    private void insertBuyPrice(String exchange, Double price) {
        if (buy_prices.isEmpty() || price < min_buy) {
            min_buy = price;
            buy_rec = exchange;
        }
        buy_prices.put(exchange, price);
    }

    private void insertSellPrice(String exchange, Double price) {
        if (sell_prices.isEmpty() || price > max_sell) {
            max_sell = price;
            sell_rec = exchange;
        }
        sell_prices.put(exchange, price);
    }

    public void reset() {
        buy_prices.clear();
        sell_prices.clear();
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public HashMap<String, Double> getBuy_prices() {
        return buy_prices;
    }

    public void setBuy_prices(HashMap<String, Double> buy_prices) {
        this.buy_prices = buy_prices;
    }

    public HashMap<String, Double> getSell_prices() {
        return sell_prices;
    }

    public void setSell_prices(HashMap<String, Double> sell_prices) {
        this.sell_prices = sell_prices;
    }

    public Double getMin_buy() {
        return min_buy;
    }

    public void setMin_buy(Double min_buy) {
        this.min_buy = min_buy;
    }

    public Double getMax_sell() {
        return max_sell;
    }

    public void setMax_sell(Double max_sell) {
        this.max_sell = max_sell;
    }

    public String getBuy_rec() {
        return buy_rec;
    }

    public void setBuy_rec(String buy_rec) {
        this.buy_rec = buy_rec;
    }

    public String getSell_rec() {
        return sell_rec;
    }

    public void setSell_rec(String sell_rec) {
        this.sell_rec = sell_rec;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "ticker='" + ticker + '\'' +
                ", buy_prices=" + buy_prices +
                ", sell_prices=" + sell_prices +
                ", min_buy=" + min_buy +
                ", max_sell=" + max_sell +
                ", buy_rec='" + buy_rec + '\'' +
                ", sell_rec='" + sell_rec + '\'' +
                '}';
    }
}
