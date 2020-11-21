package com.ul.vclc.calculator;

import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.RestAssured.given;
import static com.ul.vclc.calculator.model.services.CalculatorService.DEFAULT_RANDOM_UPPER_BOUND;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorApplicationTests {

    @BeforeAll
    public static void setup() {
        RestAssured.port = 8080;
        RestAssured.basePath = "/calculator";
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testCaculatorAdder() {
        int left = 10;
        int right = 25;
        String calcResult = given().param("left", left).param("right", right).when()
                .post("/add").then().statusCode(HttpStatus.OK.value()).extract().asString();
        assertEquals(Integer.parseInt(calcResult), left + right);
    }

    @Test
    public void testCaculatorSubstracter() {
        int left = 10;
        int right = 25;
        String calcResult = given().param("left", left).param("right", right).when()
                .post("/sub").then().statusCode(HttpStatus.OK.value()).extract().asString();
        assertEquals(Integer.parseInt(calcResult), left - right);
    }

    @Test
    public void testCaculatorMultiplier() {
        int left = 10;
        int right = 25;
        String calcResult = given().param("left", left).param("right", right).when()
                .post("/mult").then().statusCode(HttpStatus.OK.value()).extract().asString();
        assertEquals(Integer.parseInt(calcResult), left * right);
    }


    @Test
    public void testCaculatorDivider() {
        int left = 10;
        int right = 25;
        String calcResult = given().param("left", left).param("right", right).when()
                .post("/div").then().statusCode(HttpStatus.OK.value()).extract().asString();
        assertEquals(Float.parseFloat(calcResult), (float)left / (float)right);

        calcResult = given().param("left", 69).param("right", 0).when()
                .post("/div").then().statusCode(HttpStatus.OK.value()).extract().asString();
        assertEquals(Float.parseFloat(calcResult), 0f);
    }

    @Test
    public void testCaculatorGenerateRandom() {
        int upperBound = 100;
        String calcResult = given().param("upperBound", upperBound).when()
                .get("/randomnumber").then().statusCode(HttpStatus.OK.value()).extract().asString();
        int response = Integer.parseInt(calcResult);
        if (response < 0 || response > upperBound) {
            fail("Generated number not within defined boundaries.");
        }

        calcResult = given().param("left", 69).param("right", 0).when()
                .get("/randomnumber").then().statusCode(HttpStatus.OK.value()).extract().asString();
        response = Integer.parseInt(calcResult);
        if (response < 0 || response > DEFAULT_RANDOM_UPPER_BOUND) {
            fail("Generated number not within default boundaries.");
        }
    }

}
