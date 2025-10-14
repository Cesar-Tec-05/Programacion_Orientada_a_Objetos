package src.app; // PAQUETE: src.app

/**
 * @author Cesar de Jesus Becerra Vera
 * @since 14 de Octubre de 2025
 * @version 2.0
 * PAQUETE: src.app
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 9 - Calculadora básica
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
    private JTextField pantalla; // Campo de texto para mostrar resultados
    
    // Variables para los colores de la interfaz
    private final Color FONDO_OSCURO = new Color(45, 45, 48);
    private final Color FONDO_PANTALLA = new Color(32, 32, 35);
    private final Color BOTON_LIMPIAR = new Color(220, 53, 69);
    private final Color BOTON_FUNCION = new Color(108, 117, 125);
    private final Color BOTON_NUMERO = new Color(73, 80, 87);
    private final Color BOTON_OPERADOR = new Color(255, 193, 7);
    private final Color BOTON_IGUAL = new Color(40, 167, 69);
    private final Color TEXTO_BLANCO = Color.WHITE;
    private final Color TEXTO_NEGRO = Color.BLACK;
    
    /**
     * Constructor que inicializa la interfaz gráfica.
     */
    public Main() {
        calculadora = new Back_calculadora_basica(); // Instancia de la lógica de la calculadora
        inicializarInterfaz();
    }
    
    /**
     * Inicializa los componentes de la interfaz gráfica. (Titulo, tamaño, colores, disposición, etc.)
     */
    public void inicializarInterfaz() {
        setTitle("Calculadora Basica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(FONDO_OSCURO);
        setLayout(new BorderLayout());
        crearPantalla();
        crearPanelBotones();
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Crea el campo de texto para mostrar resultados. (Estilo, tamaño, color, alineación, etc.)
     */
    public void crearPantalla() {
        pantalla = new JTextField("0");
        pantalla.setFont(new Font("Arial", Font.BOLD, 28));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);
        pantalla.setBackground(FONDO_PANTALLA);
        pantalla.setForeground(TEXTO_BLANCO);
        pantalla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pantalla.setPreferredSize(new Dimension(350, 80));
        add(pantalla, BorderLayout.NORTH);
    }
    
    /**
     * Crea el panel de botones con sus respectivos estilos y acciones. (Diseño, colores, tamaño, etc.)
     * Tabla de botones
     */
    public void crearPanelBotones() {
        JPanel panelPrincipal = new JPanel(new GridLayout(4, 4, 3, 3));
        panelPrincipal.setBackground(FONDO_OSCURO);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Fila 1: C, ±, 9, ÷
        panelPrincipal.add(crearBoton("C", BOTON_LIMPIAR, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("±", BOTON_FUNCION, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("9", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("÷", BOTON_OPERADOR, TEXTO_NEGRO));
        
        // Fila 2: 7, 8, 6, ×
        panelPrincipal.add(crearBoton("7", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("8", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("6", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("×", BOTON_OPERADOR, TEXTO_NEGRO));
        
        // Fila 3: 4, 5, 3, -
        panelPrincipal.add(crearBoton("4", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("5", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("3", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("-", BOTON_OPERADOR, TEXTO_NEGRO));
        
        // Fila 4: 1, 2, 0, +
        panelPrincipal.add(crearBoton("1", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("2", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("0", BOTON_NUMERO, TEXTO_BLANCO));
        panelPrincipal.add(crearBoton("+", BOTON_OPERADOR, TEXTO_NEGRO));

        // Agregar el panel principal a la ventana
        add(panelPrincipal, BorderLayout.CENTER);

        // Panel inferior para punto decimal e igual
        JPanel panelInferior = new JPanel(new GridLayout(1, 2, 3, 3));
        panelInferior.setBackground(FONDO_OSCURO);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        panelInferior.add(crearBoton(".", BOTON_NUMERO, TEXTO_BLANCO));
        panelInferior.add(crearBoton("=", BOTON_IGUAL, TEXTO_BLANCO));
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    /**
     * Crea un botón con estilo personalizado.
     * @param texto Texto del botón.
     * @param colorFondo Color de fondo del botón.
     * @param colorTexto Color del texto del botón.
     * @return El botón creado.
     */
    public JButton crearBoton(String texto, Color colorFondo, Color colorTexto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 18));
        boton.setBackground(colorFondo);
        boton.setForeground(colorTexto);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(80, 60));
        boton.addActionListener(this);
        
        // Efecto hover (es lo que hace que cambie de color al pasar el mouse)
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            Color colorOriginal = colorFondo;
            
            /**
             * Efecto hover (es lo que hace que cambie de color al pasar el mouse)
             * @param evt Evento del mouse
             */
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo.brighter());
            }

            /**
             * Efecto hover (es lo que hace que cambie de color al pasar el mouse)
             * @param evt Evento del mouse
             */
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorOriginal);
            }
        });
        return boton;
    }
    
    /**
     * Maneja los eventos de los botones.
     * @param e Evento de acción.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        try { // Manejar las acciones según el botón presionado
            switch (comando) { // Dependiendo del botón presionado, llamar a la función correspondiente
                case "0": case "1": case "2": case "3": case "4":
                case "5": case "6": case "7": case "8": case "9":
                    calculadora.agregarDigito(comando); // Agregar dígito
                    break;
                    
                case ".": // Agregar punto decimal
                    calculadora.agregarDigito(".");
                    break;
                    
                case "+": // Operación suma
                    calculadora.establecerOperacion("+");
                    break;
                    
                case "-": // Operación resta
                    calculadora.establecerOperacion("-");
                    break;
                    
                case "×": // Operación multiplicación
                    calculadora.establecerOperacion("*");
                    break;
                    
                case "÷": // Operación división
                    calculadora.establecerOperacion("/");
                    break;
                    
                case "=": // Calcular resultado
                    calculadora.calcular();
                    break;
                    
                case "C": // Limpiar todo
                    calculadora.limpiar();
                    break;
                    
                case "±": // Cambiar signo
                    calculadora.cambiarSigno();
                    break;
                    
                default: // Si el comando no es reconocido, no hacer nada
                    return;
            }
            
            actualizarPantalla(); // Actualizar el display después de cada acción
            
        } catch (Exception ex) { // Manejar cualquier excepción inesperada
            pantalla.setText("Error");
            pantalla.setForeground(Color.RED);
        }
    }
    
    /**
     * Actualiza el texto del display y su color según el estado de la calculadora.
     */
    public void actualizarPantalla() {
        String textoMostrar;
        
        if (calculadora.tieneError()) {
            textoMostrar = "Error";
        } else {
            // Usar el texto actual tal como está formateado en el backend
            textoMostrar = calculadora.obtenerTextoActual();
        }
        
        pantalla.setText(textoMostrar);
        
        if (calculadora.tieneError()) { // Si hay un error, mostrar el texto en rojo
            pantalla.setForeground(Color.RED);
        } else { // Si no hay error, ajustar el color según el estado
            if (calculadora.obtenerOperacionActual() != null && calculadora.esNuevaEntrada()) { // Si hay una operación pendiente, mostrar el texto en color más tenue
                pantalla.setForeground(new Color(200, 200, 200)); // Gris claro
            } else { // Si no, mostrar en blanco
                pantalla.setForeground(TEXTO_BLANCO);
            }
        }
        
        if (textoMostrar.length() > 10) { // Ajustar el tamaño de fuente si el texto es muy largo
            pantalla.setFont(new Font("Arial", Font.BOLD, 20));
        } else { // Si no, usar tamaño normal
            pantalla.setFont(new Font("Arial", Font.BOLD, 28));
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
