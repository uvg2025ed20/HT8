package uvg.edu;

import java.util.Objects;


public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char codigoEmergencia;

    public Paciente(String nombre, String sintoma, char codigoEmergencia) {
        this.nombre = nombre;
        this.sintoma = sintoma;
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

    @Override
    public int compareTo(Paciente otroPaciente) {
        return Character.compare(this.codigoEmergencia, otroPaciente.codigoEmergencia);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return codigoEmergencia == paciente.codigoEmergencia &&
                Objects.equals(nombre, paciente.nombre) &&
                Objects.equals(sintoma, paciente.sintoma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, sintoma, codigoEmergencia);
    }
}