package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest
{
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        response= given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void extractLimit()
    {
        int limit=  response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : "+limit );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractTotal()
    {
        //2. Extract the total
        int total=  response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : "+total );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractName()
    {
        //3. Extract the name of 5th store
        String name=  response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : "+name );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractListNameOfAllStore()
    {
        //4. Extract the names of all the store
        List<String> listOfStoreName=  response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all store names : "+listOfStoreName );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void extractAllStoreId()
    {
        //5. Extract the storeId of all the store
        List<Integer> listOfStoreId=  response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all store Id : "+listOfStoreId );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void printSizeOfData()
    {
        //6. Print the size of the data list
        int sizeOfData=  response.extract().path("data.size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of data : "+sizeOfData );
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getValueOfName()
    {
        //7. Get all the value of the store where store name = St Cloud
        List<HashMap<String, ?>> values= response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for  store where store name = St Cloud are: " +values);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAddressOfStore()
    {
        //8. Get the address of the store where store name = Rochester
        List<HashMap<String, ?>> address= response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for  store where store name = Rochester: " +address);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAllServicesOfStore()
    {
        //9. Get all the services of 8th store
        List<HashMap<String, ?>> serviceOfStore= response.extract().path("data.findAll{it.id==13}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the services of 8th store " +serviceOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAllServicesByServiceName()
    {
        //10. Get storeservices of the store where service name = Windows Store
        List<HashMap<String, ?>> services= response.extract().path("data.services*.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get storeservices of the store where service name = Windows Store" +services);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAllStoreId()
    {
        //11. Get all the storeId of all the store
        List<HashMap<Integer, ?>> storeId= response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the storeId of all the store" +storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAllIdStore()
    {
        //12. Get id of all the store
        List<HashMap<Integer, ?>> storeId= response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of all the store" +storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void getAllServicesOfTheStore()
    {
        //13. Find the store names Where state = ND
        List<HashMap<String, ?>> storeName= response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the store names Where state ND" + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void totalNumberOfService()
    {
        //14. Find the Total number of services for the store where store name = Rochester.
        List<HashMap<String, ?>> totalService= response.extract().path("data.findAll{it.name=='Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the Total number of services for the store where store name = Rochester" + totalService);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findCreatedAt()
    {
        //15. Find the createdAt for all services whose name = “Windows Store”
        List<HashMap<String, ?>> createdAt= response.extract().path("data.services*.findAll{it.name=='Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Find the createdAt for all services whose name = Windows Store" + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findNameOfAllServices()
    {
        //16. Find the name of all services Where store name = “Fargo”
        List<HashMap<String, ?>> nameServices= response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the name of all services Where store name = Fargo" + nameServices);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheZip()
    {
        //17. Find the zip of all the store
        List<HashMap<String, ?>> findTheZip= response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store" + findTheZip);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheZipOfSpecificStore()
    {
        //19. Find the storeservices details of the service name = Magnolia Home Theater
        List<HashMap<String, ?>> findTheZip= response.extract().path("data.services*.findAll{it.name=='Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the storeservices details of the service name = Magnolia Home Theater" + findTheZip);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void findTheLat()
    {
        //20. Find the lat of all the stores
        List<HashMap<String, ?>> findTheLat= response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the lat of all the stores" + findTheLat);
        System.out.println("------------------End of Test---------------------------");
    }
}
