package domain;

import java.util.Set;

/**
 * This class is used to define the modified version of standard Trie to return
 * String matching the pattern passed.
 * 
 * @author suraj
 *
 */
public class Trie {
	TrieNode root = null;

	/**
	 * Trie constructor
	 * 
	 * @param words Words for which Trie needs to be constructed
	 */
	public Trie(Set<String> words) {
		this.root = new TrieNode();
		addWords(words);
	}

	/**
	 * Adds word to the Trie
	 * 
	 * @param word
	 */
	private void addWord(String word) {
		TrieNode node = this.root;
		// Iterate through each character of the word
		for (char c : word.toCharArray()) {
			// If TrieNode does not exist, create the TrieNode
			node.children.putIfAbsent(c, new TrieNode(c));
			// Move node pointer to the newly created TrieNode
			node = node.children.get(c);
		}
		// Mark the end of word by saving the word string in the Trie Node
		node.word = word;
	}

	/**
	 * Add set of words to Trie
	 * 
	 * @param words Words to be added to the Trie
	 */
	private void addWords(Set<String> words) {
		// Iterate through all the words in the set and add them to Trie
		for (String word : words) {
			addWord(word.toLowerCase());
		}
	}

	/**
	 * This function adds all words matching the passed pattern to result set
	 * 
	 * @param pattern A string pattern of form *b*d where * can be any character
	 *                between a to z
	 * @param root    TrieNode where the search needs to begin
	 * @param idx     Index of the pattern under consideration
	 * @param result  The result set of words matching the pattern
	 */
	public void search(String pattern, TrieNode root, int idx, Set<String> result) {
		TrieNode node = root == null ? this.root : root;
		for (int i = idx; i < pattern.length(); i++) {
			char c = pattern.charAt(i);
			if (c == '*') {
				for (TrieNode child : node.children.values()) {
					search(pattern, child, i + 1, result);
				}
			}
			if (node.children.containsKey(c)) {
				node = node.children.get(c);
			} else {
				break;
			}
		}
		if (node.word != null && node.word.length() == pattern.length()) {
			result.add(node.word);
		}
	}
}
