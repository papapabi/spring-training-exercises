INSERT INTO Country (iso2, iso3, name) VALUES ('GB', 'GBR', 'United Kingdom');
INSERT INTO Country (iso2, iso3, name) VALUES ('JP', 'JPN', 'Japan');
INSERT INTO Country (iso2, iso3, name) VALUES ('PH', 'PHL', 'Philippines');
INSERT INTO Country (iso2, iso3, name) VALUES ('US', 'USA', 'United States of America');

INSERT INTO Party (id, type, name, gender) VALUES (101, 'PER', 'John', 0);
INSERT INTO Party (id, type, name, gender) VALUES (102, 'PER', 'Sally', 1);
INSERT INTO Party (id, type, name, gender) VALUES (103, 'PER', 'Bill', 0);
INSERT INTO Party (id, type, name, gender) VALUES (104, 'PER', 'Megan', 1);

INSERT INTO Party (id, type, name) VALUES (105, 'ORG', 'Acme Corporation');
INSERT INTO ContactMechanism (partyId, type, line2, region, country_iso2) VALUES (105, 'ADDR', 'Las Vegas', 'NV', 'US');
INSERT INTO Party (id, type, name) VALUES (106, 'ORG', 'Standard Oil');
INSERT INTO ContactMechanism (partyId, type, line2, region, country_iso2) VALUES (106, 'ADDR', 'New York', 'NY', 'US');
INSERT INTO Party (id, type, name) VALUES (107, 'ORG', 'Applied Materials');
INSERT INTO ContactMechanism (partyId, type, line2, region, country_iso2) VALUES (107, 'ADDR', 'Santa Clara', 'CA', 'US');
INSERT INTO Party (id, type, name) VALUES (108, 'ORG', 'Bell Labs');
INSERT INTO ContactMechanism (partyId, type, line2, region, country_iso2) VALUES (108, 'ADDR', 'Murray Hill', 'NJ', 'US');
INSERT INTO Party (id, type, name) VALUES (109, 'ORG', 'AT&T');
INSERT INTO ContactMechanism (partyId, type, line2, region, country_iso2) VALUES (109, 'ADDR', 'Dallas', 'TX', 'US');
INSERT INTO Party (id, type, name) VALUES (110, 'ORG', 'Apple');
INSERT INTO ContactMechanism (partyId, type, line2, region, country_iso2) VALUES (110, 'ADDR', 'Cupertino', 'CA', 'US');
INSERT INTO ContactMechanism (partyId, type, areaCode, number, country_iso2) VALUES (110, 'TELE', '800', '2752273', 'US');
INSERT INTO Party (id, type, name) VALUES (111, 'ORG', 'San Miguel Corporation');
INSERT INTO ContactMechanism (partyId, type, line2, region, country_iso2) VALUES (111, 'ADDR', 'Mandaluyong', 'Metro Manila', 'PH');
INSERT INTO ContactMechanism (partyId, type, areaCode, number, country_iso2) VALUES (111, 'TELE', '32', '6323000', 'PH');
INSERT INTO Party (id, type, name) VALUES (112, 'ORG', 'Virgin Atlantic');
INSERT INTO ContactMechanism (partyId, type, line2, region, country_iso2) VALUES (112, 'ADDR', 'Craley', 'West Sussex', 'GB');
INSERT INTO ContactMechanism (partyId, type, areaCode, number, country_iso2) VALUES (112, 'TELE', '844', '8110000', 'GB');
