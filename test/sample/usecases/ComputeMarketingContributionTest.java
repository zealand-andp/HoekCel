package sample.usecases;

import org.junit.jupiter.api.Test;
import sample.entities.*;

import static org.junit.jupiter.api.Assertions.*;

class ComputeMarketingContributionTest {

    @Test
    void withValidInput() throws NegativePromotionalCostsException {
        // Testcase 01
        // Input
        double grossProfit = 1;
        double promotionalCost = 0;
        //Expected output
        double expectedMarketingContribution = 1.0;
        int expectedUpdateInvocations = 1;

        // Setup (Arrange)
        GrossProfit gp = new GrossProfitImplWithSetConstructor(grossProfit);
        HoekCelController hoekCel = new HoekCelControllerImplWithSetConstructor(gp);
        ObserverMonitor monitor = new ObserverMonitor();
        hoekCel.addObserver(monitor);

        // Execution (Act)
        hoekCel.computeMarketingContribution(promotionalCost);

        // Assert
        assertEquals(expectedMarketingContribution, hoekCel.getMarketingContribution());
        assertEquals(expectedUpdateInvocations, monitor.updateCounter);

        // Teardown
    }

    @Test
    void throwsExceptionOnNegativePromotionalCost() {
        // Testcase 02
        // Input
        double grossProfit = 1;
        double promotionalCost = -1;
        //Expected output
        // NegativePromotionalCostsException thrown
        int expectedUpdateInvocations = 0;

        // Setup (Arrange)
        GrossProfit gp = new GrossProfitImplWithSetConstructor(grossProfit);
        HoekCelController hoekCel = new HoekCelControllerImplWithSetConstructor(gp);
        ObserverMonitor monitor = new ObserverMonitor();
        hoekCel.addObserver(monitor);

        // Execution (Act)
        try {
            hoekCel.computeMarketingContribution(promotionalCost);
            fail(); // if no exception is thrown
        }
        catch (NegativePromotionalCostsException npce) {
            // Pass test on expected expection
        }

        // Assert
        assertEquals(expectedUpdateInvocations, monitor.updateCounter);
    }

    @Test
    void withValidNonZeroInput() throws NegativePromotionalCostsException {
        // Testcase 03
        String testDescription = "MarketingContribution is 24227000 with Gross Profit of 25000000 minus Promotional Cost of 773000.";
        // Input
        double grossProfit = 25000000;
        double promotionalCost = 773000;
        //Expected output
        double expectedMarketingContribution = 24227000.0;
        int expectedUpdateInvocations = 1;

        // Setup (Arrange)
        GrossProfit gp = new GrossProfitImplWithSetConstructor(grossProfit);
        HoekCelController hoekCel = new HoekCelControllerImplWithSetConstructor(gp);
        ObserverMonitor monitor = new ObserverMonitor();
        hoekCel.addObserver(monitor);

        // Execution (Act)
        hoekCel.computeMarketingContribution(promotionalCost);

        // Assert
        assertEquals(expectedMarketingContribution, hoekCel.getMarketingContribution(),testDescription);
        assertEquals(expectedUpdateInvocations, monitor.updateCounter);

        // Teardown
    }

    private class HoekCelControllerImplWithSetConstructor extends HoekCelControllerImpl {
        public HoekCelControllerImplWithSetConstructor(GrossProfit gp) {
            super();
            this.gp = gp;
        }
    }

    private class GrossProfitImplWithSetConstructor extends GrossProfitImpl {
        public GrossProfitImplWithSetConstructor(double amount) {
            super();
            this.amount = amount;
        }
    }

    private class ObserverMonitor implements Observer {
        public int updateCounter = 0;

        @Override
        public void update() {
            updateCounter++;
        }
    }
}