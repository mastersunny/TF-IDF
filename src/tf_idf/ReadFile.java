/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tf_idf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author sss
 */
public class ReadFile {

	public String[] readNewsFromFile() {

		try {

			File inputFile = new File("/Users/sunny/desktop/test.txt");

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));

			StringBuilder totalNews = new StringBuilder();
			String currentLine;

			while ((currentLine = in.readLine()) != null) {

				totalNews.append(currentLine.trim());
			}

			in.close();

			String totalNewsArray[] = null;

			totalNewsArray = totalNews.toString().split("#");

			return totalNewsArray;

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;

	}

}
