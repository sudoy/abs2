CREATE database abs2;

use abs2;

CREATE TABLE myhab(
	id INT PRIMARY KEY AUTO_INCREMENT,
	dating DATE NOT NULL,
	in_out INT,
	category INT NOT NULL,
	memo TEXT,
	money INT NOT NULL
);

INSERT INTO myhab(dating, in_out, category, memo, money)
VALUES ('2018-06-10', 1, 1, '日用品', -600);
INSERT INTO myhab(dating, in_out, category, money)
VALUES ('2018-06-11', 2, 2, 3000);
INSERT INTO myhab(dating, in_out, category, memo, money)
VALUES ('2018-06-12', 1, 3, '交通費', -360);