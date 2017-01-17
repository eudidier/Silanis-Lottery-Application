/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide.silanislottery.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import ide.silanislottery.resources.DataBase;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import static java.util.Arrays.asList;

/**
 *
 * @author euloge
 */
@Component
public class SilanisLottery implements CommandMarker {

    private double POT = 200;
    private final double POT_VALUE = 0.5;
    private final int TICKET_PRICE = 10;
    private final int TOTAL_TICKETS = 50;

    private final double FIRST_DRAW_WINIS = 0.75;
    private final double SECOND_DRAW_WINS = 0.15;
    private final double THIRD_DRAW_WINS = 0.10;

    List<String> winnersInfo = new ArrayList();
    List<Double> price = asList(FIRST_DRAW_WINIS, SECOND_DRAW_WINS, THIRD_DRAW_WINS);

    private int ticketNumber = 0;

    @Autowired
    DataBase dataBase;

    private Boolean desableCommand = true; // It does not allow more than one draw or winners before a draw

    public SilanisLottery() {
    }

    @CliCommand(value = "purchase", help = "you want to buy a ticket for the draw by providing a first name. (run 'purchase --name ABC')")
    public String purchase(@CliOption(key = {"name"}, mandatory = true, help = "your first name") final String name) {

        ticketNumber++;
        if (desableCommand && ticketNumber <= TOTAL_TICKETS) {
            dataBase.addPlayer(ticketNumber, name); 
            POT = POT + TICKET_PRICE;
            return "Thank you " + name + " your ticket number is : " + ticketNumber;
            
        } else if (ticketNumber > TOTAL_TICKETS) {
            return "Sorry no tickets available ";
            
        } else {
            return "Sorry the draw is done ";
        }

    }

    @CliCommand(value = "draw", help = "you want to start a draw (run 'draw')")
    public String draw() {

        if (desableCommand) {
            List<Integer> winners = dataBase.copyAsListOfKey();
            Collections.shuffle(winners);
            Iterator<Integer> itrWinner = winners.iterator();
            Iterator<Double> itrPrice = price.iterator();

            while (itrWinner.hasNext() && itrPrice.hasNext()) {
                winnersInfo.add(dataBase.getWinnerNameAndNumber(itrWinner.next())
                        + ": " + Math.round(POT * POT_VALUE * itrPrice.next()) + "$");
            }

            desableCommand = false;

            return "Thank you, the draw is complete";

        } else {
            return "Sorry the draw is done ";
        }
    }

    @CliCommand(value = "winners", help = "You want to display the winning tickets (run 'winners')")
    public String winners() {

        if (!desableCommand) {
            dataBase.tableRenderer(winnersInfo);
            return "Thank you for your participation";
        } else {
            return "Sorry you need to start a draw ";
        }
    }

}
