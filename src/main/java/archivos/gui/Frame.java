package archivos.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private Logger logger = LogManager.getRootLogger();
    private Panel panel;

    public Frame() {
        this.setTitle("Practico6 - Sistemas de Archivos");
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new Panel();
        añadirPanelPrincipal();
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        logger.info("Se inicia las cracteristicas del Frame");
    }

    private void añadirPanelPrincipal() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.revalidate();
        logger.info("Se agrega el panel al Frame");
        repaint();
    }
}
