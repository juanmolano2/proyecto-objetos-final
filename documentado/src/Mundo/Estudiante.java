package Mundo;

import java.util.ArrayList;

/**
 * Representa a un estudiante universitario dentro del sistema de gestión académica.
 * <p>
 * Un estudiante posee información de identificación (código, nombre, programa y semestre)
 * y gestiona dos colecciones de materias:
 * <ul>
 *   <li><b>Materias inscritas:</b> las que cursa actualmente en el semestre vigente.</li>
 *   <li><b>Materias aprobadas:</b> códigos de materias superadas en semestres anteriores,
 *       usados para verificar prerrequisitos.</li>
 * </ul>
 * </p>
 * <p>
 * El límite de créditos inscritos simultáneamente está fijado en {@value #MAX_CREDITOS}.
 * </p>
 *
 * @author CJ
 * @version 1.0
 * @see Materia
 */
public class Estudiante {

  
    //  CONSTANTE
   

    /**
     * Máximo de créditos académicos que un estudiante puede inscribir en un semestre.
     * Si intentar agregar una materia supera este límite, la inscripción es rechazada.
     */
    private static final int MAX_CREDITOS = 20;


    //  ATRIBUTOS
    

    /** Código único de identificación del estudiante (p. ej. {@code "2024001"}). */
    String codigo;

    /** Nombre completo del estudiante. */
    String nombre;

    /** Programa académico en el que está matriculado el estudiante. */
    String programa;

    /** Semestre actual del estudiante (rango 1-6). */
    int semestre;

    /**
     * Lista de códigos de materias que el estudiante ya aprobó en semestres anteriores.
     * Se utiliza para verificar el cumplimiento de prerrequisitos al inscribir nuevas materias.
     */
    ArrayList<String> materiasAprobadas;

    /**
     * Lista de objetos {@link Materia} inscritos en el semestre vigente.
     * Se vacía al cerrar el semestre, y las materias aprobadas pasan a {@link #materiasAprobadas}.
     */
    ArrayList<Materia> materiasInscritas;


    //  CONSTRUCTOR
   

    /**
     * Crea un nuevo estudiante con su información básica de registro.
     * <p>
     * La lista de materias inscritas se inicializa vacía; las materias aprobadas
     * se reciben como parámetro para poder registrar estudiantes avanzados que
     * ya tienen historia académica.
     * </p>
     *
     * @param codigo            código único del estudiante
     * @param nombre            nombre completo del estudiante
     * @param programa          programa académico en el que está matriculado
     * @param semestre          semestre actual del estudiante (1-6)
     * @param materiasAprobadas lista de códigos de materias ya aprobadas previamente
     */
    public Estudiante(String codigo, String nombre, String programa,
                      int semestre, ArrayList<String> materiasAprobadas) {
        this.codigo            = codigo;
        this.nombre            = nombre;
        this.programa          = programa;
        this.semestre          = semestre;
        this.materiasAprobadas = materiasAprobadas;
        this.materiasInscritas = new ArrayList<>();
    }

   
    //  GETTERS
  

    /**
     * Retorna el código de identificación del estudiante.
     *
     * @return código del estudiante
     */
    public String getCodigo() 
    { 
    	return codigo; 
    
    }

    /**
     * Retorna el nombre completo del estudiante.
     *
     * @return nombre del estudiante
     */
    public String getNombre() 
    { 
    	return nombre; 
    	
    }

    /**
     * Retorna el nombre del programa académico del estudiante.
     *
     * @return programa del estudiante
     */
    public String getPrograma() 
    {
    	return programa; 
    }

    /**
     * Retorna el semestre actual del estudiante.
     *
     * @return semestre (rango 1-6)
     */
    public int getSemestre() 
    { 
    	return semestre; 
    	
    }

    /**
     * Retorna la lista de materias inscritas en el semestre vigente.
     *
     * @return lista de objetos {@link Materia} actualmente inscritos
     */
    public ArrayList<Materia> getMateriasInscritas() 
    { 
    	return materiasInscritas; 
    	
    }

    /**
     * Retorna la lista de códigos de materias ya aprobadas por el estudiante.
     *
     * @return lista de códigos de materias aprobadas
     */
    public ArrayList<String> getMateriasAprobadas() 
    { 
    	return materiasAprobadas; 
    	
    }

  
    //  MÉTODOS DE CRÉDITOS E INSCRIPCIÓN
  

    /**
     * Calcula el total de créditos actualmente inscritos por el estudiante.
     * <p>
     * Suma los créditos de todas las materias presentes en {@link #materiasInscritas}.
     * </p>
     *
     * @return suma total de créditos inscritos
     */
    public int getTotalCreditos() {
        int total = 0;
        for (Materia m : materiasInscritas) total += m.getCreditos();
        return total;
    }

