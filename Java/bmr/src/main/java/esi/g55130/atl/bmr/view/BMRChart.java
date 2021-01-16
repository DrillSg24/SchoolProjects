package esi.g55130.atl.bmr.view;

import esi.g55130.atl.bmr.model.BMRModel;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * Class represents a chart in this program. Every chart has separate series for male and female data.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public abstract class BMRChart extends LineChart<Number, Number> implements Observer {

    private final XYChart.Series<Number, Number> maleData;
    private final XYChart.Series<Number, Number> femaleData;

    /**
     * Constructor for the chart, creates a Line chart with the two data series.
     *
     * @param numberAxis  The x-axis
     * @param numberAxis2 The y-axis
     */
    public BMRChart(Axis<Number> numberAxis, Axis<Number> numberAxis2) {
        super(numberAxis, numberAxis2);
        this.femaleData = new Series<>();
        femaleData.setName("Femme");
        this.getData().add(femaleData);
        this.maleData = new Series<>();
        maleData.setName("Homme");
        this.getData().add(maleData);
    }

    public Series<Number, Number> getMaleData() {
        return maleData;
    }

    public Series<Number, Number> getFemaleData() {
        return femaleData;
    }

    /**
     * This method is to be defined by the different charts that extend BMRChart.
     *
     * @param bmrModel Model of the game to get data from.
     */
    @Override
    public abstract void update(BMRModel bmrModel);
}
