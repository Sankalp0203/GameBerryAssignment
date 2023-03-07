package gameberry.service.recommendation.rules;

import java.util.*;
import gameberry.model.Restaurant;
import gameberry.service.recommendation.AttributeEnum;
import gameberry.service.recommendation.filtercriteria.BaseCriteria;
import gameberry.service.recommendation.ProcessedInput;
import gameberry.service.recommendation.ProcessedOutput;

public abstract class RecommendationBaseRule {
    protected final RecommendationBaseRule nextRule;
    protected BaseCriteria baseCriteria;
    protected final Integer topKRestraunts;
    protected final ProcessedInput input;
    protected final ProcessedOutput output;

    RecommendationBaseRule(RecommendationBaseRule nextRule, ProcessedInput input, ProcessedOutput output){
        topKRestraunts = 100;
        this.nextRule = nextRule;
        this.input = input;
        this.output = output;
    }

    abstract Set<String> sortAndFilter(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet);

    public LinkedHashSet<String> applyNext() {
        Set<String> filteredRestaurants = sortAndFilter(input.getClassifedRestrauntMap(), input.getRestaurantSet());
        output.mergeAndReturn(filteredRestaurants, topKRestraunts);

        if(nextRule != null && output.validateSetSize(topKRestraunts)){
            nextRule.applyNext();
        }

        return output.getRecommendedRestaurants();
    }
}
