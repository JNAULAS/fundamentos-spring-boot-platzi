package com.fundamentosplatzi.springboot.fundamentos.bean;

import java.util.Date;

public class MyOperationCalEdadImpl implements  MyOperationCalEdad{
    @Override
    public int EdadPersona(int anoNacimiento) {
        int anoactual =0;
        Date fecha = new Date();
        anoactual = fecha.getYear();
        return anoactual - anoNacimiento;
    }
}
