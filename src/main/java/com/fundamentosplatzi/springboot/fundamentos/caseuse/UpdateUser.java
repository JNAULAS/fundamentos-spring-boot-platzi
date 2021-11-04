package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    //Inyectamos la dependencia para registro y consulta de información de usuarios.
    private UserService userService;

    //Creamos el constructor que permite inyectar la dependencia
    public UpdateUser(UserService userService) {
        this.userService = userService;
    }

    public User update(User newUser, Long id) {
       return userService.update(newUser,id);
    }
}
