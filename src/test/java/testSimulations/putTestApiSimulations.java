package testSimulations;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class putTestApiSimulations extends BaseApi {
    String bodyValueUpdate = "{\n" +
            "  \"nome\": \"Novo\",\n" +
            "  \"email\": \"email@email.com\",\n" +
            "  \"valor\": 1220,\n" +
            "  \"parcelas\": 4,\n" +
            "  \"seguro\": true\n" +
            "}";
    @Test
    public void putCpfUpdate(){
        given().pathParam("cpf","62648716050").contentType("application/json").body(bodyValueUpdate).
                when().put("/v1/simulacoes/{cpf}").

                then().statusCode(HttpStatus.SC_OK);

    }
    @Test
    public void putCpfUpdateNotFound() {
        given().pathParam("cpf", "62648716051")
                .contentType("application/json")
                .body(bodyValueUpdate)
                .when().put("/v1/simulacoes/{cpf}")
                .then().statusCode(HttpStatus.SC_NOT_FOUND)
                .body("mensagem", is("CPF 62648716051 não encontrado"));
    }
}
