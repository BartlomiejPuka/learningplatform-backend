INSERT INTO users (created_at, username, password, email, first_name, last_name, enabled)
VALUES
    (now(), 'testUser', 'testHash', 'test@gmail.com', 'testFirstName', 'testLastName', 1)
;