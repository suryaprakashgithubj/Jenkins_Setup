package listeners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * RetryListener — Q5: Retry Failed Tests
 *
 * FIXED: Must implement BOTH IRetryAnalyzer AND IAnnotationTransformer.
 * Wiring as <listener> in testng.xml only works when IAnnotationTransformer
 * is implemented — it injects the retryAnalyzer into every @Test at runtime.
 *
 * Without IAnnotationTransformer, testng.xml <listener> does NOT attach
 * IRetryAnalyzer to tests — they would never be retried.
 */
public class RetryListener implements IRetryAnalyzer, IAnnotationTransformer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 1; // retry once on failure

    // ── IRetryAnalyzer ──────────────────────────────────────────────────────
    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCount < MAX_RETRY) {
            retryCount++;
            System.out.println("[RetryListener] Retrying: "
                    + result.getName() + " | Attempt #" + retryCount);
            return true;
        }
        return false;
    }

    // ── IAnnotationTransformer ───────────────────────────────────────────────
    // FIXED: This injects retryAnalyzer=RetryListener.class into every @Test
    // so that testng.xml <listener> declaration actually takes effect.
    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          Constructor testConstructor,
                          Method testMethod) {
        annotation.setRetryAnalyzer(RetryListener.class);
    }
}
