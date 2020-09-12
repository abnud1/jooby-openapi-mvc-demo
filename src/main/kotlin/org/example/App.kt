package org.example

import io.jooby.Kooby
import io.jooby.OpenAPIModule
import io.jooby.annotations.GET
import io.jooby.annotations.Path
import io.jooby.runApp

@Path("/")
class Hello{
    @GET
    fun hello(): String{
        return "Hello World"
    }
}
@Path("/hi")
class Hi{
    @GET
    fun hi(): String{
        return "Hi World"
    }
}
class App : Kooby({
    install(OpenAPIModule())
    mvc(Hi())
    routes {
        before {
            // do something that is specific to Hello controller
        }
        mvc(Hello())
    }
})
fun main(args: Array<String>){
    runApp(args,App::class)
}