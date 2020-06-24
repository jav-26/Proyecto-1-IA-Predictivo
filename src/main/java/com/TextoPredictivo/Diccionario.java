package com.TextoPredictivo;

/**
 * Clase
 */
public class Diccionario {
    private InterDiccionario interDiccionario;

    public void setInterDiccionario(InterDiccionario interDiccionario){
        this.interDiccionario = interDiccionario;
    }

    public boolean contains(String key){
        return interDiccionario.contains(key);
    }

    public void add(String key){
        interDiccionario.add(key);
    }
}
