package sample.entities;

public class GrossProfitImpl implements GrossProfit {
    protected double amount = Double.NaN;

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public boolean hasAmount() {
        return amount != Double.NaN;
    }
}
