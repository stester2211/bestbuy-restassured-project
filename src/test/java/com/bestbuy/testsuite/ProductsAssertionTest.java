package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest
{
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt()
    {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        response= given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //11. Verify the if the total is equal to 51957
    @Test
    public void verifyTotal()
    {
        response.body("total",equalTo(51957));
    }

    @Test
    public void verifyLimit()
    {
        //12. Verify the if the stores of limit is equal to 10
        response.body("limit",equalTo(10));
    }

    @Test
    public void verifySingleName()
    {
        //13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
        response.body("data.name",hasItem("Duracell - AA Batteries (8-Pack)"));
    }

    //14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
    //Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    @Test
    public void verifyListOfNames()
    {
        response.body("data.name",hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)","Duracell - AA Batteries (8-Pack)","Energizer - MAX Batteries AA (4-Pack)"));
    }

    //15. Verify the productId=pcmcat303600050001 inside categories of the third name is “Household Batteries”
    @Test
    public void verifyProductId()
    {
        response.body("data[3].categories[2].name", equalTo("Household Batteries"));
    }

    @Test
    public void verifyCategories()
    {
        //16. Verify the categories second name = “Housewares” of productID = 333179
        response.body("data[8].categories[1].name", equalTo("Housewares"));
    }

    @Test
    public void verifyPrice()
    {
        //17. Verify the price = 4.99 of forth product
        response.body("data[3]",hasKey("price"));
    }

    @Test
    public void verifyProductName()
    {
        //18. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
        response.body("data.name",hasItem("Duracell - D Batteries (4-Pack)"));
    }

    @Test
    public void verifyProductIdByProduct()
    {
        //19. Verify the ProductId = 333179 for the 9th product
        response.body("data[8].id",equalTo(333179));
    }

    @Test
    public void verifyProductIdByCategories()
    {
        //20. Verify the prodctId = 185230 have 5 categories
        response.body("data[4].id",equalTo(185230));
    }
}
