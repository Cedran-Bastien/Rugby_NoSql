import Pojo.Joueurs;
import Pojo.Match;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Queris {

//    MongoClient mg ;
    MongoDatabase md;

    MongoCollection<Match> matchs;

    public Queris(){
        // Connect to database
        // Get database
        // Get matchs collection
        matchs = Connection.getMatchsCollection();
    }

    public void getJoueur(String idEquipe){
        // Get team player
        List<Joueurs> correspondance = new ArrayList<>();
        for (Match m : matchs.find()){
            if (!m.getEquipeRecoit().getidEquipe().equals(idEquipe) || m.getEquipeRecu().getidEquipe().equals(idEquipe)){
                for (Joueurs joueurs : m.getJoueurs()){
                    if (Objects.equals(joueurs.getIdEquipe(), idEquipe))
                        correspondance.add(joueurs);
                }
            }
        }

        // Sort list
        correspondance.sort((j1, j2) -> {
            int res = 0;
            if (j1.getCoefficientEfficacite() < j2.getCoefficientEfficacite())
                res = 1;

            if (j1.getCoefficientEfficacite() > j2.getCoefficientEfficacite())
                res = -1;

            return res;
        });

        for (Joueurs j : correspondance){
            System.out.printf("\nnom : %s : " +
                    "\n\t - temps de jeu : %s, " +
                    "\n\t - Nb essais marqués : %s, " +
                    "\n\t - Nb point marqué : %s, " +
                    "\n\t - Coefficient d'éfficacité : %s",
                    j.getNom(),
                    j.getTempsJeu(),
                    j.getNbEssaisMarques(),
                    j.getPtsMarques(),
                    j.getCoefficientEfficacite());
        }

    }

    public void getMatchDatePoints(Date d, int points){
        for (Match m : matchs.find()){
            if (m.getDate().equals(d) && m.getPtsD() > points || m.getPtsE() > points){
                // todo -> amelioré match print
                System.out.println(m.getIdMatch());
            }
        }
    }
}
