package org.example.premierbears.user;

import jakarta.persistence.*;
import lombok.*;
import org.example.premierbears.role.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
//pour faire fonctionner l'auditing,
// il faut ajouter l'annotation @EnableJpaAuditing dans la classe principale de l'application
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {

    // The user's ID
    @Id
    @Generated
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    @Column(unique = true)
    private String email;
    private String password;
    //variables for the user's account status (locked, enabled, etc.)
    private boolean accountLocked;
    private boolean enabled;

    //fetch = FetchType.EAGER est utilisé pour charger les rôles de l'utilisateur
    //lorsque l'utilisateur est chargé à partir de la base de données
    //c'est-à-dire que les rôles de l'utilisateur seront chargés en même temps que l'utilisateur
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;


    //variables for the user's creation and update dates
    //the createdDate variable is annotated with @CreatedDate
    //to indicate that it should be automatically set when the user is created
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdDate;
    //the updatedDate variable is annotated with @LastModifiedDate
    //to indicate that it should be automatically set when the user is updated
    //the insertable = false attribute is used to indicate that the updatedDate
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedDate;
    @Override
    public String getName() {
        return email;
    }

    // cette methode permet de retourner les roles de l'utilisateur
    // sous forme de collection de GrantedAuthority
    // GrantedAuthority est une interface qui représente une autorité accordée à un utilisateur dans le cadre de Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    private  String fullName(){
        return firstname + " " + lastname;
    }
}
