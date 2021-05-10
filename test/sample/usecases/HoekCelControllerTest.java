package sample.usecases;

import org.junit.jupiter.api.Test;
import sample.entities.*;

import static org.junit.jupiter.api.Assertions.*;

class HoekCelControllerTest {

    @Test
    void computeMarketingContribution() throws NegativePromotionalCostsException {

        int expectedUpdateInvocations = 1;

        // Arrange
        GrossProfit gp = new GrossProfitMock();
        HoekCelControllerImplWithSetConstructor hoekCel = new HoekCelControllerImplWithSetConstructor(gp);
        ObserverMonitor monitor = new ObserverMonitor();
        hoekCel.addObserver(monitor);
        double suppliedPC = 10;

        // Act
        hoekCel.computeMarketingContribution(suppliedPC);

        // Assert
        assertEquals(suppliedPC, ((PromotionalCostMock) hoekCel.promotionalCostInUse).suppliedPC);
        assertEquals(hoekCel.promotionalCostInUse, ((MarketingContributionMock) hoekCel.marketingContributionInUse).suppliedPC);
        assertEquals(gp, ((MarketingContributionMock) hoekCel.marketingContributionInUse).suppliedGP);
        assertEquals(expectedUpdateInvocations, monitor.updateCounter);
    }

    private class HoekCelControllerImplWithSetConstructor extends HoekCelControllerImpl {
        public PromotionalCost promotionalCostInUse;
        public MarketingContribution marketingContributionInUse;

        public HoekCelControllerImplWithSetConstructor(GrossProfit gp) {
            super();
            this.gp = gp;
        }

        @Override
        protected PromotionalCost newPromotionalCost(double promotionalCosts) throws NegativePromotionalCostsException {
            promotionalCostInUse = new PromotionalCostMock(promotionalCosts);
            return promotionalCostInUse;
        }

        @Override
        protected MarketingContribution newMarketingContribution(GrossProfit gp, PromotionalCost pc) {
            marketingContributionInUse = new MarketingContributionMock(gp, pc);
            return marketingContributionInUse;
        }
    }

    private class GrossProfitMock implements GrossProfit {

        @Override
        public double getAmount() {
            return 100;
        }

        @Override
        public boolean hasAmount() {
            return true;
        }
    }

    private class ObserverMonitor implements Observer {
        public int updateCounter = 0;

        @Override
        public void update() {
            updateCounter++;
        }
    }

    private class PromotionalCostMock implements PromotionalCost {
        public double suppliedPC;
        public PromotionalCostMock(double promotionalCosts) {
            suppliedPC = promotionalCosts;
        }

        @Override
        public double getAmount() {
            return 10;
        }
    }

    private class MarketingContributionMock implements MarketingContribution {
        public GrossProfit suppliedGP;
        public PromotionalCost suppliedPC;

        public MarketingContributionMock(GrossProfit gp, PromotionalCost pc) {
            suppliedGP = gp;
            suppliedPC = pc;
        }

        @Override
        public double getAmount() {
            return 90;
        }
    }
}