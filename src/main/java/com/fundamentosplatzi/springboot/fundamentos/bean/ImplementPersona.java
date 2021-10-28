package com.fundamentosplatzi.springboot.fundamentos.bean;

public class ImplementPersona implements InterfacePersona {
    @Override
    public void bienvenida(String nombres, int edad) {
        System.out.println("******************" + "\n" +
                "Datos Personales" + "\n" +
                "Nombres: " + nombres + "\n" +
                "Edad: " + edad + "\n" +
                "******************");
    }
}
