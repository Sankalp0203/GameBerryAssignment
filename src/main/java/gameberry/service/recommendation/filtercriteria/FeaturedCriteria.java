package gameberry.service.recommendation.filtercriteria;


import gameberry.model.Restaurant;
import gameberry.service.recommendation.AttributeEnum;

import java.util.Map;
import java.util.Set;

public class FeaturedCriteria extends CriteriaDecorator {
    private final Boolean isFeatured;

    public FeaturedCriteria(BaseCriteria baseCriteria, Boolean isFeatured) {
        super(baseCriteria);
        this.baseCriteria = baseCriteria;
        this.isFeatured = isFeatured;
    }


    @Override
    public Set<String> filterRestraunts(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet) {
        Map<String, Set<String>> featuredMap = classifiedRestraunts.get(AttributeEnum.FEATURED);
        Set<String> filteredSet = featuredMap.get(isFeatured.toString());
        filteredRestaurants.retainAll(filteredSet);
        return filteredRestaurants;
    }
}
