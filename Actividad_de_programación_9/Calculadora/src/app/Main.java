package src.app; // PAQUETE: src.app

/**
 * @author Cesar de Jesus Becerra Vera
 * @since 09 de Octubre de 2025
 * @version 2.0
 * PAQUETE: src.app
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Interfaz para la calculadora.
 */

import javax.swing.*; // Importar librerías Swing para GUI
import java.awt.*; // Importar librerías AWT para diseño
import java.awt.event.ActionEvent; // Importar librerías para realizar acciones
import java.awt.event.ActionListener; // Importar librerías para leer eventos
import src.app.Back_calculadora_basica; // Importar la lógica de la calculadora

/**
 * Clase principal que crea la interfaz gráfica de la calculadora.
 */
public class Main extends JFrame implements ActionListener {
    
    private Back_calculadora_basica calculadora; // Lógica de la calculadora
    private JTextField display; // Campo de texto para mostrar resultados
    
    // Variables para los colores de la interfaz
    private final Color DARK_BG = new Color(45, 45, 48);
    private final Color DISPLAY_BG = new Color(32, 32, 35);
    private final Color CLEAR_BUTTON = new Color(220, 53, 69);
    private final Color FUNCTION_BUTTON = new Color(108, 117, 125);
    private final Color NUMBER_BUTTON = new Color(73, 80, 87);
    private final Color OPERATOR_BUTTON = new Color(255, 193, 7);
    private final Color EQUALS_BUTTON = new Color(40, 167, 69);
    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_BLACK = Color.BLACK;
    
    /**
     * Constructor que inicializa la interfaz gráfica.
     */
    public Main() {
        calculadora = new Back_calculadora_basica(); // Instancia de la lógica de la calculadora
        initializeGUI();
    }
    
    /**
     * Inicializa los componentes de la interfaz gráfica. (Titulo, tamaño, colores, disposición, etc.)
     */
    private void initializeGUI() {
        setTitle("Calculadora Basica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(DARK_BG);
        setLayout(new BorderLayout());
        createDisplay();
        createButtonPanel();
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Crea el campo de texto para mostrar resultados. (Estilo, tamaño, color, alineación, etc.)
     */
    private void createDisplay() {
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(DISPLAY_BG);
        display.setForeground(TEXT_WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        display.setPreferredSize(new Dimension(350, 80));
        add(display, BorderLayout.NORTH);
    }
    
    /**
     * Crea el panel de botones con sus respectivos estilos y acciones. (Diseño, colores, tamaño, etc.)
     * Tabla de botones
     */
    private void createButtonPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 4, 3, 3));
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Fila 1: C, ±, 9, ÷
        mainPanel.add(createButton("C", CLEAR_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("±", FUNCTION_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("9", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("÷", OPERATOR_BUTTON, TEXT_BLACK));
        
        // Fila 2: 7, 8, 6, ×
        mainPanel.add(createButton("7", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("8", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("6", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("×", OPERATOR_BUTTON, TEXT_BLACK));
        
        // Fila 3: 4, 5, 3, -
        mainPanel.add(createButton("4", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("5", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("3", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("-", OPERATOR_BUTTON, TEXT_BLACK));
        
        // Fila 4: 1, 2, 0, +
        mainPanel.add(createButton("1", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("2", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("0", NUMBER_BUTTON, TEXT_WHITE));
        mainPanel.add(createButton("+", OPERATOR_BUTTON, TEXT_BLACK));

        // Agregar el panel principal a la ventana
        add(mainPanel, BorderLayout.CENTER);

        // Panel inferior para punto decimal e igual
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 3, 3));
        bottomPanel.setBackground(DARK_BG);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        bottomPanel.add(createButton(".", NUMBER_BUTTON, TEXT_WHITE));
        bottomPanel.add(createButton("=", EQUALS_BUTTON, TEXT_WHITE));
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Crea un botón con estilo personalizado.
     * @param text Texto del botón.
     * @param bgColor Color de fondo del botón.
     * @param textColor Color del texto del botón.
     * @return El botón creado.
     */
    private JButton createButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(80, 60));
        button.addActionListener(this);
        
        // Efecto hover (es lo que hace que cambie de color al pasar el mouse)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color originalColor = bgColor;
            
            /**
             * Efecto hover (es lo que hace que cambie de color al pasar el mouse)
             * @param evt Evento del mouse
             */
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            /**
             * Efecto hover (es lo que hace que cambie de color al pasar el mouse)
             * @param evt Evento del mouse
             */
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }
        });
        return button;
    }
    
    /**
     * Maneja los eventos de los botones.
     * @param e Evento de acción.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        try { // Manejar las acciones según el botón presionado
            switch (command) { // Dependiendo del botón presionado, llamar a la función correspondiente
                case "0": case "1": case "2": case "3": case "4":
                case "5": case "6": case "7": case "8": case "9":
                    calculadora.appendDigit(command); // Agregar dígito
                    break;
                    
                case ".": // Agregar punto decimal
                    calculadora.appendDigit(".");
                    break;
                    
                case "+": // Operación suma
                    calculadora.setOperation("+");
                    break;
                    
                case "-": // Operación resta
                    calculadora.setOperation("-");
                    break;
                    
                case "×": // Operación multiplicación
                    calculadora.setOperation("*");
                    break;
                    
                case "÷": // Operación división
                    calculadora.setOperation("/");
                    break;
                    
                case "=": // Calcular resultado
                    calculadora.calculate();
                    break;
                    
                case "C": // Limpiar todo
                    calculadora.clear();
                    break;
                    
                case "±": // Cambiar signo
                    calculadora.changeSign();
                    break;
                    
                default: // Si el comando no es reconocido, no hacer nada
                    return;
            }
            
            updateDisplay(); // Actualizar el display después de cada acción
            
        } catch (Exception ex) { // Manejar cualquier excepción inesperada
            display.setText("Error");
            display.setForeground(Color.RED);
        }
    }
    
    /**
     * Actualiza el texto del display y su color según el estado de la calculadora.
     */
    private void updateDisplay() {
        String displayText = calculadora.getDisplayText();
        display.setText(displayText);
        
        if (calculadora.hasError()) { // Si hay un error, mostrar el texto en rojo
            display.setForeground(Color.RED);
        } else { // Si no hay error, ajustar el color según el estado
            if (calculadora.getCurrentOperation() != null && calculadora.isNewInput()) { // Si hay una operación pendiente, mostrar el texto en color más tenue
                display.setForeground(new Color(200, 200, 200)); // Gris claro
            } else { // Si no, mostrar en blanco
                display.setForeground(TEXT_WHITE);
            }
        }
        
        if (displayText.length() > 10) { // Ajustar el tamaño de fuente si el texto es muy largo
            display.setFont(new Font("Arial", Font.BOLD, 20));
        } else { // Si no, usar tamaño normal
            display.setFont(new Font("Arial", Font.BOLD, 28));
        }
    }
    
    /**
     * Método principal para iniciar la aplicación.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        try { // Configurar el Look and Feel oscuro
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            // Usar el look and feel por defecto
        }
        
        SwingUtilities.invokeLater(() -> { // Iniciar la aplicación en el hilo de eventos de Swing
            new Main().setVisible(true);
        });
    }
}
