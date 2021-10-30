package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UserDTO;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//Extendemos de la interfaz JpaRepository que recibe dos parametros (entidad a mapear, y el tipo de dato del id de la entidad)
@Repository//Estereotipo utilizado para inyectar esta interfaz (UserRepository) como dependencia.
public interface UserRepository extends JpaRepository<User, Long> {
    //Metodos con el uso de JPQL
    //Optional ==> Permite realizar el manejo de null
    // Devolvemos un User , nombre de metodo y parametros que recibe
    // Usamos la anotacion @Query y aqui agregamos la consulta JPQL
    @Query("SELECT u FROM User u  WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);


    //Uso de libreria import org.springframework.data.domain.Sort;
    @Query("SELECT u FROM User u WHERE u.name LIKE ?1%")
//Metodo que busca y ordena.
    List<User> findAndSort(String name, Sort sort);

    //Query Methods
    List<User> findByName(String name);

    //Usamos un optional de tipo user para manejo de errores y que retorna una entidad User
    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);

    /*
    Query Methods con name parameters haciendo uso de JPQL

    Usamos nuestra DTO para representar los datos que vamos a obtener de nuestra base de datos
    Copiamos el paquete donde esta nuestra DTO -> com.fundamentosplatzi.springboot.fundamentos.dto y llamamos a nuestro DTO con su constructor vacio
    Creamos la instancia de nuestra DTO con su contructor vacio
    From entidad donde vamos a obtener la informacion y la representamos con la u, esta u va a traer los valores de la base de datos
    De esta forma seteamos los valores retornados por la base de datos a nuestar dto (SELECT new com.fundamentosplatzi.springboot.fundamentos.dto.UserDTO(u.id, u.name,u.birthDate))
    @Param("parameterFecha") LocalDate fecha ==> el parametro @Param("parameterFecha") se va a representar dentro de la variable del metodo  LocalDate fecha
    */
    @Query("SELECT new com.fundamentosplatzi.springboot.fundamentos.dto.UserDTO(u.id, u.name,u.birthDate)" +
            " FROM User u" +
            " WHERE u.birthDate =:parameterFecha" +
            " AND u.email =:parameterEmail"
    )
    Optional<UserDTO> getAllByBirthDateAndEmail(@Param("parameterFecha") LocalDate date, @Param("parameterEmail") String email);
}
