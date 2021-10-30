package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Extendemos de la interfaz JpaRepository que recibe dos parametros (entidad a mapear, y el tipo de dato del id de la entidad)
@Repository//Estereotipo utilizado para inyectar esta interfaz (UserRepository) como dependencia.
public interface UserRepository extends JpaRepository<User,Long> {
}
