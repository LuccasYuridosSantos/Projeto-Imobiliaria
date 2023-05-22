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
     * O nome do usuário.
     */
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

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
     * @param nome O nome do usuário.
     * @param username O nome de usuário utilizado para realizar login.
     * @param senha A senha do usuário.
     */
    public Usuario(String nome, String username, String senha) {
        this.nome = nome;
        this.username = username;
        this.senha = senha;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

