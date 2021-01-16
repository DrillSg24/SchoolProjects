package esi.g55130.atl.bmr.view;

import esi.g55130.atl.bmr.model.BMRModel;
import esi.g55130.atl.bmr.model.Sex;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Chart of the BMR as a function of the height.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class BMRChart_bmrVsHeight extends BMRChart {

    /**
     * Uses the constructor of the BMRChart class.<br>
     * Defines the x-axis as the height and the y-axis as the BMR index.
     */
    public BMRChart_bmrVsHeight() {
        super(new NumberAxis(), new NumberAxis());
        this.getXAxis().setLabel("Height (cm)");
        this.getYAxis().setLabel("BMR index");
        this.setTitle("BMR Index vs Height");
    }

    /**
     * Creates a new couple of coordinates (height,bmr) from the model, then adds it to the corresponding data series.
     *
     * @param bmrModel Model of the game to get data from.
     */
    @Override
    public void update(BMRModel bmrModel) {
        XYChart.Data<Number, Number> newCouple =
                new XYChart.Data<>(
                        bmrModel.getHeight()
                        , bmrModel.getBMR()
                );
        if (bmrModel.getSex() == Sex.MALE) {
            super.getMaleData().getData().add(newCouple);
        } else {
            super.getFemaleData().getData().add(newCouple);
        }
    }

}
