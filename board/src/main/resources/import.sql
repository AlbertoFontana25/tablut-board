INSERT INTO `user_roles` (id_user_role, role_name) VALUES (1, 'ADMIN');
INSERT INTO `user_roles` (id_user_role, role_name) VALUES (2, 'USER');

INSERT INTO `tournaments` (name, year) VALUES ('test', 2021);

INSERT INTO `teams` (name, id_tournament) VALUES ('team-1', 1);
INSERT INTO `players` (name, shell_script, type, id_team) VALUES ('player-b-1', '/teams/1/black.sh', 'BLACK', 1);
INSERT INTO `players` (name, shell_script, type, id_team) VALUES ('player-w-1', '/teams/1/white.sh', 'WHITE', 1);

INSERT INTO `teams` (name, id_tournament) VALUES ('team-2', 1);
INSERT INTO `players` (name, shell_script, type, id_team) VALUES ('player-b-2', 'fake', 'BLACK', 2);
INSERT INTO `players` (name, shell_script, type, id_team) VALUES ('player-w-2', 'fake', 'WHITE', 2);

INSERT INTO `teams` (name, id_tournament) VALUES ('team-3', 1);
INSERT INTO `players` (name, shell_script, type, id_team) VALUES ('player-b-3', 'fake', 'BLACK', 3);
INSERT INTO `players` (name, shell_script, type, id_team) VALUES ('player-w-3', 'fake', 'WHITE', 3);

