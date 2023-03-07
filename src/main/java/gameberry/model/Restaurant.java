package gameberry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Restaurant {
        private String restaurantId;
        private Cuisine cuisine;
        private Integer costBracket;
        private Float rating;

        public Float getRating(){
                return this.rating;
        }

        private Boolean isRecommended;
        private Date onboardedTime;
}
