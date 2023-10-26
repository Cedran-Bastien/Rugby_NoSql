import Pojo.Match;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Connection {
    private static MongoClient mg;

    private static MongoCollection<Match> matchs;

    private static MongoDatabase md;

    public static MongoCollection<Match> getMatchsCollection(){
        if (matchs == null) {
            // Get POJO codec
            CodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry codecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(codecProvider));

            // Set up connection settings
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
            MongoClientSettings clientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .codecRegistry(codecRegistry)
                    .build();


            // Create connection and get collection
            mg = MongoClients.create(clientSettings);
            md = mg.getDatabase("coupeRugby");
            matchs = md.getCollection("matchs", Match.class);
        }

        return matchs;
    }

    public static void closeConnection() {
        if (mg != null)
            mg.close();
    }
}
