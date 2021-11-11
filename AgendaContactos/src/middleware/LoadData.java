/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package middleware;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadData {
    
    public static ArrayList<String> read( ArrayList<String> DATA) {
        
        File file = new File( "./src/data/contacts.txt");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                DATA.add(st);
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DATA;
    }

    public static void write( String FILE_NAME, String[] DATA ) {     
        try {
            FileWriter file = new FileWriter( "./src/proyecto_final/data/" + FILE_NAME );

            for( String data : DATA ){
                file.write( data );
            }
            System.out.println( "Successfully wrote to the file: "+FILE_NAME );
            file.close();
            
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void print(ArrayList<String> DATA) {
        for (String x : DATA) {
            System.out.println(x);
        }
    }
    
    public static void main(String args[]){
        ArrayList<String> aux = new ArrayList<String>();
        read(aux);
        print(aux);
        
    }
}

