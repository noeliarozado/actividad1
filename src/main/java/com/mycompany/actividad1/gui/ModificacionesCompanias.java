package com.mycompany.actividad1.gui;

import com.mycompany.actividad1.dto.Compania;
import com.mycompany.actividad1.logica.LogicaCompania;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * @author Noelia Rozado
 */
public class ModificacionesCompanias extends javax.swing.JFrame {

    private LogicaCompania logicaCompanias = new LogicaCompania();
    private JFXPanel fxPanel;
    private JFrame frame;
    private Map<JComponent, String> contextualHelpMap;

    /**
     * Creates new form ModificacionesCompanias
     */
    public ModificacionesCompanias() {
        initComponents();

        comboPrefijo.setBackground(Color.WHITE);
        btnModificar.setBackground(new Color(186, 213, 255));

        List<Compania> companias = logicaCompanias.getListaCompanias();

        String[] prefijos = new String[companias.size()];

        int i = 0;
        for (Compania compania : companias) {
            prefijos[i] = Integer.toString(compania.getPrefijo());
            i++;
        }
        comboPrefijo.setModel(new DefaultComboBoxModel(prefijos));

        inputPrecargado();

        setHelp();
    }

    /**
     * Carga la información de la compañía
     */
    private void inputPrecargado() {
        List<Compania> companias = logicaCompanias.getListaCompanias();

        String prefijoSeleccionado = (String) comboPrefijo.getSelectedItem();

        if (prefijoSeleccionado != null) {
            int prefijoSeleccionadoInt = Integer.parseInt(prefijoSeleccionado);
            for (Compania compania : companias) {
                if (compania.getPrefijo() == prefijoSeleccionadoInt) {
                    inputCodigo.setText(compania.getCodigo());
                    inputNombreCompania.setText(compania.getNombreCompania());
                    inputDireccion.setText(compania.getDireccion());
                    inputMunicipio.setText(compania.getMunicipio());
                    inputTelefonoPasajero.setText(compania.getTelefonoPasajero());
                    inputTelefonoAeropuerto.setText(compania.getTelefonoAeropuerto());
                    break;
                }
            }
        }
    }

    /**
     * Muestra la ayuda principal
     */
    private void setHelp() {
        fxPanel = new JFXPanel();
        frame = new JFrame("Ayuda");
        frame.setSize(new Dimension(500, 600));
        frame.add(fxPanel);

        contextualHelpMap = new HashMap<>();
        contextualHelpMap.put(comboPrefijo, "https://noelia-2.gitbook.io/ayuda3/pagina-principal/combo-prefijo");

        setContextualHelp(contextualHelpMap);
    }

