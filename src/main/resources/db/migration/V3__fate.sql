ALTER TABLE world
ADD COLUMN description varchar;

UPDATE world SET description = 'description of world 0' where id = 0;