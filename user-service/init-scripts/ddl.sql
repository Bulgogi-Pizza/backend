CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 사용자 테이블
CREATE TABLE p_user (
                        id UUID PRIMARY KEY DEFAULT (uuid_generate_v4()),
                        nickname VARCHAR(50) NOT NULL,
                        slack_email VARCHAR(100) UNIQUE NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        created_by UUID NULL,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        updated_by UUID NULL,
                        is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
                        deleted_at TIMESTAMP NULL,
                        deleted_by UUID NULL
);