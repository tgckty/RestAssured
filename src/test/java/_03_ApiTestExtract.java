import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _03_ApiTestExtract {
    @Test
    public void extractingJsonPath() {
        String ulke = given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .extract().path("country") // veriyi bir değişkene atamamızı sağlar.
                ;

        System.out.println("Ülke: " + ulke);
        Assert.assertEquals(ulke, "United States", "Ülkenen değeri beklenen değil");

    }

    @Test
    public void extractingJsonPath2() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // limit bilgisinin 10 olduğunu testNG ile doğrulayınız.
        int limit = given()
                .when()
                .get("https://gorest.co.in/public/v1/users")
                .then()
                .extract().path("meta.pagination.limit");

        System.out.println("Limit: " + limit);
        Assert.assertEquals(limit, 10);
    }
    @Test
    public void extractingJsonPath3() {

        ArrayList<Integer> idler=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        //.log().body()
                        .extract().path("data.id")
                ;

        System.out.println("idler = " + idler);
        Assert.assertTrue(idler.contains(8531303));
    }

    @Test
    public void extractingJsonPath4() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // bütün name leri in içinde "Anusuya Reddy JD" değerinin geçtiğini
        // TestNg assertion ile doğrulayınız.
        ArrayList<String> nameler=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        //.log().body()
                        .extract().path("data.name")
                ;

        System.out.println("nameler = " + nameler);
        Assert.assertTrue(nameler.contains("Anusuya Reddy JD"));
    }
    @Test
    public void extractingJsonPathResponseAll() {
        Response donenBody=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        // .log().body()
                        .extract().response();

        int limit= donenBody.path("meta.pagination.limit");
        ArrayList<Integer> idler=donenBody.path("data.id");
        ArrayList<String> nameler=donenBody.path("data.name");

        System.out.println("limit = " + limit);
        System.out.println("idler = " + idler);
        System.out.println("nameler = " + nameler);

        Assert.assertTrue(limit == 10);
        Assert.assertTrue(idler.contains(8531303));
        Assert.assertTrue(nameler.contains("Anusuya Reddy JD"));
    }
}
