package com.mycompany.actividad1.gui;

import com.mycompany.actividad1.dto.VueloDiario;
import com.mycompany.actividad1.logica.LogicaVueloDiario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
public class ModificacionesVuelosDiarios extends javax.swing.JFrame {

    private LogicaVueloDiario logicaVueloDiario = new LogicaVueloDiario();

    private JFXPanel fxPanel;
    private JFrame frame;
    private Map<JComponent, String> contextualHelpMap;

    /**
     * Creates new form ModificacionesV
     */
    public ModificacionesVuelosDiarios() {
        initComponents();

        comboCodigo.setBackground(Color.WHITE);
        btnModificar.setBackground(new Color(186, 213, 255));

        List<VueloDiario> vuelosDiarios = logicaVueloDiario.getListaVueloDiario();
        String[] codigosVuelo = new String[vuelosDiarios.size()];
        int i = 0;
        for (VueloDiario vueloDiario : vuelosDiarios) {
            codigosVuelo[i] = vueloDiario.getCodigoVuelo();
            i++;
        }
        comboCodigo.setModel(new DefaultComboBoxModel(codigosVuelo));

        inputPrecargado();

        setHelp();
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
        contextualHelpMap.put(comboCodigo, "https://noelia-2.gitbook.io/ayuda5/ayuda5/combo-codigo-de-vuelo");

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
     * Carga la información del vuelo
     */
    private void inputPrecargado() {
        List<VueloDiario> vuelosDiarios = logicaVueloDiario.getListaVueloDiario();

        String codigoSeleccionado = (String) comboCodigo.getSelectedItem();

        if (codigoSeleccionado != null) {
            for (VueloDiario vueloDiario : vuelosDiarios) {
                if (vueloDiario.getCodigoVuelo().equals(codigoSeleccionado)) {
                    inputFecha.setText(vueloDiario.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    inputHoraSalida.setText(vueloDiario.getHoraSalidaReal().toString());
                    inputHoraLlegada.setText(vueloDiario.getHoraLlegadaReal().toString());
                    inputPlazas.setText(String.valueOf(vueloDiario.getPlazasOcupadas()));
                    inputPrecioAsiento.setText(String.valueOf(vueloDiario.getPrecio()));
                    break;
                }
            }
        }
    }

    /**
     * Valida los componentes antes de guardar la modificación de un vuelo
     * diario
     *
     * @return true si los datos son válidos y false si no lo son
     */
    private boolean validarComponente() {
        String fecha = inputFecha.getText();
        if (fecha == null || "".equals(fecha)) {
            JOptionPane.showMessageDialog(this, "La fecha del vuelo no puede estar vacía.",
                    "Error en la fecha.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String horaSalida = inputHoraSalida.getText();
        if (horaSalida == null || "".equals(horaSalida)) {
            JOptionPane.showMessageDialog(this, "La hora de salida no puede estar vacía.", "Error en la hora de salida.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String horaLlegada = inputHoraLlegada.getText();
        if (horaLlegada == null || "".equals(horaLlegada)) {
            JOptionPane.showMessageDialog(this, "La hora de llegada no puede estar vacía.", "Error en la hora de llegada.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        double precioVuelo = Double.parseDouble(inputPrecioAsiento.getText());
        if ("".equals(precioVuelo)) {
            JOptionPane.showMessageDialog(this, "El precio del vuelo no puede estar vacío.", "Error en el precio del vuelo.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (precioVuelo <= 0) {
            JOptionPane.showMessageDialog(this, "El precio del vuelo no cumple los requisitos.", "Error en el precio del "
                    + "vuelo.", JOptionPane.ERROR_MESSAGE);
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
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboCodigo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        inputPrecioAsiento = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        inputHoraSalida = new javax.swing.JFormattedTextField();
        inputHoraLlegada = new javax.swing.JFormattedTextField();
        inputFecha = new javax.swing.JTextField();
        inputPlazas = new javax.swing.JTextField();
        btnMenu = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuAyuda = new javax.swing.JMenu();
        menuAyudaPrincipal = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(214, 240, 248));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Vuelo a diario a modificar:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Código de vuelo:");

        comboCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCodigoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Datos del nuevo vuelo diario:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Fecha:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Hora de salida real:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Hora de llegada real:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Número de plazas ocupadas:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Precio del vuelo:");

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setText("MODIFICACIÓN DE VUELOS DIARIOS");

        try {
            inputHoraSalida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            inputHoraLlegada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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
                .addGap(149, 149, 149)
                .addComponent(jLabel8)
                .addContainerGap(172, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(68, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboCodigo, 0, 221, Short.MAX_VALUE)
                            .addComponent(inputHoraSalida)
                            .addComponent(inputHoraLlegada)
                            .addComponent(inputPrecioAsiento)
                            .addComponent(inputFecha)
                            .addComponent(inputPlazas)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar)))
                .addGap(83, 83, 83)
                .addComponent(btnMenu)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(73, 73, 73)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(comboCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(inputFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(inputHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputHoraLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(inputPrecioAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(inputPlazas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnModificar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMenu)
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))))))
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

    private void comboCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCodigoActionPerformed
        inputPrecargado();
    }//GEN-LAST:event_comboCodigoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (validarComponente()) {
            String codigoVuelo = (String) comboCodigo.getSelectedItem();
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate fecha = LocalDate.parse(inputFecha.getText(), formatterDate);
            LocalTime horaSalida = LocalTime.parse(inputHoraSalida.getText());
            LocalTime horaLlegada = LocalTime.parse(inputHoraLlegada.getText());
            int plazasOcupadas = Integer.parseInt(inputPlazas.getText());
            double precioVuelo = Double.parseDouble(inputPrecioAsiento.getText());

            logicaVueloDiario.actualizarVueloDiario(new VueloDiario(codigoVuelo, fecha, horaSalida, horaLlegada,
                    plazasOcupadas, precioVuelo));
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void menuAyudaPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAyudaPrincipalActionPerformed
        openWebView("https://noelia-2.gitbook.io/ayuda5/");
    }//GEN-LAST:event_menuAyudaPrincipalActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        VuelosDiariosPanelCRUD vuelosDiariosPanelCRUD = new VuelosDiariosPanelCRUD();
        vuelosDiariosPanelCRUD.setVisible(true);
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
            java.util.logging.Logger.getLogger(ModificacionesVuelosDiarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificacionesVuelosDiarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificacionesVuelosDiarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificacionesVuelosDiarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificacionesVuelosDiarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> comboCodigo;
    private javax.swing.JTextField inputFecha;
    private javax.swing.JFormattedTextField inputHoraLlegada;
    private javax.swing.JFormattedTextField inputHoraSalida;
    private javax.swing.JTextField inputPlazas;
    private javax.swing.JTextField inputPrecioAsiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
