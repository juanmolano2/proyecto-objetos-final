package Interfas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import Mundo.GestionAcademica;
import Mundo.Materia;

/**
 * Panel lateral de la interfaz gráfica que cumple dos funciones complementarias:
 * <ol>
 *   <li><b>Crear nueva materia:</b> proporciona campos de entrada (código, nombre,
 *       créditos, semestre, horario, prerrequisitos y programa) para registrar
 *       una materia nueva en el catálogo del sistema.</li>
 *   <li><b>Inscribir materia:</b> muestra una lista dinámica de materias disponibles
 *       para el estudiante activo, permitiendo seleccionar una para su inscripción.</li>
 * </ol>
 * <p>
 * El panel se organiza en una cuadrícula de 10 filas × 2 columnas (etiqueta + control).
 * Las últimas tres filas son ocupadas por la lista de materias disponibles.
 * Este panel ocupa la posición {@code EAST} del {@link java.awt.BorderLayout}
 * de la ventana principal {@link InterfazGestionAca}.
 * </p>
 *
 * @author CJ
 * @version 1.0
 * @see Materia
 * @see GestionAcademica
 * @see InterfazGestionAca
 */
public class panelAdicionMaterias extends JPanel {

  
    //  ATRIBUTOS — CAMPOS PARA CREAR NUEVA MATERIA


    /** Etiqueta del campo de código de la nueva materia. */
    private JLabel labCodigo;

    /**
     * Campo de texto para ingresar el código de la nueva materia
     * (p. ej. {@code "IS110"}).
     */
    private JTextField txtCodigo;

    /** Etiqueta del campo de nombre de la nueva materia. */
    private JLabel labNombre;

    /** Campo de texto para ingresar el nombre completo de la nueva materia. */
    private JTextField txtNombre;

    /** Etiqueta del campo de créditos de la nueva materia. */
    private JLabel labCreditos;

    /**
     * Campo de texto para ingresar los créditos académicos de la nueva materia.
     * El valor se valida en rango 1-10 al momento de crear la materia.
     */
    private JTextField txtCreditos;

    /** Etiqueta del selector de semestre de la nueva materia. */
    private JLabel labSemestre;

    /**
     * Lista desplegable con los semestres (1-6) en que puede dictarse
     * la nueva materia.
     */
    private JComboBox<String> cmbSemestre;

    /** Etiqueta del campo de horario de la nueva materia. */
    private JLabel labHorario;

    /**
     * Campo de texto para ingresar el horario de la nueva materia.
     * Formato esperado: {@code "Día HH:MM-HH:MM"} (p. ej. {@code "Lun-Mie 07:00-09:00"}).
     */
    private JTextField txtHorario;

    /** Etiqueta del campo de prerrequisitos de la nueva materia. */
    private JLabel labRequisitos;

    /**
     * Campo de texto para ingresar los códigos de materias prerrequisito,
     * separados por coma (p. ej. {@code "IS101,IS102"}).
     */
    private JTextField txtRequisitos;

    /** Etiqueta del selector de programa de la nueva materia. */
    private JLabel labPrograma;

    /**
     * Lista desplegable con los programas disponibles más la opción {@code "COMUN"},
     * que indica que la materia es compartida por todos los programas.
     */
    private JComboBox<String> cmbPrograma;

   
    //  ATRIBUTOS — LISTA DE MATERIAS DISPONIBLES PARA INSCRIPCIÓN
 

    /**
     * Modelo de datos de la lista de materias disponibles para inscripción.
     * Se actualiza dinámicamente con {@link #cargarMaterias(ArrayList)}.
     */
    private DefaultListModel<Materia> modeloLista;

    /**
     * Componente visual que muestra las materias disponibles para que el
     * estudiante seleccione la que desea inscribir.
     * Configurado con selección simple (un solo elemento a la vez).
     */
    private JList<Materia> listaMaterias;

    /**
     * Panel de desplazamiento que envuelve {@link #listaMaterias},
     * permitiendo hacer scroll cuando la lista es extensa.
     */
    private JScrollPane scroll;

   
    //  CONSTRUCTOR
   

    /**
     * Construye el panel con todos sus componentes Swing, organizados en una
     * cuadrícula de 10 filas × 2 columnas.
     * <p>
     * El borde del panel lleva el título {@code "Adicion / Crear Materia"}
     * en color azul. Los combos de programa ofrecen las opciones de
     * {@link GestionAcademica#PROGRAMAS} más {@code "COMUN"}.
     * </p>
     */
    public panelAdicionMaterias() {
        TitledBorder borde = BorderFactory.createTitledBorder("Adicion / Crear Materia");
        setBorder(borde);
        borde.setTitleColor(Color.BLUE);
        setLayout(new GridLayout(10, 2));

        // Inicializar controles de creación de materia
        labCodigo   = new JLabel("CODIGO NUEVA MATERIA:");
        txtCodigo   = new JTextField();
        labNombre   = new JLabel("NOMBRE NUEVA MATERIA:");
        txtNombre   = new JTextField();
        labCreditos = new JLabel("CREDITOS (1-10):");
        txtCreditos = new JTextField();
        labSemestre = new JLabel("SEMESTRE (1-6):");
        String[] sems = {"1", "2", "3", "4", "5", "6"};
        cmbSemestre = new JComboBox<>(sems);
        labHorario  = new JLabel("HORARIO (ej: Lun-Mie 07:00-09:00):");
        txtHorario  = new JTextField();
        labRequisitos = new JLabel("REQUISITOS (ej: IS101,IS102):");
        txtRequisitos = new JTextField();
        labPrograma = new JLabel("PROGRAMA:");
        String[] progs = {
            "Ingenieria de Sistemas",
            "Administracion de Empresas",
            "Psicologia",
            "COMUN"
        };
        cmbPrograma = new JComboBox<>(progs);

        // Inicializar lista de materias disponibles
        modeloLista   = new DefaultListModel<>();
        listaMaterias = new JList<>(modeloLista);
        listaMaterias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMaterias.setFont(new Font("SansSerif", Font.PLAIN, 11));
        scroll = new JScrollPane(listaMaterias);

        // Agregar componentes en orden a la cuadrícula
        add(labCodigo);     add(txtCodigo);
        add(labNombre);     add(txtNombre);
        add(labCreditos);   add(txtCreditos);
        add(labSemestre);   add(cmbSemestre);
        add(labHorario);    add(txtHorario);
        add(labRequisitos); add(txtRequisitos);
        add(labPrograma);   add(cmbPrograma);
        // La lista ocupa las últimas 3 filas
        add(new JLabel("MATERIAS DISPONIBLES:"));
        add(new JLabel(""));
        add(scroll);
        add(new JLabel(""));
    }


