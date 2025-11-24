package src.app; // PAQUETE PRINCIPAL

// IMPORTACIONES
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import src.back_calculadora.Back_calculadora_basica;

/**
 * Clase del panel de la calculadora básica
 */
public class PanelCalculadora extends JPanel {
    
    // Componentes de la calculadora
    private Back_calculadora_basica calculadora;
    private JTextField pantalla;
    private JLabel lblOperacion;
    private JButton[] botonesNumeros;
    private JButton btnSumar, btnRestar, btnMultiplicar, btnDividir;
    private JButton btnIgual, btnLimpiar, btnCambiarSigno, btnPunto;
    
    // Colores personalizados
    private static final Color BG_DARK = new Color(30, 30, 35);
    private static final Color PANEL_DARK = new Color(40, 40, 45);
    private static final Color BTN_NUMBER = new Color(60, 60, 65);
    private static final Color BTN_NUMBER_HOVER = new Color(70, 70, 75);
    private static final Color BTN_OPERATOR = new Color(100, 150, 255);
    private static final Color BTN_OPERATOR_HOVER = new Color(120, 170, 255);
    private static final Color BTN_EQUAL = new Color(80, 200, 120);
    private static final Color BTN_EQUAL_HOVER = new Color(100, 220, 140);
    private static final Color BTN_CLEAR = new Color(255, 100, 100);
    private static final Color BTN_CLEAR_HOVER = new Color(255, 120, 120);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static final Color DISPLAY_BG = new Color(25, 25, 30);
    private static final Color ACCENT_COLOR = new Color(100, 150, 255);
    
    /**
     * Constructor del panel de calculadora
     */
    public PanelCalculadora() {
        calculadora = new Back_calculadora_basica();
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(BG_DARK);
        
        inicializarComponentes();
    }
    
