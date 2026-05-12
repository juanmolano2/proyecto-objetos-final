package Mundo;

import java.util.ArrayList;

/**
 * Capa de lógica de negocio del Sistema de Gestión Académica UNIBAGUE.
 * <p>
 * Actúa como fachada ({@code Facade}) entre la interfaz gráfica y los objetos
 * del dominio ({@link Estudiante} y {@link Materia}). Centraliza todas las
 * operaciones académicas disponibles:
 * </p>
 * <ul>
 *   <li>Registro y eliminación de estudiantes.</li>
 *   <li>Creación y eliminación de materias en el catálogo.</li>
 *   <li>Consulta de información, horario y calificaciones de un estudiante.</li>
 *   <li>Inscripción de materias con todas las validaciones de negocio.</li>
 *   <li>Cierre de semestre con actualización del historial académico.</li>
 *   <li>Listado completo de estudiantes y materias del catálogo.</li>
 * </ul>
 * <p>
 * Al instanciarse, carga automáticamente un catálogo predefinido de materias
 * para los tres programas disponibles y las materias comunes.
 * </p>
 *
 * @author CJ
 * @version 1.0
 * @see Estudiante
 * @see Materia
 */
public class GestionAcademica {

   
    //  CONSTANTE PÚBLICA
   

    /**
     * Arreglo con los nombres de los programas académicos disponibles en el sistema.
     * <p>
     * Es {@code public} y {@code static} para que la interfaz gráfica pueda
     * consultarlo directamente al construir los controles de selección de programa
     * (p. ej. un {@link javax.swing.JComboBox}).
     * </p>
     */
    public static final String[] PROGRAMAS = {
        "Ingenieria de Sistemas",
        "Administracion de Empresas",
        "Psicologia"
    };

   
    //  ATRIBUTOS
   

    /** Lista de todos los estudiantes actualmente registrados en el sistema. */
    private ArrayList<Estudiante> estudiantes;

    /** Catálogo de materias disponibles para inscripción. */
    private ArrayList<Materia> catalogo;

   
    //  CONSTRUCTOR
   
    /**
     * Crea una nueva instancia de la gestión académica.
     * <p>
     * Inicializa las listas de estudiantes y catálogo de materias, y ejecuta
     * {@link #cargarCatalogo()} para poblar el catálogo con las materias
     * predefinidas de los tres programas y las materias comunes.
     * </p>
     */
    public GestionAcademica() {
        estudiantes = new ArrayList<>();
        catalogo    = new ArrayList<>();
        cargarCatalogo();
    }

   
    //  CARGA INICIAL DEL CATÁLOGO
    

