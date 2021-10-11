/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tf_idf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author sunny
 */
public class TF_IDF {

	/**
	 * @param args
	 *            the command line arguments
	 */
	private Set<String> terms = new HashSet<>();
	private Map<String, Double> idf = new HashMap<>();

	public static void main(String[] args) {

		ReadFile readFile = new ReadFile();
		String totalNewsArray[] = null;

		// totalNewsArray[0] = "The game of life is a game of evarlasting
		// learning";
		// totalNewsArray[1] = "The unexamined life is not worth living";
		// totalNewsArray[2] = "Never stop learning";
		// totalNewsArray[3] = "life learning";

		totalNewsArray = readFile.readNewsFromFile();

		if (totalNewsArray != null) {

			String eachNewsDetails[];

			int count = 0;

			TF_IDF tf_idf = new TF_IDF();

			List<Document> documentList = new ArrayList<>();

			for (int i = 0; i < totalNewsArray.length; i++) {

				count++;

				Document document = new Document();

				document.setDoc_id(i + 1);

				Map<String, Double> Tf = tf_idf.calculateTf(totalNewsArray[i]);

				double total = 0;

				for (Map.Entry<String, Double> entry : Tf.entrySet()) {

					String key = entry.getKey().toString();
					Double value = entry.getValue();
					total += value;
					// System.out.println("key, " + key + " value " + value);
				}

				document.setTotalTerms(total);

				for (Map.Entry<String, Double> entry : Tf.entrySet()) {

					String key = entry.getKey().toString();
					Double value = entry.getValue();

					Tf.put(key, (value / total));

				}

				document.setTf(Tf);
				documentList.add(document);

				//
				// try{
				//
				// FileWriter fw = new
				// FileWriter("/Users/sunny/desktop/finaldataset/testing2.txt",true);
				//
				// BufferedWriter br = new BufferedWriter(fw);
				// fw.append("\n\n");
				// fw.append(news.toString());
				// fw.append("\n\n");
				//
				// br.close();
				//
				// }catch(Exception e){
				// e.printStackTrace();
				// }

			}

			tf_idf.calculateIdf(documentList);

			tf_idf.calculateTfIdf(documentList);

			tf_idf.calculateCosine(documentList);

			System.out.println("total number of news::: " + count);

		}

	}

	public Map<String, Double> calculateTf(String document) {

		Map<String, Double> Tf = new HashMap<>();

		String[] tokenizedTerms = document.replaceAll("[-।,:()]", "").split("\\s+");

		// System.out.println("tokenized terms: " + tokenizedTerms);

		for (String term : tokenizedTerms) {

			Stemmer stemmer = new Stemmer();
			String stemmedWord = stemmer.stem(term.trim().toString());
			// System.out.println(stemmedWord);

			term = stemmedWord;

			if (term.length() != 0) {
				
				if (!Tf.containsKey(term.trim())) {

					Tf.put(term, 1.0);

				} else {

					Tf.put(term, (Tf.get(term) + 1));

				}
			}

			this.terms.add(term.toString());
		}

		return Tf;
	}

	public void calculateIdf(List<Document> documentList) {

		for (String s : terms) {

			double numberOfDocumentsWithThisTerm = 0;

			for (Document document : documentList) {

				if (document.getTf().containsKey(s)) {
					numberOfDocumentsWithThisTerm += 1.0;
				}
			}

			if (numberOfDocumentsWithThisTerm > 0) {

				Integer obj = new Integer(documentList.size());

				double d = obj.doubleValue();
				double idf_term = 1.0 + Math.log(d / numberOfDocumentsWithThisTerm);

				this.idf.put(s, idf_term);

			} else {
				this.idf.put(s, 1.0);
			}

		}

		// System.err.println("idf is: ");
		// for (Map.Entry<String, Double> entry : this.idf.entrySet()) {
		//
		// String key = entry.getKey().toString();
		// Double value = entry.getValue();
		// System.out.println(key + " :::: " + value);
		// }

	}

	public void calculateTfIdf(List<Document> documentList) {

		for (Document document : documentList) {

			Map<String, Double> tfIdfMap = new HashMap<>();

			for (String s : terms) {

				if (document.getTf().containsKey(s)) {
					double tfidf = document.getTf().get(s) * this.idf.get(s);
					tfIdfMap.put(s, tfidf);
				} else {
					tfIdfMap.put(s, 0.0);
				}

			}

			document.setTfIdf(tfIdfMap);
		}

	}

	public void calculateCosine(List<Document> documentList) {

		for (Document document1 : documentList) {

			for (Document document2 : documentList) {

				System.out.println(" between " + document1.getDoc_id() + " and " + document2.getDoc_id() + " = "
						+ new CosineSimilarity().cosineSimilarity(document1.getTfIdf(), document2.getTfIdf()));

				try {

					FileWriter fw = new FileWriter("/Users/sunny/desktop/test2.txt", true);

					BufferedWriter br = new BufferedWriter(fw);
					fw.append("\n\n");
					fw.append(" between " + document1.getDoc_id() + " and " + document2.getDoc_id() + " = "
							+ new CosineSimilarity().cosineSimilarity(document1.getTfIdf(), document2.getTfIdf()));
					fw.append("\n");

					br.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
