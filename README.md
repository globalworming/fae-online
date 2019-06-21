## build on circle ci [![CircleCI](https://circleci.com/gh/globalworming/fae-online/tree/master.svg?style=svg)](https://circleci.com/gh/globalworming/fae-online/tree/master)

## prerequisutes

* java
* maven
* node
* chrome

## run locally

    # check out the project
    createdb fate
    # change the credentials in application.json
    cd src/react-client
    npm package
    cd $projectRoot
    mvn spring-boot:run
    
