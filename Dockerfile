FROM tomcat:9.0.65-jdk17-corretto

EXPOSE 8080

COPY /target/escape_quest.war /usr/local/tomcat/webapps/