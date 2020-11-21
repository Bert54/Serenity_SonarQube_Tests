package com.ul.vclc.calculator.model.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CalculatorService {

    public static final int DEFAULT_RANDOM_UPPER_BOUND = 50;

    public int rand(int upperbound) {
        if (upperbound <= 0) {
            upperbound = DEFAULT_RANDOM_UPPER_BOUND;
        }
        return (new Random()).nextInt(upperbound + 1);
    }

    public int add(int left, int right) {
        return left + right;
    }

    public int sub(int left, int right) {
        return left - right;
    }

    public int mult(int left, int right) {
        return left * right;
    }

    public float div(int left, int right) {
        if (right == 0) {
            return 0;
        }
        return (float)left / (float)right;
    }

}