    /**
     * Puebla el catálogo con todas las materias predefinidas del sistema.
     * <p>
     * Carga materias para los siguientes programas (semestres 1 al 6):
     * </p>
     * <ul>
     *   <li>Materias COMUNES (compartidas por todos los programas).</li>
     *   <li>Ingeniería de Sistemas.</li>
     *   <li>Administración de Empresas.</li>
     *   <li>Psicología.</li>
     * </ul>
     * <p>
     * Este método es invocado únicamente por el constructor.
     * </p>
     */
    private void cargarCatalogo() {
        ArrayList<String> sinPre = new ArrayList<>();

        //MATERIAS COMUNES
        agregarMateria("COM101", "Comunicacion Oral y Escrita",   2, 1, "Lun 07:00-09:00",     sinPre, "COMUN");
        agregarMateria("COM102", "Etica y Ciudadania",            2, 1, "Mie 07:00-09:00",     sinPre, "COMUN");
        agregarMateria("MAT001", "Matematicas Fundamentales",     3, 1, "Mar-Jue 07:00-09:00", sinPre, "COMUN");

        // INGENIERÍA DE SISTEMAS  Semestres 1-6
        agregarMateria("IS101", "Calculo I",               3, 1, "Lun-Mie 07:00-09:00", sinPre,                  "Ingenieria de Sistemas");
        agregarMateria("IS102", "Programacion I",          3, 1, "Mar-Jue 09:00-11:00", sinPre,                  "Ingenieria de Sistemas");
        agregarMateria("IS103", "Fisica I",                3, 1, "Lun-Mie 11:00-13:00", sinPre,                  "Ingenieria de Sistemas");
        agregarMateria("IS104", "Ingles I",                2, 1, "Vie 08:00-10:00",      sinPre,                  "Ingenieria de Sistemas");
        agregarMateria("IS105", "Introduccion a la Ing.",  2, 1, "Mar 14:00-16:00",      sinPre,                  "Ingenieria de Sistemas");

        agregarMateria("IS201", "Calculo II",              3, 2, "Lun-Mie 07:00-09:00", lista("IS101"),           "Ingenieria de Sistemas");
        agregarMateria("IS202", "Programacion II",         3, 2, "Mar-Jue 09:00-11:00", lista("IS102"),           "Ingenieria de Sistemas");
        agregarMateria("IS203", "Fisica II",               3, 2, "Lun-Mie 11:00-13:00", lista("IS103"),           "Ingenieria de Sistemas");
        agregarMateria("IS204", "Ingles II",               2, 2, "Vie 10:00-12:00",      lista("IS104"),           "Ingenieria de Sistemas");
        agregarMateria("IS205", "Matematica Discreta",     3, 2, "Mar-Jue 14:00-16:00",  lista("IS101"),           "Ingenieria de Sistemas");

        agregarMateria("IS301", "Estructuras de Datos",   4, 3, "Lun-Mie 14:00-16:00", lista("IS202"),            "Ingenieria de Sistemas");
        agregarMateria("IS302", "Algebra Lineal",         3, 3, "Mar-Jue 07:00-09:00",  lista("IS201"),            "Ingenieria de Sistemas");
        agregarMateria("IS303", "Bases de Datos I",       3, 3, "Vie 07:00-10:00",      lista("IS202"),            "Ingenieria de Sistemas");
        agregarMateria("IS304", "Ingles III",             2, 3, "Mie 10:00-12:00",       lista("IS204"),            "Ingenieria de Sistemas");
        agregarMateria("IS305", "Electronica Digital",    3, 3, "Lun-Mie 09:00-11:00",  lista("IS203"),            "Ingenieria de Sistemas");

        agregarMateria("IS401", "Algoritmos",             4, 4, "Lun-Mie 09:00-11:00", lista("IS301"),             "Ingenieria de Sistemas");
        agregarMateria("IS402", "Sistemas Operativos",    3, 4, "Mar-Jue 11:00-13:00",  lista("IS301"),             "Ingenieria de Sistemas");
        agregarMateria("IS403", "Bases de Datos II",      3, 4, "Vie 07:00-10:00",      lista("IS303"),             "Ingenieria de Sistemas");
        agregarMateria("IS404", "Redes de Computadores",  3, 4, "Lun-Mie 07:00-09:00", lista("IS205", "IS301"),    "Ingenieria de Sistemas");
        agregarMateria("IS405", "Ingenieria de Software I", 3, 4, "Mar-Jue 14:00-16:00", lista("IS301", "IS303"),  "Ingenieria de Sistemas");

        agregarMateria("IS501", "Compiladores",           4, 5, "Lun-Mie 11:00-13:00", lista("IS401", "IS402"),    "Ingenieria de Sistemas");
        agregarMateria("IS502", "Arquitectura de Comp.",  3, 5, "Mar-Jue 07:00-09:00",  lista("IS402"),             "Ingenieria de Sistemas");
        agregarMateria("IS503", "Ingenieria de Software II", 3, 5, "Vie 09:00-12:00",   lista("IS405"),             "Ingenieria de Sistemas");
        agregarMateria("IS504", "Seguridad Informatica",  3, 5, "Lun-Mie 09:00-11:00",  lista("IS404"),             "Ingenieria de Sistemas");
        agregarMateria("IS505", "Inteligencia Artificial", 4, 5, "Mar-Jue 14:00-16:00", lista("IS401", "IS302"),    "Ingenieria de Sistemas");

        agregarMateria("IS601", "Proyecto de Grado I",    4, 6, "Lun-Mie 07:00-09:00", lista("IS503"),             "Ingenieria de Sistemas");
        agregarMateria("IS602", "Electiva I",             3, 6, "Mar-Jue 09:00-11:00",  sinPre,                     "Ingenieria de Sistemas");
        agregarMateria("IS603", "Gestion de TI",          3, 6, "Vie 07:00-10:00",      lista("IS405"),             "Ingenieria de Sistemas");

        // ADMINISTRACIÓN DE EMPRESAS  Semestres 1-6 
        agregarMateria("AE101", "Fundamentos de Administracion",  3, 1, "Lun-Mie 07:00-09:00", sinPre,                  "Administracion de Empresas");
        agregarMateria("AE102", "Matematicas Empresariales I",    3, 1, "Mar-Jue 09:00-11:00", sinPre,                  "Administracion de Empresas");
        agregarMateria("AE103", "Contabilidad Basica",            3, 1, "Lun-Mie 11:00-13:00", sinPre,                  "Administracion de Empresas");
        agregarMateria("AE104", "Introduccion a la Economia",     2, 1, "Vie 08:00-10:00",      sinPre,                  "Administracion de Empresas");
        agregarMateria("AE105", "Ingles Empresarial I",           2, 1, "Mar 14:00-16:00",      sinPre,                  "Administracion de Empresas");

        agregarMateria("AE201", "Teoria Organizacional",          3, 2, "Lun-Mie 07:00-09:00", lista("AE101"),           "Administracion de Empresas");
        agregarMateria("AE202", "Matematicas Empresariales II",   3, 2, "Mar-Jue 09:00-11:00", lista("AE102"),           "Administracion de Empresas");
        agregarMateria("AE203", "Contabilidad de Costos",         3, 2, "Lun-Mie 11:00-13:00", lista("AE103"),           "Administracion de Empresas");
        agregarMateria("AE204", "Microeconomia",                  3, 2, "Vie 09:00-12:00",      lista("AE104"),           "Administracion de Empresas");
        agregarMateria("AE205", "Ingles Empresarial II",          2, 2, "Mar 14:00-16:00",      lista("AE105"),           "Administracion de Empresas");

        agregarMateria("AE301", "Gestion del Talento Humano",     3, 3, "Lun-Mie 09:00-11:00", lista("AE201"),           "Administracion de Empresas");
        agregarMateria("AE302", "Estadistica Empresarial",        3, 3, "Mar-Jue 07:00-09:00",  lista("AE202"),           "Administracion de Empresas");
        agregarMateria("AE303", "Finanzas I",                     4, 3, "Lun-Mie 14:00-16:00", lista("AE203"),           "Administracion de Empresas");
        agregarMateria("AE304", "Macroeconomia",                  3, 3, "Vie 07:00-10:00",      lista("AE204"),           "Administracion de Empresas");
        agregarMateria("AE305", "Derecho Comercial",              2, 3, "Jue 14:00-16:00",      sinPre,                   "Administracion de Empresas");

        agregarMateria("AE401", "Direccion Estrategica",          4, 4, "Lun-Mie 07:00-09:00", lista("AE301", "AE201"),  "Administracion de Empresas");
        agregarMateria("AE402", "Investigacion de Mercados",      3, 4, "Mar-Jue 11:00-13:00",  lista("AE302"),           "Administracion de Empresas");
        agregarMateria("AE403", "Finanzas II",                    4, 4, "Lun-Mie 11:00-13:00", lista("AE303"),           "Administracion de Empresas");
        agregarMateria("AE404", "Comercio Internacional",         3, 4, "Vie 07:00-10:00",      lista("AE304"),           "Administracion de Empresas");
        agregarMateria("AE405", "Marketing Empresarial",          3, 4, "Jue 09:00-11:00",      lista("AE402"),           "Administracion de Empresas");

        agregarMateria("AE501", "Emprendimiento e Innovacion",    3, 5, "Lun-Mie 09:00-11:00", lista("AE401"),            "Administracion de Empresas");
        agregarMateria("AE502", "Gestion de Proyectos",           3, 5, "Mar-Jue 14:00-16:00",  lista("AE401", "AE302"),  "Administracion de Empresas");
        agregarMateria("AE503", "Auditoria y Control",            3, 5, "Lun-Mie 14:00-16:00", lista("AE403"),            "Administracion de Empresas");
        agregarMateria("AE504", "Negocios Electronicos",          2, 5, "Vie 09:00-11:00",      lista("AE402", "AE405"),  "Administracion de Empresas");

        agregarMateria("AE601", "Trabajo de Grado",               4, 6, "Lun-Mie 07:00-09:00", lista("AE502"),            "Administracion de Empresas");
        agregarMateria("AE602", "Electiva Empresarial",           3, 6, "Mar-Jue 09:00-11:00",  sinPre,                    "Administracion de Empresas");

        // PSICOLOGÍA Semestres 1-6
        agregarMateria("PS101", "Historia de la Psicologia",      3, 1, "Lun-Mie 07:00-09:00", sinPre, "Psicologia");
        agregarMateria("PS102", "Biologia y Conducta",            3, 1, "Mar-Jue 09:00-11:00", sinPre, "Psicologia");
        agregarMateria("PS103", "Introduccion a la Psicologia",   3, 1, "Lun-Mie 11:00-13:00", sinPre, "Psicologia");
        agregarMateria("PS104", "Estadistica Basica",             3, 1, "Vie 08:00-11:00",      sinPre, "Psicologia");
        agregarMateria("PS105", "Filosofia de la Mente",          2, 1, "Jue 14:00-16:00",      sinPre, "Psicologia");

        agregarMateria("PS201", "Psicologia del Desarrollo I",    3, 2, "Lun-Mie 07:00-09:00", lista("PS103"),           "Psicologia");
        agregarMateria("PS202", "Psicobiologia",                  3, 2, "Mar-Jue 09:00-11:00", lista("PS102"),           "Psicologia");
        agregarMateria("PS203", "Procesos Psicologicos Basicos",  3, 2, "Lun-Mie 11:00-13:00", lista("PS103"),           "Psicologia");
        agregarMateria("PS204", "Estadistica Inferencial",        3, 2, "Vie 08:00-11:00",      lista("PS104"),           "Psicologia");
        agregarMateria("PS205", "Teoria del Conocimiento",        2, 2, "Jue 14:00-16:00",      lista("PS105"),           "Psicologia");

        agregarMateria("PS301", "Psicologia del Desarrollo II",   3, 3, "Lun-Mie 07:00-09:00", lista("PS201"),           "Psicologia");
        agregarMateria("PS302", "Fundamentos de Psicopatologia",  3, 3, "Mar-Jue 09:00-11:00", lista("PS203"),           "Psicologia");
        agregarMateria("PS303", "Psicologia Social",              3, 3, "Lun-Mie 11:00-13:00", lista("PS203"),           "Psicologia");
        agregarMateria("PS304", "Metodologia de Investigacion",   3, 3, "Vie 08:00-11:00",      lista("PS204"),           "Psicologia");
        agregarMateria("PS305", "Neurociencias",                  3, 3, "Mar-Jue 14:00-16:00",  lista("PS202"),           "Psicologia");

        agregarMateria("PS401", "Evaluacion Psicologica I",       4, 4, "Lun-Mie 07:00-09:00", lista("PS302", "PS304"),  "Psicologia");
        agregarMateria("PS402", "Psicologia Clinica I",           3, 4, "Mar-Jue 09:00-11:00", lista("PS302"),           "Psicologia");
        agregarMateria("PS403", "Psicologia Organizacional",      3, 4, "Lun-Mie 11:00-13:00", lista("PS303"),           "Psicologia");
        agregarMateria("PS404", "Diseno de Investigacion",        3, 4, "Vie 08:00-11:00",      lista("PS304"),           "Psicologia");
        agregarMateria("PS405", "Psicologia Cognitiva",           3, 4, "Jue 14:00-16:00",      lista("PS305", "PS203"),  "Psicologia");

        agregarMateria("PS501", "Evaluacion Psicologica II",      4, 5, "Lun-Mie 07:00-09:00", lista("PS401"),            "Psicologia");
        agregarMateria("PS502", "Psicologia Clinica II",          4, 5, "Mar-Jue 09:00-11:00", lista("PS402"),            "Psicologia");
        agregarMateria("PS503", "Intervencion Psicosocial",       3, 5, "Lun-Mie 11:00-13:00", lista("PS403", "PS303"),  "Psicologia");
        agregarMateria("PS504", "Psicodiagnostico",               3, 5, "Vie 08:00-11:00",      lista("PS401", "PS402"),  "Psicologia");
        agregarMateria("PS505", "Salud Mental Comunitaria",       3, 5, "Jue 14:00-16:00",      lista("PS502"),            "Psicologia");

        agregarMateria("PS601", "Practica Profesional",           4, 6, "Lun-Mie 07:00-09:00", lista("PS502"),            "Psicologia");
        agregarMateria("PS602", "Electiva de Profundizacion",     3, 6, "Mar-Jue 09:00-11:00",  sinPre,                    "Psicologia");
    }

