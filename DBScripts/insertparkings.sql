INSERT INTO `parking_area` VALUES (1,'West Garage'),(2,'Maverick'),(3,'Davis'),(4,'Nedderman');

INSERT INTO `parking_area_floors` VALUES (1,5,'Basic',250),(1,2,'Midrange',250),(1,3,'Midrange',250),
(1,4,'Midrange',250),(1,1,'Premium',230),(1,1,'Access',20)
,(2,1,'Basic',200),(2,1,'Access',20),(3,1,'Basic',150),(3,1,'Access',20),(4,1,'Basic',180),(4,1,'Access',20);

INSERT INTO `parking_spots` 
VALUES (1,1,1,0,'Access'),(1,1,2,0,'Access'),(1,1,1,0,'Premium'),(1,1,2,0,'Premium'),(1,5,1,0,'Basic'),(1,5,2,0,'Basic'),
(1,2,1,0,'Midrange'),(1,2,2,0,'Midrange'),(1,3,1,0,'Midrange'),(1,3,2,0,'Midrange'),(1,4,1,0,'Midrange'),(1,4,2,0,'Midrange'),
(2,1,1,0,'Basic'),(2,1,2,0,'Basic'),(2,1,1,0,'Access'),(2,1,2,0,'Access'),
(3,1,1,0,'Basic'),(3,1,2,0,'Basic'),(3,1,1,0,'Access'),(3,1,2,0,'Access'),
(4,1,1,0,'Basic'),(4,1,2,0,'Basic'),(4,1,1,0,'Access'),(4,1,2,0,'Access');
