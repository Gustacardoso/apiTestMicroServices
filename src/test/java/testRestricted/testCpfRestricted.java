package testRestricted;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseApi;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import static io.restassured.RestAssured.given;


public class testCpfRestricted extends BaseApi {

    @Test
    public void cpfRestricted() {
        given().
                pathParam("cpf", "97093236014").
        when().
            get("/v1/restricoes/{cpf}")
                .then().statusCode(HttpStatus.SC_OK)
                .body("mensagem", is("O CPF 97093236014 tem problema"));
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