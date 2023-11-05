package Pojo;

public class Equipe {

    String idEquipe;
    String pays;
    String couleur;
    String entraineur;

    public Equipe() {}

    public String getIdEquipe() {
        return idEquipe;
    }

    public String getPays() {
        return pays;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getEntraineur() {
        return entraineur;
    }

    public void setIdEquipe(String idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setEntraineur(String entraineur) {
        this.entraineur = entraineur;
    }
}
