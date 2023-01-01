package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                  "html:target/default-html-reports",
        "pretty"},
        features = "src/test/resources/features",
        glue = "com/Yolcu360_Login/step_definitions",
        dryRun =false,
        tags="@deneme"
)
public class TestRunner {
}
