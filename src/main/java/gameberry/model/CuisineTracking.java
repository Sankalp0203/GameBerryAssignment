package gameberry.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuisineTracking {
    private Cuisine type;
    private Long noOfOrders;
}
