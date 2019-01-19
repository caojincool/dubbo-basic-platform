#!/bin/bash
set -m
source /etc/profile

export JAVA_HOME=/data/services/jdk1.8.0_131
export M2_HOME=/data/apache-maven-3.0.3
export M2=$M2_HOME/bin
export MAVEN_OPTS="-Xms256m -Xmx512m"
export PATH=$M2:$PATH:

cd /data/code/trunk/SRC/
svn update .

cd /data/code/trunk/SRC/b2c-search/
mvn clean install -Dmaven.test.skip=true -Plsmysit

cd /data/code/trunk/SRC/b2c-ons/b2c-ons-common
mvn clean install -Dmaven.test.skip=true -Plsmysit

cd /data/code/trunk/SRC/b2c-search/b2c-search-api
mvn clean install -Dmaven.test.skip=true -Plsmysit

kill -9 $(ps -ef|grep tomcat-13201-search-api | grep -v grep|awk '{print $2}')

rm -rf /data/webservers/tomcat-13201-search-api/webapps/*

cp -r /data/code/trunk/SRC/b2c-search/b2c-search-api/target/b2c-search-api.war /data/webservers/tomcat-13201-search-api/webapps/ROOT.war

sh /data/webservers/tomcat-13201-search-api/bin/catalina.sh start
tail -f /data/webservers/tomcat-13201-search-api/logs/catalina.out

