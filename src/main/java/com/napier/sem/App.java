package com.napier.sem;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;


public class App
{
    public static void main(String[] args)
    {
        // Connect to MongoDB on local system - we're using port 27000
        MongoClient mongoClient = new MongoClient("localhost", 27000);
        // Get a database - will create when we use it
        MongoDatabase database = mongoClient.getDatabase("mydb");
        // Get a collection from the database
        MongoCollection<Document> collection = database.getCollection("test");
        // Create a document to store
        Document doc = new Document("name", "Kevin Sim")
                .append("class", "DevOps")
                .append("year", "2024")
                .append("result", new Document("CW", 95).append("EX", 85));
        // Add document to collection
        collection.insertOne(doc);

        // Check document in collection
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }
}
Now all we have to do is run the application normally (i.e. not as a Docker container). Select Run then Run and select App as the configuration. Your application should launch, connect to the MongoDB server running in the Docker container and perform some basic operations as shown. The console output will look something like the following:

        "C:\Program Files\Java\jdk1.8.0_181\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=51700:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_181\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\rt.jar;C:\Users\KevL\Desktop\devops_Demo\target\classes;C:\Users\KevL\.m2\repository\org\mongodb\mongodb-driver\3.6.4\mongodb-driver-3.6.4.jar;C:\Users\KevL\.m2\repository\org\mongodb\bson\3.6.4\bson-3.6.4.jar;C:\Users\KevL\.m2\repository\org\mongodb\mongodb-driver-core\3.6.4\mongodb-driver-core-3.6.4.jar" com.napier.devops.App
Feb 02, 2021 11:41:28 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Cluster created with settings {hosts=[localhost:27000], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
Feb 02, 2021 11:41:28 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Cluster description not yet available. Waiting for 30000 ms before timing out
Feb 02, 2021 11:41:28 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Opened connection [connectionId{localValue:1, serverValue:1}] to localhost:27000
Feb 02, 2021 11:41:28 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Monitor thread successfully connected to server with description ServerDescription{address=localhost:27000, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[4, 4, 3]}, minWireVersion=0, maxWireVersion=9, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=4243600}
Feb 02, 2021 11:41:28 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Opened connection [connectionId{localValue:2, serverValue:2}] to localhost:27000
        { "_id" : { "$oid" : "60193a68517ac5004c914c07" }, "name" : "Kevin Sim", "class" : "DevOps", "year" : "2024", "result" : { "CW" : 95, "EX" : 85 } }

Process finished with exit code 0
If you look at the logs of the MongoDB container in IntelliJ you will see the something similar to the following partial log:

        {"t":{"$date":"2021-02-02T11:41:28.619+00:00"},"s":"I",  "c":"NETWORK",  "id":22943,   "ctx":"listener","msg":"Connection accepted","attr":{"remote":"172.17.0.1:58692","connectionId":2,"connectionCount":2}}
        {"t":{"$date":"2021-02-02T11:41:28.619+00:00"},"s":"I",  "c":"NETWORK",  "id":51800,   "ctx":"conn2","msg":"client metadata","attr":{"remote":"172.17.0.1:58692","client":"conn2","doc":{"driver":{"name":"mongo-java-driver","version":"3.6.4"},"os":{"type":"Windows","name":"Windows 10","architecture":"amd64","version":"10.0"},"platform":"Java/Oracle Corporation/1.8.0_181-b13"}}}
        {"t":{"$date":"2021-02-02T11:41:28.639+00:00"},"s":"I",  "c":"STORAGE",  "id":20320,   "ctx":"conn2","msg":"createCollection","attr":{"namespace":"mydb.test","uuidDisposition":"generated","uuid":{"uuid":{"$uuid":"b862f932-c8aa-4b30-904b-0c3a1e6a1dd2"}},"options":{}}}
        {"t":{"$date":"2021-02-02T11:41:28.658+00:00"},"s":"I",  "c":"INDEX",    "id":20345,   "ctx":"conn2","msg":"Index build: done building","attr":{"buildUUID":null,"namespace":"mydb.test","index":"_id_","commitTimestamp":{"$timestamp":{"t":0,"i":0}}}}
        {"t":{"$date":"2021-02-02T11:41:29.035+00:00"},"s":"I",  "c":"NETWORK",  "id":22944,   "ctx":"conn2","msg":"Connection ended","attr":{"remote":"172.17.0.1:58692","connectionId":2,"connectionCount":0}}
        {"t":{"$date":"2021-02-02T11:41:29.035+00:00"},"s":"I",  "c":"NETWORK",  "id":22944,   "ctx":"conn1","msg":"Connection ended","attr":{"remote":"172.17.0.1:58688","connectionId":1,"connectionCount":1}}
A good time to push this update to GitHub.

Add files to commit.
Create commit.
Push to GitHub.
Now we need to modify our application so that it runs in our Docker containers.

        Linking Containers
Linking containers requires container discovery. There are a few ways to do this, but we will use the simplest. Docker networking and container discovery is an entire subject in itself, and outside the scope of this module.

We are going to undertake the following steps:

        Create a self-contained JAR for our project - this will include any external libraries.
        Add a network bridge to docker.
        Update our code files, Dockerfile, and MongoDB instance.
        Update GitHub Actions build file.
        Creating a Self-contained JAR
        So far we have not been doing good practice. For Java, JAR (Java ARchive) files should be deployed and not individual code files as we have been doing. The advantage of a JAR file is it can contain library dependencies, such as the MongoDB one we have added. Maven can build this for us automatically.

        First we must update our pom.xml file. Add the following below the dependencies section:

        <properties>
        <maven.compiler.source>10</maven.compiler.source>
        <maven.compiler.target>10</maven.compiler.target>
        </properties>

        <build>
        <plugins>
        <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-assembly-plugin</artifactId>
        <version>3.3.0</version>
        <configuration>
        <archive>
        <manifest>
        <mainClass>com.napier.devops.App</mainClass>
        </manifest>
        </archive>
        <descriptorRefs>
        <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
        </configuration>
        <executions>
        <execution>
        <id>make-assembly</id>
        <phase>package</phase>
        <goals>
        <goal>single</goal>
        </goals>
        </execution>
        </executions>
        </plugin>
        </plugins>
        </build>