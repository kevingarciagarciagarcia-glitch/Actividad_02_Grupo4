package universidad;

import com.fabricanteb.sdk.BiometricDeviceSdk;
import universidad.asistencia.ServicioAsistencia;
import universidad.asistencia.SistemaAsistencia;
import universidad.dispositivos.AdaptadorFabricanteB;
import universidad.dispositivos.DispositivoProveedorA;

import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        LocalDate fecha = LocalDate.of(2026, 10, 15);

        ServicioAsistencia proveedorA = new DispositivoProveedorA();
        ServicioAsistencia fabricanteB =
                new AdaptadorFabricanteB(new BiometricDeviceSdk(), ZoneId.systemDefault());

        System.out.println("Proveedor A : " + new SistemaAsistencia(proveedorA).consultar("1001", fecha));
        System.out.println("Fabricante B: " + new SistemaAsistencia(fabricanteB).consultar("1001", fecha));
        System.out.println("Fabricante B: " + new SistemaAsistencia(fabricanteB).consultar("2002", fecha));
    }
}
