//Universidad del Valle de Guatemala - POO
//Mauricio Montenegro - 23679
//Ejercicio 3 - este ejercicio y codigo tiene la funcion de agregar estudiantes para un examen, asi mismo poder publicar sus notas
// de parte de los docentes.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sede sedeNorte = new Sede("Sede Norte");
        Sede sedeCentral = new Sede("Sede Central");

        while (true) {
            // Menú de opciones
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
                // Opción 1: Registrar estudiante
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
                System.out.print("Ingrese la sede (Norte o Central): ");
                String sedeSeleccionada = scanner.nextLine();

                Sede sede;
                if (sedeSeleccionada.equalsIgnoreCase("Norte")) {
                    sede = sedeNorte;
                } else if (sedeSeleccionada.equalsIgnoreCase("Central")) {
                    sede = sedeCentral;
                } else {
                    System.out.println("Sede no válida.");
                    continue;
                }

                Estudiante estudiante = new Estudiante(nombre, apellido, codigoUnico, fechaNacimiento, correoElectronico);
                sede.agregarEstudiante(estudiante);
                System.out.println("Estudiante registrado en la sede: " + sede.getNombreSede());
            } else if (opcion == 2) {
                // Opción 2: Registrar notas de exámenes
                System.out.print("Ingrese el código único del estudiante: ");
                String codigoUnico = scanner.nextLine();
                System.out.print("Ingrese el nombre del examen: ");
                String nombreExamen = scanner.nextLine();
                System.out.print("Ingrese la nota del examen: ");
                double nota = scanner.nextDouble();
                scanner.nextLine();

                Estudiante estudiante = null;
                for (Sede sede : Sede.obtenerSedes()) {
                    estudiante = sede.buscarEstudiantePorCodigo(codigoUnico);
                    if (estudiante != null) {
                        break;
                    }
                }

                if (estudiante != null) {
                    estudiante.agregarNota(nombreExamen, nota);
                    System.out.println("Nota registrada para el estudiante: " + estudiante.getNombre());
                } else {
                    System.out.println("Estudiante no encontrado.");
                }
            } else if (opcion == 3) {
                // Opción 3: Generar informe de estadísticas
                System.out.println("Informe de Estadísticas:");
                for (Sede sede : Sede.obtenerSedes()) {
                    System.out.println("Sede: " + sede.getNombreSede());
                    Map<String, Double> promedios = sede.calcularPromedios();
                    for (Map.Entry<String, Double> entry : promedios.entrySet()) {
                        String nombreExamen = entry.getKey();
                        double promedio = entry.getValue();
                        System.out.println("Promedio de " + nombreExamen + ": " + promedio);
                    }
                }
            } else if (opcion == 4) {
                // Opción 4: Guardar información por sede
                System.out.println("Guardar información por sede (Norte y Central)");
                guardarInformacionPorSede(sedeNorte, "sede_norte.txt");
                guardarInformacionPorSede(sedeCentral, "sede_central.txt");
            } else if (opcion == 5) {
                // Opción 5: Generar reportes
                System.out.println("Generar reportes (Norte y Central)");
                generarReporte(sedeNorte, "reporte_sede_norte.csv");
                generarReporte(sedeCentral, "reporte_sede_central.csv");
            } else if (opcion == 6) {
                // Opción 6: Salir del programa
                break;
            } else {
                System.out.println("Opción inválida");
            }
        }
        scanner.close();
    }

    private static void guardarInformacionPorSede(Sede sede, String archivo) {
        // Método para guardar información de los estudiantes por sede
        try (PrintWriter writer = new PrintWriter(new File(archivo))) {
            for (Estudiante estudiante : sede.getEstudiantes()) {
                writer.println(estudiante.getNombre() + "," + estudiante.getApellido() + "," + estudiante.getCodigoUnico() + "," + estudiante.getFechaNacimiento() + "," + estudiante.getCorreoElectronico());
                for (Map.Entry<String, Double> entry : estudiante.getNotas().entrySet()) {
                    writer.println(entry.getKey() + "," + entry.getValue());
                }
            }
            System.out.println("Información de la sede " + sede.getNombreSede() + " guardada en " + archivo);
        } catch (FileNotFoundException e) {
            System.out.println("Error al guardar la información");
        }
    }

    private static void generarReporte(Sede sede, String archivo) {
        // Método para generar reportes por sede
        try (PrintWriter writer = new PrintWriter(new File(archivo))) {
            writer.println("Nombre,Apellido,Código Único,Fecha de Nacimiento,Correo Electrónico,Examen,Nota");
            for (Estudiante estudiante : sede.getEstudiantes()) {
                for (Map.Entry<String, Double> entry : estudiante.getNotas().entrySet()) {
                    writer.println(estudiante.getNombre() + "," + estudiante.getApellido() + "," + estudiante.getCodigoUnico() + "," + estudiante.getFechaNacimiento() + "," + estudiante.getCorreoElectronico() + "," + entry.getKey() + "," + entry.getValue());
                }
            }
            System.out.println("Reporte de la sede " + sede.getNombreSede() + " generado en " + archivo);
        } catch (FileNotFoundException e) {
            System.out.println("Error al generar el reporte");
        }
    }
}
