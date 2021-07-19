
INSERT INTO
    tasks(title, description, seid, order_id, course_id)
VALUES
    ('Print hello world', 'Napisz program wypisujacy napis hello world.', 'tD2Ln2MoPz', 1, 3),
    ('Fizz buzz', 'Rozwiaz zadanie fizz buzz.', 'tD2Ln2MoPz', 1, 3),
    ('Parzyste liczby', 'Wypisz parzyste liczby od 0 do 10.', 'tD2Ln2MoPz', 1, 3);


INSERT INTO
    courses_tasks(course_id, task_id)
VALUES
(3, 1),
(3, 2),
(3, 3);