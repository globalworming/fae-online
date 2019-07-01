insert into player VALUES (0, now(), now(), 'player 0');

insert into world VALUES (0, now(), now(), 'description of world 0', 'world 0', 0);

insert into message VALUES (0, now(), now(), 'content0', 'chat', 'sender0', 0);

insert into campaign VALUES (0, now(), now(), 'spread the ashes', 0);
insert into campaign VALUES (1, now(), now(), 'lokis awakening', 0);

insert into scenario VALUES (0, now(), now(), 'an unexpected visitor', 0);
insert into scenario VALUES (1, now(), now(), 'the journey begins', 0);
insert into scenario VALUES (2, now(), now(), 'the true name', 1);
insert into scenario VALUES (3, now(), now(), 'big footsteps', 1);

insert into scene VALUES (0, now(), now(), 'kratos home', 0);
insert into scene VALUES (1, now(), now(), 'the edge of the tree circle', 0);
insert into scene VALUES (2, now(), now(), 'forest entrance', 1);
insert into scene VALUES (3, now(), now(), 'dwarven shop', 1);
insert into scene VALUES (4, now(), now(), 'on top of the mountain', 2);
insert into scene VALUES (5, now(), now(), 'travelling down the mine', 2);
insert into scene VALUES (6, now(), now(), 'plain with dragons footsteps', 3);
insert into scene VALUES (7, now(), now(), 'tunnel to the lair', 3);

insert into character VALUES (0, now(), now(), 3, 'kratos', 3, 3, 'i get +2 when forcefully attacking in melee; once a session i can rage', null, null, null);


insert into aspect VALUES (0, now(), now(), 0, false, 'aspect 0', 0);
insert into aspect VALUES (1, now(), now(), 0, false, 'i am the god of war', 0);
insert into aspect VALUES (2, now(), now(), 0, false, 'my boy', 0);
insert into aspect VALUES (3, now(), now(), 0, false, 'approach forcefully', 0);
insert into aspect VALUES (4, now(), now(), 0, false, 'i say "boy" a lot', 0);
insert into aspect VALUES (5, now(), now(), 0, false, 'i carry my dead wife s ashes', 0);
insert into aspect VALUES (6, now(), now(), 0, false, 'my bow is infused with light', 0);

insert into measurable_aspect VALUES (0, now(), now(), 4, 3);

update character set high_concept_id = 1;
update character set dilemma_id = 2;

insert into character_aspects VALUES (0, 3);
insert into character_aspects VALUES (0, 4);
insert into character_aspects VALUES (0, 5);

insert into world_characters VALUES (0, 0);

insert into mook VALUES (0, now(), now(), 'composure', 'shooting with the bow', 'Boy', 2, 0);

insert into mook_aspects VALUES (0, 6);

