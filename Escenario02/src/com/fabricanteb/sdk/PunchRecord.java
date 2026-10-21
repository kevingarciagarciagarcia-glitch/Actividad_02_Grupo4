package com.fabricanteb.sdk;

/** Estructura propia del fabricante (biblioteca NO modificable). */
public class PunchRecord {
    private final int userCode;
    private final long timestampMillis;
    private final TipoMarcacion type;

    public PunchRecord(int userCode, long timestampMillis, TipoMarcacion type) {
        this.userCode = userCode;
        this.timestampMillis = timestampMillis;
        this.type = type;
    }

    public int getUserCode() { return userCode; }
    public long getTimestampMillis() { return timestampMillis; }
    public TipoMarcacion getType() { return type; }
}
