package Interfas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * Panel central de la interfaz gráfica que actúa como área de visualización
 * de resultados del Sistema de Gestión Académica.
 * <p>
 * Contiene un {@link JTextArea} de solo lectura con fuente monoespaciada,
 * envuelto en un {@link JScrollPane} para soportar contenido de longitud variable.
 * Al iniciarse, muestra la pantalla de bienvenida con instrucciones de uso del sistema.
 * </p>
 * <p>
 * Este panel ocupa la posición {@code CENTER} del {@link java.awt.BorderLayout}
 * de la ventana principal {@link InterfazGestionAca}, por lo que se expande
 * para ocupar todo el espacio disponible.
 * </p>
 *
 * @author CJ
 * @version 1.0
 * @see InterfazGestionAca
 */
public class panelConsultaHorario extends JPanel {

  
    //  ATRIBUTOS
    

    /**
     * Área de texto de solo lectura donde se muestran los resultados de las
     * operaciones del sistema (horarios, calificaciones, listados, mensajes, etc.).
     * Usa fuente monoespaciada para alinear correctamente columnas de texto.
     */
    private JTextArea txtHorario;

    /**
     * Panel de desplazamiento que envuelve {@link #txtHorario},
     * permitiendo hacer scroll cuando el contenido supera el tamaño visible del panel.
     */
    private JScrollPane scroll;

 
    //  CONSTRUCTOR
  

    /**
     * Construye el panel de información con el área de texto y la pantalla
     * de bienvenida inicial.
     * <p>
     * Configuraciones aplicadas:
     * </p>
     * <ul>
     *   <li>El área de texto es no editable por el usuario.</li>
     *   <li>La fuente es {@code Monospaced} tamaño 12 para alineación de columnas.</li>
     *   <li>El borde del panel lleva el título {@code "Informacion"} en color azul.</li>
     *   <li>Se muestra automáticamente la pantalla de bienvenida al construirse.</li>
     * </ul>
     */
    public panelConsultaHorario() {
        TitledBorder borde = BorderFactory.createTitledBorder("Informacion");
        setBorder(borde);
        borde.setTitleColor(Color.BLUE);
        setLayout(new GridLayout(1, 1));

        txtHorario = new JTextArea();
        txtHorario.setEditable(false);
        txtHorario.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Mostrar la pantalla de bienvenida al iniciar
        txtHorario.setText(
            "Bienvenido al Sistema de Gestion Academica UNIBAGUE\n\n" +
            "Programas disponibles:\n" +
            "  - Ingenieria de Sistemas\n" +
            "  - Administracion de Empresas\n" +
            "  - Psicologia\n\n" +
            "FUNCIONES DISPONIBLES:\n" +
            "  REGISTRAR          -> Registrar nuevo estudiante\n" +
            "  ELIMINAR USUARIO   -> Eliminar estudiante del sistema\n" +
            "  CONSULTAR          -> Ver informacion del estudiante\n" +
            "  VER HORARIO        -> Ver materias inscritas\n" +
            "  CREAR MATERIA      -> Agregar nueva materia al catalogo\n" +
            "  ELIMINAR MATERIA   -> Eliminar materia (ingrese codigo en el panel derecho)\n" +
            "  ADICIONAR MATERIA  -> Inscribir materia al estudiante\n" +
            "  VER NOTAS          -> Ver e ingresar notas por cortes\n" +
            "  CERRAR SEMESTRE    -> Finalizar semestre\n" +
            "  LISTAR MATERIAS    -> Ver todas las materias del catalogo\n" +
            "  LISTAR ESTUDIANTES -> Ver todos los estudiantes registrados\n\n" +
            "FORMATOS IMPORTANTES:\n" +
            "  Horario   : Dia HH:MM-HH:MM  (ej: Lun-Mie 07:00-09:00)\n" +
            "  Requisitos: codigos separados por coma  (ej: IS101,IS102)\n\n" +
            "NOTAS - 3 cortes:\n" +
            "  Corte 1 = 30%,  Corte 2 = 30%,  Corte 3 = 40%"
        );

        scroll = new JScrollPane(txtHorario);
        add(scroll);
    }

  
    //  MÉTODOS PÚBLICOS
   

    /**
     * Muestra un texto arbitrario en el área de resultados, reemplazando
     * cualquier contenido anterior.
     * <p>
     * Es el método principal usado por {@link InterfazGestionAca} para
     * presentar al usuario los resultados de cada operación (horarios,
     * notas, listados, mensajes de confirmación o error, etc.).
     * </p>
     *
     * @param texto cadena a mostrar en el área de resultados
     */
    public void mostrarTexto(String texto) {
        txtHorario.setText(texto);
    }

    /**
     * Restaura la pantalla de bienvenida con las instrucciones de uso del sistema.
     * <p>
     * Se invoca al pulsar el botón {@code "INFO DEL PROGRAMA"} en el panel de botones,
     * o para reiniciar la vista a su estado inicial.
     * </p>
     */
    public void mostrarInfo() {
        txtHorario.setText(
            "Bienvenido al Sistema de Gestion Academica UNIBAGUE\n\n" +
            "Programas disponibles:\n" +
            "  - Ingenieria de Sistemas\n" +
            "  - Administracion de Empresas\n" +
            "  - Psicologia\n\n" +
            "FUNCIONES DISPONIBLES:\n" +
            "  REGISTRAR          -> Registrar nuevo estudiante\n" +
            "  ELIMINAR USUARIO   -> Eliminar estudiante del sistema\n" +
            "  CONSULTAR          -> Ver informacion del estudiante\n" +
            "  VER HORARIO        -> Ver materias inscritas\n" +
            "  CREAR MATERIA      -> Agregar nueva materia al catalogo\n" +
            "  ELIMINAR MATERIA   -> Eliminar materia (ingrese codigo en el panel derecho)\n" +
            "  ADICIONAR MATERIA  -> Inscribir materia al estudiante\n" +
            "  VER NOTAS          -> Ver e ingresar notas por cortes\n" +
            "  CERRAR SEMESTRE    -> Finalizar semestre\n" +
            "  LISTAR MATERIAS    -> Ver todas las materias del catalogo\n" +
            "  LISTAR ESTUDIANTES -> Ver todos los estudiantes registrados\n" +
            "  INFO DEL PROGRAMA  -> Mostrar esta pantalla\n\n" +
            "FORMATOS IMPORTANTES:\n" +
            "  Horario   : Dia HH:MM-HH:MM  (ej: Lun-Mie 07:00-09:00)\n" +
            "  Requisitos: codigos separados por coma  (ej: IS101,IS102)\n\n" +
            "NOTAS - 3 cortes:\n" +
            "  Corte 1 = 30%,  Corte 2 = 30%,  Corte 3 = 40%"
        );
    }
}
