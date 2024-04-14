# Manatee API

The following API is made for educational purposes only and does not provide any meaningful functionalities.

## Getting started

This project requires Java 21 to be installed.
For developers, Amazon Coretta or Eclipse Termium are recommended JDKs.

For development purposes only, the relational H2 database is initialized in the local runtime.
On the shutdown, the database is torn down. There is no other option to set a persistent database.

### For Linux users (bash)

```bash
./gradlew build # Generates OpenAPI models, builds the application and runs tests.
./gradlew bootRun # Starts the application on a local network. 
```

### For Windows users

```bash
gradlew build # Generates OpenAPI models, builds the application and runs tests.
gradlew bootRun # Starts the application on a local network. 
```


# Summary
| Question                                 | Answer |
|------------------------------------------|--------|
| Time  spent (h)                          | 12h     |
| Hardest task, (with reasoning)           | The most challenging task for me was the bonus task. It was more difficult than other tasks because I do not have as much practical experience with front-end than I have with back-end. What is more, doing styling can take a lot of time because there are no right or wrong answers and it is easy to not be satisfied with your work.   |
| Uncompleted tasks, if any                | None      |
| Additional dependencies (with reasoning) | Added "axios" for making HTTP requests to the backend, improving the ease and readability of the code.     | 


In summary, describe your overall experience with the topic, what you learned,
and any technical challenges you encountered. Your answer should be
between 50-100 words.

SUMMARY:
Overall, the experience was a valuable learning experience for me. Although I have previously worked with Spring Boot, the approach of auto-generating DTOs from the manatee-api.yaml file was new for me. Initially, the absence of DTO classes was confusing, but at one point after compiling the project, classes appeared as if out of thin air! Additionally, building the front end was more time-consuming than anticipated, still having some functionalities that I would like to change.
Front end of the Manatee application: [Manatee Front-end Repository.](https://github.com/K-AMeus/ProjectManateeFront)

