import org.junit.Test;
import org.junit.runner.RunWith;

import models.users.Datum;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import questions.GetUsersQuestion;
import questions.ResponseCode;
import tasks.GetUsers;

import static org.assertj.core.api.Assertions.*;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.*;

@SuppressWarnings("unused")
@RunWith(SerenityRunner.class)
public class SerenityInitialTests {

	private static final String restApiUrl = "http://localhost:5000/api";

	@Test
	public void getUsers() {

		Actor julian = Actor.named("Julian the trainer").whoCan(CallAnApi.at(restApiUrl));

		julian.attemptsTo(GetUsers.fromPage(1));

//		assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);

		julian.should(seeThat("el codigo de respuesta", ResponseCode.was(), equalTo(200)));

		Datum user = new GetUsersQuestion().answeredBy(julian).getData().stream().filter(x -> x.getId() == 1)
				.findFirst().orElse(null);
		
		julian.should(seeThat("Usuario no es nuelo", act -> user, notNullValue()));
		
		julian.should(seeThat("El nombre del usuario es ", act -> user.getName(), equalTo("cerulean")),
				seeThat("El año del usuario es ", act -> user.getYear(), equalTo("2000")));
	}

}