    /**
     * Crea un objeto {@link Materia} y lo agrega directamente al catálogo.
     * <p>
     * Método auxiliar privado para simplificar la carga masiva de materias
     * en {@link #cargarCatalogo()}.
     * </p>
     *
     * @param cod      código de la materia
     * @param nom      nombre de la materia
     * @param cred     créditos académicos
     * @param sem      semestre en que se dicta
     * @param hor      horario de la materia
     * @param pre      lista de códigos prerrequisito
     * @param programa programa al que pertenece la materia
     */
    private void agregarMateria(String cod, String nom, int cred, int sem,
                                 String hor, ArrayList<String> pre, String programa) {
        catalogo.add(new Materia(cod, nom, cred, sem, hor, pre, programa));
    }

    /**
     * Crea y retorna un {@link ArrayList} de cadenas a partir de los argumentos
     * variables recibidos.
     * <p>
     * Método auxiliar privado utilizado para construir listas de prerrequisitos
     * de forma concisa dentro de {@link #cargarCatalogo()}.
     * </p>
     *
     * @param items códigos de materias prerrequisito
     * @return lista con los códigos proporcionados
     */
    private ArrayList<String> lista(String... items) {
        ArrayList<String> l = new ArrayList<>();
        for (String s : items) l.add(s);
        return l;
    }

  
    //  OPERACIONES SOBRE ESTUDIANTES
  

