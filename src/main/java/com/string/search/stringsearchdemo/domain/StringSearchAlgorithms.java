/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.string.search.stringsearchdemo.domain;

import com.string.search.stringsearchdemo.helpers.HelperMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author aldo.lushkja
 */
public class StringSearchAlgorithms {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringSearchAlgorithms.class);

    public static int simpleTextSearch(char[] pattern, char[] text) {

        long inizio = System.currentTimeMillis();

        int patternSize = pattern.length;
        int textSize = text.length;

        int i = 0;

        while ((i + patternSize) <= textSize) {
            int j = 0;
            while (text[i + j] == pattern[j]) {
                j += 1;
                if (j >= patternSize) {
                    long fine = System.currentTimeMillis();
                    long tempo = (fine - inizio) / 100;
                    LOGGER.info("Simple Text Algorithm -> time elapsed: {} sec", tempo);
                    return i;
                }
            }
            i += 1;
        }
        long fine = System.currentTimeMillis();
        long tempo = (fine - inizio) / 100;
        LOGGER.info("Simple Text Algorithm -> time elapsed: {} sec", tempo);
        return -1;

    }

    public static int rabinKarpSearch(char[] pattern, char[] text) {
        long inizio = System.currentTimeMillis();
        int patternSize = pattern.length;
        int textSize = text.length;

        long prime = HelperMethods.getBiggerPrime(patternSize);

        long r = 1;
        for (int i = 0; i < patternSize - 1; i++) {
            r *= 2;
            r = r % prime;
        }

        long[] t = new long[textSize];
        t[0] = 0;

        long pfinger = 0;

        for (int j = 0; j < patternSize; j++) {
            t[0] = (2 * t[0] * text[j]) % prime;
            pfinger = (2 * pfinger * pattern[j]) % prime;
        }

        int i = 0;
        boolean passed = false;

        int diff = textSize - patternSize;
        for (i = 0; i <= diff; i++) {
            if (t[i] == pfinger) {
                passed = true;
                for (int k = 0; k < patternSize; k++) {
                    if (text[i + k] != pattern[k]) {
                        passed = false;
                        break;
                    }
                }

                if (passed) {
                    long fine = System.currentTimeMillis();
                    long tempo = (fine - inizio) / 1000;
                    LOGGER.info("time elapsed: {} sec", tempo);
                    return i;

                }
            }

            if (i < diff) {
                long value = 2 * (t[i] - r * text[i]) + text[i + patternSize];
                t[i + 1] = ((value % prime) + prime) % prime;
            }
        }
        long fine = System.currentTimeMillis();
        long tempo = (fine - inizio) / 1000;
        LOGGER.info("Rabin Karp Algorithm -> time elapsed: {} sec", tempo);
        return -1;
    }

    public static int knuthMottisPrattSearch(char[] pattern, char[] text) {

        long inizio = System.currentTimeMillis();
        int patternSize = pattern.length;
        int textSize = text.length;

        int i = 0, j = 0;

        int[] shift = knuthMorrisPrattShift(pattern);

        while ((i + patternSize) <= textSize) {
            while (text[i + j] == pattern[j]) {
                j += 1;
                if (j >= patternSize) {
                    long fine = System.currentTimeMillis();
                    long tempo = (fine - inizio) / 1000;
                    LOGGER.info("time elapsed: {} sec", tempo);
                    return i;
                }
            }

            if (j > 0) {
                i += shift[j - 1];
                j = Math.max(j - shift[j - 1], 0);
            } else {
                i++;
                j = 0;
            }
        }
        long fine = System.currentTimeMillis();
        long tempo = (fine - inizio) / 1000;
        LOGGER.info("Knuth-Morris-Pratt Algorithm -> time elapsed: {} sec", tempo);
        return -1;
    }

    private static int[] knuthMorrisPrattShift(char[] pattern) {
        int patternSize = pattern.length;

        int[] shift = new int[patternSize];
        shift[0] = 1;

        int i = 1, j = 0;

        while ((i + j) < patternSize) {
            if (pattern[i + j] == pattern[j]) {
                shift[i + j] = i;
                j++;
            } else {
                if (j == 0) {
                    shift[i] = i + 1;
                }
                if (j > 0) {
                    i = i + shift[j - 1];
                    j = Math.max(j - shift[j - 1], 0);
                } else {
                    i = i + 1;
                    j = 0;
                }
            }
        }

        return shift;
    }

    public static int boyerMooreHorspolSimpleSearch(char[] pattern, char[] text) {

        long inizio = System.currentTimeMillis();
        int patternSize = pattern.length;
        int textSize = text.length;

        int i = 0, j = 0;

        while ((i + patternSize) <= textSize) {
            j = patternSize - 1;
            while (text[i + j] == pattern[j]) {
                j--;
                if (j < 0) {
                    long fine = System.currentTimeMillis();
                    long tempo = (fine - inizio) / 1000;
                    LOGGER.info("Boyer-Moore-Horspol Algorithm -> time elapsed: {} sec", tempo);
                    return i;
                }
            }
            i++;
        }

        long fine = System.currentTimeMillis();
        long tempo = (fine - inizio) / 1000;
        LOGGER.info("Boyer-Moore-Horspol Algorithm -> time elapsed: {} sec", tempo);
        return -1;
    }

    public static int boyerMooreHorspolSearch(char[] pattern, char[] text) {
        int shift[] = new int[256];

        for (int k = 0; k < 256; k++) {
            shift[k] = pattern.length;
        }

        for (int k = 0; k < pattern.length - 1; k++) {
            shift[pattern[k]] = pattern.length - 1 - k;
        }

        int i = 0, j = 0;

        while ((i + pattern.length) <= text.length) {
            j = pattern.length - 1;
            while (text[i + j] == pattern[j]) {
                j -= 1;
                if (j < 0) {
                    return i;
                }
            }
            i = i + shift[text[i + pattern.length - 1]];
        }

        return -1;

    }

}
