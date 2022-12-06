package com.Franz3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static String pathInput = "E:\\Music\\missedSongs\\Musik input";
    public static String pathOutput = "E:\\Music\\missedSongs\\Musik output";
    public static String input = "01. xyz";

    public static void main(String[] args) {

        /*get playlist names
        * create files for playlists
        * get file name
        * sort file names
        * count up and find missing
        * write to file*/

        // create folder for output
        new File(pathOutput).mkdirs();
        // get playlist names for naming the txt and creating files
        ArrayList<String> playlistNames = new ArrayList<>();
        File playlistDir = new File(pathInput);
        File[] playlists = playlistDir.listFiles();
        String[] playlistNameArray = new String[0];
        if (playlists != null) {
            for (File file : playlists) {
                if (file.isDirectory()) {
                    playlistNames.add(file.getName());
                    // create File for output
                    try {
                        File playlistTxt = new File(pathOutput + "\\" + file.getName() + ".txt");
                        playlistTxt.createNewFile();
                    } catch (IOException e){
                        System.out.println("file creation error");
                    }
                }
            }
            playlistNameArray = playlistNames.toArray(new String[0]);
        }else {
            System.out.println("there are no playlists");
        }


        // get file names for naming the txt
        File songDir = new File(pathInput + "\\" + playlistNameArray[0] + "\\download\\Temp\\" + playlistNameArray[0]);
        System.out.println(songDir.getPath());
        File[] songFiles = songDir.listFiles();
        //split Strings and sort them
        if (songFiles != null){
            Arrays.sort(songFiles, (a, b) -> {
                int aInt = Integer.parseInt(a.getName().split("\\.")[0]);
                int bInt = Integer.parseInt(b.getName().split("\\.")[0]);

                // Compare the numeric parts of the file names
                return Integer.compare(aInt, bInt);
            });
        }
        System.out.println(playlists.length);
        for (int i =0; i < playlists.length; i++){
            System.out.println(i);
        }
    }


     static void createFile(String fileName){
         // create folder for output
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
