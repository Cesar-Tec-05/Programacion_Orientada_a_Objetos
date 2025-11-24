package src.app; // PAQUETE PRINCIPAL

// IMPORTACIONES
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import src.back_geometria.*;

/**
 * Clase del panel de geometría para cálculos de área y perímetro
 */
public class PanelGeometria extends JPanel {
    
    // Componentes del panel
    private JComboBox<String> comboFiguras;
    private JPanel panelInputs;
    private JTextArea txtResultado;
    private JButton btnCalcular, btnLimpiar;
    
    // Campos de entrada dinámicos
    private JTextField txtArista1, txtArista2, txtArista3;
    private JTextField txtBase, txtAltura;
    private JTextField txtNumLados;
    
    // Objetos backend
    private Circulo_back circulo;
    private Cuadrado_back cuadrado;
    private Rectangulo_back rectangulo;
    private Triangulo_back triangulo;
    private Elipse_back elipse;
    private Poligono_regular_back poligono;
    private Romboide_back romboide;
    
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
     * Constructor del panel de geometría
     */
    public PanelGeometria() {
        inicializarBackends();
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(BG_DARK);
        
        inicializarComponentes();
    }
    
    /**
     * Metodo para inicializar los objetos backend
     */
    private void inicializarBackends() {
        circulo = new Circulo_back();
        cuadrado = new Cuadrado_back();
        rectangulo = new Rectangulo_back();
        triangulo = new Triangulo_back();
        elipse = new Elipse_back();
        poligono = new Poligono_regular_back();
        romboide = new Romboide_back();
    }
    
    /**
     * Metodo para inicializar todos los componentes del panel
     */
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(BG_DARK);
        
