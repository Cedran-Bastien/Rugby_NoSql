import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Queris qr = new Queris();

        boolean end = false;
        while (!end) {

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
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
                    try {
                        Date date = formatter.parse(argument[1]);
                        qr.getMatchDatePoints(date, Integer.parseInt(argument[2]));
                    } catch (ParseException e) {
                        System.out.println("Error : wrong date argument : must be in form : '01/02/2003' or date given doesn't exist");
                        break;
                    } catch (NumberFormatException n){
                        System.out.println("value of point isn't number, please check the given value");
                    }
                    break;
            }
        }
    }
}
