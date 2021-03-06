package com.fundamentosplatzi.springboot.fundamentos.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/*
@ConfigurationProperties(prefix = "user") ==>
Anotación que representa el archivo .properties, recibe prefix que es el que representa
al prefijo agregado al .properties
@ConstructorBinding ==>
Permite inyectar como dependencia, esto para construir el pojo a partir de las propiedades
*/
@ConstructorBinding
@ConfigurationProperties(prefix = "user")
public class UserPojo {
    //Atributos para representar las propiedades creadas en el archivo .properties
    private String email;
    private String password;
    private int age;

    //Creacion de constructor a partir de las propiedades (Atributos)

    public UserPojo(String email, String password, int age) {
        this.email = email;
        this.password = password;
        this.age = age;
    }
    //Creamos nuestros metodos Getter and Setter

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}