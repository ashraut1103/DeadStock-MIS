package deadstock.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public class DatabaseHandler 
{
    public DatabaseHandler()
    {
        MongoClient mongo = new MongoClient();
        MongoIterable<String> allDatabases = mongo.listDatabaseNames();
        boolean flag = false;
        for(String db:allDatabases)
        {
            if(db.equals("deadstock"))
            {
                System.out.println("Database exists !");
                flag = true;
            }
        }
        if(!flag)
        {
            System.out.println("Database not found! Creating database !");
            MongoDatabase db = mongo.getDatabase("deadstock");
            db.createCollection("login");
            MongoCollection<Document> col = db.getCollection("login");
            Document obj = new Document();
            obj.put("user", "admin");
            obj.put("pass", "12345678");
            obj.put("security", "default");
            col.insertOne(obj);
            System.out.println("Database Created Successfully !");
            System.out.println("Username : admin \nPassword : 12345678 \nSecurity : default");
        }
    }
}
