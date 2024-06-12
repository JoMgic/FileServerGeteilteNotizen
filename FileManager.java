 

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Beschreiben Sie hier die Klasse FileWorker.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class FileManager
{
    private FileManager(){}
    public static boolean createFile(String filename){
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + filename);
                return true;
            }
            } catch (IOException e) { e.printStackTrace(); }
        return false;
    }
    public static void writeFile(String filename, String value){
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(value);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) { e.printStackTrace(); System.out.println("Error");}
    }
    public static void printFile(String filename){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }
    public static void deleteFile(String filename){
        File myObj = new File(filename); 
        if (myObj.delete()) { 
            System.out.println("Deleted the file: " + filename);
        } else {
            System.out.println("Failed to delete the file.");
        } 
    }
    public static ArrayList<String> readFile(String filename){
        ArrayList<String> arrlist = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                arrlist.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        return arrlist;
    }
    
    public static Iterator iteratorFileReader(String filename){
        return readFile(filename).iterator();
    }
    public static void appendFile(String filename, String value){
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            myWriter.write(value+"\n");
            myWriter.close();
            System.out.println("Successfully appended to file.");
        } catch (IOException e) { e.printStackTrace(); System.out.println("Error");}
    }
    public static void overrideFile(String filename, ArrayList<String> arrList){
        for(String s : arrList){
            appendFile(filename, s);
        }
    }
    public static String readFirstLine(String filename){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
                return myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        return "0";
    }
    public static void clearFile(String filename){
        try {
            FileWriter myWriter = new FileWriter(filename, false);
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) { e.printStackTrace(); System.out.println("Error");}
    }
}
