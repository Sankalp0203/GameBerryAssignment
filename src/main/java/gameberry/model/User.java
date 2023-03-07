package gameberry.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Getter
@RequiredArgsConstructor
public class User {
    private CuisineTracking[] cuisines;
    private CostTracking[] costBracket;
    private List<Cuisine> topThreeCuisine = new ArrayList<>();
    private List<Integer> topThreeCostBracket = new ArrayList<>();

    public User(CuisineTracking[] cuisines, CostTracking[] costBracket){
        this.cuisines = cuisines;
        this.costBracket = costBracket;
        updateTopThreeCostBrackets();
        updateTopThreeCuisine();
    }

    private void updateTopThreeCuisine(){
        PriorityQueue<CuisineTracking> topThreeHeap = new PriorityQueue<CuisineTracking>(3,
                Comparator.comparing(CuisineTracking::getNoOfOrders));
        for(CuisineTracking cuisineTracking: cuisines){
            if(topThreeHeap.size() < 3){
                topThreeHeap.offer(cuisineTracking);
            } else {
                CuisineTracking cuisineTrackingTop = topThreeHeap.peek();
                if(cuisineTrackingTop.getNoOfOrders() < cuisineTracking.getNoOfOrders()){
                    topThreeHeap.poll();
                    topThreeHeap.offer(cuisineTracking);
                }
            }
        }

        while(!topThreeHeap.isEmpty()){
            topThreeCuisine.add(topThreeHeap.poll().getType());
        }
    }

    private void updateTopThreeCostBrackets(){
        PriorityQueue<CostTracking> topThreeHeap = new PriorityQueue<CostTracking>(3,
                Comparator.comparing(CostTracking::getNoOfOrders));
        for(CostTracking costTracking: costBracket){
            if(topThreeHeap.size() < 3){
                topThreeHeap.offer(costTracking);
            } else {
                CostTracking cuisineTrackingTop = topThreeHeap.peek();
                if(costTracking.getNoOfOrders() < costTracking.getNoOfOrders()){
                    topThreeHeap.poll();
                    topThreeHeap.offer(costTracking);
                }
            }
        }

        while(!topThreeHeap.isEmpty()){
            topThreeCostBracket.add(topThreeHeap.poll().getType());
        }
    }


    public Cuisine getPrimaryCuisine(){
        return topThreeCuisine.get(topThreeCuisine.size()-1);
    }

    public Integer getPrimaryCostBracket(){
        return topThreeCostBracket.get(topThreeCostBracket.size()-1);
    }



}
