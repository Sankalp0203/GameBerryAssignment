package gameberry.service.recommendation.rules;

import gameberry.model.Restaurant;
import gameberry.service.recommendation.AttributeEnum;
import gameberry.service.recommendation.ProcessedInput;
import gameberry.service.recommendation.ProcessedOutput;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendationRule5 extends RecommendationBaseRule {

    int kLatest;

    public RecommendationRule5(RecommendationBaseRule nextRule, ProcessedInput input, ProcessedOutput output, int kLatest){
        super(nextRule, input, output);
        this.kLatest = kLatest;
    }
    @Override
    Set<String> sortAndFilter(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet) {
        Set<String> primaryFilterSet = setBaseCriteriaAndFilter(classifiedRestraunts, restrauntSet);
        return primaryFilterSet;
    }

    private Set<String> setBaseCriteriaAndFilter(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet){
        List<Restaurant> restaurantList = restrauntSet.stream().collect(Collectors.toList());
        Collections.sort(restaurantList, Comparator.comparing(Restaurant::getOnboardedTime).reversed());
        Set<String> orderedSet = new HashSet<>();
        for(int i=0;i<kLatest;i++){
            orderedSet.add(restaurantList.get(i).getRestaurantId());
        }

        return orderedSet;
    }
}

