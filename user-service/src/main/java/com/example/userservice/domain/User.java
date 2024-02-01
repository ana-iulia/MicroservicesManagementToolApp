package com.example.userservice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "description")
    private String description;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany()
    @JoinColumn(name = "user_id")
    private Set<UserProjectAssignment> projectAssignmets = new HashSet<>();

    @OneToMany()
    @JoinColumn(name = "user_id")
    private Set<Notification> notifications = new HashSet<>();

}
