package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOGGER = LogFactory.getLog(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
@Transactional//Si existe un error al realizar el insert, esta Transactional permite realizar el rollback de todas las tranacciones que se an hido registrando a nivel de base de datos.
    public void saveTransactional(List<User> users) {
        users.stream()
                .peek(user -> LOGGER.info("Usurio Insertado: "+user))
                .forEach(userRepository::save);//En java 8 podemos hacer uso de los metodos a traves de referencias del save el va a obtener la entidad, esa entidad se llama con los ::
                //.forEach(user -> userRepository.save(user));
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
