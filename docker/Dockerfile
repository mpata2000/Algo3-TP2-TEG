FROM maven:3.6.3-jdk-11 as base
WORKDIR /app
COPY pom.xml ./
RUN mvn install
CMD ["/bin/bash"]
