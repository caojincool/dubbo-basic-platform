drop table if exists PUB_MSG_INFO_PRODUCER;

create table PUB_MSG_INFO_PRODUCER
(
   PRODUCER_ID          numeric(20) not null,
   KEY                  varchar(64),
   MESSAGE              varchar(4000),
   QUEUE_CODE           varchar(64),
   IS_CACHE             numeric(5),
   IS_PERSISTENCE       numeric(5),
   PERSISTENCE_TYPE     varchar(64),
   CREATE_TIME          timestamp,
   primary key (PRODUCER_ID)
);