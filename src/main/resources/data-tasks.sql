
INSERT INTO
    tasks(title, description, seid, order_id, course_id)
VALUES
    ('Print hello world', 'Napisz program wypisujacy napis hello world.', 'tD2Ln2MoPz', 1, 12),
    ('Fizz buzz', 'Rozwiaz zadanie fizz buzz.', 'tD2Ln2MoPz', 2, 12),
    ('Parzyste liczby', 'Wypisz parzyste liczby od 0 do 10.', 'tD2Ln2MoPz', 3, 12);



INSERT INTO
    courses_tasks(course_id, task_id)
VALUES
    (12, 1),
    (12, 2),
    (12, 3);