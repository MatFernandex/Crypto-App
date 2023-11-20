/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package po23s.http;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

public class PainelCrip extends javax.swing.JFrame {
        String tick = new String();
        String result = new String();
        ClienteHttp cripto = new ClienteHttp();
        List<String> tickersAdicionados = new ArrayList<>();
    public PainelCrip() {
        initComponents();
               

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TickerFild = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        UpdateButton = new javax.swing.JButton();
        RemoveButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Ticker");

        TickerFild.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        TickerFild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TickerFildActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TICKER", "COMPRA", "VENDA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        UpdateButton.setText("ATUALIZAR");
        UpdateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateButtonMouseClicked(evt);
            }
        });

        RemoveButton.setText("REMOVER");
        RemoveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RemoveButtonMouseClicked(evt);
            }
        });

        AddButton.setText("ADICIONAR");
        AddButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TickerFild, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(AddButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UpdateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RemoveButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TickerFild)
                    .addComponent(jLabel1)
                    .addComponent(AddButton)
                    .addComponent(UpdateButton)
                    .addComponent(RemoveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TickerFildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TickerFildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TickerFildActionPerformed

    private void AddButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddButtonMouseClicked
        DefaultTableModel TabelaTick = (DefaultTableModel) jTable1.getModel();

        tick = TickerFild.getText().toUpperCase();

        // Verifica se o ticker já foi adicionado
        if (tickersAdicionados.contains(tick)) {
            JOptionPane.showMessageDialog(null, "Tick informado já esta adicionado", "Tick já existente", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try{
            //Busca os dados na API
        result = cripto.buscaDados("https://www.mercadobitcoin.net/api/"+tick+"/ticker");
        JSONObject obj = new JSONObject(result);

        JSONObject ticker = obj.getJSONObject("ticker");

        String buy = ticker.getString("buy");
        String sell = ticker.getString("sell");
        
        String[] novaLinha = {tick, buy, sell};
            //Adiciona as infromaçoes na tabela 
        TabelaTick.addRow(novaLinha);

        // Adiciona o ticker à lista
        tickersAdicionados.add(tick);
        
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Tick informado não exsite", "Tick errado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_AddButtonMouseClicked

    private void RemoveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveButtonMouseClicked
        DefaultTableModel TabelaTick = (DefaultTableModel) jTable1.getModel();
            //Seleciona a linha 
        int selectedRow = jTable1.getSelectedRow();
        
        if (selectedRow != -1){
            // Remove o ticker da lista
            tickersAdicionados.remove(selectedRow);
            // Remove a linha da tabela
            TabelaTick.removeRow(selectedRow);
        }
    }//GEN-LAST:event_RemoveButtonMouseClicked

    private void UpdateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateButtonMouseClicked
        DefaultTableModel TabelaTick = (DefaultTableModel) jTable1.getModel();

        TabelaTick.setRowCount(0);
            //Percorre a lista, e atualiza todas
        for (String tick : tickersAdicionados) {
            result = cripto.buscaDados("https://www.mercadobitcoin.net/api/"+tick+"/ticker");
            JSONObject obj = new JSONObject(result);

            JSONObject ticker = obj.getJSONObject("ticker");

            String buy = ticker.getString("buy");
            String sell = ticker.getString("sell");
            String[] novaLinha = {tick, buy, sell};
            TabelaTick.addRow(novaLinha);
        }
    }//GEN-LAST:event_UpdateButtonMouseClicked


    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PainelCrip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JTextField TickerFild;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
