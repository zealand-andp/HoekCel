package sample.usecases;

import sample.entities.NegativePromotionalCostsException;

public interface HoekCelController {

    void computeMarketingContribution(double promotionalCosts) throws NegativePromotionalCostsException;

    double getMarketingContribution();

    void addObserver(Observer observer);
}
