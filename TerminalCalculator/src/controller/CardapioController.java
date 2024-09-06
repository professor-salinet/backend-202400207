package controller;

import model.CardapioModel;

public class CardapioController {

    public static String[] verProdutos() {
        return CardapioModel.buscarCardapio();
    }

    public static String respostaParaCliente(int respostaDoCliente) {
        String resposta;
        // o switch case abaixo tem q estar na controller
        switch (respostaDoCliente) {
            case 1:
                // conforme o usuário digitar a opção, será requisitado "dar baixa" no produto/opção escolhida
                resposta = "Caprichou na escolha hein, pois esse lanche mata qualquer fome, uhuuu";
                break;

            case 2:
                resposta = "Pensa num lanche li-te-ral-men-te animal, pois os três hamburgueres artesanais de 100g (cada), irá forrar seu estômago tranquilamente";
                break;

            case 3:
                resposta = "Socorro, você decidiu pegar o lanche mais bruto, q minhasarma te ajude a comer esse monstro";
                break;

            default:
                resposta = "Opção inválida";
                break;
        }
        
        return resposta;
    }
    
}
