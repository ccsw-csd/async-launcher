package com.ccsw.poc.job;

import org.springframework.stereotype.Service;

import com.ccsw.poc.orquestador.model.JobContext;

@Service("step5")
public class Step5 implements ExecutionStep {

    @Override
    public String execute(JobContext jobContext) {
        System.out.println("Soy el step 5");

        return "OrquestadorService";
    }

}
