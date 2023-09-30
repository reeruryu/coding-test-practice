SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, 
(CASE
 WHEN STATUS = 'SALE' THEN '판매중'
 WHEN STATUS = 'RESERVED' THEN '예약중'
 WHEN STATUS = 'DONE' THEN '거래완료'
 END
) AS STATUS
FROM USED_GOODS_BOARD
WHERE CREATED_DATE = '2022-10-05'
ORDER BY BOARD_ID DESC

# SELECT board_id,writer_id,title, price ,CASE WHEN status = 'SALE' THEN '판매중'
# WHEN status = 'RESERVED' THEN '예약중' 
# WHEN status = 'DONE' THEN '거래완료' END status
# FROM used_goods_board
# WHERE board_id IN(SELECT board_id FROM used_goods_board WHERE created_date = '2022-10-05')
# ORDER BY board_id DESC