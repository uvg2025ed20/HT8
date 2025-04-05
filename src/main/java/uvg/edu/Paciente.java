package uvg.edu;

import java.util.Objects;

/**
 * Represents a patient with their details and emergency code.
 * Implements Comparable to define priority based on the emergency code (A=highest).
 */
public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char codigoEmergencia; // A, B, C, D, E

    public Paciente(String nombre, String sintoma, char codigoEmergencia) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        // Ensure the code is uppercase for consistent comparison
        this.codigoEmergencia = Character.toUpperCase(codigoEmergencia);
    }

    public String getNombre() {
        return nombre;
    }

    public String getSintoma() {
        return sintoma;
    }

    public char getCodigoEmergencia() {
        return codigoEmergencia;
    }

    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigoEmergencia;
    }

    /**
     * Compares this patient to another based on their emergency code.
     * 'A' is highest priority (considered "less than" 'B', etc.).
     * @param otroPaciente the patient to be compared.
     * @return a negative integer, zero, or a positive integer as this patient
     * has higher, equal, or lower priority than the specified patient.
     */
    @Override
    public int compareTo(Paciente otroPaciente) {
        // Character comparison works directly: 'A' < 'B' < 'C' ...
        return Character.compare(this.codigoEmergencia, otroPaciente.codigoEmergencia);
    }

    // Optional: equals and hashCode for completeness if used in collections like HashSet/HashMap
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        // Consider if two patients are equal only based on name, or all fields
        return codigoEmergencia == paciente.codigoEmergencia &&
                Objects.equals(nombre, paciente.nombre) &&
                Objects.equals(sintoma, paciente.sintoma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, sintoma, codigoEmergencia);
    }
}