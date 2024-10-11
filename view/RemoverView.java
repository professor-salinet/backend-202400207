package view;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemoverView extends JFrame {
    public static JLabel lblId;
    public static JComboBox<String> cbxId;
    public static String[] ids;

    public static JLabel lblNome;
    public static JTextField txtNome;

    public static JLabel lblEmail;
    public static JTextField txtEmail;

    public static JLabel lblNotificacoes;

    public static JButton btnRemover;
    public static JButton btnCancelar;

    public static int tamanhoInputs = 20;

    public RemoverView()
    {
        super("Tela de Atualização");
        setLayout(new GridLayout(5,1,5,5));

        JPanel linha_id = new JPanel(new GridLayout(1, 2));

        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        linha_id.add(lblId);

        RemoverController.popularIds();
        cbxId = new JComboBox<String>(ids);
        linha_id.add(cbxId);

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

        txtEmail = new JTextField(tamanhoInputs);
        txtEmail.setEditable(false);
        linha_email.add(txtEmail);

        add(linha_email);

        JPanel linha_botoes = new JPanel(new GridLayout(1, 2));

        btnRemover = new JButton("Remover");
        linha_botoes.add(btnRemover);

        btnCancelar = new JButton("Cancelar");
        linha_botoes.add(btnCancelar);

        add(linha_botoes);

        JPanel linha_notificacoes = new JPanel(new GridLayout(1, 1));

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        linha_notificacoes.add(lblNotificacoes);

        add(linha_notificacoes);

        btnRemover.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    RemoverController.removerId();
                }
            }
        );

        btnCancelar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    RemoverController.limparCampos();
                }
            }
        );

        cbxId.addItemListener(
            new ItemListener() {
            @Override
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        RemoverController.atualizarCampos(false);
                    }
                } 
            }
        );

        setSize(250, 300);
        ImageIcon img = new ImageIcon("./senac-logo.png");
        setIconImage(img.getImage());
        setVisible(true);
        cbxId.requestFocus();
    }

    public static String setHtmlFormat(String strTexto) {
        return "<html><body>" + strTexto + "</body></html>";
    }

    public static void main(String[] args) {
        RemoverView appRemoverView = new RemoverView();
        appRemoverView.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }    
}
