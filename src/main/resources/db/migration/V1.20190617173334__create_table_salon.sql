create table salon (
  salon_id INT NOT NULL PRIMARY KEY,
  salon_name VARCHAR(255) NOT NULL,
  description TEXT,
  salon_status CHAR(1) NOT NULL,
  created_by INT NOT NULL,
  created_date DATETIME NOT NULL,
  updated_date DATETIME NOT NULL,

  FOREIGN KEY fk_created_by (created_by) REFERENCES user(user_id)
);



