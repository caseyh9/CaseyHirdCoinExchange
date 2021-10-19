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
import java.util.List;

@Configuration
public class ExchangeConfig {

    Logger logger = LoggerFactory.getLogger(ExchangeConfig.class);

    @Bean
    public List<Exchange> getExchanges() {
        List<Exchange> exchangeList = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            exchangeList = Arrays.asList(mapper.readValue(
                    new ClassPathResource("exchanges.json").getFile(), Exchange[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        logger.trace(exchangeList.toString());

        return exchangeList;
    }

}
