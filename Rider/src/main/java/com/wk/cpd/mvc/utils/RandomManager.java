package com.wk.cpd.mvc.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 随机数生成器管理类
 */
public class RandomManager extends Thread {
    
    private boolean isRunning = true;
    
    public void done() {
        isRunning = false;
    }
    @Override
    public void run() {

        List<String> removeKeys = new ArrayList<>();
        Date date = new Date();
        while (true) {
            
            date.setTime(System.currentTimeMillis());
            System.out.println(date.toString() + " ==================清理无用随机数生成器==============");
            // 遍历随机数生成器仓库，删除无用的生成器
            for (String key : RandomFactory.getAllkeys()) {

                // 获取特定生成器
                DAPRandom random = RandomFactory.getRandom(key);
                // 如果生成器正在被使用
                if (random.isUsered()) {
                    // 设置被清理次数为0.这样容易识别长时间不被使用的生成器
                    random.getCleanTime().set(0);
                    // 设置生成器不被使用状态
                    random.setUsered(false);
                } else { // 若生成器没有被使用
                    // 生成器被检测到没有被使用的次数+1
                    random.getCleanTime().incrementAndGet();
                    // 如果生成器被检测到没有被使用的次数大于5，删除生成器
                    if (random.getCleanTime().get() > 5) {
                        removeKeys.add(key);
                    }
                }
            }
            
            for (String key : removeKeys) {
                RandomFactory.remove(key);
            }
            removeKeys.clear();
            try {
                // 每小时执行一次。暂时不使用文件可配置
                Thread.sleep(3600000L);
            } catch (InterruptedException e) {
                // tomcat停服务时被唤醒
//                e.printStackTrace();
            }
            
            // 避免停tomcat时报错，唤醒线程后运行完成
            if (!isRunning) {
                break;
            }
        }
    }

}
