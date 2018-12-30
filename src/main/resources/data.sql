INSERT INTO users (id, first_name, last_name, email, phone, password, created_at, created_by) VALUES (
'5bdc25fcb9a54f4ab4b873357056c8cf',
'John',
'Average',
'john.average@gmail.com',
'0000',
'test',
'2000-01-01 01:01:01',
'5bdc25fcb9a54f4ab4b873357056c8cf');

INSERT INTO user_roles (user_id, roles) VALUES
('5bdc25fcb9a54f4ab4b873357056c8cf', 'MODERATOR'),
('5bdc25fcb9a54f4ab4b873357056c8cf', 'USER');