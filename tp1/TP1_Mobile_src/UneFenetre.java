import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class UneFenetre extends JFrame {
    private final int LARG = 200, HAUT = 250;

    private final int N = 3;
    private boolean[] isRunning = new boolean[N];
    private Thread[] threads = new Thread[N];

    /** 
     * Créer un nouveau bouton qui changera l'état du mobile i
     */
    private JButton createSuspendButton(int i) {
        JButton btn = new JButton("Suspend");
        btn.addActionListener((e) -> {
            isRunning[i] = !isRunning[i];
            if (isRunning[i]) {
                threads[i].resume();
                btn.setText("Suspend");
            } else {
                btn.setText("Resume");
                threads[i].suspend();
            }
        });
        return btn;
    }

    public UneFenetre() {
        setLayout(new GridLayout(N + 1,2));

        for (int i = 0; i < N; i++) {
            UnMobile sonMobile = new UnMobile(LARG, HAUT / N);
            JButton btn = createSuspendButton(i);
            isRunning[i] = true;
            Thread thread = new Thread(sonMobile);
            threads[i] = thread;
            add(sonMobile);
            add(btn);
        }

        for (Thread t : threads) {
            t.start();
        }

        JButton suspendAll = new JButton("Suspend all");
        suspendAll.addActionListener((e) -> {
            for (Thread t : threads) {
                t.suspend();
            }
        });
        add(suspendAll);

        JButton resumeAll = new JButton("Resume all");
        resumeAll.addActionListener((e) -> {
            for (Thread t : threads) {
                t.resume();
            }
        });
        add(resumeAll);


        setSize(LARG, HAUT);
        setVisible(true);
    }
}
