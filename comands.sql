SELECT 
         u.ID,
         u.NAME,
         u.EMAIL,
         o.ID_CLIENT,
         o.MOMENT
FROM 
         TB_USER u
JOIN 
          TB_ORDER o
ON u.id = o.ID_CLIENT
ORDER BY u.ID;


