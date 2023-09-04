import java.util.List;

class Reporte {
    private List<Sede> sedes;

    public Reporte(List<Sede> sedes) {
        this.sedes = sedes;
    }

    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        for (Sede sede : sedes) {
            reporte.append(sede.generarReporte());
            reporte.append("\n");
        }
        return reporte.toString();
    }
}
