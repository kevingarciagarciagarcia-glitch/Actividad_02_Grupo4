package universidad.asistencia;

import java.time.LocalDate;
import java.time.LocalTime;

/** Estructura normalizada que espera el sistema institucional. */
public record RegistroAsistencia(String idColaborador, LocalDate fecha,
                                 LocalTime horaIngreso, LocalTime horaSalida) {
}
