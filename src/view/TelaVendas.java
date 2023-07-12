package view;

import data.Cliente;
import data.ClienteDAO;
import data.Produto;
import data.ProdutoDAO;
import data.ProdutoVendido;
import data.Venda;
import data.VendaDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class TelaVendas extends javax.swing.JFrame {

    ClienteDAO clienteDAO = new ClienteDAO();
    ProdutoDAO produtoDAO = new ProdutoDAO();
    private List<ProdutoVendido> itensVendidos = new ArrayList<>();
    private double valorTotalVenda = 0.0;

    public TelaVendas() {
        initComponents();
        preencherClientes();
        preencherProdutos();
        cmbCliente.setSelectedItem(0);
        cmbProduto.setSelectedItem(0);

    }

    public void validaPermissao(int id) {
        // Verifica o ID da permissão
        if (id == 2) {
            // Oculta o botão de produtos
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

    private void preencherProdutos() {
        try {
            // Obter a lista de produtos do banco de dados
            List<Produto> produtos = produtoDAO.listarProdutos();

            // Criar um modelo para o combo box
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("<<< Selecione o produto >>>");

            // Adicionar os nomes dos produtos ao modelo
            for (Produto produto : produtos) {
                model.addElement(produto.getNome());
            }

            // Definir o modelo no combo box
            cmbProduto.setModel(model);

            // Selecionar o valor "Selecione o produto"
            cmbProduto.setSelectedItem("<<< Selecione o produto >>>");
        } catch (SQLException e) {
            // Tratar exceção ao obter a lista de produtos do banco de dados
            e.printStackTrace();
        }
    }

    private Date parseDate(String dateString) {
        dateString = dateString.trim(); // Remover espaços em branco

        // Verificar se a string de data possui pelo menos um valor numérico
        if (!dateString.matches(".*\\d.*")) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Não permitir datas inválidas

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void limparFormulario() {
        ftxtData.setText("");
        ftxtDataEntrega.setText("");
        cmbCliente.setSelectedIndex(0);
        cmbProduto.setSelectedIndex(0);
        txtQuantidade.setText("");
        itensVendidos.clear();
        DefaultTableModel model = (DefaultTableModel) tblListaItens.getModel();
        model.setRowCount(0);
        atualizarTotalVenda();
    }

    private void calcularValorTotal() {
        valorTotalVenda = 0.0;
        for (ProdutoVendido item : itensVendidos) {
            valorTotalVenda += item.getValorTotal();
        }
        atualizarTotalVenda();
    }

    private void atualizarTotalVenda() {
        double total = 0.0;
        for (ProdutoVendido item : itensVendidos) {
            total += item.getValorTotal();
        }
        ftxtTotalVenda.setValue(total);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaItens = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        ftxtData = new javax.swing.JFormattedTextField();
        cmbCliente = new javax.swing.JComboBox<>();
        cmbProduto = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ftxtDataEntrega = new javax.swing.JFormattedTextField();
        ftxtTotalVenda = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(150, 0, 159));
        jLabel1.setText("MODULO DE VENDAS");

        tblListaItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD PRODUTO", "NOME", "VALOR UNITÁRIO", "QUANTIDADE", "VALOR TOTAL"
            }
        ));
        jScrollPane1.setViewportView(tblListaItens);

        jLabel2.setText("Data:*");

        jLabel3.setText("Cliente:*");

        jLabel4.setText("Produto:*");

        btnSalvar.setBackground(new java.awt.Color(0, 153, 51));
        btnSalvar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        try {
            ftxtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Quantidade*");

        txtQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeActionPerformed(evt);
            }
        });

        btnAdicionar.setBackground(new java.awt.Color(0, 153, 51));
        btnAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionar.setText("+");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setBackground(new java.awt.Color(255, 0, 0));
        btnRemover.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRemover.setForeground(new java.awt.Color(255, 255, 255));
        btnRemover.setText("-");
        btnRemover.setToolTipText("Clique aqui para retirar um intem selecionado da lista");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(204, 0, 51));
        jLabel6.setText("(*) Preenchimento obrigatório");

        jLabel7.setText("Data de entrega:*");

        try {
            ftxtDataEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ftxtTotalVenda.setEditable(false);
        ftxtTotalVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        ftxtTotalVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtTotalVendaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Total da Venda:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftxtTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(217, 217, 217))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(86, 86, 86))
                            .addComponent(ftxtData, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(ftxtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftxtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftxtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvar)
                        .addComponent(btnCancelar)
                        .addComponent(ftxtTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        TelaCadastroCliente cadastroCliente = new TelaCadastroCliente();
        cadastroCliente.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        String data = ftxtData.getText();
        String cliente = cmbCliente.getSelectedItem().toString();
        String produto = cmbProduto.getSelectedItem().toString();
        String quantidade = txtQuantidade.getText();

        // Verificar se todos os campos estão preenchidos
        if (data.isEmpty() || cliente.isEmpty() || produto.isEmpty() || quantidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obter o produto selecionado no combo box
        Produto produtoSelecionado = produtoDAO.obterProdutoPorNome(produto);

        // Verificar se o produto foi encontrado
        if (produtoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para adicionar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar um objeto ProdutoVendido com as informações do produto selecionado e quantidade
        ProdutoVendido itemVendido = new ProdutoVendido(produtoSelecionado, Integer.parseInt(quantidade));

        // Adicionar o item à lista de itens vendidos
        itensVendidos.add(itemVendido);

        // Atualizar o valor total
        calcularValorTotal();
        atualizarTotalVenda();

        // Calcular o valor total do item
        double valorUnitario = produtoSelecionado.getValorUnitario();
        int qtd = Integer.parseInt(quantidade);
        double valorTotal = valorUnitario * qtd;

        // Obter o modelo da tabela
        DefaultTableModel model = (DefaultTableModel) tblListaItens.getModel();

        // Adicionar o item à tabela
        model.addRow(new Object[]{produtoSelecionado.getId(), produtoSelecionado.getNome(), valorUnitario, qtd, valorTotal});

        // Limpar os campos
        txtQuantidade.setText("");

    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVendasActionPerformed

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed
        TelaPedidos pedidos = new TelaPedidos();
        pedidos.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnPedidosActionPerformed

    private void btnProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutosActionPerformed
        TelaCadastroProdutos cadastroProduto = new TelaCadastroProdutos();
        cadastroProduto.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnProdutosActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        int selectedRow = tblListaItens.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblListaItens.getModel();

        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
            // Atualizar o valor total
            calcularValorTotal();
            atualizarTotalVenda();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        // Obter a data, cliente e data de entrega selecionados
        String dataString = ftxtData.getText();
        String dataEntregaString = ftxtDataEntrega.getText();
        String clienteNome = (String) cmbCliente.getSelectedItem();

        // Verificar se todos os campos obrigatórios foram preenchidos
        if (dataString.isEmpty() || cmbCliente.getSelectedIndex() == 0 || dataEntregaString.isEmpty() || itensVendidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Converter as datas para o formato Date
        Date dataVenda = parseDate(dataString);
        Date dataEntrega = parseDate(dataEntregaString);

        // Verificar se as datas são válidas
        if (dataVenda == null || dataEntrega == null) {
            JOptionPane.showMessageDialog(this, "Data inválida. Preencha os campos de data correntamente! Ex: 01/01/2000", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obter o objeto Cliente selecionado com base no nome
        Map<String, Cliente> clienteMap = (Map<String, Cliente>) cmbCliente.getClientProperty("clienteMap");
        Cliente cliente = clienteMap.get(clienteNome);

        // Criar um objeto Venda com os dados fornecidos
        Venda venda = new Venda();
        venda.setDataVenda(dataVenda);
        venda.setCliente(cliente);
        venda.setDataEntrega(dataEntrega);

        // Calcular o valor total dos itens vendidos
        double valorTotal = 0;
        for (ProdutoVendido produtoVendido : itensVendidos) {
            valorTotal += produtoVendido.getValorTotal();
        }
        venda.setValorTotal(valorTotal);

        ftxtTotalVenda.setValue(valorTotalVenda);

        // Salvar a venda no banco de dados
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.salvarVenda(venda, itensVendidos);

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void ftxtTotalVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtTotalVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtTotalVendaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnProdutos;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVendas;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbProduto;
    private javax.swing.JFormattedTextField ftxtData;
    private javax.swing.JFormattedTextField ftxtDataEntrega;
    private javax.swing.JFormattedTextField ftxtTotalVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaItens;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
