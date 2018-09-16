package analizador;

import javafx.beans.property.SimpleStringProperty;

public class ItemTablaErrores {

    private final SimpleStringProperty error, linea_error;

    public ItemTablaErrores(SimpleStringProperty error, SimpleStringProperty linea_error) {
        this.error = error;
        this.linea_error = linea_error;
    }

    public String getError() {
        return error.get();
    }

    public SimpleStringProperty errorProperty() {
        return error;
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public String getLinea_error() {
        return linea_error.get();
    }

    public SimpleStringProperty linea_errorProperty() {
        return linea_error;
    }

    public void setLinea_error(String linea_error) {
        this.linea_error.set(linea_error);
    }
}
