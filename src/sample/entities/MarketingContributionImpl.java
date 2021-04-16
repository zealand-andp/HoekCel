package sample.entities;

public class MarketingContributionImpl implements MarketingContribution {
    private GrossProfit gp;
    private PromotionalCost pc;

    public MarketingContributionImpl(GrossProfit gp, PromotionalCost pc) {
        this.gp = gp;
        this.pc = pc;
    }

    @Override
    public double getAmount() {
        return gp.getAmount() - pc.getAmount();
    }
}
