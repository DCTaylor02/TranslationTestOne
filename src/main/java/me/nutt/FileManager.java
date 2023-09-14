package me.nutt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    public FileManager(){

    }

    public static List<File> findInputFiles() {
        File dir = new File("Input");
        File[] files = dir.listFiles(pathname -> pathname.toString().contains(".txt"));
        if (files == null) {
            return List.of();
        }
        if (files.length == 0) {
            return List.of();
        }
        return Arrays.stream(files).toList();
    }

    public static List<String> getFileNames() {
        List<String> names = new ArrayList<>();
        for (File file : FileManager.findInputFiles()){
            names.add(file.getName());
        }
        return names;
    }

    public static String createFile(String fileName){
        boolean created = false;
        int count = 0;
        String num = "";
        while(!created){
            try{
                if (new File("Output/"+fileName.replace(".txt","")+num+".txt").createNewFile()){
                    created = true;
                }else {
                    count++;
                    num = count!=0 ? "("+count +")" : "";
                }
            }catch (IOException exception){
                exception.printStackTrace();
                break;
            }
        }
        return fileName.replace(".txt", "").concat(num).concat(".txt");
    }

    public static void mkdirs(){
        new File("Input").mkdir();
        new File("Output").mkdir();
    }

}
