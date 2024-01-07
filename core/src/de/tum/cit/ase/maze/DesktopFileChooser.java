package de.tum.cit.ase.maze;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DesktopFileChooser implements FileChooser{

    @Override
    public void chooseFile(FileChooserCallBack callBack) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Property files","properties"));

        int returnValue = fileChooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            callBack.onFileChosen(filePath);
        }
    }
}
