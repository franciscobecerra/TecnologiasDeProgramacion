/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Contact;

/**
 *
 * @author macbook
 */
public class ContactsController {

    static ArrayList<Contact> List = new ArrayList<Contact>();
    static String uri = "./src/data/contacts.txt";

    public ContactsController() {
        read();
    }

    public ArrayList<Contact> getList() {
        return List;
    }

    public void setList(ArrayList<Contact> List) {
        this.List = List;
        write(List);
    }

    static void read() {
        File file = new File(uri);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {

                String[] array = st.split(",");
                List.add(new Contact(array[0], array[1], array[2], array[3]));
            }
            System.out.println("read Successfull " + List.size() + " file(s) added");
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void write(ArrayList<Contact> List) {
        try {
            FileWriter file = new FileWriter( uri );
            
            for (Contact contact : List) {
                file.write( contact.toString(null) + "\n" );
            }
            
            System.out.println("Successfully wrote to the file: " +uri);
            file.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        read();
        
        for (Contact contact : List) {
            System.out.println( contact.toString(null) );
        }
    }
}
