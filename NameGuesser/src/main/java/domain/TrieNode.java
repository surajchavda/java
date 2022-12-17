package domain;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	char c;
	Map<Character, TrieNode> children = null;
	String word = null;
	
	public TrieNode() {
		this.c = '#';
		this.children = new HashMap<>();
	}

	public TrieNode(char c) {
		this.c = c;
		this.children = new HashMap<>();
	}
}
