package gameberry.service.recommendation.filtercriteria;

import gameberry.model.Restaurant;
import gameberry.service.recommendation.AttributeEnum;

import java.util.Map;
import java.util.Set;

public class ConcreteBaseCriteria extends BaseCriteria{
    public ConcreteBaseCriteria(Set<String> restrauntSet){
        super(restrauntSet);
    }

    @Override
    public Set<String> filterRestraunts(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet) {
        return filteredRestaurants;
    }
}
