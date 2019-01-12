drop table if exists PUB_MSG_INFO_CONSUMER;

create table PUB_MSG_INFO_CONSUMER
(
   CONSUMER_ID          numeric(20) not null,
   KEY                  varchar(64),
   QUEUE_CODE           varchar(64),
   IS_CACHE             numeric(5),
   IS_PERSISTENCE       numeric(5),
   CREATE_TIME          timestamp,
   primary key (CONSUMER_ID)
);
