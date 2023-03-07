package gameberry.service.recommendation;

import gameberry.model.Restaurant;
import gameberry.model.User;
import gameberry.service.recommendation.rules.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationRuleFactory {

    public RecommendationBaseRule createRule(User user, Restaurant[] restaurantList){
        List<Restaurant> restaurants = Arrays.stream(restaurantList).collect(Collectors.toList());
        ProcessedInput inputs = new ProcessedInput(user, restaurants);
        ProcessedOutput outputs = new ProcessedOutput();

        RecommendationBaseRule rule9 = new RecommendationRule9(null, inputs, outputs);
        RecommendationBaseRule rule8 = new RecommendationRule8(rule9, inputs, outputs);
        RecommendationBaseRule rule7 = new RecommendationRule7(rule8, inputs, outputs);
        RecommendationBaseRule rule6 = new RecommendationRule6(rule7, inputs, outputs);
        RecommendationBaseRule rule5 = new RecommendationRule5(rule6, inputs, outputs, 4);
        RecommendationBaseRule rule4 = new RecommendationRule4(rule5, inputs, outputs);
        RecommendationBaseRule rule3 = new RecommendationRule3(rule4, inputs, outputs);
        RecommendationBaseRule rule2 = new RecommendationRule2(rule3, inputs, outputs);
        RecommendationBaseRule rule1 = new RecommendationRule1(rule2, inputs, outputs);

        return rule1;
    }
}
