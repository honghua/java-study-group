package shared;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Guice;
import com.google.inject.Module;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class GuiceRule implements MethodRule {
  private final Module module;

  public GuiceRule(Module module) {
    this.module = checkNotNull(module);
  }

  @Override
  public Statement apply(final Statement statement,
      FrameworkMethod frameworkMethod, final Object target) {
    return new Statement() {
      @Override public void evaluate() throws Throwable {
        Guice.createInjector(module).injectMembers(target);
        statement.evaluate();
      }
    };
  }
}