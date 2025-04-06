-- DROP TABLE IF EXISTS usercommunities;
-- DROP TABLE IF EXISTS communities;
DROP TABLE IF EXISTS sharelist;
DROP TABLE IF EXISTS wantlist;
DROP TABLE IF EXISTS profiles;

CREATE TABLE profiles
(
    username  VARCHAR(100) PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname  VARCHAR(100) NOT NULL,
    longitude NUMERIC,
    latitude  NUMERIC,
    password  VARCHAR(100) NOT NULL,
    radius    INTEGER,
    contact   VARCHAR(100) NOT NULL
);

INSERT INTO profiles (username, password, firstname, lastname, longitude, latitude, radius, contact)
VALUES
    ('christine', 'meow1234', 'Christine', 'Doe', -122.4194, 37.7749, 10, 'contact1@gmail.com'),
('avi', 'meow5678', 'Avi', 'Doe', -118.2437, 34.0522, 15, 'contact2@gmail.com'),
('piyusha', 'meow91011', 'Piyusha', 'Doe', -74.0060, 40.7128, 20, 'contact3@gmail.com'),
('harper', 'meow121314', 'Harper', 'Doe', -87.6298, 41.8781, 3, 'contact4@gmail.com'),
('charity', 'meow151617', 'Charity', 'Doe', -73.9352, 40.7306, 10, 'contact5@gmail.com');

CREATE TABLE sharelist
(
    username       VARCHAR(100),
    ingredient     VARCHAR(100),
    quantity       NUMERIC NOT NULL,
    units          VARCHAR(50) NOT NULL,
    expiration_date DATE,
    PRIMARY KEY (username, ingredient),
    CONSTRAINT fk_share_user FOREIGN KEY (username) REFERENCES profiles(username) ON DELETE CASCADE
);

INSERT INTO sharelist (username, ingredient, quantity, units, expiration_date)
VALUES
    ('christine', 'sugar', 1, 'tsp', '2025-06-01'),
('christine', 'spinach', 4, 'cups', '2025-05-15'),
('christine', 'rice', 500, 'g', '2025-07-01'),
('christine', 'olive oil', 1, 'L', '2025-08-10'),
('christine', 'bread', 8, 'slices', '2025-04-25'),
('avi', 'spinach', 4, 'cups', '2025-05-15'),
('piyusha', 'rice', 500, 'g', '2025-07-01'),
('harper', 'olive oil', 1, 'L', '2025-08-10'),
('charity', 'bread', 8, 'slices', '2025-04-25');

CREATE TABLE wantlist
(
    username   VARCHAR(100),
    ingredient VARCHAR(100),
    quantity   NUMERIC NOT NULL,
    units      VARCHAR(50) NOT NULL,
    PRIMARY KEY (username, ingredient),
    CONSTRAINT fk_share_user FOREIGN KEY (username) REFERENCES profiles(username) ON DELETE CASCADE
);

INSERT INTO wantlist (username, ingredient, quantity, units)
VALUES
    ('christine', 'sugar', 1, 'tsp'),
('avi', 'spinach', 4, 'cups'),
('piyusha', 'rice', 500, 'g'),
('piyusha', 'beans', 500, 'g'),
('piyusha', 'pesto', 500, 'g'),
('harper', 'olive oil', 1, 'L'),
('charity', 'bread', 8, 'slices');

-- CREATE TABLE communities
-- (
--     communityname VARCHAR(100) PRIMARY KEY,
--     description TEXT
-- );
--
-- INSERT INTO communities (communityname, description)
-- VALUES
--     ('UBC Garden Club', 'A community for gardening enthusiasts to share fresh produce.'),
--     ('test2', 'description2'),
--     ('test3', 'description3'),
--     ('test4', 'description4'),
--     ('test5', 'description5');
--
--
-- CREATE TABLE usercommunities
-- (
--     communityname VARCHAR(100),
--     username      VARCHAR(100),
--     PRIMARY KEY (communityname, username),
--     CONSTRAINT fk_uc_community FOREIGN KEY (communityname) REFERENCES communities(communityname) ON DELETE CASCADE,
--     CONSTRAINT fk_uc_user FOREIGN KEY (username) REFERENCES profiles(username) ON DELETE CASCADE
-- );
--
-- INSERT INTO usercommunities (communityname, username)
-- VALUES
--     ('UBC Garden Club', 'christine'),
--     ('UBC Garden Club', 'avi'),
--     ('test2', 'piyusha'),
--     ('test
