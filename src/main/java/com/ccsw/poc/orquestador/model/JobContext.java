package com.ccsw.poc.orquestador.model;

import java.util.HashMap;
import java.util.Map;

import com.ccsw.poc.queue.model.QueueJobEntity;

public class JobContext {

    private QueueJobEntity job;
    private Map<String, Object> params;

    public JobContext(QueueJobEntity job) {
        this.job = job;
        this.params = new HashMap<>();
    }

    /**
     * Añade un parametro de contexto
     * @param key
     * @param value
     */
    public void putParam(String key, Object value) {
        params.put(key, value);
    }

    /**
     * Borra los parametros de contexto
     */
    public void clearParams() {
        params.clear();
    }

    /**
     * Recupera un parametro de contexto
     * @param key
     * @return
     */
    public Object getParam(String key) {
        return params.get(key);
    }

    /**
     * @return the job
     */
    public QueueJobEntity getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(QueueJobEntity job) {
        this.job = job;
    }

}
