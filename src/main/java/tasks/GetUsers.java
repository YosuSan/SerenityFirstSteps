package tasks;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;;

@SuppressWarnings("unused")
public class GetUsers implements Task{
	
	private final int page;
	
	public GetUsers(int page) {
		this.page = page;
	}
	
	public static Performable fromPage(int page) {
		return instrumented(GetUsers.class, page);
	}
	
	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Get.resource("/user?page=" + page).
				with(requestSpecification -> requestSpecification.contentType(ContentType.JSON).
						header("header1", "value1")));
		
	}

}
