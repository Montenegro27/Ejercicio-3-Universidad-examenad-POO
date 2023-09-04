import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Examen {
    private List<Double> resultados;

    public Examen(String nombreExamen) {
        this.resultados = new ArrayList<>();
    }

    public void agregarResultado(double resultado) {
        resultados.add(resultado);
    }

    public double calcularPromedio() {
        double suma = 0;
        for (double resultado : resultados) {
            suma += resultado;
        }
        return suma / resultados.size();
    }

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

    public double calcularDesviacionEstandar() {
        double promedio = calcularPromedio();
        double sumaCuadrados = 0;
        for (double resultado : resultados) {
            sumaCuadrados += Math.pow(resultado - promedio, 2);
        }
        return Math.sqrt(sumaCuadrados / resultados.size());
    }
}
