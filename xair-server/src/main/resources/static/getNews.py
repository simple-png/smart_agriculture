import requests
from bs4 import BeautifulSoup

# 发起HTTP请求，获取网页内容
url = 'http://www.agri.cn'
url_list = []
response = requests.get(url)
response.encoding = 'utf-8'
html_content = response.text

# 使用BeautifulSoup解析网页内容
soup = BeautifulSoup(html_content, 'html.parser')
# 查找指定id的div内容
div_element = soup.find("div", id="TagContent1_3")
# 查找div下所有a标签的内容
a_tags = div_element.find_all("a")
for a in a_tags:
    relative_path = a.get('href').lstrip(".")
    content = a.get('title')
    if relative_path[-3:] == "htm":
        map = {}
        full_path = url + relative_path
        map[content] = full_path
        url_list.append(map)
print(url_list)
