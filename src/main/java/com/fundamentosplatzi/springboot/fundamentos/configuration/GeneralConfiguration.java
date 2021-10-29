package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
Anotacion que realiza la configuracion de nuestro pojo como dependencia e ingresamos la clase a configurar
Esta clase indica que se va a representar como propiedades dentro de la Aplicacion
 */
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
    //@Value + ("${nombre de la propiedad creada}")
    @Value("${value.name}")
    private String name;
    @Value("${value.apellido}")
    private String apellido;
    @Value("${value.random}")
    private String random;

    @Bean
    public MyBeanWithProperties function() {
        return new MyBeanWithPropertiesImplement(name, apellido);
    }
}