    /**
     * Intenta inscribir una materia al estudiante aplicando todas las validaciones
     * de negocio en el siguiente orden:
     * <ol>
     *   <li>La materia no debe estar ya inscrita.</li>
     *   <li>La materia no debe haber sido ya aprobada.</li>
     *   <li>La materia debe pertenecer al programa del estudiante o ser {@code "COMUN"}.</li>
     *   <li>El estudiante debe cumplir todos los prerrequisitos de la materia.</li>
     *   <li>Inscribir la materia no debe superar el límite de {@value #MAX_CREDITOS} créditos.</li>
     * </ol>
     * <p>
     * Si todas las validaciones pasan, la materia es agregada a {@link #materiasInscritas}.
     * </p>
     *
     * @param m materia que se desea inscribir
     * @return mensaje de resultado: éxito o descripción del motivo de rechazo
     */
    public String agregarMateria(Materia m) {
        // ¿Ya está inscrita?
        for (Materia ins : materiasInscritas) {
            if (ins.getCodigo().equals(m.getCodigo())) {
                return "Ya tienes la materia '" + m.getNombre() + "' inscrita.";
            }
        }
        // ¿Ya fue aprobada?
        if (materiasAprobadas.contains(m.getCodigo())) {
            return "Ya aprobaste la materia '" + m.getNombre() + "'. No necesitas inscribirla de nuevo.";
        }
        // ¿Pertenece a otro programa?
        if (!m.getPrograma().equalsIgnoreCase("COMUN") && !m.getPrograma().equalsIgnoreCase(programa)) {
            return "La materia '" + m.getNombre() + "' pertenece al programa '" +
                   m.getPrograma() + "'. Tu programa es '" + programa + "'.";
        }
        // ¿Cumple prerrequisitos?
        if (!m.verificarPrereq(materiasAprobadas)) {
            String faltantes = "";
            for (String pre : m.getPrerequisitos()) {
                if (!materiasAprobadas.contains(pre)) faltantes += pre + " ";
            }
            return "Faltan prerrequisitos para '" + m.getNombre() + "': " + faltantes.trim();
        }
        // ¿Quedan créditos disponibles?
        if (getTotalCreditos() + m.getCreditos() > MAX_CREDITOS) {
            return "No puedes inscribir '" + m.getNombre() +
                   "': excede el limite de " + MAX_CREDITOS + " creditos. " +
                   "(Tienes " + getTotalCreditos() + "/" + MAX_CREDITOS + ")";
        }
        materiasInscritas.add(m);
        return "Materia '" + m.getNombre() + "' inscrita exitosamente. " +
               "Creditos: " + getTotalCreditos() + "/" + MAX_CREDITOS;
    }

  
    //  MÉTODOS DE CONSULTA
  

    /**
     * Genera y retorna el horario del estudiante con todas sus materias inscritas.
     * <p>
     * El horario incluye código, nombre, franja horaria y créditos de cada materia,
     * más el total de créditos inscritos al final.
     * Si el estudiante no tiene materias inscritas, retorna un aviso informativo.
     * </p>
     *
     * @return cadena formateada con el horario del estudiante
     */
    public String buscarHorario() {
        if (materiasInscritas.isEmpty()) {
            return "No tienes materias inscritas aun.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Horario de ").append(nombre).append(" (").append(codigo).append(")\n");
        sb.append("-".repeat(60)).append("\n");
        for (Materia m : materiasInscritas) {
            sb.append(String.format("  %-8s %-28s %s  (%d cred)\n",
                    m.getCodigo(), m.getNombre(), m.getHorario(), m.getCreditos()));
        }
        sb.append("-".repeat(60)).append("\n");
        sb.append("Total creditos inscritos: ").append(getTotalCreditos()).append("/").append(MAX_CREDITOS);
        return sb.toString();
    }

