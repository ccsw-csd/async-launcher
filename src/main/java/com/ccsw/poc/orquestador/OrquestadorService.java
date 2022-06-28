package com.ccsw.poc.orquestador;

import com.ccsw.poc.orquestador.model.JobContext;

public interface OrquestadorService {

    /**
     * Orquesta la ejecución de un job
     * @param jobContext
     */
    void execute(JobContext jobContext);
}
