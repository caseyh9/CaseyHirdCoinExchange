package com.CoinExchange.Backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeService {

    Logger logger = LoggerFactory.getLogger(ExchangeConfig.class);

    private RestTemplate restTemplate;

    private ObjectMapper mapper;

    @Autowired
    private List<Exchange> exchanges;

    @Autowired
    private List<Coin> coins;

    public ExchangeService() {
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
    }

    private void requestPrices(Exchange exchange, Coin coin) throws JsonProcessingException {
        String result;
        Map map;
        switch (exchange.getName()) {
            case "Bittrex":
                result = this.restTemplate.getForObject(
                    exchange.getTickerRequestUrl(coin.getTicker()), String.class);
                map = this.mapper.readValue(result, Map.class);
                coin.insertPrice("buy", exchange.getName(),
                    Double.parseDouble(map.get("askRate").toString()));
                coin.insertPrice("sell", exchange.getName(),
                    Double.parseDouble(map.get("bidRate").toString()));
                break;

                // Note url format uses lowercase ticker, have to convert
            case "Bitstamp":
                result = this.restTemplate.getForObject(
                        exchange.getTickerRequestUrl(coin.getTicker().toLowerCase()), String.class);
                map = this.mapper.readValue(result, Map.class);
                coin.insertPrice("buy", exchange.getName(),
                        Double.parseDouble(map.get("ask").toString()));
                coin.insertPrice("sell", exchange.getName(),
                        Double.parseDouble(map.get("bid").toString()));
                break;
        }
    }

    public void fetchPrices() {
        coins.forEach(Coin::reset);

        exchanges.forEach(exchange -> {
            coins.forEach((coin) -> {
                try {
                    requestPrices(exchange, coin);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        });

        logger.trace(coins.toString());
    }

    public List<Exchange> getExchanges() {
        return exchanges;
    }

    public List<Coin> getCoins() {
        return coins;
    }

}
