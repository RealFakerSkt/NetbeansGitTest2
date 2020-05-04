package bildbearbeitermain;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Bildbearbeiter implements ActionListener {

    private JFrame fenster;
    private Bildflaeche bildflaeche;
    private JLabel dateinameLabel, statusLabel;
    private Farbbild aktuellesBild;
    private static final String VERSION = "Hier ist der Code geändert";
    private static final String VERSION2 = "Hier auch";

    public Bildbearbeiter() {
        erzeugeFenster();
        erzeugeMenuezeile(fenster);
    }
    
    private void zeigeInfo(){
        JOptionPane.showMessageDialog(fenster,"Und hier\n" + VERSION,
                "Hier auch",
                JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    private void speichern(){
        JOptionPane.showMessageDialog(fenster,"ACHTUNG!!!\n" + VERSION2,
                "ERROR 4992",
                JOptionPane.WARNING_MESSAGE);
        
    }

    private void erzeugeFenster() {
        fenster = new JFrame("Gernertastisch");
        Container contentPane = fenster.getContentPane();

        BorderLayout layout = new BorderLayout(6, 6);
        fenster.setLayout(layout);

        dateinameLabel = new JLabel();
        contentPane.add(dateinameLabel, BorderLayout.NORTH);

        bildflaeche = new Bildflaeche();
        contentPane.add(bildflaeche, BorderLayout.CENTER);

        statusLabel = new JLabel("Version 1.0");
        contentPane.add(statusLabel, BorderLayout.SOUTH);

        //JLabel label = new JLabel("Gerner i bang di so oag weg oida!");
        // contentPane.add(label);
        // JButton button = new JButton("Ich bin ein G drück auf mich draaaauuuuuuuffffff");
        // contentPane.add(button);
        // JButton button2 = new JButton("Ou Mann alter");
        // contentPane.add(button2);
        fenster.pack();
        fenster.setVisible(true);
    }

    private void erzeugeMenuezeile(JFrame fenster) {
        JMenuBar menuzeile = new JMenuBar();
        fenster.setJMenuBar(menuzeile);

        JMenu dateiMenu = new JMenu("Datei");
        menuzeile.add(dateiMenu);

        JMenu filter = new JMenu("Filter");
        menuzeile.add(filter);

        JMenu bitteHüfMa = new JMenu("Hilfe");
        menuzeile.add(bitteHüfMa);

        JMenuItem heOida = new JMenuItem("Info");
        heOida.addActionListener(this);
        bitteHüfMa.add(heOida);

        JMenuItem heller = new JMenuItem("Heller");
        heller.addActionListener(this);
        filter.add(heller);
        
        JMenuItem greyscale = new JMenuItem("Greyscale");
        greyscale.addActionListener(this);
        filter.add(greyscale);
        
        JMenuItem spiegeln = new JMenuItem("Spiegeln");
        spiegeln.addActionListener(this);
        filter.add(spiegeln);

        JMenuItem dunkler = new JMenuItem("Dunkler");
        dunkler.addActionListener(this);
        filter.add(dunkler);
        
        JMenuItem bw = new JMenuItem("Schwarzweiß");
        bw.addActionListener(this);
        filter.add(bw);
        
        JMenuItem negativ= new JMenuItem("Negativ");
        negativ.addActionListener(this);
        filter.add(negativ);
        
        JMenuItem weichzeichner= new JMenuItem("Weichzeichner");
        weichzeichner.addActionListener(this);
        filter.add(weichzeichner);
        
        JMenuItem negativ2= new JMenuItem("Solarisationsfilter");
        negativ2.addActionListener(this);
        filter.add(negativ2);
        
        JMenuItem kante= new JMenuItem("Kantenerkennung");
        kante.addActionListener(this);
        filter.add(kante);

        JMenuItem oeffnenEintrag = new JMenuItem("Öffnen");
        oeffnenEintrag.addActionListener(this);
        dateiMenu.add(oeffnenEintrag);

        JMenuItem speichern = new JMenuItem("Speichern");
        speichern.addActionListener(this);
        dateiMenu.add(speichern);

        JMenuItem beendenEintrag = new JMenuItem("Beenden");
        beendenEintrag.addActionListener(this);
        dateiMenu.add(beendenEintrag);

    }

    public void actionPerformed(ActionEvent event) {
        String Gerner = event.getActionCommand();
        if (Gerner.equals("Öffnen")) {
            oeffnen();
        }
        if (Gerner.equals("Speichern")) {
            speichern2();
        }
        if (Gerner.equals("Beenden")) {
            beenden();
        }
        if (Gerner.equals("Info")) {
            info();
        }
        if (Gerner.equals("Heller")) {
            heller();
        }
        if (Gerner.equals("Dunkler")) {
            dunkler();
        }
        if (Gerner.equals("Schwarzweiß")) {
            schwarzweiß();
        }
        if (Gerner.equals("Greyscale")){
            graustufe();
        }
        if (Gerner.equals("Spiegeln")){
            spiegeln();
        }
        if (Gerner.equals("Negativ")){
            negativieren();
        }
        if (Gerner.equals("Solarisationsfilter")){
            negativieren2();
        }
        if (Gerner.equals("Weichzeichner")){
            weichzeichnerr();
        }
        if (Gerner.equals("Kantenerkennung")){
            kante();
        }
    }

    private void oeffnen() {
        aktuellesBild = BilddateiManager.gibBild();
        if (aktuellesBild == null) {
            return;
        }
        bildflaeche.setzeBild(aktuellesBild);
        statusAnzeigen("Datei geladen,");
        fenster.pack();
    }
    
    private void spiegeln() {
            if (aktuellesBild != null) {
            aktuellesBild.spiegelnn();    
            fenster.repaint();
            System.out.println("Spiegeln");
            
            } else {
            System.out.println("Kein Bild!");
        }
    }
    
    private void kante() {
            if (aktuellesBild != null) {
            aktuellesBild.Kantenerkennung();    
            fenster.repaint();
            System.out.println("Kantenerkennung");
            
            } else {
            System.out.println("Kein Bild!");
        }
    }
    
    private void negativieren() {
            if (aktuellesBild != null) {
            aktuellesBild.negativ();    
            fenster.repaint();
            System.out.println("Negativ");
            
            } else {
            System.out.println("Kein Bild!");
        }
    }
    private void negativieren2() {
            if (aktuellesBild != null) {
            aktuellesBild.negativ2();    
            fenster.repaint();
            System.out.println("Solarisationsfilter");
            
            } else {
            System.out.println("Kein Bild!");
        }
    }

    private void statusAnzeigen(String meldung) {
        statusLabel.setText(meldung);
    }

    private void speichern2() {
        File datei = new File("GernerMain3.jpg");
        BilddateiManager.speichereBild(aktuellesBild, datei);
        speichern();
    }

    private void beenden() {
        System.out.println("Menüeintrag: Beenden");
        System.exit(0);
    }

    private void info() {
        zeigeInfo();
    }

    private void heller() {
        if (aktuellesBild != null) {
            aktuellesBild.aufhellen();
            fenster.repaint();
            System.out.println("Heller");
        } else {
            System.out.println("Kein Bild!");
        }
    }
    
    private void graustufe() {
        if (aktuellesBild != null) {
            aktuellesBild.greyscale();
           fenster.repaint();
            System.out.println("Graustufe");
        } else {
            System.out.println("Kein Bild!");
        }
    }

    private void dunkler() {
        if (aktuellesBild != null) {
            aktuellesBild.abdunkeln();
            fenster.repaint();
            System.out.println("Dunkler");
        } else {
            System.out.println("Kein Bild!");
        }
    }

    private void weichzeichnerr() {
        if (aktuellesBild != null) {
            aktuellesBild.weichzeichner();
            fenster.repaint();
            System.out.println("Weichzeichner");
        } else {
            System.out.println("Kein Bild!");
        }
    }
    
    private void schwarzweiß() {
        if (aktuellesBild != null) {
            aktuellesBild.sw();
            fenster.repaint();
            System.out.println("Schwarzweiß");
        } else {
            System.out.println("Kein Bild!");
        }
    }

}
