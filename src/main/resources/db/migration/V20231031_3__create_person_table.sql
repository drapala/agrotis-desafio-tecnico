	CREATE TABLE person (
    id_person BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name_person VARCHAR(255) NOT NULL,
    initial_date TIMESTAMP NOT NULL,
    final_date TIMESTAMP NOT NULL,
    id_property_info BIGINT,
    id_laboratory BIGINT,
    observation TEXT,
    FOREIGN KEY (id_property_info) REFERENCES property_info(id_property_info),
    FOREIGN KEY (id_laboratory) REFERENCES laboratory(id_laboratory)
);
