package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:connection.properties")//Aqui pasamos el class path de nuestro archivo de propiedades
/*
Anotacion que realiza la configuracion de nuestro pojo como dependencia e ingresamos la clase a configurar
Esta clase indica que se va a representar como propiedades dentro de la Aplicacion
 */
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
    //@Value + ("${nombre de la propiedad creada}")
    @Value("${value.name}")//Mapeamos la propiedad a una variable
    private String name;
    @Value("${value.apellido}")
    private String apellido;
    @Value("${value.random}")
    private String random;
//Propiedades para conectar a la base de datos
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${driver}")
    private String driver;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;


    @Bean
    public MyBeanWithProperties function() {
        return new MyBeanWithPropertiesImplement(name, apellido);
    }
    //Bean para configurar nuestra base de datos: Para construir utilizamos  DataSourceBuilder
    // DataSource es una interfaz, entonces cuando inyectemos esta dependencia nosotros ya podemos utilizar toda esta configuracion
    // Invertimos el control para que spring boot nos inyecte esta dependencia.
    /*
    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");//Driver
        dataSourceBuilder.url("jdbc:h2:mem:testdb");//Url a nivel de base de datos
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        //Retorna la configuracion construida
        return  dataSourceBuilder.build();
    }
    */
    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);//Driver
        dataSourceBuilder.url(jdbcUrl);//Url a nivel de base de datos
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        //Retorna la configuracion construida
        return  dataSourceBuilder.build();
    }

}
