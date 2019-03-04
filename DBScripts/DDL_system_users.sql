Create database `parking_management`;

CREATE TABLE IF NOT EXISTS `parking_management`.`system_users` (
  `User_Id` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(10) NOT NULL,
  `HashedPassword` CHAR(128) NOT NULL,
  `Role` VARCHAR(10) NOT NULL,
  `IsRevoked` TINYINT NOT NULL DEFAULT 0,
  `PermitType` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`User_Id`),
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC));
  
  CREATE TABLE IF NOT EXISTS `parking_management`.`user_details` (
  `User_Id` INT NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `MiddleName` VARCHAR(45) NULL,
  `LastName` VARCHAR(45) NULL,
  `Sex` VARCHAR(45) NULL,
  `DOB` DATE NULL,
  `Address` VARCHAR(200) NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` INT(10) NOT NULL,
  `DL_Number` INT(8) NOT NULL,
  `DL_Expiry` DATE NOT NULL,
  `Reg_Number` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`User_Id`),
  CONSTRAINT `User_Id_FK`
    FOREIGN KEY (`User_Id`)
    REFERENCES `parking_management`.`system_users` (`User_Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
	
	

CREATE TABLE IF NOT EXISTS `parking_management`.`parking_area` (
  `Area_Id` INT NOT NULL AUTO_INCREMENT,
  `Area_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Area_Id`),
  UNIQUE INDEX `Area_Name_UNIQUE` (`Area_Name` ASC));
  
  CREATE TABLE IF NOT EXISTS `parking_management`.`parking_area_floors` (
  `Area_Id` INT NOT NULL,
  `Floor_Number` INT NOT NULL,
  `PermitType` VARCHAR(10) NOT NULL,
  `No_Spots` INT(3) NOT NULL,
  PRIMARY KEY (`Area_Id`, `Floor_Number`, `PermitType`),
  CONSTRAINT `parking_area_floor_FK`
    FOREIGN KEY (`Area_Id`)
    REFERENCES `parking_management`.`parking_area` (`Area_Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
	
ALTER TABLE `parking_management`.`parking_area_floors` 
ADD INDEX `floor_index` (`Floor_Number` ASC);
;

ALTER TABLE `parking_management`.`parking_area_floors` 
ADD INDEX `permit_index` (`PermitType` ASC);
;

CREATE TABLE IF NOT EXISTS `parking_management`.`parking_spots` (
  `Area_Id` INT NOT NULL,
  `Floor_Number` INT NOT NULL,
  `Spot_Id` INT NOT NULL,
  PRIMARY KEY (`Area_Id`, `Floor_Number`, `Spot_Id`),
  INDEX `parking_slot_floor_FK_idx` (`Floor_Number` ASC),
  CONSTRAINT `parking_slot_area_FK`
    FOREIGN KEY (`Area_Id`)
    REFERENCES `parking_management`.`parking_area` (`Area_Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `parking_slot_floor_FK`
    FOREIGN KEY (`Floor_Number`)
    REFERENCES `parking_management`.`parking_area_floors` (`Floor_Number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
	
ALTER TABLE `parking_management`.`parking_spots` 
ADD COLUMN `IsBlocked` TINYINT NULL DEFAULT 0 AFTER `Spot_Id`;

ALTER TABLE `parking_management`.`parking_spots` 
ADD COLUMN `PermitType` VARCHAR(10) NOT NULL AFTER `IsBlocked`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`Area_Id`, `Floor_Number`, `Spot_Id`, `PermitType`),
ADD INDEX `parking_slot_permit_FK_idx` (`PermitType` ASC);
;

ALTER TABLE `parking_management`.`parking_spots` 
ADD CONSTRAINT `parking_permit_type_FK`
  FOREIGN KEY (`PermitType`)
  REFERENCES `parking_management`.`parking_area_floors` (`PermitType`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `parking_management`.`system_users` modify Role varchar(20);
ALTER TABLE `parking_management`.`system_users` modify PermitType varchar(20);
ALTER TABLE `parking_management`.`user_details` add uta_Id varchar(20);

ALTER TABLE `parking_management`.`user_details` modify Phone varchar(25);
ALTER TABLE `parking_management`.`user_details` modify DL_Number varchar(25);