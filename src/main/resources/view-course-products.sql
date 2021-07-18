
CREATE OR REPLACE VIEW view_course_products AS
SELECT
    ROW_NUMBER() OVER (ORDER BY uc.user_id) as id,
    c.id as course_id,
    c.author,
    c.description,
    c.icon_url,
    c.price,
    c.title,
    cc.category,
    uc.user_id,
    uc.id is not null as owned
FROM
    courses c
LEFT JOIN
    course_categories cc
ON
    c.category_id = cc.id
LEFT JOIN
    user_courses uc
ON
    c.id = uc.course_id;



CREATE OR REPLACE VIEW view_test AS
SELECT
    c.id as course_id,
    c.author,
    c.description,
    c.icon_url,
    c.price,
    c.title,
    null as user_id,
    0 as owned
FROM
    courses c
UNION ALL
select
    c.id as course_id,
    c.author,
    c.description,
    c.icon_url,
    c.price,
    c.title,
    uc.user_id,
    1 as owned
from
    user_courses uc
        left join
    courses c
on
        uc.course_id = c.id;
