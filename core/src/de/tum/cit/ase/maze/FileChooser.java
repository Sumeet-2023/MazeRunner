package de.tum.cit.ase.maze;

/**
 * This interface defines a contract for classes that provide
 * file choosing functionality.
 */

public interface FileChooser {

    /**
     * Opens a file chooser dialog for the user to select a file.
     * @param callBack The callback to handle the chosen file path.
     */

    void chooseFile(FileChooserCallBack callBack);
}
