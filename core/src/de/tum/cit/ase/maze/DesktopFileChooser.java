package de.tum.cit.ase.maze;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * This class provides a desktop-specific implementation of
 * the {@link FileChooser} interface using Swing's {@link JFileChooser}.
 * It allows users to choose a file with a specific extension filter.
 */
public class DesktopFileChooser implements FileChooser{

    /**
     * Opens a file chooser dialog for the user to select a file with a specific extension.
     * @param callBack The callback to handle the chosen file path.
     */
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
