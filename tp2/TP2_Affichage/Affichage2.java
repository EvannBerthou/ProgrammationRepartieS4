import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

/**
 * Affichage thread-safe à l'aide de notre propre implémentation d'un sempaphore
 */
public class Affichage2 extends Thread {
    static semaphoreBinaire semaphore = new semaphoreBinaire(1);
    String texte; 

    public Affichage2 (String txt){
        texte = txt;
    }

    public void run(){
        semaphore.syncWait();
        for (int i = 0; i < texte.length(); i++){
            System.out.print(texte.charAt(i));
            try {
                sleep(100);
            } catch(InterruptedException e) {};
        }
        semaphore.syncSignal();
    }
}
