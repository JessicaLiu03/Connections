package connections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Save{

    private AppController controller;

    public Save(AppController controller){
        this.controller=controller;
    }

    public Save(){
    }

    public List<List<String>> getFromFile(){
        List<List<String>> wordBank = new ArrayList<>();
        try{
        File wordFile = new File("src/main/java/connections/WordBank.txt");
        Scanner myReader = new Scanner(wordFile);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            wordBank.add(Arrays.asList(data.split(",")));
            }
        myReader.close();
        return wordBank;
        }
        catch (FileNotFoundException e){
            System.out.println("FAILED FILE READING");
            return wordBank;
        }
    }

    public void writeToFile(List<String> list){
        String toFile="";
        for(String word : list){
            toFile+=word+",";
        }
        System.out.println(toFile);
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("c:/Users/jessi/Objekt2024/prosjekt-base/src/main/java/connections/WordBank.txt",true));
            //myWriter = new FileWriter("c:/Users/jessi/Objekt2024/prosjekt-base/src/main/java/connections/WordBank.txt");
            writer.println(toFile);
            System.out.println("Written to file");
            writer.close();
            }
        catch(IOException e){
            controller.resetAdd(true);
            }
            
        }
    }