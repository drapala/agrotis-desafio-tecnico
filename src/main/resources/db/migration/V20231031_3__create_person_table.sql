	CREATE TABLE person (
    id_person BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name_person VARCHAR(255) NOT NULL,
    initial_date TIMESTAMP NOT NULL,
    final_date TIMESTAMP NOT NULL,
    info_property_id BIGINT,
    laboratory_id BIGINT,
    observation TEXT,
    FOREIGN KEY (info_property_id) REFERENCES property_info(id_property_info),
    FOREIGN KEY (laboratory_id) REFERENCES laboratory(id_laboratory)
);
