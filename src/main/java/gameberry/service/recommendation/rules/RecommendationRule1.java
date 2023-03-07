package gameberry.service.recommendation.rules;
import gameberry.model.Restaurant;
import gameberry.service.recommendation.*;
import gameberry.service.recommendation.filtercriteria.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecommendationRule1 extends RecommendationBaseRule {

    public RecommendationRule1(RecommendationBaseRule nextRule, ProcessedInput input, ProcessedOutput output){
        super(nextRule, input, output);
    }


    @Override
    Set<String> sortAndFilter(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet) {
        Set<String> primaryFilterSet = setBaseCriteriaAndFilter(classifiedRestraunts, restrauntSet);
        if(primaryFilterSet.isEmpty()){
            return setSecondaryBaseCriteria(classifiedRestraunts, restrauntSet);
        }
        return primaryFilterSet;
    }

    private Set<String> setBaseCriteriaAndFilter(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet){
       BaseCriteria baseCriteriaInput = new ConcreteBaseCriteria(input.getRestrauntIdSet());
       baseCriteriaInput = new CuisineCriteria(baseCriteriaInput, List.of(input.getPrimaryCuisine()));
       baseCriteriaInput = new CostCriteria(baseCriteriaInput, List.of(input.getPrimaryCostBracket()));
       baseCriteriaInput = new FeaturedCriteria(baseCriteriaInput, Boolean.TRUE);
       this.baseCriteria = baseCriteriaInput;
       return baseCriteria.filterRestraunts(classifiedRestraunts, restrauntSet);
    }

    private Set<String> setSecondaryBaseCriteria(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet){
        BaseCriteria baseCriteriaInput = new ConcreteBaseCriteria(input.getRestrauntIdSet());
        baseCriteriaInput = new CuisineCriteria(baseCriteriaInput, List.of(input.getPrimaryCuisine()));
        baseCriteriaInput = new CostCriteria(baseCriteriaInput, List.of(input.getSecondaryCostBracketFirst(), input.getSecondaryCostBracketSecond()));
        this.baseCriteria = baseCriteriaInput;
        Set<String> setIntermediateFirst = baseCriteria.filterRestraunts(classifiedRestraunts, restrauntSet);

        baseCriteriaInput = new ConcreteBaseCriteria(setIntermediateFirst);
        baseCriteriaInput = new CostCriteria(baseCriteriaInput, List.of(input.getPrimaryCostBracket()));
        baseCriteriaInput = new CuisineCriteria(baseCriteriaInput, List.of(input.getSecondaryCuisineFirst(), input.getSecondaryCuisineSecond()));
        this.baseCriteria = baseCriteriaInput;
        setIntermediateFirst = baseCriteria.filterRestraunts(classifiedRestraunts, restrauntSet);

        return setIntermediateFirst;
    }
}
