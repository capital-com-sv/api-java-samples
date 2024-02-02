package com.capital.api.java.samples.util;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;

public final class AlgorithmUtil {
    public static double[] rsi(double[] prices, int period) {
        Core core = new Core();
        double[] output = new double[prices.length];
        double[] tempOutPut = new double[prices.length];
        MInteger begin = new MInteger();
        MInteger length = new MInteger();
        begin.value = -1;
        length.value = -1;
        core.rsi(0, prices.length - 1, prices, period, begin, length, tempOutPut);
        for (int i = 0; i < period; i++) {
            output[i] = 0;
        }
        for (int i = period; 0 < i && i < (prices.length); i++) {
            output[i] = tempOutPut[i - period];
        }
        return output;
    }
}
