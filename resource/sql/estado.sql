SELECT 'cr' estado, c.saf, c.programa,c.subPrograma,c.proyecto,
c.actividad,c.obra,c.fuente,c.inciso,SUM(c.credito_disponible),SUM (coalesce(j.importe,0)),
SUM(c.credito_disponible)- SUM (coalesce(j.importe, 0)) saldo
FROM credito c
LEFT JOIN
(SELECT e.saf, programa,subPrograma,proyecto,actividad,obra
,fuente,inciso,principal,parcial,subParcial, importe
FROM liresto l
LEFT JOIN empresa e
on l.clave_saf = e.clave_saf) j
on c.saf = j.saf AND c.programa = j.programa
   AND c.subPrograma = j.subPrograma AND c.proyecto = j.proyecto
   AND c.actividad = j.actividad AND c.obra = j.obra 
   AND c.fuente = j.fuente AND c.inciso = j.inciso
   AND c.principal = j.principal AND c.parcial = j.parcial
   AND c.subParcial = j.subParcial
 
WHERE c.saf >= 1 AND c.fuente = 311
GROUP BY c.saf, c.programa,c.subPrograma,c.proyecto,
c.actividad,c.obra,c.fuente,c.inciso
HAVING
SUM(c.credito_disponible)- SUM (coalesce(j.importe, 0)) 