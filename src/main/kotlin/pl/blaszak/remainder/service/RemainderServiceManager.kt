package pl.blaszak.remainder.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.ExecutorService
import javax.annotation.PreDestroy

class RemainderServiceManager(private var executorService: ExecutorService, private var remainderServices: List<RemainderService>) {

    private var logger: Logger = LoggerFactory.getLogger(RemainderServiceManager::class.java)

    fun start() {
        logger.debug("Starting threads...")
        remainderServices.forEach { executorService.submit(it) }
    }

    @PreDestroy
    fun stop() {
        logger.debug("Stopping threads...")
        executorService.shutdown()
    }
}