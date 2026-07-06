import Model.Todo;
import Model.ZipCode;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _04_ApiTestPOJO {
    @Test
    public void extractJsonAll_POJO() {
        ZipCode adres1 =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().body().as(ZipCode.class);// Tüm body all Location.class (kalıba göre) çevir

        System.out.println("adres1.getPlaces() = " + adres1.getPlaces());
        System.out.println("adres1.getPostcode() = " + adres1.getPostcode());

    }
    @Test
    public void Task1(){
        /** Task 1
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * Converting Into POJO body data and write
         */
        Todo todo =
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos/2")

                        .then()
                        .extract().body().as(Todo.class);// Tüm body all Location.class (kalıba göre) çevir

        System.out.println(todo);
    }
}
