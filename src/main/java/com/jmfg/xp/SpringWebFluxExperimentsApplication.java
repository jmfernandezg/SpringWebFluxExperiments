package com.jmfg.xp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringWebFluxExperimentsApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringWebFluxExperimentsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxExperimentsApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void bye() {
        WebClient.create()
                .post()
                .uri("http://localhost:8080/actuator/shutdown")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(log::info, error -> log.error(error.getMessage()), () -> log.info("shutdown finished!"));
    }

}
