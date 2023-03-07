package gameberry.service.recommendation.filtercriteria;

import gameberry.model.Restaurant;
import gameberry.service.recommendation.AttributeEnum;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CostCriteria extends CriteriaDecorator {
    private final List<Integer> costBracketList;


    public CostCriteria(BaseCriteria baseCriteria, List<Integer> costBracketList) {
        super(baseCriteria);
        this.costBracketList = costBracketList;
    }

    @Override
    public Set<String> filterRestraunts(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet) {
        Set<String> startSet = new HashSet<>();
        for(Integer costBracket: costBracketList){
            Map<String, Set<String>> costMap = classifiedRestraunts.get(AttributeEnum.COST_BRACKET);
            Set<String> filteredSet = costMap.get(costBracket.toString());
            startSet.addAll(filteredSet);
        }
        filteredRestaurants.retainAll(startSet);
        return filteredRestaurants;
    }
}
