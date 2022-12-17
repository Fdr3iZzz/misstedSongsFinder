package com.Franz3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static String pathInput = "E:\\Music\\missedSongs\\Musik input";
    public static String pathOutput = "E:\\Music\\missedSongs\\Musik output";
    public static String splitter = "\\."; // character in song name after the number
// might have to set the songPath when where we get the song names

    public static void main(String[] args) {
        // create folder for output
        new File(pathOutput).mkdirs();
        // get playlist names
        File inputFile = new File(pathInput);
        String[] playlistNames = inputFile.list();
        // create txt files
        for(String playlistName : playlistNames ){
            File playlistFiles = new File(pathOutput + "\\" + playlistName + ".txt");
            try{
                playlistFiles.createNewFile();
            }catch (IOException e){
                System.out.println("Failed to create playlist .txt files");
            }
        }
        for (String playlistName : playlistNames){
            getSongTitle(playlistName);
        }
    }

    //get file name
    static void getSongTitle (String playlistName){
        // get song names
        File songsFile = new File(pathInput + "\\" + playlistName);
        String[] songsFileList = songsFile.list();
        ArrayList<String> songNames = new ArrayList<>();
        if (songsFileList != null){
            songNames = new ArrayList<String>(Arrays.asList(songsFileList));
        }else System.out.println("Dir has no songs");
        System.out.println(songNames);
        // split Strings
        ArrayList<String> songNamesFormatted = new ArrayList<String>();
        for (String songName : songNames){
            String[] substrings = songName.split(splitter,0);
            songNamesFormatted.add(substrings[0]);
        }
        System.out.println(songNamesFormatted);
        //convert String arraylist to int arraylist
        ArrayList<Integer> songNumbers = new ArrayList<>();
        try{
            for (String formattedSong : songNamesFormatted){
                songNumbers.add(Integer.parseInt(formattedSong));
            }
        }catch (NumberFormatException e) {
            System.out.println("songNamesFormatted consisted not only out of Integers");
        }
        System.out.println(songNumbers);
        // sort Integers
        Collections.sort(songNumbers);
        System.out.println(songNumbers);
        // find missing
        ArrayList<Integer> missingNumbers = new ArrayList<>();
            for (int i = 1; i<=songNumbers.get(songNumbers.size()-1); i++){
                boolean isPresent = false;
                for (Integer songNumber : songNumbers) {
                    if (songNumber == i) {
                        isPresent = true;
                        break;
                    }
                }
                if (!isPresent) {
                    missingNumbers.add(i);
                }
            }
        System.out.println(missingNumbers);
        // write to file
        writeFile(playlistName, "Checked until " + songNumbers.get(songNumbers.size()-1) + "\n");
        for (int number : missingNumbers){
            writeFile(playlistName, number + "\n");
        }
    }


    // write to file
    static void writeFile(String fileName, String fileInput) {
        try {
            FileWriter fileWriter = new FileWriter(pathOutput + "\\" + fileName + ".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(fileInput);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("file writing error");
        }
    }
}