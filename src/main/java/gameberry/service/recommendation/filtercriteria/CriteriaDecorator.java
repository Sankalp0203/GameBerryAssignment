package gameberry.service.recommendation.filtercriteria;

public abstract class CriteriaDecorator extends BaseCriteria{
    BaseCriteria baseCriteria;

    CriteriaDecorator(BaseCriteria baseCriteria) {
        super(baseCriteria.filteredRestaurants);
        this.baseCriteria = baseCriteria;
    }

}
