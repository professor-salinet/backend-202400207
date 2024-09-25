package controller;
import model.*;
import view.*;

// import java.awt.*;
import javax.swing.*;

public class LoginController extends JFrame {
    public static void logar() {
        try {
            boolean achouUsuario = LoginModel.loginUsuario(LoginFrameView.txtLogin.getText(), String.valueOf(LoginFrameView.txtSenha.getPassword()).trim());
            if (achouUsuario == true) {
                MenuFrameView appMenuFrame = new MenuFrameView();
                appMenuFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                LoginFrameView.appLoginFrame.dispose();
            } else {
                LoginFrameView.lblNotificacoes.setText(HelperController.setHtmlFormat("Não foi encontrado o login e/ou senha digitados! Por favor, verifique e tente novamente."));
            }
        } catch (Exception e) {
            System.err.println(e);
            LoginFrameView.lblNotificacoes.setText(HelperController.setHtmlFormat("Ops! Houve um problema com o banco de dados. Por favor, tente novamente mais tarde, obrigado."));
        }        
    }
}
