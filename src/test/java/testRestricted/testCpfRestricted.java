package testRestricted;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseApi;
import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
import java.util.Random;


public class testCpfRestricted extends BaseApi {
    Random random = new Random();
    int i = random.nextInt(9);
    String test[] = {"97093236014","60094146012","60094146012", "62648716050", "26276298085",
                     "01317496094", "01317496094", "19626829001", "24094592008", "58063164083"} ;
    @Test
    public void cpfRestricted() {
        given().
                pathParam("cpf", test[i]).
        when().
            get("/v1/restricoes/{cpf}")
                .then().statusCode(HttpStatus.SC_OK)
                .body("mensagem", is("O CPF " +test[i]+" tem problema"));
    }
    @Test
    public void cpfNotRestricted(){
        given().
           pathParam("cpf", "99999999999").
        when().
            get("/v1/restricoes/{cpf}").
        then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

}