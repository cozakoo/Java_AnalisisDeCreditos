SELECT e.clave_saf, programa,subPrograma,proyecto,actividad,obra,fuente,inciso,principal,parcial,subParcial, SUM( importe ) 
FROM liresto l
LEFT JOIN empresa e
on l.clave_saf = e.clave_seg  
WHERE l.clave_saf = "COT"
GROUP BY e.clave_saf, programa,subPrograma,proyecto,actividad,obra,fuente,inciso,principal,parcial,subParcial
