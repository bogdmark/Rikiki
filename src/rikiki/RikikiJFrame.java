package rikiki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author Márk
 */
public class RikikiJFrame extends javax.swing.JFrame {

    /**
     * Creates new form RikikiJFrame
     */
    public String choice;
    public boolean start;
    public boolean exit;

    public RikikiJFrame() {
        initComponents();
        this.choice = "";
        this.start = false;
        this.exit = false;
        this.TopPanel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        helpScreen = new javax.swing.JDialog();
        helpPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        StartDialog = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        MainPanel = new javax.swing.JPanel();
        TopPanel = new javax.swing.JPanel();
        RoundLabel = new java.awt.Label();
        RoundNumber = new java.awt.Label();
        trumpLabel = new java.awt.Label();
        TrumpPic = new javax.swing.JLabel();
        TablePanel = new javax.swing.JPanel();
        PlayerPanel = new javax.swing.JPanel();
        ScorePanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        javax.swing.JMenu jMenu1 = new javax.swing.JMenu();
        Start = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        helpScreen.setBounds(new java.awt.Rectangle(0, 0, 300, 300));
        helpScreen.setMinimumSize(new java.awt.Dimension(400, 400));
        helpScreen.setResizable(false);

        helpPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Játékszabályok"));
        helpPanel.setAlignmentX(1.0F);
        helpPanel.setAlignmentY(1.0F);
        helpPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        helpPanel.setLayout(new java.awt.BorderLayout());
        String help1 = "<html>A rikiki egy a bridgehez hasonló kártyajáték. Legalább hárman játszák egy pakli franciakártyával. Mindenkinek osztanak ugyan annyi lapot és sorsolnak egy adu színt a játékosok. </br>"
        + "(A francia kártya színei: pikk ♠, kőr ♥, káró ♦ és treff ♣.) </br>"
        + "Az egyik játékost kiválasztják indulónak (emberek között az osztótól jobbra ülő), ő fog elsőnek hívni. Utána mindenki megtippeli, hogy hány ütése lesz. </br>"
        + "A hívó játékos kiválaszt egy kártyát és kirakja. Utána minden játékosnak sorban haladva rá kell raknia egy ugyan olyan színű lapot, vagy ha nincs neki olyan színű, akkor egy adut és ha az sincs akkor tetszőleges lapot. </br>"
        + "Az üt, akié a legnagyobb lap a hívott színben, vagy ha került bele adu, akkor az aki a legnagyobb adut rakta. Az ász a legnagyobb, utána a dáma, majd a bubi és utána a számos lapok következnek. Aki ütött az hív következőnek. </br>"
        + "Ha elfogytak a lapok mindenki megszámolja az ütéseit aki annyit ütött ahányat vállalt, az 10+2*n pontot kap, ahol n az ütéseinek száma. </br>Aki pedig a válalásától eltérő számút ütött az -2*|n-v| pontot kap, ahol n szintén az ütések száma és v a vállalások száma. Lehet nullát is vállalni.</html>";

        jLabel1.setText(help1);
        jLabel1.setAlignmentX(1.0F);
        jLabel1.setAlignmentY(1.0F);
        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        helpPanel.add(jLabel1, java.awt.BorderLayout.CENTER);

        helpScreen.getContentPane().add(helpPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout StartDialogLayout = new javax.swing.GroupLayout(StartDialog.getContentPane());
        StartDialog.getContentPane().setLayout(StartDialogLayout);
        StartDialogLayout.setHorizontalGroup(
            StartDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        StartDialogLayout.setVerticalGroup(
            StartDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(34, 177, 76));

        MainPanel.setBackground(new java.awt.Color(34, 177, 76));

        TopPanel.setBackground(new java.awt.Color(34, 177, 76));

        RoundLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        RoundLabel.setText("ROUND:");

        RoundNumber.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N

        trumpLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        trumpLabel.setText("TRUMP:");

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(RoundNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trumpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TrumpPic, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(302, Short.MAX_VALUE))
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TrumpPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trumpLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RoundNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RoundLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                .addContainerGap())
        );

        TablePanel.setBackground(new java.awt.Color(34, 177, 76));
        TablePanel.setMinimumSize(new java.awt.Dimension(827, 100));
        TablePanel.setPreferredSize(new java.awt.Dimension(827, 175));

        javax.swing.GroupLayout TablePanelLayout = new javax.swing.GroupLayout(TablePanel);
        TablePanel.setLayout(TablePanelLayout);
        TablePanelLayout.setHorizontalGroup(
            TablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        TablePanelLayout.setVerticalGroup(
            TablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
        );

        PlayerPanel.setBackground(new java.awt.Color(34, 177, 76));
        PlayerPanel.setLayout(new java.awt.GridBagLayout());

        ScorePanel.setBackground(new java.awt.Color(34, 177, 76));

        javax.swing.GroupLayout ScorePanelLayout = new javax.swing.GroupLayout(ScorePanel);
        ScorePanel.setLayout(ScorePanelLayout);
        ScorePanelLayout.setHorizontalGroup(
            ScorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
        ScorePanelLayout.setVerticalGroup(
            ScorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(ScorePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TablePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PlayerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(TablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(PlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(ScorePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jMenu1.setText("File");

        Start.setText("Start");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });
        jMenu1.add(Start);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem1.setText("Játékszabályok");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        // TODO add your handling code here:
        String[] robotCntr = {"2", "3", "4", "5"};
        this.choice = (String) JOptionPane.showInputDialog(this,
                "How many robots do you want to play against?",
                "Number",
                JOptionPane.QUESTION_MESSAGE,
                null,
                robotCntr,
                robotCntr[0]);

        this.start = true;

    }//GEN-LAST:event_StartActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        this.exit = true;
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        helpScreen.setTitle("Help");
        helpScreen.setSize(200, 200);
//        String help1 = "A rikiki egy a bridgehez hasonló kártyajáték. Legalább hárman játszák egy pakli franciakártyával. Mindenkinek osztanak ugyan annyi lapot és sorsolnak egy adu színt a játékosok. "
//                + "(A francia kártya színei: pikk ♠, kőr ♥, káró ♦ és treff ♣.) "
//                + "Az egyik játékost kiválasztják indulónak (emberek között az osztótól jobbra ülő), ő fog elsőnek hívni. Utána mindenki megtippeli, hogy hány ütése lesz. "
//                + "A hívó játékos kiválaszt egy kártyát és kirakja. Utána minden játékosnak sorban haladva rá kell raknia egy ugyan olyan színű lapot, vagy ha nincs neki olyan színű, akkor egy adut és ha az sincs akkor tetszőleges lapot. "
//                + "Az üt, akié a legnagyobb lap a hívott színben, vagy ha került bele adu, akkor az aki a legnagyobb adut rakta. Az ász a legnagyobb, utána a dáma, majd a bubi és utána a számos lapok következnek. Aki ütött az hív következőnek. "
//                + "Ha elfogytak a lapok mindenki megszámolja az ütéseit aki annyit ütött ahányat vállalt, az 10+2*n pontot kap, ahol n az ütéseinek száma. Aki pedig a válalásától eltérő számút ütött az -2*|n-v| pontot kap, ahol n szintén az ütések száma és v a vállalások száma. Lehet nullát is vállalni.";
        //jDialog1.setLocationRelativeTo(null);
//        helpScreen.pack();
        helpScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Exit;
    private javax.swing.JPanel MainPanel;
    public javax.swing.JPanel PlayerPanel;
    private java.awt.Label RoundLabel;
    public java.awt.Label RoundNumber;
    public javax.swing.JPanel ScorePanel;
    private javax.swing.JMenuItem Start;
    private javax.swing.JDialog StartDialog;
    public javax.swing.JPanel TablePanel;
    public javax.swing.JPanel TopPanel;
    public javax.swing.JLabel TrumpPic;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JDialog helpScreen;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private java.awt.Label trumpLabel;
    // End of variables declaration//GEN-END:variables
}
