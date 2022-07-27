package com.jmfg.xp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.List;

@SpringBootApplication
public class SpringWebFluxExperimentsApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(SpringWebFluxExperimentsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxExperimentsApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        var list = List.of("andrew 1", "david 2", "hugo 3", "joe 4");
        var names = Flux.fromIterable(list);
        var users = names.map(it -> new User(it, 0))
                .doOnNext(it -> {
                    if (it.getName().isEmpty()) {
                        throw new RuntimeException("Name cannot be empty");
                    } else {
                        System.out.println(it);
                    }
                });
        users.subscribe(it -> log.info(it.getName()),
                error -> log.error(error.getMessage()),
                () -> log.info("Something finished!"));
    }

    static class User {
        private final String name;
        private final Integer id;

        public User(String name, Integer id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public Integer getId() {
            return id;
        }
    }

}
