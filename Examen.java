//Universidad del Valle de Guatemala - POO
//Mauricio Montenegro - 23679
//Ejercicio 3 - este ejercicio y codigo tiene la funcion de agregar estudiantes para un examen, asi mismo poder publicar sus notas
// de parte de los docentes.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Examen {
    // Lista para almacenar los resultados de los exámenes
    private List<Double> resultados;

    // Constructor que inicializa la lista de resultados
    public Examen(String nombreExamen) {
        this.resultados = new ArrayList<>();
    }

    // Método para agregar un resultado a la lista
    public void agregarResultado(double resultado) {
        resultados.add(resultado);
    }

    // Método para calcular el promedio de los resultados
    public double calcularPromedio() {
        double suma = 0;
        for (double resultado : resultados) {
            suma += resultado;
        }
        return suma / resultados.size();
    }

    // Método para calcular la mediana de los resultados
    public double calcularMediana() {
        int n = resultados.size();
        List<Double> listaResultados = new ArrayList<>(resultados);
        listaResultados.sort(null);
        if (n % 2 == 0) {
            return (listaResultados.get(n / 2 - 1) + listaResultados.get(n / 2)) / 2.0;
        } else {
            return listaResultados.get(n / 2);
        }
    }

    // Método para calcular la moda de los resultados
    public double calcularModa() {
        Map<Double, Integer> frecuencias = new HashMap<>();
        for (double resultado : resultados) {
            frecuencias.put(resultado, frecuencias.getOrDefault(resultado, 0) + 1);
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

    // Método para calcular la desviación estándar de los resultados
    public double calcularDesviacionEstandar() {
        double promedio = calcularPromedio();
        double sumaCuadrados = 0;
        for (double resultado : resultados) {
            sumaCuadrados += Math.pow(resultado - promedio, 2);
        }
        return Math.sqrt(sumaCuadrados / resultados.size());
    }
}
