package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.*;

public class NovoCadastroView extends JFrame {
    public static NovoCadastroView appNovoCadastro = null;

    public final JLabel nomeJLabel = new JLabel("Digite um nome:", SwingConstants.RIGHT);
    public static final JTextField nomeJTextField = new JTextField();

    public final JLabel emailJLabel = new JLabel("Digite um email:", SwingConstants.RIGHT);
    public static final JTextField emailJTextField = new JTextField();

    public final JLabel senhaJLabel = new JLabel("Digite uma senha:", SwingConstants.RIGHT);
    public static final JPasswordField senhaJPasswordField = new JPasswordField();

    public static JLabel lblImg = new JLabel("", SwingConstants.CENTER);
    public static final JButton btnFoto = new JButton("Selecionar arquivo");

    public final JButton cadastrarJButton = new JButton("Cadastrar");

    public static final JLabel notificacaoJLabel = new JLabel("Notificações...", SwingConstants.CENTER);

    public static boolean propriaTela = false;

    public final String logoFullPath = System.getProperty("user.dir") + "\\src\\view\\img\\logo-perfect-burguer.jpg";

    public NovoCadastroView() {
        super("Novo Cadastro");
        setLayout(new GridLayout(7,1,5,5));

        JPanel linha_nome = new JPanel(new GridLayout(1, 2, 5, 5));

        linha_nome.add(nomeJLabel);
        linha_nome.add(nomeJTextField);
        add(linha_nome);

        JPanel linha_email = new JPanel(new GridLayout(1, 2, 5, 5));

        linha_email.add(emailJLabel);
        linha_email.add(emailJTextField);
        add(linha_email);

        JPanel linha_senha = new JPanel(new GridLayout(1, 2, 5, 5));

        linha_senha.add(senhaJLabel);
        linha_senha.add(senhaJPasswordField);
        add(linha_senha);

        JPanel linha_Foto = new JPanel(new GridLayout(1, 1, 5, 5));

        lblImg.setIcon(NovoCadastroController.definirFoto("avatar.png"));
        linha_Foto.add(lblImg);
        add(linha_Foto);

        JPanel linha_btnFoto = new JPanel(new GridLayout(1, 1, 5, 5));
        linha_btnFoto.add(btnFoto);
        add(linha_btnFoto);

        JPanel linha_btnCadastrar = new JPanel(new GridLayout(1, 1, 5, 5));

        linha_btnCadastrar.add(cadastrarJButton);
        add(linha_btnCadastrar);

        JPanel linha_notificacao = new JPanel(new GridLayout(1, 1, 5, 5));

        linha_notificacao.add(notificacaoJLabel);
        add(linha_notificacao);

        btnFoto.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    NovoCadastroController.selecionarArquivoController();
                }
            }
        );

        cadastrarJButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (nomeJTextField.getText().trim().length() <= 0) {
                        notificacaoJLabel.setText("Ops! Digite um nome para prosseguir e tente novamente.");
                        nomeJTextField.requestFocus();
                        return;
                    }
                    if (emailJTextField.getText().trim().length() <= 0) {
                        notificacaoJLabel.setText("Ops! Digite um email para prosseguir e tente novamente.");
                        emailJTextField.requestFocus();
                        return;
                    }
                    if (String.valueOf(senhaJPasswordField.getPassword()).trim().length() <= 0) {
                        notificacaoJLabel.setText("Ops! Digite uma senha para prosseguir e tente novamente.");
                        senhaJPasswordField.requestFocus();
                        return;
                    }
                    if (NovoCadastroController.fileFullName.equals("")) {
                        int respostaUsuario = JOptionPane.showConfirmDialog(null, "Ops! Não foi selecionado um arquivo para foto no cadastro. Deseja continuar?");
                        System.out.println("respostaUsuario: " + respostaUsuario);
                        if (respostaUsuario < 1) {
                            NovoCadastroController.novoCadastroController();
                        } else {
                            notificacaoJLabel.setText("Clique em: \"Selecionar arquivo\" para escolher uma foto.");
                        }
                    } else {
                        NovoCadastroController.novoCadastroController();
                    }
                }
            }
        );

        ImageIcon img = new ImageIcon(logoFullPath);
        setIconImage(img.getImage());
        setSize(500, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        appNovoCadastro = new NovoCadastroView();
        appNovoCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        propriaTela = true;
        appNovoCadastro.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (NovoCadastroController.fileFullName.equals("") == false) {
                    NovoCadastroController.removerArquivo();
                }
            }
        });
    }
}


