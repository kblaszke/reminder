package pl.blaszak.remainder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import pl.blaszak.remainder.service.RemainderServiceManager

@SpringBootApplication
class RemainderApplication

fun main(args: Array<String>) {
	val applicationBuilder = SpringApplicationBuilder(RemainderApplication::class.java)
	applicationBuilder.headless(false)
	val context = applicationBuilder.run(*args)
	val remindServicesManager = context.getBean(RemainderServiceManager::class.java)
	remindServicesManager.start()
}
