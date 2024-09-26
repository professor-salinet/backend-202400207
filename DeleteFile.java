import javax.swing.*;
import java.nio.file.*;

public class DeleteFile {
    public static void deleteFile() {
        try {
            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Selecione o arquivo que deseja apagar");
            chooser.setApproveButtonText("Apagar arquivo");
            int returnVal1 = chooser.showOpenDialog(null);
            String fileFullPath = "";
            if(returnVal1 == JFileChooser.APPROVE_OPTION) {
                fileFullPath = chooser.getSelectedFile().getAbsolutePath();
            } else {
                System.out.println("Que pena!");
                System.exit(0);
            }

            Path pathOrigin = Paths.get(fileFullPath);
            Files.delete(pathOrigin);
            System.out.println("Arquivo apagado com sucesso!");
        } catch (Exception e) {
            System.out.println("Não foi possível apagar o arquivo.");
        }
    }

    public static void main(String[] args) throws Exception {
        deleteFile();
    }
}
