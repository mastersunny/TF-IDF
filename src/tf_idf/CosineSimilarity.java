package tf_idf;

import java.util.Map;

public class CosineSimilarity {
	
	public double cosineSimilarity(Map<String,Double> tfIdf1, Map<String,Double> tfIdf2){
		
		double dotProduct = 0.0;
		double magnitude1 = 0.0;
		double magnitude2 = 0.0;
		double cosineSimilarity = 0.0;
		
		for (Map.Entry<String, Double> entry : tfIdf1.entrySet()) {

			dotProduct += tfIdf1.get(entry.getKey()) * tfIdf2.get(entry.getKey());
			
			magnitude1 += Math.pow(tfIdf1.get(entry.getKey()),2);
			magnitude2 += Math.pow(tfIdf2.get(entry.getKey()),2);
			
			
		}
		
		magnitude1 = Math.sqrt(magnitude1);
		magnitude2 = Math.sqrt(magnitude2);
		
		if(magnitude1 != 0.0 && magnitude2 != 0.0){
			cosineSimilarity = dotProduct/(magnitude1*magnitude2);
		}
		else{
			return 0.0;
		}
		
		return cosineSimilarity;
	}

}
