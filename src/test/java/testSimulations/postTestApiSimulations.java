package testSimulations;

import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseApi;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.util.List;
import java.util.Optional;

import static groovy.xml.Entity.not;
import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;


public class postTestApiSimulations extends BaseApi {
    String bodyValueSucess = "{\n" +
            "  \"nome\": \"22\",\n" +
            "  \"cpf\": 62648716050,\n" +
            "  \"email\": \"email@email.com\",\n" +
            "  \"valor\": 1200,\n" +
            "  \"parcelas\": 3,\n" +
            "  \"seguro\": true\n" +
            "}";
    Response response;
    @Test
    public void postRegistrationSuccess(){
       response  = given().contentType("application/json").body(bodyValueSucess).
                   when().post("/v1/simulacoes");
        response.then().statusCode(HttpStatus.SC_CREATED).body(matchesJsonSchemaInClasspath("contrato-schema.json"));
    }
    @Test
    public void postCpfAlreadyRegistered(){
        response =  given().contentType("application/json").body(bodyValueSucess).
                when().post("/v1/simulacoes");
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).body("mensagem", is("CPF duplicado"));
    }

    @Test
    public void postEmailInvalid(){
        response = given().contentType("application/json").body("{\n" +
                "  \"nome\": \"22\",\n" +
                "  \"cpf\": 62648716050,\n" +
                "  \"email\": \"emailemail.com\",\n" +
                "  \"valor\": 1200,\n" +
                "  \"parcelas\": 3,\n" +
                "  \"seguro\": true\n" +
                "}").
                when().post("/v1/simulacoes");
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).body("erros.email", is("não é um endereço de e-mail"));

    }

    @Test
    public void postNomeEmpty() {
        response = given().contentType("application/json").body("{\n" +
                "  \"cpf\": 62648716050,\n" +
                "  \"email\": \"email@email.com\",\n" +
                "  \"valor\": 1200,\n" +
                "  \"parcelas\": 3,\n" +
                "  \"seguro\": true\n" +
                "}").
                when().post("/v1/simulacoes");
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).body("erros.nome", is("Nome não pode ser vazio"));
    }

}
