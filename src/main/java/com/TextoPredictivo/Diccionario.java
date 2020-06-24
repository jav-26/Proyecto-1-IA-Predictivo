package com.TextoPredictivo;

/**
 * Clase diccionario nos ayudará con la lectura de las palabras almacenadas en documentos XML
 * dentro de la carpeta "palabrasDiccionarios"
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
