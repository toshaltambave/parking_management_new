SELECT paf.Area_Id AS AreaID,paf.Floor_Number AS FloorNumber ,paf.PermitType AS PermitType,(paf.No_Spots-Count(res.Spot_UID)) AS AvailableSpots
FROM parking_area_floors AS paf
LEFT JOIN parking_spots AS ps ON paf.Area_Id = ps.Area_Id and ps.Floor_Number = paf.Floor_number and paf.PermitType = ps.PermitType
LEFT JOIN reservations res ON ps.Spot_UID = res.Spot_UID AND res.Start_Time >= '2019-02-03 10:00:00' AND res.End_Time <= '2019-02-03 11:00:00'
where paf.Area_Id = '1'
GROUP BY paf.Area_Id,paf.Floor_Number,paf.PermitType





