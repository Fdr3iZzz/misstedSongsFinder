package com.Franz3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static String pathInput = "E:\\Music\\missedSongs\\Musik input";
    public static String input = "01. xyz";

    public static String pathOutput = "E:\\Music\\missedSongs\\Musik output";
    public static void main(String[] args) {
        //split Strings
        String formatedInput [] = input.split("\\.");
        System.out.println(formatedInput [0]);

    }

    // create folder for output
     static void createFile(String fileName){
         new File(pathOutput).mkdirs();
         // create File for output
         try {
             File file = new File(pathOutput + "\\" + fileName + ".txt");
             file.createNewFile();
         } catch (IOException e){
             System.out.println("file creation error");
         }

    }
    // write to file
    static void writeFile (String fileName, String fileInput){
        try {
            FileWriter writer = new FileWriter(pathOutput + "\\" + fileName + ".txt");
            writer.write(fileInput);
            writer.close();
        } catch (IOException e) {
            System.out.println("file writing error");
        }
    }

}
