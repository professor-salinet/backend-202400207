package view;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import controller.*;
// import model.MySQLConnectorModel;

public class NovoCadastroView extends JFrame {
    private final JLabel nomeJLabel = new JLabel("Digite um nome:", SwingConstants.RIGHT);
    public static final JTextField nomeJTextField = new JTextField();

    private final JLabel emailJLabel = new JLabel("Digite um email:", SwingConstants.RIGHT);
    public static final JTextField emailJTextField = new JTextField();

    private final JLabel senhaJLabel = new JLabel("Digite uma senha:", SwingConstants.RIGHT);
    public static final JPasswordField senhaJPasswordField = new JPasswordField();

    private final JButton cadastrarJButton = new JButton("Cadastrar");

    public static final JLabel notificacaoJLabel = new JLabel("Notificações...", SwingConstants.CENTER);

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
                    NovoCadastroController.novoCadastroController();
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

    public static NovoCadastroView appNovoCadastro = null;
    public static void main(String[] args) {
        appNovoCadastro = new NovoCadastroView();
        appNovoCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}


