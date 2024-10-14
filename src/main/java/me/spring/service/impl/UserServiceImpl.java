package me.spring.service.impl;

import me.spring.domain.model.User;
import me.spring.domain.repository.UserRepository;
import me.spring.service.exception.BusinessException;
import me.spring.service.exception.NotFoundException;
import me.spring.service.interfaces.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

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
        ofNullable(userToCreate.getConta()).orElseThrow(() -> new BusinessException("User account must not be null."));
       

        
        if (userRepository.existsByContaNumber(userToCreate.getConta().getNumber())) {
            throw new BusinessException("This account number already exists.");
        }
        return this.userRepository.save(userToCreate);
    }

    @Transactional
    public User update(Long id, User userToUpdate) {
        User dbUser = this.findById(id);
        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbUser.setName(userToUpdate.getName());
        dbUser.setConta(userToUpdate.getConta());
 

        return this.userRepository.save(dbUser);
    }

    @Transactional
    public void delete(Long id) {

        User dbUser = this.findById(id);
        this.userRepository.delete(dbUser);
    }

}

