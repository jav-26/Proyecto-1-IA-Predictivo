package com.TextoPredictivo;

import java.util.*;

public class AscensoALaColina implements Dictionary{
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

    private AscensoALaColina(){
        root = new Nodo();
    }

    private void BuscarAscensoColina(Nodos raiz)
    {
        Nodos nodoactual = raiz;
        do{
            //estadosrecorridos++;
            // System.out.println("\nEstado "+estadosrecorridos);
            nodoactual.GenerarHijos(nodoactual);
            if(nodoactual.es_solucion){
                encontrados.add(nodoactual);
                encontrado=true;
            }
            if(nodoactual.hijos.size()>0){
                // Se procederá a almacenar los hijos de mayor a menor
                int contadortemporal=0;
                Nodo []hijo = new Nodo[nodoactual.hijos.size()];
                //1. se almacena los hijos en una cadena temporal llamada hijo
                for(Nodo temporal : nodoactual.hijos){
                    hijo[contadortemporal]=temporal;
                    contadortemporal++;
                }
                //2. se procede a ordenar los hijos
                for(int i=0 ; i < nodoactual.hijos.size() ; i++){
                    for(int j=i ; j< nodoactual.hijos.size() ; j++){
                        if( hijo[j].peso <= hijo[i].peso) {
                            Nodo temporal = hijo[i];
                            hijo[i] = hijo[j];
                            hijo[j] = temporal;
                        }
                    }
                    //nodo actual será el hijo mayor y se elimina del arreglo "hijo"
                }
                try{
                    nodoactual = hijo[hijo.length-1];
                    hijo = Arrays.copyOf(hijo, hijo.length - 1);
                    for(int i = hijo.length-1; i >= -1; i--){
                        Nodo hijoactual = hijo[i];
                        cola.add(hijoactual);
                        //  System.out.println("Hijo: "+hijoactual.letra+" "+hijoactual.peso);
                    }
                }
                catch(Exception e){}

            }
            else if(!cola.isEmpty()){
                nodoactual = cola.remove();
            }
            else {
                break;
            }
        }while(encontrados.size()<20);
    }

    private static class SingletonHelper{
        private static final AscensoALaColina INSTANCE = new AscensoALaColina();
    }

    public static AscensoALaColina getInstance(){
        return AscensoALaColina.SingletonHelper.INSTANCE;
    }

    public boolean contains(String key){
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

    public void add(String key){
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
        BuscarAscensoColina(pCrawl, key, result);
        return result;
    }
}
