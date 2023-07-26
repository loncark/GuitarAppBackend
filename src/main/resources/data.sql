delete from guitar;

insert into guitar (code, name, body, neck, stock, price)
values
    ('1231', 'Fender American Vintage II 1961 Stratocaster', 'ALDER', 'ROSEWOOD', 2, 2449.00),
    ('1232', 'Fender American Ultra Telecaster', 'EBONY', 'MAPLE', 3, 2649.00),
    ('1233', 'Fender Gold Foil Jazzmaster', 'MAHOGANY', 'ROSEWOOD', 4, 1649.00),
    ('1234', 'Gibson Les Paul Standard 60s', 'EBONY', 'ROSEWOOD', 5, 2999.00),
    ('1235', 'Gibson SG Special', 'MAHOGANY', 'MAPLE', 1, 1599.00),
    ('1236', 'Ibanez RG450DX', 'ALDER', 'MAPLE', 8, 449.00);

delete from userinfo;

/*johnpassword, markpassword, lukepassword, matthewpassword*/
insert into userinfo (name, password, roles)
values ( 'John', '$2a$10$jfi6MfM2yLz9/EXq6MXDEefljQUBJsz7zrbHmduuqnl.ux9KPgTYi', 'ROLE_ADMIN' ),
       ( 'Mark', '$2a$10$lngh53VQJRIeY1hOHaQEZ.M/7Obv8WTV2D6g/IbjodfgH7pf/ocZC', 'ROLE_USER' ),
       ( 'Luke', '$2a$10$d8hKlo4xzuXyrM2vXBJ8yeiBjmMJB2cg/.uQSOE5ZoytIJDXsOaCC', 'ROLE_USER' ),
       ( 'Matthew', '$2a$10$fee/dAG7dZ2CtFTEsYQRIuMPGv7e.egp87t8ItXTVBHO9lXfh1QIe', 'ROLE_USER' );

delete from refreshtoken;