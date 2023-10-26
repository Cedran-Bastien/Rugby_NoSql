package Pojo;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class Match {
    ObjectId _id;
    Arbitre arbitre;
    Date date;
    Equipe equipeRecoit;
    Equipe equipeRecu;
    int essaisD;
    int essaisE;
    int idMatch;
    List<Joueurs> joueurs;
    int nbSpecs;
    int ptsD;
    int ptsE;
    Stade stade;

    public Match(){}

    public Stade getStade() {
        return stade;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public Equipe getEquipeRecoit() {
        return equipeRecoit;
    }

    public Equipe getEquipeRecu() {
        return equipeRecu;
    }

    public List<Joueurs> getJoueurs() {
        return joueurs;
    }

    public Date getDate() {
        return date;
    }

    public Arbitre getArbitre() {
        return arbitre;
    }

    public int getEssaisD() {
        return essaisD;
    }

    public int getEssaisE() {
        return essaisE;
    }

    public int getNbSpecs() {
        return nbSpecs;
    }

    public int getPtsD() {
        return ptsD;
    }

    public int getPtsE() {
        return ptsE;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setArbitre(Arbitre arbitre) {
        this.arbitre = arbitre;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEquipeRecoit(Equipe equipeRecoit) {
        this.equipeRecoit = equipeRecoit;
    }

    public void setEquipeRecu(Equipe equipeRecu) {
        this.equipeRecu = equipeRecu;
    }

    public void setEssaisD(int essaisD) {
        this.essaisD = essaisD;
    }

    public void setEssaisE(int essaisE) {
        this.essaisE = essaisE;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public void setJoueurs(List<Joueurs> joueurs) {
        this.joueurs = joueurs;
    }

    public void setNbSpecs(int nbSpecs) {
        this.nbSpecs = nbSpecs;
    }

    public void setPtsD(int ptsD) {
        this.ptsD = ptsD;
    }

    public void setPtsE(int ptsE) {
        this.ptsE = ptsE;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }
}
