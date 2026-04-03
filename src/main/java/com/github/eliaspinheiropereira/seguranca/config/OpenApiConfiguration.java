package com.github.eliaspinheiropereira.seguranca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Spring Security - login com usuario de banco de dados",
                version = "1.0",
                contact = @Contact(
                        name = "Elias Pinheiro Pereira",
                        email = "eliaspinheiropereiraa@gmail.com",
                        url = "http://www.github.com/eliaspinheiropereira"
                ),
                description = "API para fazer login utilizando um usuário armazenado em banco de dados"
        )
)
public class OpenApiConfiguration {
}
