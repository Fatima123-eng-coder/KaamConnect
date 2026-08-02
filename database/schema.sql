CREATE DATABASE practice;
USE practice;
CREATE TABLE Users(

                      user_id INT AUTO_INCREMENT PRIMARY KEY,

                      first_name VARCHAR(100) NOT NULL,
                      last_name VARCHAR(100) NOT NULL,

                      email VARCHAR(100) NOT NULL UNIQUE,

                      password VARCHAR(255) NOT NULL,

                      phone VARCHAR(20) UNIQUE,

                      profile_image_path VARCHAR(255),

                      role ENUM(
        'ADMIN',
        'CUSTOMER',
        'WORKER'
    ) NOT NULL,

                      status ENUM(
        'ACTIVE',
        'SUSPENDED'
    ) DEFAULT 'ACTIVE',

                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE Worker_Profile(

                               user_id INT PRIMARY KEY,

                               cnic_number VARCHAR(20) NOT NULL UNIQUE,

                               cnic_front_path VARCHAR(255) NOT NULL,

                               cnic_back_path VARCHAR(255) NOT NULL,

                               bio TEXT,

                               experience_years INT DEFAULT 0,

                               city VARCHAR(100) NOT NULL,

                               service_area VARCHAR(100),

                               address VARCHAR(255),

                               approval_status ENUM(
        'PENDING',
        'APPROVED',
        'REJECTED'
    ) DEFAULT 'PENDING',

                               profile_completed BOOLEAN DEFAULT FALSE,

                               CONSTRAINT fk_worker_user
                                   FOREIGN KEY(user_id)
                                       REFERENCES Users(user_id)
                                       ON DELETE CASCADE
                                       ON UPDATE CASCADE

);
CREATE TABLE Service_Category(

                                 category_id INT AUTO_INCREMENT PRIMARY KEY,

                                 category_name VARCHAR(100) NOT NULL UNIQUE,

                                 description VARCHAR(255),

                                 icon_path VARCHAR(255)

);
CREATE TABLE Worker_Category(

                                user_id INT,

                                category_id INT,

                                PRIMARY KEY(user_id, category_id),

                                CONSTRAINT fk_wc_worker
                                    FOREIGN KEY(user_id)
                                        REFERENCES Worker_Profile(user_id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE,

                                CONSTRAINT fk_wc_category
                                    FOREIGN KEY(category_id)
                                        REFERENCES Service_Category(category_id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE

);