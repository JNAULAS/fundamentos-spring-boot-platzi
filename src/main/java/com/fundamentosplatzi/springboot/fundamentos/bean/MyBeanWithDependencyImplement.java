package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements  MyBeanWithDependency{
    //Declaracion de variable tipo error
    private final Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    //Dentro de esta implementacion llamamos a otra dependencia
    private MyOperation myOperation;
    //Creamos el constructo de nuestra clase para lo cual damos Click derecho y Generate y seleccionamos constructor
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Ingreso al metodo printWithDependency()");
        int numero =1;
        LOGGER.debug("El numeo enviado como parametro a la dependencia operation es: "+numero);
        System.out.println("El resultado de la operacion de la dependencia inyetada dentro de la implementacion es: " + myOperation.sum(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
