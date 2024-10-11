package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PesquisarView extends JFrame {
    public static JLabel lblPesquisa;
    public static JTextField txtPesquisa;

    public static JLabel lblId;
    public static JTextField txtId;

    public static JLabel lblNome;
    public static JTextField txtNome;

    public static JLabel lblEmail;
    public static JTextField txtEmail;

    public static JButton btnPesquisar;
    public static JButton btnPrimeiro;
    public static JButton btnAnterior;
    public static JButton btnProximo;
    public static JButton btnUltimo;

    public static JLabel lblNotificacoes;

    public static int tamanhoInputs = 20;
    public static String txtUsuario = "";

    public PesquisarView()
    {
        super("Tela de Pesquisa");
        setLayout(new GridLayout(7,1,5,5));

        JPanel linha_lblPesquisa = new JPanel(new GridLayout(1, 2));

        lblPesquisa = new JLabel("Pesquisa:", SwingConstants.CENTER);
        linha_lblPesquisa.add(lblPesquisa);

        btnPesquisar = new JButton("🔍");
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setEnabled(false);
        linha_lblPesquisa.add(btnPesquisar);

        add(linha_lblPesquisa);

        JPanel linha_inputPesquisa = new JPanel(new GridLayout(1, 1));

        txtPesquisa = new JTextField(tamanhoInputs);
        linha_inputPesquisa.add(txtPesquisa);

        add(linha_inputPesquisa);

        JPanel linha_id = new JPanel(new GridLayout(1, 2));

        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        linha_id.add(lblId);

        txtId = new JTextField(tamanhoInputs);
        txtId.setEnabled(false);
        linha_id.add(txtId);

        add(linha_id);

        JPanel linha_nome = new JPanel(new GridLayout(1, 2));

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        linha_nome.add(lblNome);

        txtNome = new JTextField(tamanhoInputs);
        txtNome.setEditable(false);
        linha_nome.add(txtNome);

        add(linha_nome);

        JPanel linha_email = new JPanel(new GridLayout(1, 2));

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        linha_email.add(lblEmail);

        txtEmail = new JTextField(10);
        txtEmail.setEditable(false);
        linha_email.add(txtEmail);

        add(linha_email);

        JPanel linha_botoes = new JPanel(new GridLayout(1, 4));

        btnPrimeiro = new JButton("<<");
        btnPrimeiro.setEnabled(false);
        linha_botoes.add(btnPrimeiro);

        btnAnterior = new JButton("<");
        btnAnterior.setEnabled(false);
        linha_botoes.add(btnAnterior);

        btnProximo = new JButton(">");
        btnProximo.setEnabled(false);
        linha_botoes.add(btnProximo);

        btnUltimo = new JButton(">>");
        btnUltimo.setEnabled(false);
        linha_botoes.add(btnUltimo);

        add(linha_botoes);

        JPanel linha_notificacoes = new JPanel(new GridLayout(1, 1));

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        linha_notificacoes.add(lblNotificacoes);

        add(linha_notificacoes);

        btnPesquisar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (txtPesquisa.getText().trim().length() <= 0) {
                        lblNotificacoes.setText(setHtmlFormat("Por favor, digite algo e tente novamente."));
                        txtPesquisa.requestFocus();
                        return;
                    } else {
                        PesquisarController.pesquisar();
                    }
                }
            }
        );

        btnPrimeiro.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (ntfCampoVazio() == false) {
                        PesquisarController.primeiroRegistro();
                    }
                }
            }
        );

        btnAnterior.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (ntfCampoVazio() == false) {
                        PesquisarController.registroAnterior();
                    }
                }
            }
        );

        btnProximo.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (ntfCampoVazio() == false) {
                        PesquisarController.proximoRegistro();
                    }
                }
            }
        );

        btnUltimo.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (ntfCampoVazio() == false) {
                        PesquisarController.ultimoRegistro();
                    }
                }
            }
        );

        txtPesquisa.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (txtPesquisa.getText().trim().equals(txtUsuario) == false && txtPesquisa.getText().trim().length() > 0) {
                        if (e.getKeyCode() == 10) {
                            PesquisarController.pesquisar();
                        }
                    } else {
                        limparCampos("Digite algo para continuar.");
                    }
                    btnPesquisar.setEnabled(true);
                }
            }
        );

        setSize(250, 300);
        ImageIcon img = new ImageIcon("./senac-logo.png");
        setIconImage(img.getImage());
        setVisible(true);
        txtPesquisa.requestFocus();
    }

    public static boolean ntfCampoVazio() {
        if (txtPesquisa.getText().trim().length() <= 0) {
            lblNotificacoes.setText(setHtmlFormat("Ops! Campo vazio. Por favor, digite algo e tente novamente."));
            txtPesquisa.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    public static void limparCampos(String notificacao) { // limparCampos("")
        btnPesquisar.setEnabled(false);
        txtId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
        btnPrimeiro.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(false);
        btnUltimo.setEnabled(false);
        if (notificacao.trim().length() > 0) {
            lblNotificacoes.setText(setHtmlFormat(notificacao));
        }
    }

    public static String setHtmlFormat(String strTexto) {
        return "<html><body>" + strTexto + "</body></html>";
    }

    public static PesquisarView appPesquisarView;
    public static void main(String[] args) {
        appPesquisarView = new PesquisarView();
        appPesquisarView.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
