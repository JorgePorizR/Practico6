package archivos.gui;

public final class Configuracion {
    private static Configuracion instance;
    private String directorioBase = "C:\\Users\\Usuario\\IdeaProjects\\Practico6\\carpetaPadre\\";

    protected Configuracion() {
    }

    public static Configuracion getInstance() {
        if (instance == null) {
            instance = new Configuracion();
        }
        return instance;
    }

    public String getDirectorioBase() {
        return directorioBase;
    }

    public void setDirectorioBase(String directorioBase) {
        this.directorioBase = directorioBase;
    }
}
