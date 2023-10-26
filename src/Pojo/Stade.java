package Pojo;

public class Stade {
    String idStade;
    int capacite;
    String nom;
    String ville;

    public Stade() {}

    public String getIdStade() {
        return idStade;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public void setIdStade(String idStade) {
        this.idStade = idStade;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