        // Título
        JLabel lblTitulo = new JLabel("Cálculo de Área y Perímetro");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(TEXT_COLOR);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(20));
        
        // Panel de selección de figura
        JPanel panelSeleccion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSeleccion.setBackground(BG_DARK);
        JLabel lblFigura = new JLabel("Seleccione la figura:");
        lblFigura.setForeground(TEXT_COLOR);
        lblFigura.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelSeleccion.add(lblFigura);
        String[] figuras = {"Círculo", "Cuadrado", "Rectángulo", "Triángulo", 
                           "Elipse", "Polígono Regular", "Romboide"};
        comboFiguras = new JComboBox<>(figuras);
        comboFiguras.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboFiguras.setBackground(FIELD_BG);
        comboFiguras.setForeground(TEXT_COLOR);
        comboFiguras.addActionListener(e -> actualizarPanelInputs());
        panelSeleccion.add(comboFiguras);
        panelPrincipal.add(panelSeleccion);
        
        panelPrincipal.add(Box.createVerticalStrut(15));
        
        // Panel de inputs dinámico
        panelInputs = new JPanel();
        panelInputs.setLayout(new BoxLayout(panelInputs, BoxLayout.Y_AXIS));
        panelInputs.setBackground(BG_DARK);
        panelInputs.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(new Color(100, 150, 255), 2, true),
            "Datos de la figura",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            TEXT_COLOR
        ));
        panelInputs.setPreferredSize(new Dimension(480, 250));
        panelInputs.setMinimumSize(new Dimension(480, 250));
        panelInputs.setMaximumSize(new Dimension(480, 250));
        panelPrincipal.add(panelInputs);
        
        panelPrincipal.add(Box.createVerticalStrut(15));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(BG_DARK);
        
        btnCalcular = crearBoton("Calcular", BTN_PRIMARY, BTN_PRIMARY_HOVER);
        btnCalcular.addActionListener(e -> calcular());
        panelBotones.add(btnCalcular);
        
        btnLimpiar = crearBoton("Limpiar", BTN_DANGER, BTN_DANGER_HOVER);
        btnLimpiar.addActionListener(e -> limpiar());
        panelBotones.add(btnLimpiar);
        panelPrincipal.add(panelBotones);
        
        panelPrincipal.add(Box.createVerticalStrut(15));
        
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
        
        txtResultado = new JTextArea(5, 30);
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtResultado.setEditable(false);
        txtResultado.setBackground(FIELD_BG);
        txtResultado.setForeground(TEXT_COLOR);
        txtResultado.setCaretColor(TEXT_COLOR);
        txtResultado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        scrollResultado.setBorder(null);
        scrollResultado.getViewport().setBackground(FIELD_BG);
        panelResultado.add(scrollResultado, BorderLayout.CENTER);
        panelPrincipal.add(panelResultado);
        
        add(panelPrincipal, BorderLayout.CENTER);
        
        // Inicializar con la primera figura
        actualizarPanelInputs();
    }
    
    /**
     * Metodo para crear un botón con estilo personalizado
     * @param texto Texto del botón
     * @param bg Color de fondo
     * @param hoverBg Color de fondo al pasar el mouse
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
     * Metodo para actualizar el panel de inputs según la figura seleccionada
     */
    private void actualizarPanelInputs() {
        panelInputs.removeAll();
        
        String figuraSeleccionada = (String) comboFiguras.getSelectedItem();
        
        switch (figuraSeleccionada) {
            case "Círculo":
                agregarCampo("Radio:", txtArista1 = new JTextField(15));
                break;
                
            case "Cuadrado":
                agregarCampo("Lado:", txtArista1 = new JTextField(15));
                break;
                
            case "Rectángulo":
                agregarCampo("Base:", txtArista1 = new JTextField(15));
                agregarCampo("Altura:", txtArista2 = new JTextField(15));
                break;
                
            case "Triángulo":
                agregarCampo("Lado 1:", txtArista1 = new JTextField(15));
                agregarCampo("Lado 2:", txtArista2 = new JTextField(15));
                agregarCampo("Lado 3:", txtArista3 = new JTextField(15));
                agregarCampo("Base:", txtBase = new JTextField(15));
                agregarCampo("Altura:", txtAltura = new JTextField(15));
                break;
                
            case "Elipse":
                agregarCampo("Semieje Mayor:", txtArista1 = new JTextField(15));
                agregarCampo("Semieje Menor:", txtArista2 = new JTextField(15));
                break;
                
            case "Polígono Regular":
                agregarCampo("Lado:", txtArista1 = new JTextField(15));
                agregarCampo("Número de Lados:", txtNumLados = new JTextField(15));
                break;
                
            case "Romboide":
                agregarCampo("Base:", txtArista1 = new JTextField(15));
                agregarCampo("Altura:", txtArista2 = new JTextField(15));
                agregarCampo("Lado Oblicuo:", txtArista3 = new JTextField(15));
                break;
        }
        
        panelInputs.revalidate();
        panelInputs.repaint();
    }
    
    /**
     * Metodo para agregar un campo de entrada al panel
     * @param etiqueta Etiqueta del campo
     * @param campo JTextField asociado
     */
    private void agregarCampo(String etiqueta, JTextField campo) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(BG_DARK);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setForeground(TEXT_COLOR);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setPreferredSize(new Dimension(150, 25));
        panel.add(lbl);
        
        // Estilizar el campo de texto
        campo.setBackground(FIELD_BG);
        campo.setForeground(TEXT_COLOR);
        campo.setCaretColor(TEXT_COLOR);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBorder(new CompoundBorder(
            new LineBorder(new Color(100, 150, 255), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        panel.add(campo);
        panelInputs.add(panel);
    }
    
    /**
     * Metodo para realizar el cálculo según la figura seleccionada
     */
    private void calcular() {
        try {
            String figuraSeleccionada = (String) comboFiguras.getSelectedItem();
            StringBuilder resultado = new StringBuilder();
            resultado.append("Figura: ").append(figuraSeleccionada).append("\n");
            resultado.append("=".repeat(40)).append("\n");
            
            float area = 0, perimetro = 0;
            
            switch (figuraSeleccionada) {
                case "Círculo":
                    float radio = Float.parseFloat(txtArista1.getText());
                    circulo.setArista(radio);
                    area = circulo.getArea();
                    perimetro = circulo.getPerimetro();
                    resultado.append("Radio: ").append(radio).append("\n");
                    break;
                    
                case "Cuadrado":
                    float lado = Float.parseFloat(txtArista1.getText());
                    cuadrado.setArista(lado);
                    area = cuadrado.getArea();
                    perimetro = cuadrado.getPerimetro();
                    resultado.append("Lado: ").append(lado).append("\n");
                    break;
                    
                case "Rectángulo":
                    float base = Float.parseFloat(txtArista1.getText());
                    float altura = Float.parseFloat(txtArista2.getText());
                    rectangulo.setArista(base);
                    rectangulo.setArista2(altura);
                    area = rectangulo.getArea();
                    perimetro = rectangulo.getPerimetro();
                    resultado.append("Base: ").append(base).append("\n");
                    resultado.append("Altura: ").append(altura).append("\n");
                    break;
                    
                case "Triángulo":
                    float lado1 = Float.parseFloat(txtArista1.getText());
                    float lado2 = Float.parseFloat(txtArista2.getText());
                    float lado3 = Float.parseFloat(txtArista3.getText());
                    float baseT = Float.parseFloat(txtBase.getText());
                    float alturaT = Float.parseFloat(txtAltura.getText());
                    triangulo.setArista(lado1);
                    triangulo.setArista2(lado2);
                    triangulo.setArista3(lado3);
                    triangulo.setBase(baseT);
                    triangulo.setAltura(alturaT);
                    area = triangulo.getArea();
                    perimetro = triangulo.getPerimetro();
                    resultado.append("Lados: ").append(lado1).append(", ")
                             .append(lado2).append(", ").append(lado3).append("\n");
                    resultado.append("Base: ").append(baseT).append("\n");
                    resultado.append("Altura: ").append(alturaT).append("\n");
                    break;
                    
                case "Elipse":
                    float semiMayor = Float.parseFloat(txtArista1.getText());
                    float semiMenor = Float.parseFloat(txtArista2.getText());
                    elipse.setArista(semiMayor);
                    elipse.setArista2(semiMenor);
                    area = elipse.getArea();
                    perimetro = elipse.getPerimetro();
                    resultado.append("Semieje Mayor: ").append(semiMayor).append("\n");
                    resultado.append("Semieje Menor: ").append(semiMenor).append("\n");
                    break;
                    
                case "Polígono Regular":
                    float ladoP = Float.parseFloat(txtArista1.getText());
                    float numLados = Float.parseFloat(txtNumLados.getText());
                    poligono.setArista(ladoP);
                    poligono.setNumLados(numLados);
                    area = poligono.getArea();
                    perimetro = poligono.getPerimetro();
                    resultado.append("Lado: ").append(ladoP).append("\n");
                    resultado.append("Número de Lados: ").append((int)numLados).append("\n");
                    resultado.append("Apotema: ").append(String.format("%.4f", poligono.getApotema())).append("\n");
                    break;
                    
                case "Romboide":
                    float baseR = Float.parseFloat(txtArista1.getText());
                    float alturaR = Float.parseFloat(txtArista2.getText());
                    float ladoOblicuo = Float.parseFloat(txtArista3.getText());
                    romboide.setArista(baseR);
                    romboide.setArista2(alturaR);
                    romboide.setLado_oblicuo(ladoOblicuo);
                    area = romboide.getArea();
                    perimetro = romboide.getPerimetro();
                    resultado.append("Base: ").append(baseR).append("\n");
                    resultado.append("Altura: ").append(alturaR).append("\n");
                    resultado.append("Lado Oblicuo: ").append(ladoOblicuo).append("\n");
                    break;
            }
            
            resultado.append("\n");
            resultado.append("Área: ").append(String.format("%.4f", area)).append(" unidades²\n");
            resultado.append("Perímetro: ").append(String.format("%.4f", perimetro)).append(" unidades\n");
            
            txtResultado.setText(resultado.toString());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese valores numéricos válidos", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al calcular: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Metodo para limpiar todos los campos y resultados
     */
    private void limpiar() {
        txtResultado.setText("");
        actualizarPanelInputs();
        
        // Limpiar todos los backends
        circulo.clearAttributes();
        cuadrado.clearAttributes();
        rectangulo.clearAttributes();
        triangulo.clearAttributes();
        elipse.clearAttributes();
        poligono.clearAttributes();
        romboide.clearAttributes();
    }
}
