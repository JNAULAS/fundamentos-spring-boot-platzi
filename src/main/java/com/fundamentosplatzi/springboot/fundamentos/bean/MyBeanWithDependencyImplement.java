package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements  MyBeanWithDependency{
    //Dentro de esta implementacion llamamos a otra dependencia
    private MyOperation myOperation;
    //Creamos el constructo de nuestra clase para lo cual damos Click derecho y Generate y seleccionamos constructor
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        int numero =1;
        System.out.println("El resultado de la operacion de la dependencia inyetada dentro de la implementacion es: " + myOperation.sum(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
