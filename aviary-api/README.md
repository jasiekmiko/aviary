App Engine Java Kotlin Servlet 3.1 with Java8
===

## Sample Servlet 3.1 written in Kotlin for use with App Engine Java8 Standard.

See the [Google App Engine standard environment documentation][ae-docs] for more
detailed instructions.

[ae-docs]: https://cloud.google.com/appengine/docs/java/

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi) (at least 3.5)
* [Google Cloud SDK](https://cloud.google.com/sdk/) (aka gcloud command line tool)

## Setup

* Download and initialize the [Cloud SDK](https://cloud.google.com/sdk/)

    `gcloud init`

* Create an App Engine app within the current Google Cloud Project

    `gcloud app create`

## Maven
### Running locally
There are two code-generation steps required for successful compilation:
1. Jooq looks at the schema of the h2 db and generates code for the tables. 
1. Dagger 2 looks for annotations (with help from `kapt`) on classes to
build dependency injection helpers.
Intellij's inbuilt compiler doesn't currently understand this :(.

Both are run by default with: `mvn compile` (in this directory).

To skip liquibase+jooq steps, run `mvn compile -skipDbUpdate=true`. (Note that this is useless atm, since there
exists a bug which causes `kapt` to fail if it runs twice without a `clean` between, while a `clean` will remove the
database-generated code. In the future a different maven profile could be considered which doesn't run any code
generationSee bug here:
`https://stackoverflow.com/questions/45151762/kotlin-kapt-java-lang-illegalstateexception-endpostable-already-set`. )

To run the application:`mvn appengine:run`

To use visit: http://localhost:8080/

### Deploying

`mvn appengine:deploy`

To use vist:  https://aviary-130922.appspot.com

## Testing

`mvn verify`

As you add / modify the source code (`src/main/java/...`) it's very useful to add [unit testing](https://cloud.google.com/appengine/docs/java/tools/localunittesting)
to (`src/main/test/...`).  The following resources are quite useful:

* [Junit4](http://junit.org/junit4/)
* [Mockito](http://mockito.org/)
* [Truth](http://google.github.io/truth/)


For further information, consult the
[Java App Engine](https://developers.google.com/appengine/docs/java/overview) documentation.
