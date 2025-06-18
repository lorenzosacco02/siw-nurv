INSERT INTO tratta (nome, descrizione) VALUES
                                                     ('Tratta A', 'Linea che collega Milano a Torino'),
                                                     ('Tratta B', 'Linea ad alta velocità tra Roma e Napoli'),
                                                     ('Tratta C', 'Collegamento costiero tra Genova e Livorno'),
                                                     ('Tratta D', 'Linea montana tra Trento e Bolzano'),
                                                     ('Tratta E', 'Tratta urbana per il trasporto regionale a Milano');


-- ADMIN con password 'admin'--
INSERT INTO users (name, surname, email) VALUES
    ('Admin', 'Admin', 'admin.admin@gmail.com');
INSERT INTO credentials (username, password, role, user_id) VALUES
    ('admin', '$2a$10$YU4NfWwyGIv9oKtdkxEMbORrWITRGP5JZG536M587vZbz40zuGf4y', 'ADMIN', 1);