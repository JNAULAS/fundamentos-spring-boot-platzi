package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {
    //Inyectamos la dependencia para registro y consulta de información de usuarios.
    private UserService userService;

    //Creamos el constructor que permite inyectar la dependencia
    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    //Metodo para eliminar el usuario
    public void delete(Long id){
        userService.delete(id);
    }
}
