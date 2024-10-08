package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFrameView extends JFrame {
    private final JLabel lblLogin = new JLabel("Login");
    private final JLabel lblSenha = new JLabel("Senha");

    public static final JLabel lblNotificacoes = new JLabel(HelperController.setHtmlFormat("Bem vindo! Faça login ou cadastre-se."), SwingConstants.CENTER);

    public static final JTextField txtLogin = new JTextField();
    public static final JPasswordField txtSenha = new JPasswordField();

    private final JButton btnLogin = new JButton("Login");
    private final JButton btnCadastrar = new JButton("Cadastrar");

    public static final JCheckBox cbxAceite = new JCheckBox("Aceito os termos");

    public LoginFrameView() {
        super("Tela de Login");
        setLayout(new GridLayout(7,1,5,5));

        JPanel pnlLinha1 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha2 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha3 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha4 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha5 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha6 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha7 = new JPanel(new GridLayout(1,2,5,5));
        JPanel pnlLinha8 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha9 = new JPanel(new GridLayout(1,1,5,5));
        JPanel pnlLinha10 = new JPanel(new GridLayout(1,1,5,5));

        pnlLinha1.add(new JLabel());
        // add(pnlLinha1);

        pnlLinha2.add(lblLogin);
        add(pnlLinha2);

        pnlLinha3.add(txtLogin);
        add(pnlLinha3);

        pnlLinha4.add(lblSenha);
        add(pnlLinha4);

        pnlLinha5.add(txtSenha);
        add(pnlLinha5);

        pnlLinha6.add(new JLabel());
        // add(pnlLinha6);

        pnlLinha7.add(btnLogin);
        pnlLinha7.add(btnCadastrar);
        add(pnlLinha7);

        pnlLinha8.add(new JLabel());

        cbxAceite.setSelected(true);
        cbxAceite.setHorizontalAlignment(SwingConstants.CENTER);
        pnlLinha9.add(cbxAceite);
        add(pnlLinha9);

        pnlLinha10.add(lblNotificacoes);
        add(pnlLinha10);

        btnLogin.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    try {
                        if (LoginFrameView.txtLogin.getText().trim().length() == 0) {
                            LoginFrameView.lblNotificacoes.setText(HelperController.setHtmlFormat("É necessário digitar um login para acessar! Por favor, verifique e tente novamente."));
                            LoginFrameView.txtLogin.requestFocus();
                        } else if (String.valueOf(LoginFrameView.txtSenha.getPassword()).trim().length() == 0) {
                            LoginFrameView.lblNotificacoes.setText(HelperController.setHtmlFormat("É necessário digitar uma senha para acessar! Por favor, verifique e tente novamente."));
                            LoginFrameView.txtSenha.requestFocus();
                        } else if (LoginFrameView.cbxAceite.isSelected() == false) {
                            LoginFrameView.lblNotificacoes.setText(HelperController.setHtmlFormat("É necessário aceitar os termos para acessar! Por favor, clique abaixo em \"Aceito os termos\" e tente novamente."));
                            LoginFrameView.cbxAceite.requestFocus();
                        } else {
                            LoginController.logar();
                        }
                    } catch (Exception e) {
                        LoginFrameView.lblNotificacoes.setText(HelperController.setHtmlFormat("Ops! Algo de errado não está certo com a digitação dos campos de login e senha. Por favor verifique e tente novamente mais tarde, mas, se preferir, veja o erro do sistema: " + e));
                    }
                }
            }
        );

        btnCadastrar.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    setVisible(false);
                    NovoCadastroView appNovoCadastro = new NovoCadastroView();
                    appNovoCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
            }
        );

        setSize(400, 300);
        setVisible(true);
    }

    public static LoginFrameView appLoginFrame;

    public static void main(String[] args) {
        appLoginFrame = new LoginFrameView();
        appLoginFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
