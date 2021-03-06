insert into company(company_name)
    values ('NSU'),
            ('Google'),
            ('Yandex'),
           ('Yahoo'),
           ('Oracle');

insert into contract(company_id)
    values (1),
           (2),
           (3),
           (4),
           (5);

insert into bonus(contract_id, bonus)
    values (1, 'Бесплатное посещение лекцмй'),
           (2, 'Бесплатный 5G'),
           (3, 'Минибар бесплатно'),
           (4, 'Десерты бесплатно'),
           (5,'Расширенный список каналов на ТВ');

insert into client(client_name, client_surname, first_appearance)
    values ('Сергей','Петренко', '2018-2-15'),
           ('Иван', 'Афанасьеф', '2018-3-17'),
           ('Матвей', 'Масыч', '2018-4-5'),
           ('Глеб', 'Проскурин', '2018-4-23'),
           ('Григорий', 'Караваев', '2019-6-18');

insert into building(level, building_height)
    values (1,5),
            (2,4),
            (3,3),
            (4,2),
            (5,2);

insert into room(room_capacity, room_floor, room_profit, building_id)
    values (1,1,1000,1),
           (1,2,1200,1),
           (1,3,1400,1),
           (1,4,1600,1),
           (1,5,1800,1),

           (1,1,2000,2),
           (1,2,2400,2),
           (1,3,2800,2),
           (1,4,3200,2),

           (2,1,3000,3),
           (2,2,3600,3),
           (2,3,4200,3),

           (2,1,5000,4),
           (2,2,6000,4),

           (1,1,15000,5),
           (2,2,50000,5);

insert into service(service_name, service_price, building_id)
    values ('Уборка номера', 500, 1),
           ('Завтрак', 100, 1),
           ('Комплексный обед',180,1),
           ('Ужин', 200, 1),
           ('Доступ к wi-fi на день', 50, 1),

           ('Завтрак', 200, 2),
           ('Обед', 250,2),
           ('Ужин', 230, 2),
           ('Аэорохоккей 1 игра', 80,2),
           ('Прачечная', 500,2),

           ('Десерт к обеду', 300, 3),
           ('Настольный теннис 30 минут', 150, 3),
           ('Партия с бильярд', 500, 3),
           ('Доступ на смотровую площадку', 1000,3),
           ('Бассейн 1 час', 350, 3),

           ('Гольф', 5000, 4),
           ('Бильярд 1 партия', 1000, 4),
           ('Боулинг 1 партия', 1500, 4),

           ('Экскурсия', 10000,5),
           ('Услуги личного водителя 1 день', 13000,5);

insert into reservation(start_date, end_date ,building_id)
    values ('2018-2-15', '2018-2-26', 1),
           ('2018-3-17', '2018-4-10', 2),
           ('2018-4-5', '2018-4-13', 3),
           ('2018-4-23', '2018-5-4', 4),
           ('2019-6-18','2019-6-21',5);

insert into guest(guest_review, review_type, client_id, company_id, reservation_id, room_id)
    values ('Уютные номера', true, 1, 1, 1, 2),
           ('Добрый персонал', true, 2, 2, 2, 6),
           ('Тихие соседи', true, 3, 3, 3, 11),
           ('Красивый вид из окна', true, 4,4,4, 14),
           ('Недостаточно вежливый персонал', false, 5, 5, 5, 16);

insert into receipt(guest_id, service_id)
    values (1,2),
           (2,9),
           (3,12),
           (4,17),
           (5, 19);