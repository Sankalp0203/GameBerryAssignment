package gameberry.service.recommendation.rules;

import gameberry.model.Restaurant;
import gameberry.service.recommendation.*;
import gameberry.service.recommendation.filtercriteria.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecommendationRule7 extends RecommendationBaseRule {

    public RecommendationRule7(RecommendationBaseRule nextRule, ProcessedInput input, ProcessedOutput output){
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
        baseCriteriaInput = new CostCriteria(baseCriteriaInput, List.of(input.getSecondaryCostBracketFirst(), input.getSecondaryCostBracketSecond()));
        baseCriteriaInput = new RatingCriteria(baseCriteriaInput, 4.5F, List.of(ComparisonEnum.LESS_THAN));
        this.baseCriteria = baseCriteriaInput;
        return baseCriteria.filterRestraunts(classifiedRestraunts, restrauntSet);
    }
}