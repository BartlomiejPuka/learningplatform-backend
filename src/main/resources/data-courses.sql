INSERT INTO
    courses(author, description, price, title, category_id, icon_url, url_slug, detailed_description, recipient_description)
VALUES
    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Spring Framework', 1, 'assets/course-icons/spring-framework.png', 'spring-framework', null, null),
    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Zaawansowana Java', 1, 'assets/course-icons/zaawansowana-java.png', 'zaawansowana-java', null, null),

    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Spring Security', 1, 'assets/course-icons/spring-security.png', 'spring-security', null, null),

    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Podstawy C++', 2, 'assets/course-icons/podstawy-cpp.png', 'podstawy-cpp', null, null),
    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Zaawansowany C++', 2, 'assets/course-icons/zaawansowany-cpp.png', 'zaawansowany-cpp', null, null),
    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'C++ STL', 2, 'assets/course-icons/cpp-stl.png', 'cpp-stl', null, null),
    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Wzorce projektowe w C++', 2, 'assets/course-icons/wzorce-w-cpp.png', 'wzorce-projektowe-w-cpp', null, null),

    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Podstawy C#', 3, 'assets/course-icons/podstawy-cs.png', 'podstawy-cs', null, null),
    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Zaawansowany C#', 3, 'assets/course-icons/zaawansowany-cs.png', 'zaawansowany-cs', null, null),

    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Podstawy Python', 4, 'assets/course-icons/podstawy-python.png', 'podstawy-python', null, null),
    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Zaawansowany Python', 4, 'assets/course-icons/zaawansowany-python.png', 'zaawansowany-python', null, null),


    ('Bartłomiej Puka', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 99.9, 'Podstawy Java', 1, 'assets/course-icons/podstawy-java.png', 'podstawy-java',
     'PODSTAWY JAVY to kompleksowy kurs stworzony z myślą o osobach początkujących, które pragną szybko i sprawnie nauczyć się programowania w języku Java. Kurs obejmuje wszystkie niezbędne teoretyczne i praktyczne aspekty programowania w Javie. Kurs podzielony jest na 8 lekcji, z których każda zawiera wstęp teoretyczny, praktyczne przykłady i zadania do rozwiązania.',
     'Kurs nie wymaga specjalnego przygotowania i przeznaczony jest dla osób, które nie miały styczności z programowaniem lub początkujących programistów. Jest odpowiedni także dla osób, które samodzielnie poznawali Javę i posiadają doświadczenie z innymi językami programowania.')

;

INSERT INTO
    course_scopes(course_id, scope)
VALUES
   (12, 'obsługi podstawowych narzędzi (JVM, JDK) niezbędnych do pierwszej kompilacji programu'),
   (12, 'podstawowych pojęć i założeń programowania: zmienne, stałe, operatory'),
   (12, 'operacji arytmetycznych i logicznych, porównania oraz rzutowania i konwersji typów danych'),
   (12, 'operacji sterujących: instrukcji warunkowych, wyboru, pętli itd.'),
   (12, 'struktur danych oraz funkcji i metod'),
   (12, 'tworzenia czystego kodu (komentarze, zasada DRY itp.)'),
   (12, 'programowania obiektowego (OOP) – dziedziczenia, polimorfizmu, hermetyzacji itd.'),
   (12, 'radzenia sobie z błędami oraz obsługą wyjątków')
;

INSERT INTO
    courses_scopes(course_id, scope_id)
VALUES
    (12, 1),
    (12, 2),
    (12, 3),
    (12, 4),
    (12, 5),
    (12, 6),
    (12, 7),
    (12, 8)
;