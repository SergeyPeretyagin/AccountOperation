Создал метод для добавления клиентов в бд. При добавлении клиентов им создается счет.
Подключил liquibase. Создаются две таблицы "client" и "account".
Делаю инсерт двух клиентов (Ivan Ivanov, Petr Petrov) и их счетов(40812345678901112131,40811223344556677889), с балансом 1000.
Есть валидация на полях. Счет должен быть длиной 20 чисел. Сумма для операций не больше 16 чисел до ".",  и 3х после.
Креды подключения к бд в properties
