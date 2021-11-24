/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author eljun
 */
public class Loguin extends javax.swing.JFrame {

    public Loguin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuario = new javax.swing.JTextField();
        txtContra = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsuario.setForeground(new java.awt.Color(0, 153, 153));
        txtUsuario.setText("Usuario");
        txtUsuario.setBorder(null);
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 125, 170, 30));

        txtContra.setForeground(new java.awt.Color(0, 153, 153));
        txtContra.setText("Contraseña");
        txtContra.setBorder(null);
        getContentPane().add(txtContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 166, 170, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilities/Boton logueo.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilities/Logueo seguro.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String usuario, contra, sql7;
        String driver = "com.mysql.cj.jdbc.Driver";
        String URL_bd = "jdbc:mysql://localhost:3306/prodactuz";
        String usu = "root";
        String cla = "";
        Connection con = null;
        usuario = txtUsuario.getText();
        contra= txtContra.getText();
        Statement comando = null;
        ResultSet registros = null;
        if(txtUsuario.getText().equals("admin1")){
            if(txtContra.getText().equals("4321")){
                Administrador.main(null);
            }else{
                    JOptionPane.showMessageDialog(null, "Acceso denegado:\n"+"Por favor ingrese un usuario y contraseña validos", "Acceso denegado", JOptionPane.ERROR_MESSAGE); 
                    try{
                        Class.forName(driver);
                        con = DriverManager.getConnection(URL_bd, usu, cla);
                        sql7="SELECT `Cod_admin` FROM tbl_admin WHERE `Nombre` = \""+usuario+"\" AND `Apellido` = \""+contra+"\" ";
                        comando = con.createStatement();
                        registros = comando.executeQuery(sql7);
                        if(registros.next()){
                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
                            Administrador_2.main(null);
                        }
                    }catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage());

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Loguin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }else{
            try{
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usu, cla);
            sql7="SELECT `Cod_admin` FROM tbl_admin WHERE `Nombre` = \""+usuario+"\" AND `Apellido` = \""+contra+"\" ";
            comando = con.createStatement();
            registros = comando.executeQuery(sql7);
            if(registros.next()){
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
                Administrador_2.main(null);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Loguin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Loguin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loguin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loguin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loguin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loguin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtContra;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
