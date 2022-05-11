import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps = 50, sonCote = 50;

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run() {
        sonDebDessin = 0;
        // Orientation dans laquelle se déplace le mobile, à savoir 1 = droite et -1 =
        // gauche
        int orientation = 1;
        while (true) {
            if (sonDebDessin > saLargeur - sonPas || sonDebDessin < -sonPas) {
                orientation *= -1;
            }
            sonDebDessin += sonPas * orientation;
            repaint();
            try {
                Thread.sleep(sonTemps);
            } catch (InterruptedException telleExcp) {
                telleExcp.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics telCG) {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
    }
}
