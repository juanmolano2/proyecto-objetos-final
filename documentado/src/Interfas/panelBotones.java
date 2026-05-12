package Interfas;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de la interfaz gráfica que contiene todos los botones de acción
 * del Sistema de Gestión Académica.
 * <p>
 * Organiza 12 botones en una cuadrícula de 3 filas × 4 columnas.
 * Cada botón delega su evento de clic a un método específico de
 * {@link InterfazGestionAca}, siguiendo el patrón de diseño <em>Observer</em>
 * mediante expresiones lambda de {@code ActionListener}.
 * </p>
 * <p>
 * Este panel actúa como el panel {@code SOUTH} de la ventana principal.
 * </p>
 *
 * @author CJ
 * @version 1.0
 * @see InterfazGestionAca
 */
public class panelBotones extends JPanel {


    //  ATRIBUTOS — BOTONES
 

    /** Botón para registrar un nuevo estudiante en el sistema. */
    private JButton btnRegistrar;

    /** Botón para eliminar un estudiante del sistema. */
    private JButton btnEliminar;

    /** Botón para consultar la información completa de un estudiante. */
    private JButton btnConsultar;

    /** Botón para mostrar el horario de materias inscritas de un estudiante. */
    private JButton btnHorario;

    /** Botón para crear una nueva materia y agregarla al catálogo. */
    private JButton btnCrearMateria;

    /** Botón para eliminar una materia del catálogo. */
    private JButton btnEliminarMateria;

    /** Botón para inscribir una materia seleccionada a un estudiante. */
    private JButton btnAdicionarMateria;

    /** Botón para consultar y editar las notas de un estudiante. */
    private JButton btnNotas;

    /** Botón para cerrar el semestre académico de un estudiante. */
    private JButton btnCerrarSemestre;

    /** Botón para listar todas las materias disponibles en el catálogo. */
    private JButton btnListarMaterias;

    /** Botón para listar todos los estudiantes registrados en el sistema. */
    private JButton btnListarEstudiantes;

    /** Botón para mostrar la pantalla de información y ayuda del sistema. */
    private JButton btnInfo;

  
    //  ATRIBUTO — REFERENCIA A LA VENTANA PRINCIPAL


    /**
     * Referencia a la ventana principal de la aplicación.
     * Se usa para delegar las acciones de cada botón a los métodos
     * correspondientes de {@link InterfazGestionAca}.
     */
    private InterfazGestionAca interfaz;


    //  CONSTRUCTOR
   

    /**
     * Construye el panel de botones y registra los {@code ActionListener}
     * de cada botón apuntando al método correspondiente de la interfaz principal.
     * <p>
     * El borde del panel lleva el título {@code "Opciones"} en color azul.
     * Los botones se organizan en una cuadrícula de 3 filas × 4 columnas
     * con separación de 5 píxeles entre ellos.
     * </p>
     *
     * @param interfaz referencia a la ventana principal {@link InterfazGestionAca}
     *                 a la que se delegan todas las acciones de los botones
     */
    public panelBotones(InterfazGestionAca interfaz) {
        this.interfaz = interfaz;

        TitledBorder borde = BorderFactory.createTitledBorder("Opciones");
        setBorder(borde);
        borde.setTitleColor(Color.BLUE);
        setLayout(new GridLayout(3, 4, 5, 5));

        // Crear los botones
        btnRegistrar         = new JButton("REGISTRAR");
        btnEliminar          = new JButton("ELIMINAR USUARIO");
        btnConsultar         = new JButton("CONSULTAR");
        btnHorario           = new JButton("VER HORARIO");
        btnCrearMateria      = new JButton("CREAR MATERIA");
        btnEliminarMateria   = new JButton("ELIMINAR MATERIA");
        btnAdicionarMateria  = new JButton("ADICIONAR MATERIA");
        btnNotas             = new JButton("VER NOTAS");
        btnCerrarSemestre    = new JButton("CERRAR SEMESTRE");
        btnListarMaterias    = new JButton("LISTAR MATERIAS");
        btnListarEstudiantes = new JButton("LISTAR ESTUDIANTES");
        btnInfo              = new JButton("INFO DEL PROGRAMA");

        // Agregar los botones al panel en orden de cuadrícula
        add(btnRegistrar);
        add(btnEliminar);
        add(btnConsultar);
        add(btnHorario);
        add(btnCrearMateria);
        add(btnEliminarMateria);
        add(btnAdicionarMateria);
        add(btnNotas);
        add(btnCerrarSemestre);
        add(btnListarMaterias);
        add(btnListarEstudiantes);
        add(btnInfo);

        // Registrar los ActionListener (delegación a InterfazGestionAca)
        btnRegistrar.addActionListener(e         -> interfaz.registrarEstudiante());
        btnEliminar.addActionListener(e          -> interfaz.eliminarEstudiante());
        btnConsultar.addActionListener(e         -> interfaz.consultarEstudiante());
        btnHorario.addActionListener(e           -> interfaz.consultarHorario());
        btnCrearMateria.addActionListener(e      -> interfaz.crearMateria());
        btnEliminarMateria.addActionListener(e   -> interfaz.eliminarMateria());
        btnAdicionarMateria.addActionListener(e  -> interfaz.adicionarMateria());
        btnNotas.addActionListener(e             -> interfaz.consultarNotas());
        btnCerrarSemestre.addActionListener(e    -> interfaz.cerrarSemestre());
        btnListarMaterias.addActionListener(e    -> interfaz.listarMaterias());
        btnListarEstudiantes.addActionListener(e -> interfaz.listarEstudiantes());
        btnInfo.addActionListener(e              -> interfaz.mostrarInfo());
    }
}
