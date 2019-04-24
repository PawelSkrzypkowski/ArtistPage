SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+01:00";

INSERT INTO artist_shop.role (id, name, active) VALUES
(1, 'ROLE_ADMIN', true),
(2, 'ROLE_CUSTOMER', true);


INSERT INTO artist_shop.user (id, name, email, password, active) VALUES
(10000, 'Kinga', 'kinga@kingamaziuk.com', '$2a$10$g06pw7roYx1ffMpIpgZM0eGfs8gFdoT9z.vrvogq2lTBrja3J0KIy', true);

INSERT INTO artist_shop.user_role (id, user_id, role_id, active) VALUES
(10000, 10000, 1, true),
(10001, 10000, 2, true);