    /**
     * Metodo para inicializar los componentes de la calculadora
     */
    private void inicializarComponentes() {
        // Panel de la pantalla con indicador de operación
        JPanel panelDisplay = new JPanel();
        panelDisplay.setLayout(new BoxLayout(panelDisplay, BoxLayout.Y_AXIS));
        panelDisplay.setBackground(DISPLAY_BG);
        panelDisplay.setBorder(new CompoundBorder(
            new LineBorder(ACCENT_COLOR, 2, true),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // Label para mostrar la operación en curso
        lblOperacion = new JLabel(" ");
        lblOperacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblOperacion.setForeground(new Color(150, 150, 150));
        lblOperacion.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblOperacion.setHorizontalAlignment(JLabel.RIGHT);
        panelDisplay.add(lblOperacion);
        
        // Espaciador vertical entre la operación y la pantalla
        panelDisplay.add(Box.createVerticalStrut(5));
        
        // Pantalla principal
        pantalla = new JTextField("0");
        pantalla.setFont(new Font("Segoe UI", Font.BOLD, 36));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);
        pantalla.setBackground(DISPLAY_BG);
        pantalla.setForeground(TEXT_COLOR);
        pantalla.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pantalla.setCaretColor(TEXT_COLOR);
        panelDisplay.add(pantalla);
        
        // Agregar el panel de pantalla al norte del panel principal
        add(panelDisplay, BorderLayout.NORTH);
        
        // Panel de botones con diseño mejorado
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setBackground(BG_DARK);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        // Configuración de restricciones para GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        // Inicializar botones de números
        botonesNumeros = new JButton[10];
        for (int i = 0; i < 10; i++) {
            botonesNumeros[i] = crearBotonNumero(String.valueOf(i));
        }
        
        // Inicializar botones de operaciones
        btnLimpiar = crearBotonLimpiar();
        btnCambiarSigno = crearBotonCambiarSigno();
        btnDividir = crearBotonOperacion("/", "÷");
        btnMultiplicar = crearBotonOperacion("*", "×");
        btnRestar = crearBotonOperacion("-", "−");
        btnSumar = crearBotonOperacion("+", "+");
        btnIgual = crearBotonIgual();
        btnPunto = crearBotonPunto();
        
        // Fila 1: C, ±, 9, ÷
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        panelBotones.add(btnLimpiar, gbc);
        gbc.gridx = 1;
        panelBotones.add(btnCambiarSigno, gbc);
        gbc.gridx = 2;
        panelBotones.add(botonesNumeros[9], gbc);
        gbc.gridx = 3;
        panelBotones.add(btnDividir, gbc);
        
        // Fila 2: 7, 8, 6, ×
        gbc.gridx = 0; gbc.gridy = 1;
        panelBotones.add(botonesNumeros[7], gbc);
        gbc.gridx = 1;
        panelBotones.add(botonesNumeros[8], gbc);
        gbc.gridx = 2;
        panelBotones.add(botonesNumeros[6], gbc);
        gbc.gridx = 3;
        panelBotones.add(btnMultiplicar, gbc);
        
        // Fila 3: 4, 5, 3, −
        gbc.gridx = 0; gbc.gridy = 2;
        panelBotones.add(botonesNumeros[4], gbc);
        gbc.gridx = 1;
        panelBotones.add(botonesNumeros[5], gbc);
        gbc.gridx = 2;
        panelBotones.add(botonesNumeros[3], gbc);
        gbc.gridx = 3;
        panelBotones.add(btnRestar, gbc);
        
        // Fila 4: 1, 2, 0, +
        gbc.gridx = 0; gbc.gridy = 3;
        panelBotones.add(botonesNumeros[1], gbc);
        gbc.gridx = 1;
        panelBotones.add(botonesNumeros[2], gbc);
        gbc.gridx = 2;
        panelBotones.add(botonesNumeros[0], gbc);
        gbc.gridx = 3;
        panelBotones.add(btnSumar, gbc);
        
        // Fila 5: . (2 columnas) y = (2 columnas)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panelBotones.add(btnPunto, gbc);
        gbc.gridx = 2; gbc.gridwidth = 2;
        panelBotones.add(btnIgual, gbc);
        
        add(panelBotones, BorderLayout.CENTER);
    }
    
    /**
     * Metodo para crear un botón de número
     * @param numero Dígito del botón
     * @return JButton creado
     */
    private JButton crearBotonNumero(String numero) {
        JButton boton = crearBotonBase(numero, BTN_NUMBER, BTN_NUMBER_HOVER);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        boton.addActionListener(e -> { // Agregar dígito a la calculadora
            calculadora.agregarDigito(numero);
            actualizarPantalla();
        });
        return boton;
    }
    
    /**
     * Metodo para crear un botón de operación
     * @param operacion Símbolo de la operación
     * @param simbolo Texto a mostrar en el botón
     * @return JButton creado
     */
    private JButton crearBotonOperacion(String operacion, String simbolo) {
        JButton boton = crearBotonBase(simbolo, BTN_OPERATOR, BTN_OPERATOR_HOVER);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        boton.addActionListener(e -> { // Establecer operación en la calculadora
            calculadora.establecerOperacion(operacion);
            actualizarPantalla();
            resaltarOperacion(boton);
        });
        return boton;
    }
    
    /**
     * Metodo para crear un botón base con estilos modernos
     * @param texto Texto del botón
     * @param bg Color de fondo normal
     * @param hoverBg Color de fondo al pasar el mouse
     * @return JButton creado
     */
    private JButton crearBotonBase(String texto, Color bg, Color hoverBg) {
        JButton boton = new JButton(texto);
        boton.setForeground(TEXT_COLOR);
        boton.setBackground(bg);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(new LineBorder(bg.darker(), 1, true));
        
        // Efecto hover
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(hoverBg);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(bg);
            }
        });
        
        return boton;
    }
    
    /**
     * Metodo para resaltar el botón de operación activa
     * @param botonActivo Botón de la operación activa
     */
    private void resaltarOperacion(JButton botonActivo) {
        // Resetear todos los botones de operación
        JButton[] operaciones = {btnSumar, btnRestar, btnMultiplicar, btnDividir};
        for (JButton btn : operaciones) {
            if (btn != null) {
                btn.setBackground(BTN_OPERATOR);
            }
        }
        // Resaltar el botón activo brevemente
        if (botonActivo != null) {
            botonActivo.setBackground(BTN_OPERATOR_HOVER);
        }
    }
    
    /**
     * Metodo para crear el botón de igual
     * @return JButton creado
     */
    private JButton crearBotonIgual() {
        JButton boton = crearBotonBase("=", BTN_EQUAL, BTN_EQUAL_HOVER);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        boton.addActionListener(e -> {
            calculadora.calcular();
            actualizarPantalla();
            resaltarOperacion(null);
        });
        return boton;
    }
    
    /**
     * Metodo para crear el botón de limpiar
     * @return JButton creado
     */
    private JButton crearBotonLimpiar() {
        JButton boton = crearBotonBase("C", BTN_CLEAR, BTN_CLEAR_HOVER);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        boton.addActionListener(e -> {
            calculadora.limpiar();
            actualizarPantalla();
            resaltarOperacion(null);
        });
        return boton;
    }
    
    /**
     * Metodo para crear el botón de cambiar signo
     * @return JButton creado
     */
    private JButton crearBotonCambiarSigno() {
        JButton boton = crearBotonBase("±", BTN_NUMBER, BTN_NUMBER_HOVER);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        boton.addActionListener(e -> {
            calculadora.cambiarSigno();
            actualizarPantalla();
        });
        return boton;
    }
    
    /**
     * Metodo para crear el botón de punto decimal
     * @return JButton creado
     */
    private JButton crearBotonPunto() {
        JButton boton = crearBotonBase(".", BTN_NUMBER, BTN_NUMBER_HOVER);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        boton.addActionListener(e -> {
            calculadora.agregarDigito(".");
            actualizarPantalla();
        });
        return boton;
    }
    
    /**
     * Metodo para actualizar el contenido de la pantalla
     */
    private void actualizarPantalla() {
        if (calculadora.tieneError()) {
            pantalla.setText(calculadora.obtenerMensajeError());
            pantalla.setForeground(new Color(255, 100, 100));
            lblOperacion.setText(" ");
        } else {
            pantalla.setText(calculadora.obtenerTextoActual());
            pantalla.setForeground(TEXT_COLOR);
            
            // Actualizar indicador de operación
            String operacion = calculadora.obtenerOperacionActual();
            if (operacion != null && !calculadora.esNuevaEntrada()) {
                String simboloOp = operacion;
                switch (operacion) {
                    case "/": simboloOp = "÷"; break;
                    case "*": simboloOp = "×"; break;
                    case "-": simboloOp = "−"; break;
                }
                double valorAlm = calculadora.obtenerValorAlmacenado();
                String valorTexto = valorAlm == Math.floor(valorAlm) ? 
                    String.valueOf((long) valorAlm) : String.valueOf(valorAlm);
                lblOperacion.setText(valorTexto + " " + simboloOp);
                lblOperacion.setForeground(ACCENT_COLOR);
            } else if (operacion != null) {
                String simboloOp = operacion;
                switch (operacion) {
                    case "/": simboloOp = "÷"; break;
                    case "*": simboloOp = "×"; break;
                    case "-": simboloOp = "−"; break;
                }
                lblOperacion.setText(calculadora.obtenerTextoActual() + " " + simboloOp);
                lblOperacion.setForeground(new Color(150, 150, 150));
            } else {
                lblOperacion.setText(" ");
            }
        }
    }
}
