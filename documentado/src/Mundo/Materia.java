package Mundo;

import java.util.ArrayList;

/**
 * Representa una materia académica dentro del sistema de gestión universitaria.
 * <p>
 * Cada materia contiene información curricular como código, nombre, créditos,
 * semestre, horario, programa al que pertenece y prerrequisitos. También
 * almacena las notas de los tres cortes evaluativos con la siguiente ponderación:
 * Corte 1 = 30%, Corte 2 = 30%, Corte 3 = 40%.
 * </p>
 * <p>
 * Una nota con valor {@code -1} indica que el corte aún no ha sido calificado.
 * </p>
 *
 * @author CJ
 * @version 1.0
 */
public class Materia {

   
    //  ATRIBUTOS
    

    /** Código único que identifica la materia (p. ej. {@code "IS101"}). */
    String codigo;

    /** Nombre descriptivo de la materia (p. ej. {@code "Cálculo I"}). */
    String nombre;

    /** Número de créditos académicos que vale la materia (rango 1-10). */
    int creditos;

    /** Semestre en el que se imparte normalmente la materia (rango 1-6). */
    int semestre;

    /**
     * Horario de la materia en formato {@code "Día HH:MM-HH:MM"}
     * (p. ej. {@code "Lun-Mie 07:00-09:00"}).
     */
    String horario;

    /**
     * Lista de códigos de materias que deben estar aprobadas antes de poder
     * inscribir esta materia. Si la lista está vacía, no hay prerrequisitos.
     */
    ArrayList<String> prerequisitos;

    /**
     * Programa académico al que pertenece la materia.
     * Puede ser el nombre de un programa específico o {@code "COMUN"} si
     * la materia es compartida por todos los programas.
     */
    String programa;

    /** Nota del primer corte evaluativo (30%). Valor {@code -1} indica sin calificar. */
    double notaCorte1;

    /** Nota del segundo corte evaluativo (30%). Valor {@code -1} indica sin calificar. */
    double notaCorte2;

    /** Nota del tercer corte evaluativo (40%). Valor {@code -1} indica sin calificar. */
    double notaCorte3;

  
    //  CONSTRUCTOR
   

    /**
     * Crea una nueva materia con toda su información curricular.
     * <p>
     * Las notas de los tres cortes se inicializan en {@code -1},
     * lo que indica que aún no han sido calificadas.
     * </p>
     *
     * @param codigo        código único de la materia (p. ej. {@code "IS101"})
     * @param nombre        nombre completo de la materia
     * @param creditos      número de créditos académicos (1-10)
     * @param semestre      semestre en que se dicta (1-6)
     * @param horario       horario en formato {@code "Día HH:MM-HH:MM"}
     * @param prerequisitos lista de códigos de materias prerrequisito; puede ser vacía
     * @param programa      programa académico al que pertenece, o {@code "COMUN"}
     */
    public Materia(String codigo, String nombre, int creditos,
                   int semestre, String horario, ArrayList<String> prerequisitos, String programa) {
        this.codigo        = codigo;
        this.nombre        = nombre;
        this.creditos      = creditos;
        this.semestre      = semestre;
        this.horario       = horario;
        this.prerequisitos = prerequisitos;
        this.programa      = programa;
        this.notaCorte1    = -1;
        this.notaCorte2    = -1;
        this.notaCorte3    = -1;
    }

 
    //  GETTERS DE INFORMACIÓN CURRICULAR
  

    /**
     * Retorna el código único de la materia.
     *
     * @return código de la materia (p. ej. {@code "IS101"})
     */
    public String getCodigo() 
    { 
    	return codigo; 
    	
    }

    /**
     * Retorna el nombre descriptivo de la materia.
     *
     * @return nombre de la materia
     */
    public String getNombre() 
    { 
    	return nombre; 
    	
    }

    /**
     * Retorna el número de créditos académicos de la materia.
     *
     * @return créditos (rango 1-10)
     */
    public int getCreditos() 
    { 
    	return creditos; 
    	
    }

    /**
     * Retorna el semestre en que normalmente se dicta la materia.
     *
     * @return semestre (rango 1-6)
     */
    public int getSemestre() 
    { 
    	return semestre; 
    	
    }

    /**
     * Retorna el horario de la materia.
     *
     * @return horario en formato {@code "Día HH:MM-HH:MM"}
     */
    public String getHorario() 
    { 
    	return horario; 
    	
    }

    /**
     * Retorna el programa académico al que pertenece la materia.
     *
     * @return nombre del programa o {@code "COMUN"} si es compartida
     */
    public String getPrograma() 
    { 
    	return programa; 
    	
    }

    /**
     * Retorna la lista de códigos de materias que son prerrequisito
     * de esta materia.
     *
     * @return lista de códigos prerrequisito; puede estar vacía
     */
    public ArrayList<String> getPrerequisitos() 
    { 
    	return prerequisitos; 
    	
    }

    
    //  GETTERS Y SETTERS DE NOTAS
   

