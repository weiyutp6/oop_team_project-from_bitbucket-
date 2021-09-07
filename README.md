# chosen topic: ticket system #

This README would normally document whatever steps are necessary to get your application up and running.

### Plan ###

##### week 1(4/29-5/6) #####

* 林和謙:空的app介面
* 廖威騏:資料庫IO程式
* 陳威宇:連通資料庫method
* 梁瑞翔:判斷查詢以及預訂的演算法

### Usage ###

* 訂票系統

#### Structure ####

* 資料庫
* 演算程式
* GUI

### Installation ###

#### java.sql ####

#### mssql ####

#### java swing ####

* https://www.formdev.com/jformdesigner/download/

### guidelines ###

* 這邊寫你程式的I/O哦~
* 格式: input:<class name>{<parameters>} output:<class name>{<parameters>}

# self picked topic: stock crawling app #
*(optional)
### Usage ###

* crawl Taiwan stock exchange and bloomberg for US stock exchange
* use US stock market to alert users in the Taiwan stock market in advance

#### goals ####
* User can set a watch list to watch certain stock (使用者可以列股票觀察名單)
* follows the prices of the stock market (app會追蹤股市指數與股價)
* could alert users about macroscopic change in the stock market that would affect the stock (當有巨觀的改變時可以事先通知使用者其股票預期受的影響)
* general advice about when to buy or sell a certain stock (對於買賣給予大略建議)
* *estimates the income of a stock with complex interest
#### Structure ####

* Mediator (收取input(watch list)並且給與insider做爬蟲)
* Insider (爬蟲軟體，接收mediator input後回傳股票訊息給receiver)
* Reciever (接收insider output後把所需分析資料回傳給compiler)
* Compiler (接收Reiever output後經過演算進行分析)
* Interface (app使用者介面)
* *possible finance manager

### Installation ###

#### jsoup ####

* Download package:https://jsoup.org/packages/jsoup-1.13.1.jar
* Add to external library

#### certificate verification ####

* see oop_team_project/selftopic/certificateSOP.pdf
* 我在 oop_team_project/selftopic/有放我用的.cer檔 直接用的話從certificateSOP.pdf第六頁開始就好

### guidelines ###

### contributers ###
  
- [Ricky Liao / 廖威騏]()
- [Jonathan Lin / 林和謙]()
- [William Chen / 陳威宇](https://github.com/weiyutp6)
- [Rui Xiang Liang / 梁瑞翔]()

### references ###
* Ke, C.-H. (2019, December 8). 每日台股大盤收盤指數 - Java 實作.Retrieved from https://medium.com/phelps-laboratory/stockvaluebydate-30c28bcf9a0f
* https://docs.microsoft.com/en-us/bingmaps/articles/ssl-certificate-validation-for-java-applications
* https://www.lightblue.asia/realtime-tw-stockprice-in-google-spreadsheet/
