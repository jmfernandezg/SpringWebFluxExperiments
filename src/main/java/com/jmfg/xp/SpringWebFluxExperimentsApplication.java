package com.jmfg.xp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class SpringWebFluxExperimentsApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(SpringWebFluxExperimentsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxExperimentsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        var list = List.of("andrew f", "david g", "hugo x", "joe r");

        Flux.fromIterable(list)
                .map(User::new)
                .doOnNext(it -> {
                    if (it.getFirstName().isEmpty()) {
                        throw new RuntimeException("Name cannot be empty");
                    } else {
                        System.out.println(it);
                    }
                })
                .subscribe(it -> log.info(it.toString()), error -> log.error(error.getMessage()), () -> log.info("flux finished!"));

        Flux.fromIterable(list)
                .map(User::new)
                .flatMap(it -> Mono.just(it.toString()))
                .subscribe(log::info, error -> log.error(error.getMessage()), () -> log.info("flatMap finished!"));

        Flux.fromIterable(list)
                .map(User::new)
                .collectList()
                .subscribe(it -> it.forEach(x -> log.info(x.toString())), error -> log.error(error.getMessage()), () -> log.info("collectList finished!"));

        System.exit(0);
    }

    public void bye() {
        WebClient.create()
                .post()
                .uri("http://localhost:8080/actuator/shutdown")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(log::info);
    }


    static class User {
        private final String firstName;
        private final String lastName;
        private final Integer id;

        public User(String name) {
            firstName = name.split(" ")[0];
            lastName = name.split(" ")[1];
            id = new Random().nextInt(1, 10);
        }

        @Override
        public String toString() {
            return String.format("User{firstName='%s', lastName='%s', id=[%d]}", firstName, lastName, id);
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public Integer getId() {
            return id;
        }
    }

}
