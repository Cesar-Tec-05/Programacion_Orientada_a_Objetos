/**
 * @author Cesar de Jesus Becerra Vera
 * @since 24 de Noviembre de 2025
 * @version 1.0
 * PAQUETE: src.app
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Proyecto final - Programa con interfaz gráfica que integra una calculadora, un conversor de bases 
 * numéricas y un calculador de propiedades geométricas.
 */

package src.app; // PAQUETE PRINCIPAL

// IMPORTACIONES
import javax.swing.*; 
import java.awt.*;

/**
 * Clase principal que crea la ventana con pestañas para cada funcionalidad
 */
public class Main extends JFrame {
    
    // Colores del tema oscuro
    public static final Color BACKGROUND_DARK = new Color(30, 30, 35);
    public static final Color PANEL_DARK = new Color(40, 40, 45);
    public static final Color PANEL_DARKER = new Color(25, 25, 30);
    public static final Color TEXT_COLOR = new Color(220, 220, 220);
    public static final Color ACCENT_COLOR = new Color(100, 150, 255);
    public static final Color ACCENT_HOVER = new Color(120, 170, 255);
    
    // Componentes de la ventana
    private JTabbedPane tabbedPane;
    private PanelCalculadora panelCalculadora;
    private PanelConversor panelConversor;
    private PanelGeometria panelGeometria;
    
    /**
     * Constructor que inicializa la ventana principal
     */
    public Main() {
        configurarTemaOscuro();
        configurarVentana();
        inicializarComponentes();
    }
    
    /**
     * Metodo para configurar el tema oscuro global
     */
    private void configurarTemaOscuro() {
        try { // Establecer el Look and Feel del sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { // Manejo de excepciones
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para configurar la ventana principal
     */
    private void configurarVentana() {
        setTitle("Aplicación Multifuncional - POO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BACKGROUND_DARK);
    }
    
    /**
     * Metodo para inicializar y agregar los componentes a la ventana
     */
    private void inicializarComponentes() {
        tabbedPane = new JTabbedPane();
        
        // Estilo del TabbedPane
        tabbedPane.setBackground(PANEL_DARK);
        tabbedPane.setForeground(TEXT_COLOR);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        // Crear los paneles de cada funcionalidad
        panelCalculadora = new PanelCalculadora();
        panelConversor = new PanelConversor();
        panelGeometria = new PanelGeometria();
        
        // Agregar pestañas con iconos
        tabbedPane.addTab("🧮 Calculadora", panelCalculadora);
        tabbedPane.addTab("🔄 Conversor", panelConversor);
        tabbedPane.addTab("📐 Geometría", panelGeometria);
        
        // Personalizar cada pestaña con colores
        configurarPestanas();
        
        // Agregar listener para cambiar el estilo de la pestaña activa
        tabbedPane.addChangeListener(e -> configurarPestanas());
        
        // Agregar el panel de pestañas a la ventana
        add(tabbedPane);
    }
    
    /**
     * Método para configurar el estilo de las pestañas según la activa
     */
    private void configurarPestanas() {
        int pestanaActiva = tabbedPane.getSelectedIndex();
        
        for (int i = 0; i < tabbedPane.getTabCount(); i++) { // Iterar sobre todas las pestañas
            if (i == pestanaActiva) {
                // Pestaña activa - solo fondo más visible, sin agrandar texto
                tabbedPane.setBackgroundAt(i, new Color(60, 60, 70));
                tabbedPane.setForegroundAt(i, ACCENT_COLOR);
            } else {
                // Pestañas inactivas - color oscuro
                tabbedPane.setBackgroundAt(i, PANEL_DARKER);
                tabbedPane.setForegroundAt(i, new Color(120, 120, 120));
            }
        }
    }
    
    /**
     * Método main para ejecutar la aplicación
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Asegurar que la GUI se cree en el hilo de despacho de eventos
            Main ventana = new Main(); // Crear instancia de la ventana principal
            ventana.setVisible(true); // Hacer visible la ventana
        });
    }
}
