insert into player VALUES (0, now(), now(), 'player 0');

insert into world VALUES (0, now(), now(), 'description of world 0', 'world 0', 0);

insert into message VALUES (0, now(), now(), 'content0', 'chat', 'sender0', 0);

insert into campaign VALUES (0, now(), now(), 'campaign 0', 0);

insert into scenario VALUES (0, now(), now(), 'scenario 0', 0);

insert into scene VALUES (0, now(), now(), 'scene 0', 0);

insert into character VALUES (0, now(), now(), 3, 'kratos', 3, 'i get +2 when forcefully attacking in melee; once a session i can rage', null, null, null);

insert into aspect VALUES (0, now(), now(), 'aspect 0', 0, false, 0);
insert into aspect VALUES (1, now(), now(), 'i am the god of war', 0, false, 0);
insert into aspect VALUES (2, now(), now(), 'my boy', 0, false, 0);
insert into aspect VALUES (3, now(), now(), 'approach forcefully', 0, false, 0);
insert into aspect VALUES (4, now(), now(), 'i say "boy" a lot', 0, false, 0);
insert into aspect VALUES (5, now(), now(), 'i carry my dead wife s ashes', 0, false, 0);

insert into measurable_aspect VALUES (0, now(), now(), 4, 3);

update character set high_concept_id = 1;
update character set dilemma_id = 2;

insert into character_aspects VALUES (0, 3);
insert into character_aspects VALUES (0, 4);
insert into character_aspects VALUES (0, 5);


