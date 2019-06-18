create table user (
  id INT NOT NULL PRIMARY KEY,
  mail_address VARCHAR(255) NOT NULL,
  name VARCHAR(255),
  password VARCHAR(255),
  valid_flag CHAR(1) NOT NULL,
  created_date DATETIME NOT NULL,
  updated_date DATETIME NOT NULL
);
