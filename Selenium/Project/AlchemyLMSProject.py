from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.service import Service as FirefoxService
from selenium.webdriver.support.wait import WebDriverWait
from webdriver_manager.firefox import GeckoDriverManager
from selenium.webdriver.support import expected_conditions as EC
import unittest

# Set up the Firefox Driver with WebDriverManger
service = FirefoxService(GeckoDriverManager().install())

# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Initialize the wait object
    wait = WebDriverWait(driver, 10)
    # Navigate to the URL
    driver.get("https://alchemy.hguy.co/lms")
    #Activity1
    print("Title: ", driver.title)
    assert driver.title == "Alchemy LMS – An LMS Application"
    #driver.quit()

    #Activity2
    heading = driver.find_element(By.TAG_NAME,"h1")
    print("Learn from Industry Experts: ",heading.text)
    assert heading.text == "Learn from Industry Experts"

    #Activity3
    title = driver.find_element(By.XPATH,"//h3[@class='uagb-ifb-title']")
    print("Title of the first info box.: ",title.text)
    assert title.text == "Actionable Training"


    #Activity5
    driver.find_element(By.LINK_TEXT,"My Account").click()
    print("Title: ",driver.title)
    assert driver.title == "My Account – Alchemy LMS"

    #Activity6
    driver.find_element(By.LINK_TEXT,"Login").click()
    driver.find_element(By.ID,"user_login").send_keys("root")
    driver.find_element(By.ID,"user_pass").send_keys("pa$$w0rd")
    driver.find_element(By.ID,"wp-submit").click()
    logoutbutton=driver.find_element(By.LINK_TEXT,"Logout")
    assert logoutbutton.is_displayed()

    #Activity7
    driver.find_element(By.LINK_TEXT,"All Courses").click()
    sTitles=driver.find_elements(By.XPATH,"//h3[@class='entry-title']")
    print(len(sTitles))
    for sTitle in sTitles:
        print(sTitle.text)

    #Activity8
    driver.find_element(By.LINK_TEXT,"Contact").click()
    driver.find_element(By.ID,"wpforms-8-field_0").send_keys("Test Name")
    driver.find_element(By.ID,"wpforms-8-field_1").send_keys("test@gmail.com")
    driver.find_element(By.ID,"wpforms-8-field_3").send_keys("Test_Subject")
    driver.find_element(By.ID,"wpforms-8-field_2").send_keys("Test conntent")
    driver.find_element(By.XPATH,"//button[@name='wpforms[submit]']").click()
    sConfirmtest=driver.find_element(By.XPATH,"//div[@id='wpforms-confirmation-8']").text
    print(sConfirmtest)
    assert sConfirmtest=="Thanks for contacting us! We will be in touch with you shortly."

    #Activity9
    driver.find_element(By.LINK_TEXT,"All Courses").click();
    driver.find_element(By.XPATH,"//a[text()='See more...'][1]").click();
    driver.find_element(By.XPATH,"//div[@class='ld-item-list-item-preview'][1]").click();
    coursetitle = driver.title
    print(coursetitle)
    assert coursetitle=="Developing Strategy – Alchemy LMS"


    if(driver.find_element(By.XPATH("//input[@value='Mark Complete'][1]")).is_displayed()):
        driver.find_element(By.XPATH("//input[@value='Mark Complete'][1]")).click();


    #driver.quit()
