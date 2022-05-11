import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

class Exclusion { }

/**
 * Affichage thread-safe à l'aide de la direction synchronized
 */
public class Affichage extends Thread {
    static Exclusion exclusion = new Exclusion();
    String texte; 

    public Affichage (String txt){
        texte = txt;
    }

    private static synchronized void afficher(String texte) {
        for (int i = 0; i < texte.length(); i++){
            System.out.print(texte.charAt(i));
            try {
                sleep(100);
            } catch(InterruptedException e) {};
        }
    }

    public void run(){
        Affichage.afficher(texte);
    }
}
