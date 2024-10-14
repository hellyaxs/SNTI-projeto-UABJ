package me.spring.service.impl;

import static java.util.Optional.ofNullable;

import java.lang.StackWalker.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.spring.domain.model.Conta;
import me.spring.domain.model.User;
import me.spring.domain.repository.UserRepository;
import me.spring.service.exception.BusinessException;
import me.spring.service.exception.NotFoundException;
import me.spring.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public User create(User userToCreate) {
        ofNullable(userToCreate).orElseThrow(() -> new BusinessException("User to create must not be null."));
        userToCreate.setConta(Conta.builder()
        .agency(new Random().nextInt(9999) + "")
        .number(new Random().nextInt(999999) + "")
        .limite(BigDecimal.ZERO)
        .saldo(BigDecimal.ZERO)
        .card(null)
        .movimentacoes(null)
        .build());
       
        
        if (userRepository.existsByContaNumber(userToCreate.getConta().getNumber())) {
            throw new BusinessException("This account number already exists.");
        }
        return this.userRepository.save(userToCreate);
    }

    @Transactional
    public User update(Long id, User userToUpdate) {
        User dbUser = this.findById(id);
        ofNullable(userToUpdate.getName()).ifPresent(dbUser::setName);
        ofNullable(userToUpdate.getCpf()).ifPresent(dbUser::setCpf);
        ofNullable(userToUpdate.getEmail()).ifPresent(dbUser::setEmail);
        ofNullable(userToUpdate.getConta()).ifPresent(dbUser::setConta);
        return this.userRepository.save(dbUser);
    }

    @Transactional
    public void delete(Long id) {

        User dbUser = this.findById(id);
        this.userRepository.delete(dbUser);
    }

}

