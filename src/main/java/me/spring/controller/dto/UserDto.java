package me.spring.controller.dto;

import me.spring.domain.model.User;
import static java.util.Optional.ofNullable;


public record UserDto(
        Long id,
        String name,
        String cpf,
        String email,
        AccountDto account
        ) {

    public UserDto(User model) {
        this(   model.getId(),
                model.getName(),
                model.getCpf(),
                model.getEmail(),
                ofNullable(model.getConta()).map(AccountDto::new).orElse(null)
        );
    }

    public User toModel() {
        User model = new User();
        model.setCpf(this.cpf);
        model.setEmail(this.email);
        model.setName(this.name);
        model.setConta(ofNullable(this.account).map(AccountDto::toModel).orElse(null));
        return model;
    }

}


