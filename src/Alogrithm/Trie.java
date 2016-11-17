package Alogrithm;

import java.util.ArrayList;
import java.util.List;

class TrieNode {
	public boolean isWord = false;
	public TrieNode[] children = new TrieNode[26];

	public TrieNode() {
	};
}

public class Trie {
	private TrieNode root = null;

	public Trie() {
		this.root = new TrieNode();
	}

	public void insert(String word) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (curr.children[c - 'a'] == null) {
				curr.children[c - 'a'] = new TrieNode();
			}
			curr = curr.children[c - 'a'];
		}
		curr.isWord = true;
	}

	public boolean search(String word) {
		TrieNode curr = root;
		for (char c : word.toCharArray()) {
			if (curr.children[c - 'a'] == null) {
				return false;
			}
			curr = curr.children[c - 'a'];
		}
		return curr.isWord;
	}

	public boolean startWith(String prefix) {
		TrieNode curr = root;
		for (char c : prefix.toCharArray()) {
			if (curr.children[c - 'a'] == null)
				return false;
			curr = curr.children[c - 'a'];
		}
		return true;
	}

	public List<String> prefixSearchWord(String word) {
		char c = word.charAt(0);
		int index = c - 'a';
		if (root.children != null && root.children[index] != null) {
			return depthSearch(root.children[index], new ArrayList<String>(),
					word.substring(1), "" + c, word);
		} else {
			return new ArrayList<String>();
		}
	}
	
	private List<String> depthSearch(TrieNode node, List<String> list,  
            String word, String matchedWord, String inputWord) {  
        if (node.isWord && word.length() == 0) {  
            list.add(matchedWord);  
        }  
        if (word.length() != 0) {  
            char c = word.charAt(0);  
            int index = c - 'a';  
            // 继续完全匹配,直到word为空串,否则未找到  
            if (node.children[index] != null) {  
                depthSearch(node.children[index], list, word.substring(1),  
                        matchedWord + c, inputWord);  
            }  
        } else {  
            if (!node.isWord) {// 若匹配单词结束,但是trie中的单词并没有完全找到,需继续找到trie中的单词结束.  
                // node.prefixCount>0表示trie中的单词还未结束  
                for (int i = 0; i < 26; i++) {  
                    if (node.children[i] != null) {  
                        int j = 'a' + i;  
                        char temp = (char) j;  
                        depthSearch(node.children[i], list, "", matchedWord  
                                + temp, inputWord);  
                    }  
                }  
            }  
        }  
        return list;  
    }  
	
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("abi");  
        trie.insert("ai");  
        trie.insert("aqi");  
        trie.insert("biiiyou");  
        trie.insert("dqdi");  
        trie.insert("ji");  
        trie.insert("li");  
        trie.insert("li");  
        trie.insert("li");  
        trie.insert("lipi");  
        trie.insert("qi");  
        trie.insert("qibi");  
        trie.insert("i");  
        trie.insert("ibiyzbi");
		System.out.println(trie.prefixSearchWord("li"));
	}
}