    //  GETTERS — DATOS PARA CREAR MATERIA
   

    /**
     * Retorna el código de la nueva materia ingresado por el usuario,
     * en mayúsculas y sin espacios adicionales.
     *
     * @return código de la nueva materia; cadena vacía si el campo está en blanco
     */
    public String getCodigoNueva() { return txtCodigo.getText().trim().toUpperCase(); }

    /**
     * Retorna el nombre de la nueva materia ingresado por el usuario.
     *
     * @return nombre de la nueva materia; cadena vacía si el campo está en blanco
     */
    public String getNombreNueva() { return txtNombre.getText().trim(); }

    /**
     * Retorna el horario de la nueva materia ingresado por el usuario.
     *
     * @return horario en formato {@code "Día HH:MM-HH:MM"}; cadena vacía si está en blanco
     */
    public String getHorario() { return txtHorario.getText().trim(); }

    /**
     * Retorna el programa seleccionado para la nueva materia.
     *
     * @return nombre del programa o {@code "COMUN"}; nunca {@code null}
     */
    public String getProgramaNueva() { return (String) cmbPrograma.getSelectedItem(); }

    /**
     * Retorna el semestre seleccionado para la nueva materia, convertido a entero.
     *
     * @return semestre (1-6)
     */
    public int getSemestreNuevo() { return Integer.parseInt((String) cmbSemestre.getSelectedItem()); }

    /**
     * Intenta parsear el campo de créditos como entero y lo retorna.
     * Si el campo contiene un valor no numérico o está vacío, retorna {@code -1}
     * como señal de error para que la capa de presentación muestre un aviso.
     *
     * @return número de créditos (1-10) o {@code -1} si el valor es inválido
     */
    public int getCreditos() {
        try {
            return Integer.parseInt(txtCreditos.getText().trim());
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Parsea el campo de prerrequisitos y retorna una lista de códigos de materias.
     * <p>
     * El campo acepta códigos separados por coma (p. ej. {@code "IS101,IS102"}).
     * Los espacios adicionales alrededor de cada código son ignorados y los
     * códigos se convierten a mayúsculas automáticamente.
     * Si el campo está vacío, retorna una lista vacía.
     * </p>
     *
     * @return lista de códigos de materias prerrequisito; lista vacía si no hay ninguno
     */
    public ArrayList<String> getRequisitos() {
        ArrayList<String> lista = new ArrayList<>();
        String texto = txtRequisitos.getText().trim();
        if (!texto.isEmpty()) {
            String[] partes = texto.split(",");
            for (String p : partes) {
                String cod = p.trim().toUpperCase();
                if (!cod.isEmpty()) lista.add(cod);
            }
        }
        return lista;
    }

    
    //  MÉTODOS DE LA LISTA DE MATERIAS DISPONIBLES
   

    /**
     * Actualiza la lista de materias disponibles para inscripción.
     * <p>
     * Limpia el modelo actual y agrega cada elemento de la lista proporcionada.
     * Si la lista está vacía, el componente se deshabilita visualmente para
     * indicar que no hay materias disponibles para el estudiante.
     * </p>
     *
     * @param materias lista de objetos {@link Materia} disponibles para inscribir
     */
    public void cargarMaterias(ArrayList<Materia> materias) {
        modeloLista.clear();
        for (Materia m : materias) modeloLista.addElement(m);
        listaMaterias.setEnabled(!materias.isEmpty());
    }

    /**
     * Retorna la materia actualmente seleccionada en la lista de materias disponibles.
     *
     * @return materia seleccionada, o {@code null} si ninguna está seleccionada
     */
    public Materia getMateriaSeleccionada() {
        return listaMaterias.getSelectedValue();
    }

  
    //  MÉTODOS DE LIMPIEZA
 

    /**
     * Limpia únicamente los campos de creación de nueva materia,
     * restableciendo los combos a su primera opción y borrando los textos.
     * <p>
     * No afecta la lista de materias disponibles.
     * Debe llamarse después de crear exitosamente una materia.
     * </p>
     */
    public void limpiarCamposNuevaMateria() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCreditos.setText("");
        txtHorario.setText("");
        txtRequisitos.setText("");
        cmbSemestre.setSelectedIndex(0);
        cmbPrograma.setSelectedIndex(0);
    }

    /**
     * Limpia completamente el panel: borra los campos de creación de materia
     * y vacía la lista de materias disponibles.
     * <p>
     * Debe llamarse al cerrar el semestre o cuando se requiere reiniciar
     * el estado completo del panel.
     * </p>
     */
    public void limpiar() {
        limpiarCamposNuevaMateria();
        modeloLista.clear();
    }
}
