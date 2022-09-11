package mkhor.cleantestdata.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Test Data FrameWork")
                                .contact(new Contact()
                                        .email("test@mail.ru")
                                        .name("Max")
                                        .url("ya.ru")
                                )
                                .description("Фремворк для хранения тестовых данных \n" +
                                        "- Клиенты \n" +
                                        "- Карты")
                                .version("1.0.0")
                );
    }
}