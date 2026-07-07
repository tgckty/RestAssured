package GoRest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _06_GoRestUsersTest {
    RequestSpecification reqSpec;
    Faker faker = new Faker();
    static int id;

    @BeforeClass
    public void Setup() {
        baseURI = "https://gorest.co.in/public/v2";

        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer c105aae2a9ad535988312b76750e402c4ccbbb5882fa4932bbd6c443b6d99e26")
                .log(LogDetail.URI)
                .build();
    }

    @Test
    public void CreateUser() {
        String ad = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String gender = faker.demographic().sex().toLowerCase();

        String body = """
                {
                    "name": "%s",
                    "email": "%s",
                    "gender": "%s",
                    "status": "active"
                }
                """.formatted(ad, email, gender);

        //2.Yöntem MAP
        HashMap<String, String> gidecekBodyMAP = new HashMap<>();
        gidecekBodyMAP.put("name", ad);
        gidecekBodyMAP.put("email", email);
        gidecekBodyMAP.put("gender", gender);
        gidecekBodyMAP.put("status", "active");

        //3.Yöntem
        User gidecekBodyCLASS = new User();
        gidecekBodyCLASS.name = ad;
        gidecekBodyCLASS.email = email;
        gidecekBodyCLASS.gender = gender;
        gidecekBodyCLASS.status = "active";
        System.out.println("ad: " + ad);
        System.out.println("email: " + email + "  gender: " + gender);

        id = given()
                .spec(reqSpec)
                .body(body)

                .when()
                .post("/users")

                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .log().body()
                .extract().path("id")
        ;
    }

    @Test(dependsOnMethods = "CreateUser")
    public void GetUserById() {

        given()
                .spec(reqSpec)
                .pathParam("id", id)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body();
    }

    @Test(dependsOnMethods = "GetUserById")
    public void UpdateUser() {

        String email2 = faker.internet().emailAddress();
        String body = " {\"email\": \"%s\"}" .formatted(email2);
        given()
                .spec(reqSpec)
                .body(body)
                .pathParam("id", id)
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("email", equalTo(email2))
                .log().body();
    }

    @Test(dependsOnMethods = "UpdateUser")
    public void DeleteUser() {

        given()
                .spec(reqSpec)
                .pathParam("id", id)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(204)
                .log().body();
    }

//    @Test(dependsOnMethods = "DeleteUser")
//    public void DeleteUserNegative() {
//        given()
//                .spec(reqSpec)
//                .pathParam("id", id)
//
//                .when()
//                .delete("/users/{id}")
//
//                .then()
//                .statusCode(404)
//        ;
//    }

}