    /**
     * Registra un nuevo estudiante en el sistema aplicando las siguientes validaciones:
     * <ul>
     *   <li>Código y nombre no pueden estar vacíos.</li>
     *   <li>El programa debe ser uno de los definidos en {@link #PROGRAMAS}.</li>
     *   <li>El semestre debe estar en el rango 1-6.</li>
     *   <li>No debe existir ya un estudiante con el mismo código.</li>
     * </ul>
     *
     * @param codigo   código único del estudiante
     * @param nombre   nombre completo del estudiante
     * @param programa programa académico (debe coincidir con uno de {@link #PROGRAMAS})
     * @param semestre semestre actual del estudiante (1-6)
     * @return mensaje de confirmación del registro, o mensaje de error si alguna
     *         validación falla (comienza con {@code "ERROR:"})
     */
    public String registrarEstudiante(String codigo, String nombre, String programa, int semestre) {
        if (codigo.isEmpty() || nombre.isEmpty() || programa.isEmpty()) {
            return "ERROR: Todos los campos son obligatorios.";
        }
        boolean programaValido = false;
        for (String p : PROGRAMAS) {
            if (p.equalsIgnoreCase(programa)) { programaValido = true; break; }
        }
        if (!programaValido) {
            String opciones = String.join(", ", PROGRAMAS);
            return "ERROR: Programa no reconocido. Programas disponibles: " + opciones;
        }
        if (semestre < 1 || semestre > 6) {
            return "ERROR: El semestre debe estar entre 1 y 6.";
        }
        if (buscarEstudiante(codigo) != null) {
            return "ERROR: Ya existe un estudiante con el codigo " + codigo + ".";
        }
        estudiantes.add(new Estudiante(codigo, nombre, programa, semestre, new ArrayList<>()));
        return "Estudiante '" + nombre + "' registrado.\n" +
               "Programa: " + programa + " | Semestre: " + semestre;
    }

