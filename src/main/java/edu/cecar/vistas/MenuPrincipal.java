/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.vistas;

import edu.cecar.controladores.ControladorNumeros;
import edu.cecar.modelo.FactorialHilo;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        jScrollPane1.setBounds(10, 11, 437, 179);

        jBCalcular.setText("Calcular");
        jBCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCalcularActionPerformed(evt);
            }
        });
        getContentPane().add(jBCalcular);
        jBCalcular.setBounds(480, 80, 150, 40);

        JTextResultado.setEditable(false);
        JTextResultado.setColumns(20);
        JTextResultado.setRows(5);
        jScrollPane2.setViewportView(JTextResultado);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 200, 440, 190);

        JBResultado.setText("Descargar Resultado");
        getContentPane().add(JBResultado);
        JBResultado.setBounds(480, 140, 150, 40);

        jLnumeroCore.setText("Número Core: ");
        getContentPane().add(jLnumeroCore);
        jLnumeroCore.setBounds(470, 10, 90, 30);

        txt_cores.setEditable(false);
        getContentPane().add(txt_cores);
        txt_cores.setBounds(570, 10, 30, 30);

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
        procesadores = 4;
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
                                finals = finals.multiply(new BigInteger("2"));
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
                    }

                } else {
                    if (procesadores == 1) {
                        cf = new FactorialHilo(new BigInteger("3"), numeroCalcular);
                        cf.start();
                        cf.join();
                        JTextResultado.setText(cf.obtenerValor() + "");
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
    private javax.swing.JLabel jLnumeroCore;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextNumeroCalcular;
    private javax.swing.JTextField txt_cores;
    // End of variables declaration//GEN-END:variables
}
