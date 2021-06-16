package testSimulations;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseApi;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class getTestApiSimulations extends BaseApi {
    Random random = new Random();
    int i = random.nextInt(9);
    String cpf[] = {"97093236014","60094146012","60094146012", "62648716050", "26276298085",
            "01317496094", "01317496094", "19626829001", "24094592008", "58063164083"} ;
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
        given().pathParam("cpf", cpf[i]).
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
