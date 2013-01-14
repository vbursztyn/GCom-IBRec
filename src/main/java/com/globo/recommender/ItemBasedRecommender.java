package com.globo.recommender;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

class ItemBasedRecommender {
	
	private ItemBasedRecommender() {
	}
	
	public static void main(String[] args) throws Exception {
		GloboComDataModel model = new GloboComDataModel(new File("actions.csv"));
		ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
		GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);
		
		String item = "http://g1.globo.com/fantastico/noticia/2013/01/reverendo-conta-intimidades-da-vida-do-casal-matsunaga.html";
		long itemId = model.rememberItemMapping(item);
		List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemId, 3);
		
		System.out.println("Users who read - " + item + " - might want to read...");
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(model.reverseItemMapping(recommendation.getItemID()));
		}
	}
}
