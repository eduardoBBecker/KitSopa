package view;

import data.Cliente;
import data.ClienteDAO;
import data.Pedido;
import data.PedidoDAO;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class TelaPedidos extends javax.swing.JFrame {

    private int id_permissao;

    ClienteDAO clienteDAO = new ClienteDAO();
    PedidoDAO pedidoDAO = new PedidoDAO();

    public TelaPedidos() {
        initComponents();
        btnPedidos.setSelected(true);
        preencherClientes();
        preencherTabelaPedidos();
        cmbCliente.setSelectedItem(0);
        btnPedidos.setSelected(true);
    }

    public void validaPermissao(int id) {

        this.id_permissao = id;

        if (id_permissao == 2) {
            btnProdutos.setVisible(false);
        }
    }

    private void preencherClientes() {
        try {
            // Obter a lista de clientes do banco de dados
            List<Cliente> clientes = clienteDAO.listarClientes();

            // Criar um modelo para o combo box
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("<<< Selecione o cliente >>>");

            // Criar um mapa para associar os nomes dos clientes aos objetos Cliente
            Map<String, Cliente> clienteMap = new HashMap<>();

            // Adicionar os nomes dos clientes ao modelo e ao mapa
            for (Cliente cliente : clientes) {
                String nomeCliente = cliente.getNome();
                model.addElement(nomeCliente);
                clienteMap.put(nomeCliente, cliente);
            }

            // Definir o modelo no combo box
            cmbCliente.setModel(model);

            // Selecionar o valor "Selecione o cliente"
            cmbCliente.setSelectedItem("<<< Selecione o cliente >>>");

            // Definir o mapa de clientes como uma propriedade do combo box
            cmbCliente.putClientProperty("clienteMap", clienteMap);
        } catch (SQLException e) {
            // Tratar exceção ao obter a lista de clientes do banco de dados
            e.printStackTrace();
        }
    }

    private void preencherTabelaPedidos() {
        try {
            List<Pedido> pedidos = pedidoDAO.listarPedidos(); // Obter a lista de pedidos

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("DATA PEDIDO");
            model.addColumn("NOME CLIENTE");
            model.addColumn("ENDEREÇO");
            model.addColumn("QTDE ITENS");
            model.addColumn("VALOR TOTAL");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            for (Pedido pedido : pedidos) {
                Object[] rowData = {
                    dateFormat.format(pedido.getDataVenda()),
                    pedido.getNomeCliente(),
                    pedido.getEndereco(),
                    pedido.getQuantidadeItens(),
                    pedido.getValorTotal()
                };
                model.addRow(rowData);
            }

            tblPedidos.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnProdutos = new javax.swing.JButton();
        btnPedidos = new javax.swing.JButton();
        btnVendas = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ftxtDataPedido = new javax.swing.JFormattedTextField();
        cmbCliente = new javax.swing.JComboBox<>();
        btnFiltroData = new javax.swing.JButton();
        btnFiltroCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Pedidos");

        jPanel1.setBackground(new java.awt.Color(150, 0, 219));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setAutoscrolls(true);

        btnClientes.setText("CLIENTES");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnSair.setText("SAIR");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnProdutos.setText("PRODUTOS");
        btnProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutosActionPerformed(evt);
            }
        });

        btnPedidos.setText("PEDIDOS");
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });

        btnVendas.setText("VENDAS");
        btnVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(btnProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(btnClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVendas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(150, 0, 159));
        jLabel1.setText("PEDIDOS");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros:"));

        jLabel2.setText("Data do pedido:");

        jLabel3.setText("Cliente:");

        try {
            ftxtDataPedido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnFiltroData.setText("Filtrar por data");
        btnFiltroData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroDataActionPerformed(evt);
            }
        });

        btnFiltroCliente.setText("Filtrar por cliente");
        btnFiltroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltroCliente))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ftxtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltroData)))
                .addContainerGap(256, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ftxtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltroData))
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltroCliente))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DATA PEDIDO", "NOME CLIENTE", "ENDEREÇO", "QTDE ITENS", "VALOR TOTAL"
            }
        ));
        jScrollPane1.setViewportView(tblPedidos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        TelaCadastroCliente cadastroCliente = new TelaCadastroCliente();
        cadastroCliente.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutosActionPerformed
        TelaCadastroProdutos cadastroProduto = new TelaCadastroProdutos();
        cadastroProduto.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnProdutosActionPerformed

    private void btnVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendasActionPerformed
        TelaVendas vendas = new TelaVendas();
        vendas.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnVendasActionPerformed

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed

    }//GEN-LAST:event_btnPedidosActionPerformed

    private void btnFiltroDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroDataActionPerformed

        String dataFiltro = ftxtDataPedido.getText(); // Obtém a data de filtro do campo de texto

        try {
            // Formata a data para o formato esperado pelo banco de dados
            DateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dataFormatada = inputDateFormat.parse(dataFiltro);
            String dataFiltroFormatada = outputDateFormat.format(dataFormatada);

            // Chama o método listarPedidos com a data de filtro formatada
            List<Pedido> pedidos = pedidoDAO.listarPedidos(dataFiltroFormatada);

            // Limpa o modelo da tabela
            DefaultTableModel model = (DefaultTableModel) tblPedidos.getModel();
            model.setRowCount(0);

            // Preenche a tabela com os pedidos filtrados
            for (Pedido pedido : pedidos) {
                Object[] row = {
                    pedido.getDataVenda(),
                    pedido.getNomeCliente(),
                    pedido.getEndereco(),
                    pedido.getQuantidadeItens(),
                    pedido.getValorTotal()
                };
                model.addRow(row);
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Preencha a data no formato dd/MM/yyyy.");
            e.printStackTrace();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar o banco de dados.");
            e.printStackTrace();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado.");
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnFiltroDataActionPerformed

    @SuppressWarnings("empty-statement")
    private void btnFiltroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroClienteActionPerformed

        String nomeCliente = cmbCliente.getSelectedItem().toString(); // Obtém o nome do cliente
        int indexCliente = cmbCliente.getSelectedIndex();
        
        if (indexCliente == 0) {
            JOptionPane.showMessageDialog(this, "Selecione o cliente");
            return;
        };

        try {
            // Chama o método listarPedidosPorCliente com o nome do cliente como filtro
            List<Pedido> pedidos = pedidoDAO.listarPedidosPorCliente(nomeCliente);

            // Limpa o modelo da tabela
            DefaultTableModel model = (DefaultTableModel) tblPedidos.getModel();
            model.setRowCount(0);

            // Preenche a tabela com os pedidos filtrados
            for (Pedido pedido : pedidos) {
                Object[] row = {
                    pedido.getDataVenda(),
                    pedido.getNomeCliente(),
                    pedido.getEndereco(),
                    pedido.getQuantidadeItens(),
                    pedido.getValorTotal()
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trate a exceção de acordo com a necessidade da sua aplicação
        }

    }//GEN-LAST:event_btnFiltroClienteActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnFiltroCliente;
    private javax.swing.JButton btnFiltroData;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnProdutos;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnVendas;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JFormattedTextField ftxtDataPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPedidos;
    // End of variables declaration//GEN-END:variables
}
