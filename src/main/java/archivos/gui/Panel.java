package archivos.gui;

import arbol.Lista;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Panel extends JPanel {

    private Logger logger = LogManager.getRootLogger();

    private JButton btnArchivo;
    private JButton btnCarpeta;
    private JLabel carpetaActual;
    private JLabel tipo;
    private JLabel tamanio;
    private JLabel nombreFisico;
    private JTextField txtTipo;
    private JTextField txtTamanio;
    private JTextField txtNombreFisico;
    private DefaultTableModel modelotabla;
    private JTable tabla;
    private JScrollPane scroll;
    private Lista<Object> columna;
    Configuracion co;

    public Panel() {
        cargarComponentes();
        this.setVisible(true);
        logger.info("Se cargan los componentes del panel");
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(530, 630);
    }

    private void cargarComponentes() {
        tabla = new JTable(modelotabla);
        scroll = new JScrollPane(tabla);
        columna = new Lista<>();
        co = new Configuracion();
        this.add(scroll);
        scroll.setVisible(false);
        this.setLayout(null);
        btnArchivo = new JButton("Subir Archivo");
        btnArchivo.setBounds(5, 5, 120, 40);
        btnCarpeta = new JButton("Crear Carpeta");
        btnCarpeta.setBounds(125, 5, 120, 40);
        this.add(btnArchivo);
        this.add(btnCarpeta);
        tipo = new JLabel("Tipo");
        tipo.setBounds(20, 50, 60, 30);
        tamanio = new JLabel("Tamaño");
        tamanio.setBounds(150, 50, 60, 30);
        nombreFisico = new JLabel("Nombre Fisico");
        nombreFisico.setBounds(300, 50, 100, 30);
        this.add(tipo);
        this.add(tamanio);
        this.add(nombreFisico);
        txtTipo = new JTextField();
        txtTipo.setBounds(20, 80, 60, 30);
        txtTamanio = new JTextField();
        txtTamanio.setBounds(150, 80, 60, 30);
        txtNombreFisico = new JTextField();
        txtNombreFisico.setBounds(300, 80, 100, 30);
        this.add(txtTipo);
        this.add(txtTamanio);
        this.add(txtNombreFisico);
        carpetaActual = new JLabel();
        carpetaActual.setBounds(5, 130, 450, 30);
        carpetaActual.setText("Carpeta Actual: " + co.getDirectorioBase());
        this.add(carpetaActual);
        iniciarTabla();
        cargarTabla();
    }

    private void iniciarTabla() {
        this.remove(scroll);
        scroll.setVisible(true);
        columna.adicionar("Nombre");
        columna.adicionar("Tipo");
        columna.adicionar("Tamaño");
        columna.adicionar("Nombre Fisico");
        modelotabla = new DefaultTableModel(generarTitulos(), 0);
        logger.info("Se inicia la tabla con las columnas: " + generarTitulos());
        btnArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btnArchivo)) {
                    mostrarDatosEnTabla();
                }
            }
        });
    }

    private void mostrarDatosEnTabla() {
        modelotabla.addRow(generarDatosMostrados());
    }

    private Object[] generarDatosMostrados() {
        Lista<Object> aux = new Lista<>();
        String auxNombreRandom = generateRandomFileName();
        //subirArchivo(auxNombreRandom+"."+txtTipo.getText());
        subirArchivo(auxNombreRandom);
        aux.adicionar(auxNombreRandom);
        aux.adicionar(txtTipo.getText());
        aux.adicionar(txtTamanio.getText());
        aux.adicionar(txtNombreFisico.getText());
        Object[] array = new Object[aux.tamano()];
        for (int i = 0; i < aux.tamano(); i++) {
            array[i] = aux.obtener(i);
        }
        return array;
    }

    private Object[] generarTitulos() {
        Object[] array = new Object[columna.tamano()];
        for (int i = 0; i < columna.tamano(); i++) {
            array[i] = columna.obtener(i);
        }
        return array;
    }

    private void cargarTabla() {
        tabla = new JTable(modelotabla);
        scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(15, 160, 500, 450);
        this.add(scroll);
    }

    public String generateRandomFileName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append((char) (Math.random() * 26 + 'a'));
        }
        logger.info("Se genera un nombre Aleatorio para el archivo: " + sb);
        return sb.toString();
    }

    private void crearCarpeta() {

    }

    private void subirArchivo(String Archivo) {
        String crear = co.getDirectorioBase() + Archivo;
        File archivo;
        archivo = new File(crear);
        try {
            archivo.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
