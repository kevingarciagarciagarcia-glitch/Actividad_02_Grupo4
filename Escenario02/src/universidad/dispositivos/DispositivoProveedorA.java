package universidad.dispositivos;

import universidad.asistencia.RegistroAsistencia;
import universidad.asistencia.ServicioAsistencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/** Proveedor historico: ya cumple el contrato, no requiere adaptacion. */
public class DispositivoProveedorA implements ServicioAsistencia {
    @Override
    public Optional<RegistroAsistencia> obtenerAsistencia(String idColaborador, LocalDate fecha) {
        return Optional.of(new RegistroAsistencia(idColaborador, fecha,
                LocalTime.of(8, 0), LocalTime.of(17, 0)));
    }
}
