package gameberry.service.recommendation.filtercriteria;

import gameberry.model.Restaurant;
import gameberry.service.recommendation.AttributeEnum;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RatingCriteria extends BaseCriteria {
    private final BaseCriteria baseCriteria;
    private final Float rating;
    private final List<ComparisonEnum> comparisons;

    public RatingCriteria(BaseCriteria baseCriteria, Float rating, List<ComparisonEnum> comparatorList) {
        super(baseCriteria.getFilteredRestaurants());
        this.baseCriteria = baseCriteria;
        this.rating = rating;
        this.comparisons = comparatorList;
    }

    @Override
    public Set<String> filterRestraunts(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet) {
        Set<String> startSet = new HashSet<>();
        Map<String,Set<String>> ratingMap = classifiedRestraunts.get(AttributeEnum.RATING);
        for(ComparisonEnum comparator: comparisons){
            if(ComparisonEnum.EQUAL_TO.equals(comparator)) {
                startSet.addAll(ratingMap.get(rating.toString()));
            } else if(ComparisonEnum.GREATER_THAN.equals(comparator)) {
                for(Map.Entry<String,Set<String>> entry: ratingMap.entrySet()){
                    if(Float.parseFloat(entry.getKey()) > rating){
                        startSet.addAll(entry.getValue());
                    }
                }
            } else {
                for(Map.Entry<String,Set<String>> entry: ratingMap.entrySet()){
                    if(Float.parseFloat(entry.getKey()) < rating){
                        startSet.addAll(entry.getValue());
                    }
                }
            }
        }
        filteredRestaurants.retainAll(startSet);
        return filteredRestaurants;
    }
}
