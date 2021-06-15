from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup

url = r'https://jwc.cuit.edu.cn/'
driver_path = r'D:\workspace\project\exam\exam2.0\src\main\resources\driver\chromedriver.exe'

chrome_options = Options()
chrome_options.add_argument('--disable-gpu')
chrome_options.add_argument('--headless')

driver = webdriver.Chrome(executable_path=driver_path, options=chrome_options)

driver.get(url)
driver.implicitly_wait(1)

soup = BeautifulSoup(driver.page_source, 'html')
week = soup.find('label', id='xxxcon')
print(week.get_text())

driver.close()

