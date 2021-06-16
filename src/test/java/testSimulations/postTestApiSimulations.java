package testSimulations;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseApi;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


public class postTestApiSimulations extends BaseApi {
    Faker faker = new Faker(new Locale("pt-BR"));
    Random random = new Random();
    int i = random.nextInt(1);
    String nome = faker.name().fullName();
    String cpf = faker.number().digits(9);
    String email = faker.internet().emailAddress();
    int valor = random.nextInt(1000);
    int parcela = random.nextInt(9);
    boolean seguro[] = {false, true};

    String bodyValueSucess = "{\n" +
            "  \"nome\": \""+nome+"\",\n" +
            "  \"cpf\": "+cpf+",\n" +
            "  \"email\": \""+email+"\",\n" +
            "  \"valor\": "+valor+" ,\n" +
            "  \"parcelas\":"+parcela+",\n" +
            "  \"seguro\": "+seguro[i]+"\n" +
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
                "  \"nome\": \""+nome+"\",\n" +
                "  \"cpf\": "+cpf+",\n" +
                "  \"email\": \"emailemail.com\",\n" +
                "  \"valor\": "+valor+",\n" +
                "  \"parcelas\": "+parcela+",\n" +
                "  \"seguro\": "+seguro[i]+"\n" +
                "}").
                when().post("/v1/simulacoes");
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).body("erros.email", is("não é um endereço de e-mail"));

    }
    @Test
    public void postAccountInvalid(){
        response = given().contentType("application/json").body("{\n" +
                "  \"nome\": \""+nome+"\",\n" +
                "  \"cpf\": "+cpf+",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"valor\": "+valor+",\n" +
                "  \"parcelas\": 1,\n" +
                "  \"seguro\": "+seguro[i]+"\n" +
                "}").
                when().post("/v1/simulacoes");
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).body("erros.parcelas", is("Parcelas deve ser igual ou maior que 2"));

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
