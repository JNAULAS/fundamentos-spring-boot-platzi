package com.fundamentosplatzi.springboot.fundamentos.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id//Representa la entidad a nivel de la tabla
    @GeneratedValue(strategy = GenerationType.AUTO)//Indica que se va a auto incrementar los codigos.
    @Column (name = "id_user", nullable = false,unique = true)//Indicamos el nombre de la columna de la tabla, que no acepte nulos y que su valor sea unico, esto evita tener valores duplicados.
    private Long id;//Representamos la columna de la base de datos como una propiedad a nivel de clase

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String email;


    private LocalDate birthDate;//Permite utilizar Java 8

    //Representamos la relacion con la entidad Post
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER) // Un usuario tiene muchos post, "user" esta es la propiedad que representa al usuario en la entidad post, el tipo de cacade va a ser todo, y tenemos un FetchTyoe=EAGER
    @JsonManagedReference // Anotacion para que cuando se acceda al servicio a nivel servico rest no nos de un error relacionado con Stack OverFlow
    private List<Post> posts = new ArrayList<>();//Retornamos una lista y le inicializamos con un array list

    public User() {
    }

    public User(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    //Generamos el metod ToString, para lo cual click derecho Generate, metodo ToString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", posts=" + posts +
                '}';
    }
}
