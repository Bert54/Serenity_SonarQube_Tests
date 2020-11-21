package com.ul.vclc.calculator.model.controllers;

import com.ul.vclc.calculator.model.services.CalculatorService;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/calculator", produces="application/json")
@RestController
public class CalculatorController {

    private CalculatorService calculator;

    public CalculatorController(CalculatorService calcserv) {
        this.calculator = calcserv;
    }

    @GetMapping(value="/randomnumber")
    public int randomNumber(@RequestParam(required = false) Integer upperBound) {
        if (upperBound == null) {
            return calculator.rand(0);
        }
        return calculator.rand(upperBound); }

    @PostMapping(value="/add")
    public int add(@RequestParam int left, @RequestParam int right) {
        return calculator.add(left, right);
    }

    @PostMapping(value="/sub")
    public int sub(@RequestParam int left, @RequestParam int right) {
        return calculator.sub(left, right);
    }

    @PostMapping(value="/mult")
    public int mult(@RequestParam int left, @RequestParam int right) {
        return calculator.mult(left, right);
    }

    @PostMapping(value="/div")
    public float div(@RequestParam int left, @RequestParam int right) {
        return calculator.div(left, right);
    }

}
