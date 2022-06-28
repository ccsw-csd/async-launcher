package com.ccsw.poc.queue.pool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.ccsw.poc.queue.model.QueueJobEntity;
import com.ccsw.poc.queue.model.QueueJobRepository;

@Service
public class QueueJobListener {

    @Value("${app.async.populateThreshold}")
    private int populateThreshold;

    @Autowired
    private QueueJobRepository queueJobRepository;

    @Autowired
    @Qualifier("executor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ConsumerPoolService consumerPoolService;

    @Scheduled(fixedDelay = 5000)
    private void executeListenerBatch() {

        if (getQueueTasks() < populateThreshold) {
            populateQueue();
        }

    }

    private int getQueueTasks() {
        return threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().size();
    }

    private void populateQueue() {

        List<QueueJobEntity> jobs = queueJobRepository.findTop100ByCompleteAndUrgentAndStatusAndSemaphoreIsNullOrderByIdAsc(true, true, "PND");

        if (jobs.size() == 0)
            return;

        threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().clear();
        for (QueueJobEntity job : jobs) {
            consumerPoolService.execute(job);
        }
    }

}
