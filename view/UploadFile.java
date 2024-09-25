package view;
import javax.swing.*;
// import javax.swing.filechooser.*;

public class UploadFile {
    public static void main(String[] args){
        JFileChooser chooser = new JFileChooser();
        // chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // esta linha ativa a opção somente pasta
        // chooser.setCurrentDirectory(new java.io.File("."));
        // chooser.setDialogTitle("Título da janela"); // esta linha define o título da tela do file chooser
        // FileNameExtensionFilter filter = new FileNameExtensionFilter(
        //         "JPG & GIF Images", "jpg", "gif");
        // chooser.setFileFilter(filter);

        chooser.setDialogTitle("Selecione o arquivo que deseja copiar.");
        int returnVal1 = chooser.showOpenDialog(null);
        String fileFullPath;
        if(returnVal1 == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
            System.out.println("Full Path: " + chooser.getSelectedFile().getAbsolutePath());
            fileFullPath = chooser.getSelectedFile().getAbsolutePath();
        } else {
            System.out.println("Que pena!");
        }

        chooser.setDialogTitle("Título da janela");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal2 = chooser.showOpenDialog(null);
        if(returnVal2 == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
            System.out.println("Full Path: " + chooser.getSelectedFile().getAbsolutePath());
        } else {
            System.out.println("Que pena!");
        }

    }
}
