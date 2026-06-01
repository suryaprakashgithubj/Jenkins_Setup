package utilities;

/**
 * ══════════════════════════════════════════════════════════════
 *  Q20 — Debugging Round: All 4 Bugs Found and Fixed
 * ══════════════════════════════════════════════════════════════
 *
 * ─────────────────────────────────────────────────────────────
 * PROBLEM 1 — Missing parentheses on method call
 * ─────────────────────────────────────────────────────────────
 * BUGGY:
 *   driver.findElement(By.id("login")).click;
 *
 * BUG:  `.click` is a field reference, NOT a method call.
 *       In Java, methods must be called with ()
 *       This compiles but silently does NOTHING — no click happens.
 *
 * FIXED:
 *   driver.findElement(By.id("login")).click();
 *
 *
 * ─────────────────────────────────────────────────────────────
 * PROBLEM 2 — Assert arguments are in wrong order
 * ─────────────────────────────────────────────────────────────
 * BUGGY:
 *   Assert.assertEquals(true, false);
 *
 * BUG:  TestNG assertEquals signature is assertEquals(actual, expected).
 *       Passing (true, false) will ALWAYS fail — it compares true to false.
 *       Even if the intent is to assert something is true, this is wrong.
 *
 * FIXED (option A — assert a condition is true):
 *   Assert.assertTrue(someCondition, "Condition should be true");
 *
 * FIXED (option B — compare two values, correct order):
 *   Assert.assertEquals(actualValue, expectedValue, "Mismatch message");
 *
 *
 * ─────────────────────────────────────────────────────────────
 * PROBLEM 3 — Cucumber Scenario keyword missing colon
 * ─────────────────────────────────────────────────────────────
 * BUGGY:
 *   Scenario Login Validation
 *
 * BUG:  Gherkin syntax requires a colon after the keyword.
 *       Without it, Cucumber parser throws:
 *       "expected: #EOF, #Comment, #BackgroundLine, #TagLine,
 *        #ScenarioLine, #RuleLine, #Empty, got 'Scenario Login Validation'"
 *
 * FIXED:
 *   Scenario: Login Validation
 *
 *
 * ─────────────────────────────────────────────────────────────
 * PROBLEM 4 — WebDriverWait constructor changed in Selenium 4
 * ─────────────────────────────────────────────────────────────
 * BUGGY (Selenium 3 style):
 *   WebDriverWait wait = new WebDriverWait(driver, 10);
 *
 * BUG:  In Selenium 4, the constructor that accepts a plain `long` seconds
 *       parameter was REMOVED. Using it causes a compilation error:
 *       "no suitable constructor found for WebDriverWait(WebDriver, int)"
 *
 * FIXED (Selenium 4 style — use Duration):
 *   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 *
 *       import java.time.Duration;  ← also required
 *
 * ══════════════════════════════════════════════════════════════
 */
public class Q20_DebuggingFixes {
    // This class is documentation only — see comments above.
}
