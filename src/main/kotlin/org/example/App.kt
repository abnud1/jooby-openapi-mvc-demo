package org.example

import io.jooby.Kooby
import io.jooby.OpenAPIModule
import io.jooby.annotations.GET
import io.jooby.annotations.Path
import io.jooby.runApp

@Path("/")
object Hello{
    @GET
    fun hello(): String{
        return "Hello World"
    }
}

class App : Kooby({
    install(OpenAPIModule())
    mvc(Hello)
})
fun main(args: Array<String>){
    runApp(args,App::class)
}