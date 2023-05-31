package br.gov.sp.fatec.pi.imobiliaria.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
/**
 * Classe de configuração para personalizar o comportamento do WebMvc.
 * Implementa a interface WebMvcConfigurer para adicionar um resolvedor de argumento personalizado.
 */
@Configuration
public class WebMvcConfigurerImp implements WebMvcConfigurer {

  /**
   * Adiciona um resolvedor de argumento personalizado para lidar com a resolução de parâmetros do tipo Pageable.
   * Define um valor padrão para fallback caso não seja fornecido um parâmetro Pageable nas requisições.
   *
   * @param resolvers A lista de resolvedores de argumento
   */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
    pageHandler.setFallbackPageable(PageRequest.of(0, 10));
    resolvers.add(pageHandler);
  }
}

