import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Persona entity.
 */
class PersonaGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "X-XSRF-TOKEN" -> "${xsrf_token}"
    )

    val keycloakHeaders = Map(
        "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
        "Upgrade-Insecure-Requests" -> "1"
    )

    val scn = scenario("Test the Persona entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*);[\\s]").saveAs("xsrf_token"))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authentication")
        .headers(headers_http_authenticated)
        .formParam("username", "admin")
        .formParam("password", "admin")
        .formParam("remember-me", "true")
        .formParam("submit", "Login")
        .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*);[\\s]").saveAs("xsrf_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all personas")
            .get("/api/personas")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new persona")
            .post("/api/personas")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "nombre":"SAMPLE_TEXT"
                , "apellido":"SAMPLE_TEXT"
                , "cuil":"SAMPLE_TEXT"
                , "dni":"0"
                , "legajo":"0"
                , "apodo":"SAMPLE_TEXT"
                , "foto":null
                , "soltero":"SAMPLE_TEXT"
                , "casado":"SAMPLE_TEXT"
                , "conviviente":"SAMPLE_TEXT"
                , "viudo":"SAMPLE_TEXT"
                , "domicilio":"SAMPLE_TEXT"
                , "lugar":"SAMPLE_TEXT"
                , "calle":"SAMPLE_TEXT"
                , "numero":"SAMPLE_TEXT"
                , "telefonofijo":"SAMPLE_TEXT"
                , "numerodecelular":"SAMPLE_TEXT"
                , "oficioprofecion":"SAMPLE_TEXT"
                , "niveldeestudios":"SAMPLE_TEXT"
                , "gruposanguineo":"SAMPLE_TEXT"
                , "factor":"SAMPLE_TEXT"
                , "donante":"SAMPLE_TEXT"
                , "diabetes":"SAMPLE_TEXT"
                , "hipertension":"SAMPLE_TEXT"
                , "alergias":"SAMPLE_TEXT"
                , "asma":"SAMPLE_TEXT"
                , "otros":"SAMPLE_TEXT"
                , "fechadeingreso":"2020-01-01T00:00:00.000Z"
                , "instrumentolegal":"SAMPLE_TEXT"
                , "categoria":"SAMPLE_TEXT"
                , "item":"SAMPLE_TEXT"
                , "planta":"SAMPLE_TEXT"
                , "area":"SAMPLE_TEXT"
                , "direccion":"SAMPLE_TEXT"
                , "annos":"0"
                , "meses":"0"
                , "dias":"0"
                , "realizocomputodeservicios":"SAMPLE_TEXT"
                , "poseeconocimientoenmaquinasviales":"SAMPLE_TEXT"
                , "casoemergenciacelular":"SAMPLE_TEXT"
                , "casoemergenciafijo":"SAMPLE_TEXT"
                , "casoemergencianombre":"SAMPLE_TEXT"
                , "casoemergenciacelular2":"SAMPLE_TEXT"
                , "casoemergenciafijo2":"SAMPLE_TEXT"
                , "casoemergencianombre2":"SAMPLE_TEXT"
                , "familiaracargonombre":"SAMPLE_TEXT"
                , "familiaracargonombre2":"SAMPLE_TEXT"
                , "familiaracargonombre3":"SAMPLE_TEXT"
                , "familiaracargonombre4":"SAMPLE_TEXT"
                , "familiaracargonombre5":"SAMPLE_TEXT"
                , "familiaracargodni":"SAMPLE_TEXT"
                , "familiaracargodni2":"SAMPLE_TEXT"
                , "familiaracargodni3":"SAMPLE_TEXT"
                , "familiaracargodni4":"SAMPLE_TEXT"
                , "familiaracargodni5":"SAMPLE_TEXT"
                , "familiaracargoedad":"SAMPLE_TEXT"
                , "familiaracargoedad2":"SAMPLE_TEXT"
                , "familiaracargoedad3":"SAMPLE_TEXT"
                , "familiaracargoedad4":"SAMPLE_TEXT"
                , "familiaracargoedad5":"SAMPLE_TEXT"
                , "altura":"SAMPLE_TEXT"
                , "barrio":"SAMPLE_TEXT"
                , "estudiosincompletos":"SAMPLE_TEXT"
                , "conyugeapellido":"SAMPLE_TEXT"
                , "conyugenombre":"SAMPLE_TEXT"
                , "conyugedni":"0"
                , "conyugecuil":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre2":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre3":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre4":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre5":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre6":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre7":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre8":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre9":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre10":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombre11":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad2":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad3":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad4":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad5":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad6":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad7":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad8":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad9":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad10":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombreedad11":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombredni":"0"
                , "grupofamiliarapellidonombredni2":"0"
                , "grupofamiliarapellidonombredni3":"0"
                , "grupofamiliarapellidonombredni4":"0"
                , "grupofamiliarapellidonombredni5":"0"
                , "grupofamiliarapellidonombredni6":"0"
                , "grupofamiliarapellidonombredni7":"0"
                , "grupofamiliarapellidonombredni8":"0"
                , "grupofamiliarapellidonombredni9":"0"
                , "grupofamiliarapellidonombredni10":"0"
                , "grupofamiliarapellidonombredni11":"0"
                , "grupofamiliarapellidonombrefamiliar":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar2":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar4":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar3":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar5":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar6":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar7":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar8":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar9":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar10":"SAMPLE_TEXT"
                , "grupofamiliarapellidonombrefamiliar11":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_persona_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created persona")
                .get("${new_persona_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created persona")
            .delete("${new_persona_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
