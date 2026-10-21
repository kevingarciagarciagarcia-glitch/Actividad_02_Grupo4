package universidad.dispositivos;

import com.fabricanteb.sdk.BiometricDeviceSdk;
import com.fabricanteb.sdk.PunchRecord;
import com.fabricanteb.sdk.TipoMarcacion;
import universidad.asistencia.RegistroAsistencia;
import universidad.asistencia.ServicioAsistencia;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

/** Adapter (de objetos): traduce ServicioAsistencia -> BiometricDeviceSdk. */
public class AdaptadorFabricanteB implements ServicioAsistencia {
    private final BiometricDeviceSdk sdk;
    private final ZoneId zona;

    public AdaptadorFabricanteB(BiometricDeviceSdk sdk, ZoneId zona) {
        this.sdk = sdk;
        this.zona = zona;
    }

    @Override
    public Optional<RegistroAsistencia> obtenerAsistencia(String idColaborador, LocalDate fecha) {
        // 1. Transformar parametros: String -> int, LocalDate -> rango epoch
        int userCode = Integer.parseInt(idColaborador);
        long desde = fecha.atStartOfDay(zona).toInstant().toEpochMilli();
        long hasta = fecha.plusDays(1).atStartOfDay(zona).toInstant().toEpochMilli();

        // 2. Delegar en la operacion propia del fabricante
        List<PunchRecord> marcaciones = sdk.fetchPunches(userCode, desde, hasta);
        if (marcaciones.isEmpty()) {
            return Optional.empty();
        }

        // 3. Transformar resultado: coleccion de marcaciones -> registro diario
        LocalTime ingreso = primeraHora(marcaciones, TipoMarcacion.CHECK_IN);
        LocalTime salida = ultimaHora(marcaciones, TipoMarcacion.CHECK_OUT);
        return Optional.of(new RegistroAsistencia(idColaborador, fecha, ingreso, salida));
    }

    private LocalTime primeraHora(List<PunchRecord> marcaciones, TipoMarcacion tipo) {
        return marcaciones.stream().filter(p -> p.getType() == tipo)
                .map(this::aHora).min(LocalTime::compareTo).orElse(null);
    }

    private LocalTime ultimaHora(List<PunchRecord> marcaciones, TipoMarcacion tipo) {
        return marcaciones.stream().filter(p -> p.getType() == tipo)
                .map(this::aHora).max(LocalTime::compareTo).orElse(null);
    }

    private LocalTime aHora(PunchRecord p) {
        return Instant.ofEpochMilli(p.getTimestampMillis()).atZone(zona).toLocalTime();
    }
}
