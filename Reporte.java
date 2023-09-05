//Universidad del Valle de Guatemala - POO
//Mauricio Montenegro - 23679
//Ejercicio 3 - este ejercicio y codigo tiene la funcion de agregar estudiantes para un examen, asi mismo poder publicar sus notas
// de parte de los docentes.

import java.util.List;

class Reporte {
    // Lista de sedes para las cuales se generará el reporte
    private List<Sede> sedes;

    // Constructor que recibe la lista de sedes como parámetro
    public Reporte(List<Sede> sedes) {
        this.sedes = sedes;
    }

    // Método para generar el reporte consolidado de todas las sedes
    public String generarReporte() {
        // StringBuilder para construir el reporte
        StringBuilder reporte = new StringBuilder();

        // Iteramos a través de cada sede en la lista de sedes
        for (Sede sede : sedes) {
            // Llamamos al método generarReporte() de cada sede y lo agregamos al reporte
            reporte.append(sede.generarReporte());

            // Agregamos un salto de línea para separar los reportes de cada sede
            reporte.append("\n");
        }

        // Devolvemos el reporte consolidado como una cadena de texto
        return reporte.toString();
    }
}
