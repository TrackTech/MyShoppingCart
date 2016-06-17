package com.myservice.restful;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Path("/list")
public class Products {
	@GET
	@Produces("application/json")
	public static String GetProducts()
	{
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("products");
		
		MongoCollection<Document> col = db.getCollection("product");
		
		List<String> results = new ArrayList<String>();   
		
		MongoCursor<Document> cursor = col.find().iterator();
		try
		{		
			while(cursor.hasNext())
			{
				results.add(cursor.next().toJson());
			}
		}
		finally{
			cursor.close();
		}
		
		return results.toString(); 
				
	}
}
