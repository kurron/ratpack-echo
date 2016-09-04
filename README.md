# Overview
This project creates a quick and dirty Ratpack based microservice that spits back all know interfaces as well as a unique identifier of
the instance.  The intent of this service is to help showcase/diagnose load balanced microservices.  Hitting different endpoints with a
simple HTTP GET should tell you which instance you are being balanced to.

# Prerequisites
* [JDK 8](http://www.oracle.com/technetwork/java/index.html) installed and working

# Building
Type `./gradlew` will assemble an executable JAR file that can be dropped into a Docker container for testing.

# Installation
The project simply creates the artifact.  It is up to you to install it somewhere, such as a Docker container.

# Tips and Tricks

## Launching the Application
For quick testing, try `./gradlew run` and watch the log messages.  It will tell you what port the application is bound to.
When you are done, hit `ctrl-c` to shutdown the application.

You can also run it as an executable JAR.  Try `$JAVA_HOME/bin/java -jar build/libs/ratpack-echo-1.0.0.RELEASE-all.jar`.  This should
launch the application.

## Testing the Application
By default, the application is bound to port 5050 so you can hit it with a web browser or command-line HTTP client. Here is an example
using the very convenient [HTTPie](https://github.com/jkbrzt/httpie) tool:

```
http localhost:5050

HTTP/1.1 200 OK
content-encoding: gzip
content-type: text/plain;charset=UTF-8
transfer-encoding: chunked

{
    "info": {
        "instance": "6E16CFB8",
        "ip-0": "fe80:0:0:0:a00:27ff:fe8f:6fd2%enp0s3",
        "ip-1": "10.0.2.15",
        "ip-2": "0:0:0:0:0:0:0:1%lo",
        "ip-3": "127.0.0.1",
        "now": "2016-09-04T13:49:57.357"
    }
}
```

# Troubleshooting
TODO

# License and Credits
This project is licensed under the [Apache License Version 2.0, January 2004](http://www.apache.org/licenses/).

