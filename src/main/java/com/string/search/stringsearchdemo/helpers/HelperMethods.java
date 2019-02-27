/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.string.search.stringsearchdemo.helpers;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author aldo.lushkja
 */
public class HelperMethods {
    
    public static long getBiggerPrime(int m){
        BigInteger prime = BigInteger.probablePrime(getNumberOfBits(m) + 1, new Random());
        return prime.longValue();
    }
    
    public static int getNumberOfBits(int number){
        return Integer.SIZE - Integer.numberOfLeadingZeros(number);
    }
    
}
