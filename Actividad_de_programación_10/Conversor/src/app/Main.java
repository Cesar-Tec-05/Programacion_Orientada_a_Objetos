/**
 * @author Cesar de Jesus Becerra Vera
 * @since 19 de Octubre de 2025
 * @version 1.0
 * PAQUETE: src.app
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 10 - Conversor de sistemas numéricos: decimal, binario, octal y hexadecimal.
 */

package src.app; // Paquete principal

// Importaciones necesarias
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.*;
import src.app.Conversor_unidiades_back;

@SuppressWarnings("unused") // Ignorar advertencias de variables no usadas

/**
 * Clase principal que implementa la interfaz gráfica del conversor de sistemas numéricos.
 */
public class Main extends JFrame {
    
    private Conversor_unidiades_back conversor; // Lógica del conversor de las importaciones
    
    // Componentes de la interfaz
    private JTextField txtValor;
    private JComboBox<String> cmbOrigen;
    private JComboBox<String> cmbDestino;
    private JTextArea txtResultado;
    private JScrollPane scrollResultado;
    private JButton btnConvertir;
    private JButton btnLimpiar;
    private JButton btnIntercambiar;
    
    // Variables para recordar selecciones previas
    private int ultimoIndiceOrigen = 0;
    private int ultimoIndiceDestino = 1;
    
    // Colores personalizados
    private final Color COLOR_FONDO = new Color(30, 30, 30);
    private final Color COLOR_FONDO_SECUNDARIO = new Color(40, 40, 40);
    private final Color COLOR_FONDO_INPUT = new Color(50, 50, 50);
    private final Color COLOR_PRIMARIO = new Color(100, 150, 255);
    private final Color COLOR_SECUNDARIO = new Color(80, 200, 120);
    private final Color COLOR_TEXTO = new Color(230, 230, 230);
    private final Color COLOR_TEXTO_SECUNDARIO = new Color(180, 180, 180);
    private final Color COLOR_BORDE = new Color(70, 70, 70);
    
    /**
     * Constructor que inicializa la interfaz gráfica.
     */
    public Main() {
        conversor = new Conversor_unidiades_back(); // Instancia del conversor
        inicializarComponentes(); // Inicializar componentes
        configurarVentana(); // Configurar ventana
    }
    
    /**
     * Inicializa y configura todos los componentes de la interfaz gráfica.
     */
    private void inicializarComponentes() {
        // Panel principal con padding
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(20, 20));
        panelPrincipal.setBackground(COLOR_FONDO);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // Título
        JLabel lblTitulo = new JLabel("Conversor de Sistemas Numéricos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(COLOR_TEXTO);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        
        // Panel central con los controles
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridBagLayout());
        panelCentral.setBackground(COLOR_FONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Campo de entrada
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weighty = 0.0; // No expandir verticalmente
        txtValor = crearTextField();
        txtValor.setFont(new Font("Consolas", Font.BOLD, 20));
        txtValor.setPreferredSize(new Dimension(450, 60));
        txtValor.setMinimumSize(new Dimension(450, 60));
        txtValor.setMaximumSize(new Dimension(450, 60));
        panelCentral.add(txtValor, gbc);
        
        // ComboBox de origen
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; // No expandir verticalmente
        String[] sistemas = {"Decimal (10)", "Binario (2)", "Octal (8)", "Hexadecimal (16)"};
        cmbOrigen = crearComboBox(sistemas);
        cmbOrigen.addActionListener(event -> validarSeleccionDiferente());
        panelCentral.add(cmbOrigen, gbc);
        
        // Botón intercambiar
        gbc.gridx = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        btnIntercambiar = crearBotonIcono("⇄");
        btnIntercambiar.addActionListener(event -> intercambiarSistemas());
        panelCentral.add(btnIntercambiar, gbc);
        
        // ComboBox de destino
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        cmbDestino = crearComboBox(sistemas);
        cmbDestino.setSelectedIndex(1); // Por defecto a Binario
        cmbDestino.addActionListener(event -> validarSeleccionDiferente());
        panelCentral.add(cmbDestino, gbc);
        
        // Panel de resultado
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0; // Este puede expandirse
        gbc.fill = GridBagConstraints.BOTH; // Expandir en ambas direcciones
        JPanel panelResultado = crearPanelResultado();
        panelCentral.add(panelResultado, gbc);
        
        panelPrincipal.add(panelCentral, BorderLayout.CENTER); // Agregar panel central
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(COLOR_FONDO);
        
        // Botones
        btnConvertir = crearBoton("Convertir", COLOR_PRIMARIO);
        btnConvertir.addActionListener(event -> convertir());
        
        // Botón limpiar
        btnLimpiar = crearBoton("Limpiar", COLOR_SECUNDARIO);
        btnLimpiar.addActionListener(event -> limpiar());
        
        // Agregar botones al panel
        panelBotones.add(btnConvertir);
        panelBotones.add(btnLimpiar);
        
        // Agregar panel de botones al principal
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        // Agregar panel principal a la ventana
        add(panelPrincipal);
        
        // Enter para convertir
        txtValor.addActionListener(event -> convertir());
    }
    
