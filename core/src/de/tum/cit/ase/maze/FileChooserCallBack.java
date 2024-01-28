package de.tum.cit.ase.maze;

/**
 * This interface defines methods to handle file
 * choosing events, such as when a file is chosen, cancelled, or an error occurs.
 */

public interface FileChooserCallBack {

    /**
     * Called when a file is chosen by the user.
     * @param filePath The absolute path of the chosen file.
     */

    void onFileChosen(String filePath);
}
