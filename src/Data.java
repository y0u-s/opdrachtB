import java.util.ArrayList;
import java.util.HashMap;

import com.mongodb.DBCursor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import static java.util.Arrays.asList;

import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import org.bson.Document;

import com.google.common.collect.Lists;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class Data {

	MongoClient mongoClient = new MongoClient();
	DB db = mongoClient.getDB("project");
	AggregationOutput output;
	AggregationOutput output1;
	DBCollection coll = db.getCollection("gps");
	ArrayList<String> unitIds = new ArrayList<String>();
	DBCollection gps = db.getCollection("gps");

	public void getData() {
		
	
		

		DBCollection coll = db.getCollection("gps");
		
		Map<String, Object> dbo = new HashMap<String, Object>();
		dbo.put("_id", "$Rdx");
		dbo.put("_id", "$Rdy");
		DBObject group = new BasicDBObject("_id", new BasicDBObject(dbo));
		output = coll.aggregate(group);
		
		
		for (DBObject result : output.results()) {
			System.out.println(result);
		}
	}

	public void getUnitId() {
		DBObject group = new BasicDBObject("$group",
				new BasicDBObject("_id", "$UnitId").append("count", new BasicDBObject("$sum", 1)));
		output = coll.aggregate(group);
		for (DBObject result : output.results()) {
			// System.out.println("Quality: " + result.get("_id"));
			unitIds.add(result.get("_id").toString());

		}
	}

	public void saveData() {

		Iterator<DBObject> mc = output.results().iterator();
		Iterator<DBObject> iteratorToArray = mc;
		List<DBObject> convertedIterator = Lists.newArrayList(iteratorToArray);
		DBObject options = BasicDBObjectBuilder.start().add("capped", true).add("size", 2000000000l).get();
		DBCollection collection = db.createCollection("results", options);
		collection.insert(convertedIterator);
	}

}