    /**
     * Elimina un estudiante del sistema buscándolo por su código.
     *
     * @param codigo código del estudiante a eliminar
     * @return mensaje de confirmación de la eliminación, o mensaje de error si
     *         no se encuentra el estudiante (comienza con {@code "ERROR:"})
     */
    public String eliminarEstudiante(String codigo) {
        Estudiante e = buscarEstudiante(codigo);
        if (e == null) return "ERROR: Estudiante con codigo '" + codigo + "' no encontrado.";
        estudiantes.remove(e);
        return "Estudiante '" + e.getNombre() + "' eliminado del sistema.";
    }

    /**
     * Retorna la información completa de un estudiante identificado por su código.
     *
     * @param codigo código del estudiante a consultar
     * @return cadena con la información completa del estudiante,
     *         o mensaje de error si no existe
     * @see Estudiante#getInfoCompleta()
     */
    public String consultarEstudiante(String codigo) {
        Estudiante e = buscarEstudiante(codigo);
        if (e == null) return "Estudiante con codigo '" + codigo + "' no encontrado.";
        return e.getInfoCompleta();
    }

    /**
     * Retorna el horario del semestre vigente del estudiante identificado por su código.
     *
     * @param codigo código del estudiante
     * @return cadena formateada con el horario, o mensaje de error si no existe
     * @see Estudiante#buscarHorario()
     */
    public String consultarHorario(String codigo) {
        Estudiante e = buscarEstudiante(codigo);
        if (e == null) return "Estudiante con codigo '" + codigo + "' no encontrado.";
        return e.buscarHorario();
    }

