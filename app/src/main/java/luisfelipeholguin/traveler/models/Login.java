package luisfelipeholguin.traveler.models;

/**
 * Created by luisfelipeholguin on 26/05/16.
 */
public class Login {

    String status;
    Usuario usuario;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
