package com.fabricanteb.sdk;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/** Adaptee: SDK del fabricante con interfaz incompatible (NO modificable). */
public class BiometricDeviceSdk {
    private final List<PunchRecord> memoria = new ArrayList<>();

    public BiometricDeviceSdk() {
        // Datos simulados del dispositivo
        ZoneId zona = ZoneId.systemDefault();
        memoria.add(new PunchRecord(1001, epoch(2026, 10, 15, 7, 52, zona), TipoMarcacion.CHECK_IN));
        memoria.add(new PunchRecord(1001, epoch(2026, 10, 15, 12, 30, zona), TipoMarcacion.CHECK_OUT));
        memoria.add(new PunchRecord(1001, epoch(2026, 10, 15, 16, 48, zona), TipoMarcacion.CHECK_OUT));
    }

    /** Recupera marcaciones de un usuario en el rango [fromMillis, toMillis). */
    public List<PunchRecord> fetchPunches(int userCode, long fromMillis, long toMillis) {
        List<PunchRecord> res = new ArrayList<>();
        for (PunchRecord p : memoria) {
            if (p.getUserCode() == userCode
                    && p.getTimestampMillis() >= fromMillis
                    && p.getTimestampMillis() < toMillis) {
                res.add(p);
            }
        }
        return res;
    }

    private static long epoch(int y, int m, int d, int h, int min, ZoneId zona) {
        return LocalDateTime.of(y, m, d, h, min).atZone(zona).toInstant().toEpochMilli();
    }
}
