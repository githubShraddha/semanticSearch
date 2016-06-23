package com.server;
import java.io.IOException;
import java.util.Arrays;

import rita.RiWordNet;

public class Synonyms {

//	public static void main(String[] args) throws IOException {
	public String[] main(String word) throws IOException {

		// Would pass in a PApplet normally, but we don't need to here
		RiWordNet wordnet = new RiWordNet("C:/Program Files (x86)/WordNet/2.1");

		// Get a random noun
//		String word = wordnet.getRandomWord("Java");
//		String word = "take";
		// Get max 15 synonyms
		String[] synonyms = wordnet.getAllSynonyms(word, "n", 15);

		System.out.println("Random noun: " + word);
		if (synonyms != null) {
			// Sort alphabetically
			Arrays.sort(synonyms);
//			for (int i = 0; i < synonyms.length; i++) {
//				System.out.println("\nSynonym " + i + ": " + synonyms[i] + "\n");
//			}
		} else {
			System.out.println("No synyonyms!");
		}
		return synonyms;
	}

}