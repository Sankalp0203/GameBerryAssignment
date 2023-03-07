package gameberry.service.recommendation.filtercriteria;

import gameberry.model.Cuisine;
import gameberry.model.Restaurant;
import gameberry.service.recommendation.AttributeEnum;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CuisineCriteria extends CriteriaDecorator {
    private final List<Cuisine> cuisineList;

    public CuisineCriteria(BaseCriteria baseCriteria, List<Cuisine> cuisineList) {
        super(baseCriteria);
        this.cuisineList = cuisineList;
    }

    @Override
    public Set<String> filterRestraunts(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet) {
        Set<String> startSet = new HashSet<>();

        for(Cuisine cuisine: cuisineList){
            Map<String, Set<String>> cuisineMap = classifiedRestraunts.get(AttributeEnum.CUISINE);
            Set<String> filteredSet = cuisineMap.get(cuisine.toString());
            startSet.addAll(filteredSet);
        }

        filteredRestaurants.retainAll(startSet);
        return filteredRestaurants;
    }
}