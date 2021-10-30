package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Extendemos de la interfaz JpaRepository que recibe dos parametros (entidad a mapear, y el tipo de dato del id de la entidad)
@Repository//Estereotipo utilizado para inyectar esta interfaz (UserRepository) como dependencia.
public interface UserRepository extends JpaRepository<User,Long> {
    //Metodos con el uso de JPQL
    //Optional ==> Permite realizar el manejo de null
    // Devolvemos un User , nombre de metodo y parametros que recibe
    // Usamos la anotacion @Query y aqui agregamos la consulta JPQL
    @Query("SELECT u FROM User u  WHERE u.email=?1")
    Optional<User> findByUserEmail (String email);
}
