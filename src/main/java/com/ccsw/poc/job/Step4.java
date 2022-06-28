package com.ccsw.poc.job;

import org.springframework.stereotype.Service;

import com.ccsw.poc.orquestador.model.JobContext;

@Service("step4")
public class Step4 implements ExecutionStep {

    @Override
    public String execute(JobContext jobContext) {
        System.out.println("Soy el step 4");

        return "stepNoExiste";
    }

}
