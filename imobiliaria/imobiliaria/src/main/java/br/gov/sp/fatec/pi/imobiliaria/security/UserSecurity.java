package br.gov.sp.fatec.pi.imobiliaria.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;


/**
 * Classe responsável por validar o usuário e acesso utilizando a configuração do Spring Security.
 */
@Configuration
@EnableWebSecurity
public class UserSecurity extends WebSecurityConfiguration {

    /**
     * Método responsável por validar o usuário e acesso.
     */
    public void validarUsuarioEAcesso() {
        // TODO: Implementar lógica de validação do usuário e acesso.
    }

}
