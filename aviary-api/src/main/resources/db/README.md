# Database migrations

## Liquibase
We use liquibase to keep track of database versions and migrations.
See [official docs](http://www.liquibase.org/documentation/).

## Gotchas
* The prod environment is not happy when a table has multiple required timestamps with implicit
  defaults. Setting explicit defaults, or the latter columns to nullable solves the issue. Related material:
  * https://dev.mysql.com/doc/refman/5.7/en/timestamp-initialization.html
  * https://dev.mysql.com/doc/refman/5.7/en/server-system-variables.html#sysvar_explicit_defaults_for_timestamp
  * https://github.com/jhipster/generator-jhipster/pull/4038