    /**
     * Asigna la ayuda contextual
     */
    private void setContextualHelp(Map<JComponent, String> map) {
        for (JComponent comp : map.keySet()) {
            KeyStroke f1KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
            InputMap inputMap = comp.getInputMap(JComponent.WHEN_FOCUSED);
            ActionMap actionMap = comp.getActionMap();
            inputMap.put(f1KeyStroke, "showContextualHelp");
            actionMap.put("showContextualHelp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String helpURL = map.get(comp);
                    openWebView(helpURL);
                }
            });
        }
    }

    /**
     * Abre una ventana que muestra la ayuda
     *
     * @param url URL de la página de ayuda
     */
    private void openWebView(String url) {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.load(url);
            fxPanel.setScene(new Scene(webView));
            frame.setVisible(true);
        });
    }

    /**
     * Valida los componentes antes de guardar la modificación de una compañía
     * aérea
     *
     * @return true si los datos son válidos y false si no lo son
     */
    private boolean validarComponente() {
        String codigo = inputCodigo.getText();
        if (codigo == null || "".equals(codigo)) {
            JOptionPane.showMessageDialog(this, "El código no puede estar vacío.", "Error en el código",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!codigo.matches("[A-Z]{2}|[A-Z][0-9]")) {
            JOptionPane.showMessageDialog(this, "El código no cumple los requisitos.", "Error en el código.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String direccion = inputDireccion.getText();
        if (direccion == null || "".equals(direccion)) {
            JOptionPane.showMessageDialog(this, "La dirección no puede estar vacía.", "Error en la dirección",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String municipio = inputMunicipio.getText();
        if (municipio == null || "".equals(municipio)) {
            JOptionPane.showMessageDialog(this, "El municipio no puede estar vacío", "Error en el municipio",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String telefonoPasajero = inputTelefonoPasajero.getText();
        if (telefonoPasajero == null || "".equals(telefonoPasajero)) {
            JOptionPane.showMessageDialog(this, "El teléfono de información al pasajero no puede estar vacío.",
                    "Error en el teléfono de atención al pasajero.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!telefonoPasajero.matches("\\d{3}\\d{1,12}")) { // \d es igual a [0-9]
            JOptionPane.showMessageDialog(this, "El teléfono de atención al pasajero no cumple los requisitos.",
                    "Error en el teléfono de atención al pasajero.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String telefonoAeropuerto = inputTelefonoAeropuerto.getText();
        if (telefonoAeropuerto == null || "".equals(telefonoAeropuerto)) {
            JOptionPane.showMessageDialog(this, "El teléfono de información a aeropuertos no puede estar vacío.",
                    "Error en el teléfono de información a aeropuertos.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!telefonoAeropuerto.matches("\\d{3}\\d{1,12}")) {
            JOptionPane.showMessageDialog(this, "El teléfono de información a aeropuertos no cumple con los requisitos.",
                    "Error en el teléfono de información a aeropuertos.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        inputCodigo = new javax.swing.JTextField();
        inputNombreCompania = new javax.swing.JTextField();
        inputDireccion = new javax.swing.JTextField();
        inputTelefonoPasajero = new javax.swing.JTextField();
        inputMunicipio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inputTelefonoAeropuerto = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboPrefijo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuAyuda = new javax.swing.JMenu();
        menuAyudaPrincipal = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(214, 240, 248));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Introduzca los nuevos datos:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Prefijo:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Dirección:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Teléfono de información al pasajero:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Teléfono de información a aeropuertos:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Municipio:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Nombre de la compañía:");

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Seleccione una compañía:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Código:");

        comboPrefijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPrefijoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("MODIFICACIÓN DE COMPAÑÍAS");

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/homepage.png"))); // NOI18N
        btnMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flechaatras.png"))); // NOI18N
        btnVolver.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(454, 570, Short.MAX_VALUE)
                                .addComponent(btnMenu))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboPrefijo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(inputCodigo))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inputNombreCompania))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(inputDireccion))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inputMunicipio))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnModificar)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(inputTelefonoAeropuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(inputTelefonoPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboPrefijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(inputCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inputNombreCompania, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(inputDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(inputMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(inputTelefonoPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputTelefonoAeropuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMenu)
                            .addComponent(btnModificar))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        menuAyuda.setText("Ayuda");
        menuAyuda.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        menuAyudaPrincipal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        menuAyudaPrincipal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuAyudaPrincipal.setText("Ayuda principal");
        menuAyudaPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAyudaPrincipalActionPerformed(evt);
            }
        });
        menuAyuda.add(menuAyudaPrincipal);

        menuBar.add(menuAyuda);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (validarComponente()) {
            int prefijo = Integer.parseInt((String) comboPrefijo.getSelectedItem());
            String código = inputCodigo.getText();
            String nombreCompania = inputNombreCompania.getText();
            String direccion = inputDireccion.getText();
            String municipio = inputMunicipio.getText();
            String telefonoPasajero = inputTelefonoPasajero.getText();
            String telefonoAeropuerto = inputTelefonoAeropuerto.getText();

            logicaCompanias.actualizarCompania(new Compania(prefijo, código, nombreCompania, direccion, municipio,
                    telefonoPasajero, telefonoAeropuerto));
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void comboPrefijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPrefijoActionPerformed
        inputPrecargado();
    }//GEN-LAST:event_comboPrefijoActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void menuAyudaPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAyudaPrincipalActionPerformed
        openWebView("https://noelia-2.gitbook.io/ayuda3/");
    }//GEN-LAST:event_menuAyudaPrincipalActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        CompaniasPanelCRUD companiasPanelCRUD = new CompaniasPanelCRUD();
        companiasPanelCRUD.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(ModificacionesCompanias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificacionesCompanias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificacionesCompanias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificacionesCompanias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificacionesCompanias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> comboPrefijo;
    private javax.swing.JTextField inputCodigo;
    private javax.swing.JTextField inputDireccion;
    private javax.swing.JTextField inputMunicipio;
    private javax.swing.JTextField inputNombreCompania;
    private javax.swing.JTextField inputTelefonoAeropuerto;
    private javax.swing.JTextField inputTelefonoPasajero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuItem menuAyudaPrincipal;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}
