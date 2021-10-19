package com.CoinExchange.Backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Configuration
public class CoinConfig {

    Logger logger = LoggerFactory.getLogger(ExchangeConfig.class);

    @Bean
    public List<Coin> getCoins() {
    //    HashMap<String,Coin> coins = new HashMap<>();
    //    List<String> coin_tickers = new ArrayList<>();
        List<Coin> coinList = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            coinList = Arrays.asList(mapper.readValue(
                    new ClassPathResource("coins.json").getFile(), Coin[].class));
         //   coin_tickers = Arrays.asList(mapper.readValue(
         //           new ClassPathResource("coins.json").getFile(), String[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

     //   coin_tickers.forEach(t -> coins.put(t, new Coin(t)));

        logger.trace(coinList.toString());

        return coinList;
    }

}
