INSERT INTO users (created_at, username, password, email, first_name, last_name, enabled)
VALUES
    (now(), 'test1', 'test1', 'test1@gmail.com', 'test1', 'test1', 0),
    (now(), 'test2', 'test2', 'test2@gmail.com', 'test2', 'test2', 1)
;
INSERT INTO courses(id,title, description)
VALUES
    (1,'Programista Javy I', 'Ten kurs jest przeznaczony dla osób początkujących, które nigdy wcześniej niczego nie programowały. Poznasz dzięki niej klasy, obiekty, metody, zmienne, typy danych, tablice, operatory warunkowe i pętle. Nauczysz sie podstaw kolekcji i programowania obiektowego, a także zaczniesz pracować w IntelliJ IDEA.'),
    (2,'Programista Python I', '')
;
INSERT INTO subcourses(course_id, title, description)
VALUES
    (1, 'Składnia Java', 'Kurs przedstawiający składnie Javy.'),
    (2, 'Podstawy Java', 'Kurs przedstawiający podstawy Javy.'),
    (3, 'Kolekcje w Java', 'Kurs przedstawiający kolekcje Javy.'),
    (4, 'Wielowątkowość w Java', 'Kurs przedstawiajacy wielowątkowść w Javie.')
;
INSERT INTO courses_sub_courses_entities(course_id, sub_courses_id)
VALUES
    (1,1), (1,2), (1,3), (1,4)
;
INSERT INTO lessons(sub_course_id, title, description)
VALUES
    (1, 'Wprowadzenie', ''),
    (2, 'Wprowadzenie do Java: wyswietlanie na ekranie, typy String i int', ''),
    (3, 'Wprowadzenie do Java: zmienne, metody, klasy', ''),
    (4, 'Twoj pierwszy program: wprowadzenie danych z klawiatury, praca z IDE', ''),
    (5, 'Wprowadzenie do instrukcji sterujących i pętli', ''),
    (6, 'Wprowadzenie do klas: pisanie własnych klas, konstruktory', ''),
    (7, 'Wprowadzenie do obiektów: pisanie własnych obiektów, cykl życia, zmienne statyczne', ''),
    (8, 'Tablice i listy: Array, ArrayList, wprowadzenie do typów generycznych', ''),
    (9, 'Kolekcje: LinkedList, HashSet, HashMap, Date', ''),
    (10, 'Wprowadzenie do wyjątków: try, catch, throws, multi-catch', ''),
    (11, 'Konwersja typów prostych: konwersje rozszerzające i zawężające', '')
;
INSERT INTO enrollments(course_id, enrollment_date, user_id)
VALUES
    (1, now(), 1)
;