
# Spring WebFlux

---

## Section 1: Intro

### T 01. Intro

- Build applications based on the Reactive Streams specification
- Is non-blocking and supports backpressure 
- Components HttpHandler and WebHandler
- Uses non blocking servers like
  - Netty (default)
  - Undertow

#### Reactive Flows features
- Continued asynchronous data flows
- Non-blocking processes, can be executed in threads
- Backpressure
- Operators transform data easily
- Can be created from streams, lists, ranges, intervals
- Are cancellable
- Are finite such as a Db query or infinite like a Stream
- Are immutable
- Easy concurrency
- Async error handling
- Retry on fail 

#### Components

- Spring Boot
- Reactor (Reactive Streams)
- String Webflux (uses rector)
- Spring Data Mongo Reactive
- Reactive Mongo Repository
- API Rest
  - Rest Controller
  - Router Function
- File upload
- Validation
- WebCliente


### T 02. Start up

- Required to have a good resolution and connection.

### T 03. Tools

- JDK: (can be latest) `choco install zulu17`
- Builder: Gradle `choco install gradle`
- Database: MongoDB `choco install mongodb`

### T 04. IDE

- IDE: (IntelliJ IDEA) `choco install intellijidea`
- VSCode and Eclipse can also be used.

## Section 2: Reactor API

### T 05. Reactive Streams
 
- Is a programming paradigm
- They are data flows processed async
- Observable: changes that can happen in the flow
- Publisher
  - Flux: 0 to N elements
  - Mono: single element
- map, filter, merge, delay, forEach: transform the data

#### Observable
- backpressure: 
  - if consumer is overloaded, producer throttles.
  - if producer is slow, consumer slows down
- Works with asynchronous continuous data.
- Can be created from other streams, lists, intervals, ranges, etc
- Are cancellable
- Can be finite, such as a database query, or infinite, such as a data stream.
- Are immutable
- Concurrency is simple: simplifies thread programming
- Asynchronous error management
- On failure, they can be retried

### T 06. Project w Reactor API

#### Reactive Streams

- The specification is in reactive-streams.org
- Is a standard to process an asynchronous stream
- Are available from JDK 9+
- Interfases
  - Publisher
  - Subscriber
- Project Reactor is used by Spring

### T 07. Create Stream Flux Observable.
º
- subscribe() creates the observer

### T 08. `suscribe()` method
- subscribe() with two parameter
  - consumer of type `Consumer<? super T>` 
  - errorConsumer `Consumer<? super Exception>`



### T 09. `onComplete()` event

- Un tercer pametro de suscribe toma un runnable


### T 10. `map()` operator

- Receives each element on the sequence and transform it

### T 11. `filter()` operator

- Filters the stream getting the elements that

### T 12. Observables are immutable

### T 13. Flux from List/Iterable

- Mono: one object
- Flux: several objects


### T 14. `flatMap()` operator

- It flattens to the same original type 
 
### T 15. Use of `flatMap()` to convert a Flux list to a Flux String

- It flattens to the same original type

### T 16. Converting an Observable Flix to Mono

- use `collectList`

### T 17. Use of `flatMap()` to merge two flows

- Combined with `flatMap` 

### T 18. Use of `zipWith()` to merge two flows

- Combined with `zipWIth`

### T 19. Range Operator
 

### T 20. Time intervals w `interval()`  and `zipWith()`

### T 21. Infinite intervals w `interval()`  and `delayElements()`

### T 22.  `create()`  operator to create an Observable Flux

### T 23.  `create()`  operator to create an Observable Flux
