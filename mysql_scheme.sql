CREATE SCHEMA IF NOT EXISTS shopping;

create user 'shopping'@'%' identified by 'shopping';
grant all on shopping.* to 'shopping'@'%';

USE shopping;

CREATE TABLE shopping_card (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY  NOT NULL,
  username        VARCHAR(45)  NOT NULL,
  create_date      DATE NOT NULL
);

CREATE TABLE shopping_card_item (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY  NOT NULL,
  shopping_card_id  BIGINT NOT NULL,
  book_id          BIGINT NOT NULL,
  quantity        BIGINT NOT NULL,
  create_date      DATE NOT NULL,
  FOREIGN KEY (shoppingCardId) REFERENCES shopping_card(id)
);