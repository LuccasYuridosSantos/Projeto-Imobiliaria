package br.gov.sp.fatec.pi.imobiliaria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para a definição da documentação do OpenAPI.
 * Define as informações gerais sobre a API, como título, versão, descrição e licença.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Cria uma instância personalizada do OpenAPI com as informações gerais da API.
     *
     * @return O objeto OpenAPI configurado
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Imobiliaria API")
                        .version("1.0")
                        .description("This is a sample project to college.")
                        .license(new License().name("Apache 2.0")));
    }
}


