package sample.entities;

public class PromotionalCostImpl implements PromotionalCost {
    private double amount;

    public PromotionalCostImpl(double amount) throws NegativePromotionalCostsException {
        if (amount < 0)
            throw new NegativePromotionalCostsException();
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
