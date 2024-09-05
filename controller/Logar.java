package controller;
import model.*;
import view.*;

import java.sql.*;
import java.awt.*;
import javax.swing.*;

public class Logar extends JFrame {
    public static void logar() {
        try {
            if (LoginFrame.txtLogin.getText().trim().length() == 0) {
                LoginFrame.lblNotificacoes.setText(Helper.setHtmlFormat("É necessário digitar um login para acessar! Por favor, verifique e tente novamente."));
                LoginFrame.txtLogin.requestFocus();
            } else if (String.valueOf(LoginFrame.txtSenha.getPassword()).trim().length() == 0) {
                LoginFrame.lblNotificacoes.setText(Helper.setHtmlFormat("É necessário digitar uma senha para acessar! Por favor, verifique e tente novamente."));
                LoginFrame.txtSenha.requestFocus();
            } else {
                boolean achouUsuario = Login.loginUsuario(LoginFrame.txtLogin.getText(), String.valueOf(LoginFrame.txtSenha.getPassword()).trim());
                if (achouUsuario == true) {
                    MenuFrame appMenuFrame = new MenuFrame();
                    appMenuFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    LoginFrame.appLoginFrame.dispose();
                } else {
                    LoginFrame.lblNotificacoes.setText(Helper.setHtmlFormat("Não foi encontrado o login e/ou senha digitados! Por favor, verifique e tente novamente."));
                }
            }
        } catch (Exception e) {
            LoginFrame.lblNotificacoes.setText(Helper.setHtmlFormat("Ops! Deu ruim no banco de dados, veja o erro: " + e));
        }        
    }
}
