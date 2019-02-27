/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.string.search.stringsearchdemo.controllers;

import com.string.search.stringsearchdemo.domain.StringSearchAlgorithms;
import com.string.search.stringsearchdemo.helpers.HelperMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aldo.lushkja
 */
@RestController
public class HomeController {

    public static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/home")
    public long getPrimeValue() {
        log.info("prime number 100 - {}", HelperMethods.getBiggerPrime(100));
        return HelperMethods.getBiggerPrime(100);
    }

    @GetMapping("/simpleText")
    public int getSimpleSearch(@RequestParam("pattern") char[] pattern, @RequestParam("text") char[] text) {
        log.info("pattern {}", pattern);
        log.info("text {}", text);
        return StringSearchAlgorithms.simpleTextSearch(pattern, text);
    }

    @GetMapping("/rabinKarp")
    public int getRabinKarpSearch(@RequestParam("pattern") char[] pattern, @RequestParam("text") char[] text) {
        log.info("pattern {}", pattern);
        log.info("text {}", text);
        return StringSearchAlgorithms.rabinKarpSearch(pattern, text);
    }

    @GetMapping("/knuthMorrisPratt")
    public int getKnuthMorrisPrattSearch(@RequestParam("pattern") char[] pattern, @RequestParam("text") char[] text) {
        log.info("pattern {}", pattern);
        log.info("text {}", text);
        return StringSearchAlgorithms.knuthMottisPrattSearch(pattern, text);
    }

    @GetMapping("/boyerMooreHorspolSimple")
    public int getBoyerMooreHorspol(@RequestParam("pattern") char[] pattern, @RequestParam("text") char[] text) {
        log.info("pattern {}", pattern);
        log.info("text {}", text);
        return StringSearchAlgorithms.boyerMooreHorspolSimpleSearch(pattern, text);
    }

    @GetMapping("/boyerMooreHorspolStd")
    public int getBoyerMooreHorspolStd(@RequestParam("pattern") char[] pattern, @RequestParam("text") char[] text) {
        log.info("pattern {}", pattern);
        log.info("text {}", text);
        return StringSearchAlgorithms.boyerMooreHorspolSearch(pattern, text);
    }
}
