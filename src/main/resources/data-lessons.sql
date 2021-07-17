INSERT INTO
    lessons(order_id, title, description, course_id)
VALUES
    (1, 'Pierwsza aplikacja', 'Przykladowa lekcja pokazujaca hello world.', 3),
    (2, 'Podstawowa składnia', 'Przykladowa lekcja pokazujaca składnie.', 3),
    (3, 'Kontrola przebiegu programu', 'Przykladowa lekcja pokazujaca kontrole przebiegu programu.', 3);



INSERT INTO
    courses_lessons(course_id, lesson_id)
VALUES
    (3, 1),
    (3, 2),
    (3, 3);