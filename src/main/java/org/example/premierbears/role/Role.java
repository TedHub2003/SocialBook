package org.example.premierbears.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.premierbears.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//pour faire fonctionner l'auditing,
// il faut ajouter l'annotation @EnableJpaAuditing dans la classe principale de l'application
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;

    // roles ici doit etre le meme nom que la variable roles dans la classe User
    // Le jsonIgnore est utilisé pour ignorer la sérialisation de la propriété roles
    //pour éviter une boucle infinie lors de la sérialisation de l'utilisateur
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;


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
}
