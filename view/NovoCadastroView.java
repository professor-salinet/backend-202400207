package view;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import model.MySQLConnectorModel;

public class NovoCadastroView extends JFrame {
    private final JLabel nomeJLabel = new JLabel("Digite um nome:", SwingConstants.RIGHT);
    private final JTextField nomeJTextField = new JTextField();

    private final JLabel emailJLabel = new JLabel("Digite um email:", SwingConstants.RIGHT);
    private final JTextField emailJTextField = new JTextField();

    private final JLabel senhaJLabel = new JLabel("Digite uma senha:", SwingConstants.RIGHT);
    private final JPasswordField senhaJPasswordField = new JPasswordField();

    private final JButton cadastrarJButton = new JButton("Cadastrar");

    private final JLabel notificacaoJLabel = new JLabel("Notificações...", SwingConstants.CENTER);

    private final String dbPadrao = "db_teste";
    private final String tblPadrao = "tbl_teste";

    public NovoCadastroView() {
        super("Novo Cadastro");
        setLayout(new GridLayout(5,1,5,5));

        JPanel linha1 = new JPanel(new GridLayout(1, 2, 5, 5));

        JPanel linha2 = new JPanel(new GridLayout(1, 2, 5, 5));

        JPanel linha3 = new JPanel(new GridLayout(1, 2, 5, 5));

        JPanel linha4 = new JPanel(new GridLayout(1, 1, 5, 5));

        JPanel linha5 = new JPanel(new GridLayout(1, 1, 5, 5));

        cadastrarJButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    try {
                        Connection conexao = MySQLConnectorModel.conectar();
                        String strSqlPesquisarEmail = "select * from `" + dbPadrao + "`.`" + tblPadrao + "` where `email` = '" + emailJTextField.getText() + "';";
                        Statement stmSqlPesquisarEmail = conexao.createStatement();
                        ResultSet rstPesquisarEmail = stmSqlPesquisarEmail.executeQuery(strSqlPesquisarEmail);
                        if (rstPesquisarEmail.next()) {
                            notificacaoJLabel.setText("Ops! Parece que há um cadastro com esse email. Porfavor, verifique e tente novamente com outro email.");
                            stmSqlPesquisarEmail.close();
                        } else {
                            String strSqlCadastrarRegistro = "insert into `" + dbPadrao + "`.`" + tblPadrao + "` (`nome`,`email`,`senha`) values ('" + nomeJTextField.getText() + "','" + emailJTextField.getText() + "','" + String.valueOf(senhaJPasswordField.getPassword()) +"');";
                            Statement stmSqlCadastrarRegistro = conexao.createStatement();
                            stmSqlCadastrarRegistro.addBatch(strSqlCadastrarRegistro);
                            stmSqlCadastrarRegistro.executeBatch();
                            stmSqlCadastrarRegistro.close();
                            notificacaoJLabel.setText("Cadastro realizado com sucesso!");
                            LoginFrameView.lblNotificacoes.setText("Cadastro realizado com sucesso!");
                            LoginFrameView.appLoginFrame.setVisible(true);
                            dispose(); // fechar a própria tela, sem fechar a tela anterior
                        }
                    } catch (Exception e) {
                        notificacaoJLabel.setText("Não foi possível realizar o cadastro. Por favor, verifique e tente novamente.");
                    }
                }
            }
        );

        linha1.add(nomeJLabel);
        linha1.add(nomeJTextField);
        add(linha1);

        linha2.add(emailJLabel);
        linha2.add(emailJTextField);
        add(linha2);

        linha3.add(senhaJLabel);
        linha3.add(senhaJPasswordField);
        add(linha3);

        linha4.add(cadastrarJButton);
        add(linha4);

        linha5.add(notificacaoJLabel);
        add(linha5);

        setSize(500, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        NovoCadastroView appNovoCadastro = new NovoCadastroView();
        appNovoCadastro.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
