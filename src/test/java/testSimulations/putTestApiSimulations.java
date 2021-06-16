package testSimulations;

import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseApi;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class putTestApiSimulations extends BaseApi {
    Faker faker = new Faker(new Locale("pt-BR"));
    Random random = new Random();
    int i = random.nextInt(1);
    String nome = faker.name().fullName();
    String cpf = faker.number().digits(9);
    String email = faker.internet().emailAddress();
    int valor = random.nextInt(1000);
    int parcela = random.nextInt(9);
    boolean seguro[] = {false, true};

    String bodyValueupdate = "{\n" +
            "  \"nome\": \""+nome+"\",\n" +
            "  \"cpf\": "+cpf+",\n" +
            "  \"email\": \""+email+"\",\n" +
            "  \"valor\": "+valor+" ,\n" +
            "  \"parcelas\":"+parcela+",\n" +
            "  \"seguro\": "+seguro[i]+"\n" +
            "}";
    @Test
    public void putCpfUpdate(){
        given().pathParam("cpf","62648716050").contentType("application/json").body(bodyValueupdate).
                when().put("/v1/simulacoes/{cpf}").

                then().statusCode(HttpStatus.SC_OK);

    }
    @Test
    public void putCpfUpdateNotFound() {
        given().pathParam("cpf", "62648716051")
                .contentType("application/json")
                .body(bodyValueupdate)
                .when().put("/v1/simulacoes/{cpf}")
                .then().statusCode(HttpStatus.SC_NOT_FOUND)
                .body("mensagem", is("CPF 62648716051 não encontrado"));
    }
}
