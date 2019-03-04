ALTER TABLE `parking_management`.`parking_spots` 
ADD COLUMN `Spot_UID` INT NULL AUTO_INCREMENT AFTER `PermitType`,
ADD UNIQUE INDEX `Spot_UID_UNIQUE` (`Spot_UID` ASC) VISIBLE;
;


CREATE TABLE `parking_management`.`reservations` (
  `Reservation_Id` INT NOT NULL AUTO_INCREMENT,
  `Spot_UID` INT NOT NULL,
  `Start_Time` DATETIME NOT NULL,
  `End_Time` DATETIME NOT NULL,
  `NoShow` TINYINT NULL DEFAULT 0,
  `OverStay` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`Reservation_Id`, `Spot_UID`, `End_Time`, `Start_Time`),
  INDEX `Fk_Spot_UID_idx` (`Spot_UID` ASC) VISIBLE,
  CONSTRAINT `Fk_Spot_UID`
    FOREIGN KEY (`Spot_UID`)
    REFERENCES `parking_management`.`parking_spots` (`Spot_UID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
	
	ALTER TABLE `parking_management`.`reservations` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`Spot_UID`, `End_Time`, `Start_Time`),
ADD UNIQUE INDEX `Reservation_Id_UNIQUE` (`Reservation_Id` ASC) VISIBLE;
;