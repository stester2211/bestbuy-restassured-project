package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest
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

    @Test
    public void extractLimit()
    {
        //21. Extract the limit
        int limit=  response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : "+limit );
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void extractTotal()
    {
        //22. Extract the total
        int total=  response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : "+total );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractNameOfProduct()
    {
        //23. Extract the name of 5th product
        String productName=  response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : "+productName );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractNameOfAllProduct()
    {
        //24. Extract the names of all the products
        List<String> productsName=  response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all products are : "+productsName );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractProductIdOfAllProducts()
    {
        //25. Extract the productId of all the products
        List<String> productId=  response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Ids of all products are : "+productId );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void sizeOfDataList()
    {
        //26. Print the size of the data list
        System.out.println((Integer) response.extract().path("data.size"));
    }

    @Test
    public void allTheValueOfProduct()
    {
        //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
        List<HashMap<String, ?>> values= response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Duracell - AA Batteries (8-Pack)' are: " +values);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void allTheModelOfTheProduct() {
        //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
        List<HashMap<String, ?>> modelName= response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)' is: " +modelName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void allTheCategoriesOfProduct()
    {
        //29. Get all the categories of 8th products
        List<HashMap<String, ?>> allCategories= response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of 8th products are: " +allCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void allCategoriesOfStore()
    {
        //30. Get categories of the store where product id = 150115
        List<HashMap<String, ?>> allcategories= response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Categories Of store where product id = 150115 are : "+ allcategories);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAllTheDescriptionOfAllTheProducts()
    {
        //31. Get all the descriptions of all the products
        List<HashMap<String, ?>> allDescription= response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All products descriptions are : "+ allDescription);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAllIdOfAllTheProducts()
    {
        //32. Get id of all the all categories of all the products
        List<HashMap<String, ?>> allId= response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All products categories Id are : "+ allId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getProductName()
    {
        //33. Find the product names Where type = HardGood
        List<HashMap<String, ?>> allProductName= response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All products names Where type = HardGood : "+ allProductName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getTotalCategories()
    {
        //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
        List<HashMap<String , ?>> totalNumberOfCategories= response.extract().path("data.findAll{it.name=='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All total categories for product Duracell - AA 1.5V CopperTop Batteries (4-Pack) are "+ totalNumberOfCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findCreatedAt()
    {
        //35. Find the createdAt for all products whose price < 5.49
        List<HashMap<String , ?>> createdAt= response.extract().path("data.findAll{it.price<5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All products whose price < 5.49 are "+ createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findNameOfAllCategories()
    {
        //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
        List<HashMap<String , ?>> nameOfCategories= response.extract().path("data.findAll{it.name== 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”  are "+ nameOfCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheManufacturer()
    {
        //37. Find the manufacturer of all the products
        List<HashMap<String, ?>> findManufacturer= response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All manufacturer of all the products : "+ findManufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheImageOfProducts()
    {
        //38. Find the image of products whose manufacturer is = Energizer
        List<HashMap<String , ?>> findTheImageOfProducts= response.extract().path("data.findAll{it.manufacturer== 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”  are "+ findTheImageOfProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findCreatedAtPrice()
    {
        //39. Find the createdAt for all categories products whose price > 5.99
        List<HashMap<String , ?>> createdAt= response.extract().path("data.findAll{it.price>5.99}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All products whose price  > 5.99 are "+ createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheURLOfAllProducts()
    {
        //40. Find the url of all the products
        List<HashMap<String, ?>> findURL= response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The url of all the products are : "+ findURL);
        System.out.println("------------------End of Test---------------------------");
    }
}
