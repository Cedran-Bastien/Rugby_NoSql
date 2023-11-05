import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Queris qr = new Queris();

        boolean end = false;
        while (!end) {

            // Specifie prompt
            System.out.println("\nCommands : ");

            // Ask command
            String command = sc.nextLine();

            // Get Arguments
            String[] argument = command.split(" ");
            // Delete too much space
            for (int i = 0; i <argument.length; i++){
                argument[i] = argument[i] .trim();
            }

            switch (argument[0]){
                case "quit":
                    end = true;
                    break;
                case "getEquipe":
//                  .. [nom equipe]
                    qr.getJoueur(argument[1]);
                    break;
                case "getEquipeDatePoints" :
//                    .. ["date at format : '01/02/2003'"] , [Points]
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


                    try {
                        //convert String to LocalDate
                        LocalDate localDate = LocalDate.parse(argument[1], formatter);
                        qr.getMatchDatePoints(localDate, Integer.parseInt(argument[2]));
                    } catch (DateTimeParseException e) {
                        System.out.println("Error : wrong date argument : must be in form : '01/02/2003' or date given doesn't exist");
                        break;
                    } catch (NumberFormatException n){
                        System.out.println("value of point isn't number, please check the given value");
                        break;
                    }
                    break;
                case "getEquipeArbitre" :
//                    .. [idEquipe] [nomArbitre]
                    qr.getEquipeArbitre(argument[1], argument[2]);
                    break;
                case "getTitulaireDate":
//                    .. [idEquipe need player] [Idequipe against] [date at format : '01/02/2003']
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    try {
                        //convert String to LocalDate
                        LocalDate localDate = LocalDate.parse(argument[3], formatter);
                        qr.getJoueurDate(argument[1],argument[2], localDate );
                    } catch (DateTimeParseException e) {
                        System.out.println("Error : wrong date argument : must be in form : '01/02/2003' or date given doesn't exist");
                        break;
                    }
                    break;
                case "getSuppleant" :
//                    .. [idEquipe]
                    qr.getSuppleant(argument[1]);
                    break;
                case "getStats" :
                    qr.getNbMatch(argument[1]);
                    break;
                case "getPlayerTeamFight":
//                    .. [id de l'equipe testée] [id de l'equipe 2] [id de l'equipe 3]
                    qr.getPlayedAgainst2Team(argument[1], argument[2], argument[3] );
                    break;
                case "getNotPlayed" :
//                    .. [id de l'equipe cible]
                    qr.getPlayerPlayedNothing(argument[1]);
                case "getPlayedAll" :
//                    .. [id de l'equipe cible]
                    qr.getPlayedAllMatch(argument[1]);
                    break;
                case "getBestPlayer" :
//                    ..
                    qr.getBestEssaiePoint();
                    break;
                case "addArbitre" :
//                    .. [id du match] [natianalité de l'arbitre]' [nom de l'arbitre'] [prenom de l'arbitre']
                    qr.addArbitre(Integer.parseInt(argument[1]), argument[2], argument[3], argument[4] );
                    break;
                case "help" :
                    System.out.println("- quit:\n" +
                            "  Description: Exit the program.\n" +
                            "  Usage: quit\n" +
                            "\n" +
                            "- getEquipe:\n" +
                            "  Description: Get players of a specific team.\n" +
                            "  Usage: getEquipe [nom equipe]\n" +
                            "  Arguments:\n" +
                            "    [nom equipe]: The name of the team for which you want to retrieve the players.\n" +
                            "\n" +
                            "- getEquipeDatePoints:\n" +
                            "  Description: Get matches based on a specific date and minimum points.\n" +
                            "  Usage: getEquipeDatePoints [date] [Points]\n" +
                            "  Arguments:\n" +
                            "    [date]: The date in the format \"dd/MM/yyyy\".\n" +
                            "    [Points]: The minimum number of points to compare.\n" +
                            "\n" +
                            "- getEquipeArbitre:\n" +
                            "  Description: Get teams for which a specific referee officiated.\n" +
                            "  Usage: getEquipeArbitre [idEquipe] [nomArbitre]\n" +
                            "  Arguments:\n" +
                            "    [idEquipe]: The ID of the team to find matches.\n" +
                            "    [nomArbitre]: The name of the referee for whom you want to retrieve matches.\n" +
                            "\n" +
                            "- getTitulaireDate:\n" +
                            "  Description: Get the starting players of one team against another team on a specific date.\n" +
                            "  Usage: getTitulaireDate [idEquipe besoin joueur] [Id équipe adversaire] [date au format : 'dd/MM/yyyy']\n" +
                            "  Arguments:\n" +
                            "    [idEquipe besoin joueur]: The ID of the team for which you want to find starting players.\n" +
                            "    [Id équipe adversaire]: The ID of the opposing team.\n" +
                            "    [date au format : 'dd/MM/yyyy']: The date in the format \"dd/MM/yyyy\".\n" +
                            "\n" +
                            "- getSuppleant:\n" +
                            "  Description: Get substitute players of a specific team.\n" +
                            "  Usage: getSuppleant [idEquipe]\n" +
                            "  Arguments:\n" +
                            "    [idEquipe]: The ID of the team for which you want to find substitute players.\n" +
                            "\n" +
                            "- getStats:\n" +
                            "  Description: Get statistics (number of matches played, points scored, tries scored) for each player of a specific team.\n" +
                            "  Usage: getStats [idEquipe]\n" +
                            "  Arguments:\n" +
                            "    [idEquipe]: The ID of the team for which you want to retrieve player statistics.\n" +
                            "\n" +
                            "- getPlayerTeamFight:\n" +
                            "  Description: Get players who played against both the tested team and two other teams.\n" +
                            "  Usage: getPlayerTeamFight [id équipe testée] [id équipe 2] [id équipe 3]\n" +
                            "  Arguments:\n" +
                            "    [id équipe testée]: The ID of the team you want to test.\n" +
                            "    [id équipe 2]: The ID of the second team.\n" +
                            "    [id équipe 3]: The ID of the third team.\n" +
                            "\n" +
                            "- getNotPlayed:\n" +
                            "  Description: Get players of the target team who have not played (playing time <= 0).\n" +
                            "  Usage: getNotPlayed [id de l'équipe cible]\n" +
                            "  Arguments:\n" +
                            "    [id de l'équipe cible]: The ID of the target team for which you want to find non-playing players.\n" +
                            "\n" +
                            "- getPlayedAll:\n" +
                            "  Description: Get players of the target team who have played in all matches.\n" +
                            "  Usage: getPlayedAll [id de l'équipe cible]\n" +
                            "  Arguments:\n" +
                            "    [id de l'équipe cible]: The ID of the target team for which you want to find players who have played in all matches.\n" +
                            "\n" +
                            "- getBestPlayer:\n" +
                            "  Description: Get the player with the highest try score and the player with the highest points score in all matches.\n" +
                            "  Usage: getBestPlayer\n" +
                            "  Arguments: No arguments.\n" +
                            "\n" +
                            "- addArbitre:\n" +
                            "  Description: Add an arbitrator to a specific match.\n" +
                            "  Usage: addArbitre [id du match] [nationalité de l'arbitre] [nom de l'arbitre] [prenom de l'arbitre]\n" +
                            "  Arguments:\n" +
                            "    [id du match]: The ID of the match to which you want to add an arbitrator.\n" +
                            "    [nationalité de l'arbitre]: The nationality of the arbitrator.\n" +
                            "    [nom de l'arbitre]: The name of the arbitrator.\n" +
                            "    [prenom de l'arbitre]: The first name of the arbitrator.");
                    break;
             }
        }
    }
}
