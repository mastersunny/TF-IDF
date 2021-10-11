/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tf_idf;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sunny
 */
public class Document {

	private int doc_id;
	private Map<String, Double> Tf = new HashMap<>();
	private Map<String, Double> tfIdf = new HashMap<>();
	private Double totalTerms;

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public Map<String, Double> getTf() {
		return Tf;
	}

	public void setTf(Map<String, Double> tf) {
		Tf = tf;
	}

	public Map<String, Double> getTfIdf() {
		return tfIdf;
	}

	public void setTfIdf(Map<String, Double> tfIdf) {
		this.tfIdf = tfIdf;
	}

	public Double getTotalTerms() {
		return totalTerms;
	}

	public void setTotalTerms(Double totalTerms) {
		this.totalTerms = totalTerms;
	}

	@Override
	public String toString() {
		return "Document [doc_id=" + doc_id + ", Tf=" + Tf + ", tfIdf=" + tfIdf + ", totalTerms=" + totalTerms + "]";
	}

	

}
