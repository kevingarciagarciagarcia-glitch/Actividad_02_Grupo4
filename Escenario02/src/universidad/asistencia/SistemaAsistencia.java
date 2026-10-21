package universidad.asistencia;

import java.time.LocalDate;

/** Client: solo conoce el contrato ServicioAsistencia. */
public class SistemaAsistencia {
    private final ServicioAsistencia servicio;

    public SistemaAsistencia(ServicioAsistencia servicio) {
        this.servicio = servicio;
    }

    public String consultar(String idColaborador, LocalDate fecha) {
        return servicio.obtenerAsistencia(idColaborador, fecha)
                .map(r -> String.format("%s | %s | ingreso %s | salida %s",
                        r.idColaborador(), r.fecha(), r.horaIngreso(), r.horaSalida()))
                .orElse(idColaborador + " | " + fecha + " | sin registros");
    }
}
