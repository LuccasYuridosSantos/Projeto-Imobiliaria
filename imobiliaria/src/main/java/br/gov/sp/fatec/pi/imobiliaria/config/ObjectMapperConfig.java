package br.gov.sp.fatec.pi.imobiliaria.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para o ObjectMapper do Jackson.
 * O ObjectMapper é usado para serializar e desserializar objetos Java para JSON e vice-versa.
 */
@Configuration
public class ObjectMapperConfig {

  /**
   * Cria uma instância do ObjectMapper configurado.
   *
   * @return O ObjectMapper configurado
   */
  @Bean
  public ObjectMapper objectMapper() {
    final ObjectMapper objectMapper = new ObjectMapper();

    // Registra todos os módulos do ObjectMapper encontrados no classpath
    objectMapper.registerModules(ObjectMapper.findModules());

    // Configura o ObjectMapper para não falhar em propriedades desconhecidas durante a desserialização
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // Configura o ObjectMapper para incluir apenas propriedades não nulas na serialização
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    return objectMapper;
  }
}

