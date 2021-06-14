package test;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

public class BaseApi {

   @BeforeMethod
   public static void baseapi(){
      baseURI = "http://localhost";
      basePath = "/api";
      port = 8080;

      config = RestAssuredConfig.newConfig().jsonConfig(JsonConfig.jsonConfig().numberReturnType(BIG_DECIMAL));
      enableLoggingOfRequestAndResponseIfValidationFails();
   }

}
