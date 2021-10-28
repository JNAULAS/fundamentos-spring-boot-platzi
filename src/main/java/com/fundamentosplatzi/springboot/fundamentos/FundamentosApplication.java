package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.InterfacePersona;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependencyInterface;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentTwoImplement;
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

    //Creamos el constructor y este recibe como parametro mi dependencia para poderlo inyectar
    // @Autowired - Esta anotacón ya no es obligatorio en versiones reciente.
    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependencyInterface componentDependencyInterface, MyBean myBean, MyBeanWithDependency myBeanWithDependency, InterfacePersona interfacePersona,MyBeanWithProperties myBeanWithProperties) {
        //Igualamos nuestra variable de tipo dependencia inyectada de nuestra clase al parametro de entrada en el constructor
        //this.dependencia = parametro de entrada.
        this.componentDependencyInterface = componentDependencyInterface;
        this.myBean = myBean;
        this.myBeanWithDependency=myBeanWithDependency;
        this.interfacePersona = interfacePersona;
        this.myBeanWithProperties = myBeanWithProperties;

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
        interfacePersona.bienvenida("Juan Gabriel Naula",36);
        System.out.println(myBeanWithProperties.function());
    }
}
