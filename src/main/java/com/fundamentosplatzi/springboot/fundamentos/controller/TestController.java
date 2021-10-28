package com.fundamentosplatzi.springboot.fundamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> function(){
        //Retornamos un new ResponseEntity del cuerpo que para este caso es un String y agregamos el estado de respuesta via http.

        return  new ResponseEntity<>("Hello from Controller un cambio con build - Build Project", HttpStatus.OK);
    }
}
