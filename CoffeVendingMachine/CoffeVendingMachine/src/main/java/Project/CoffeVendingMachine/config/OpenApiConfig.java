package Project.CoffeVendingMachine.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basicAuth",
        scheme = "basic")
public class OpenApiConfig
{
    @Bean
    public OpenAPI usersMicroserviceOpenAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("Coffee vending machine")
                        .description("Efficiently manage ingredients and drink options for your vending machines. Create, update, and maintain tea and coffee offerings effortlessly")
                        .version("1.0"));
    }
}