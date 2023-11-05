import Pojo.Arbitre;
import Pojo.Equipe;
import Pojo.Joueurs;
import Pojo.Match;

import com.mongodb.client.MongoCollection;
import org.bson.Document;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


public class Queris {

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
            if (!m.getEquipeRecoit().getIdEquipe().equals(idEquipe) || m.getEquipeRecu().getIdEquipe().equals(idEquipe)){
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

    public void getMatchDatePoints(LocalDate d, int points){
        for (Match m : matchs.find()){
            LocalDate dateMatch = m.getDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (dateMatch.isEqual(d) && m.getPtsD() > points || dateMatch.isEqual(d) && m.getPtsE() > points){
                System.out.println(m.getIdMatch());
            }
        }
    }

    public void getEquipeArbitre(String idEquipe, String nomArbitre){

        List<Equipe> correspondance = new ArrayList<>();

        for (Match m : matchs.find()){
            if (m.getArbitre().getNom().equals(nomArbitre)){
                if (m.getEquipeRecoit().getIdEquipe().equals(idEquipe) && correspondance.stream().noneMatch(equipe -> equipe.getIdEquipe().equals(m.getEquipeRecoit().getIdEquipe()))){
                    correspondance.add(m.getEquipeRecoit());
                }
            }
        }

        // Print results
        for (Equipe e : correspondance){
            System.out.printf("\nEquipe : %s, pays : %s", e.getIdEquipe(), e.getPays());

        }
    }

    public void getJoueurDate(String idE1, String idE2, LocalDate date){

        // Get matched players
        List<Joueurs> correspondance = new ArrayList<>();
        for (Match m : matchs.find()){
            LocalDate dateMatch = m.getDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // If Teams are both against at the right date
            if (
                    (m.getEquipeRecoit().getIdEquipe().equals(idE1) && m.getEquipeRecu().getIdEquipe().equals(idE2))
                    || (m.getEquipeRecu().getIdEquipe().equals(idE1)) && m.getEquipeRecoit().getIdEquipe().equals(idE2)
                    && dateMatch.isEqual(date)
            ){
                for (Joueurs j : m.getJoueurs()){
                    if (j.getTitulaire() && j.getIdEquipe().equals(idE1) ){
                        correspondance.add(j);
                    }
                }
            }
        }

        // Print result
        for (Joueurs j: correspondance){
            System.out.printf("%s %s\n", j.getPrenom(), j.getNom());
        }
    }

    public void getSuppleant(String idEquipe){
        // Get matched players
        List<Joueurs> correspondance = new ArrayList<>();
        for (Match m : matchs.find()){
            if (m.getEquipeRecoit().getIdEquipe().equals(idEquipe) || m.getEquipeRecu().getIdEquipe().equals(idEquipe))
                for (Joueurs j : m.getJoueurs()){
                    if (!j.getTitulaire()
                        && correspondance.stream().noneMatch(joueurs -> joueurs.getIdJoueur().equals(j.getIdJoueur()))
                    ){
                        correspondance.add(j);
                    }
                }
        }

        // Print results
        for (Joueurs j: correspondance){
            System.out.printf("%s %s\n", j.getPrenom(), j.getNom());
        }
    }

    public void getNbMatch(String idEquipe){

        // Get needed information
        Map<String, String> name = new HashMap<>();
        Map<String, Integer> nbMatch = new HashMap<>();
        Map<String, Integer> nbPoint = new HashMap<>();
        Map<String, Integer> nbEssais = new HashMap<>();
        for (Match m : matchs.find()){
            if (m.getEquipeRecoit().getIdEquipe().equals(idEquipe) || m.getEquipeRecu().getIdEquipe().equals(idEquipe))
                for (Joueurs j : m.getJoueurs()){
                    if (j.getIdEquipe().equals(idEquipe)){
                        name.put(j.getIdJoueur(), j.getPrenom() + " " + j.getNom());
                        nbMatch.put(j.getIdJoueur(), nbMatch.containsKey(j.getIdJoueur()) ? nbMatch.get(j.getIdJoueur()) + 1 : 0);
                        nbPoint.put(j.getIdJoueur(), nbPoint.containsKey(j.getIdJoueur()) ? nbPoint.get(j.getIdJoueur()) + j.getPtsMarques() : 0);
                        nbEssais.put(j.getIdJoueur(), nbEssais.containsKey(j.getIdJoueur()) ? nbEssais.get(j.getIdJoueur()) + j.getNbEssaisMarques() : 0);
                    }
                }
        }

        // Print result
        for (String idJ : nbMatch.keySet()){
            System.out.printf("\n%s : " +
                    "\n\t - nombre de matchs joué : %s" +
                    "\n\t - nombre de points marqué : %s" +
                    "\n\t - nombre d'essais marqué : %s",
                    name.get(idJ),
                    nbMatch.get(idJ),
                    nbPoint.get(idJ),
                    nbEssais.get(idJ));
        }
    }

    public void getPlayedAgainst2Team(String id1, String id2, String id3){

        // Get and separate player from team one that fight against team 2 and against team 3
        List<Joueurs> e1_V_e2 = new ArrayList<>();
        List<Joueurs> e1_V_e3 = new ArrayList<>();
        List<Joueurs> actual;
        for (Match m : matchs.find()){
            actual = null;
            // If team 1 is against team 2
            if (
                    ((m.getEquipeRecoit().getIdEquipe().equals(id1) && m.getEquipeRecu().getIdEquipe().equals(id2))
                    || (m.getEquipeRecoit().getIdEquipe().equals(id2) && m.getEquipeRecu().getIdEquipe().equals(id1)))
            )
                actual = e1_V_e2;

            // If team 1 is against team 3
            if (
                    (m.getEquipeRecoit().getIdEquipe().equals(id1) && m.getEquipeRecu().getIdEquipe().equals(id3))
                    || (m.getEquipeRecoit().getIdEquipe().equals(id3) && m.getEquipeRecu().getIdEquipe().equals(id1))
            )
                actual = e1_V_e3;

            if (actual != null)
                for (Joueurs j : m.getJoueurs()){
                    if (actual.stream().noneMatch(joueurs -> joueurs.getIdJoueur().equals(j.getIdJoueur())))
                        actual.add(j);
                }

        }

        // Get player that fight against both team
        List<Joueurs> againstBothTeam = new ArrayList<>();
        e1_V_e2.retainAll(e1_V_e3);

        // Print result
        for (Joueurs j : e1_V_e2){
            System.out.printf("%s %s\n", j.getPrenom(), j.getNom());
        }
    }


    public void getPlayerPlayedNothing(String idEquipe){
        for (Match m : matchs.find()){
            if (m.getEquipeRecoit().getIdEquipe().equals(idEquipe) || m.getEquipeRecu().getIdEquipe().equals(idEquipe))
                for (Joueurs j : m.getJoueurs()){
                    if (j.getTempsJeu() <= 0)
                        System.out.printf("%s %s\n", j.getPrenom(), j.getNom());
                }
        }
    }

    public void getPlayedAllMatch(String idEquipe){

        // Initialize result list
        List<Joueurs> matched = new ArrayList<>();
        for (Match m : matchs.find()){
            if (m.getEquipeRecoit().getIdEquipe().equals(idEquipe) || m.getEquipeRecu().getIdEquipe().equals(idEquipe)){
                matched = m.getJoueurs();
                break;
            }

        }

        // Get hthe player we search
        for (Match m : matchs.find()){
            if (m.getEquipeRecoit().getIdEquipe().equals(idEquipe) || m.getEquipeRecu().getIdEquipe().equals(idEquipe))
                // TODO -> retain all retain all  player that have same name, must be player that have timePlayed > 0
                matched.retainAll(m.getJoueurs());
        }

        // Print result
        for (Joueurs j : matched){
            System.out.printf("%s %s\n", j.getPrenom(), j.getNom());
        }
    }

    public void getBestEssaiePoint(){

        // Get Totaux points et essais par joueur
        Map<String, Integer> essaie = new HashMap<>();
        Map<String, Integer> points = new HashMap<>();

        for (Match m : matchs.find()){
            for (Joueurs j :  m.getJoueurs()){
                essaie.put(j.getPrenom() + " " + j.getNom(), essaie.containsKey(j.getIdJoueur()) ? essaie.get(j.getIdJoueur()) + j.getNbEssaisMarques() : 0);
                points.put(j.getPrenom() + " " + j.getNom(), points.containsKey(j.getIdJoueur()) ? points.get(j.getIdJoueur()) + j.getPtsMarques() : 0);
            }
        }



        // Get inJoueur for max of totaux
        String maxEssais = essaie.entrySet().stream().max((entry1, entry2) -> entry1.getValue() >= entry2.getValue() ? 1 : -1).get().getKey();
        String maxPoints = points.entrySet().stream().max((entry1, entry2) -> entry1.getValue() >= entry2.getValue() ? 1 : -1).get().getKey();

        // Print result
        System.out.printf("Best essais score : %s \n" +
                "Best points score : %s",
                maxEssais,
                maxPoints);

    }

    public void addArbitre(int id,String nationalite, String nom, String prenom){
        Match m = matchs.find(new Document("idMatch", id)).first();

        if (m == null){
            System.out.println("match doesn't exist");
            return;
        }

        Arbitre a = new Arbitre();
        a.setIdArbitre(UUID.randomUUID().toString());
        a.setNom(nom);
        a.setPrenom(prenom);
        a.setNationalite(nationalite);

        m.setArbitre(a);

        if (a.getNationalite().equals(m.getEquipeRecoit().getPays()) && a.getNationalite().equals(m.getEquipeRecu().getPays())){
            System.out.println("Arbitre have same nationalities of one team");
            return;
        }

        System.out.println("ici");


    matchs.replaceOne(new Document("idMatch", id), m);

    }


}
