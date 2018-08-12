package ExceptionPackage;

public class ConnectionException extends Throwable {

    private String error;
    public ConnectionException(String e) {error = e;}
    public String toString() {return "Erreur de connexion : "+ error;}
}
