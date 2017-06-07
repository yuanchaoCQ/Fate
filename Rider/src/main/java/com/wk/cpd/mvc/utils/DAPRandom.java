package com.wk.cpd.mvc.utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: Dap使用的随机数生成器
 */
public class DAPRandom {

    private final Random choiceRandom = new Random();
    
    private final Random distribute = new Random();
    /** 生成器是否被使用 **/
    private boolean isUsered = true;
    
    /** 生成器被清理次数 **/
    private AtomicInteger cleanTime = new AtomicInteger(0);

    public boolean isUsered() {
        return isUsered;
    }

    public void setUsered(boolean isUsered) {
        this.isUsered = isUsered;
    }

    public AtomicInteger getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(AtomicInteger cleanTime) {
        this.cleanTime = cleanTime;
    }

    public Random getChoiceRandom() {
        return choiceRandom;
    }

    public Random getDistribute() {
        return distribute;
    }
}
