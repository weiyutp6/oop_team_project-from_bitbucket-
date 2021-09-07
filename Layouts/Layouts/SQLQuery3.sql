/** 宣告使用的資料庫 **/
use [Travel];

select * from [dbo].[trip_data]

/** 查詢可報名行程 **/
DECLARE 
  @travel_code int,
  @start_date datetime,
  @end_date datetime

set @travel_code='430'
set @start_date='2020-05-07 00:00:00.000'
set @end_date='2020-10-18 00:00:00.000'

select b.travel_code,b.travel_code_name,a.title,a.price,a.lower_bound,a.upper_bound,a.start_date,a.end_date from trip_data as a
left join travel_code as b on a.travel_code = b.travel_code
where a.travel_code = b.travel_code and
a.travel_code = @travel_code and
a.start_date >= @start_date and a.end_date <= @end_date 
order by start_date -- 排序   
-- 降排 order by start_date desc 
-- 依price排序 order by price 



/** 查詢訂單 **/
DECLARE 
  @member_id nvarchar(50),
  @pwd nvarchar(20),
  @order_no nchar(10)

set @member_id='James'
set @pwd='james1234'
set @order_no='2020041701'

select a.member_id, a.member_id, b.pwd, b.member_name, a.product_code,c.title,c.product_key, c.start_date, c.end_date, a.adults_qty,a.kids_qty, a.babys_qty, a.total_price from order_data as a
left join member_data as b on a.member_id = b.member_id
left join trip_data as c on a.product_code = c.product_code
where a.member_id = @member_id and b.pwd = @pwd and a.order_no = @order_no 