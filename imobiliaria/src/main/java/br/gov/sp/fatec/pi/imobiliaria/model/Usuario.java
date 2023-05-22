package br.gov.sp.fatec.pi.imobiliaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A classe Usuario representa um usuário da aplicação.
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    /**
     * O id do usuário.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome de usuário utilizado para realizar login.
     */
    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

    /**
     * A senha do usuário.
     */
    @Column(name = "senha", length = 100, nullable = false)
    private String senha;

    /**
     * Construtor padrão.
     */
    public Usuario() {}

    /**
     * Construtor que inicializa um usuário com nome, nome de usuário e senha.
     * @param id O codigo de registro na base.
     * @param username O nome de usuário utilizado para realizar login.
     * @param senha A senha do usuário.
     */
    public Usuario(final Long id, final String username, final String senha) {
        this.id = id;
        this.username = username;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }
}