    /**
     * Crea y configura un JTextField personalizado.
     * @return JTextField configurado para entrada de datos
     */
    private JTextField crearTextField() {
        JTextField textField = new JTextField(25);
        textField.setFont(new Font("Consolas", Font.BOLD, 20));
        textField.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(12),
            BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        textField.setBackground(COLOR_FONDO_INPUT);
        textField.setForeground(COLOR_TEXTO);
        textField.setCaretColor(COLOR_PRIMARIO);
        textField.setHorizontalAlignment(JTextField.LEFT);
        return textField;
    }
    
    /**
     * Crea y configura un JComboBox personalizado.
     * @param items Elementos a incluir en el JComboBox
     * @return JComboBox configurado
     */
    private JComboBox<String> crearComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboBox.setBackground(COLOR_FONDO_INPUT);
        comboBox.setForeground(COLOR_TEXTO);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(12),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        comboBox.setPreferredSize(new Dimension(180, 50));
        
        // Personalizar el renderizador
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
                label.setBackground(isSelected ? COLOR_PRIMARIO : COLOR_FONDO_INPUT);
                label.setForeground(COLOR_TEXTO);
                label.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
                return label;
            }
        });
        return comboBox;
    }

    /**
     * Crea y configura un JButton personalizado.
     * @param texto Texto del botón
     * @param color Color de fondo del botón
     * @return JButton configurado
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        boton.setForeground(COLOR_TEXTO);
        boton.setBackground(color);
        boton.setBorder(new RoundedBorder(12));
        boton.setPreferredSize(new Dimension(140, 50));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efectos hover
        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(color.brighter());
            }
            public void mouseExited(MouseEvent e) {
                boton.setBackground(color);
            }
        });
        return boton;
    }

    /**
     * Crea y configura un JButton con un ícono.
     * @param icono Ruta del ícono a mostrar en el botón
     * @return JButton configurado con ícono
     */
    private JButton crearBotonIcono(String icono) {
        JButton boton = new JButton(icono);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        boton.setForeground(COLOR_PRIMARIO);
        boton.setBackground(COLOR_FONDO_INPUT);
        boton.setBorder(new RoundedBorder(12));
        boton.setPreferredSize(new Dimension(60, 50));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(COLOR_FONDO_SECUNDARIO);
                boton.setForeground(COLOR_PRIMARIO.brighter());
            }
            public void mouseExited(MouseEvent e) {
                boton.setBackground(COLOR_FONDO_INPUT);
                boton.setForeground(COLOR_PRIMARIO);
            }
        });
        
        return boton;
    }

    /**
     * Crea y configura un JPanel para mostrar los resultados.
     * @return JPanel configurado para mostrar resultados
     */
    private JPanel crearPanelResultado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_INPUT);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(12),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        // TextArea para resultados que se adapta automáticamente
        txtResultado = new JTextArea("El resultado aparecerá aquí");
        txtResultado.setFont(new Font("Consolas", Font.BOLD, 18));
        txtResultado.setForeground(COLOR_TEXTO_SECUNDARIO);
        txtResultado.setBackground(COLOR_FONDO_INPUT);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(false); // Para números, no romper palabras
        txtResultado.setEditable(false);
        txtResultado.setMargin(new Insets(15, 20, 15, 20));
        txtResultado.setBorder(null);
        txtResultado.setRows(3); // Permitir múltiples líneas
        
        // ScrollPane para cuando el texto sea largo
        scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setBorder(null);
        scrollResultado.setBackground(COLOR_FONDO_INPUT);
        scrollResultado.getViewport().setBackground(COLOR_FONDO_INPUT);
        scrollResultado.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollResultado.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollResultado.setPreferredSize(new Dimension(450, 100));
        
        // Personalizar scrollbar
        scrollResultado.getVerticalScrollBar().setBackground(COLOR_FONDO_INPUT);
        scrollResultado.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() { // Personalización del scrollbar

            @Override
            protected void configureScrollBarColors() { // Colores personalizados
                this.thumbColor = COLOR_BORDE;
                this.trackColor = COLOR_FONDO_INPUT;
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) { // Botones invisibles
                return createZeroButton();
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) { // Botones invisibles
                return createZeroButton();
            }
            
            private JButton createZeroButton() { // Botón de tamaño cero
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });
        
        panel.add(scrollResultado, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Obtiene la base numérica según la selección del usuario.
     * @param seleccion Selección del usuario en el JComboBox
     * @return Base numérica correspondiente a la selección
     */
    private int obtenerBase(String seleccion) {
        if (seleccion.contains("Decimal")) return 10;
        if (seleccion.contains("Binario")) return 2;
        if (seleccion.contains("Octal")) return 8;
        if (seleccion.contains("Hexadecimal")) return 16;
        return 10;
    }
    
    /**
     * Obtiene la longitud máxima permitida para un número en una base específica.
     * @param base Base numérica
     * @return Longitud máxima permitida
     */
    private int obtenerLongitudMaxima(int base) {
        switch (base) {
            case 2:  return 63;  // Binario: 63 bits
            case 8:  return 21;  // Octal: ~21 dígitos
            case 10: return 18;  // Decimal: 18 dígitos
            case 16: return 15;  // Hexadecimal: ~15 dígitos
            default: return 15;  // Por defecto
        }
    }

    /**
     * Convierte el valor ingresado en el campo de texto de una base a otra.
     */
    private void convertir() {
        String valor = txtValor.getText().trim().toUpperCase(); // Convertir a mayúsculas para hexadecimal
        
        if (valor.isEmpty()) { // Validar campo vacío
            mostrarError("Por favor ingrese un valor");
            txtValor.requestFocus();
            return;
        }
        
        int baseOrigen = obtenerBase((String) cmbOrigen.getSelectedItem());
        int baseDestino = obtenerBase((String) cmbDestino.getSelectedItem());
        
        // Validar longitud del número primero
        int longitudMaxima = obtenerLongitudMaxima(baseOrigen);
        if (valor.length() > longitudMaxima) {
            mostrarError("Número demasiado largo. Máximo " + longitudMaxima + " dígitos en base " + baseOrigen);
            txtValor.requestFocus();
            return;
        }
        
        // Validar el número según su base
        if (!conversor.validarNumeroEnBase(valor, baseOrigen)) {
            mostrarError("Número no válido para la base " + baseOrigen);
            txtValor.requestFocus();
            return;
        }
        
        // Realizar la conversión
        if (conversor.convertirSistemaNumericos(valor, baseOrigen, baseDestino)) {
            // Mostrar resultado con formato mejorado
            String resultado = conversor.getConversion();
            txtResultado.setText(resultado);
            txtResultado.setForeground(COLOR_PRIMARIO);
            txtResultado.setCaretPosition(0); // Scroll al inicio
            
            // Ajustar el tamaño del panel según el contenido real del TextArea
            txtResultado.setSize(new Dimension(450, Integer.MAX_VALUE));
            int alturaTexto = txtResultado.getPreferredSize().height;
            int altura = Math.max(100, Math.min(250, alturaTexto + 40));
            
            scrollResultado.setPreferredSize(new Dimension(450, altura));
            scrollResultado.setMinimumSize(new Dimension(450, altura));
            scrollResultado.setMaximumSize(new Dimension(450, 250));
            scrollResultado.getParent().revalidate();
            scrollResultado.getParent().repaint();
            
            // Mantener el foco en el campo de entrada para continuar escribiendo
            txtValor.requestFocus();
        } else {
            mostrarError(conversor.getError());
            txtValor.requestFocus();
        }
    }

    /**
     * Valida que las selecciones de origen y destino sean diferentes.
     */
    private void validarSeleccionDiferente() {
        int origenIndex = cmbOrigen.getSelectedIndex();
        int destinoIndex = cmbDestino.getSelectedIndex();
        
        // Si son iguales, restaurar la selección anterior
        if (origenIndex == destinoIndex) {
            // Identificar cuál combo fue el que cambió y revertirlo
            if (origenIndex != ultimoIndiceOrigen) {
                // Se cambió el origen, revertirlo
                cmbOrigen.removeActionListener(cmbOrigen.getActionListeners()[0]);
                cmbOrigen.setSelectedIndex(ultimoIndiceOrigen);
                cmbOrigen.addActionListener(event -> validarSeleccionDiferente());
            } else if (destinoIndex != ultimoIndiceDestino) {
                // Se cambió el destino, revertirlo
                cmbDestino.removeActionListener(cmbDestino.getActionListeners()[0]);
                cmbDestino.setSelectedIndex(ultimoIndiceDestino);
                cmbDestino.addActionListener(event -> validarSeleccionDiferente());
            }
        } else {
            // Si son diferentes, actualizar los últimos índices válidos
            ultimoIndiceOrigen = origenIndex;
            ultimoIndiceDestino = destinoIndex;
        }
    }

    /**
     * Limpia los campos de entrada y salida.
     */
    private void limpiar() {
        txtValor.setText("");
        txtResultado.setText("El resultado aparecerá aquí");
        txtResultado.setForeground(COLOR_TEXTO_SECUNDARIO);
        scrollResultado.setPreferredSize(new Dimension(450, 100));
        cmbOrigen.setSelectedIndex(0);
        cmbDestino.setSelectedIndex(1);
        ultimoIndiceOrigen = 0;
        ultimoIndiceDestino = 1;
        conversor.clear();
        txtValor.requestFocus();
    }
    
    /**
     * Intercambia las selecciones de los sistemas numéricos de origen y destino.
     */
    private void intercambiarSistemas() {
        // Temporalmente remover los listeners para evitar validación durante el intercambio
        ActionListener[] origenListeners = cmbOrigen.getActionListeners();
        ActionListener[] destinoListeners = cmbDestino.getActionListeners();
        
        for (ActionListener listener : origenListeners) {
            cmbOrigen.removeActionListener(listener);
        }
        for (ActionListener listener : destinoListeners) {
            cmbDestino.removeActionListener(listener);
        }
        
        // Realizar el intercambio
        int tempIndex = cmbOrigen.getSelectedIndex();
        cmbOrigen.setSelectedIndex(cmbDestino.getSelectedIndex());
        cmbDestino.setSelectedIndex(tempIndex);
        
        // Actualizar los últimos índices válidos después del intercambio
        ultimoIndiceOrigen = cmbOrigen.getSelectedIndex();
        ultimoIndiceDestino = cmbDestino.getSelectedIndex();
        
        // Restaurar los listeners
        for (ActionListener listener : origenListeners) {
            cmbOrigen.addActionListener(listener);
        }
        for (ActionListener listener : destinoListeners) {
            cmbDestino.addActionListener(listener);
        }
        
        // Mantener el foco en el campo de entrada
        txtValor.requestFocus();
    }
    
    /**
     * Muestra un mensaje de error en el área de resultados.
     * @param mensaje Mensaje de error a mostrar
     */
    private void mostrarError(String mensaje) {
        txtResultado.setText("❌ " + mensaje);
        txtResultado.setForeground(new Color(255, 100, 100));
        
        // Ajustar tamaño también para errores
        txtResultado.setSize(new Dimension(450, Integer.MAX_VALUE));
        int alturaTexto = txtResultado.getPreferredSize().height;
        int altura = Math.max(100, Math.min(250, alturaTexto + 40));
        
        scrollResultado.setPreferredSize(new Dimension(450, altura));
        scrollResultado.setMinimumSize(new Dimension(450, altura));
        scrollResultado.getParent().revalidate();
        scrollResultado.getParent().repaint();
    }
    
    /**
     * Configura las propiedades de la ventana principal.
     */
    private void configurarVentana() {
        setTitle("Conversor de Sistemas Numéricos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(COLOR_FONDO);
        
        // Icono de la aplicación (opcional)
        try {
            setIconImage(Toolkit.getDefaultToolkit().createImage(""));
        } catch (Exception e) {
            // Si no hay icono, continuar sin él
        }
    }
    
    /**
     * Método principal para iniciar la aplicación.
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Establecer Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Crear y mostrar la ventana en el hilo de eventos
        SwingUtilities.invokeLater(() -> {
            Main ventana = new Main();
            ventana.setVisible(true);
        });
    }
    
    // Clase interna para bordes redondeados
    class RoundedBorder extends AbstractBorder {
        private int radius;
        
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        
        // Dibuja el borde redondeado
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COLOR_BORDE);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        // Devuelve los márgenes del borde
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius / 2, this.radius / 2, this.radius / 2, this.radius / 2);
        }

        // Devuelve los márgenes del borde
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = this.radius / 2;
            return insets;
        }
    }
}
