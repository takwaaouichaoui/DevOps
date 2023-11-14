package tn.esprit.rh.achat;
import org.junit.jupiter.api.Test;
import tn.esprit.rh.achat.entities.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StockTest {

    @Test
    public void testStockConstructor() {
        Stock stock = new Stock("TestStock", 50, 5);

        assertEquals("TestStock", stock.getLibelleStock());
        assertEquals(50, stock.getQte());
        assertEquals(5, stock.getQteMin());
    }



}