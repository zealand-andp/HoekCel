package sample.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for sample.entities.PromotionalCost.
 */
public class MarketingContributionTest {

    @Test
    void createWithNoGrossProfit() {
        try {
            new MarketingContributionImpl(null, new PromotionalCostMock());
            fail(); // if GrossProfitNotDefinedException was not thrown
        }
        catch (GrossProfitNotDefinedException gpnde) {
            // GrossProfitNotDefinedException caught as expected. Test passes.
        }
    }

    private class PromotionalCostMock implements PromotionalCost {

        @Override
        public double getAmount() {
            return 773000;
        }
    }

    @Test
    void createWithNoPromotionalCost() {
        try {
            new MarketingContributionImpl(new GrossProfitMock(), null);
            fail(); // if PromotionalCostNotDefinedException was not thrown
        }
        catch (PromotionalCostNotDefinedException pcnde) {
            // PromotionalCostNotDefinedException caught as expected. Test passes.
        }
    }

    private class GrossProfitMock implements GrossProfit {
        @Override
        public double getAmount() {
            return 25000000;
        }

        @Override
        public boolean hasAmount() {
            return true;
        }
    }

    @Test
    void withValidInput() {
        // Testcase 03
        // Input
        double grossProfit = 25000000;
        double promotionalCost = 773000;
        //Expected output
        double expectedMarketingContribution = 24227000;

        // Setup
        MarketingContributionImpl marketingContribution = new MarketingContributionImpl(new GrossProfitMock(), new PromotionalCostMock());

        // Execute
        double computedMarketingContribution = marketingContribution.getAmount();

        // Assert
        assertEquals(expectedMarketingContribution, computedMarketingContribution);

    }
}
