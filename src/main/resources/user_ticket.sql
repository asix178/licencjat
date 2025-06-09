create view public.user_ticket(user_id, ticket_id) as
SELECT u.domain_id  AS user_id,
       lt.domain_id AS ticket_id
FROM app_user u
         LEFT JOIN app_user_lottery_tickets ault ON u.id = ault.user_entity_id
         LEFT JOIN lottery_ticket lt ON lt.id = ault.lottery_tickets_id;

alter table public.user_ticket
    owner to admin;

