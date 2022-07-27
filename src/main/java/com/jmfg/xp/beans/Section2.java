package com.jmfg.xp.beans;

import com.couchbase.client.core.env.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@EnableScheduling
public class Section2 implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(Section2.class);

    private void userCommentsFlatMap() {
        Mono<User> userMono = Mono.fromCallable(() -> new User("none moose"));
        Mono<Comments> commentsMono = Mono.fromCallable(() ->
                new Comments()
                        .addComment("comment 1")
                        .addComment("comment 2")
                        .addComment("comment 3")
        );

        userMono.flatMap(user -> commentsMono.map(comment -> new UserComments(user, comment)))
                .subscribe(it -> log.info(it.toString()), error -> log.error(error.getMessage()), () -> log.info("flatMap finished!"));
    }

    private void userCommentsZipWith() {
        Mono<User> userMono = Mono.fromCallable(() -> new User("caca pipi"));

        Mono<Comments> commentsMono = Mono.fromCallable(() ->
                new Comments()
                        .addComment("comment 1")
                        .addComment("comment 2")
                        .addComment("comment 3")
        );

        userMono.zipWith(commentsMono, UserComments::new)
               .subscribe(it -> log.info(it.toString()), error -> log.error(error.getMessage()), () -> log.info("zipWith finished!"));
    }

    private void flux(List<String> list) {
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
    }

    private void flatMap(List<String> list) {
        Flux.fromIterable(list)
                .map(User::new)
                .flatMap(it -> Mono.just(it.toString()))
                .subscribe(log::info, error -> log.error(error.getMessage()), () -> log.info("flatMap finished!"));

    }

    private void collectList(List<String> list) {
        Flux.fromIterable(list)
                .map(User::new)
                .collectList()
                .subscribe(it -> it.forEach(x -> log.info(x.toString())), error -> log.error(error.getMessage()), () -> log.info("collectList finished!"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        var list = List.of("andrew fox", "david gun", "hugo xen", "joe rogers");

        flux(list);

        flatMap(list);

        collectList(list);

        userCommentsFlatMap();

        userCommentsZipWith();
    }


    static class Comments {
        private final List<String> comments = new ArrayList<>();

        public Comments addComment(String comment) {
            comments.add(comment);
            return this;
        }

        @Override
        public String toString() {
            return String.format("Comment{comments=%s}", comments);
        }
    }

    static class UserComments {
        private final User user;
        private final Comments comments;

        public UserComments(User user, Comments comments) {
            this.user = user;
            this.comments = comments;
        }

        @Override
        public String toString() {
            return String.format("UserComments{user=%s, comments=%s}", user, comments);
        }
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
            return String.format("User{firstName='%s', lastName='%s', id=[%d]}", firstName, getLastName(), getId());
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
