package controller;
import model.*;
import view.*;

public class DespertadorController {
    public static String[] verOpcoes() {
        return DespertadorModel.mostrarOpcoes();
    }

    public static String acaoDespertador(int opcaoUsuario) {
        String resposta = "";
        switch (opcaoUsuario) {
            case 1:
                resposta = "Ok! Alarme adiado em: " + DespertadorView.adiar + " minutos.";
                DespertadorView.minutoDespertar += DespertadorView.adiar;
                if (DespertadorView.minutoDespertar > 60) {
                    DespertadorView.minutoDespertar = 60;
                }
                break;
        
            case 2:
                System.out.println("Ok! Alarme parado.");
                System.exit(0);
                break;

            default:
                resposta = "Opção inválida.";
                break;
        }
        return resposta;
    }
}
