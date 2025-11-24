package src.app; // PAQUETE PRINCIPAL

// IMPORTACIONES
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import src.back_conversor.Conversor_unidiades_back;

/**
 * Clase que representa el panel de conversión de sistemas numéricos
 */
public class PanelConversor extends JPanel {
    
    // Componentes del panel
    private Conversor_unidiades_back conversor;
    private JTextField txtEntrada;
    private JTextArea txtResultado;
    private JComboBox<String> comboBaseOrigen;
    private JComboBox<String> comboBaseDestino;
    private JButton btnConvertir, btnLimpiar;
    
    // Variables para rastrear el índice anterior
    private int indiceAnteriorOrigen = 2;
    private int indiceAnteriorDestino = 0;
    
    // Colores del tema oscuro
    private static final Color BG_DARK = new Color(30, 30, 35);
    private static final Color PANEL_DARK = new Color(40, 40, 45);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static final Color FIELD_BG = new Color(50, 50, 55);
    private static final Color BTN_PRIMARY = new Color(100, 150, 255);
    private static final Color BTN_PRIMARY_HOVER = new Color(120, 170, 255);
    private static final Color BTN_DANGER = new Color(255, 100, 100);
    private static final Color BTN_DANGER_HOVER = new Color(255, 120, 120);
    
    /**
     * Constructor del panel de conversor
     */
    public PanelConversor() {
        conversor = new Conversor_unidiades_back();
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(BG_DARK);
        
        inicializarComponentes();
    }
    
