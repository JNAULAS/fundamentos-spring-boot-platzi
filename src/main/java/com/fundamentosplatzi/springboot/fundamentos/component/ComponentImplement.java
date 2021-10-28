package com.fundamentosplatzi.springboot.fundamentos.component;

import org.springframework.stereotype.Component;
//Dependencia creada a partir de una interfaz
@Component
public class ComponentImplement implements  ComponentDependencyInterface{
    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi componente");
    }
}
