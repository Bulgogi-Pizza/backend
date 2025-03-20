-- 기존 데이터 삭제
DELETE
FROM p_hub;
DELETE
FROM p_center_spoke_hub_link;

-- 서울특별시 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '서울특별시 센터', 'SPOKE', '서울특별시 송파구 송파대로 55', 37.506476, 127.105021);

-- 경기 북부 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '경기 북부 센터', 'SPOKE', '경기도 고양시 덕양구 권율대로 570', 37.655312, 126.837468);

-- 경기 남부 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '경기 남부 센터', 'HUB', '경기도 이천시 덕평로 257-21', 37.270838, 127.482994);

-- 부산광역시 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '부산광역시 센터', 'SPOKE', '부산 동구 중앙대로 206', 35.103800, 129.040795);

-- 대구광역시 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '대구광역시 센터', 'HUB', '대구 북구 태평로 161', 35.867060, 128.609730);

-- 인천광역시 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '인천광역시 센터', 'SPOKE', '인천 남동구 정각로 29', 37.456590, 126.705051);

-- 광주광역시 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '광주광역시 센터', 'SPOKE', '광주 서구 내방로 111', 35.159545, 126.851430);

-- 대전광역시 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '대전광역시 센터', 'HUB', '대전 서구 둔산로 100', 36.350411, 127.384548);

-- 울산광역시 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '울산광역시 센터', 'SPOKE', '울산 남구 중앙로 201', 35.540017, 129.311136);

-- 세종특별자치시 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '세종특별자치시 센터', 'SPOKE', '세종특별자치시 한누리대로 2130', 36.480286, 127.289372);

-- 강원특별자치도 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '강원특별자치도 센터', 'SPOKE', '강원특별자치도 춘천시 중앙로 1', 37.880220, 127.727746);

-- 충청북도 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '충청북도 센터', 'SPOKE', '충북 청주시 상당구 상당로 82', 36.635698, 127.492606);

-- 충청남도 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '충청남도 센터', 'SPOKE', '충남 홍성군 홍북읍 충남대로 21', 36.635342, 126.679073);

-- 전북특별자치도 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '전북특별자치도 센터', 'SPOKE', '전북특별자치도 전주시 완산구 효자로 225', 35.822823,
        127.150739);

-- 전라남도 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '전라남도 센터', 'SPOKE', '전남 무안군 삼향읍 오룡길 1', 34.999219, 126.719442);

-- 경상북도 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '경상북도 센터', 'SPOKE', '경북 안동시 풍천면 도청대로 455', 36.565768, 128.739294);

-- 경상남도 센터
INSERT INTO p_hub (id, name, type, address, latitude, longitude)
VALUES (uuid_generate_v4(), '경상남도 센터', 'SPOKE', '경남 창원시 의창구 중앙대로 300', 35.228541, 128.681829);


-- 경기남부 - 경기북부 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '경기남부 센터'),
        (SELECT id FROM p_hub WHERE name = '경기북부'));

-- 경기남부 - 서울 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '경기남부 센터'),
        (SELECT id FROM p_hub WHERE name = '서울특별시 센터'));

-- 경기남부 - 인천 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '경기남부 센터'),
        (SELECT id FROM p_hub WHERE name = '인천광역시 센터'));

-- 경기남부 - 강원도 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '경기남부 센터'),
        (SELECT id FROM p_hub WHERE name = '강원특별자치도 센터'));

-- 대전 - 충청남도 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대전광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '충청남도 센터'));

-- 대전 - 충청북도 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대전광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '충청북도 센터'));

-- 대전 - 세종 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대전광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '세종특별자치시 센터'));

-- 대전 - 전라북도 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대전광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '전라북도 센터'));

-- 대전 - 광주 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대전광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '광주광역시 센터'));

-- 대전 - 전라남도 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대전광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '전라남도 센터'));

-- 대구 (5) 연결
-- 대구 - 경상북도 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대구광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '경상북도 센터'));

-- 대구 - 경상남도 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대구광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '경상남도 센터'));

-- 대구 - 부산 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대구광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '부산광역시 센터'));

-- 대구 - 울산 연결
INSERT INTO p_center_spoke_hub_link (id, center, spoke)
VALUES (uuid_generate_v4(),
        (SELECT id FROM p_hub WHERE name = '대구광역시 센터'),
        (SELECT id FROM p_hub WHERE name = '울산광역시 센터'));