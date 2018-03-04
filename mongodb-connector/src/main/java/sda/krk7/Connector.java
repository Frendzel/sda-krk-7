package sda.krk7;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoCredential.createCredential;

public class Connector {

    private MongoClient mongoClient;

    public MongoDatabase connect() {
        PropertiesLoader properties = new PropertiesLoader();
        ServerAddress serverAddress = new ServerAddress(properties.getHost(), properties.getPort());
        MongoCredential credential = createCredential(properties.getUser(), properties.getDB(), properties.getPassword().toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);
        mongoClient = new MongoClient(serverAddress, credentials);
        return mongoClient.getDatabase(properties.getDB());
    }
}
