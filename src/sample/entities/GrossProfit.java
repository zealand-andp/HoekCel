package sample.entities;

public interface GrossProfit {

    /**
     * Computes gross profit from revenue minus cost of sales.
     *
     * @return amount
     */
    double getAmount();

    /**
     * @return true if the initial Double.NaN has been overwritten
     */
    boolean hasAmount();
}
