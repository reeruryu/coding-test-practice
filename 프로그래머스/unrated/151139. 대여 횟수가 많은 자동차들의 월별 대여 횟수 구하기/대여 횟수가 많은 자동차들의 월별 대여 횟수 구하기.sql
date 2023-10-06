SELECT MONTH(START_DATE) AS MONTH
, CAR_ID
, COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE CAR_ID IN (
    SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE DATE_FORMAT(START_DATE, '%Y-%m-%d') BETWEEN '2022-08-01' AND '2022-10-31'
    GROUP BY CAR_ID
    HAVING COUNT(*) >= 5
)
AND DATE_FORMAT(START_DATE, '%Y-%m-%d') BETWEEN '2022-08-01' AND '2022-10-31'
GROUP BY MONTH, CAR_ID
ORDER BY MONTH, CAR_ID DESC










# SELECT MONTH(START_DATE) AS MONTH, CAR_ID, COUNT(CAR_ID) AS RECORDS
# FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
# WHERE YEAR(START_DATE) = 2022 AND (MONTH(START_DATE) >= 8 AND MONTH(START_DATE) <= 10)
#     AND CAR_ID IN (
#         SELECT CAR_ID
#         FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
#         WHERE DATE_FORMAT(START_DATE, '%Y-%m') BETWEEN '2022-08' AND '2022-10'
#         GROUP BY CAR_ID
#         HAVING COUNT(CAR_ID) >= 5
#     )
# GROUP BY MONTH(START_DATE), CAR_ID
# HAVING RECORDS >= 1
# ORDER BY MONTH, CAR_ID DESC