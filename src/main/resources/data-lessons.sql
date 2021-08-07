INSERT INTO
    lessons(order_id, title, description, url_slug, course_id)
VALUES
    (1, 'Programowanie obiektowe', 'Przykladowa lekcja pokazujaca hello world.', 'programowanie-obiektowe', 12),
    (2, 'Wstęp do obiektów w Javie', 'Przykladowa lekcja pokazujaca składnie.', 'obiekty', 12),
    (3, 'Uruchamianie aplikacji', 'Przykladowa lekcja pokazujaca kontrole przebiegu programu.', 'aplikacja', 12),
    (4, 'Interakcja z użytkownikiem', 'Przykladowa lekcja pokazujaca kontrole przebiegu programu.', 'interakcja', 12),
    (5, 'Konwersja typów', 'Przykladowa lekcja pokazujaca kontrole przebiegu programu.', 'konwersja-typow' ,12),
    (6, 'Funkcje', 'Przykladowa lekcja pokazujaca kontrole przebiegu programu.', 'funkcje',12),
    (7, 'Klasy', 'Przykladowa lekcja pokazujaca kontrole przebiegu programu.', 'klasy',12),
    (8, 'Interfejsy', 'Przykladowa lekcja pokazujaca kontrole przebiegu programu.', 'interfejsy', 12);



INSERT INTO
    courses_lessons(course_id, lesson_id)
VALUES
    (12, 1),
    (12, 2),
    (12, 3),
    (12, 4),
    (12, 5),
    (12, 6),
    (12, 7),
    (12, 8);
