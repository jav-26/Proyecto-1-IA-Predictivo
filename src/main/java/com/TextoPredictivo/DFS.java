package com.TextoPredictivo;

import java.util.*;

public class DFS implements Dictionary {

    class Nodo
    {
        Map<Character, Nodo> children = new LinkedHashMap<Character, Nodo>();

        // Si finDePalabra es verdadero entonces el nodo representa
        // el fin de la palabra.

        /**
         *
         */
        boolean finDePalabra;

        boolean isVisit;

        Nodo(){
            finDePalabra = false;
            isVisit = false;
        }
    };

    private Nodo root;

    public Nodo getRoot() {
        return root;
    }

    /**
     * Constructor privado de la clase DFS para crear una raiz
     */
    private DFS(){
        root = new Nodo();
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
        Nodo pCrawl = root;

        for(i = 0; i < length; i++){
            c = key.charAt(i);

            if (!pCrawl.children.containsKey(c))
                return false;

            pCrawl = pCrawl.children.get(c);
        }

        return (pCrawl != null && pCrawl.finDePalabra);
    }

    /**
     * Insertar las palabras del diccionario para hacer la búsqueda en DFS
     * @param key recibirá la palabra correspondiente a agregar para su posterior búsqueda.
     */
    public void add(String key) {
        int i;
        int length = key.length();
        char c;

        Nodo pCrawl = root;

        for (i = 0; i < length; i++)
        {
            c = key.charAt(i);
            if (!pCrawl.children.containsKey(c))
                pCrawl.children.put(c, new Nodo());

            pCrawl = pCrawl.children.get(c);
        }

        // mark last node as leaf
        pCrawl.finDePalabra = true;
    }

    public List<String> query(String key)
    {
        List<String> result = new ArrayList<String>();
        int i;
        int length = key.length();
        char c;
        Nodo pCrawl = root;

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

    private void DFS(Nodo pCrawl, String key, List<String> result){
        pCrawl.isVisit = true;
        if(pCrawl.finDePalabra){
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


