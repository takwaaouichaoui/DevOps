package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    Stock existingStock;

    @BeforeEach
    void setUp() {
        existingStock = new Stock(1L, "ExistingStock", 100, 10);
    }

    @Test
    public void testRetrieveAllStocks() {
        List<Stock> mockStocks = new ArrayList<>();
        mockStocks.add(new Stock(1L, "Stock1", 100, 10));
        mockStocks.add(new Stock(2L, "Stock2", 200, 20));

        Mockito.when(stockRepository.findAll()).thenReturn(mockStocks);
        List<Stock> retrievedStocks = stockService.retrieveAllStocks();
        assertEquals(2, retrievedStocks.size());
        assertEquals("Stock1", retrievedStocks.get(0).getLibelleStock());
        assertEquals(100, retrievedStocks.get(0).getQte());
    }

    @Test
    public void testAddStock() {
        Stock newStock = new Stock("NewStock", 50, 5);

        Mockito.when(stockRepository.save(any(Stock.class))).thenReturn(newStock);

        Stock addedStock = stockService.addStock(newStock);

        assertEquals("NewStock", addedStock.getLibelleStock());
        assertEquals(50, addedStock.getQte());
    }

    @Test
    public void testUpdateStock() {
        Stock existingStock = new Stock(1L, "ExistingStock", 100, 10);

        Mockito.when(stockRepository.save(any(Stock.class))).thenReturn(existingStock);

        Stock updatedStock = stockService.updateStock(existingStock);

        assertEquals(1L, updatedStock.getIdStock());
        assertEquals("ExistingStock", updatedStock.getLibelleStock());
        assertEquals(100, updatedStock.getQte());
    }

    @Test
    public void testDeleteStock() {

        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(existingStock));

        Mockito.doNothing().when(stockRepository).deleteById(1L);

        stockService.deleteStock(1L);

    }

    @Test
    public void testRetrieveStock() {
        Stock existingStock = new Stock(1L, "ExistingStock", 100, 10);

        Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(existingStock));

        Stock retrievedStock = stockService.retrieveStock(1L);

        assertEquals(1L, retrievedStock.getIdStock());
        assertEquals("ExistingStock", retrievedStock.getLibelleStock());
        assertEquals(100, retrievedStock.getQte());
        assertEquals(10, retrievedStock.getQteMin());
    }

}