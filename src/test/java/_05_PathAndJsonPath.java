import Model.Place;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _05_PathAndJsonPath {
    @Test
    public void extractingPath()
    {
        String postCode=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("'post code'")   // "post code": "90210",
                ;

        System.out.println("postCode = " + postCode);
        int postCodeInt= Integer.parseInt(postCode);
        System.out.println("postCodeInt = " + postCodeInt);

    }

    @Test
    public void extractingPath2()
    {
        int postCode=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("'post code'")   // "post code": "90210",
                ;

        System.out.println("postCode = " + postCode);
    }

    @Test
    public void extractingJsonPath()
    {
        int postCode=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().jsonPath().getInt("'post code'")   // "post code": "90210",
                ;// 1.Avantaj : tip dönüşümü yapabiliyor, uygun tip verilmeli

        System.out.println("postCode = " + postCode);
    }

    @Test
    public void extractJsonPath_POJO() {

        List<Place> places=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().jsonPath().getList("places", Place.class)
                ;

        System.out.println("places = " + places);

        // 2. Avantajı : Sadece Place dizisi lazım ise diğerlerini yazmak zorunda değilsin.

        // Daha önceki örneklerde (as) Clas dönüşümleri için tüm yapıya karşılık gelen
        // gereken tüm classları yazarak dönüştürüp istediğimiz elemanlara ulaşıyorduk.

        // Burada ise(JsonPath) aradaki bir veriyi clasa dönüştürerek bir list olarak almamıza
        // imkan veren JSONPATH i kullandık.Böylece tek class ile veri alınmış oldu
        // diğer class lara gerek kalmadan

        // path : class veya tip dönüşümüne imkan veremeyen direk veriyi verir. List<String> gibi
        // jsonPath : class dönüşümüne ve tip dönüşümüne izin vererek , veriyi istediğimiz formatta

    }
}
