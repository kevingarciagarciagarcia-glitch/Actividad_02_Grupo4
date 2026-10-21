package universidad.asistencia;

import java.time.LocalDate;
import java.util.Optional;

/** Target: contrato estable del sistema institucional. */
public interface ServicioAsistencia {
    Optional<RegistroAsistencia> obtenerAsistencia(String idColaborador, LocalDate fecha);
}
