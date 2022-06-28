package com.ccsw.poc.job;

import com.ccsw.poc.orquestador.model.JobContext;

public interface ExecutionStep {

    String execute(JobContext jobContext);

}
