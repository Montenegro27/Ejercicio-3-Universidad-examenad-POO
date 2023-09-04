import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sede sede = new Sede("Nombre de la Sede"); // Debes crear una instancia de Sede
        
        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar notas de exámenes");
            System.out.println("3. Generar informe de estadísticas");
            System.out.println("4. Guardar información por sede");
            System.out.println("5. Generar reportes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.print("Ingrese el nombre del estudiante: ");
                String nombre = scanner.nextLine();
                System.out.print("Ingrese el apellido del estudiante: ");
                String apellido = scanner.nextLine();
                System.out.print("Ingrese el código único del estudiante: ");
                String codigoUnico = scanner.nextLine();
                System.out.print("Ingrese la fecha de nacimiento del estudiante (dd/mm/yyyy): ");
                String fechaNacimiento = scanner.nextLine();
                System.out.print("Ingrese el correo electrónico del estudiante: ");
                String correoElectronico = scanner.nextLine();
                Estudiante estudiante = new Estudiante(nombre, apellido, codigoUnico, fechaNacimiento, correoElectronico);
                Sede.agregarEstudiante(estudiante);
            } 
            else if (opcion == 2) {
                System.out.print("Ingrese el código único del estudiante: ");
                String codigoUnico = scanner.nextLine();
                Estudiante estudiante = sede.buscarEstudiantePorCodigo(codigoUnico);
                if (estudiante == null) {
                    System.out.println("Estudiante no encontrado");
                } else {
                    System.out.print("Ingrese el nombre del examen: ");
                    String nombreExamen = scanner.nextLine();
                    System.out.print("Ingrese la nota del examen: ");
                    double nota = scanner.nextDouble();
                    scanner.nextLine();
                    estudiante.agregarNota(nombreExamen, nota);
                }
            } 
            else if (opcion == 3) {
                Map<String, Double> promedios = sede.calcularPromedios();
                for (Map.Entry<String, Double> entry : promedios.entrySet()) {
                    String nombreExamen = entry.getKey();
                    double promedio = entry.getValue();
                    System.out.println("Promedio de " + nombreExamen + ": " + promedio);
                }
            } 
            else if (opcion == 4) {
                try (PrintWriter writer = new PrintWriter(new File("sede.txt"))) {
                    for (Estudiante estudiante : sede.getEstudiantes()) {
                        writer.println(estudiante.getNombre() + "," + estudiante.getApellido() + "," + estudiante.getCodigoUnico() + "," + estudiante.getFechaNacimiento() + "," + estudiante.getCorreoElectronico());
                        for (Map.Entry<String, Double> entry : estudiante.getNotas().entrySet()) {
                            writer.println(entry.getKey() + "," + entry.getValue());
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error al guardar la información");
                }
            } 
            else if (opcion == 5) {
                try (PrintWriter writer = new PrintWriter(new File("reporte.csv"))) {
                    writer.println("Nombre,Apellido,Código Único,Fecha de Nacimiento,Correo Electrónico,Examen,Nota");
                    for (Estudiante estudiante : sede.getEstudiantes()) {
                        for (Map.Entry<String, Double> entry : estudiante.getNotas().entrySet()) {
                            writer.println(estudiante.getNombre() + "," + estudiante.getApellido() + "," + estudiante.getCodigoUnico() + "," + estudiante.getFechaNacimiento() + "," + estudiante.getCorreoElectronico() + "," + entry.getKey() + "," + entry.getValue());
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error al generar el reporte");
                }
            } 
            else if (opcion == 6) {
                break;
            } else {
                System.out.println("Opción inválida");
            }
        }
        scanner.close();
    }
}
