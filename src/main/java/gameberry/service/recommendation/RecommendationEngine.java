package gameberry.service.recommendation;

import gameberry.model.Restaurant;
import gameberry.model.User;
import gameberry.service.recommendation.rules.RecommendationBaseRule;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class RecommendationEngine {

    private RecommendationBaseRule recommendationBaseRule;
    private RecommendationRuleFactory factory;

    public RecommendationEngine(){
        factory = new RecommendationRuleFactory();
    }

    public ArrayList<String> getRestaurantRecommendations(User user, Restaurant[] restaurants){
        recommendationBaseRule = factory.createRule(user,restaurants);
        LinkedHashSet<String> reccomendations = recommendationBaseRule.applyNext();
        return (ArrayList<String>) reccomendations.stream().collect(Collectors.toList());
    }

}
