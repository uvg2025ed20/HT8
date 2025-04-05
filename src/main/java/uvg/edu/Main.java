package uvg.edu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean menu = true;

        while (menu) {
            System.out.println("\n\nSeleccione el sistema de emergencia que quiere usar:");
            System.out.println("1. VectorHeap");
            System.out.println("2. PriorityQueue");
            System.out.println("3. Salir");

            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.println("\nSistema de Emergencia con VectorHeap");
                PriorityQueue<Paciente> colaEmergenciaVH = new VectorHeap<>();
                cargarPacientes("src/main/java/uvg/edu/pacientes.txt", colaEmergenciaVH);
                atenderPacientes(colaEmergenciaVH);
            }
            else if (opcion == 2) {
                System.out.println("\nSistema de Emergencia PriorityQueue");
                java.util.PriorityQueue<Paciente> colaEmergenciaJCF = new java.util.PriorityQueue<>();
                cargarPacientesJCF("src/main/java/uvg/edu/pacientes.txt", colaEmergenciaJCF);
                atenderPacientesJCF(colaEmergenciaJCF);
            } else {
                System.out.println("Saliendo del sistema.");
                menu = false;
            }
        }
    }

    public static void cargarPacientes(String filename, PriorityQueue<Paciente> cola) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String sintoma = partes[1].trim();
                    char codigo = partes[2].trim().toUpperCase().charAt(0);
                    if (codigo >= 'A' && codigo <= 'E') {
                        Paciente p = new Paciente(nombre, sintoma, codigo);
                        cola.add(p);
                        System.out.println(p);
                    } else {
                        System.err.println("Código de emergencia inválido en línea: " + linea);
                    }
                } else {
                    System.err.println("Formato incorrecto en línea: " + linea);
                }
            }
            System.out.println("Pacientes en cola: " + cola.size());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + filename + ": " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al procesar archivo (VH): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void cargarPacientesJCF(String filename, java.util.PriorityQueue<Paciente> cola) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String linea;
            System.out.println("Cargando pacientes desde " + filename + " para JCF PriorityQueue...");
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String sintoma = partes[1].trim();
                    char codigo = partes[2].trim().toUpperCase().charAt(0);
                    if (codigo >= 'A' && codigo <= 'E') {
                        Paciente p = new Paciente(nombre, sintoma, codigo);
                        cola.add(p);
                        System.out.println(p);
                    } else {
                        System.err.println("Código de emergencia inválido en línea: " + linea);
                    }
                } else {
                    System.err.println("Formato incorrecto en línea: " + linea);
                }
            }
            System.out.println("Pacientes en cola: " + cola.size());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + filename + ": " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al procesar archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void atenderPacientes(PriorityQueue<Paciente> cola) {
        while (!cola.isEmpty()) {
            Paciente pacienteAtendido = cola.remove();
            System.out.println("\n\nAtendiendo a: " + pacienteAtendido.getNombre());
            System.out.println("Síntoma: " + pacienteAtendido.getSintoma());
            System.out.println("Prioridad: " + pacienteAtendido.getCodigoEmergencia());
        }
            System.out.println("\nSe atendieron todos los pacientes usando VectorHeap.");
    }

    public static void atenderPacientesJCF(java.util.PriorityQueue<Paciente> cola) {
        while (!cola.isEmpty()) {
            Paciente pacienteAtendido = cola.poll();
            System.out.println("\n\nAtendiendo a: " + pacienteAtendido.getNombre());
            System.out.println("Síntoma: " + pacienteAtendido.getSintoma());
            System.out.println("Prioridad: " + pacienteAtendido.getCodigoEmergencia());
        }
            System.out.println("\nSe atendieron todos los pacientes usando PriorityQueue de JCF.");
    }
}