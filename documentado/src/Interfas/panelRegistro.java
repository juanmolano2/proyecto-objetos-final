package Interfas;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Mundo.GestionAcademica;

/**
 * Panel de la interfaz gráfica destinado al registro y búsqueda de estudiantes.
 * <p>
 * Contiene los campos de entrada necesarios para identificar o crear un estudiante:
 * código, nombre, programa académico y semestre actual. El panel se organiza
 * en una cuadrícula de 4 filas × 2 columnas (etiqueta + control de entrada).
 * </p>
 * <p>
 * Los programas disponibles se obtienen directamente de
 * {@link GestionAcademica#PROGRAMAS} para garantizar coherencia con la lógica
 * de negocio. El semestre se selecciona mediante un {@link JComboBox} con opciones
 * del 1 al 6, lo que evita entradas inválidas por parte del usuario.
 * </p>
 *
 * @author CJ
 * @version 1.0
 * @see GestionAcademica#PROGRAMAS
 */
public class panelRegistro extends JPanel {


    //  ATRIBUTOS
   

    /** Etiqueta descriptiva del campo de código del estudiante. */
    private JLabel labCodigo;

    /** Campo de texto para ingresar el código único del estudiante. */
    private JTextField txtCodigo;

    /** Etiqueta descriptiva del campo de nombre del estudiante. */
    private JLabel labNombre;

    /** Campo de texto para ingresar el nombre completo del estudiante. */
    private JTextField txtNombre;

    /** Etiqueta descriptiva del selector de programa académico. */
    private JLabel labPrograma;

    /**
     * Lista desplegable con los programas académicos disponibles,
     * cargados desde {@link GestionAcademica#PROGRAMAS}.
     */
    private JComboBox<String> cmbPrograma;

    /** Etiqueta descriptiva del selector de semestre. */
    private JLabel labSemestre;

    /**
     * Lista desplegable con los semestres disponibles (1 al 6).
     * Usar un combo en lugar de un campo de texto previene entradas inválidas.
     */
    private JComboBox<String> cmbSemestre;


    //  CONSTRUCTOR
    

    /**
     * Construye el panel de registro con todos sus componentes Swing y los
     * organiza en una cuadrícula de 4 filas × 2 columnas.
     * <p>
     * El borde del panel lleva el título {@code "Registro / Busqueda"} en color azul.
     * </p>
     */
    public panelRegistro() {
        TitledBorder borde = BorderFactory.createTitledBorder("Registro / Busqueda");
        setBorder(borde);
        borde.setTitleColor(Color.BLUE);
        setLayout(new GridLayout(4, 2));

        labCodigo   = new JLabel("CODIGO DEL ESTUDIANTE: ");
        txtCodigo   = new JTextField();
        labNombre   = new JLabel("NOMBRE DEL ESTUDIANTE: ");
        txtNombre   = new JTextField();
        labPrograma = new JLabel("PROGRAMA: ");
        cmbPrograma = new JComboBox<>(GestionAcademica.PROGRAMAS);
        labSemestre = new JLabel("SEMESTRE (1-6) *obligatorio: ");

        String[] semestres = {"1", "2", "3", "4", "5", "6"};
        cmbSemestre = new JComboBox<>(semestres);

        add(labCodigo);
        add(txtCodigo);
        add(labNombre);
        add(txtNombre);
        add(labPrograma);
        add(cmbPrograma);
        add(labSemestre);
        add(cmbSemestre);
    }

 
    //  GETTERS DE DATOS DE ENTRADA
 

    /**
     * Retorna el código del estudiante ingresado por el usuario.
     *
     * @return código del estudiante; cadena vacía si el campo está en blanco
     */
    public String getCodigo() { return txtCodigo.getText().trim(); }

    /**
     * Retorna el nombre del estudiante ingresado por el usuario.
     *
     * @return nombre del estudiante; cadena vacía si el campo está en blanco
     */
    public String getNombre() { return txtNombre.getText().trim(); }

    /**
     * Retorna el programa académico seleccionado en el combo.
     *
     * @return nombre del programa seleccionado; nunca {@code null}
     */
    public String getPrograma() { return (String) cmbPrograma.getSelectedItem(); }

    /**
     * Retorna el semestre seleccionado en el combo, convertido a entero.
     *
     * @return semestre seleccionado (1-6)
     */
    public int getSemestre() { return Integer.parseInt((String) cmbSemestre.getSelectedItem()); }

 
    //  UTILIDADES
 

    /**
     * Limpia todos los campos del panel, restableciendo los combos
     * a su primera opción y borrando los campos de texto.
     * <p>
     * Debe llamarse después de un registro exitoso para dejar el formulario
     * listo para la siguiente operación.
     * </p>
     */
    public void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        cmbPrograma.setSelectedIndex(0);
        cmbSemestre.setSelectedIndex(0);
    }
}
