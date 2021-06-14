package testSimulations;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseApi;

import static io.restassured.RestAssured.given;

public class deleteTestApiSimulations extends BaseApi {

    @Test
    public void deleteIdSucess() {
        given().
                pathParam("id", "25").
                when().
                delete("/v1/simulacoes/{id}")
                .then().statusCode(HttpStatus.SC_OK);
    }
}
