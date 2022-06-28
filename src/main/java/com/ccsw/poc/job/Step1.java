package com.ccsw.poc.job;

import org.springframework.stereotype.Service;

import com.ccsw.poc.orquestador.model.JobContext;

@Service("step1")
public class Step1 implements ExecutionStep {

    @Override
    public String execute(JobContext jobContext) {
        System.out.println("Soy el step 1");

        jobContext.putParam("step1-param", "Soy una variable de contexto: " + System.currentTimeMillis());

        return "step2";
    }

}
