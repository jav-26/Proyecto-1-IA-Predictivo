package com.TextoPredictivo;

import java.util.*;

public class DFS implements InterDiccionario {
    /**
     * La clase Nodo dentro de la Clase Publica DFS nos ayuda a crear los nodos hijos para que
     * se puedan buscar todos los hijos del árbol.
     */
    class Nodo
    {
        Map<Character, Nodo> children = new LinkedHashMap<Character, Nodo>();

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;

        boolean isVisit;

        Nodo(){
            isEndOfWord = false;
            isVisit = false;
        }
    };

    private Nodo root;

    /**
     * getRoot nos devuelve la palabra o letra raiz.
     * @return la palabra o letra raiz del árbol generado.
     */
    public Nodo getRoot() {
        return root;
    }

    /**
     * Nos crea la raíz del árbol en dónde se empezará a buscar por DFS.
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

    /**
     * Nos permite determinar si existe la palabra que el usuario esta buscando.
     * Este método es para buscar una palabra determinada por el usuario escrita por completo.
     * @param key palabra que ingresa el usuario.
     * @return un valor true en caso de que existe y false en caso contrario.
     */
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

        Nodo pCrawl = root;

        for (i = 0; i < length; i++)
        {
            c = key.charAt(i);
            if (!pCrawl.children.containsKey(c))
                pCrawl.children.put(c, new Nodo());

            pCrawl = pCrawl.children.get(c);
        }

        // marcar el último nodo como hoja
        pCrawl.isEndOfWord = true;
    }

    /**
     * Una vez que el usuario comience a ingresar letras en el cuadro para ingresar las palabras
     * se llamará a esta funcion para obtener letra a letra e ir buscando dentro de la lista de palabras
     * por el método de búsqueda DFS.
     * @param key será la palabra que hasta el momento el usuario haya ingresado para ser buscada.
     * @return la lista que se haya encontrado con DFS hasta el momento con la escritura recibida.
     */
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

    /**
     * Este método se encargará de realizar la búsqueda en profundidad para encontrar las palabras
     * que coincidan con la palabra que haya ingresado el usuario.
     * @param hijo es un estado nuevo creado a partir de lo recibido por el usuario para determinar el
     *             camino que se debe recorrer.
     * @param key es la palabra ingresada por el usuario.
     * @param result es la lista con las palabras almacenadas y en la cual se realizará la búsqueda en profundidad.
     */
    private void DFS(Nodo hijo, String key, List<String> result){
        hijo.isVisit = true;
        if(hijo.isEndOfWord){
            result.add(key);
        }

        for(Character c : hijo.children.keySet())
        {
            if(!hijo.children.get(c).isVisit){
                key+=c;
                DFS(hijo.children.get(c), key, result);
                key = key.substring(0, key.length() - 1);
            }
        }
        hijo.isVisit = false;
    }
}