    /**
     * Retorna la nota del primer corte evaluativo (30%).
     *
     * @return nota del corte 1 en rango 0.0-5.0, o {@code -1} si no ha sido calificado
     */
    public double getNotaCorte1() 
    { 
    	return notaCorte1; 
    	
    }

    /**
     * Retorna la nota del segundo corte evaluativo (30%).
     *
     * @return nota del corte 2 en rango 0.0-5.0, o {@code -1} si no ha sido calificado
     */
    public double getNotaCorte2() 
    { 
    	return notaCorte2; 
    	
    }

    /**
     * Retorna la nota del tercer corte evaluativo (40%).
     *
     * @return nota del corte 3 en rango 0.0-5.0, o {@code -1} si no ha sido calificado
     */
    public double getNotaCorte3() 
    { 
    	return notaCorte3; 
    	
    }

    /**
     * Establece la nota del primer corte evaluativo (30%).
     *
     * @param n nota a asignar, en rango 0.0-5.0
     */
    public void setNotaCorte1(double n) 
    { 
    	this.notaCorte1 = n; 
    	
    }

    /**
     * Establece la nota del segundo corte evaluativo (30%).
     *
     * @param n nota a asignar, en rango 0.0-5.0
     */
    public void setNotaCorte2(double n) 
    { 
    	this.notaCorte2 = n; 
    	
    }

    /**
     * Establece la nota del tercer corte evaluativo (40%).
     *
     * @param n nota a asignar, en rango 0.0-5.0
     */
    public void setNotaCorte3(double n) 
    { 
    	this.notaCorte3 = n; 
    	
    }

    //  MÉTODOS DE CÁLCULO Y VERIFICACIÓN
 

    /**
     * Calcula y retorna la nota definitiva de la materia aplicando la
     * ponderación oficial de los tres cortes:
     * <ul>
     *   <li>Corte 1: 30%</li>
     *   <li>Corte 2: 30%</li>
     *   <li>Corte 3: 40%</li>
     * </ul>
     * <p>
     * Si alguno de los tres cortes no ha sido calificado (valor {@code -1}),
     * retorna {@code -1} indicando que la nota final no está disponible.
     * </p>
     *
     * @return nota final calculada en rango 0.0-5.0, o {@code -1} si algún corte está sin nota
     */
    public double getNotaFinal() 
    {
        if (notaCorte1 < 0 || notaCorte2 < 0 || notaCorte3 < 0) return -1;
        return notaCorte1 * 0.30 + notaCorte2 * 0.30 + notaCorte3 * 0.40;
    }

    /**
     * Retorna la nota oficial de la materia, equivalente a la nota final calculada.
     * <p>
     * Este método es utilizado por {@code cerrarSemestre()} en la clase
     * {@link Estudiante} para determinar si la materia fue aprobada (nota {@code >= 3.0}).
     * </p>
     *
     * @return nota final; {@code -1} si algún corte está pendiente
     * @see #getNotaFinal()
     */
    public double getNota() 
    {
        return getNotaFinal();
    }

    /**
     * Verifica si los tres cortes evaluativos han sido calificados.
     * <p>
     * Un corte está calificado cuando su nota es {@code >= 0}.
     * </p>
     *
     * @return {@code true} si los tres cortes tienen nota; {@code false} en caso contrario
     */
    public boolean tieneNotaCompleta() 
    {
        return notaCorte1 >= 0 && notaCorte2 >= 0 && notaCorte3 >= 0;
    }

    /**
     * Verifica si el estudiante cumple todos los prerrequisitos de esta materia.
     * <p>
     * Compara cada código en la lista de prerrequisitos contra la lista de
     * materias que el estudiante ya tiene aprobadas. Si algún prerrequisito
     * no está en dicha lista, retorna {@code false}.
     * </p>
     *
     * @param aprobadas lista de códigos de materias ya aprobadas por el estudiante
     * @return {@code true} si se cumplen todos los prerrequisitos; {@code false} si falta alguno
     */
    public boolean verificarPrereq(ArrayList<String> aprobadas) {
        for (String pre : prerequisitos) {
            if (!aprobadas.contains(pre)) 
            	return false;
        }
        return true;
    }

    
    //  MÉTODOS DE PRESENTACIÓN
  

    /**
     * Retorna una cadena con la información completa de la materia en un
     * formato legible de una sola línea.
     * <p>
     * Formato: {@code "[código] nombre | N cred | Sem S | horario | Prereq: lista}
     * </p>
     *
     * @return información resumida de la materia
     */
    public String getInfo() 
    {
        String pre = prerequisitos.isEmpty() ? "Ninguno" : String.join(", ", prerequisitos);
        return String.format("[%s] %s | %d cred | Sem %d | %s | Prereq: %s",
                codigo, nombre, creditos, semestre, horario, pre);
    }

    /**
     * Retorna una representación textual de la materia con nombre y código.
     * <p>
     * Utilizada, entre otros, por los componentes de la lista gráfica
     * ({@link javax.swing.JList}) para mostrar las materias disponibles.
     * </p>
     *
     * @return cadena en formato {@code "nombre (código)"}
     */
    @Override
    public String toString() 
    { 
    	return nombre + " (" + codigo + ")"; 
    	
    }
}
