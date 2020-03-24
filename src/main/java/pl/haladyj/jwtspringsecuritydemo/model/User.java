package pl.haladyj.jwtspringsecuritydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 40)
    private String name;

    @NotBlank
    @Column(nullable = false, name = "username")
    @Size(max=15)
    private String userName;

    @NotBlank
    @Column(nullable = false)
    @NaturalId
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(max=100)
    private String password;
}
