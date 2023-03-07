package gameberry.service.recommendation.rules;

import gameberry.model.Restaurant;
import gameberry.service.recommendation.*;
import gameberry.service.recommendation.filtercriteria.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecommendationRule2 extends RecommendationBaseRule {

    public RecommendationRule2(RecommendationBaseRule nextRule, ProcessedInput input, ProcessedOutput output){
        super(nextRule, input, output);
    }
    @Override
    Set<String> sortAndFilter(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet) {
        Set<String> primaryFilterSet = setBaseCriteriaAndFilter(classifiedRestraunts, restrauntSet);
        return primaryFilterSet;
    }

    private Set<String> setBaseCriteriaAndFilter(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet){
        BaseCriteria baseCriteriaInput = new ConcreteBaseCriteria(input.getRestrauntIdSet());
        baseCriteriaInput = new CuisineCriteria(baseCriteriaInput, List.of(input.getPrimaryCuisine()));
        baseCriteriaInput = new CostCriteria(baseCriteriaInput, List.of(input.getPrimaryCostBracket()));
        baseCriteriaInput = new RatingCriteria(baseCriteriaInput, 4F, List.of(ComparisonEnum.GREATER_THAN, ComparisonEnum.EQUAL_TO));
        this.baseCriteria = baseCriteriaInput;
        return baseCriteria.filterRestraunts(classifiedRestraunts, restrauntSet);
    }
}
