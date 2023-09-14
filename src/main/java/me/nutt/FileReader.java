package me.nutt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileReader {

    public FileReader(){

    }

    public static List<String> getText(String fileName){
        try{
            FileInputStream inputStream = new FileInputStream("Input/"+fileName);
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1){
                stringBuilder.append((char) character);
            }
            reader.close();
            String string = stringBuilder.toString();
            String[] raw = string.split("(?=。)");
            List<String> lines = new ArrayList<>(List.of(raw));
            ListIterator<String> listIterator = lines.listIterator();
            List<String> finalLines = new ArrayList<>();
            while (listIterator.hasNext()){
                String next = listIterator.next();
                if (next.contains("」")){
                    String[] col = next.split("(?=」)");
                    List<String> list = new ArrayList<>(Arrays.stream(col).toList());
                    finalLines.addAll(list);
                } else {
                    finalLines.add(next);
                }
            }
            return finalLines;
        }catch (IOException e){
            return List.of();
        }
    }

    public static List<String> getBatchText(String fileName){
        try{
            FileInputStream inputStream = new FileInputStream("Input/" + fileName);
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                stringBuilder.append((char) character);
            }
            reader.close();
            String string = stringBuilder.toString();
            return Collections.singletonList(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
