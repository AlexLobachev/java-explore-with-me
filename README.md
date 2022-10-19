
Template repository for ExploreWithMe project.
# java-explore-with-me (англ. «исследуй со мной»)
## Ссылка проекта на github [ExploreWithMe](https://github.com/HONDACIVIC5DDDDD/java-explore-with-me/pull/1)


# Описание 
Свободное время — ценный ресурс. Ежедневно мы планируем, как его потратить — куда и с кем сходить. Сложнее всего в таком планировании поиск информации и переговоры. Какие намечаются мероприятия, свободны ли в этот момент друзья, как всех пригласить и где собраться. Это приложение — афиша, где можно предложить какое-либо событие от выставки до похода в кино и набрать компанию для участия в нём.

![alt text](https://github.com/HONDACIVIC5DDDDD/java-explore-with-me/blob/develop/application.png)
# Описание сервисов
**Приложение состоит из 3-х сервисов и 2-х БД:**

- *Основной сервис (ewm-main-service-spec)*
  - API основного сервиса делится на три части. Первая — публичная, доступна без регистрации любому пользователю сети. Вторая — закрытая, доступна только авторизованным пользователям. Третья — административная, для администраторов сервиса.
    - Структурная схема:

      ![alt text](https://github.com/HONDACIVIC5DDDDD/java-explore-with-me/blob/develop/ewm-main-service-spec.png)
- *Сервис статистики (ewm-stats-service)*
  - Сервис статистики, призван собирать информацию. Во-первых, о количестве обращений пользователей к спискам событий и, во-вторых, о количестве запросов к подробной информации о событии. На основе этой информации должна формироваться статистика о работе приложения.
    - Структурная схема:

      ![alt text](https://github.com/HONDACIVIC5DDDDD/java-explore-with-me/blob/develop/ewm-stats-service.png)
- *Сервис "шлюз" (gateway)*
  - Выполняет *первоначальную* валидацию входящих запросов без обращения к БД.
    - Структурная схема:

      ![alt text](https://github.com/HONDACIVIC5DDDDD/java-explore-with-me/blob/develop/gateway.png)
- *Две базы данных ewm-db и stats-db*
  - Структурная схема БД ewm-db
  
    ![alt text](https://github.com/HONDACIVIC5DDDDD/java-explore-with-me/blob/develop/event.png)
  - Структурная схема БД stats-db
  
    ![alt text](https://github.com/HONDACIVIC5DDDDD/java-explore-with-me/blob/develop/event-stat.png)



# Порядок и способ запуска приложения
- Приложение запускается в следующем порядке:
  1. ewm-db
  2. stats-db
  3. ewm-main-service-spec
  4. ewm-stats-service
  5. gateway
- Ссылка на [docker-compose](https://github.com/HONDACIVIC5DDDDD/java-explore-with-me/blob/develop/docker-compose.yaml)
- Ознакомиться с принципом и командами для запуска можно ознакомиться в оф. документации [Docker](https://docs.docker.com/engine/reference/run/)



# Спецификация API

- Для обоих сервисов существует подробная спецификация API.

    - спецификация основного сервиса: [ewm-main-service-spec.json](https://raw.githubusercontent.com/yandex-praktikum/java-explore-with-me/main/ewm-main-service-spec.json)
    - спецификация сервиса статистики: [ewm-stats-service.json](https://raw.githubusercontent.com/yandex-praktikum/java-explore-with-me/main/ewm-stats-service-spec.json)
  
- Для работы со спецификациями вам понадобится редактор [Swagger](https://editor-next.swagger.io/). Чтобы просмотреть спецификацию в редакторе, необходимо выполнить ряд шагов:

  - Скопировать ссылку на файл спецификации.
  - Открыть онлайн-версию редактора Swagger. На верхней панели выбрать меню File, затем пункт Import URL.
  - Вставить скопированную ссылку в текстовое поле появившегося диалогового окна и нажать OK.





