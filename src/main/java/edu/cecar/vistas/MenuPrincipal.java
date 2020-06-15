/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.vistas;

import edu.cecar.modelo.FactorialHilo;
import edu.cecar.modelo.FiltroArchivoPlano;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        procesadores = Runtime.getRuntime().availableProcessors();
        txt_cores.setText(procesadores + "");
        JBResultado.setVisible(false);
    }

    public boolean modulo(BigInteger val) {
        if (!val.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextNumeroCalcular = new javax.swing.JTextArea();
        jBCalcular = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTextResultado = new javax.swing.JTextArea();
        JBResultado = new javax.swing.JButton();
        jLnumeroCore = new javax.swing.JLabel();
        txt_cores = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");
        setResizable(false);
        getContentPane().setLayout(null);

        jTextNumeroCalcular.setColumns(20);
        jTextNumeroCalcular.setRows(5);
        jTextNumeroCalcular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNumeroCalcularKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextNumeroCalcular);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 50, 437, 140);

        jBCalcular.setText("Calcular");
        jBCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCalcularActionPerformed(evt);
            }
        });
        getContentPane().add(jBCalcular);
        jBCalcular.setBounds(480, 170, 150, 40);

        JTextResultado.setEditable(false);
        JTextResultado.setColumns(20);
        JTextResultado.setRows(5);
        jScrollPane2.setViewportView(JTextResultado);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 240, 440, 150);

        JBResultado.setText("Descargar Resultado");
        JBResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBResultadoActionPerformed(evt);
            }
        });
        getContentPane().add(JBResultado);
        JBResultado.setBounds(480, 230, 150, 40);

        jLnumeroCore.setText("Número Core: ");
        getContentPane().add(jLnumeroCore);
        jLnumeroCore.setBounds(480, 130, 90, 30);

        txt_cores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_coresKeyTyped(evt);
            }
        });
        getContentPane().add(txt_cores);
        txt_cores.setBounds(580, 130, 30, 30);

        jLabel1.setText("Resultado:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(190, 210, 130, 20);

        jLabel2.setText("Numero a calcular: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(160, 20, 130, 20);

        setSize(new java.awt.Dimension(675, 444));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCalcularActionPerformed
        // TODO add your handling code here:
        FactorialHilo cf;
        BigInteger numeroCalcular = new BigInteger((jTextNumeroCalcular.getText()));
        BigInteger inicio;
        BigInteger indice;
        BigInteger finals;
        ArrayList<FactorialHilo> hilos = new ArrayList<>();
        procesadores = Integer.parseInt(txt_cores.getText());
        // System.out.println("RESAULTSADO: "+ cf.factorial(new BigInteger("19")));
        try {
            if (numeroCalcular.compareTo(BigInteger.ZERO) == 0 || numeroCalcular.compareTo(BigInteger.ONE) == 0) {
                JTextResultado.setText(1 + "");
            } else {
                if (modulo(numeroCalcular) == false) {
                    //par    
                    if (procesadores == 1) {
                        cf = new FactorialHilo(new BigInteger("2"), numeroCalcular);
                        cf.start();
                        cf.join();
                        JTextResultado.setText(cf.obtenerValor() + "");
                        JBResultado.setVisible(true);
                    } else {
                        inicio = new BigInteger("2");
                        indice = ((numeroCalcular.divide(new BigInteger("2"))).divide(BigInteger.valueOf(procesadores)));
                        finals = indice.multiply(new BigInteger("2"));
                        System.out.println("indiceasdf " + indice);
                        BigInteger resultado = new BigInteger("1");
                        for (int i = 0; i < procesadores; i++) {
                            System.out.println("Inicio: " + inicio);
                            System.out.println("Indice: " + finals);
                            cf = new FactorialHilo(inicio, finals);
                            hilos.add(cf);
                            cf.start();
                            if (i == procesadores - 2) {
                                System.out.println("asd");
                                inicio = finals.add(new BigInteger("2"));
                                finals = numeroCalcular;
                            } else {
                                inicio = finals.add(new BigInteger("2"));
                                finals = finals.add(indice.multiply(new BigInteger("2")));
                            }

                        }
                        for (FactorialHilo hilo : hilos) {
                            hilo.join();
                        }
                        for (FactorialHilo hilo : hilos) {
                            resultado = resultado.multiply(hilo.obtenerValor());
                        }
                        System.out.println("R " + resultado);
                        JTextResultado.setText(resultado + "");
                        JBResultado.setVisible(true);
                    }

                } else {
                    if (procesadores == 1) {
                        cf = new FactorialHilo(new BigInteger("3"), numeroCalcular);
                        cf.start();
                        cf.join();
                        JTextResultado.setText(cf.obtenerValor() + "");
                        JBResultado.setVisible(true);
                    } else {
                        //inpar
                        inicio = new BigInteger("3");
                        indice = ((numeroCalcular.divide(new BigInteger("2"))).divide(BigInteger.valueOf(procesadores)));
                        finals = indice.multiply(new BigInteger("2"));
                        finals = finals.add(BigInteger.ONE);
                        System.out.println("indiceasdf " + indice);
                        BigInteger resultado = new BigInteger("1");
                        for (int i = 0; i < procesadores; i++) {
                            System.out.println("Inicio: " + inicio);
                            System.out.println("Indice: " + finals);
                            cf = new FactorialHilo(inicio, finals);
                            hilos.add(cf);
                            cf.start();
                            if (i == procesadores - 2) {
                                System.out.println("asd");
                                inicio = finals.add(new BigInteger("2"));
                                finals = numeroCalcular;
                            } else {
                                inicio = finals.add(new BigInteger("2"));
                                finals = finals.add(indice.multiply(new BigInteger("2")));
                            }

                        }
                        for (FactorialHilo hilo : hilos) {
                            hilo.join();
                        }
                        for (FactorialHilo hilo : hilos) {
                            resultado = resultado.multiply(hilo.obtenerValor());
                        }
                        System.out.println("R " + resultado);
                        JTextResultado.setText(resultado + "");
                        JBResultado.setVisible(true);
                    }

                }
            }

        } catch (Exception e) {

        }

    }//GEN-LAST:event_jBCalcularActionPerformed

    private void jTextNumeroCalcularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNumeroCalcularKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextNumeroCalcularKeyTyped

    private void JBResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBResultadoActionPerformed
        // TODO add your handling code here:
        JFileChooser jf = new JFileChooser();
        jf.addChoosableFileFilter(new FiltroArchivoPlano());
        jf.setAcceptAllFileFilterUsed(false);
        try {
            int retrival = jf.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fw = new FileWriter(jf.getSelectedFile() + ".txt");
                    fw.write(JTextResultado.getText());
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el Archivo.");
        }

    }//GEN-LAST:event_JBResultadoActionPerformed

    private void txt_coresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_coresKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_coresKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    public int procesadores;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBResultado;
    private javax.swing.JTextArea JTextResultado;
    private javax.swing.JButton jBCalcular;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLnumeroCore;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextNumeroCalcular;
    private javax.swing.JTextField txt_cores;
    // End of variables declaration//GEN-END:variables
}
