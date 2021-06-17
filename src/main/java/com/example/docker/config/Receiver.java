package com.example.docker.config;

import com.example.docker.model.Pessoa;
import com.example.docker.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    private final CountDownLatch latch = new CountDownLatch(1);
    private final PessoaRepository repository;

    public Receiver(PessoaRepository repository) {
        this.repository = repository;
    }

    public void receiveMessage(Pessoa msg) {
        try{
            LOG.info("*** Mensagem recebida dto: " + msg + " ***");
            repository.save(msg);
            latch.countDown();
        }catch (Exception e){
            LOG.error("Deu ruim pai");
        }
    }

    CountDownLatch getLatch() {
        return latch;
    }

}
