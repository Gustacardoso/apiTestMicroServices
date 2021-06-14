package testSimulations;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseApi;

import static io.restassured.RestAssured.given;

public class getTestApiSimulations extends BaseApi {

    @Test
    public void getAll() {
        given().
                when().
                get("/v1/simulacoes")
                .then().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void getAllNoContent() {
        given().
                when().
                get("/v1/simulacoes")
                .then().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void getCpf() {
        given().pathParam("cpf", "97093236014").
                when().
                get("/v1/simulacoes/{cpf}")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getInvalidCpf() {
        given().pathParam("cpf", "91093236014").
                when().
                get("/v1/simulacoes/{cpf}")
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
