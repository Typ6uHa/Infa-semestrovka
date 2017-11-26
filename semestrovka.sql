CREATE TABLE role(
	id SERIAL PRIMARY KEY,
	name varchar(16));

CREATE TABLE "user"(
	id SERIAL PRIMARY KEY,
	login varchar(16),
	password varchar(32),
	nickname varchar(16),
	name varchar(32),
	surname varchar(32),
	city varchar(32),
	photo_url text,
	date_reg date,
	role integer REFERENCES role(id));

CREATE TABLE "topic"(
	id serial primary key,
	user_id integer references "user"(id),
	theme text,
	description text,
	photo_url text,
	"like" integer,
	dislike integer,
	date_post date);

create table "comment"(
	id serial primary key,
	user_id integer references "user"(id),
	topic_id integer references topic(id),
	comment_text text,
	date_comment date);

CREATE TABLE favorites(
	id SERIAL PRIMARY KEY,
	user_id integer references "user"(id),
	topic_id integer references topic(id));


CREATE FUNCTION user_pass() RETURNS trigger AS $$
	BEGIN
		if (TG_OP = 'UPDATE') THEN
			IF ((NEW.password) != (OLD.password)) THEN
				NEW.password := md5(NEW.password);
			end IF;
		ELSEIF (TG_OP = 'INSERT') THEN
			NEW.password := md5(NEW.password);
			NEW.date_reg = now();
		END IF;
		return new;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER user_pass 
BEFORE INSERT OR UPDATE 
ON "user" 
FOR EACH ROW 
EXECUTE PROCEDURE user_pass();


CREATE FUNCTION user_role() RETURNS trigger AS $$
	BEGIN
		NEW.role = 2;
		return new;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER user_role 
BEFORE INSERT 
ON "user" 
FOR EACH ROW 
EXECUTE PROCEDURE user_role();

CREATE FUNCTION create_post() RETURNS trigger AS $$
	BEGIN
		NEW.like = 0;
		NEW.dislike = 0;
		NEW.date_post = now();
		return new;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER create_post 
BEFORE INSERT 
ON "topic" 
FOR EACH ROW 
EXECUTE PROCEDURE create_post();

CREATE FUNCTION create_comment() RETURNS trigger AS $$
	BEGIN
		NEW.date_comment = now();
		return new;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER create_comment 
BEFORE INSERT 
ON "comment" 
FOR EACH ROW 
EXECUTE PROCEDURE create_comment();