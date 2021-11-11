/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author macbook
 */
public class Contact {
    
    private String name;
    private String phone;
    private String email;
    private String uriImage;

    public Contact(String name, String phone, String email, String uriImage) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.uriImage = uriImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public String toString( String option ) {
        if( option == "print" )
            return "Contact{" + "name=" + name + ", phone=" + phone + ", email=" + email + ", uriImage=" + uriImage + '}';
        
        return name + "," + phone + "," + email + "," + uriImage;
    }
}
