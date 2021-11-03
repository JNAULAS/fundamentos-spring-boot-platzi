package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependencyInterface;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentTwoImplement;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
    //Uso de logs, uso de clase Log de la libreria apache common y le agregamos el nombre de la app que estamos
    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

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
    private UserRepository userRepository;
    private UserService userService;

    //Creamos el constructor y este recibe como parametro mi dependencia para poderlo inyectar
    // @Autowired - Esta anotacón ya no es obligatorio en versiones reciente.
    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependencyInterface componentDependencyInterface, MyBean myBean, MyBeanWithDependency myBeanWithDependency, InterfacePersona interfacePersona, MyBeanWithProperties myBeanWithProperties, EmpresaInterface empresaInterface, UserRepository userRepository,UserService userService) {
        //Igualamos nuestra variable de tipo dependencia inyectada de nuestra clase al parametro de entrada en el constructor
        //this.dependencia = parametro de entrada.
        this.componentDependencyInterface = componentDependencyInterface;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.interfacePersona = interfacePersona;
        this.myBeanWithProperties = myBeanWithProperties;
        this.empresaInterface = empresaInterface;
        this.userRepository = userRepository;
        this.userService = userService;


    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //ejemplosAnteriores();
        saveUserInDataBase();
        getInformationJpqlFromUser();
        saveWithErrorTransactional();//Registro de usuarios con uso de @Transactional
    }

    //Metodo para guardar informacion
    private void saveUserInDataBase() {
        //Entidad variable = creamos una instancia a partir del constructor
        User user1 = new User("Jhon", "jhon@hotmail.com", LocalDate.of(2021, 10, 27));
        User user2 = new User("Julie", "julie@hotmail.com", LocalDate.of(2021, 10, 28));
        User user3 = new User("Daniela", "daniela@hotmail.com", LocalDate.of(2021, 10, 29));
        //Creamos una lista para poder guardar mas rapido
        List<User> listUser = Arrays.asList(user1, user2, user3);

        // utilizamos nuestro stream de java y por cada uno de nuestros usuarios realizamos un registro en la base de datos :: save
        listUser.stream().forEach(userRepository::save);

        /*
        Consulta y ordena la lista (letra a buscar, parametro de la clase sort para que se ordene a partir de una propiedad e indicamos si es descendente o ascendente)
        realizo .stream() y por cada propiedad voy a mostrar mi usuario  en los logs con nivel info
         */

        userRepository.findAndSort("J", Sort.by("id").descending())
                .stream().forEach(J -> LOGGER.info("Retorno con  metodo sort: " + J));

        //Llamamos a nuestro query methods
        userRepository.findByName("Jhon")
                .stream().forEach(user -> LOGGER.info("Resultado de query methods: " + user));

        LOGGER.info("El User con Optional es: " + userRepository.findByEmailAndName("julie@hotmail.com", "Julie")
                .orElseThrow(() -> new RuntimeException("Usuario no encontrato por name and email")));

        userRepository.findByNameLike("%J%").stream().forEach(user -> LOGGER.info("Usuario con Like es: " + user));
        userRepository.findByNameOrEmail(null, "jhon@hotmail.com").stream().forEach(user -> LOGGER.info("Usuario con name or email es: " + user));

        userRepository.findByBirthDateBetween(LocalDate.of(2021, 10, 10), LocalDate.of(2021, 10, 30)).stream().forEach(user -> LOGGER.info("Usuario con between o rango de fechas: " + user));
        //Debemos enviar la cadena para concatenar con el like
        userRepository.findByNameLikeOrderByIdDesc("%J%").stream().forEach(user -> LOGGER.info("findByNameLikeOrderByIdDesc: " + user));
        // Uso de withMethods con Containing
        userRepository.findByNameContainingOrderByIdDesc("J").stream().forEach(user -> LOGGER.info("findByNameContainingOrderByIdDesc: " + user));
        // Consulta de metodo con uso de @Param asignado a paramero de metodo
        LOGGER.info("Consulta de metodo con @param: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 10, 27), "jhon@hotmail.com")
                .orElseThrow(() -> new RuntimeException("No se encuentro el usuario")));
    }

    //
    private void getInformationJpqlFromUser() {
        // si no existe vamos caso contrario lasce una exception + expresion lambda (() ->) lanzamos una instancia de  RuntimeException
        LOGGER.info("Usuario existente: " +
                userRepository.findByUserEmail("jhon@hotmail.com")
                        .orElseThrow(() -> new RuntimeException("No se encontro el Usuario")));
    }

    public void ejemplosAnteriores() {
        componentDependencyInterface.saludar();
        //Llamado a la implementacion
        myBean.print();
        //Llamamos a la implementación y llamamos al metodo implemetado
        myBeanWithDependency.printWithDependency();
        empresaInterface.imprimeEmpresa();
        interfacePersona.bienvenida("Juan Gabriel Naula", 36);
        System.out.println(myBeanWithProperties.function());
        //Uso de nuestro projo
        System.out.println("Sus datos de ingreso son: User: " + "\n" + userPojo.getEmail() + "\n" + " Password: " + userPojo.getPassword() + "\n" + "Se edad es:" + userPojo.getAge());
        LOGGER.error("Representa un error del aplicativo");
        try {
            int valor = 10 / 0;
            LOGGER.debug("Mi valor es:" + valor);
        } catch (Exception e) {
            LOGGER.error("Error presentado al dividir por cero: " + e.getMessage());
        }
    }
    //Metodo para guardar información haciendo uso del transactional
    public void saveWithErrorTransactional(){
        User user1 = new User("UserTransactional1","userTransactional1@yahoo.com",LocalDate.now());
        User user2 = new User("UserTransactional2","userTransactional2@yahoo.com",LocalDate.now());
        User user3 = new User("UserTransactional3","userTransactional3@yahoo.com",LocalDate.now());
        User user4 = new User("UserTransactional4","userTransactional3@yahoo.com",LocalDate.now());
        User user5 = new User("UserTransactional5","userTransactional4@yahoo.com",LocalDate.now());

        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);
        try {
            userService.saveTransactional(users);
        }catch (Exception e){
            LOGGER.error("Se a producido un error al realizar el registro en la base de datos causado por: "+e.getMessage());
        }
            userService.getAllUsers()
                    .stream().forEach(userRetorno -> LOGGER.info("Retorno user luego de transacctional: "+userRetorno));

    }
}
