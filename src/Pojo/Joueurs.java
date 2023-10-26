package Pojo;

public class Joueurs {
    double coefficientEfficacite;
    String idEquipe;
    String idJoueur;
    int nbEssaisMarques;
    String nom;
    Poste poste;
    String prenom;
    int ptsMarques;
    int tempsJeu;
    Boolean titulaire;

    public Joueurs() {}

    public double getCoefficientEfficacite() {
        return coefficientEfficacite;
    }

    public String getIdEquipe() {
        return idEquipe;
    }

    public String getIdJoueur() {
        return idJoueur;
    }

    public int getNbEssaisMarques() {
        return nbEssaisMarques;
    }

    public String getNom() {
        return nom;
    }

    public Poste getPoste() {
        return poste;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getPtsMarques() {
        return ptsMarques;
    }

    public int getTempsJeu() {
        return tempsJeu;
    }

    public Boolean getTitulaire() {
        return titulaire;
    }

    public void setCoefficientEfficacite(double coefficientEfficacite) {
        this.coefficientEfficacite = coefficientEfficacite;
    }

    public void setIdEquipe(String idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setIdJoueur(String idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void setNbEssaisMarques(int nbEssaisMarques) {
        this.nbEssaisMarques = nbEssaisMarques;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPtsMarques(int ptsMarques) {
        this.ptsMarques = ptsMarques;
    }

    public void setTempsJeu(int tempsJeu) {
        this.tempsJeu = tempsJeu;
    }

    public void setTitulaire(Boolean titulaire) {
        this.titulaire = titulaire;
    }
}
