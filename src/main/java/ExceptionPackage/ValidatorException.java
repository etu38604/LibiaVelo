package ExceptionPackage;

public class ValidatorException extends Exception {

    private String properties;
    private String error;

    public ValidatorException(String error, String properties) {
        this.properties = properties;
        this.error = error;
    }


    public String toString() {
        return "Erreur de validation du champ "+ properties + " - erreur : "+ error;
    }
}
