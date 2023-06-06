package br.gov.sp.fatec.pi.imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A classe Usuario representa um usuário do sistema.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome do usuário.
     */
    private String name;

    /**
     * O nome de usuário para autenticação.
     */
    @Column(unique = true)
    private String username;

    /**
     * A senha do usuário.
     */
    private String password;

    /**
     * O cliente associado ao usuário.
     */
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Cliente cliente;

    /**
     * A imobiliária associada ao usuário.
     */
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Imobiliaria imobiliaria;

    /**
     * As autoridades do usuário.
     */
    private String authorities;
    
    /**
     * token do usuário.
     */
    private String token;

    public Usuario(final Usuario usuario) {
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
    }

    /**
     * Retorna as autoridades do usuário.
     *
     * @return A lista de autoridades do usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return A senha do usuário.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Verifica se a conta do usuário está expirada.
     *
     * @return true se a conta não estiver expirada, false caso contrário.
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Verifica se a conta do usuário está bloqueada.
     *
     * @return true se a conta não estiver bloqueada, false caso contrário.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Verifica se as credenciais do usuário estão expiradas.
     *
     * @return true se as credenciais não estiverem expiradas, false caso contrário.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Verifica se o usuário está habilitado.
     *
     * @return true se o usuário estiver habilitado, false caso contrário.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
