package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependencyInterface;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentTwoImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
    //Formateo de codigo Alt+ Command+ L
    //Inyectamos nuestra dependencia, objeto a inyectar es nuestra interfaz
    private ComponentDependencyInterface componentDependencyInterface;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private InterfacePersona interfacePersona;
    private MyBeanWithProperties myBeanWithProperties;
    private EmpresaInterface empresaInterface;
    @Autowired
    private UserPojo userPojo;

    //Creamos el constructor y este recibe como parametro mi dependencia para poderlo inyectar
    // @Autowired - Esta anotacón ya no es obligatorio en versiones reciente.
    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependencyInterface componentDependencyInterface, MyBean myBean, MyBeanWithDependency myBeanWithDependency, InterfacePersona interfacePersona, MyBeanWithProperties myBeanWithProperties, EmpresaInterface empresaInterface) {
        //Igualamos nuestra variable de tipo dependencia inyectada de nuestra clase al parametro de entrada en el constructor
        //this.dependencia = parametro de entrada.
        this.componentDependencyInterface = componentDependencyInterface;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.interfacePersona = interfacePersona;
        this.myBeanWithProperties = myBeanWithProperties;
        this.empresaInterface = empresaInterface;
        //this.userPojo = userPojo;

    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        componentDependencyInterface.saludar();
        //Llamado a la implementacion
        myBean.print();
        //Llamamos a la implementación y llamamos al metodo implemetado
        myBeanWithDependency.printWithDependency();
        empresaInterface.imprimeEmpresa();
        interfacePersona.bienvenida("Juan Gabriel Naula", 36);
        System.out.println(myBeanWithProperties.function());
        //Uso de nuestro projo
        System.out.println("Sus datos de ingreso son: User: "+"\n"+userPojo.getEmail()+"\n"+" Password: "+userPojo.getPassword()+"\n"+"Se edad es:"+userPojo.getAge());

    }
}
