# DDD Cinema

## Project setup

In order to make some objects more readable and understandable (that's part of the DDD approach) the project use lombok.
At [this link](https://www.baeldung.com/lombok-ide) you can find the instructions to active it in your favourite IDE.


## DDD open points

* If a command needs to take a decision based on the current time (e.g: _"You'll have 12 minutes to confirm the tickets"_), I identified two possible implementations:
    1. The command contains the current time, in this why the client is responsible to decide the value of _Now_
    1. The _CommandHandler_ interfaces himself with the infrastructure and identifies the _Now_ (this is the solution currently implemented)


## DDD workshop tasks

* Implement and test the QueryModel approach
* Split entities using the two main responsibilities (see Marco H. responsibilities splitting example):
    * status building: a class that only rebuild the status applying events
    * behaviours execution: a class that understand a command and generate one or more events
* It exists tickets for adult, student, child with varying prices
* Movie with 3D Movies and D-Box Seats cost extra. For students the price gets procentually redacted.


## Possible technical enhancements

* Create an _EventStore_ interface in order to isolate all the infrastructure from the _CommandHandler_
* Introduce JUnit 5 extensions to remove inheritance from BDDBaseTest: this inheritance doesn't specialize the class, but it only shares code ([an example](https://www.infoq.com/articles/deep-dive-junit5-extensions/)).


