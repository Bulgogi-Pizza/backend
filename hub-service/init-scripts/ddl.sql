CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 허브 테이블
CREATE TABLE p_hub (
                       id UUID PRIMARY KEY DEFAULT (uuid_generate_v4()),
                       name VARCHAR(100) NOT NULL,
                       type ENUM('HUB', 'SPOKE') NOT NULL,
                       address VARCHAR(255) NOT NULL,
                       coordinates GEOGRAPHY(POINT, 4326) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       created_by VARCHAR(50) UNIQUE NOT NULL,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_by VARCHAR(50) NOT NULL,
                       is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
                       deleted_at TIMESTAMP NULL,
                       deleted_by VARCHAR(50) NULL
);