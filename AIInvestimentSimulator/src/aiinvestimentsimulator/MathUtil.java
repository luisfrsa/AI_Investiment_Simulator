package aiinvestimentsimulator;


import java.text.DecimalFormat;

public final class MathUtil {

    public static Double getPercentage(Double firstValue, Double seccondValue) {
        if (firstValue != 0) {
//            double scale = Math.pow(10, 3);
            double valor = (int) (((seccondValue * 100 / firstValue) - 100) * 100);
            return valor / 1000;
        }
        return 0.0;

    }
}
