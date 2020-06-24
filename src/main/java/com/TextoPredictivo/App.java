package com.TextoPredictivo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Clase App es la encargada de cargar la interfaz gráfica con todos los componentes
 * necesarios para el usuario y así pueda hacer la búsqueda de las palabras correspondientes
 * al resultado que arroje el texto predictivo.
 */
public class App {
    private static final int MAX_ITEM_DISPLAY = 20;
    private JTextField txtInput;
    private JPanel panelMain;
    private JButton btnSearch;
    private JScrollPane scrollListPredictive;
    private JList listPredictiveText;
    private JComboBox cbxSearchMethod;

    public App() {

        prepareGUI();

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = (listPredictiveText.getSelectedIndex() == -1) ? txtInput.getText() : listPredictiveText.getSelectedValue().toString();
                if (key.equals("") == false) {
                    DictionaryList dictionaryList = new DictionaryList();

                    if (cbxSearchMethod.getSelectedIndex() == 1) {
                        dictionaryList.setDictionary(AscensoALaColina.getInstance());
                    } else {
                        dictionaryList.setDictionary(DFS.getInstance());
                    }

                    //Nos permite almacenar en nombre del método utilizado para la búsqueda de la palabra.
                    String metodoUtilizado = cbxSearchMethod.getSelectedItem().toString();

                    long TiempoDeInicio = System.nanoTime();
                    boolean verificarExistencia = dictionaryList.contains(key);
                    long tiempoFinal = System.nanoTime();
                    long prueba = tiempoFinal - TiempoDeInicio;

                    /**
                     *  si la palabra buscada existe entonces se mostrará el nombre del método utilizado para
                    *   su busqueda con la búsqueda que la encontro
                    */
                    if (verificarExistencia) {
                        JOptionPane.showMessageDialog(null, key + " existe en la busqueda " + metodoUtilizado + " en un tiempo de: " + prueba + " ns");
                    } else {
                        JOptionPane.showMessageDialog(null, key + " no existe dentro de la búsqueda " + metodoUtilizado + " buscado en un tiempo:  " + prueba + " ns");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Entrada no nula");
                }
            }
        });

        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if (txtInput.getText().equals("") == false) {
                    listPredictiveText.setEnabled(true);
                    List<String> list = DFS.getInstance().query(txtInput.getText().toLowerCase());
                    int maxItemDisplay = list.size() > MAX_ITEM_DISPLAY ? MAX_ITEM_DISPLAY : list.size();
                    listPredictiveText.setListData(list.subList(0, maxItemDisplay).toArray());
                } else {
                    listPredictiveText.setEnabled(false);
                    listPredictiveText.setListData(new String[]{""});
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Texto Predictivo");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
    }

    private void prepareGUI() {
        listPredictiveText.setEnabled(false);
        listPredictiveText.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPredictiveText.setLayoutOrientation(JList.VERTICAL);

        cbxSearchMethod.addItem("DFS");
        cbxSearchMethod.addItem("Ascenso a la colina");

        scrollListPredictive.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollListPredictive.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            DataReader.read(System.getProperty("user.dir") + "/palabrasDiccionarios");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}