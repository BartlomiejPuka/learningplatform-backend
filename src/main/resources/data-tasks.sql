
INSERT INTO
    tasks(title, description, seid, order_id, course_id, url_slug)
VALUES
    ('Print hello world', 'Napisz program wypisujacy napis hello world.', 'tD2Ln2MoPz', 1, 12, 'hello-world-task'),
    ('Fizz buzz', 'Rozwiaz zadanie fizz buzz.', 'LSJ5HmtmAu', 2, 12, 'fizz-buzz-task'),
    ('Parzyste liczby', 'Wypisz parzyste liczby od 0 do 10.', 'Y3B599AdUW', 3, 12, 'even-numbers-task'),
    ('Print hello world', 'Napisz program wypisujacy napis hello world.', 'tD2Ln2MoPz', 1, 10, 'hello-world-task'),
    ('Fizz buzz', 'Rozwiaz zadanie fizz buzz.', 'LSJ5HmtmAu', 2, 10, 'fizz-buzz-task'),
    ('Parzyste liczby', 'Wypisz parzyste liczby od 0 do 10.', 'Y3B599AdUW', 3, 10, 'even-numbers-task');



INSERT INTO
    courses_tasks(course_id, task_id)
VALUES
    (12, 1),
    (12, 2),
    (12, 3),
    (10, 4),
    (10, 5),
    (10, 6);