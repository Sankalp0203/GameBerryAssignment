package gameberry.service.recommendation;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class ProcessedOutput {
    private LinkedHashSet<String> recommendedRestaurants = new LinkedHashSet<>();

    public LinkedHashSet mergeAndReturn(Set<String> toBeMerged, int maxSize){
        for(String restrauntId: toBeMerged){
            if(validateSetSize(maxSize)){
                recommendedRestaurants.add(restrauntId);
            }
        }
        return recommendedRestaurants;
    }

    public boolean validateSetSize(int maxSize){
        return recommendedRestaurants.size() < maxSize;
    }

}
