package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1.Verify the if the total record is 10
    @Test
    public void Test001() {
        response.body("size", equalTo(10));
    }

    //2.Verify the if the name of id = 4104810 is equal to ”Jagadish Ahuja”
    @Test
    public void Test002() {
        response.body("findAll{it.id == 4104810}", hasItem(hasEntry("name","Jagadish Ahuja")));
    }

    //3.Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void Test003() {
        response.body("name[6]", equalTo("Uttam Trivedi"));
    }

    //4.Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void Test004() {
        response.body("name", hasItems("Jagadish Ahuja", "Uttam Trivedi", "Mandakini Mukhopadhyay"));
    }


    //5.Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void Test005() {
        response.body("findAll{it.id == 4104805}.email",hasItem(equalTo("anjaneya_kaul@kutch.example")));

    }

    //6.Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void Test006() {
        response.body("findAll{it.name == 'Anjaneya Kaul'}.status", hasItem(equalTo("active")));

    }

    //7.Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void Test007() {
        response.body("findAll{it.name == 'Anshula Dhawan'}.gender", hasItem(equalTo("male")));

    }
}
