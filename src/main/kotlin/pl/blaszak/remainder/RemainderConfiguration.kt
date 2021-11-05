package pl.blaszak.remainder

import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.blaszak.remainder.service.RemainderService
import pl.blaszak.remainder.service.RemainderServiceManager
import java.io.FileReader
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Configuration
class RemainderConfiguration {

    var remainderConfigFileName = "remainderConfig.json"
    var name = "name"
    var message = "message"
    var interval = "interval"

    @Bean
    fun executorService(remainderConfigs: JSONArray): ExecutorService {
        return Executors.newFixedThreadPool(remainderConfigs.size)
    }

    @Bean
    fun remainderService(executorService: ExecutorService, remainderConfigs: JSONArray): RemainderServiceManager {
        val remainderServices = remainderConfigs
            .map{ it as JSONObject }
            .map { RemainderService(it.get(name) as String, it.get(message) as String, it.get(interval) as Long) }
        return RemainderServiceManager(executorService, remainderServices)
    }

    @Bean
    fun getJSONConfigArray(): JSONArray {
        val jsonParser = JSONParser()
        val reader = FileReader(remainderConfigFileName)
        return jsonParser.parse(reader) as JSONArray
    }
}