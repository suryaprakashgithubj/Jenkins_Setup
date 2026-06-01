//package pages;
//
//import base.DriverFactory;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import utilities.PopupHandler;
//import utilities.WaitUtils;
//
///**
// * LogoutPage — Fixed XPath for PHPTravels navigation bar.
// *
// * ROOT CAUSE OF FAILURE:
// * After login, PHPTravels shows a nav dropdown with the user's name.
// * The profile trigger is an <li> or <a> with class "dropdown" in the top nav.
// * The previous XPath searched for 'Account', 'avatar', 'user-menu' —
// * none of which match the actual rendered HTML.
// *
// * FIX: Use the actual PHPTravels nav structure:
// *   - Profile trigger: <li class="dropdown"> containing username or user icon
// *     OR the My Account / username link directly
// *   - Logout link: <a href="...logout..."> inside the dropdown
// */
//public class LogoutPage {
//
//    private final WebDriver driver;
//    private final WaitUtils wait;
//
//    // FIXED: PHPTravels top-nav after login shows username in a dropdown <li>
//    // Multiple fallback strategies ordered from most to least specific
//    private final By profileBtn = By.xpath(
//        // Strategy 1: The dropdown toggle li/a that contains "Hi," or username area
//        "//li[contains(@class,'dropdown')]//a[contains(@class,'dropdown-toggle')]"
//        // Strategy 2: Any nav link pointing to /account or /dashboard
//        + " | //nav//a[contains(@href,'/account') or contains(@href,'/dashboard')]"
//        // Strategy 3: Nav item with user/person icon (font-awesome or similar)
//        + " | //li[contains(@class,'dropdown')][.//i[contains(@class,'fa-user') or contains(@class,'user')]]"
//        // Strategy 4: The username text link directly
//        + " | //a[contains(@href,'account') and ancestor::nav]"
//        // Strategy 5: Any element with 'Hi' greeting text (common in travel sites)
//        + " | //*[starts-with(normalize-space(.),'Hi') and ancestor::nav]"
//    );
//
//    // Logout: PHPTravels uses /logout route
//    private final By logoutLink = By.xpath(
//        "//a[contains(@href,'logout') or contains(@href,'signout') or contains(@href,'sign-out')]"
//        + " | //a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'logout')]"
//        + " | //a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign out')]"
//    );
//
//    public LogoutPage() {
//        this.driver = DriverFactory.getDriver();
//        this.wait   = new WaitUtils(driver);
//        PopupHandler.dismissIfPresent();
//    }
//
//    public void clickProfile() {
//        // Scroll to top first — nav bar might be hidden if page scrolled down
//        org.openqa.selenium.JavascriptExecutor js =
//            (org.openqa.selenium.JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, 0);");
//        PopupHandler.jsClick(wait.waitForClickable(profileBtn));
//    }
//
//    public void clickLogout() {
//        PopupHandler.jsClick(wait.waitForClickable(logoutLink));
//    }
//}
package pages;

import org.openqa.selenium.By;

public class LogoutPage {

    // Profile Dropdown
    public By profileDropdown =
            By.xpath("/html/body/header/div[1]/div/div[2]/div[3]/button/span[2]");

    // Logout Button
    public By logoutButton =
            By.xpath("/html/body/header/div[1]/div/div[2]/div[3]/div[2]/div[2]/a[4]");
}