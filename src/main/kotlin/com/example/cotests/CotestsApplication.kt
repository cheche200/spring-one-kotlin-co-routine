package com.example.cotests

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@SpringBootApplication
class CotestsApplication {

	companion object{
	@JvmStatic
	fun main(args: Array<String>) {
			runApplication<CotestsApplication>(*args)
		}
	}

	@Bean
	fun router(service: UserService) = coRouter {
		GET("/all/{id}") {req: ServerRequest ->
			ServerResponse
					.ok()
					.bodyValueAndAwait(service.byId(req.pathVariable("id")));
		}
	}
}

data class User(val id: String, val name:String)

@Service
class UserService() {
	suspend fun byId(id: String) = User("Spring", "SpringOne 2020")
}
