package com.wk.cpd.mvc.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: 随机数生成及工厂
 */
public class RandomFactory {

    /** 随意数生成器仓库 
     *  @key 
     *      策略主键
     *  @value 
     *      随机数生成器
     *  @description
     *      每个策略使用自己的随机数生成器，使得每个策略都获得均匀分布的生成数字 。
     *      当策略停止使用后，这个策略对应的随机数生成器{@code RandomManager}会被删除
     *      出现新策略，生成新的随机数生成器 **/
    private final static Map<String, DAPRandom> randomWarehouse = new HashMap<>();
    
    /**
     * @description: 获取随机数生成器
     * @param key
     * @return
     */
    public static DAPRandom getRandom(String key) {
        
        return randomWarehouse.get(key);
    }
    
    public static DAPRandom random(String key) {
        
        // 从仓库中获取生成器
        DAPRandom random = randomWarehouse.get(key);
        // 若没有，生成新的生成器，并放入仓库
        if (null == random) {
            random = new DAPRandom();
            randomWarehouse.put(key, random);
        }
        // 设置生成器为使用状态
        random.setUsered(true);
        return random;
    }
    
    /**
     * @description: 获取全部的key值
     * @return
     */
    public static Set<String> getAllkeys() {
        return randomWarehouse.keySet();
    }
    
    /**
     * @description: 删除随机数生成器
     * @param key
     */
    public static void remove(String key) {
        randomWarehouse.remove(key);
    }
}
