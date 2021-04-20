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

        pc = new PromotionalCostImpl(promotionalCosts);
        mc = new MarketingContributionImpl(gp, pc);

        notifyObservers();
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
