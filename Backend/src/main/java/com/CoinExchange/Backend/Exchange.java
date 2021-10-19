package com.CoinExchange.Backend;

public class Exchange {
    private String name;
    private String base_url;
    private String ticker_url;

    public Exchange() {}

    public Exchange(String name, String base_url, String ticker_url) {
        this.name = name;
        this.base_url = base_url;
        this.ticker_url = ticker_url;
    }

    public String getTickerRequestUrl(String coin) {
        return String.format(base_url+ticker_url, coin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getTicker_url() {
        return ticker_url;
    }

    public void setTicker_url(String ticker_url) {
        this.ticker_url = ticker_url;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "name='" + name + '\'' +
                ", base_url='" + base_url + '\'' +
                ", ticker_url='" + ticker_url + '\'' +
                '}';
    }
}
