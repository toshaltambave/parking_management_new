SELECT ps.Spot_Id AS Spot_Id from parking_spots AS ps
where ps.Spot_Id NOT IN( SELECT psn.Spot_Id AS Spot_Id
FROM parking_spots AS psn 
JOIN reservations res ON psn.Spot_UID = res.Spot_UID AND res.Start_Time >= '2019-02-03 10:00:00' AND res.End_Time <= '2019-02-03 11:00:00'
where psn.Area_Id = '1' AND psn.Floor_Number = '3' AND psn.PermitType ='Midrange')
AND ps.Area_Id = '1' AND ps.Floor_Number = '2' AND ps.PermitType ='Midrange';

