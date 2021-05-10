package sample.usecases;

import sample.entities.*;

import java.util.ArrayList;

public class HoekCelControllerImpl implements HoekCelController {
    protected GrossProfit gp;
    private PromotionalCost pc;
    private MarketingContribution mc;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    @Override
    public void computeMarketingContribution(double promotionalCosts) throws NegativePromotionalCostsException {
        if (gp == null || !gp.hasAmount())
            throw new GrossProfitNotDefinedException();

        pc = newPromotionalCost(promotionalCosts);
        mc = newMarketingContribution(gp, pc);

        notifyObservers();
    }

    protected PromotionalCost newPromotionalCost(double promotionalCosts) throws NegativePromotionalCostsException {
        return new PromotionalCostImpl(promotionalCosts);
    }

    protected MarketingContribution newMarketingContribution(GrossProfit gp, PromotionalCost pc) {
        return new MarketingContributionImpl(gp, pc);
    }

    @Override
    public double getMarketingContribution() {
        if (mc == null)
            throw new MarketingContributionNotDefinedException();

        return mc.getAmount();
    }

    private void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer != null)
            if (!observers.contains(observer))
                observers.add(observer);
    }
}
