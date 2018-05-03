//package aiinvestimentsimulator;


import java.text.DecimalFormat;

import static java.lang.Math.*;

public final class MathUtil {

    public static double getFormatedPercentage(double currentValue, double targetValue) {
        if (currentValue != 0) {
            double valor = (targetValue * 100 / currentValue) * 100;
        }
        return 0.0;

    }

    public static double getPercentage(double currentValue, double targetValue) {
        if (currentValue != 0) {
            return (targetValue * 100 / currentValue);
        }
        return 0.0;
    }

    public static int numerOfActionsFromValue(double avaliableMoney, double actionValue) {
        return (int) floor(avaliableMoney / actionValue);
    }

    public static double calcAVGWithParamPercentToBuy(double PARAM_PERCENT, double average) {
        return average * (1 - PARAM_PERCENT);
    }

    public static double calcAVGWithParamPercentToSell(double PARAM_PERCENT, double average) {
        return average * (1 + PARAM_PERCENT);

    }
}