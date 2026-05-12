package Interfas;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Mundo.Estudiante;
import Mundo.GestionAcademica;
import Mundo.Materia;

/**
 * Ventana principal del Sistema de Gestión Académica UNIBAGUE.
 * <p>
 * Esta clase extiende {@link JFrame} y actúa como <em>Controlador</em> en el patrón
 * MVC: coordina la interacción entre los paneles de la vista y la lógica de
 * negocio encapsulada en {@link GestionAcademica}.
 * </p>
 * <p>
 * La ventana utiliza un {@link BorderLayout} y distribuye cuatro paneles:
 * </p>
 * <ul>
 *   <li>{@code NORTH}  – {@link panelRegistro}: campos para identificar o registrar un estudiante.</li>
 *   <li>{@code SOUTH}  – {@link panelBotones}: botones de acción del sistema.</li>
 *   <li>{@code EAST}   – {@link panelAdicionMaterias}: formulario para crear materias e inscribirlas.</li>
 *   <li>{@code CENTER} – {@link panelConsultaHorario}: área de resultados y mensajes.</li>
 * </ul>
 * <p>
 * El punto de entrada de la aplicación es el método {@link #main(String[])},
 * que lanza la ventana en el hilo de despacho de eventos de Swing.
 * </p>
 *
 * @author CJ
 * @version 1.0
 * @see GestionAcademica
 * @see panelRegistro
 * @see panelBotones
 * @see panelAdicionMaterias
 * @see panelConsultaHorario
 */
public class InterfazGestionAca extends JFrame {

    // =====================================================================
    //  ATRIBUTOS — PANELES DE LA VISTA
    // =====================================================================

    /**
     * Panel superior con los campos de código, nombre, programa y semestre
     * del estudiante. Posición {@code NORTH}.
     */
    private panelRegistro panelRegistro;

    /**
     * Panel inferior con los 12 botones de acción del sistema.
     * Posición {@code SOUTH}.
     */
    private panelBotones panelBotones;

    /**
     * Panel lateral con el formulario de creación de materias y la lista
     * de materias disponibles para inscribir. Posición {@code EAST}.
     */
    private panelAdicionMaterias panelAdicionMaterias;

    /**
     * Panel central con el área de texto que muestra los resultados de cada
     * operación. Posición {@code CENTER}.
     */
    private panelConsultaHorario panelConsultaHorario;

    // =====================================================================
    //  ATRIBUTO — LÓGICA DE NEGOCIO
    // =====================================================================

    /**
     * Instancia de la capa de lógica de negocio que gestiona estudiantes,
     * materias y todas las operaciones académicas del sistema.
     */
    private GestionAcademica gestion;

    // =====================================================================
    //  CONSTRUCTOR
    // =====================================================================

    /**
     * Construye y configura la ventana principal de la aplicación.
     * <p>
     * Inicializa la capa de negocio, crea los cuatro paneles y los ubica
     * en el {@link BorderLayout}. La ventana se centra en la pantalla y
     * se cierra completamente al pulsar el botón de cierre.
     * </p>
     */
    public InterfazGestionAca() {
        setTitle("UNIBAGUE - Sistema de Gestion Academica");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        gestion = new GestionAcademica();

        panelRegistro        = new panelRegistro();
        panelBotones         = new panelBotones(this);
        panelAdicionMaterias = new panelAdicionMaterias();
        panelConsultaHorario = new panelConsultaHorario();

        add(panelRegistro,        BorderLayout.NORTH);
        add(panelBotones,         BorderLayout.SOUTH);
        add(panelAdicionMaterias, BorderLayout.EAST);
        add(panelConsultaHorario, BorderLayout.CENTER);
    }

    // =====================================================================
    //  OPERACIONES SOBRE ESTUDIANTES
    // =====================================================================

