package ide.silanisLottery;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;

public class SilanisLotteryApplicationTests {

    private static JLineShellComponent shell;

    @Before
    public void startUp() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        shell = bootstrap.getJLineShellComponent();
    }

    @After
    public void shutdown() throws InterruptedException {
        shell.stop();
    }

    @Test
    public void testPurchaseMutlipleTickets() {

        int i = 1;
        while (i <= 50) {
            CommandResult purchase = shell.executeCommand("purchase --name a");
            assertEquals(true, purchase.isSuccess());
            assertEquals("Thank you a your ticket number is : " + i, purchase.getResult());
            i++;
        }
    }

    @Test
    public void testNoTicketsAvailable() {

        int i = 1;
        while (i <= 50) {
            CommandResult purchase = shell.executeCommand("purchase --name a");
            assertEquals(true, purchase.isSuccess());
            assertEquals("Thank you a your ticket number is : " + i, purchase.getResult());
            i++;
        }
        CommandResult purchase = shell.executeCommand("purchase --name a");
        assertEquals(true, purchase.isSuccess());
        assertEquals("Sorry no tickets available ", purchase.getResult());
    }

    @Test
    public void testPurchaseAfterDraw() {

        CommandResult draw = shell.executeCommand("draw");
        assertEquals(true, draw.isSuccess());
        assertEquals("Thank you, the draw is complete", draw.getResult());

        CommandResult purchase = shell.executeCommand("purchase --name a");
        assertEquals(true, purchase.isSuccess());
        assertEquals("Sorry the draw is done ", purchase.getResult());
    }

    @Test
    public void testWinnersBeforeDraw() {
        CommandResult winners = shell.executeCommand("winners");
        assertEquals(true, winners.isSuccess());
        assertEquals("Sorry you need to start a draw ", winners.getResult());

        CommandResult draw = shell.executeCommand("draw");
        assertEquals(true, draw.isSuccess());
        assertEquals("Thank you, the draw is complete", draw.getResult());
    }

    @Test
    public void testDraw() {
        CommandResult draw = shell.executeCommand("draw");
        assertEquals(true, draw.isSuccess());
        assertEquals("Thank you, the draw is complete", draw.getResult());
    }

    @Test
    public void testWinners() {
        CommandResult draw = shell.executeCommand("draw");
        assertEquals(true, draw.isSuccess());

        CommandResult winners = shell.executeCommand("winners");
        assertEquals(true, winners.isSuccess());
        assertEquals("Thank you for your participation", winners.getResult());
    }

    @Test
    public void testPurchaseAfterWinner() {

        CommandResult draw = shell.executeCommand("draw");
        assertEquals(true, draw.isSuccess());
        assertEquals("Thank you, the draw is complete", draw.getResult());

        CommandResult winners = shell.executeCommand("winners");
        assertEquals(true, winners.isSuccess());
        assertEquals("Thank you for your participation", winners.getResult());

        CommandResult purchase = shell.executeCommand("purchase --name a");
        assertEquals(true, purchase.isSuccess());
        assertEquals("Sorry the draw is done ", purchase.getResult());
    }
}
