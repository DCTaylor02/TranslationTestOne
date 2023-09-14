package me.nutt;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileWriter {

    public static void WriteToFile(String fileName, List<String> lines){
        try{
            String newName = FileManager.createFile(fileName);
            FileOutputStream outputStream = new FileOutputStream("Output/"+newName);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (String line : lines){
                bufferedWriter.append(line);
            }
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
