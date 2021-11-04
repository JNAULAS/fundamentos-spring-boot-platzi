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

    @Transactional
//Si existe un error al realizar el insert, esta Transactional permite realizar el rollback de todas las tranacciones que se an hido registrando a nivel de base de datos.
    public void saveTransactional(List<User> users) {
        users.stream()
                .peek(user -> LOGGER.info("Usurio Insertado: " + user))
                .forEach(userRepository::save);//En java 8 podemos hacer uso de los metodos a traves de referencias del save el va a obtener la entidad, esa entidad se llama con los ::
        //.forEach(user -> userRepository.save(user));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //Metodo para regitrar los nuevos usuarios enviados desde nuestro servicio.
    public User save(User newUser){
        return userRepository.save(newUser);
    }
    public void delete(Long id){
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        // 1) Obtenemos el usuario en base al id
        // 2) En caso de encontra realizamos el mapeo del usuario encontrado y al usuario encontrato le seteamos los nuevos datos del nuevo usuario ingresado como parametro
        // 3) Una vez seteado los parametros procedemos a retornar el usuario guardado.
        // 4) Agregamso el metod Get dentro de map ya que nuestro findById devuelve un optional a partir de nuestra entidad user
        // 5) Retornamos dentro de nuestro metodo update el metod get.
        return
                userRepository.findById(id)
                .map(
                        user -> {
                            user.setName(newUser.getName());
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            return userRepository.save(user);
                        }
                ).get();
    }
}
