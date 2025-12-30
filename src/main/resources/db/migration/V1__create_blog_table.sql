CREATE TABLE IF NOT EXISTS blogs (
    id              BIGSERIAL PRIMARY KEY,
    title           TEXT NOT NULL,
    author_id       BIGINT NOT NULL,
    status          TEXT NOT NULL,
    content_path    TEXT NOT NULL,
    version         INT NOT NULL,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_blogs_author_id ON blogs(author_id);
