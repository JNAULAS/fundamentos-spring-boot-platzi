package com.fundamentosplatzi.springboot.fundamentos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id//Representa la entidad a nivel de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)//crea un id único.
    @Column (name = "id_post", nullable = false,unique = true)//Indicamos el nombre de la columna de la tabla, que no acepte nulos y que su valor sea unico, esto evita tener valores duplicados.
    private Long id;//Representamos la columna de la base de datos como una propiedad a nivel de clase

    @Column(name = "description",length = 255)
    private String description;

    //Relacionamos con otra entidad que se llama usuario.
    @ManyToOne //Relacion ManyToOne porque muchos post tiene un Usuario
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    public Post() {
    }

    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
