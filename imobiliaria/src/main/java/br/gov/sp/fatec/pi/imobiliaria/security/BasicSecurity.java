package br.gov.sp.fatec.pi.imobiliaria.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classe de configuração de segurança básica.
 */
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class BasicSecurity extends WebSecurityConfigurerAdapter {

    private final UsuarioDetailsService usuarioDetailsService;

    /**
     * Configura as regras de segurança HTTP.
     *
     * @param http  Configuração do objeto HttpSecurity
     * @throws Exception  Exceção lançada em caso de erro na configuração
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
                .and().csrf().disable();
    }

    /**
     * Cria um bean do PasswordEncoder para codificar senhas.
     *
     * @return  Implementação do PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Configura o gerenciador de autenticação.
     *
     * @param auth  Gerenciador de autenticação
     * @throws Exception  Exceção lançada em caso de erro na configuração
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDetailsService);
        auth.inMemoryAuthentication()
                .withUser("root")
                .password(passwordEncoder().encode("root"))
                .authorities("ROLE_USER","ROLE_ADMIN");
    }
}

