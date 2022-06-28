package com.ccsw.poc.job;

import org.springframework.stereotype.Service;

import com.ccsw.poc.orquestador.model.JobContext;

@Service("step3")
public class Step3 implements ExecutionStep {

    @Override
    public String execute(JobContext jobContext) {
        System.out.println("Soy el step 3");

        if (System.currentTimeMillis() % 2 == 0)
            return "step4";

        return "step5";
    }

}
