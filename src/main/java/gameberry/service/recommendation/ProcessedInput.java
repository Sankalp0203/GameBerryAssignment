package gameberry.service.recommendation;

import gameberry.model.Cuisine;
import gameberry.model.Restaurant;
import gameberry.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ProcessedInput {
    User user;
    Set<Restaurant> restaurantSet;
    Set<String> restrauntIdSet;
    Map<AttributeEnum, Map<String, Set<String>>> classifedRestrauntMap;
    private Cuisine primaryCuisine;
    private Cuisine secondaryCuisineFirst;
    private Cuisine secondaryCuisineSecond;

    private Integer primaryCostBracket;
    private Integer secondaryCostBracketFirst;
    private Integer secondaryCostBracketSecond;


    public ProcessedInput(User user, List<Restaurant> restrauntList) {
        this.user = user;
        for(Restaurant restaurant: restrauntList){
            this.restaurantSet.add(restaurant);
            this.restrauntIdSet.add(restaurant.getRestaurantId());
        }
        classifedRestrauntMap = new HashMap<>();
        classifyRestraunts();
    }

    private void classifyRestraunts(){
        Map<String, Set<String>> costMap = new HashMap<>();
        Map<String, Set<String>> ratingMap = new HashMap<>();
        Map<String, Set<String>> cuisineMap = new HashMap<>();
        Map<String, Set<String>> featuredMap = new HashMap<>();

        for(Restaurant restaurant: restaurantSet){
            costMap.computeIfAbsent(Integer.toString(restaurant.getCostBracket()), x -> new HashSet<>()).add(restaurant.getRestaurantId());
            ratingMap.computeIfAbsent(Float.toString(restaurant.getRating()), x -> new HashSet<>()).add(restaurant.getRestaurantId());
            cuisineMap.computeIfAbsent(restaurant.getCuisine().toString(), x->new HashSet<>()).add(restaurant.getRestaurantId());
            cuisineMap.computeIfAbsent(restaurant.getIsRecommended().toString(), x->new HashSet<>()).add(restaurant.getRestaurantId());
        }

        classifedRestrauntMap.put(AttributeEnum.COST_BRACKET, costMap);
        classifedRestrauntMap.put(AttributeEnum.CUISINE, ratingMap);
        classifedRestrauntMap.put(AttributeEnum.RATING, cuisineMap);
        classifedRestrauntMap.put(AttributeEnum.FEATURED, cuisineMap);
    }
}
