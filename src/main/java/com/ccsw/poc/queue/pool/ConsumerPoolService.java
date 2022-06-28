package com.ccsw.poc.queue.pool;

import com.ccsw.poc.queue.model.QueueJobEntity;

public interface ConsumerPoolService {

    /**
     * Hilo de ejecución que lanza un orquestador
     */
    void execute(QueueJobEntity job);

}
