package esi.g55130.atl.bmr.view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * TabPane of three different charts in the program.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class ChartsTabPane extends TabPane {
    /**
     * Constructor a tab pane using the different charts in parameter.
     *
     * @param bmrVsWeightChart      Chart of BMR vs weight.
     * @param caloriesVsWeightChart Chart of calories vs weight.
     * @param bmrVsHeightChart      Chart of BMR vs height.
     */
    public ChartsTabPane(BMRChart bmrVsWeightChart, BMRChart caloriesVsWeightChart, BMRChart bmrVsHeightChart) {
        Tab bmrVsWeightTab = new Tab("BMR vs Weight", bmrVsWeightChart);
        Tab caloriesVsWeightTab = new Tab("Calories vs Weight", caloriesVsWeightChart);
        Tab bmrVsHeightTab = new Tab("BMR vs Height", bmrVsHeightChart);
        this.getTabs().addAll(bmrVsWeightTab, caloriesVsWeightTab, bmrVsHeightTab);
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }
}
