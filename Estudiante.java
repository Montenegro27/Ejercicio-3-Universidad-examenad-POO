import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estudiante {
    private String nombre;
    private String apellido;
    private String codigoUnico;
    private Date fechaNacimiento;
    private String correoElectronico;
    private Map<String, Double> notas;

    public Estudiante(String nombre, String apellido, String codigoUnico, Date fechaNacimiento, String correoElectronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoUnico = codigoUnico;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.notas = new HashMap<>();
    }

    public Estudiante(String nombre2, String apellido2, String codigoUnico2, String fechaNacimiento2,
            String correoElectronico2) {
    }

    public void agregarNota(String nombreExamen, double nota) {
        notas.put(nombreExamen, nota);
    }

    public double calcularPromedio() {
        double suma = 0;
        for (double nota : notas.values()) {
            suma += nota;
        }
        return suma / notas.size();
    }

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

    public double calcularDesviacionEstandar() {
        double promedio = calcularPromedio();
        double sumaCuadrados = 0;
        for (double nota : notas.values()) {
            sumaCuadrados += Math.pow(nota - promedio, 2);
        }
        return Math.sqrt(sumaCuadrados / notas.size());
    }

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

    public Object mostrarInformacion() {
        return null;
    }

    public void add(Estudiante estudiante) {
    }


}
