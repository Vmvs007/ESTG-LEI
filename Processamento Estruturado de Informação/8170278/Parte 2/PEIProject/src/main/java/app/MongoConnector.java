package app;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class MongoConnector {
    private MongoClient mongoClient;

    public MongoConnector(){
        //TODO: Parameterizar connection string
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    }

    public String getData(String databaseName, String collectionName, String field, String value){
        MongoDatabase database = mongoClient.getDatabase(databaseName);//"restaurantsDB"
        MongoCollection<Document> collection = database.getCollection(collectionName);//"restaurants"
        Bson filter = eq(field, value);//borough, bronx

        //NOTA: Apenas apresenta os 10 primeiros resultados (limit(10))
        return StreamSupport.stream(collection.find(filter).limit(10).spliterator(), false)
                .map(Document::toJson)
                .collect(Collectors.joining(", ", "[", "]")).toString();
    }

    /**
     * Método que permite aplicar vários stages ao método aggregate
     * @param databaseName
     * @param collectionName
     * @param query A query deverá ser enviada com os parêntesis retos (representando os vários stages aplicados ao método aggregate. Por exemplo: "[{},{}]"
     * @return
     */
    public String aggregateDataByQueryString(String databaseName, String collectionName, String query){
        MongoDatabase database = mongoClient.getDatabase(databaseName);//"restaurantsDB"
        MongoCollection<Document> collection = database.getCollection(collectionName);//"restaurants"
        List<Document> myList=this.getAggregateStagesFromString(query);
        //Mapear o resultado para um array em JSON
        return StreamSupport.stream(collection.aggregate(myList).spliterator(), false)
                .map(Document::toJson)
                .collect(Collectors.joining(", ", "[", "]")).toString();
    }

    /**
     * Método responsável por interpretar a consulta aplicada ao método aggregate de forma a aplicar vários stages
     *
     * @param query A query deverá ser enviada com os parêntesis retos (representando os vários stages aplicados ao método aggregate. Por exemplo: "[{},{}]"
     * @return Lista de documentos JSON com a composição de cada stage
     */
    public List<Document> getAggregateStagesFromString(String query){
        List<Document> myList = new ArrayList<>();
        try {
            JSONArray jsonObj = new JSONArray(query);
            for(int i=0;i<jsonObj.length();i++){
                myList.add(Document.parse(jsonObj.get(i).toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myList;
    }


}
