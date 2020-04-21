FROM tomcat:8
RUN apt-get update && apt-get -y upgrade
COPY target/*.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
EXPOSE 8080
