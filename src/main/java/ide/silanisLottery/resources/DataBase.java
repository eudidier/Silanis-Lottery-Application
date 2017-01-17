/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide.silanislottery.resources;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.shell.support.table.Table;
import org.springframework.shell.support.table.TableHeader;
import org.springframework.shell.support.table.TableRow;
import org.springframework.stereotype.Component;

/**
 *
 * @author euloge
 */
@Component
public class DataBase {

    private final HashMap<Integer, String> dataBase = new HashMap();

    public DataBase() {
        this.initData();
    }

    public void addPlayer(int ballNumber, String name) {
        dataBase.replace(ballNumber, name);
    }

    public ArrayList<Integer> copyAsListOfKey() {
        return new ArrayList(dataBase.keySet());
    }

    public String getWinnerNameAndNumber(int key) {
        return dataBase.get(key) + " ticket #" + key;
    }

    public void tableRenderer(List<String> winnersList) {

        Table tableOfWinners = new Table();
        TableRow tableRow = new TableRow();

        List<String> header = asList("1st ball", "2nd ball", "3rd ball");
        Iterator<String> itrHeader = header.iterator();
        Iterator<String> itrWinner = winnersList.iterator();
        int Column = 0;

        while (itrHeader.hasNext() && itrWinner.hasNext()) {
            String winner = itrWinner.next();
            tableOfWinners.addHeader(Column, new TableHeader(itrHeader.next()));
            tableOfWinners.getHeaders().get(Column).updateWidth(winner.length());
            tableRow.addValue(Column, winner);
            Column++;
        }

        tableOfWinners.getRows().add(tableRow);
        System.out.println(tableOfWinners);
    }
    
        public void initData() {
        for (int i = 1; i <= 50; i++) {
            dataBase.put(i, "eSigneLive");
        }
    }

}
