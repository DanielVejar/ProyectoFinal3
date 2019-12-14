package cl.proyecto.inacap.proyectofinal.Clases;

public class Lineas {
    private String id;
    private String linea;

    public Lineas(String id, String linea) {
        this.id = id;
        this.linea = linea;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }
}
