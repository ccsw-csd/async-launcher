package com.ccsw.poc.orquestador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ccsw.poc.job.ExecutionStep;
import com.ccsw.poc.orquestador.model.JobContext;

@Service
public class OrquestadorServiceImpl implements OrquestadorService {

    @Autowired
    private ApplicationContext context;

    @Override
    public void execute(JobContext jobContext) {

        System.out.println("Ejecutamos job: " + jobContext.getJob().getId());

        //TODO: Decidir cual es el primer step
        String nextStep = "step1";

        while (nextStep != null) {
            nextStep = executeStep(nextStep, jobContext);
        }

        System.out.println("Fin job: " + jobContext.getJob().getId());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String executeStep(String beanName, JobContext jobContext) {

        ExecutionStep executionStep = getBean(beanName);

        if (executionStep == null)
            return null;

        return executionStep.execute(jobContext);
    }

    private ExecutionStep getBean(String beanName) {

        Object bean = null;

        try {
            bean = context.getBean(beanName);
        } catch (Exception e) {
            bean = null;
        }

        if (bean == null) {
            System.out.println("Error. No se encontró el bean [" + beanName + "]");
            return null;
        }

        if (bean instanceof ExecutionStep)
            return (ExecutionStep) bean;

        System.out.println("Error. El bean [" + beanName + "] no es de tipo ExecutionStep");

        return null;

    }

}
