create table user_salon (
  user_salon_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  salon_id INT NOT NULL,
  user_status VARCHAR(255) NOT NULL,
  created_date DATETIME NOT NULL,
  updated_date DATETIME NOT NULL,

  FOREIGN KEY fk_user_id (user_id) REFERENCES user(user_id),
  FOREIGN KEY fk_salon_id (salon_id) REFERENCES salon(salon_id)
);

