package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Anotacion para indicar a spring boot que se tiene una configuracion adicional de nuestro bean
@Configuration
public class MyConfigurationBean {
    //Creamos una implementacion de nuestro Bean con la anotacion @Bean
    //Declaramos metodo que retorna nuestra interfaz MyBean.java
    //Debemos devolver la interfaz que implementa esta clase
    @Bean
    public MyBean beanOperation() {
        //Retornamos la implementacion de nuestro Bean, pero devemos agrega new para retornar instanciada esa impl
        return new MyBeanTwoImplement();
    }

    @Bean
    public MyOperation myOperationBean() {
        return new MyOperationImplement();
    }

    //Devemos devolver la interfaz que implementa la clase
    @Bean
    public MyBeanWithDependency myOperationBeanImplementWithDependency(MyOperation myOperation) {
        //Le pasamos la operacion
        return new MyBeanWithDependencyImplement(myOperation);
    }
    @Bean
    public InterfacePersona retornaPersona(){
        return new ImplementPersona();
    }
}
