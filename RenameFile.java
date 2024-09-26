import javax.swing.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;

public class RenameFile {
    public static void renameFile() {
        try {
            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Selecione o arquivo que deseja renomear");
            chooser.setApproveButtonText("Selecionar arquivo");
            int returnVal1 = chooser.showOpenDialog(null);
            String fileFullPath = "";
            String folderFullPath = "";
            String fileName = "";
            if(returnVal1 == JFileChooser.APPROVE_OPTION) {
                fileFullPath = chooser.getSelectedFile().getAbsolutePath();
                folderFullPath = chooser.getSelectedFile().getParent();
                fileName = chooser.getSelectedFile().getName();
            } else {
                System.out.println("Que pena! Ok, saindo...");
                System.exit(0);
            }

            String newFileName = JOptionPane.showInputDialog(null, "Digite o novo nome do arquivo", fileName);

            Path pathOrigin = Paths.get(fileFullPath);
            Path pathDestination = Paths.get(folderFullPath + "\\" + newFileName);
            if (
                fileFullPath.length() > 0 && 
                folderFullPath.length() > 0 &&
                fileName.length() > 0 &&
                newFileName.length() > 0) {
                Files.copy(pathOrigin, pathDestination, REPLACE_EXISTING);
                Files.delete(pathOrigin);
                System.out.println("Arquivo " + chooser.getSelectedFile().getName() + " renomeado com sucesso.");
            } else {
                System.out.println("Ops! Não foi possível renomear o arquivo. Por favor, verifique e tente novamente mais tarde.");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível renomear o arquivo. Erro: " + e);
        }
    }

    public static void main(String[] args) throws Exception {
        renameFile();
    }
}
