/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import agendacontactos.AgendaContactos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import models.Contact;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author macbook
 */
public class MainController implements Initializable {

    static ContactsController load = new ContactsController();
    static ArrayList<Contact> listContacts;
    static String searchString;
    Alert alert = new Alert(AlertType.NONE);

    @FXML
    private Label LabelEmailFail;

    @FXML
    private Label LabelPhoneFail;

    @FXML
    private Label LabelUriImageFail;

    @FXML
    private Label Title;

    @FXML
    private ListView<String> ListContact;

    @FXML
    private Pane btnAdd;

    @FXML
    private Pane btnCancel;

    @FXML
    private Pane btnDelete;

    @FXML
    private Pane btnSave;

    @FXML
    private Pane btnUpdate;

    @FXML
    private TextField email;

    @FXML
    private ImageView image;

    @FXML
    private Label labelNameFail;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField search;

    @FXML
    private TextField uriImage;

    @FXML
    void btnAddAction(MouseEvent event) {
        block("NEW-CONTACT");
    }

    @FXML
    void btnCancelAction(MouseEvent event) {
        block("CLEAN");
        ListContact.getSelectionModel().select(-1);
    }

    @FXML
    void btnDeleteAction(MouseEvent event) {
        int pointer = -1;
        for( int x = 0; x < listContacts.size(); x++ ){
            Contact aux = listContacts.get(x);
            if( aux.getName() == Title.getText() ){
                pointer = x;
            }
        }
        
        if( pointer != -1 ){
            listContacts.remove(pointer);
            
            setData( listContacts );
            getData();
            block("CONTACT-NOT-SELECT");
            ListContact.getSelectionModel().select(-1);
        } 
    }

    @FXML
    void btnSaveAction(MouseEvent event) {
        if( validField() ){
            
            Contact aux = new Contact( name.getText(), phone.getText(), email.getText(), uriImage.getText());
            listContacts.add( aux );
            
            setData( listContacts );
            getData();
            block("CLEAN");
        }
    }

    @FXML
    void btnUpdateAction(MouseEvent event) {
        
        int pointer = -1;
        for( int x = 0; x < listContacts.size(); x++ ){
            Contact aux = listContacts.get(x);
            if( aux.getName() == Title.getText() ){
                pointer = x;
            }
        }
        
        if( pointer != -1 ){
            listContacts.set(pointer, new Contact( name.getText(), phone.getText(), email.getText(), uriImage.getText()));
            
            setData( listContacts );
            getData();
            block("CLEAN");
        }
    }

    @FXML
    void ListContactAction(MouseEvent event) {
        Contact contact = find(ListContact.getSelectionModel().getSelectedItem());

        if (contact != null) {
            
            name.setText(contact.getName());
            email.setText(contact.getEmail());
            phone.setText(contact.getPhone());
            uriImage.setText(contact.getUriImage());

            image.setImage(new Image(contact.getUriImage()));
            Title.setText(contact.getName());

            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            
            block("CONTACT-SELECT");
        }
    }

    boolean validField() {
        if (name.getText().equals("")) {
            labelNameFail.setText("Fail");
            return false;
        }
        
        if (phone.getText().equals("")) {
            LabelPhoneFail.setText("Fail");
            return false;
        }
        
        if (email.getText().equals("")) {
            LabelEmailFail.setText("Fail");
            return false;
        }
        
        
        if (uriImage.getText().equals("")) {
            LabelUriImageFail.setText("Fail");
            return false;
        }
        return true;
    }

    void block(String option) {

        if (option == "CONTACT-SELECT") {
            name.setDisable(false);
            phone.setDisable(false);
            email.setDisable(false);
            uriImage.setDisable(false);

            btnSave.setDisable(true);
            btnCancel.setDisable(false);
        }

        if (option == "CONTACT-NOT-SELECT") {
            name.setDisable(true);
            phone.setDisable(true);
            email.setDisable(true);
            uriImage.setDisable(true);

            btnSave.setDisable(true);
            btnCancel.setDisable(true);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            name.setText("");
            phone.setText("");
            email.setText("");
            uriImage.setText("");
            
            labelNameFail.setText("");
            LabelEmailFail.setText("");
            LabelPhoneFail.setText("");
            LabelUriImageFail.setText("");

            Title.setText("");
            image.setImage(new Image("file: src/assets/user-disabled.png"));

        }

        if (option == "NEW-CONTACT") {

            name.setDisable(false);
            phone.setDisable(false);
            email.setDisable(false);
            uriImage.setDisable(false);

            btnSave.setDisable(false);
            btnCancel.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            name.setText("");
            phone.setText("");
            email.setText("");
            uriImage.setText("https://picsum.photos/200");
            
            labelNameFail.setText("");
            LabelEmailFail.setText("");
            LabelPhoneFail.setText("");
            LabelUriImageFail.setText("");

            Title.setText("");
            image.setImage(new Image("file: src/assets/user-disabled.png"));
        }

        if (option == "CLEAN") {

            name.setText("");
            phone.setText("");
            email.setText("");
            uriImage.setText("");

            name.setDisable(true);
            phone.setDisable(true);
            email.setDisable(true);
            uriImage.setDisable(true);

            btnSave.setDisable(true);
            btnCancel.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            
            labelNameFail.setText("");
            LabelEmailFail.setText("");
            LabelPhoneFail.setText("");
            LabelUriImageFail.setText("");

            Title.setText("");
            image.setImage(new Image("file: src/assets/user-disabled.png"));
        }

    }

    Contact find(String name) {
        if( name == null ) return null;
        
        for (Contact contact : listContacts) {
            if (name.equals(contact.getName())) {
                return contact;
            }
        }
        return null;
    }
    
    void getData(){
        ListContact.getItems().clear();
        listContacts = load.getList();
        for (Contact contact : listContacts) {
            ListContact.getItems().add(contact.getName());
        }
    }
    
    void setData( ArrayList<Contact> array ){
        load.setList( array );
        
        alert.setAlertType(AlertType.INFORMATION);
        alert.setContentText("Operacion Exitosa");
        alert.show();
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        getData();
        
        search.textProperty().addListener((observable, oldValue, newValue) -> {            
//            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            ListContact.getItems().clear();
            block("CLEAN");
            
            if (!newValue.equals("")) {                
                for (Contact contact : listContacts) {
                    if( contact.getName().contains( newValue ) ){
                        ListContact.getItems().add(contact.getName());
                    }
                }
            }else{
                for (Contact contact : listContacts) {
                    ListContact.getItems().add(contact.getName());
                }
            }
        });

    }
}