    /**
     * Lee los datos del {@link panelRegistro} y solicita a {@link GestionAcademica}
     * que registre un nuevo estudiante.
     * <p>
     * Validaciones previas al llamado de negocio:
     * <ul>
     *   <li>El código y el nombre no pueden estar vacíos.</li>
     * </ul>
     * Si el registro es exitoso, muestra un diálogo de confirmación y limpia
     * el panel de registro. Si ocurre un error, muestra un diálogo de error.
     * </p>
     */
    public void registrarEstudiante() {
        String codigo   = panelRegistro.getCodigo();
        String nombre   = panelRegistro.getNombre();
        String programa = panelRegistro.getPrograma();
        int semestre    = panelRegistro.getSemestre();

        if (codigo.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Complete el Codigo y el Nombre del estudiante.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String resultado = gestion.registrarEstudiante(codigo, nombre, programa, semestre);
        panelConsultaHorario.mostrarTexto(resultado);

        if (!resultado.startsWith("ERROR")) {
            JOptionPane.showMessageDialog(this, resultado, "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            panelRegistro.limpiar();
        } else {
            JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lee el código del {@link panelRegistro} y solicita a {@link GestionAcademica}
     * que elimine al estudiante correspondiente.
     * <p>
     * Antes de eliminar muestra un diálogo de confirmación advirtiendo que la
     * acción es irreversible. Si el usuario cancela, no se realiza ninguna acción.
     * Si el código está vacío, muestra un diálogo de error.
     * </p>
     */
    public void eliminarEstudiante() {
        String codigo = panelRegistro.getCodigo();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Ingrese el codigo del estudiante a eliminar.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(this,
            "Esta seguro que desea eliminar al estudiante con codigo: " + codigo + "?\n" +
            "Esta accion no se puede deshacer.",
            "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmar != JOptionPane.YES_OPTION) return;

        String resultado = gestion.eliminarEstudiante(codigo);
        panelConsultaHorario.mostrarTexto(resultado);

        if (!resultado.startsWith("ERROR")) {
            JOptionPane.showMessageDialog(this, resultado, "Eliminado", JOptionPane.INFORMATION_MESSAGE);
            panelRegistro.limpiar();
        } else {
            JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lee el código del {@link panelRegistro} y muestra en el área de resultados
     * la información completa del estudiante encontrado.
     * <p>
     * Si el campo de código está vacío, muestra un diálogo de error.
     * </p>
     */
    public void consultarEstudiante() {
        String codigo = panelRegistro.getCodigo();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Ingrese el codigo del estudiante.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String resultado = gestion.consultarEstudiante(codigo);
        panelConsultaHorario.mostrarTexto(resultado);
    }

    /**
     * Lee el código del {@link panelRegistro} y muestra en el área de resultados
     * el horario de materias inscritas del estudiante.
     * <p>
     * Si el campo de código está vacío, muestra un diálogo de error.
     * </p>
     */
    public void consultarHorario() {
        String codigo = panelRegistro.getCodigo();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Ingrese el codigo del estudiante.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String resultado = gestion.consultarHorario(codigo);
        panelConsultaHorario.mostrarTexto(resultado);
    }

  
    //  OPERACIONES SOBRE MATERIAS
 

    /**
     * Lee los datos del {@link panelAdicionMaterias} y solicita a
     * {@link GestionAcademica} que cree una nueva materia en el catálogo.
     * <p>
     * Validación previa: los créditos deben ser un número entero válido (no {@code -1}).
     * Las demás validaciones (campos vacíos, rango de valores, duplicados, programa)
     * las realiza la capa de negocio.
     * Si la creación es exitosa, limpia los campos del formulario y muestra confirmación.
     * </p>
     */
    public void crearMateria() {
        String codigo   = panelAdicionMaterias.getCodigoNueva();
        String nombre   = panelAdicionMaterias.getNombreNueva();
        int creditos    = panelAdicionMaterias.getCreditos();
        int semestre    = panelAdicionMaterias.getSemestreNuevo();
        String horario  = panelAdicionMaterias.getHorario();
        ArrayList<String> requisitos = panelAdicionMaterias.getRequisitos();
        String programa = panelAdicionMaterias.getProgramaNueva();

        if (creditos == -1) {
            JOptionPane.showMessageDialog(this,
                "Ingrese un numero valido de creditos (1-10).",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String resultado = gestion.crearMateria(codigo, nombre, creditos, semestre, horario, requisitos, programa);
        panelConsultaHorario.mostrarTexto(resultado);

        if (!resultado.startsWith("ERROR")) {
            JOptionPane.showMessageDialog(this, resultado, "Materia Creada", JOptionPane.INFORMATION_MESSAGE);
            panelAdicionMaterias.limpiarCamposNuevaMateria();
        } else {
            JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lee el código de materia del campo {@code "CODIGO NUEVA MATERIA"} del
     * {@link panelAdicionMaterias} y solicita a {@link GestionAcademica} que
     * la elimine del catálogo.
     * <p>
     * Muestra un diálogo de confirmación antes de eliminar.
     * Si el campo está vacío, muestra un mensaje orientando al usuario sobre
     * qué campo utilizar para ingresar el código.
     * </p>
     */
    public void eliminarMateria() {
        String codigo = panelAdicionMaterias.getCodigoNueva();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Ingrese el codigo de la materia a eliminar en el campo 'CODIGO NUEVA MATERIA'.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(this,
            "Esta seguro que desea eliminar la materia con codigo: " + codigo + "?\n" +
            "Esta accion no se puede deshacer.",
            "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmar != JOptionPane.YES_OPTION) return;

        String resultado = gestion.eliminarMateria(codigo);
        panelConsultaHorario.mostrarTexto(resultado);

        if (!resultado.startsWith("ERROR")) {
            JOptionPane.showMessageDialog(this, resultado, "Materia Eliminada", JOptionPane.INFORMATION_MESSAGE);
            panelAdicionMaterias.limpiarCamposNuevaMateria();
        } else {
            JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    //  INSCRIPCIÓN DE MATERIAS


    /**
     * Gestiona la inscripción de una materia al estudiante activo.
     * <p>
     * El proceso es de dos pasos:
     * </p>
     * <ol>
     *   <li><b>Primera pulsación:</b> si no hay ninguna materia seleccionada en la lista,
     *       carga en {@link panelAdicionMaterias} las materias disponibles para el estudiante.</li>
     *   <li><b>Segunda pulsación:</b> si el usuario ya seleccionó una materia de la lista,
     *       la inscribe y recarga la lista con las materias aún disponibles.</li>
     * </ol>
     * <p>
     * Validaciones previas: el código del estudiante no puede estar vacío y el
     * estudiante debe existir en el sistema.
     * </p>
     */
    public void adicionarMateria() {
        String codigo = panelRegistro.getCodigo();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Ingrese el codigo del estudiante.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (gestion.buscarEstudiante(codigo) == null) {
            JOptionPane.showMessageDialog(this,
                "Estudiante no encontrado.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Si ya hay una materia seleccionada, la inscribe
        Materia seleccionada = panelAdicionMaterias.getMateriaSeleccionada();
        if (seleccionada != null) {
            String resultado = gestion.inscribirMateria(codigo, seleccionada.getCodigo());
            panelConsultaHorario.mostrarTexto(resultado);
        }

        // Recargar la lista de materias disponibles
        ArrayList<Materia> disponibles = gestion.getMateriasDisponibles(codigo);
        panelAdicionMaterias.cargarMaterias(disponibles);

        if (disponibles.isEmpty() && seleccionada == null) {
            panelConsultaHorario.mostrarTexto("No hay materias disponibles para el estudiante " + codigo + ".\n" +
                "Puede que le falten prerrequisitos o ya tiene el maximo de creditos.");
        } else if (seleccionada == null) {
            panelConsultaHorario.mostrarTexto("Materias disponibles para " + codigo + " cargadas.\n" +
                "Seleccione una de la lista y vuelva a presionar 'ADICIONAR MATERIA'.");
        }
    }

 
    //  GESTIÓN DE NOTAS
 

    /**
     * Muestra las calificaciones del estudiante activo y permite actualizar
     * las notas de los tres cortes de cualquier materia inscrita.
     * <p>
     * Flujo de la operación:
     * </p>
     * <ol>
     *   <li>Valida que el código no esté vacío y que el estudiante exista.</li>
     *   <li>Muestra las notas actuales en el área de resultados.</li>
     *   <li>Pregunta si desea editar alguna nota; si el usuario responde No, termina.</li>
     *   <li>Muestra un selector de materia mediante {@link JOptionPane#showInputDialog}.</li>
     *   <li>Para la materia elegida, abre tres diálogos (uno por corte) que muestran
     *       el valor actual y permiten ingresar o actualizar la nota (0.0 - 5.0).</li>
     *   <li>Actualiza las notas y refresca el área de resultados.</li>
     * </ol>
     * <p>
     * Cancelar cualquier diálogo de nota deja el valor anterior sin cambios.
     * </p>
     */
    public void consultarNotas() {
        String codigo = panelRegistro.getCodigo();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Ingrese el codigo del estudiante.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Estudiante e = gestion.buscarEstudiante(codigo);
        if (e == null) {
            panelConsultaHorario.mostrarTexto("Estudiante con codigo '" + codigo + "' no encontrado.");
            return;
        }

        if (e.getMateriasInscritas().isEmpty()) {
            panelConsultaHorario.mostrarTexto("El estudiante no tiene materias inscritas.");
            return;
        }

        // Mostrar notas actuales
        panelConsultaHorario.mostrarTexto(gestion.consultarNotas(codigo));

        // Preguntar si desea editar alguna nota
        int respuesta = JOptionPane.showConfirmDialog(this,
            "Desea ingresar o actualizar notas de alguna materia?",
            "Editar Notas", JOptionPane.YES_NO_OPTION);

        if (respuesta != JOptionPane.YES_OPTION) return;

        // Construir arreglo de opciones con las materias inscritas
        ArrayList<Materia> inscritas = e.getMateriasInscritas();
        String[] opciones = new String[inscritas.size()];
        for (int i = 0; i < inscritas.size(); i++) {
            Materia m = inscritas.get(i);
            opciones[i] = m.getNombre() + " (" + m.getCodigo() + ")";
        }

        String materiaElegida = (String) JOptionPane.showInputDialog(this,
            "Seleccione la materia a calificar:",
            "Seleccionar Materia",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]);

        if (materiaElegida == null) return;

        // Encontrar el índice de la materia elegida
        int indice = -1;
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(materiaElegida)) { indice = i; break; }
        }
        if (indice < 0) return;

        Materia m = inscritas.get(indice);

        // CORTE 1 (30%)
        String c1str = JOptionPane.showInputDialog(this,
            "Nota CORTE 1 de '" + m.getNombre() + "' (30%):\n" +
            "Valor actual: " + (m.getNotaCorte1() < 0 ? "Sin nota" : m.getNotaCorte1()) + "\n" +
            "Ingrese nota de 0.0 a 5.0 (o cancele para dejarlo igual):",
            "Corte 1", JOptionPane.QUESTION_MESSAGE);

        if (c1str != null && !c1str.trim().isEmpty()) {
            try {
                double c1 = Double.parseDouble(c1str.trim());
                if (c1 < 0 || c1 > 5) {
                    JOptionPane.showMessageDialog(this, "La nota debe ser entre 0.0 y 5.0", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    m.setNotaCorte1(c1);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Numero invalido para corte 1.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        //  CORTE 2 (30%)
        String c2str = JOptionPane.showInputDialog(this,
            "Nota CORTE 2 de '" + m.getNombre() + "' (30%):\n" +
            "Valor actual: " + (m.getNotaCorte2() < 0 ? "Sin nota" : m.getNotaCorte2()) + "\n" +
            "Ingrese nota de 0.0 a 5.0 (o cancele para dejarlo igual):",
            "Corte 2", JOptionPane.QUESTION_MESSAGE);

        if (c2str != null && !c2str.trim().isEmpty()) {
            try {
                double c2 = Double.parseDouble(c2str.trim());
                if (c2 < 0 || c2 > 5) {
                    JOptionPane.showMessageDialog(this, "La nota debe ser entre 0.0 y 5.0", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    m.setNotaCorte2(c2);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Numero invalido para corte 2.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // CORTE 3 (40%) 
        String c3str = JOptionPane.showInputDialog(this,
            "Nota CORTE 3 de '" + m.getNombre() + "' (40%):\n" +
            "Valor actual: " + (m.getNotaCorte3() < 0 ? "Sin nota" : m.getNotaCorte3()) + "\n" +
            "Ingrese nota de 0.0 a 5.0 (o cancele para dejarlo igual):",
            "Corte 3", JOptionPane.QUESTION_MESSAGE);

        if (c3str != null && !c3str.trim().isEmpty()) {
            try {
                double c3 = Double.parseDouble(c3str.trim());
                if (c3 < 0 || c3 > 5) {
                    JOptionPane.showMessageDialog(this, "La nota debe ser entre 0.0 y 5.0", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    m.setNotaCorte3(c3);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Numero invalido para corte 3.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(this, "Notas actualizadas.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        panelConsultaHorario.mostrarTexto(gestion.consultarNotas(codigo));
    }


    //  CIERRE DE SEMESTRE
  

    /**
     * Cierra el semestre académico del estudiante activo.
     * <p>
     * Validaciones previas:
     * </p>
     * <ul>
     *   <li>El código del estudiante no puede estar vacío.</li>
     *   <li>El estudiante debe existir en el sistema.</li>
     *   <li>El estudiante debe tener al menos una materia inscrita.</li>
     * </ul>
     * <p>
     * Muestra un diálogo de confirmación advirtiendo que las materias con nota
     * final {@code >= 3.0} pasarán a aprobadas y que la acción es irreversible.
     * Tras el cierre limpia el {@link panelAdicionMaterias}.
     * </p>
     */
    public void cerrarSemestre() {
        String codigo = panelRegistro.getCodigo();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Ingrese el codigo del estudiante.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Estudiante e = gestion.buscarEstudiante(codigo);
        if (e == null) {
            JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (e.getMateriasInscritas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El estudiante no tiene materias inscritas.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(this,
            "Seguro que desea cerrar el semestre?\n" +
            "Las materias con nota final >= 3.0 pasaran a aprobadas.\n" +
            "Esta accion no se puede deshacer.",
            "Confirmar Cierre", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmar != JOptionPane.YES_OPTION) return;

        String resultado = gestion.cerrarSemestre(codigo);
        panelConsultaHorario.mostrarTexto(resultado);
        panelAdicionMaterias.limpiar();

        if (!resultado.startsWith("ERROR")) {
            JOptionPane.showMessageDialog(this, "Semestre cerrado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    //  LISTADOS
  

    /**
     * Solicita a {@link GestionAcademica} el listado completo del catálogo de materias
     * y lo muestra en el área de resultados, agrupado por programa académico.
     */
    public void listarMaterias() {
        String resultado = gestion.listarMaterias();
        panelConsultaHorario.mostrarTexto(resultado);
    }

    /**
     * Solicita a {@link GestionAcademica} el listado de todos los estudiantes
     * registrados y lo muestra en el área de resultados.
     */
    public void listarEstudiantes() {
        String resultado = gestion.listarEstudiantes();
        panelConsultaHorario.mostrarTexto(resultado);
    }


    //  INFORMACIÓN DEL SISTEMA
   

    /**
     * Restaura la pantalla de bienvenida con las instrucciones de uso del sistema
     * en el área de resultados, invocando {@link panelConsultaHorario#mostrarInfo()}.
     */
    public void mostrarInfo() {
        panelConsultaHorario.mostrarInfo();
    }

  
    //  PUNTO DE ENTRADA
   

    /**
     * Punto de entrada de la aplicación.
     * <p>
     * Lanza la ventana principal en el <em>Event Dispatch Thread</em> (EDT)
     * de Swing mediante {@link SwingUtilities#invokeLater(Runnable)},
     * garantizando la seguridad de hilos de los componentes gráficos.
     * </p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGestionAca app = new InterfazGestionAca();
            app.setVisible(true);
        });
    }
}
