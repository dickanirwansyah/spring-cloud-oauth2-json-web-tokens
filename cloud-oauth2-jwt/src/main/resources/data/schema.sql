CREATE TABLE oauth_client_details (
client_id VARCHAR(255) NOT NULL PRIMARY KEY,
client_secret VARCHAR(255) NOT NULL,
resource_ids VARCHAR(255) DEFAULT NULL,
scope VARCHAR(255) DEFAULT NULL,
authorized_grant_types VARCHAR(255) DEFAULT NULL,
web_server_redirect_uri VARCHAR(255) DEFAULT NULL,
authorities VARCHAR(255) DEFAULT NULL,
access_token_validity INT(11) DEFAULT NULL,
refresh_token_validity INT(11) DEFAULT NULL,
additional_information VARCHAR(4096) DEFAULT NULL,
autoapprove VARCHAR(255) DEFAULT NULL);

 INSERT INTO oauth_client_details (
	client_id,client_secret,
	resource_ids,
	scope,
	authorized_grant_types,
	web_server_redirect_uri,authorities,
	access_token_validity,refresh_token_validity,
	additional_information,autoapprove)
	VALUES(
    'USER_CLIENT_APP','{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi',
	'USER_CLIENT_RESOURCE,USER_ADMIN_RESOURCE',
	'role_admin,role_user',
	'authorization_code,password,refresh_token,implicit',
	NULL,NULL,
	900,3600,
	'{}',NULL);



CREATE TABLE permission (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(60) UNIQUE KEY);

INSERT INTO permission (name) VALUES
('can_create_user'),
('can_update_user'),
('can_read_user'),
('can_delete_user');



		CREATE TABLE role
		(id INT PRIMARY KEY AUTO_INCREMENT,
		name VARCHAR(60) UNIQUE KEY);


		INSERT INTO role (name) VALUES
		('role_admin'),('role_user');




	CREATE TABLE permission_role(
    permission_id INT,
    FOREIGN KEY(permission_id) REFERENCES permission(id),
    role_id INT,
    FOREIGN KEY(role_id) REFERENCES role(id));

    INSERT INTO permission_role (permission_id, role_id) VALUES
    (1,1), /* can_create_user assigned to role_admin */
    (2,1), /* can_update_user assigned to role_admin */
    (3,1), /* can_read_user assigned to role_admin */
    (4,1), /* can_delete_user assigned to role_admin */

    (3,2);  /* can_read_user assigned to role_user */





	CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(24) UNIQUE KEY NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    enabled BIT(1) NOT NULL,
    account_expired BIT(1) NOT NULL,
    credentials_expired BIT(1) NOT NULL,
    account_locked BIT(1) NOT NULL);





	INSERT INTO users (
    username,password,
    email,enabled,account_expired,credentials_expired,account_locked) VALUES (
    'admin','{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi',
    'william@gmail.com',1,0,0,0),
    ('user','{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi',
    'john@gmail.com',1,0,0,0);



	CREATE TABLE users_role (role_id INT,FOREIGN KEY(role_id) REFERENCES role(id),
    user_id INT, FOREIGN KEY(user_id) REFERENCES users(id));


    INSERT INTO users_role(role_id, user_id)
    VALUES
    (1, 1) /* role_admin assigned to admin users */,
    (2, 2) /* role_user assigned to user users */ ;