    /**
     * Metodo para inicializar todos los componentes del panel
     */
    private void inicializarComponentes() {
        // Panel principal con BoxLayout
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(BG_DARK);
        
        // Título
        JLabel lblTitulo = new JLabel("Conversor de Sistemas Numéricos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(TEXT_COLOR);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(20));
        
        // Panel de entrada
        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEntrada.setBackground(BG_DARK);
        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setForeground(TEXT_COLOR);
        lblNumero.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelEntrada.add(lblNumero);
        txtEntrada = new JTextField(20);
        txtEntrada.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEntrada.setBackground(FIELD_BG);
        txtEntrada.setForeground(TEXT_COLOR);
        txtEntrada.setCaretColor(TEXT_COLOR);
        txtEntrada.setBorder(new CompoundBorder(
            new LineBorder(new Color(100, 150, 255), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panelEntrada.add(txtEntrada);
        panelPrincipal.add(panelEntrada);
        
        panelPrincipal.add(Box.createVerticalStrut(15));
        
        // Panel de base origen
        JPanel panelBaseOrigen = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBaseOrigen.setBackground(BG_DARK);
        JLabel lblOrigen = new JLabel("Base Origen:");
        lblOrigen.setForeground(TEXT_COLOR);
        lblOrigen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelBaseOrigen.add(lblOrigen);
        String[] bases = {"Binario (2)", "Octal (8)", "Decimal (10)", "Hexadecimal (16)"};
        comboBaseOrigen = new JComboBox<>(bases);
        comboBaseOrigen.setSelectedIndex(2); // Decimal por defecto
        estilizarComboBox(comboBaseOrigen);
        // Agregar listener para evitar seleccionar la misma base
        comboBaseOrigen.addActionListener(e -> verificarBasesIguales());
        panelBaseOrigen.add(comboBaseOrigen);
        panelPrincipal.add(panelBaseOrigen);
        
        panelPrincipal.add(Box.createVerticalStrut(15));
        
        // Panel de base destino
        JPanel panelBaseDestino = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBaseDestino.setBackground(BG_DARK);
        JLabel lblDestino = new JLabel("Base Destino:");
        lblDestino.setForeground(TEXT_COLOR);
        lblDestino.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelBaseDestino.add(lblDestino);
        comboBaseDestino = new JComboBox<>(bases);
        comboBaseDestino.setSelectedIndex(0); // Binario por defecto
        estilizarComboBox(comboBaseDestino);
        // Agregar listener para evitar seleccionar la misma base
        comboBaseDestino.addActionListener(e -> verificarBasesIguales());
        panelBaseDestino.add(comboBaseDestino);
        panelPrincipal.add(panelBaseDestino);
        
        panelPrincipal.add(Box.createVerticalStrut(20));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(BG_DARK);
        
        btnConvertir = crearBoton("Convertir", BTN_PRIMARY, BTN_PRIMARY_HOVER);
        btnConvertir.addActionListener(e -> convertir());
        panelBotones.add(btnConvertir);
        
        btnLimpiar = crearBoton("Limpiar", BTN_DANGER, BTN_DANGER_HOVER);
        btnLimpiar.addActionListener(e -> limpiar());
        panelBotones.add(btnLimpiar);
        
        panelPrincipal.add(panelBotones);
        
        panelPrincipal.add(Box.createVerticalStrut(20));
        
        // Panel de resultado
        JPanel panelResultado = new JPanel(new BorderLayout());
        panelResultado.setBackground(BG_DARK);
        panelResultado.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(new Color(100, 150, 255), 2, true),
            "Resultado",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            TEXT_COLOR
        ));
        
        // Área de texto para el resultado
        txtResultado = new JTextArea(8, 30);
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setBackground(FIELD_BG);
        txtResultado.setForeground(TEXT_COLOR);
        txtResultado.setCaretColor(TEXT_COLOR);
        txtResultado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Agregar el área de texto dentro de un JScrollPane
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setBorder(null);
        scrollResultado.getViewport().setBackground(FIELD_BG);
        panelResultado.add(scrollResultado, BorderLayout.CENTER);
        panelPrincipal.add(panelResultado);
        
        add(panelPrincipal, BorderLayout.CENTER);
    }
    
    /**
     * Metodo para verificar que las bases origen y destino no sean iguales
     * Si son iguales, revierte al valor anterior sin mostrar mensaje
     */
    private void verificarBasesIguales() {
        if (comboBaseOrigen.getSelectedIndex() == comboBaseDestino.getSelectedIndex()) {
            // Revertir silenciosamente al valor anterior
            comboBaseOrigen.removeActionListener(comboBaseOrigen.getActionListeners()[0]);
            comboBaseDestino.removeActionListener(comboBaseDestino.getActionListeners()[0]);
            
            comboBaseOrigen.setSelectedIndex(indiceAnteriorOrigen);
            comboBaseDestino.setSelectedIndex(indiceAnteriorDestino);
            
            comboBaseOrigen.addActionListener(e -> verificarBasesIguales());
            comboBaseDestino.addActionListener(e -> verificarBasesIguales());
        } else {
            // Actualizar los índices anteriores solo si la selección es válida
            indiceAnteriorOrigen = comboBaseOrigen.getSelectedIndex();
            indiceAnteriorDestino = comboBaseDestino.getSelectedIndex();
        }
    }
    
    /**
     * Metodo para estilizar un JComboBox con el tema oscuro
     */
    private void estilizarComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        combo.setBackground(FIELD_BG);
        combo.setForeground(TEXT_COLOR);
    }
    
    /**
     * Metodo para crear un botón con estilo personalizado
     * @return JButton estilizado
     */
    private JButton crearBoton(String texto, Color bg, Color hoverBg) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setForeground(TEXT_COLOR);
        boton.setBackground(bg);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(hoverBg);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(bg);
            }
        });
        
        return boton;
    }
    
    /**
     * Metodo para realizar la conversión entre sistemas numéricos
     */
    private void convertir() {
        String numero = txtEntrada.getText().trim().toUpperCase();
        
        if (numero.isEmpty()) { // Validar entrada vacía
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese un número", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int baseOrigen = obtenerBase(comboBaseOrigen.getSelectedIndex());
        int baseDestino = obtenerBase(comboBaseDestino.getSelectedIndex());
        
        boolean exito = conversor.convertirSistemaNumericos(numero, baseOrigen, baseDestino);
        
        if (exito) { // Mostrar resultado detallado
            StringBuilder resultado = new StringBuilder();
            resultado.append("Entrada: ").append(conversor.getEntrada()).append("\n");
            resultado.append("Valor Decimal: ").append(conversor.getValorNumerico()).append("\n");
            resultado.append("Resultado: ").append(conversor.getConversion()).append("\n");
            resultado.append("\nConversión exitosa!");
            txtResultado.setText(resultado.toString());
        } else { // Mostrar mensaje de error
            txtResultado.setText("ERROR: " + conversor.getError());
            JOptionPane.showMessageDialog(this, 
                conversor.getError(), 
                "Error de Conversión", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Metodo para obtener el valor de la base según el índice seleccionado
     * @param indice Índice seleccionado en el JComboBox
     * @return Valor numérico de la base
     */
    private int obtenerBase(int indice) {
        switch (indice) {
            case 0: return 2;  // Binario
            case 1: return 8;  // Octal
            case 2: return 10; // Decimal
            case 3: return 16; // Hexadecimal
            default: return 10;
        }
    }
    
    /**
     * Metodo para limpiar todos los campos
     */
    private void limpiar() {
        txtEntrada.setText("");
        txtResultado.setText("");
        comboBaseOrigen.setSelectedIndex(2);
        comboBaseDestino.setSelectedIndex(0);
        conversor.clear();
    }
}
