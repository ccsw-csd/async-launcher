package com.ccsw.poc.job;

import org.springframework.stereotype.Service;

import com.ccsw.poc.orquestador.model.JobContext;

@Service("step2")
public class Step2 implements ExecutionStep {

    @Override
    public String execute(JobContext jobContext) {
        System.out.println("Soy el step 2");

        System.out.println("\tLeo la variable -> " + jobContext.getParam("step1-param"));

        return "step3";
    }

}
