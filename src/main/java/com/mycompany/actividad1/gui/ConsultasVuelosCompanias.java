package com.mycompany.actividad1.gui;

import com.mycompany.actividad1.dto.Compania;
import com.mycompany.actividad1.dto.VueloTableModel;
import com.mycompany.actividad1.dto.VueloDiario;
import com.mycompany.actividad1.dto.Vuelo;
import com.mycompany.actividad1.logica.LogicaAeropuerto;
import com.mycompany.actividad1.logica.LogicaCompania;
import com.mycompany.actividad1.logica.LogicaVueloDiario;
import com.mycompany.actividad1.logica.LogicaVuelo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * @author Noelia Rozado
 */
public class ConsultasVuelosCompanias extends javax.swing.JFrame {

    private LogicaVueloDiario logicaVuelosDiarios = new LogicaVueloDiario();
    private LogicaCompania logicaCompanias = new LogicaCompania();
    private LogicaVuelo logicaVuelos = new LogicaVuelo();
    private LogicaAeropuerto logicaAeropuerto = new LogicaAeropuerto();

    /**
     * Creates new form ConsultasVuelosCompanias
     */
    public ConsultasVuelosCompanias() {
        initComponents();
        btnSeleccionarFecha.setBackground(new Color(186, 213, 255));
        comboCompanias.setBackground(new Color(186, 213, 255));

        List<Compania> companias = logicaCompanias.getListaCompanias();

        String[] codigos = new String[companias.size()];
        int i = 0;
        for (Compania compania : companias) {
            codigos[i] = compania.getCodigo();
            i++;
        }

        comboCompanias.setModel(new DefaultComboBoxModel(codigos));

        actualizarTabla(LocalDate.now());

        formatearEncabezadoTabla();

        formatearTabla();
    }

    /**
     * Actualiza la tabla con los vuelos por compañía
     *
     * @param date fecha actual
     */
    private void actualizarTabla(LocalDate date) {
        List<VueloDiario> vuelosDiarios = logicaVuelosDiarios.mostrarDiasCompania((String) comboCompanias.getSelectedItem(),
                date);

        List<Vuelo> listaVuelos = logicaVuelos.getListaVuelos();

        VueloTableModel dataModel = new VueloTableModel(vuelosDiarios, listaVuelos);

        tblVuelosCompania.setModel(dataModel);
    }

