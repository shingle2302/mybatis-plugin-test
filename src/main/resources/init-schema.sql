CREATE TABLE IF NOT EXISTS t_user (
                                    id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY,
                                    username VARCHAR(255) NOT NULL UNIQUE,
                                    password VARCHAR(255) NOT NULL,
                                    email VARCHAR(255),
                                    created_at TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);