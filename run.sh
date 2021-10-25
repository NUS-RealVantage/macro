fuser -k 8765/tcp
nohup java -Dspring.config.location=/home/ubuntu/realvantage/macro/application.properties -jar /home/ubuntu/realvantage/macro/macro-0.0.1-SNAPSHOT.jar &
