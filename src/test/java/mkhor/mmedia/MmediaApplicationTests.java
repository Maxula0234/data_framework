package mkhor.mmedia;

import io.restassured.RestAssured;
import mkhor.mmedia.dto.request.TranslateReq;
import mkhor.mmedia.dto.response.translate.TranslateResult;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class MmediaApplicationTests {

    @Test
    void contextLoads() {
        TranslateReq translateReq = TranslateReq.builder()
                .lang("ru-be")
                .text("утро")
                .build();

        TranslateResult as = RestAssured.given()
                .baseUri("https://dictionary.yandex.net/api/v1/dicservice.json/lookup")
                .queryParam("key", "dict.1.1.20220509T191313Z.b3ce9dfd8b1e2040.83e6d246fe3011331ab1949d990fb3ccfd423d21")
                .queryParam("lang", translateReq.getLang())
                .queryParam("text", translateReq.getText())
                .expect().statusCode(200)
                .when().post()
                .as(TranslateResult.class);
    }

}
