import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _01_APITest {
    @Test
    public void Test1() {
        // POSTMAN
        // 1- Endpoint i çağırmadan önce hazırların yapıldığı bölüm: url, gidecek body,token
        // 2- Endpoint in çağrıldığı bölüm : Endpoint in çağrılması(METOD : GET, POST ..)
        // 3- Endpoint çağrıldıktan sonraki bölüm: Response, gelen body, gelen status code, TEST(Assertion)
        given()
                //1.bölümlerle ilgili işler : giden body,token
                .when()
                //2.bölümlerle ilgili işler : gidiş metodu , endpoint, apinin çağrılma kısmı
                .then()
        //3.bölümlerle ilgili işler: gelen data, assert,test
        ;
    }

    @Test
    public void statusCodeTest() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then() // alttakiler assertion gibi çalışır.
                .log().body() // dönen veriyi gösterir. all: bütün bilgiler
                .statusCode(200); //dönen kod 200 mü diye kontrol eder.
    }

    @Test
    public void contentTypeTest() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then() // alttakiler assertion gibi çalışır.
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON); // dönen veriyi formatı json mı?
    }

    @Test
    public void checkCountryInResponseBody() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then() // alttakiler assertion gibi çalışır.
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON) // dönen veriyi formatı json mı?
                .body("country", equalTo("United States")); // bodyde yer alan country değeri, verdiğimiz değere eşit mi?
    }

    @Test
    public void checkHasItem() {
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // places dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız
        given()
                .when()
                .get("http://api.zippopotam.us/tr/01000")
                .then() // alttakiler assertion gibi çalışır.
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("places.'place name'",hasItem("Dörtağaç Köyü")); //tüm place namelerde bu item var mı?
    }
    @Test
    public void bodyArrayHasSizeTest() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint in dönen Places dizisinin
        // boyutunun 1 olduğunu doğrulayınız.
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then() // alttakiler assertion gibi çalışır.
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("places",hasSize(1)); //dizinin boyutu bu mu?
    }

    @Test
    public void combineTest() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint in dönen Places dizisinin
        // boyutunun 1 olduğunu doğrulayınız.
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then() // alttakiler assertion gibi çalışır.
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("places.'place name'",hasItem("Beverly Hills"))
                .body("country", equalTo("United States"))
                .body("places",hasSize(1)); //dizinin boyutu bu mu?
    }
    @Test
    public void pathParamTest(){

        given()
                .pathParam("ulke","us")
                .pathParam("pk","90210")
                .log().uri() // oluşacak endpointi yazdırdık
                .when()
                .get("http://api.zippopotam.us/{ulke}/{pk}")
                .then()
                .log().body()
        ;
    }
    @Test
    public void queryParamTest() {
        //https://gorest.co.in/public/v1/users?page=3
        given()
                .queryParam("page","3")
                .log().uri()
                .when()
                .get("https://gorest.co.in/public/v1/users")
                .then()
                .log().body()
        ;
    }
}
