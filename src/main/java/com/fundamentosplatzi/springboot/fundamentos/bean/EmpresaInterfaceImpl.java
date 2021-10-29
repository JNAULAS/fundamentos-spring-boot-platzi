package com.fundamentosplatzi.springboot.fundamentos.bean;

public class EmpresaInterfaceImpl  implements EmpresaInterface{
    MyOperation myOperation;
    //MyOperationCalEdad myOperationCalEdad;

    public EmpresaInterfaceImpl(MyOperation myOperation) {

        this.myOperation = myOperation;

    }
    /*
    public EmpresaInterfaceImpl(MyOperation myOperation, MyOperationCalEdad myOperationCalEdad) {

        this.myOperation = myOperation;
        this.myOperationCalEdad = myOperationCalEdad;

    }*/


    @Override
    public void imprimeEmpresa() {
        System.out.println("Total Empresa Registrada: "+ myOperation.sum(2));
        //System.out.println("La fecha de su nacimiento es: "+ myOperationCalEdad.EdadPersona(1985));
    }
}
