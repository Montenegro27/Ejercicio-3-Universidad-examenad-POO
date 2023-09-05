//Universidad del Valle de Guatemala - POO
//Mauricio Montenegro - 23679
//Ejercicio 3 - este ejercicio y codigo tiene la funcion de agregar estudiantes para un examen, asi mismo poder publicar sus notas
// de parte de los docentes.
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estudiante {
    // Atributos de la clase Estudiante
    private String nombre;
    private String apellido;
    private String codigoUnico;
    private Date fechaNacimiento;
    private String correoElectronico;
    private Map<String, Double> notas;

    // Constructor para inicializar un objeto Estudiante
    public Estudiante(String nombre, String apellido, String codigoUnico, Date fechaNacimiento, String correoElectronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoUnico = codigoUnico;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.notas = new HashMap<>(); // Inicializa el mapa de notas
    }

    public Estudiante(String nombre2, String apellido2, String codigoUnico2, String fechaNacimiento2,
            String correoElectronico2) {
    }

    // Método para agregar una nota de un examen al estudiante
    public void agregarNota(String nombreExamen, double nota) {
        notas.put(nombreExamen, nota);
    }

    // Método para calcular el promedio de las notas del estudiante
    public double calcularPromedio() {
        double suma = 0;
        for (double nota : notas.values()) {
            suma += nota;
        }
        return suma / notas.size();
    }

    // Método para calcular la mediana de las notas del estudiante
    public double calcularMediana() {
        List<Double> listaNotas = new ArrayList<>(notas.values());
        int n = listaNotas.size();
        listaNotas.sort(null);
        if (n % 2 == 0) {
            return (listaNotas.get(n / 2 - 1) + listaNotas.get(n / 2)) / 2.0;
        } else {
            return listaNotas.get(n / 2);
        }
    }

    // Método para calcular la moda de las notas del estudiante
    public double calcularModa() {
        Map<Double, Integer> frecuencias = new HashMap<>();
        for (double nota : notas.values()) {
            frecuencias.put(nota, frecuencias.getOrDefault(nota, 0) + 1);
        }
        double moda = 0;
        int maxFrecuencia = 0;
        for (Map.Entry<Double, Integer> entry : frecuencias.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                moda = entry.getKey();
                maxFrecuencia = entry.getValue();
            }
        }
        return moda;
    }

    // Método para calcular la desviación estándar de las notas del estudiante
    public double calcularDesviacionEstandar() {
        double promedio = calcularPromedio();
        double sumaCuadrados = 0;
        for (double nota : notas.values()) {
            sumaCuadrados += Math.pow(nota - promedio, 2);
        }
        return Math.sqrt(sumaCuadrados / notas.size());
    }

    // Métodos para obtener los atributos del estudiante
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Map<String, Double> getNotas() {
        return notas;
    }

    // Método para mostrar la información del estudiante (pendiente de implementación)
    public Object mostrarInformacion() {
        return null; // Implementa la generación de información aquí
    }

    // Método que no parece tener uso, ¿puede eliminarse?
    public void add(Estudiante estudiante) {
    }
}
