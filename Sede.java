import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sede {
    private String nombreSede;
    private static List<Estudiante> estudiantes;

    public Sede(String nombreSede) {
        this.nombreSede = nombreSede;
        Sede.estudiantes = new ArrayList<>();
    }

    public static void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

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

    public String getNombreSede() {
        return nombreSede;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public Object generarReporte() {
        return null;
    }

    public Estudiante buscarEstudiantePorCodigo(String codigoUnico) {
        return null;
    }
}
