package com.habbokt.game

import com.google.inject.Inject
import com.google.inject.Singleton
import com.habbokt.api.client.Client
import com.habbokt.api.threading.Synchronizer
import io.ktor.server.application.ApplicationEnvironment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/**
 * @author Jordan Abraham
 */
@Singleton
class GameSynchronizer @Inject constructor(
    private val applicationEnvironment: ApplicationEnvironment,
    private val dispatcher: CoroutineDispatcher,
    private val connectionPool: ConnectionPool,
    private val executorService: ScheduledExecutorService
) : Synchronizer {
    private var cycle = 0

    override fun start() {
        executorService.scheduleAtFixedRate(this, 500, 500, TimeUnit.MILLISECONDS)
    }

    override fun stop() {
        executorService.shutdown()
    }

    @OptIn(ExperimentalTime::class)
    override fun run() {
        try {
            if (executorService.isShutdown) return

            val time = measureTime {
                runBlocking(dispatcher) {
                    // The dispatcher controls the parallelism.
                    connectionPool.parallelStream().forEach(Client::processReadPool)
                    connectionPool.parallelStream().forEach(Client::processWritePool)
                }
            }

            cycle++

            applicationEnvironment.log.info("Game Synchronizer: Time: $time, Clients: ${connectionPool.size}, Cycle: $cycle")
        } catch (exception: Exception) {
            applicationEnvironment.log.error(exception.stackTraceToString())
        }
    }
}