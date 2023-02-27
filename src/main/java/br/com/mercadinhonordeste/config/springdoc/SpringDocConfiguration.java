package br.com.mercadinhonordeste.config.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mercadinho Nordeste API")
                        .description("API Rest para gerenciar os produtos, compras e vendas do Mercadinho Nordeste")
                        .contact(new Contact()
                                .name("Marcos Dinei")
                                .email("marcos_dinei@ymail.com"))
                );
    }
}