    /**
     * Retorna el reporte de calificaciones del estudiante identificado por su código.
     *
     * @param codigoEstudiante código del estudiante
     * @return cadena formateada con los tres cortes y la nota final de cada materia;
     *         mensaje de error si no existe el estudiante
     * @see Estudiante#buscarNotas()
     */
    public String consultarNotas(String codigoEstudiante) {
        Estudiante e = buscarEstudiante(codigoEstudiante);
        if (e == null) return "Estudiante con codigo '" + codigoEstudiante + "' no encontrado.";
        return e.buscarNotas();
    }

    /**
     * Cierra el semestre académico del estudiante identificado por su código.
     * <p>
     * Las materias con nota final {@code >= 3.0} pasan al historial de aprobadas.
     * La lista de materias inscritas queda vacía al finalizar.
     * </p>
     *
     * @param codigoEstudiante código del estudiante
     * @return resumen del cierre con estado de cada materia, o mensaje de error
     * @see Estudiante#cerrarSemestre()
     */
    public String cerrarSemestre(String codigoEstudiante) {
        Estudiante e = buscarEstudiante(codigoEstudiante);
        if (e == null) return "Estudiante con codigo '" + codigoEstudiante + "' no encontrado.";
        return e.cerrarSemestre();
    }

    /**
     * Lista de forma resumida todos los estudiantes actualmente registrados en el sistema.
     * <p>
     * Cada línea incluye código, nombre, programa, semestre y número de materias inscritas.
     * Al final muestra el total de estudiantes.
     * </p>
     *
     * @return cadena con el listado completo, o aviso si no hay estudiantes registrados
     */
    public String listarEstudiantes() {
        if (estudiantes.isEmpty()) return "No hay estudiantes registrados.";
        StringBuilder sb = new StringBuilder("=== ESTUDIANTES REGISTRADOS ===\n\n");
        for (Estudiante e : estudiantes) {
            sb.append("[").append(e.getCodigo()).append("] ")
              .append(e.getNombre())
              .append(" | ").append(e.getPrograma())
              .append(" | Sem:").append(e.getSemestre())
              .append(" | Materias:").append(e.getMateriasInscritas().size()).append("\n");
        }
        sb.append("\nTotal: ").append(estudiantes.size()).append(" estudiantes.");
        return sb.toString();
    }

 
    //  OPERACIONES SOBRE MATERIAS
  

