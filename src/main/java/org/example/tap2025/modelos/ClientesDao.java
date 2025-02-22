package org.example.tap2025.modelos;

public class ClientesDao {

    private int idCte;
    private String nomCte;
    private String telCte;
    private String direccion;
    private String emailCte;

    public String getEmailCte() {
        return emailCte;
    }
    public void INSERT(){}
        String query = "INSERT INTO clientes(nomCte, telCte, direccion, emailCte) " +
                "values('"+nomCte+"','"+telCte+"','"+direccion+"','"+emailCte+"')";
    public void UPDATE(){}
    public void DELETE(){}
    public void SELECT(){}

    public void setEmailCte(String emailCte) {
        this.emailCte = emailCte;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelCte() {
        return telCte;
    }

    public void setTelCte(String telCte) {
        this.telCte = telCte;
    }

    public int getIdCte() {
        return idCte;
    }

    public void setIdCte(int idCte) {
        this.idCte = idCte;
    }
}
