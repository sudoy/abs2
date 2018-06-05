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



CREATE TABLE categorylist(
	category_id INT PRIMARY KEY,
	category_name VARCHAR(100) NOT NULL
);

INSERT INTO categorylist
VALUES (1, '食費');
INSERT INTO categorylist
VALUES (2, '日用品');
INSERT INTO categorylist
VALUES (3, '交際費');
INSERT INTO categorylist
VALUES (4, 'アルバイト代');
INSERT INTO categorylist
VALUES (5, 'その他');




