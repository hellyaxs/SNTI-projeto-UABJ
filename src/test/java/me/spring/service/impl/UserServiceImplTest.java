package me.spring.service.impl;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import me.spring.domain.model.User;
import me.spring.domain.repository.UserRepository;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreate() {
        User novouser = Instancio.create(User.class);
        when(userRepository.existsByContaNumber(novouser.getConta().getNumber())).thenReturn(false);
        when(userRepository.save(novouser)).thenReturn(novouser);

        assertEquals(novouser, userServiceImpl.create(novouser));

    }

    @Test
    void testFindById() {
        User novouser = Instancio.create(User.class);
        when(userRepository.findById(novouser.getId())).thenReturn(Optional.of(novouser));

        assertEquals(novouser, userServiceImpl.findById(novouser.getId()));

    }

    @Test
    void testUpdate() {
        User novouser = Instancio.create(User.class);
        User novouser2 = Instancio.create(User.class);
        when(userRepository.findById(novouser.getId())).thenReturn(Optional.of(novouser));
        when(userRepository.save(novouser)).thenReturn(novouser2);

        assertEquals(novouser2, userServiceImpl.update(novouser.getId(), novouser2));

    }
}