    /**
     * Genera y retorna un reporte de las calificaciones del estudiante.
     * <p>
     * Muestra los tres cortes y la nota final de cada materia inscrita.
     * Al final del reporte incluye el promedio de todas las materias que
     * ya tienen nota final calculada.
     * Las notas aún no registradas se muestran como {@code " - "}.
     * </p>
     *
     * @return cadena formateada con las calificaciones del estudiante;
     *         aviso si no hay materias inscritas
     */
    public String buscarNotas() {
        if (materiasInscritas.isEmpty()) {
            return "No tienes materias inscritas.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Calificaciones de ").append(nombre).append("\n");
        sb.append("-".repeat(65)).append("\n");
        sb.append(String.format("  %-26s  %8s  %8s  %8s  %8s\n", "Materia", "Corte1", "Corte2", "Corte3", "Final"));
        sb.append("-".repeat(65)).append("\n");

        double sumaNotas   = 0;
        int    calificadas = 0;

        for (Materia m : materiasInscritas) {
            String c1 = m.getNotaCorte1() < 0 ? "  -  " : String.format("%.1f", m.getNotaCorte1());
            String c2 = m.getNotaCorte2() < 0 ? "  -  " : String.format("%.1f", m.getNotaCorte2());
            String c3 = m.getNotaCorte3() < 0 ? "  -  " : String.format("%.1f", m.getNotaCorte3());
            String cf = m.getNotaFinal()  < 0 ? "  -  " : String.format("%.2f", m.getNotaFinal());
            sb.append(String.format("  %-26s  %8s  %8s  %8s  %8s\n", m.getNombre(), c1, c2, c3, cf));
            if (m.getNotaFinal() >= 0) {
                sumaNotas += m.getNotaFinal();
                calificadas++;
            }
        }

        if (calificadas > 0) {
            sb.append("-".repeat(65)).append("\n");
            sb.append(String.format("  Promedio final: %.2f", sumaNotas / calificadas));
        }
        return sb.toString();
    }


    //  CIERRE DE SEMESTRE
  
    /**
     * Cierra el semestre académico del estudiante procesando los resultados finales.
     * <p>
     * El proceso de cierre:
     * <ol>
     *   <li>Verifica que todas las materias inscritas tengan los tres cortes calificados;
     *       si alguna está incompleta, aborta el cierre con un mensaje de error.</li>
     *   <li>Recorre cada materia inscrita: si la nota final es {@code >= 3.0},
     *       su código se agrega a {@link #materiasAprobadas}.</li>
     *   <li>Vacía la lista {@link #materiasInscritas}, dejando al estudiante listo
     *       para inscribir materias del siguiente semestre.</li>
     * </ol>
     * </p>
     *
     * @return resumen del cierre con el estado (aprobada/reprobada) y nota de cada materia;
     *         mensaje de error si algún corte está pendiente o si no hay materias inscritas
     */
    public String cerrarSemestre() {
        if (materiasInscritas.isEmpty()) {
            return "No tienes materias inscritas para cerrar.";
        }
        // Verificar que todas las materias tengan los 3 cortes
        for (Materia m : materiasInscritas) {
            if (!m.tieneNotaCompleta()) {
                return "ERROR: La materia '" + m.getNombre() + "' aun no tiene los 3 cortes ingresados. " +
                       "Debes calificar todos los cortes antes de cerrar el semestre.";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Resumen cierre de semestre ").append(semestre).append(" - ").append(nombre).append("\n");
        sb.append("-".repeat(50)).append("\n");
        int aprobadas  = 0;
        int reprobadas = 0;
        for (Materia m : materiasInscritas) {
            if (m.getNotaFinal() >= 3.0) {
                materiasAprobadas.add(m.getCodigo());
                sb.append("  APROBADA  ").append(String.format("%-28s", m.getNombre()))
                  .append(String.format("%.2f\n", m.getNotaFinal()));
                aprobadas++;
            } else {
                sb.append("  REPROBADA ").append(String.format("%-28s", m.getNombre()))
                  .append(String.format("%.2f\n", m.getNotaFinal()));
                reprobadas++;
            }
        }
        sb.append("-".repeat(50)).append("\n");
        sb.append("Aprobadas: ").append(aprobadas).append("  |  Reprobadas: ").append(reprobadas).append("\n");
        materiasInscritas.clear();
        sb.append("\nSemestre cerrado. Ya puedes inscribir materias del siguiente semestre.");
        return sb.toString();
    }

  
    //  INFORMACIÓN COMPLETA
   

    /**
     * Retorna un resumen completo de la información del estudiante.
     * <p>
     * Incluye: código, nombre, programa, semestre, materias aprobadas y
     * materias actualmente inscritas.
     * </p>
     *
     * @return cadena formateada con toda la información del estudiante
     */
    public String getInfoCompleta() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo:   ").append(codigo).append("\n");
        sb.append("Nombre:   ").append(nombre).append("\n");
        sb.append("Programa: ").append(programa).append("\n");
        sb.append("Semestre: ").append(semestre).append("\n");
        if (materiasAprobadas.isEmpty()) {
            sb.append("Materias aprobadas: Ninguna\n");
        } else {
            sb.append("Materias aprobadas: ").append(String.join(", ", materiasAprobadas)).append("\n");
        }
        if (materiasInscritas.isEmpty()) {
            sb.append("Materias inscritas: Ninguna");
        } else {
            sb.append("Materias inscritas:\n");
            for (Materia m : materiasInscritas) {
                sb.append("  - ").append(m.getNombre()).append(" (").append(m.getCodigo()).append(")\n");
            }
        }
        return sb.toString();
    }
}
