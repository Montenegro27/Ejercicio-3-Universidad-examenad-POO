//Universidad del Valle de Guatemala - POO
//Mauricio Montenegro - 23679
//Ejercicio 3 - este ejercicio y codigo tiene la funcion de agregar estudiantes para un examen, asi mismo poder publicar sus notas
// de parte de los docentes.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sede {
    // Lista estática para almacenar las sedes disponibles
    private static List<Sede> sedes = new ArrayList<>();

    private String nombreSede;
    private List<Estudiante> estudiantes;

    public Sede(String nombreSede) {
        this.nombreSede = nombreSede;
        this.estudiantes = new ArrayList<>();
        sedes.add(this);  // Agregar esta sede a la lista de sedes disponibles
    }

    // Método para agregar un estudiante a la sede
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    // Método para calcular los promedios de notas en la sede
    public Map<String, Double> calcularPromedios() {
        Map<String, Double> promedios = new HashMap<>();
        for (Estudiante estudiante : estudiantes) {
            for (Map.Entry<String, Double> entry : estudiante.getNotas().entrySet()) {
                String nombreExamen = entry.getKey();
                double nota = entry.getValue();
                promedios.put(nombreExamen, promedios.getOrDefault(nombreExamen, 0.0) + nota);
            }
        }
        for (Map.Entry<String, Double> entry : promedios.entrySet()) {
            String nombreExamen = entry.getKey();
            double sumaNotas = entry.getValue();
            promedios.put(nombreExamen, sumaNotas / estudiantes.size());
        }
        return promedios;
    }

    // Método para obtener el nombre de la sede
    public String getNombreSede() {
        return nombreSede;
    }

    // Método para obtener la lista de estudiantes en la sede
    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    // Método para generar un reporte (debes implementar la lógica)
    public Object generarReporte() {
        return null;  // Implementa la generación de reportes aquí
    }

    // Método para buscar un estudiante por código único
    public Estudiante buscarEstudiantePorCodigo(String codigoUnico) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCodigoUnico().equals(codigoUnico)) {
                return estudiante;
            }
        }
        return null;  // Estudiante no encontrado
    }

    // Método estático para obtener las sedes disponibles
    public static List<Sede> obtenerSedes() {
        return sedes;
    }

    // Método estático para crear las sedes "Norte" y "Central"
    public static void inicializarSedes() {
        new Sede("Norte");
        new Sede("Central");
    }

    public static void main(String[] args) {
        Sede.inicializarSedes();
    }
}
