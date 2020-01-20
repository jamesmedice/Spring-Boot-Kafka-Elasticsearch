# poc-kafka

# Spring Boot KAFKA

ZooKeeper Installation
1. Go to your ZooKeeper config directory. For me its C:\zookeeper-[version]\conf

2. Rename file “zoo_sample.cfg” to “zoo.cfg”

3. Open zoo.cfg in any text editor, like Notepad; I prefer Notepad++.

4. Find and edit dataDir=/tmp/zookeeper to :\zookeeper-3.4.7\data  

5. Add an entry in the System Environment Variables as we did for Java.

a. Add ZOOKEEPER_HOME = C:\zookeeper-[version] to the System Variables.

b. Edit the System Variable named “Path” and add ;%ZOOKEEPER_HOME%\bin; 

6. You can change the default Zookeeper port in zoo.cfg file (Default port 2181).

7. Run ZooKeeper by opening a new cmd and type zkserver.


kafka installation: 
Go to your Kafka config directory. For me its C:\kafka_[version]\config

2. Edit the file “server.properties.”

3. Find and edit the line log.dirs=/tmp/kafka-logs” to “log.dir= C:\kafka_[version]\kafka-logs.

kafka server : .\bin\windows\kafka-server-start.bat .\config\server.properties



# CREATE TOPICS :

@echo off 

call kafka_2.12-2.3.1\bin\windows\kafka-topics.bat  --create --zookeeper localhost:2181 --replication-factor 1 --partitions 8 --topic stackHoldertopic
@echo topic   created
@pause
 
call kafka_2.12-2.3.1\bin\windows\kafka-topics.bat  --create --zookeeper localhost:2181 --replication-factor 1 --partitions 8 --topic directortopic
@echo topic   created
@pause


# yaml config files

check the files:  application.yaml configuring the properties for the topics !!!
kafka:
  topic:
    json:
      stackHolder: stackHoldertopic
      director: directortopic

  