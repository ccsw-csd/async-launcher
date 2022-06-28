package com.ccsw.poc.queue.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ccsw.poc.orquestador.OrquestadorService;
import com.ccsw.poc.orquestador.model.JobContext;
import com.ccsw.poc.queue.model.QueueJobEntity;
import com.ccsw.poc.queue.model.QueueJobRepository;

@Service
public class ConsumerPoolServiceImpl implements ConsumerPoolService {

    @Autowired
    private QueueJobRepository queueJobRepository;

    @Autowired
    private OrquestadorService orquestadorService;

    @Override
    @Async
    public void execute(QueueJobEntity job) {

        lockAndExecuteJob(job);

    }

    private void lockAndExecuteJob(QueueJobEntity job) {
        if (job == null)
            return;

        if (lockQueueJob(job.getId())) {
            try {
                orquestadorService.execute(new JobContext(job));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private String generateSemaphore() {

        StringBuilder key = new StringBuilder();
        key.append(Thread.currentThread().getName());
        key.append("_");
        key.append(System.currentTimeMillis());
        key.append("_");
        key.append(Math.random());
        key.append("_");
        key.append(Math.random());

        return key.toString();
    }

    private boolean lockQueueJob(Long jobId) {
        int updatedRows = queueJobRepository.lockQueueJob(jobId, generateSemaphore());

        return updatedRows == 1;
    }

}
