package esi.g55130.atl.bmr.view;

import esi.g55130.atl.bmr.model.BMRModel;
import esi.g55130.atl.bmr.model.Sex;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Chart of the calories count as function of the weight.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class BMRChart_calVsWeight extends BMRChart {
    /**
     * Uses the constructor of the BMRChart class.<br>
     * Defines the x-axis as the weight and the y-axis as the calories count.
     */
    public BMRChart_calVsWeight() {
        super(new NumberAxis(), new NumberAxis());
        this.getXAxis().setLabel("Weight (kg)");
        this.getYAxis().setLabel("Calories (kCal)");
        this.setTitle("Calories vs Weight");
    }

    /**
     * Creates a new couple of coordinates (weight,calories) from the model,
     * then adds it to the corresponding data series.
     *
     * @param bmrModel Model of the game to get data from.
     */
    @Override
    public void update(BMRModel bmrModel) {
        XYChart.Data<Number, Number> newCouple =
                new XYChart.Data<>(
                        bmrModel.getWeight()
                        , bmrModel.getCalories()
                );
        if (bmrModel.getSex() == Sex.MALE) {
            super.getMaleData().getData().add(newCouple);
        } else {
            super.getFemaleData().getData().add(newCouple);
        }
    }
}
