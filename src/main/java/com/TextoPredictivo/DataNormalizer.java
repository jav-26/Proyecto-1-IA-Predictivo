package com.TextoPredictivo;

import java.util.*;
import java.util.regex.*;

/**
 * Esta clase nos permite (con ayuda de la clase DataReader) distinguir entre espacios y carácteres
 * no deseados para separar las palabras entre si en caso de que existe.
 */
public class DataNormalizer {

    public static Set<String> getListWord(String document){
        Set<String> listWord = new TreeSet<String>();

        Pattern pattern = Pattern.compile("<post>(.*?)</post>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(document);
        while (matcher.find()){
            listWord.addAll(DataNormalizer.splitWord(matcher.group(1).trim()));
        }
        return listWord;
    }

    public static Set<String> splitWord(String line){
        line = line.replaceAll("[!?,.:;`'~@*#$%&()|\"]", " ");
        String[] words = line.split("[\\s,.]+");
        return new LinkedHashSet<String>(Arrays.asList(words));
    }

    public static void addDataIntoApp(Set<String> listWord){

        for(String word : listWord){
            DFS.getInstance().add(word);
            AscensoALaColina.getInstance().add(word);
        }
    }
}