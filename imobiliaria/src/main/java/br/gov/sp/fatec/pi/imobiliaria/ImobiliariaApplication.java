package br.gov.sp.fatec.pi.imobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Classe principal do aplicativo Imobiliaria.
 * Responsável por iniciar a aplicação Spring Boot.
 */
@SpringBootApplication
public class ImobiliariaApplication {

	/**
	 * Método principal para iniciar o aplicativo.
	 *
	 * @param args Os argumentos de linha de comando passados ao aplicativo.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ImobiliariaApplication.class, args);
	}

}