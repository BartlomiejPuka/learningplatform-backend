INSERT INTO
    platform_users(created_at,email,is_enabled, is_non_locked, first_name,last_name,password,username)
VALUES
    (NOW(), 'test@gmail.com', 1, 1, 'Bartłomiej', 'Puka', '$2a$10$kv2tZn8h8qx0yWaJAmkHvOBUPntiekXB9vy.wU5cOnvPAiYOgG9W2', 'test')
;