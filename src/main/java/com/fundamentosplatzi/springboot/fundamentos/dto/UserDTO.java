package com.fundamentosplatzi.springboot.fundamentos.dto;

import java.time.LocalDate;

public class UserDTO {
    private Long id;
    private String name;
    private LocalDate bithDate;

    public UserDTO(Long id, String name, LocalDate bithDate) {
        this.id = id;
        this.name = name;
        this.bithDate = bithDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBithDate() {
        return bithDate;
    }

    public void setBithDate(LocalDate bithDate) {
        this.bithDate = bithDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bithDate='" + bithDate + '\'' +
                '}';
    }
}
