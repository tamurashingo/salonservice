create table user (
  user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_email VARCHAR(255) NOT NULL,
  user_name VARCHAR(255),
  password VARCHAR(255),
  user_status CHAR(1) NOT NULL,
  created_date DATETIME NOT NULL,
  updated_date DATETIME NOT NULL
);
