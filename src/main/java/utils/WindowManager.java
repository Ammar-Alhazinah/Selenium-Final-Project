package utils;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class WindowManager {

    private WebDriver driver;
    private WebDriver.Navigation navigate;

    public WindowManager(WebDriver driver){
        this.driver = driver;
        navigate = driver.navigate();
    }

    public void goBack(){
        navigate.back();
    }

    public void goForward(){
        navigate.forward();
    }

    public void refreshPage(){
        navigate.refresh();
    }

    public void goTo(String url){
        navigate.to(url);
    }


    public void maximize(){ driver.manage().window().maximize();}

    public void switchToTab(){
        Set windows = driver.getWindowHandles();

        // window handles iterate
        Iterator i = windows.iterator();
        String popwnd = String.valueOf(i.next());
        // switching pop up tab
        driver.switchTo().window(popwnd);
    }
   /* public void switchToTab(String tabTitle){
        var windows = driver.getWindowHandles();

        System.out.println("Number of tabs: " + windows.size());

        System.out.println("Window handles:");
        windows.forEach(System.out::println);

        for(String window : windows){
            System.out.println("Switching to window: " + window);
            driver.switchTo().window(window);

            System.out.println("Current window title: " + driver.getTitle());

            if(tabTitle.equals(driver.getTitle())){
                break;
            }
        }
    }*/
}
