import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonCote = 50;
    int sonTemps;
    static semaphore sem = new semaphoreGeneral(3);
    boolean inCritique = false;

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
        sonTemps = 40 + (int)(Math.random() * 30);
    }

    /**
     * Réaliser des aller retour sur la largeur de la fenêtre
     * Un nombre limité de mobiles peuvent être présent sur la partie centrale de l'écran
     * Les autres restent bloqués jusqu'à qu'un autre mobile sorte.
     */
    public void run() {
        while (true) {
            sonDebDessin = 0;
            for (; sonDebDessin < saLargeur / 3; sonDebDessin += sonPas) {
                repaint();
                try { Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) { telleExcp.printStackTrace(); }
            }

            // Début de la partie centrale
            sem.syncWait();
            inCritique = true;
            for (; sonDebDessin < (saLargeur / 3) * 2; sonDebDessin += sonPas) {
                repaint();
                try { Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) { telleExcp.printStackTrace(); }
            }
            inCritique = false;
            sem.syncSignal();
            // Fin de la partie centrale

            for (; sonDebDessin < saLargeur; sonDebDessin += sonPas) {
                repaint();
                try { Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) { telleExcp.printStackTrace(); }
            }

            sonDebDessin = saLargeur;
            for (; sonDebDessin > (saLargeur / 3) * 2; sonDebDessin -= sonPas) {
                repaint();
                try { Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) { telleExcp.printStackTrace(); }
            }

            // Début de partie centrale 
            sem.syncWait();
            inCritique = true;
            for (; sonDebDessin > (saLargeur / 3); sonDebDessin -= sonPas) {
                repaint();
                try { Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) { telleExcp.printStackTrace(); }
            }
            inCritique = false;
            sem.syncSignal();
            // Fin de la partie centrale

            for (; sonDebDessin > 0; sonDebDessin -= sonPas) {
                repaint();
                try { Thread.sleep(sonTemps);
                } catch (InterruptedException telleExcp) { telleExcp.printStackTrace(); }
            }
        }
    }

    public void paintComponent(Graphics telCG) {
        super.paintComponent(telCG);
        // Change la couleur du mobile en fonction de s'il est au centre (rouge) ou non (noir)
        telCG.setColor(this.inCritique ? Color.RED : Color.BLACK);
        telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
    }
}
