import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ServiceSimulation extends Simulation {

  private val url = "http://localhost:8080"
  private val header = "application/json"

  val httpProtocol = http
    .baseUrl(url)
    .acceptHeader(header)

  private val scenarioName = "Test POST for /publish"
  private val requestName = "post_for_publish_request_1"
  private val endpoint = "/publish"
  private val jsonPayload = "payload.json"

  val scn = scenario(scenarioName)
    .exec(http(requestName)
      .post(endpoint)
      .body(RawFileBody(jsonPayload))
      .check(status is 200)

    )

  setUp(scn.inject(
    atOnceUsers(1000)
  ).protocols(httpProtocol))
}