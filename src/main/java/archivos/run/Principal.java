package archivos.run;

import archivos.gui.Frame;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new Frame().setVisible(true);
    }
}
