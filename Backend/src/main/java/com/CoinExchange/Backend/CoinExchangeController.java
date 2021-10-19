package com.CoinExchange.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CoinExchangeController {

    @Autowired
    ExchangeService exchangeService;

    @CrossOrigin("http://localhost:3000/")
    @GetMapping("/coinexchange")
    public List<Coin> getFrontPage() {
        exchangeService.fetchPrices();
        return exchangeService.getCoins();
    }

}
