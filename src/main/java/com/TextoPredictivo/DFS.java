package com.TextoPredictivo;

import java.util.*;

public class DFS implements Dictionary {

    class TrieNode
    {
        Map<Character, TrieNode> children = new LinkedHashMap<Character, TrieNode>();

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;

        boolean isVisit;

        TrieNode(){
            isEndOfWord = false;
            isVisit = false;
        }
    };

    private TrieNode root;

    public TrieNode getRoot() {
        return root;
    }

    private DFS(){
        root = new TrieNode();
    };

    private static class SingletonHelper{
        private static final DFS INSTANCE = new DFS();
    }

    public static DFS getInstance(){
        return SingletonHelper.INSTANCE;
    }

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node


    // Returns true if key presents in trie, else false

    public boolean contains(String key) {
        int i;
        int length = key.length();
        char c;
        DFS.TrieNode pCrawl = root;

        for(i = 0; i < length; i++){
            c = key.charAt(i);

            if (!pCrawl.children.containsKey(c))
                return false;

            pCrawl = pCrawl.children.get(c);
        }

        return (pCrawl != null && pCrawl.isEndOfWord);
    }

    /**
     * Insertar las palabras del diccionario para hacer la búsqueda en DFS
     * @param key recibirá la palabra correspondiente a agregar para su posterior búsqueda.
     */
    public void add(String key) {
        int i;
        int length = key.length();
        char c;

        TrieNode pCrawl = root;

        for (i = 0; i < length; i++)
        {
            c = key.charAt(i);
            if (!pCrawl.children.containsKey(c))
                pCrawl.children.put(c, new TrieNode());

            pCrawl = pCrawl.children.get(c);
        }

        // marcar el último nodo como hoja
        pCrawl.isEndOfWord = true;
    }

    public List<String> query(String key)
    {
        List<String> result = new ArrayList<String>();
        int i;
        int length = key.length();
        char c;
        TrieNode pCrawl = root;

        for (i = 0; i < length; i++)
        {
            c = key.charAt(i);

            if (!pCrawl.children.containsKey(c))
                return result;

            pCrawl = pCrawl.children.get(c);
        }
        DFS(pCrawl, key, result);
        return result;
    }

    private void DFS(TrieNode pCrawl, String key, List<String> result){
        pCrawl.isVisit = true;
        if(pCrawl.isEndOfWord){
            result.add(key);
        }

        for(Character c : pCrawl.children.keySet())
        {
            if(!pCrawl.children.get(c).isVisit){
                key+=c;
                DFS(pCrawl.children.get(c), key, result);
                key = key.substring(0, key.length() - 1);
            }
        }
        pCrawl.isVisit = false;
    }
}


