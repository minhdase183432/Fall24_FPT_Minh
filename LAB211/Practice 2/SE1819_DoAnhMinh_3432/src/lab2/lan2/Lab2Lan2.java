/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.lan2;

import Management.RAMManagementSystem;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Lab2Lan2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        RAMManagementSystem managementSystem = new RAMManagementSystem();
        Menu menu = new Menu(managementSystem); // Pass management system to menu
        menu.showMenu(); // Call the showMenu method
   

  }}
