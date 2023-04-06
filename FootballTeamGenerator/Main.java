package FootballTeamGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Team> teams = new LinkedHashMap<>();

        String commandInput = scanner.nextLine();

        while (!"END".equals(commandInput)){
            String[] commandParts = commandInput.split(";");
            String command = commandParts[0];
            String teamName = commandParts[1];

            try {
                switch (command) {
                    case "Team":
                        Team team = new Team(teamName);
                        teams.put(teamName, team);
                        break;
                    case "Add":
                        String playerToAdd = commandParts[2];
                        //endurance, sprint, dribble, passing, and shooting
                        int endurance = Integer.parseInt(commandParts[3]);
                        int sprint = Integer.parseInt(commandParts[4]);
                        int dribble = Integer.parseInt(commandParts[5]);
                        int passing = Integer.parseInt(commandParts[6]);
                        int shooting = Integer.parseInt(commandParts[7]);

                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            Player player = new Player(playerToAdd, endurance, sprint, dribble, passing, shooting);
                            teams.get(teamName).addPlayer(player);
                        }
                        break;
                    case "Remove":
                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        }else {
                            String playerToRemove = commandParts[2];
                            teams.get(teamName).removePlayer(playerToRemove);
                        }
                        break;
                    case "Rating":
                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            System.out.printf("%s - %d", teamName, Math.round(teams.get(teamName).getRating()));
                        }
                        break;
                }
            }catch (IllegalArgumentException exception){
                System.out.println(exception.getMessage());
            }

            commandInput = scanner.nextLine();
        }
    }
}
