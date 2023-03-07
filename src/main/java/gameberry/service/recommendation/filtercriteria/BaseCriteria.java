package gameberry.service.recommendation.filtercriteria;

import gameberry.model.Restaurant;
import gameberry.service.recommendation.AttributeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;


@Getter
@Setter
public abstract class BaseCriteria {

    protected Set<String> filteredRestaurants;

    BaseCriteria(Set<String> restaurantList) {
        this.filteredRestaurants = restaurantList;
    }

    public abstract Set<String> filterRestraunts(Map<AttributeEnum, Map<String, Set<String>>> classifiedRestraunts, Set<Restaurant> restrauntSet);
}
