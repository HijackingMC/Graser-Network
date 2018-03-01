package com.grasernetwork.common.database;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class Mongo
{
	private MongoClient _mongoClient;
	private MongoDatabase _mongoDatabase;
	
	public Mongo(String database)
	{
		_mongoClient = new MongoClient();
		_mongoDatabase = _mongoClient.getDatabase(database);
	}
	
	public MongoClient getMongoClient()
	{
		return _mongoClient;
	}
	
	public MongoDatabase getMongoDatabase()
	{
		return _mongoDatabase;
	}
	
	public Document findDocumentById(String id)
	{
		FindIterable<Document> docs = _mongoDatabase.getCollection("users").find(new Document("_id", id));
		return docs.first();
	}
}
