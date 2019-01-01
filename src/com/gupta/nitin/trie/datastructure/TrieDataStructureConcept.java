package com.gupta.nitin.trie.datastructure;

import java.util.HashMap;

public class TrieDataStructureConcept {

	public static TrieNode root = new TrieNode((char) 0);

	static class TrieNode {
		private char value;
		private HashMap<Character, TrieNode> children;
		private boolean bIsEnd;

		public TrieNode(char ch) {
			value = ch;
			children = new HashMap<>();
			bIsEnd = false;
		}
	}

	// Method to insert a new word to Trie
	static public void insert(String word) {

		// Find length of the given word
		int length = word.length();
		TrieNode crawl = root;

		// Traverse through all characters of given word
		for (int level = 0; level < length; level++) {
			HashMap<Character, TrieNode> child = crawl.children;
			char ch = word.charAt(level);

			// If there is already a child for current character of given word
			if (child.containsKey(ch))
				crawl = child.get(ch);
			else // Else create a child
			{
				TrieNode temp = new TrieNode(ch);
				child.put(ch, temp);
				crawl = temp;
			}
		}

		// Set bIsEnd true for last character
		crawl.bIsEnd = true;
	}

	// The main method that finds out the longest string 'input'
	static public String getMatchingPrefix(String input) {
		String result = ""; // Initialize resultant string
		int length = input.length(); // Find length of the input string

		// Initialize reference to traverse through Trie
		TrieNode crawl = root;

		// Iterate through all characters of input string 'str' and traverse
		// down the Trie
		int level, prevMatch = 0;
		for (level = 0; level < length; level++) {
			// Find current character of str
			char ch = input.charAt(level);

			// HashMap of current Trie node to traverse down
			HashMap<Character, TrieNode> child = crawl.children;

			// See if there is a Trie edge for the current character
			if (child.containsKey(ch)) {
				result += ch; // Update result
				crawl = child.get(ch); // Update crawl to move down in Trie

				// If this is end of a word, then update prevMatch
				if (crawl.bIsEnd)
					prevMatch = level + 1;
			} else
				break;
		}

		// If the last processed character did not match end of a word,
		// return the previously matching prefix
		if (!crawl.bIsEnd)
			return result.substring(0, prevMatch);

		else
			return result;
	}

	static private boolean delete(TrieNode current, String word, int index) {
		if (index == word.length()) {
			if (!current.bIsEnd) {
				return false;
			}
			current.bIsEnd = false;
			return current.children.isEmpty();
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		if (node == null) {
			return false;
		}
		boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.bIsEnd;

		if (shouldDeleteCurrentNode) {
			current.children.remove(ch);
			return current.children.isEmpty();
		}
		return false;
	}

	public static void main(String[] args) {
		insert("are");
		insert("area");
		insert("base");
		insert("cat");
		insert("cater");
		insert("basement");

		String input = "caterer";
		System.out.print(input + ":   ");
		System.out.println(getMatchingPrefix(input));

		input = "basement";
		System.out.print(input + ":   ");
		System.out.println(getMatchingPrefix(input));

		input = "are";
		System.out.print(input + ":   ");
		System.out.println(getMatchingPrefix(input));

		input = "arex";
		System.out.print(input + ":   ");
		System.out.println(getMatchingPrefix(input));

		input = "basemexz";
		System.out.print(input + ":   ");
		System.out.println(getMatchingPrefix(input));

		input = "xyz";
		System.out.print(input + ":   ");
		System.out.println(getMatchingPrefix(input));

	}

}
