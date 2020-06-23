package com.TextoPredictivo;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class Nodos {
    char letra;
    String subpalabra;
    Nodos padre;
    int nivel;
    int peso;
    int [] ListaDePesos = null;
    String[] ListaDePalabras = null;
    public List<Nodos> hijos= new ArrayList<Nodos>();
    boolean es_solucion;
    Collator comparador = Collator.getInstance();

    Nodos(){

    }
    Nodos(char letra, int peso, int nivel, Nodos padre, int[] pesos, String[] palabras){
        this.letra = letra;
        this.peso = peso;
        this.nivel = nivel;
        this.padre = padre;
        this.subpalabra = padre.subpalabra + letra;
        this.ListaDePesos = pesos;
        this.ListaDePalabras = palabras;
    }
    Nodos(String raiz,int[] pesos, String[] palabras){

        this.nivel = 0;
        this.peso = 0;
        this.subpalabra = raiz;
        this.padre = null;
        this.ListaDePesos = pesos;
        this.ListaDePalabras = palabras;
        comparador.setStrength(Collator.PRIMARY);
        for(int x=0; x < ListaDePalabras.length; x++)
        {
            if(comparador.compare(subpalabra, ListaDePalabras[x])==0){
                peso = ListaDePesos[x];
            }
        }
    }





    public char[] GenerarHijos(Nodos Padre){
        //comparador de textos que no discrimina acentos ni mayúzculas o minúsculas
        // PRIMARY:   Hóla = hola
        // SECONDARY: Hola = hola
        // TERTIARY:  hola = hola

        // comparador.compare("Hóla", "hola");

        String Raiz = Padre.subpalabra;
        String letras="";
        //recorre todas las palabras de la lista
        for(int i=0; i < ListaDePalabras.length; i++){
            comparador.setStrength(Collator.PRIMARY);
            String palabra = ListaDePalabras[i];
            String palabratemporal;//si la palabra raiz es menor a la palabra comparada de la lista
            if(palabra.length() > Raiz.length()){
                //se compara si empieza igual que la raiz
                palabratemporal = palabra.substring(0,Raiz.length());
                if(palabratemporal.equals(Raiz) || comparador.compare(palabratemporal,Raiz)==0){
                    //si es igual, se procede a agregar la siguiente letra a hijos
                    char sig_letra = palabra.charAt(Raiz.length());
                    //pero antes se se asegura de no crear un hijo repetido
                    if (letras.indexOf(sig_letra) == -1){ // check if a char already exist, if not exist then return -1
                        letras = letras+sig_letra;
                        int ElPeso= ListaDePesos[i];
                        //crear un nuevo nodo con la letra buscada y el peso de la lista
                        Nodos hijo = new Nodos(sig_letra, ElPeso,Padre.nivel + 1, Padre, ListaDePesos, ListaDePalabras);
                        hijos.add(hijo);
                    }
                    //Si el hijo está repetido suma del peso de ambos hijos
                    else if(!hijos.isEmpty()){
                        for(Nodos hijo : hijos){
                            if(hijo.subpalabra.equals(palabratemporal+sig_letra)){
                                hijo.peso += ListaDePesos[i];
                            }
                        }
                    }
                }
            }
            if(Raiz.length() == palabra.length()){
                comparador.setStrength(Collator.SECONDARY);
                if(Raiz.equals(palabra)|| comparador.compare(palabra,Raiz)==0){
                    //Son iguales, se encontró una solución
                    subpalabra = palabra;
                    es_solucion = true;
                }
            }

        }

        return letras.toCharArray();
    }
    public String getpalabra(){
        Nodos temporal = padre;
        String palabra = "";
        while(temporal!=null){
            palabra = palabra + temporal;
        }
        return palabra;
    }
    public String getpalabrasencillo(){
        return subpalabra;
    }
}
