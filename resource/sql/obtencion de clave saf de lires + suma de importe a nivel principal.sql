SELECT * FROM empresa 
LEFT JOIN (SELECT clave_saf, programa,subPrograma,proyecto,actividad,obra,fuente,inciso,principal,parcial,subParcial, SUM(importe)
FROM liresto l 
GROUP BY clave_saf, programa,subPrograma,proyecto,actividad,obra,fuente,inciso,principal,parcial) l
WHERE empresa.clave_seg = l.clave_saf