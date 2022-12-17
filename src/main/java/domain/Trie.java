package domain;

import java.util.Set;

public class Trie {
	TrieNode root = null;
	
	public Trie(Set<String> words) {
		this.root = new TrieNode();
		addWords(words);
	}
	
	private void addWord(String word) {
		TrieNode node = this.root;
		for (char c : word.toCharArray()) {
			node.children.putIfAbsent(c, new TrieNode(c));
			node = node.children.get(c);
		}
		node.word = word;
	}
	
	private void addWords(Set<String> words) {
		for (String word : words) {
			addWord(word);
		}
	}
	
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
