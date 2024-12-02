package jsges.nails.excepcion;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
/*
* ConflictException se usa para manejar errores de conflictos
* Por ejemplo: Cuando un recurso ya existe.
* */