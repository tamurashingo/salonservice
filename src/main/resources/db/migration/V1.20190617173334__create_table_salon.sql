create table salon (
  id INT NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  valid_flag CHAR(1) NOT NULL,
  created_by INT NOT NULL,
  created_date DATETIME NOT NULL,
  updated_date DATETIME NOT NULL,

  FOREIGN KEY fk_created_by (created_by) REFERENCES user(id)
);



