DELETE FROM user;

INSERT INTO user (id, name, age, email, deleted, version, create_time, update_time) VALUES
  (1, 'Jone', 18, 'test1@baomidou.com', '0', 0, '2020-03-10 09:00:00', '2020-03-10 09:00:00'),
  (2, 'Jack', 20, 'test2@baomidou.com', '0', 0, '2020-03-10 09:00:00', '2020-03-10 09:00:00'),
  (3, 'Tom', 28, 'test3@baomidou.com', '0', 0, '2020-03-10 09:00:00', '2020-03-10 09:00:00'),
  (4, 'Sandy', 21, 'test4@baomidou.com', '0', 0, '2020-03-10 09:00:00', '2020-03-10 09:00:00'),
  (5, 'Billie', 24, 'test5@baomidou.com', '1', 0, '2020-03-10 09:00:00', '2020-03-10 09:00:00');