package com.TextoPredictivo;

import java.io.*;
import java.util.*;

public class DataReader {

    public static void read(String pathToFolder) throws FileNotFoundException {
        File folder = new File(pathToFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new FileNotFoundException();
        }
        File[] fileNames = folder.listFiles();

        BufferedReader reader;

        List<String> totalWord = new ArrayList<String>();

        for (File file : fileNames) {
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder document = new StringBuilder();

                while ((line = reader.readLine()) != null) {

                    document.append(line).append("\n");
                }

                Set<String> listWord = DataNormalizer.getListWord(document.toString());

                DataNormalizer.addDataIntoApp(listWord);
                totalWord.addAll(listWord);

                System.out.println("Palabras por archivo: " + listWord.size());

                document.setLength(0);

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total de palabras: " + totalWord.size());
    }
}

