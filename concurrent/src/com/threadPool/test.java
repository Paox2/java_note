package com.threadPool;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class test {
    private static Logger log = Logger.getLogger("com.threadPool");
    public static void main(String[] args) {
        threadPool pool = new threadPool(1,1000, TimeUnit.MILLISECONDS,1, ((queue, task) -> {
            queue.put(task);}));
//        switch (args[1]) {
//            case "1" :
//                pool = new threadPool(1,1000, TimeUnit.MILLISECONDS,1, ((queue, task) -> {
//                    queue.put(task);
//                }));
//                break;
//            case "2" :
//                pool = new threadPool(1, 1000, TimeUnit.MILLISECONDS, 1, ((queue, task) -> {
//                    queue.offer(task, 500, TimeUnit.MILLISECONDS);
//                }));
//                break;
//            case "3" :
//                pool = new threadPool(1, 1000, TimeUnit.MILLISECONDS, 1, ((queue, task) -> {
//                    log.info("give up: "+task);
//                }));
//                break;
//            case "4" :
//                pool = new threadPool(1, 1000, TimeUnit.MILLISECONDS, 1, ((queue, task) -> {
//                    throw new RuntimeException("task execute fail: "+task);
//                }));
//                break;
//            case "5" :
//                pool = new threadPool(1, 1000, TimeUnit.MILLISECONDS, 1, ((queue, task) -> {
//                    task.run();
//                }));
//                break;
//        }

        for (int i = 0; i < 3; i ++) {
            int finalI = i;
            pool.execute(()->{
                log.info(""+ finalI);
            });
        }
    }
}
