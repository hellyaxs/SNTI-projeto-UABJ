package me.spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import me.spring.domain.model.Conta;
import me.spring.domain.repository.ContaRepository;

@SpringBootTest
public class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        
    }



    @Test
    void testDepositar() {
        Conta minhacontatest = Instancio.create(Conta.class);
        minhacontatest.setSaldo(BigDecimal.valueOf(0));
        when(contaRepository.findByNumber(minhacontatest.getNumber())).thenReturn(minhacontatest);
        when(contaRepository.save(minhacontatest)).thenReturn(minhacontatest);
        when(contaService.depositar(minhacontatest.getNumber(),BigDecimal.valueOf(1200)))
        .thenReturn(minhacontatest);

        contaService.depositar(minhacontatest.getNumber(), BigDecimal.valueOf(1200));

        assertEquals(BigDecimal.valueOf(1200),contaService.findByContaNumber(minhacontatest.getNumber()).getSaldo());

    }

    @Test
    void testExistsByContaNumber() {
         Conta minhacontatest = Instancio.create(Conta.class);
         when(contaRepository.existsByNumber(minhacontatest.getNumber())).thenReturn(true);
         when(contaService.existsByContaNumber(minhacontatest.getNumber())).thenReturn(true);

         assertTrue(contaService.existsByContaNumber(minhacontatest.getNumber()));
    }

    @Test
    void testFindByContaNumber() {
        Conta minhacontatest = Instancio.create(Conta.class);
        when(contaRepository.findByNumber(minhacontatest.getNumber())).thenReturn(minhacontatest);
        when(contaService.findByContaNumber(minhacontatest.getNumber())).thenReturn(minhacontatest);

        assertEquals(minhacontatest,contaService.findByContaNumber(minhacontatest.getNumber()));

    }

    @Test
    void testTrasfeir() {
        Conta minhacontatest = Instancio.create(Conta.class);
        Conta minhacontatest2 = Instancio.create(Conta.class);
        minhacontatest.setSaldo(BigDecimal.valueOf(1200));
        minhacontatest2.setSaldo(BigDecimal.valueOf(0));

        when(contaRepository.findByNumber(minhacontatest.getNumber())).thenReturn(minhacontatest);
        when(contaRepository.findByNumber(minhacontatest2.getNumber())).thenReturn(minhacontatest2);
        when(contaRepository.save(minhacontatest)).thenReturn(minhacontatest);
        when(contaRepository.save(minhacontatest2)).thenReturn(minhacontatest2);
        // when(contaService.trasfeir(minhacontatest.getNumber(), minhacontatest2.getNumber(), BigDecimal.valueOf(1200))).thenReturn(minhacontatest);
        contaService.trasfeir(minhacontatest.getNumber(), minhacontatest2.getNumber(), BigDecimal.valueOf(1200));

        assertEquals(BigDecimal.valueOf(0),contaService.findByContaNumber(minhacontatest.getNumber()).getSaldo());
        assertEquals(BigDecimal.valueOf(1200),contaService.findByContaNumber(minhacontatest2.getNumber()).getSaldo());
          
    }
}
