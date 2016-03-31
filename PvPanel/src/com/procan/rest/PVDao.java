package com.procan.rest;

import java.io.IOException;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class PVDao {


	public String getAllPV() {
		String resList = null;
		String xml = "";
		try {
			ResourceBundle PropBD = ResourceBundle.getBundle("BD");
			String DB = PropBD.getString("bd");
			String COLLECTION = PropBD.getString("COLLECTION");

			MongoClient mongoClient = new MongoClient(new ServerAddress(
					"127.0.0.1"));
			MongoDatabase db = mongoClient.getDatabase(DB);
			MongoCollection<Document> Collection = db.getCollection(COLLECTION);
			Document b = new Document().append("device_id", "id");
			FindIterable<Document> iterable = Collection.find();

			MongoCursor<Document> mongoIT = iterable.iterator();
			while (mongoIT.hasNext()) {
				Document doc = mongoIT.next();
				
				xml += "<Device><ts>";
				xml += doc.get("ts");
				xml += "</ts>";
				xml += "<DeviceId>";
				xml += doc.get("device_id");
				xml += "</DeviceId>";
				xml += "<Production>";
				xml += doc.get("production");
				xml += "</Production></Device>";

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "<?xml version=\"1.0\"?> <PvPanel>" + xml + "</PvPanel>";
	}

	public String getPv(String id) {
		String resList = null;
		String xml = "";
		try {

			ResourceBundle PropBD = ResourceBundle.getBundle("BD");
			String DB = PropBD.getString("bd");
			String COLLECTION = PropBD.getString("COLLECTION");

			MongoClient mongoClient = new MongoClient(new ServerAddress(
					"127.0.0.1"));
			MongoDatabase db = mongoClient.getDatabase(DB);
			MongoCollection<Document> Collection = db.getCollection(COLLECTION);
			FindIterable<Document> iterable = Collection.find(new Document()
					.append("device_id", id));

			MongoCursor<Document> mongoIT = iterable.iterator();
			while (mongoIT.hasNext()) {
				Document doc = mongoIT.next();
			
				xml += "<Device><ts>";
				xml += doc.get("ts");
				xml += "</ts>";
				xml += "<DeviceId>";
				xml += doc.get("device_id");
				xml += "</DeviceId>";
				xml += "<Production>";
				xml += doc.get("production");
				xml += "</Production></Device>";

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "<?xml version=\"1.0\"?> <PvPanel>" + xml + "</PvPanel>";
	}

	public void savePV(long ts, String device_id, double production) {
		try {
		

			ResourceBundle PropBD = ResourceBundle.getBundle("BD");
			String DB = PropBD.getString("bd");
			String COLLECTION = PropBD.getString("COLLECTION");

			MongoClient mongoClient = new MongoClient(new ServerAddress(
					"127.0.0.1"));
			MongoDatabase db = mongoClient.getDatabase(DB);
			MongoCollection<Document> Collection = db.getCollection(COLLECTION);
			Collection.insertOne(new Document().append("ts", ts)
					.append("device_id", device_id)
					.append("production", production));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
