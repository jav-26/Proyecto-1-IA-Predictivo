
package Formularios;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author javie
 */
public class FormularioPrincipal extends javax.swing.JFrame {
    private final static String [] COLORS = new String [] {"red", "orange", "yellow", "green", "cyan", "blue", "violet", "brown", "rojo", "carro"};
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        lblIngresar = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("frameP");
        setName("frameP"); // NOI18N

        lblIngresar.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        lblIngresar.setText("Ingrese una palabra");

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIngresar)
                    .addComponent(jTextField1))
                .addGap(58, 58, 58))
        );

        jTextField1.getAccessibleContext().setAccessibleName("txtField");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("frameP");
        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public FormularioPrincipal(){
        initComponents();
    }
    public void Inicializacion ()
    {
        jTextField1.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate (DocumentEvent e)
            {
                // Do nothing
            }

            @Override
            public void insertUpdate (DocumentEvent e)
            {
                if (e.getOffset() + e.getLength() == e.getDocument().getLength())
                    SwingUtilities.invokeLater(new Runnable()
                    {
                        @Override
                        public void run ()
                        {
                            predict(jTextField1);
                        }
                    });
            }

            @Override
            public void changedUpdate (DocumentEvent e)
            {
                // Do nothing
            }
        });

        /*JFrame frame = new JFrame ("Auto complete");
        Container contentPane = frame.getContentPane ();
        contentPane.setLayout (new BorderLayout ());
        contentPane.add (jTextField1, BorderLayout.CENTER);
        frame.pack ();
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setVisible (true);*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioPrincipal().setVisible(true);
            }
        });
    }

    private static void predict (JTextField field)
    {
        String text = field.getText ();

        String prediction = null;

        for (String color: COLORS)
        {
            if (color.startsWith (text) && !color.equals (text))
            {
                if (prediction != null) return;

                prediction = color;
            }
        }

        if (prediction != null)
        {
            field.setText (prediction);

            field.setCaretPosition (text.length ());
            field.select (text.length (), prediction.length ());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblIngresar;
    // End of variables declaration//GEN-END:variables
}
