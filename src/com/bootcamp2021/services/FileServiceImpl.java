package com.bootcamp2021.services;

import com.bootcamp2021.interfaces.FileService;
import com.bootcamp2021.interfaces.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {

    // it holds user data in String form when data is written in to the file or read from the file
    private final List<String> dataBuffer = new ArrayList<>();

    /**
     * Reads file into an ArrayList
     */
    public void readFile(){
        // Store file path
        File file = new File("data/users.txt");

        // It uses try-with-resources where the resource "BufferedReader" is automatically closed once finished (normally or abruptly),
        // hence no need for finally block.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String userData;
            while ((userData = br.readLine()) != null){
                // For you :) (code reviewer)
                System.out.println(userData);
                this.dataBuffer.add(userData);
            }

        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param data - data to write into the file
     */
    public void writeFile(List<User> data){

        // sets user data in the dataBuffer in fileService
        // prepares data for writing
        setDataBuffer(data);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.txt",false))){
            for(String userData:this.dataBuffer){
                writer.write(userData);
                writer.newLine();
            }
        }catch (Exception e){
            System.out.println("Something went wrong during file writing!");
        }
    }

    /**
     * Converts a list of user objects into a List of String
     * @param userDataList - a list of User objects
     */
    public void setDataBuffer(List<User> userDataList){
        for(User data:userDataList){
            this.dataBuffer.add(data.toString());
        }
    }


    public void resetDataBuffer(){
        this.dataBuffer.clear();
    }

    public List<String> getDataBuffer() {
        return this.dataBuffer;
    }
}
