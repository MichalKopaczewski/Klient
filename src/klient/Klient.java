package klient;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Klient {
    public static void main(String[] args) {
        Socket gniazdoKlienta = null;
        InputStream odczytZSerwera = null;
        BufferedReader odczyt = null;
        while (true) {
            try {
                gniazdoKlienta = new Socket("192.168.200.102", 9999);
            } catch (UnknownHostException e) {
                System.out.println("Nieznana nazwa hosta");
            } catch (IOException ex) {
                System.out.println("Nie udalo sie utworzyc gniazda klienta");
                Logger.getLogger(Klient.class.getName()).log(Level.SEVERE, null, ex);
            } 
            try {
                odczytZSerwera = gniazdoKlienta.getInputStream();
                if (odczytZSerwera != null) {
                    odczyt = new BufferedReader(new InputStreamReader(odczytZSerwera));
                    String tekst = odczyt.readLine();
                    System.out.println(tekst);
                    odczyt.close();
                }
                odczytZSerwera.close();
            } catch (IOException ex) {
                System.out.println("Nie mozna pobrac strumienia wejscia");
                Logger.getLogger(Klient.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                gniazdoKlienta.close();
            } catch (IOException ex) {
                Logger.getLogger(Klient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