    /**
     * Crea una nueva materia y la agrega al catálogo, aplicando las siguientes validaciones:
     * <ul>
     *   <li>Código, nombre y horario no pueden estar vacíos.</li>
     *   <li>Los créditos deben estar en el rango 1-10.</li>
     *   <li>El semestre debe estar en el rango 1-6.</li>
     *   <li>No debe existir ya una materia con el mismo código en el catálogo.</li>
     *   <li>El programa debe ser uno de los definidos en {@link #PROGRAMAS} o {@code "COMUN"}.</li>
     * </ul>
     *
     * @param codigo        código único de la nueva materia
     * @param nombre        nombre descriptivo de la materia
     * @param creditos      créditos académicos (1-10)
     * @param semestre      semestre en que se dicta (1-6)
     * @param horario       horario en formato {@code "Día HH:MM-HH:MM"}
     * @param prerequisitos lista de códigos de materias prerrequisito; puede ser vacía
     * @param programa      programa al que pertenece, o {@code "COMUN"}
     * @return mensaje de confirmación de creación, o mensaje de error si alguna
     *         validación falla (comienza con {@code "ERROR:"})
     */
    public String crearMateria(String codigo, String nombre, int creditos,
                                int semestre, String horario, ArrayList<String> prerequisitos, String programa) {
        if (codigo.isEmpty() || nombre.isEmpty() || horario.isEmpty()) {
            return "ERROR: Codigo, nombre y horario son obligatorios.";
        }
        if (creditos < 1 || creditos > 10) {
            return "ERROR: Los creditos deben estar entre 1 y 10.";
        }
        if (semestre < 1 || semestre > 6) {
            return "ERROR: El semestre debe estar entre 1 y 6.";
        }
        for (Materia m : catalogo) {
            if (m.getCodigo().equalsIgnoreCase(codigo)) {
                return "ERROR: Ya existe una materia con el codigo '" + codigo + "'.";
            }
        }
        boolean programaValido = programa.equalsIgnoreCase("COMUN");
        for (String p : PROGRAMAS) {
            if (p.equalsIgnoreCase(programa)) { programaValido = true; break; }
        }
        if (!programaValido) {
            return "ERROR: Programa no valido. Use COMUN o uno de: " + String.join(", ", PROGRAMAS);
        }
        catalogo.add(new Materia(codigo, nombre, creditos, semestre, horario, prerequisitos, programa));
        return "Materia '" + nombre + "' (" + codigo + ") creada exitosamente en semestre " + semestre + ".";
    }

    /**
     * Elimina una materia del catálogo buscándola por su código.
     *
     * @param codigoMateria código de la materia a eliminar
     * @return mensaje de confirmación de la eliminación, o mensaje de error si
     *         no existe la materia (comienza con {@code "ERROR:"})
     */
    public String eliminarMateria(String codigoMateria) {
        Materia m = buscarMateria(codigoMateria);
        if (m == null) return "ERROR: Materia con codigo '" + codigoMateria + "' no encontrada.";
        catalogo.remove(m);
        return "Materia '" + m.getNombre() + "' eliminada del catalogo.";
    }

    /**
     * Lista de forma agrupada por programa todas las materias disponibles en el catálogo.
     * <p>
     * Para cada materia muestra código, nombre, semestre, créditos y horario.
     * Al final indica el total de materias en el catálogo.
     * </p>
     *
     * @return cadena formateada con el catálogo completo, o aviso si el catálogo está vacío
     */
    public String listarMaterias() {
        if (catalogo.isEmpty()) return "No hay materias en el catalogo.";
        StringBuilder sb = new StringBuilder("=== CATALOGO DE MATERIAS ===\n\n");
        String programaActual = "";
        for (Materia m : catalogo) {
            if (!m.getPrograma().equals(programaActual)) {
                programaActual = m.getPrograma();
                sb.append("-- ").append(programaActual).append(" --\n");
            }
            sb.append("  [").append(m.getCodigo()).append("] ")
              .append(m.getNombre())
              .append(" | Sem:").append(m.getSemestre())
              .append(" | Cred:").append(m.getCreditos())
              .append(" | ").append(m.getHorario()).append("\n");
        }
        sb.append("\nTotal: ").append(catalogo.size()).append(" materias.");
        return sb.toString();
    }

   
    //  INSCRIPCIÓN DE MATERIAS
  

