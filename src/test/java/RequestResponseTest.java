import com.restassured.automation.models.DataInjection;
import com.restassured.automation.utils.SpecialMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class RequestResponseTest {

    DataInjection dataInjection = new DataInjection();

   @BeforeEach
    public void setUp(){

        RestAssured.baseURI = dataInjection.getBaseUri();
        RestAssured.basePath = dataInjection.getBasePath();
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON).build();
    }

    @Test
    public void loginTestPost(){
       SpecialMethods.configPropertiesRead();
        RestAssured
                .given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post(SpecialMethods.properties.getProperty("urlPost"))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue());
    }

    @Test
    public void singleUserGet(){
       SpecialMethods.configPropertiesRead();
        RestAssured
                .given()
                .get(SpecialMethods.properties.getProperty("urlGet"))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data.first_name", equalTo(SpecialMethods.properties.getProperty("nameGet")))
                .body("data.id",equalTo(2));
    }

    @Test
    public void userDelete(){
         RestAssured
               .given()
                .delete("users/2")
                 .then()
                 .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void updateUserPatch(){
       SpecialMethods.configPropertiesRead();
       RestAssured
               .given()
               .body("{\n" +
                       "    \"name\": \"Daniel\"}")
               .patch("users/2")
               .then()
               .statusCode(HttpStatus.SC_OK)
               .body("name",equalTo(SpecialMethods.properties.getProperty("namePatch")));
    }

    @Test
    public void updateUserPut(){
       RestAssured
               .given()
               .body("{\n" +
                       "    \"name\": \"morpheus\",\n" +
                       "    \"job\": \"zion resident\"\n" +
                       "}")
               .put("users/2")
               .then()
               .statusCode(HttpStatus.SC_OK)
               .body("job",equalTo("zion resident"));
    }
    }




