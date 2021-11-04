package com.fundamentosplatzi.springboot.fundamentos.controller;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController// Hereda de la anotación controller y permite que todos los metodos se formateen como JSON.
@RequestMapping("/api/users")//Agregamos ruta por donde va a ser consumido este servicio
public class UserRestController {
    //Inyección por dependencia de los casos de uso
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;
    private UserRepository userRepository;
//Creamos el constructor para inyectar nuestra dependencia.

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser, UserRepository userRepository) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.userRepository = userRepository;
    }

    //Servicios: create, get, delete, update
    @GetMapping("/")
    List<User> get() {
        return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser) {
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);//Las respuetas http es una buena practica ya que nos da como resultado el estado de la insección con lo cual se puede manejar los resultado de la insección
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id) {//El id lo Mapeamos dentro de la anotación @PathVariable
        deleteUser.delete(id);
        //Respondemos una nueva instancia de ResponseEntity y pasamos como respuesta el HttpStatus.No_Content, primero xq responde como succesful y no tenemos contenido de respuesta a nivel de servicio.
        return new ResponseEntity(HttpStatus.NO_CONTENT);//204
    }


    //Recibe como parametro el nuevo usuario y el id del usuario a remplazar
    //Responde una nueva instancia de la entidad y el statud http 200 esto xq solo estamos actualizando
    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
    }

    //Servicio pageable
    @GetMapping("/pageable")
    List<User> getUserPegeable(@RequestParam int page, @RequestParam int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }

}