    /**
     * Retorna la lista de materias del catálogo que un estudiante puede inscribir
     * en el semestre actual.
     * <p>
     * Una materia es "disponible" si cumple todas estas condiciones:
     * <ol>
     *   <li>No está ya inscrita por el estudiante.</li>
     *   <li>No ha sido aprobada previamente.</li>
     *   <li>Pertenece al programa del estudiante o es {@code "COMUN"}.</li>
     *   <li>El estudiante cumple todos sus prerrequisitos.</li>
     * </ol>
     * </p>
     * <p>
     * <b>Nota:</b> no se filtra por límite de créditos; ese control lo realiza
     * {@link Estudiante#agregarMateria(Materia)} al momento de la inscripción efectiva.
     * </p>
     *
     * @param codigoEstudiante código del estudiante
     * @return lista de materias disponibles; lista vacía si el estudiante no existe
     *         o si no hay materias que pueda inscribir
     */
    public ArrayList<Materia> getMateriasDisponibles(String codigoEstudiante) {
        Estudiante e = buscarEstudiante(codigoEstudiante);
        if (e == null) return new ArrayList<>();
        ArrayList<Materia> disponibles = new ArrayList<>();
        for (Materia m : catalogo) {
            boolean yaInscrita = false;
            for (Materia ins : e.getMateriasInscritas()) {
                if (ins.getCodigo().equals(m.getCodigo())) { yaInscrita = true; break; }
            }
            if (yaInscrita) continue;
            if (e.getMateriasAprobadas().contains(m.getCodigo())) continue;
            boolean esDelPrograma = m.getPrograma().equalsIgnoreCase("COMUN") ||
                                    m.getPrograma().equalsIgnoreCase(e.getPrograma());
            if (!esDelPrograma) continue;
            if (m.verificarPrereq(e.getMateriasAprobadas())) {
                disponibles.add(m);
            }
        }
        return disponibles;
    }

    /**
     * Inscribe una materia del catálogo a un estudiante, delegando todas las
     * validaciones de negocio a {@link Estudiante#agregarMateria(Materia)}.
     *
     * @param codigoEstudiante código del estudiante
     * @param codigoMateria    código de la materia a inscribir
     * @return mensaje de resultado: éxito o descripción del motivo de rechazo;
     *         mensaje de error si el estudiante o la materia no existen
     */
    public String inscribirMateria(String codigoEstudiante, String codigoMateria) {
        Estudiante e = buscarEstudiante(codigoEstudiante);
        if (e == null) return "Estudiante no encontrado.";
        Materia m = buscarMateria(codigoMateria);
        if (m == null) return "Materia con codigo '" + codigoMateria + "' no existe en el catalogo.";
        return e.agregarMateria(m);
    }

   
    //  BÚSQUEDAS INTERNAS


    /**
     * Busca y retorna un estudiante por su código (insensible a mayúsculas/minúsculas).
     *
     * @param codigo código del estudiante a buscar
     * @return el objeto {@link Estudiante} encontrado, o {@code null} si no existe
     */
    public Estudiante buscarEstudiante(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) return e;
        }
        return null;
    }

    /**
     * Busca y retorna una materia por su código en el catálogo
     * (insensible a mayúsculas/minúsculas).
     *
     * @param codigo código de la materia a buscar
     * @return el objeto {@link Materia} encontrado, o {@code null} si no existe
     */
    public Materia buscarMateria(String codigo) {
        for (Materia m : catalogo) {
            if (m.getCodigo().equalsIgnoreCase(codigo)) return m;
        }
        return null;
    }


    //  GETTERS DE LAS COLECCIONES


    /**
     * Retorna el catálogo completo de materias del sistema.
     *
     * @return lista de todas las materias registradas en el catálogo
     */
    public ArrayList<Materia> getCatalogo() 
    { 
    	return catalogo;
    	
    }

    /**
     * Retorna la lista completa de estudiantes registrados en el sistema.
     *
     * @return lista de todos los estudiantes registrados
     */
    public ArrayList<Estudiante> getEstudiantes() 
    { 
    	return estudiantes; 
    	
    }
}
