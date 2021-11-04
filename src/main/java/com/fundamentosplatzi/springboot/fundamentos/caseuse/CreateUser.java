package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component //Anotacion que se agrega cuando queremos hacer algo muy general, para este caso representa los casos de uso.
public class CreateUser {
    //Inyectamos la dependencia para registro y consulta de información de usuarios.
    private UserService userService;

    //Creamos el constructor que permite inyectar la dependencia
    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser){
        return userService.save(newUser);
    }

}
