package gameberry.service.recommendation.rules;

import gameberry.model.Restaurant;
import gameberry.service.recommendation.AttributeEnum;
import gameberry.service.recommendation.ProcessedInput;
import gameberry.service.recommendation.ProcessedOutput;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RecommendationRule9 extends RecommendationBaseRule {

    public RecommendationRule9(RecommendationBaseRule nextRule, ProcessedInput input, ProcessedOutput output){
        super(nextRule, input, output);
    }
    @Override
    Set<String> sortAndFilter(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet) {
        Set<String> primaryFilterSet = setBaseCriteriaAndFilter(classifiedRestraunts, restrauntSet);
        return primaryFilterSet;
    }

    private Set<String> setBaseCriteriaAndFilter(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet){
        return restrauntSet.stream().map((restaurant) -> restaurant.getRestaurantId()).collect(Collectors.toSet());
    }
}