    /**
     * Formatea el encabezado de la tabla
     */
    private void formatearEncabezadoTabla() {
        JTableHeader encabezado = tblVuelosCompania.getTableHeader();

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                c.setBackground(new Color(210, 230, 255));
                setHorizontalAlignment(JLabel.CENTER);
                setFont(new Font("Segoe UI", Font.BOLD, 13));
                return c;
            }
        };
        encabezado.setDefaultRenderer(headerRenderer);
    }

    /**
     * Formatea la apariencia de la tabla
     */
    private void formatearTabla() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        int[] columnasCentradas = {0, 1, 2, 3, 4, 5};

        for (int columnIndex : columnasCentradas) {
            tblVuelosCompania.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        TableColumnModel columnModel = tblVuelosCompania.getColumnModel();

        int indiceColumnaCodigo = 0;
        TableColumn columnaCodigo = columnModel.getColumn(indiceColumnaCodigo);
        columnaCodigo.setPreferredWidth(80);

        int indiceColumnaFecha = 1;
        TableColumn columnaFecha = columnModel.getColumn(indiceColumnaFecha);
        columnaFecha.setPreferredWidth(60);

        int indiceColumnaHoraSalida = 2;
        TableColumn columnaHoraSalida = columnModel.getColumn(indiceColumnaHoraSalida);
        columnaHoraSalida.setPreferredWidth(90);

        int indiceColumnaHoraLlegada = 3;
        TableColumn columnaHoraLlegada = columnModel.getColumn(indiceColumnaHoraLlegada);
        columnaHoraLlegada.setPreferredWidth(90);

        int indiceColumnaAeropuertoOrigen = 4;
        TableColumn columnaAeropuertoOrigen = columnModel.getColumn(indiceColumnaAeropuertoOrigen);
        columnaAeropuertoOrigen.setPreferredWidth(120);

        int indiceColumnaAeropuertoDestino = 5;
        TableColumn columnaAeropuertoDestino = columnModel.getColumn(indiceColumnaAeropuertoDestino);
        columnaAeropuertoDestino.setPreferredWidth(120);
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
        comboCompanias = new javax.swing.JComboBox<>();
        inputFecha = new javax.swing.JFormattedTextField();
        btnSeleccionarFecha = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVuelosCompania = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        apiTemperaturasMiAeropuerto = new com.mycompany.actividad1.api.ApiTemperaturas();
        apiTemperaturasOtroAeropuerto = new com.mycompany.actividad1.api.ApiTemperaturas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(214, 240, 248));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Seleccione una compañía:");

        comboCompanias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboCompanias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCompaniasActionPerformed(evt);
            }
        });

        try {
            inputFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        inputFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSeleccionarFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSeleccionarFecha.setText("Seleccionar compañía y fecha");
        btnSeleccionarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarFechaActionPerformed(evt);
            }
        });

        tblVuelosCompania.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVuelosCompania.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblVuelosCompaniaMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblVuelosCompania);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 204));
        jLabel7.setText("VUELOS POR COMPAÑÍA");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Escriba una fecha:");

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

        apiTemperaturasMiAeropuerto.setApiInformacion(new com.mycompany.actividad1.api.ApiInformacion(33044, "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvY2Vhbm95dGllcnJhQGdtYWlsLmNvbSIsImp0aSI6IjAwNzI2NzZkLTU4ZDctNDgzMC1iOTA5LWM2OGYxNDkxNTQyZCIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjk5MjgxNjg4LCJ1c2VySWQiOiIwMDcyNjc2ZC01OGQ3LTQ4MzAtYjkwOS1jNjhmMTQ5MTU0MmQiLCJyb2xlIjoiIn0.rFtCYDSZY8jRcXPtaQPXaBFhDEubJ3tNP3sKxZEsSX0"));

        apiTemperaturasOtroAeropuerto.setApiInformacion(new com.mycompany.actividad1.api.ApiInformacion(33044, "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvY2Vhbm95dGllcnJhQGdtYWlsLmNvbSIsImp0aSI6IjAwNzI2NzZkLTU4ZDctNDgzMC1iOTA5LWM2OGYxNDkxNTQyZCIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjk5MjgxNjg4LCJ1c2VySWQiOiIwMDcyNjc2ZC01OGQ3LTQ4MzAtYjkwOS1jNjhmMTQ5MTU0MmQiLCJyb2xlIjoiIn0.rFtCYDSZY8jRcXPtaQPXaBFhDEubJ3tNP3sKxZEsSX0"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(apiTemperaturasMiAeropuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(251, 251, 251))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(comboCompanias, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(inputFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(apiTemperaturasOtroAeropuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(570, 570, 570)
                        .addComponent(btnMenu)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(btnSeleccionarFecha)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCompanias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(apiTemperaturasOtroAeropuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(apiTemperaturasMiAeropuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnSeleccionarFecha)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenu, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblVuelosCompaniaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVuelosCompaniaMousePressed
        if (tblVuelosCompania.getSelectedRow() != -1) {
            VueloTableModel llegadasTableModel = (VueloTableModel) tblVuelosCompania.getModel();
            try {
                apiTemperaturasMiAeropuerto.cambiarCiudad(llegadasTableModel.getCodigoDestinoEn(tblVuelosCompania.getSelectedRow()));
                apiTemperaturasOtroAeropuerto.cambiarCiudad(llegadasTableModel.getCodigoOrigenEn(tblVuelosCompania.getSelectedRow()));
            } catch (NullPointerException np) {
                JOptionPane.showMessageDialog(null, "Sin información.");
            }
        }
    }//GEN-LAST:event_tblVuelosCompaniaMousePressed

    private void btnSeleccionarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarFechaActionPerformed
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d/M/yyyy");

        LocalDate fechaSeleccionada = LocalDate.parse(inputFecha.getText(), formatterDate);

        actualizarTabla(fechaSeleccionada);

        formatearTabla();
    }//GEN-LAST:event_btnSeleccionarFechaActionPerformed

    private void comboCompaniasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCompaniasActionPerformed
        actualizarTabla(LocalDate.now());

        formatearTabla();
    }//GEN-LAST:event_comboCompaniasActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultasVuelosCompanias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultasVuelosCompanias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultasVuelosCompanias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultasVuelosCompanias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultasVuelosCompanias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.actividad1.api.ApiTemperaturas apiTemperaturasMiAeropuerto;
    private com.mycompany.actividad1.api.ApiTemperaturas apiTemperaturasOtroAeropuerto;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnSeleccionarFecha;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> comboCompanias;
    private javax.swing.JFormattedTextField inputFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblVuelosCompania;
    // End of variables declaration//GEN-END:variables
}
